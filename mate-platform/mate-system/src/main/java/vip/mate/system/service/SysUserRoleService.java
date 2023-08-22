package vip.mate.system.service;

import vip.mate.system.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.system.vo.SysUserRoleVO;
import vip.mate.system.req.SysUserRoleReq;
import vip.mate.core.mybatis.res.PageRes;

/**
* <p>
* 用户角色 服务类
* </p>
*
* @author matevip
* @since 2023-08-22
*/
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 列表查询
     * @param req 参数
     * @return 分页结果
     */
    PageRes<SysUserRoleVO> queryPage(SysUserRoleReq req);

    /**
     * 新增
     * @param entity 实体类对象
     * @return true：成功，false：失败
     */
    Boolean createData(SysUserRole entity);

    /**
    * 修改
    * @param entity 实体类对象
    * @return true：成功，false：失败
    */
    Boolean updateData(SysUserRole entity);

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
    SysUserRoleVO getData(Long id);
}
