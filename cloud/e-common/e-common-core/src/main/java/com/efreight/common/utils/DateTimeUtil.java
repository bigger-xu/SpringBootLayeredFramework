package com.efreight.common.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.efreight.common.constants.CommonResultCode;
import com.efreight.common.exception.EfreightBizException;
import lombok.extern.slf4j.Slf4j;

import org.springframework.util.StringUtils;

/**
 * 时间日期工具类
 *
 * @author LB
 */
@Slf4j
public class DateTimeUtil {
    
    public static final String DATE_TIME_FMT = "yyyy-MM-dd HH:mm:ss";
    
    public static final String DATE_TIME_MINUTE_FMT = "yyyy-MM-dd HH:mm";
    
    public static final String DATE_TIME_MERGE_FMT = "yyMMddHHmmss";
    
    public static final String DATE_FMT = "yyyy-MM-dd";
    
    public static final String DATE_FMT_NO_SEPARATOR = "yyyyMMdd";
    
    public static final String DATE_FMT_TWO = "yyMMdd";
    
    public static final String TIME_FMT = "HH:mm:ss";
    
    public static final String TIME_FMT_MINUTE = "HH:mm";
    
    /**
     * 将日期时间 转换成 字符串
     *
     * @param localDateTime LocalDateTime对象
     * @return 例如："2023-03-05 23:14:32"
     */
    public static String format(LocalDateTime localDateTime) {
        return format(localDateTime, DATE_TIME_FMT);
    }
    
    /**
     * 将日期时间 转换成 字符串
     *
     * @param localDateTime LocalDateTime对象
     * @param fmt           例如：DateTimeUtil.DATE_TIME_FMT
     * @return 例如："2023-03-05 23:14:32"
     */
    public static String format(LocalDateTime localDateTime, String fmt) {
        if (Objects.isNull(localDateTime)) {
            throw new EfreightBizException(CommonResultCode.PARAMS_ERROR);
        }
        if (!StringUtils.hasText(fmt)) {
            throw new EfreightBizException(CommonResultCode.PARAMS_ERROR);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(fmt);
        return localDateTime.format(dtf);
    }
    
    /**
     * 将日期时间字符串 转换成 LocalDateTime
     *
     * @param localDateTime 例如："2023-03-05 23:14:32"
     * @return LocalDateTime
     */
    public static LocalDateTime parseDateTime(String localDateTime) {
        return parseDateTime(localDateTime, DATE_TIME_FMT);
    }
    
    /**
     * 将日期时间字符串 转换成 LocalDateTime
     *
     * @param localDateTime 例如："2023-03-05 23:14:32"
     * @param fmt           例如：DateTimeUtil.DATE_TIME_FMT
     * @return LocalDateTime
     */
    public static LocalDateTime parseDateTime(String localDateTime, String fmt) {
        if (!StringUtils.hasText(localDateTime)) {
            throw new EfreightBizException(CommonResultCode.PARAMS_ERROR);
        }
        if (!StringUtils.hasText(fmt)) {
            throw new EfreightBizException(CommonResultCode.PARAMS_ERROR);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(fmt);
        return LocalDateTime.parse(localDateTime, dtf);
    }
    
    /**
     * 将时间字符串 转换成 LocalTime
     *
     * @param localTime 例如："23:14:32"
     * @return LocalTime
     */
    public static LocalTime parseTime(String localTime) {
        return parseTime(localTime, TIME_FMT);
    }
    
    /**
     * 将时间字符串 转换成 LocalTime
     *
     * @param localTime 例如："23:14:32"
     * @param fmt       例如：DateTimeUtil.TIME_FMT
     * @return LocalTime
     */
    public static LocalTime parseTime(String localTime, String fmt) {
        if (!StringUtils.hasText(localTime)) {
            throw new EfreightBizException(CommonResultCode.PARAMS_ERROR);
        }
        if (!StringUtils.hasText(fmt)) {
            throw new EfreightBizException(CommonResultCode.PARAMS_ERROR);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(fmt);
        return LocalTime.parse(localTime, dtf);
    }
    
    /**
     * 将时间 转换成 字符串
     *
     * @param localTime LocalTime对象
     * @return 例如："23:03:05"
     */
    public static String format(LocalTime localTime) {
        if (Objects.isNull(localTime)) {
            throw new EfreightBizException(CommonResultCode.PARAMS_ERROR);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(TIME_FMT);
        return localTime.format(dtf);
    }
    
    /**
     * 将时间 转换成 字符串
     *
     * @param localTime LocalTime对象
     * @param fmt       例如：DateTimeUtil.TIME_FMT
     * @return 例如："23:03:05"
     */
    public static String format(LocalTime localTime, String fmt) {
        if (Objects.isNull(localTime)) {
            throw new EfreightBizException(CommonResultCode.PARAMS_ERROR);
        }
        if (!StringUtils.hasText(fmt)) {
            throw new EfreightBizException(CommonResultCode.PARAMS_ERROR);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(fmt);
        return localTime.format(dtf);
    }
    
    /**
     * 日期所在月的第一天
     *
     * @param localDate 入参不存在 就取当前日期
     */
    public static LocalDate monthDateStart(LocalDate localDate) {
        if (localDate == null) {
            localDate = LocalDate.now();
        }
        return localDate.with(TemporalAdjusters.firstDayOfMonth());
    }
    
    /**
     * 日期所在月的最后一天
     *
     * @param localDate 入参不存在 就取当前日期
     */
    public static LocalDate monthDateEnd(LocalDate localDate) {
        if (localDate == null) {
            localDate = LocalDate.now();
        }
        return localDate.with(TemporalAdjusters.lastDayOfMonth());
    }
    
    /**
     * 区间内的星期日期（包含区间两端）
     * 例：获取 2023-10-12 ~ 2023-10-22 范围内的星期日，返回2023-10-15，2023-10-22
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @param dayOfWeek 星期几
     */
    public static List<LocalDate> rangeWeekDateList(LocalDate startDate, LocalDate endDate, DayOfWeek dayOfWeek) {
        List<LocalDate> result = new ArrayList<>();
        if (startDate != null && endDate != null && !endDate.isBefore(startDate)) {
            LocalDate weekDay = startDate.with(dayOfWeek);
            if (weekDay.isBefore(startDate)) {
                weekDay = weekDay.plusWeeks(1).with(dayOfWeek);
            }
            while (!endDate.isBefore(weekDay)) {
                result.add(weekDay);
                //当前周数加一，在取出星期时间，并加入集合
                weekDay = weekDay.plusWeeks(1).with(dayOfWeek);
            }
            return result;
        }
        return result;
    }
    
    /**
     * 获取当前日期
     *
     * @return "YYYY-MM-DD"
     */
    public static String getCurrentDate() {
        return format(LocalDate.now());
    }
    
    /**
     * 将日期 转换成 字符串
     *
     * @param localDate LocalDate对象
     * @return 例如："2023-03-05"
     */
    public static String format(LocalDate localDate) {
        return format(localDate, DATE_FMT);
    }
    
    /**
     * 将日期 转换成 字符串
     *
     * @param localDate LocalDate对象
     * @param fmt       例如：DateTimeUtil.DATE_FMT
     * @return 例如："2023-03-05"
     */
    public static String format(LocalDate localDate, String fmt) {
        if (Objects.isNull(localDate)) {
            throw new EfreightBizException(CommonResultCode.PARAMS_ERROR);
        }
        if (!StringUtils.hasText(fmt)) {
            throw new EfreightBizException(CommonResultCode.PARAMS_ERROR);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(fmt);
        return localDate.format(dtf);
    }
    
    /**
     * 获取本周第一天
     *
     * @return "YYYY-MM-DD"
     */
    public static String getCurrentWeekFirst() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfWeek = today.with(DayOfWeek.MONDAY);
        return format(firstDayOfWeek);
    }
    
    /**
     * 获取上周第一天
     *
     * @return "YYYY-MM-DD"
     */
    public static String getLastWeekFirst() {
        LocalDate today = LocalDate.now();
        DayOfWeek currentDayOfWeek = today.getDayOfWeek();
        int daysToSubtract = currentDayOfWeek.getValue() + 6; // 计算需要减去的天数
        LocalDate lastWeekFirstDay = today.minusDays(daysToSubtract);
        return format(lastWeekFirstDay);
    }
    
    /**
     * 获取本月第一天
     *
     * @return "YYYY-MM-DD"
     */
    public static String getCurrentMonthFirst() {
        LocalDate today = LocalDate.now();
        LocalDate firstDayOfThisMonth = today.withDayOfMonth(1);
        return format(firstDayOfThisMonth);
    }
    
    /**
     * 获取上月第一天
     *
     * @return "YYYY-MM-DD"
     */
    public static String getLastMonthFirst() {
        LocalDate today = LocalDate.now();
        LocalDate lastToday = today.minusMonths(1);//月份
        LocalDate firstDay = lastToday.with(TemporalAdjusters.firstDayOfMonth());
        return format(firstDay);
    }
    
    /**
     * 获取本年第一天
     *
     * @return "YYYY-MM-DD"
     */
    public static String getCurrentYearFirst() {
        LocalDate today = LocalDate.now();
        today = today.withMonth(1).withDayOfMonth(1);
        return format(today);
    }
    
    /**
     * 获取两个时间的每天
     *
     * @param startDateStr 开始时间
     * @param endDateStr   结束时间
     * @return List<String>
     * @since 2024/4/26
     */
    public static List<String> getDateRange(String startDateStr, String endDateStr) {
        List<String> dateRange = new ArrayList<>();
        LocalDate startDate = parseDate(startDateStr);
        LocalDate endDate = parseDate(endDateStr);
        
        while (!startDate.isAfter(endDate)) {
            dateRange.add(format(startDate));
            startDate = startDate.plusDays(1);
        }
        
        return dateRange;
    }
    
    /**
     * 将日期字符串 转换成 LocalDate
     *
     * @param localDate 例如："2023-03-05"
     * @return LocalDate
     */
    public static LocalDate parseDate(String localDate) {
        return parseDate(localDate, DATE_FMT);
    }
    
    /**
     * 将日期字符串 转换成 LocalDate
     *
     * @param localDate 例如："2023-03-05"
     * @param fmt       例如：DateTimeUtil.DATE_FMT
     * @return LocalDate
     */
    public static LocalDate parseDate(String localDate, String fmt) {
        if (!StringUtils.hasText(localDate)) {
            throw new EfreightBizException(CommonResultCode.PARAMS_ERROR);
        }
        if (!StringUtils.hasText(fmt)) {
            throw new EfreightBizException(CommonResultCode.PARAMS_ERROR);
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(fmt);
        return LocalDate.parse(localDate, dtf);
    }
    
    /**
     * 获取两个时间的每周  第一个星期一假如第一周能超过三天，计算为本周的第一周，否则是第0周
     *
     * @param startDateStr 开始时间
     * @param endDateStr   结束时间
     * @return List<String>
     * @since 2024/4/26
     */
    public static List<String> getWeekRange(String startDateStr, String endDateStr) {
        Set<String> result = new LinkedHashSet<>();
        LocalDate startDate = parseDate(startDateStr);
        LocalDate endDate = parseDate(endDateStr);
        
        while (!startDate.isAfter(endDate)) {
            int weekOfYear = LocalDateTimeUtil.weekOfYear(startDate);
            int year = startDate.getYear();
            result.add(year + "年-" + weekOfYear + "周");
            startDate = startDate.plusDays(1);
        }
        return new ArrayList<>(result);
    }
    
    
    /**
     * 获取两个时间的每季度
     *
     * @param startDateStr 开始时间
     * @param endDateStr   结束时间
     * @return List<String>
     * @since 2024/4/26
     */
    public static List<String> getQuarterRange(String startDateStr, String endDateStr) {
        Set<String> result = new LinkedHashSet<>();
        LocalDate startDate = parseDate(startDateStr);
        LocalDate endDate = parseDate(endDateStr);
        YearMonth startYearMonth = YearMonth.from(startDate);
        YearMonth endYearMonth = YearMonth.from(endDate);
        while (!startYearMonth.isAfter(endYearMonth)) {
            int year = startYearMonth.getYear();
            Calendar date = Calendar.getInstance();
            // 可以设置具体的年、月、日
            date.set(startYearMonth.getYear(), startYearMonth.getMonthValue(), startDate.getDayOfMonth());
            int i = date.get(Calendar.MONTH) / 3 + 1;
            result.add(year + "年-Q" + i);
            startYearMonth = startYearMonth.plusMonths(1);
        }
        return new ArrayList<>(result);
    }
    
    /**
     * 获取两个时间的每月
     *
     * @param startDateStr 开始时间
     * @param endDateStr   结束时间
     * @return List<String>
     * @since 2024/4/26
     */
    public static List<String> getMonthRange(String startDateStr, String endDateStr) {
        Set<String> result = new LinkedHashSet<>();
        LocalDate startDate = parseDate(startDateStr);
        LocalDate endDate = parseDate(endDateStr);
        YearMonth startYearMonth = YearMonth.from(startDate);
        YearMonth endYearMonth = YearMonth.from(endDate);
        while (!startYearMonth.isAfter(endYearMonth)) {
            int year = startYearMonth.getYear();
            result.add(year + "年-" + startYearMonth.getMonthValue() + "月");
            startYearMonth = startYearMonth.plusMonths(1);
        }
        return new ArrayList<>(result);
    }
    
    /**
     * 获取两个时间的每年
     *
     * @param startDateStr 开始时间
     * @param endDateStr   结束时间
     * @return List<String>
     * @since 2024/4/26
     */
    public static List<String> getYearRange(String startDateStr, String endDateStr) {
        Set<String> result = new LinkedHashSet<>();
        LocalDate startDate = parseDate(startDateStr);
        LocalDate endDate = parseDate(endDateStr);
        Year startYear = Year.from(startDate);
        Year endYear = Year.from(endDate);
        while (!startYear.isAfter(endYear)) {
            result.add(startYear.getValue() + "年");
            startYear = startYear.plusYears(1);
        }
        return new ArrayList<>(result);
    }
    
    /**
     * 在时间集合中获取最早的时间
     *
     * @param dateTimeList 时间列表
     * @return LocalDateTime
     * @since 2024/3/13
     */
    public static LocalDateTime minDate(List<LocalDateTime> dateTimeList) {
        if (BizExceptionCheckUtils.isNull(dateTimeList)) {
            return null;
        }
        return dateTimeList.stream().min(LocalDateTime::compareTo).orElse(null);
    }
}
