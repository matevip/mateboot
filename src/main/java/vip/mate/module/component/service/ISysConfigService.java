package vip.mate.module.component.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.core.oss.props.OssProperties;
import vip.mate.module.component.entity.SysConfig;

/**
 * <p>
 * 配置表 服务类
 * </p>
 *
 * @author pangu
 * @since 2020-08-05
 */
public interface ISysConfigService extends IService<SysConfig> {

    OssProperties getOssProperties();

    OssProperties getConfigByCode(String code);

    boolean saveConfigOss(OssProperties ossProperties, String code);

    boolean saveDefaultOss(String code);

    String defaultOss();

}
