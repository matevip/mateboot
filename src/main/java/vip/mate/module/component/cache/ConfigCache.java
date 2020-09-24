package vip.mate.module.component.cache;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vip.mate.module.component.service.ISysConfigService;

import javax.annotation.PostConstruct;

/**
 * OSS配置文件初始化
 * 将数据库的配置信息读取到redis作为缓存数据
 * @author pangu
 */
@Slf4j
@Service
@AllArgsConstructor
public class ConfigCache {

    private final ISysConfigService sysConfigService;

    @PostConstruct
    public void init() {
        //加载OSS配置文件
        sysConfigService.getOssProperties();
    }
}
