package vip.mate.code.config;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 代码配置对象
 *
 * @author matevip
 * @date 2023/8/18
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
@Data
public class CodeConfig implements Serializable {

    private DbConfig dbConfig;

    private GlobalConfig globalConfig;

    private ControllerConfig controllerConfig;

    private ServiceConfig serviceConfig;

    private Boolean ifEnableMapstruct;

    private EntityConfig entityConfig;

    private ReqConfig reqConfig;

    @Data
    public class DbConfig{
        private String jdbcUrl;
        private String username;
        private String password;
    }
    @Data
    public class GlobalConfig{
        private Boolean ifReadPackagePath;
        private String parentPath;
        private String parentPackage;
        private String parentModule;
        private String outDir;
        private Boolean ifEnableModel;
        private String permissionModel;
        private String mapperOutDir;
        private String author;
        private List<String> prefixStr;
    }
    @Data
    public class ControllerConfig{
        private Boolean ifHasRequestPrefix;
        private Boolean ifFixedRequestPrefix;
        private String requestPrefix;
        private String requestConstantClass;
        private String requestConstantClassNameAndValue;
        private String commonReturnClass;
        private String commonReturnClassName;
        private String successMethod;
        private String failMethod;
        private Boolean ifExtend;
        private String extendClass;
        private String extendClassName;
        private String permissionPrefix;
        private Boolean ifHasAppPackage;
    }
    @Data
    public class ServiceConfig{
        private String pageReturnClass;
        private String pageReturnClassName;
        private String emptyMethod;
    }
    @Data
    public class EntityConfig{
        private Boolean ifExtendBaseEntity;
        private String baseEntityClass;
        private String baseEntityClassName;
        private Boolean ifEnableTenant;
        private String tenantEntityClass;
        private String tenantEntityClassName;
        private List<String> exclusionField;
        private String logicDeleteColumnName;
        private Boolean isEnableFastJson;
    }
    @Data
    public class ReqConfig{
        private Boolean ifExtendPageParam;
        private String pageParamClass;
        private String pageParamClassName;
    }
}
