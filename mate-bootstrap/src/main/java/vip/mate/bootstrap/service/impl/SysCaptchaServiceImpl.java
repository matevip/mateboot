package vip.mate.bootstrap.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import vip.mate.bootstrap.service.SysCaptchaService;
import vip.mate.bootstrap.vo.SysCaptchaVO;
import vip.mate.core.redis.constant.CacheConstant;
import vip.mate.core.redis.utils.CacheUtils;

/**
 * 验证码
 *
 * @author matevip 7333791@qq.com
 */
@Service
@AllArgsConstructor
public class SysCaptchaServiceImpl implements SysCaptchaService {

    private final CacheUtils cacheUtils;

    @Override
    public SysCaptchaVO generate() {
        // 生成验证码key
        String key = UUID.randomUUID().toString();

        // 生成验证码
        SpecCaptcha captcha = new SpecCaptcha(150, 40);
        captcha.setLen(5);
        captcha.setCharType(Captcha.TYPE_DEFAULT);
        String image = captcha.toBase64();

        // 保存到缓存
        String redisKey = CacheConstant.getCaptchaKey(key);
        cacheUtils.set(redisKey, captcha.text(), 300);

        // 封装返回数据
        SysCaptchaVO captchaVO = new SysCaptchaVO();
        captchaVO.setKey(key);
        captchaVO.setImage(image);

        return captchaVO;
    }

    @Override
    public boolean validate(String key, String code) {
        if (StrUtil.isBlank(key) || StrUtil.isBlank(code)) {
            return false;
        }

        // 获取验证码
        String captcha = getCache(key);

        // 效验成功
        return code.equalsIgnoreCase(captcha);
    }

    private String getCache(String key) {
        key = CacheConstant.getCaptchaKey(key);
        String captcha = (String) cacheUtils.get(key);
        // 删除验证码
        if (captcha != null) {
            cacheUtils.delete(key);
        }

        return captcha;
    }
}
