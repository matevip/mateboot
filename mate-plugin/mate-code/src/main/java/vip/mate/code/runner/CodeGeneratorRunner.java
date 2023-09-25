package vip.mate.code.runner;

import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONReader;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.ObjectMapper;
import vip.mate.code.config.CodeConfig;
import vip.mate.code.enums.CodeEnum;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 手动代码生成工具
 *
 * @author matevip
 * @date 2023/8/18
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
@Slf4j
public class CodeGeneratorRunner {
    public static void main(String[] args) {
        CodeConfig config = parseConfigFile();
        if (config.getGlobalConfig().getIfReadPackagePath()) {
            String path = System.getProperty("user.dir");
            //替换为项目所在包路径
            config.getGlobalConfig().setParentPath(path);
        }

        /* 下方参数是可以动态设置的 */
        //需要生成的表名称，多个用“,”分割
        String tableName = "mate_sys_operate_log";
        //设置是否开启包分层-按需开启
        config.getGlobalConfig().setIfEnableModel(false);
        //业务模块[包分层]/权限字符[第一段]，例如 controller/system/aaa.java
        config.getGlobalConfig().setPermissionModel("admin");
        config.getGlobalConfig().setAuthor("matevip");
        //是否开启租户，用于实体类是否继承TenantEntity
        config.getEntityConfig().setIfEnableTenant(false);
        System.out.println("codeConfig" + JSONObject.toJSONString(config));
        genCode(config, tableName);
        System.out.println("代码生成成功，请到路径： " + config.getGlobalConfig().getParentPath() + " 查看");
    }

    public static void genCode(CodeConfig config, String tableName) {
        FastAutoGenerator.create(config.getDbConfig().getJdbcUrl(), config.getDbConfig().getUsername(), config.getDbConfig().getPassword())
                .globalConfig(builder -> {
                    builder.author(config.getGlobalConfig().getAuthor())
                            .enableSwagger()
                            .disableOpenDir()
                            .outputDir(config.getGlobalConfig().getParentPath() + config.getGlobalConfig().getOutDir());
                })
                .packageConfig(builder -> {
                    builder.parent(config.getGlobalConfig().getParentPackage()) // 设置父包名
                            .moduleName(config.getGlobalConfig().getParentModule()); // 设置父包模块名
                    String mapperPath = config.getGlobalConfig().getParentPath() + config.getGlobalConfig().getMapperOutDir();

                    if (config.getGlobalConfig().getIfEnableModel()) {
                        builder.entity("entity." + config.getGlobalConfig().getPermissionModel())
                                .service("service." + config.getGlobalConfig().getPermissionModel())
                                .controller("controller" + addAdminPackage(config.getControllerConfig().getIfHasAppPackage()) + "." + config.getGlobalConfig().getPermissionModel())
                                .serviceImpl("service." + config.getGlobalConfig().getPermissionModel() + ".impl")
                                .mapper("mapper." + config.getGlobalConfig().getPermissionModel());
                        mapperPath += "/" + config.getGlobalConfig().getPermissionModel();
                    } else {
                        builder.entity("entity")
                                .service("service")
                                .controller("controller" + addAdminPackage(config.getControllerConfig().getIfHasAppPackage()))
                                .serviceImpl("service.impl")
                                .mapper("mapper");
                    }
                    // 设置mapperXml生成路径
                    builder.xml("model").pathInfo(Collections.singletonMap(OutputFile.xml, mapperPath));
                })
                .strategyConfig(builder -> builder.addInclude(tableName.trim().split(",")) // 设置需要生成的表名
                        .addTablePrefix(convert(config.getGlobalConfig().getPrefixStr())) // 设置过滤表前缀
                        .entityBuilder().enableLombok().enableChainModel()
                        //移除is前缀
                        //.enableRemoveIsPrefix()
                        .enableTableFieldAnnotation()
                        .logicDeleteColumnName(config.getEntityConfig().getLogicDeleteColumnName())
                        .idType(IdType.NONE)

                        .controllerBuilder()
                        .enableRestStyle()
                        .formatFileName("%sController")

                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")

                        .mapperBuilder()
                        .enableBaseColumnList()
                        .enableBaseResultMap())
                //自定义模板
                .injectionConfig(builder -> {
                    //设置自定义参数
                    Map<String, Object> map = new HashMap<>(8);
                    /*
                     * 设置自定义参数，自定义参数定义步骤：
                     * 1.再json文件里面添加对应配置
                     * 2.CodeConfig类中定义字段接收
                     * 3.模板中使用，示例：${codeConfig.XXX.XXX},注意属性大小写
                     */
                    map.put("codeConfig", convertMap(config));
                    builder.customMap(map);
                    //自定义其他模板
                    Map<String, String> customFile = new HashMap<>(8);
                    customFile.put(CodeEnum.VO.getModelSuffix(), CodeEnum.VO.getPath());
                    customFile.put(CodeEnum.COVERT.getModelSuffix(), CodeEnum.COVERT.getPath());
                    customFile.put(CodeEnum.REQ.getModelSuffix(), CodeEnum.REQ.getPath());
                    builder.customFile(customFile).build();
                })
                .templateEngine(new EnhanceVelocityTemplateEngine(config))
                .execute();

    }

    private static String addAdminPackage(boolean ifHasAppPackage) {
        if (ifHasAppPackage) {
            return ".admin";
        }
        return "";
    }

    private static String[] convert(List<String> prefixStr) {
        String[] arr = new String[prefixStr.size()];
        for (int i = 0; i < prefixStr.size(); i++) {
            arr[i] = prefixStr.get(i);
        }
        return arr;
    }

    public final static class EnhanceVelocityTemplateEngine extends VelocityTemplateEngine {

        private final CodeConfig codeConfig;

        public EnhanceVelocityTemplateEngine(CodeConfig codeConfig) {
            this.codeConfig = codeConfig;
        }

        @Override
        public Map<String, Object> getObjectMap(ConfigBuilder config, TableInfo tableInfo) {
            // 获取实体类名字
            String entityName = tableInfo.getEntityName();
            // 获取object map，设置自定义参数
            Map<String, Object> objectMap = super.getObjectMap(config, tableInfo);
            // 获取Other的盘符路径
            String otherPath = (String) ((Map<String, Object>) objectMap.get("package")).get("Parent");
            // 自定义枚举
            List<String> modelList = CodeEnum.getModel();
            // 循环
            modelList.forEach(it -> {
                // 转小写
                String var = it.toLowerCase();
                String path = otherPath + "." + var + "." + entityName + it;
                if (codeConfig.getGlobalConfig().getIfEnableModel()) {
                    path = otherPath + "." + var + "." + codeConfig.getGlobalConfig().getPermissionModel() + "." + entityName + it;
                }
                // 存入object map
                objectMap.put(var, path);
            });
            return objectMap;
        }

        /**
         * 文件输出路径
         *
         * @param customFiles 自定义文件List
         * @param tableInfo  表信息
         * @param objectMap  对象map
         */
        @Override
        protected void outputCustomFile(List<CustomFile> customFiles, TableInfo tableInfo, Map<String, Object> objectMap) {
            // 获取实体类名字
            String entityName = tableInfo.getEntityName();
            // 获取other包盘符路径
            String otherPath = this.getPathInfo(OutputFile.parent);
            // 输出自定义java模板
            customFiles.forEach((c) -> {
                // 输出路径
                String fileName = otherPath + File.separator + c.getFileName().toLowerCase() + File.separator + entityName + c.getFileName() + ".java";
                if (codeConfig.getGlobalConfig().getIfEnableModel()) {
                    fileName = otherPath + File.separator + c.getFileName().toLowerCase()  + File.separator + codeConfig.getGlobalConfig().getPermissionModel() + File.separator + entityName + c.getFileName() + ".java";
                }
                // 输出velocity的java模板
                this.outputFile(new File(fileName), objectMap, c.getTemplatePath(), c.isFileOverride());
            });
        }
    }

    private static Object convertMap(CodeConfig codeConfig) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(codeConfig);
            // 将JSON字符串转换为Map
            return objectMapper.readValue(json, Map.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从Resources中读取json文件，并转换成CodeConfig
     *
     * @return
     * @throws IOException
     */
    private static CodeConfig parseConfigFile() {
        InputStream inputStream = CodeGeneratorRunner.class.getClassLoader().getResourceAsStream("config/code.json");
        assert inputStream != null;
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        // 创建 JSONReader 对象并解析 JSON 字符串
        final String result = JSONReader.of(inputStreamReader).readString();
        return JSONObject.parseObject(result, CodeConfig.class);
    }
}
