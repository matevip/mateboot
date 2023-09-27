package vip.mate.system.service;

import vip.mate.system.entity.SysLoginLog;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.system.vo.SysLoginLogVO;
import vip.mate.system.req.SysLoginLogReq;
import vip.mate.core.mybatis.res.PageRes;

/**
* <p>
* 登录日志 服务类
* </p>
*
* @author matevip
* @since 2023-09-27
*/
public interface SysLoginLogService extends IService<SysLoginLog> {

    /**
     * 列表查询
     * @param req 参数
     * @return 分页结果
     */
    PageRes<SysLoginLogVO> queryPage(SysLoginLogReq req);

    /**
     * 保存日志
     * @param username 用户名
     * @param status 状态
     * @param businessType 业务类型
     */
    void saveLog(String username, int status, int businessType);
}
