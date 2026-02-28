package io.github.iweidujiang.druid;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Types;
import java.util.Collections;

/**
 * mybatis plus 自动成代码
 *
 * @author 苏渡苇
 * @from GitHub: https://github.com/iweidujiang | 公众号: 苏渡苇
 * @date 2026/2/28
 */
public class MpTest {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/test_druid?useUnicode=true&useSSL=false&characterEncoding=utf8";
    private static final String JDBC_USER_NAME = "root";
    private static final String JDBC_PASSOWRD = "111111";

    public static void main(String[] args) {
        FastAutoGenerator.create(JDBC_URL, JDBC_USER_NAME, JDBC_PASSOWRD)
                .globalConfig(builder -> {
                    builder.author("公众号: 苏渡苇")
                            //.dateType(DateType.ONLY_DATE)
                            .disableOpenDir()
                            .outputDir(System.getProperty("user.dir") + "/springboot-druid-safe-demo/src/main/java"); // 指定输出目录
                })
                .dataSourceConfig(builder ->
                        builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                            int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                            if (typeCode == Types.SMALLINT || typeCode == Types.TINYINT) {
                                // 自定义类型转换
                                return DbColumnType.INTEGER;
                            }
                            return typeRegistry.getColumnType(metaInfo);
                        })
                )
                .packageConfig(builder ->
                        builder.parent("io.github.iweidujiang.druid") // 设置父包名
//                                .moduleName("test")
                                .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir") + "/src/main/resources/mapper")) // 设置mapperXml生成路径
                )
                // 不生成 controller
                .templateConfig(builder -> builder.disable(TemplateType.CONTROLLER))
                .strategyConfig(builder ->
                                builder.addInclude("t_user") // 设置需要生成的表名
                                        .addTablePrefix("t_") // 设置过滤表前缀
                                        .entityBuilder()
                                        .formatFileName("%sDO")
                                        .enableLombok()
                                        //.controllerBuilder()
                                        //.enableRestStyle()
                                        .mapperBuilder()
                                        .mapperAnnotation(Mapper.class)
                                        .serviceBuilder()
                                        .formatServiceFileName("%sService")
                                        .formatServiceImplFileName("%sServiceImpl")
                )
                .execute();
    }
}
