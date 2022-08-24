package com.wjl.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GeneratorCode {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/system", "root", "123456")
            // 全局配置
            .globalConfig(builder -> {
                builder.enableSwagger()
                        .author("jay") // 设置作者
                        .outputDir("D:/pojo"); // 指定输出目录
            })
            .packageConfig(builder -> {
                builder.parent("com.wjl") // 设置父包名
                    .moduleName("system") // 设置父包模块名
                    .entity("entity")
                    .service("service")
                    .serviceImpl("service.impl")
                    .pathInfo(Collections.singletonMap(OutputFile.xml, "D:/pojo")); // 设置mapperXml生成路径
            })
            .strategyConfig(builder -> {
                builder.addInclude("system_business_type")
                        //.addTablePrefix("t_") // 增加过滤表前缀
                        //.addTableSuffix("_db") // 增加过滤表后缀
                        //.addFieldPrefix("t_") // 增加过滤字段前缀
                        //.addFieldSuffix("_field") // 增加过滤字段后缀
                        .entityBuilder()
                        //.enableLombok() // 开启lombok
                        //.enableChainModel() // 链式
                        .enableRemoveIsPrefix() // 开启boolean类型字段移除is前缀
                        //.enableTableFieldAnnotation() //开启生成实体时生成的字段注解
                        //.versionColumnName("version") // 乐观锁数据库字段
                        //.versionPropertyName("version") // 乐观锁实体类名称
                        //.logicDeleteColumnName("is_deleted") // 逻辑删除数据库中字段名
                        //.logicDeletePropertyName("deleted") // 逻辑删除实体类中的字段名
                        //.naming(NamingStrategy.underline_to_camel) // 表名 下划线 -》 驼峰命名
                        //.columnNaming(NamingStrategy.underline_to_camel) // 字段名 下划线 -》 驼峰命名
                        //.idType(IdType.ASSIGN_ID) // 主键生成策略 雪花算法生成id
                        //.formatFileName("%s") // Entity 文件名称
                        //.addTableFills(new Column("create_time", FieldFill.INSERT)) // 表字段填充
                        //.addTableFills(new Column("update_time", FieldFill.INSERT_UPDATE)) // 表字段填充
                        //.enableColumnConstant()
                        //.enableActiveRecord()
                    .controllerBuilder()
                    .enableRestStyle();
            })
                .templateConfig(builder -> {
                    builder.controller("/templates/controller.java")
                            .entity("/templates/entity.java")
                            .service("/templates/service.java")
                            .serviceImpl("/templates/serviceImpl.java")
                            .mapper("/templates/mapper.java");
                })
            .templateEngine(new VelocityTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
            .execute();
    }
}
