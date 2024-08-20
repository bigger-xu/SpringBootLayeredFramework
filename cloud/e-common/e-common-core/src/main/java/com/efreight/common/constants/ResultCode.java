package com.efreight.common.constants;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 状态码统一返回类
 *
 * @author 张永伟
 * @since 2023/4/24
 */
@Slf4j
@Getter
@Setter
public abstract class ResultCode {

    // 范围声明
    static {
        // 系统全局码，从0开始，step=1000
        ResponseCodeContainer.register(CommonResultCode.class, 0, 10000);
        // 业务异常一共6位，前两位代表业务,例如
        // 100001代表afbase返回的业务code，根据class名称看具体的业务状态码
        ResponseCodeContainer.register(AfbaseResultCode.class, 100001, 109999);
        ResponseCodeContainer.register(GatewayResultCode.class, 110001, 119999);
        ResponseCodeContainer.register(CrmResultCode.class, 120001, 129999);
        ResponseCodeContainer.register(HrsResultCode.class, 130001, 139999);
        ResponseCodeContainer.register(TmsResultCode.class, 140001, 149999);
        ResponseCodeContainer.register(WmsResultCode.class, 150001, 159999);
        ResponseCodeContainer.register(FfmResultCode.class, 160001, 169999);
        ResponseCodeContainer.register(BaseResultCode.class, 170001, 179999);
    }

    private Integer code;

    private String msg;

    private ResultCode() {
    }

    protected ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        ResponseCodeContainer.put(this);
    }

    public static void init() {
        log.info("ResultCode 初始化....");
    }

    /**
     * 内部类，用于检测code范围
     */
    @Slf4j
    private static class ResponseCodeContainer {

        private static final Map<Integer, ResultCode> RESPONSE_CODE_MAP = new HashMap<>();

        private static final Map<Class<? extends ResultCode>, Integer[]> RESPONSE_CODE_RANGE_MAP = new HashMap<>();

        /**
         * id的范围：[start, end]左闭右闭
         *
         * @param clazz 目标类
         * @param start 开始
         * @param end   结束
         */
        private static void register(Class<? extends ResultCode> clazz, Integer start, Integer end) {
            if (start > end) {
                throw new IllegalArgumentException("<ResultDTO> 开始编码不能大于结束编码!");
            }

            if (RESPONSE_CODE_RANGE_MAP.containsKey(clazz)) {
                throw new IllegalArgumentException(String.format("<ResultDTO> Class:%s 已经存在!", clazz.getSimpleName()));
            }
            RESPONSE_CODE_RANGE_MAP.forEach((k, v) -> {
                if ((start >= v[0] && start <= v[1]) || (end >= v[0] && end <= v[1])) {
                    throw new IllegalArgumentException(
                            String.format("<ResultDTO> Class:%s 's 取值范围[%d,%d] 和 class:%s" + "相交", clazz.getSimpleName(), start, end,
                                    k.getSimpleName()));
                }
            });

            RESPONSE_CODE_RANGE_MAP.put(clazz, new Integer[] { start, end });

            // 提前初始化static变量，进行范围检测
            Field[] fields = clazz.getFields();
            if (fields.length != 0) {
                try {
                    fields[0].get(clazz);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    log.error("", e);
                }
            }
        }

        private static void put(ResultCode codeConst) {
            Integer[] idRange = RESPONSE_CODE_RANGE_MAP.get(codeConst.getClass());
            if (idRange == null) {
                throw new IllegalArgumentException(
                        String.format("<ResultDTO> Class:%s 没有注册Bean!", codeConst.getClass().getSimpleName()));
            }
            Integer code = codeConst.code;
            if (code < idRange[0] || code > idRange[1]) {
                throw new IllegalArgumentException(
                        String.format("<ResultDTO> Id(%d) 超出范围[%d,%d], " + "class:%s", code, idRange[0], idRange[1],
                                codeConst.getClass().getSimpleName()));
            }
            if (RESPONSE_CODE_MAP.containsKey(code)) {
                log.error(String.format("<ResultDTO> Id(%d) 超出范围[%d,%d], " + "class:%s  编码重复!", code, idRange[0], idRange[1],
                        codeConst.getClass().getSimpleName()));
                System.exit(0);
            }
            RESPONSE_CODE_MAP.put(code, codeConst);
        }

    }

}
