package com.cto.testing.utils;

import java.util.ArrayList;
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
 * @author ZhangYongWei
 * @since 2024/10/18
 */
public class GeneratorUtils {
    
    public static void execute(String dateBaseName, String modelName, String tableName, String outPutPath) {
        String dateSourceUrl = "jdbc:mysql://172.16.10.100:3306/" + dateBaseName + "?characterEncoding=utf8&allowMultiQueries=true&useSSL=false";
        String username = "root";
        String password = "SaaS.Onine.Mysql-20200323";
        
        FastAutoGenerator.create(dateSourceUrl, username, password)
                .globalConfig(builder ->
                                      builder.author("Zhang YongWei")
                                              .disableOpenDir()
                                              .outputDir(outPutPath + "/src/main/java")
                                              .enableSpringdoc()
                                              .dateType(DateType.TIME_PACK)
                                              .build()
                )
                .packageConfig((scanner, builder) ->
                                       builder.parent("com.efreight." + modelName)
                                               .mapper("dao")
                                               .pathInfo(Collections.singletonMap(OutputFile.xml, outPutPath + "/src/main/resources/mapper"))
                                               .build()
                )
                .templateConfig(builder ->
                                        builder.entity("/local/entity.java")
                                                .controller("/local/controller.java")
                                                .build()
                )
                // 策略配置
                .strategyConfig((scanner, builder) ->
                                        builder.addInclude(Collections.singletonList(tableName))
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
                .injectionConfig(consumer -> {
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
                    customFiles.add(new Builder().templatePath("/local/vue/index.vue.ftl")
                                            .enableFileOverride()
                                            .filePath(outPutPath + "/src/main/resources")
                                            .formatNameFunction(tableInfo -> tableInfo.getEntityPath() + "/")
                                            .fileName("index.vue")
                                            .packageName("vue")
                                            .build()
                    );
                    customFiles.add(new Builder().templatePath("/local/vue/api.ts.ftl")
                                            .enableFileOverride()
                                            .filePath(outPutPath + "/src/main/resources")
                                            .formatNameFunction(tableInfo -> tableInfo.getEntityPath() + "/" + tableInfo.getEntityPath())
                                            .fileName(".ts")
                                            .packageName("vue")
                                            .build()
                    );
                    customFiles.add(new Builder().templatePath("/local/vue/query.json.ftl")
                                            .enableFileOverride()
                                            .filePath(outPutPath + "/src/main/resources/")
                                            .formatNameFunction(tableInfo -> tableInfo.getEntityPath() + "/")
                                            .fileName("query.json")
                                            .packageName("vue")
                                            .build()
                    );
                    customFiles.add(new Builder().templatePath("/local/vue/Edit.vue.ftl")
                                            .enableFileOverride()
                                            .filePath(outPutPath + "/src/main/resources")
                                            .formatNameFunction(tableInfo -> tableInfo.getEntityPath() + "/")
                                            .fileName("Edit.vue")
                                            .packageName("vue")
                                            .build()
                    );
                    consumer.customFile(customFiles);
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
