package com.efreight.common.utils;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

/**
 * 自定义LocalDateStringConverter
 * 用于解决使用easyexcel导出表格时候，默认不支持LocalDateTime日期格式
 * 在需要的属性上添加注解 @ExcelProperty(value = "创建日期", converter = LocalDateStringConverter.class)
 *
 * @author ZhangYongWei
 * @since 2024/8/12
 */

public class LocalDateStringConverter implements Converter<LocalDate> {
    @Override
    public Class supportJavaTypeKey() {
        return LocalDateTime.class;
    }
    
    
    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }
    
    @Override
    public WriteCellData<?> convertToExcelData(LocalDate localDate, ExcelContentProperty excelContentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        WriteCellData cellData = new WriteCellData();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd ");
        String cellValue;
        cellValue = formatter.format(localDate);
        cellData.setType(CellDataTypeEnum.STRING);
        cellData.setStringValue(cellValue);
        cellData.setData(cellValue);
        return cellData;
    }
}
