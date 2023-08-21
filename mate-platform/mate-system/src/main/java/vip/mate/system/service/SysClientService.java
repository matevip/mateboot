package vip.mate.system.service;

import vip.mate.system.entity.SysClient;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.system.vo.SysClientVO;
import vip.mate.system.req.SysClientReq;
import vip.mate.core.mybatis.res.PageRes;

/**
 * <p>
 * 系统授权表 服务类
 * </p>
 *
 * @author matevip
 * @since 2023-08-21
 */
public interface SysClientService extends IService<SysClient> {

    /**
     * 列表查询
     *
     * @param req 参数
     * @return 分页结果
     */
    PageRes<SysClientVO> queryPage(SysClientReq req);

    /**
     * 新增
     *
     * @param entity 实体类对象
     * @return true：成功，false：失败
     */
    Boolean createData(SysClient entity);

    /**
     * 修改
     *
     * @param entity 实体类对象
     * @return true：成功，false：失败
     */
    Boolean updateData(SysClient entity);

    /**
     * 删除
     *
     * @param id 主键
     * @return true：成功，false：失败
     */
    Boolean removeData(Long id);

    /**
     * 单条查询
     *
     * @param id 主键
     * @return true：成功，false：失败
     */
    SysClientVO getData(Long id);

    /**
     * 根据客户端ID查询
     *
     * @param clientId 客户端ID
     * @return
     */
    SysClientVO queryByClientId(String clientId);
}
