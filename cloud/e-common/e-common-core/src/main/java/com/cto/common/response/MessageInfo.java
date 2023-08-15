package com.cto.common.response;

import java.io.Serializable;
import java.util.Objects;

import com.cto.common.constants.CommonResultCode;
import com.cto.common.constants.ResultCode;
import com.cto.common.utils.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 统一返回实体
 *
 * @author 张永伟
 * @since 2023/4/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageInfo<T> implements Serializable {

    private static final long serialVersionUID = -3430603013914181383L;

    protected Integer code;

    protected String messageInfo;

    protected T data;

    public MessageInfo(ResultCode resultCode, String messageInfo) {
        this.code = resultCode.getCode();
        this.messageInfo = MessageUtil.get(messageInfo);
    }

    public MessageInfo(ResultCode resultCode, T data) {
        super();
        this.code = resultCode.getCode();
        this.messageInfo = MessageUtil.get(resultCode.getMsg());
        this.data = data;
    }

    public MessageInfo(ResultCode resultCode, T data, String messageInfo) {
        super();
        this.code = resultCode.getCode();
        this.messageInfo = MessageUtil.get(messageInfo);
        this.data = data;
    }

    private MessageInfo(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.messageInfo = MessageUtil.get(resultCode.getMsg());
    }

    private MessageInfo(Integer resultCode, String messageInfo) {
        this.code = resultCode;
        this.messageInfo = MessageUtil.get(messageInfo);
    }

    public static <T> MessageInfo<T> ok() {
        return new MessageInfo<>(CommonResultCode.SUCCESS);
    }

    public static <T> MessageInfo<T> ok(T data) {
        return new MessageInfo<>(CommonResultCode.SUCCESS, data);
    }

    public static <T> MessageInfo<T> ok(T data, String messageInfo) {
        return new MessageInfo<>(CommonResultCode.SUCCESS, data, messageInfo);
    }

    public static <T> MessageInfo<T> ok(T data, ResultCode resultCode) {
        return new MessageInfo<>(resultCode, data);
    }

    public static <T> MessageInfo<T> ok(T data, Integer code, String messageInfo) {
        return new MessageInfo<>(code, messageInfo, data);
    }

    public static <T> MessageInfo<T> failed() {
        return new MessageInfo<>(CommonResultCode.SYSTEM_ERROR);
    }

    public static <T> MessageInfo<T> failed(T data) {
        return new MessageInfo<>(CommonResultCode.SYSTEM_ERROR, data);
    }

    public static <T> MessageInfo<T> failed(ResultCode resultCode) {
        return new MessageInfo<>(resultCode);
    }

    public static <T> MessageInfo<T> failed(String messageInfo) {
        return new MessageInfo<>(CommonResultCode.SYSTEM_ERROR.getCode(), messageInfo);
    }

    public static <T> MessageInfo<T> failed(Integer code, String messageInfo) {
        return new MessageInfo<>(code, messageInfo);
    }


    public static boolean isSuccess(MessageInfo<?> info) {
        if (Objects.isNull(info)) {
            return Boolean.FALSE;
        }
        return info.getCode().equals(CommonResultCode.SUCCESS.getCode());
    }

    @Override
    public String toString() {
        return "MessageInfo{" + "code=" + code + ", messageInfo=" + messageInfo + ", data=" + data + '}';
    }
}
