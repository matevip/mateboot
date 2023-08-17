package vip.mate.bootstrap.handler;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vip.mate.core.common.exception.ServerException;
import vip.mate.core.common.response.Result;

import java.io.IOException;

/**
 * 全局异常页面处理器，覆盖默认的Whitelabel Error Page
 *
 * @author matevip
 * @date 2023/8/13
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 **/
@Slf4j
@RestController
public class GlobalErrorViewController {

    /**
     * Error页面视图，直接响应JSON
     *
     **/
    @RequestMapping("/errorView")
    public Result<String> globalError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Result result = Result.error(404, "路径不存在");

        Object model = request.getAttribute("model");
        if (ObjectUtil.isNotEmpty(model)) {
            if (model instanceof Exception) {
                if (model instanceof ServerException) {
                    JSONObject errorObj = JSONUtil.parseObj(model);
                    Integer code = errorObj.getInt("code");
                    String msg = errorObj.getStr("msg");
                    if (ObjectUtil.isAllNotEmpty(code, msg)) {
                        result = Result.error(code, msg);
                    } else if (ObjectUtil.isNotEmpty(msg)) {
                        result = Result.error(msg);
                    } else {
                        result = Result.error();
                    }
                } else {
                    result = Result.error();
                    log.error(">>> 服务器未知异常，具体信息：", (Exception) model);
                }
            }
        }
        return result;
    }
}
