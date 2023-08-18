package vip.mate.code.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义扩展模板
 *
 * @author matevip
 * @date 2023/8/18
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
public enum CodeEnum {
    /**
     * vo
     */
    VO("VO", "templates/vo.java.vm"),
    /**
     * 转换工具
     */
    COVERT("Convert", "templates/convert.java.vm"),
    /**
     * Req
     */
    REQ("Req", "templates/req.java.vm")
    ;
    /**
     * 模型后缀
     */
    private final String modelSuffix;
    /**
     * 模板路径
     */
    private final String path;

    CodeEnum(String modelSuffix, String path) {
        this.modelSuffix = modelSuffix;
        this.path = path;
    }

    public String getModelSuffix() {
        return modelSuffix;
    }

    public String getPath() {
        return path;
    }

    public static List<String> getModel(){
        List<String> result = new ArrayList<>();
        CodeEnum[] values = CodeEnum.values();
        for (CodeEnum it : values) {
            result.add(it.getModelSuffix());
        }
        return result;
    }
}
