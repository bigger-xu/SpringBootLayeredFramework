package com.efreight.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile.Builder;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

/**
 * Mybatis-plus代码生成器
 *
 * @author 张永伟
 * @since 2023/7/1
 */
public class CodeGenerator {


    public static void main(String[] args) {
        String dateSourceUrl = "jdbc:mysql://172.16.10.100:3306/cargo_base?characterEncoding=utf8&allowMultiQueries=true&useSSL=false";
        String username = "root";
        String password = "SaaS.Onine.Mysql-20200323";

        FastAutoGenerator.create(dateSourceUrl, username, password)
                .globalConfig(builder ->
                        builder.author("Zhang YongWei")
                                .outputDir(System.getProperty("code_path") + "/src/main/java")
                                .enableSpringdoc()
                                .dateType(DateType.TIME_PACK)
                                .build()
                )
                .packageConfig((scanner, builder) ->
                        builder.parent("com.efreight." + scanner.apply("请输入模块名，如:hrs/order/crm等"))
                                .mapper("dao")
                                .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("code_path") + "/src/main/resources/mapper"))
                                .build()
                )
                .templateConfig(builder ->
                    builder.entity("/local/entity.java")
                            .controller("/local/controller.java")
                            .build()
                )
                // 策略配置
                .strategyConfig((scanner, builder) ->
                        builder.addInclude(getTables(scanner
                                .apply("请输入表名，多个英文逗号分隔,所有输入 all")))
                                .controllerBuilder()
                                .enableRestStyle()
                                .enableFileOverride()
                                .superClass("com.efreight.common.global.BaseController")
                                .serviceBuilder()
                                .enableFileOverride()
                                .entityBuilder()
                                .enableFileOverride()
                                .addTableFills(new Column("creator_id", FieldFill.INSERT))
                                .addTableFills(new Column("creator_name", FieldFill.INSERT))
                                .addTableFills(new Column("create_time", FieldFill.INSERT))
                                .addTableFills(new Column("editor_id", FieldFill.UPDATE))
                                .addTableFills(new Column("editor_name", FieldFill.UPDATE))
                                .addTableFills(new Column("edit_time", FieldFill.UPDATE))
                                .idType(IdType.ASSIGN_ID)
                                .enableLombok()
                                .mapperBuilder()
                                .enableFileOverride()
                                .build()
                )
                .injectionConfig( consumer -> {
                            List<CustomFile> customFiles = new ArrayList<>();
                            customFiles.add(new Builder().templatePath("/local/entityDTO.java.ftl")
                                    .enableFileOverride()
                                    .fileName("DTO.java")
                                    .packageName("dto")
                                    .build()
                            );
                            customFiles.add(new Builder().templatePath("/local/entityVO.java.ftl")
                                    .enableFileOverride()
                                    .fileName("VO.java")
                                    .packageName("vo")
                                    .build()
                            );
                            customFiles.add(new Builder().templatePath("/local/entityReq.java.ftl")
                                    .enableFileOverride()
                                    .fileName("Req.java")
                                    .packageName("req")
                                    .build()
                            );
                            consumer.customFile(customFiles);
                        })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }

    /**
     * 处理 all 情况   输入all就是所有表都生成
     *
     * @param tables 表名
     * @return List<String>
     * @since 2023/7/3
     */
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}
