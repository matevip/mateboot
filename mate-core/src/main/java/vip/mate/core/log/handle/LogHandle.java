package vip.mate.core.log.handle;

import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import vip.mate.core.redis.constant.CacheConstant;
import vip.mate.core.redis.utils.CacheUtils;

/**
 * 异步保存日志
 *
 * @author matevip
 * @date 2023/9/25
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
@Service
@AllArgsConstructor
public class LogHandle {
    private final CacheUtils cacheUtils;

    @Async
    public void saveLog(Object log) {
        // 保存到Redis队列
        cacheUtils.leftPush(CacheConstant.getLogKey(), log, CacheUtils.NOT_EXPIRE);
    }
}
