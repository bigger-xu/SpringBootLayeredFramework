package com.cto.testing.operator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.ql.util.express.Operator;

public class DateDiffOperator extends Operator {

    @Override
    public Object executeInner(Object[] list) {
        String stringBeforeDate = list[0].toString();
        String stringAfterDate = list[1].toString();
        String dateType = list[2].toString();
        if ("Y".equals(dateType)) {
            return ChronoUnit.YEARS.between(LocalDate.parse(stringBeforeDate), LocalDate.parse(stringAfterDate));
        } else if ("M".equals(dateType)) {
            return ChronoUnit.MONTHS.between(LocalDate.parse(stringBeforeDate), LocalDate.parse(stringAfterDate));
        } else {
            return ChronoUnit.DAYS.between(LocalDate.parse(stringBeforeDate), LocalDate.parse(stringAfterDate));
        }
    }
}
