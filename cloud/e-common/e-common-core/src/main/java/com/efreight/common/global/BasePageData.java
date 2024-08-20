package com.efreight.common.global;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.efreight.common.annotation.ThousandConvert;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author Libiao
 */
@Data
@Schema(description = "分页对象")
public class BasePageData<T, R> {

    /**
     * 总记录数
     */
    @Schema(description = "总记录数")
    private Long total;

    /**
     * 统计对象
     */
    @ThousandConvert
    @Schema(description = "统计对象")
    private R statistic;

    /**
     * 列表结果集
     */
    @ThousandConvert
    @Schema(description = "列表结果集")
    private List<T> records;

    public static <T, R> BasePageData<T, R> convert(IPage<T> page) {
        return convert(page, null);
    }

    public static <T, R> BasePageData<T, R> convert(IPage<T> page, R statistic) {
        return newInstance(page.getRecords(), page.getTotal(), statistic);
    }

    public static <T, R> BasePageData<T, R> newInstance(List<T> data, Long total) {
        return newInstance(data, total, null);
    }

    public static <T, R> BasePageData<T, R> newInstance() {
        return newInstance(null, null, null);
    }

    public static <T, R> BasePageData<T, R> newInstance(List<T> data, Long total, R statistic) {
        BasePageData<T, R> pageData = new BasePageData<>();
        pageData.setRecords(data);
        pageData.setTotal(total);
        pageData.setStatistic(statistic);
        return pageData;
    }
}
