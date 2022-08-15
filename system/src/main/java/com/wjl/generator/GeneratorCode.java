package com.wjl.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.querys.MySqlQuery;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import java.util.Collections;

public class GeneratorCode {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/system", "root", "123456")
            // 全局配置
            .globalConfig(builder -> {
                builder.author("jay") // 设置作者
                    .outputDir("/Users/jay/pojo"); // 指定输出目录
            })
            .packageConfig(builder -> {
                builder.parent("com.wjl") // 设置父包名
                    .moduleName("system") // 设置父包模块名
                    .entity("entity")
                    .service("service")
                    .serviceImpl("service.impl")
                    .pathInfo(Collections.singletonMap(OutputFile.xml, "/Users/jay/pojo")); // 设置mapperXml生成路径
            })
            .strategyConfig(builder -> {
                builder.addInclude("system_role_authorization")
                    .controllerBuilder()
                    .enableRestStyle();
            })
            .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
            .execute();
    }
}
