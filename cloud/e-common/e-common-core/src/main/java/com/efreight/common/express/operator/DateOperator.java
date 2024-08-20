package com.efreight.common.express.operator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ql.util.express.Operator;

/**
 * 日期处理规则
 *
 * @author 张永伟
 * @since 2023/8/23
 */
public class DateOperator extends Operator {
    private final String operator;

    public DateOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public Object executeInner(Object[] list) throws Exception {
        Date date;
        if (list.length > 0) {
            String stringDate = list[0].toString();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = dateFormat.parse(stringDate);
        } else {
            date = new Date();
        }
        if ("DATE".equals(this.operator)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(date);
        } else if ("DATETIME".equals(this.operator)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return dateFormat.format(date);
        } else if ("YEAR".equals(this.operator)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.YEAR);
        } else if ("MONTH".equals(this.operator)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.MONTH);
        } else if ("DAY".equals(this.operator)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar.get(Calendar.DAY_OF_MONTH);
        } else if ("WEEK".equals(this.operator)) {
            String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0) {
                w = 0;
            }
            return weekDays[w];
        } else {
            return "";
        }
    }
}
