package vip.mate.bootstrap.utils;

import com.antherd.smcrypto.sm2.Sm2;
import com.antherd.smcrypto.sm3.Sm3;
import com.antherd.smcrypto.sm4.Sm4;
import com.antherd.smcrypto.sm4.Sm4Options;
import lombok.extern.slf4j.Slf4j;

/**
 * 加密工具类，本框架目前使用 https://github.com/antherd/sm-crypto 项目中一些加解密方式
 * 使用小伙伴需要过等保密评相关，请在此处更改为自己的加密方法，或加密机，使用加密机同时需要替换公钥，私钥在内部无法导出，提供加密的方法
 * 如果不涉及到加密机方面的内容，请更改公私要为自己重新生成的，生成方式请看集成的sm-crypto主页
 *
 * @author matevip
 * @date 2023/8/25
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
@Slf4j
public class CryptoUtil {

    /** 公钥 */
    private static final String PUBLIC_KEY = "04c5c773e251c9e81611835090906b757baca669d2df0153532a7e23c4345152a9b191ff09f167d02e9246d236c7f177005294be678f2a89736c95a7d235d7afa7";

    /** 私钥 */
    private static final String PRIVATE_KEY = "2d85fc3787e7515332302b14d543a2ff425ed2967f670f3586a780275e061ad6";

    /** SM4的对称秘钥（生产环境需要改成自己使用的） 16 进制字符串，要求为 128 比特 */
    private static final String KEY = "0123456789abcdeffedcba9876543210";

    /**
     * 加密方法（Sm2 的专门针对前后端分离，非对称秘钥对的方式，暴露出去的公钥，对传输过程中的密码加个密）
     *
     * @author yubaoshan
     * @date 2022/9/15 21:51
     * @param str 待加密数据
     * @return 加密后的密文
     */
    public static String doSm2Encrypt(String str) {
        return Sm2.doEncrypt(str, PUBLIC_KEY);
    }

    /**
     * 解密方法
     * 如果采用加密机的方法，用try catch 捕捉异常，返回原文值即可
     *
     * @author yubaoshan
     * @date 2022/9/15 21:51
     * @param str 密文
     * @return 解密后的明文
     */
    public static String doSm2Decrypt(String str) {
        // 解密
        return Sm2.doDecrypt(str, PRIVATE_KEY);
    }

    /**
     * 加密方法
     *
     * @author yubaoshan
     * @date 2022/9/15 21:51
     * @param str 待加密数据
     * @return 加密后的密文
     */
    public static String doSm4CbcEncrypt(String str) {
        // SM4 加密  cbc模式
        Sm4Options sm4Options4 = new Sm4Options();
        sm4Options4.setMode("cbc");
        sm4Options4.setIv("fedcba98765432100123456789abcdef");
        return Sm4.encrypt(str, KEY, sm4Options4);
    }

    /**
     * 解密方法
     * 如果采用加密机的方法，用try catch 捕捉异常，返回原文值即可
     *
     * @author yubaoshan
     * @date 2022/9/15 21:51
     * @param str 密文
     * @return 解密后的明文
     */
    public static String doSm4CbcDecrypt(String str) {
        // 解密，cbc 模式，输出 utf8 字符串
        Sm4Options sm4Options8 = new Sm4Options();
        sm4Options8.setMode("cbc");
        sm4Options8.setIv("fedcba98765432100123456789abcdef");
        String docString =  Sm4.decrypt(str, KEY, sm4Options8);
        if ("".equals(docString)) {
            log.warn(">>> 字段解密失败，返回原文值：{}", str);
            return str;
        } else {
            return docString;
        }
    }

    /**
     * 纯签名
     *
     * @author yubaoshan
     * @date 2022/9/15 21:51
     * @param str 待签名数据
     * @return 签名结果
     */
    public static String doSignature(String str) {
        return Sm2.doSignature(str, PRIVATE_KEY);
    }

    /**
     * 验证签名结果
     *
     * @author yubaoshan
     * @date 2022/9/15 21:51
     * @param originalStr 签名原文数据
     * @param str 签名结果
     * @return 是否通过
     */
    public static boolean doVerifySignature(String originalStr, String str) {
        return Sm2.doVerifySignature(originalStr, str, PUBLIC_KEY);
    }

    /**
     * 通过杂凑算法取得hash值，用于做数据完整性保护
     *
     * @author yubaoshan
     * @date 2022/9/15 21:51
     * @param str 字符串
     * @return hash 值
     */
    public static String doHashValue(String str) {
        return Sm3.sm3(str);
    }

}
