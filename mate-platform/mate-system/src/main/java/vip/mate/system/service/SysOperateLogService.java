package vip.mate.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.core.log.req.SysOperateLogReq;
import vip.mate.system.entity.SysOperateLog;
import vip.mate.system.vo.SysOperateLogVO;
import vip.mate.core.mybatis.res.PageRes;

/**
* <p>
* 操作日志 服务类
* </p>
*
* @author matevip
* @since 2023-09-25
*/
public interface SysOperateLogService extends IService<SysOperateLog> {

    /**
     * 列表查询
     * @param req 参数
     * @return 分页结果
     */
    PageRes<SysOperateLogVO> queryPage(SysOperateLogReq req);

    /**
     * 新增
     * @param entity 实体类对象
     * @return true：成功，false：失败
     */
    Boolean createData(SysOperateLog entity);

    /**
    * 修改
    * @param entity 实体类对象
    * @return true：成功，false：失败
    */
    Boolean updateData(SysOperateLog entity);

    /**
     * 删除
     * @param id 主键
     * @return true：成功，false：失败
     */
    Boolean removeData(Long id);

    /**
     * 单条查询
     * @param id 主键
     * @return true：成功，false：失败
     */
    SysOperateLogVO getData(Long id);
}
