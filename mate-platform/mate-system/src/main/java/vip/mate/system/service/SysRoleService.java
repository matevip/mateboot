package vip.mate.system.service;

import vip.mate.system.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.system.vo.SysRoleVO;
import vip.mate.system.req.SysRoleReq;
import vip.mate.core.mybatis.res.PageRes;

import java.util.List;

/**
* <p>
* 角色管理 服务类
* </p>
*
* @author matevip
* @since 2023-08-22
*/
public interface SysRoleService extends IService<SysRole> {

    /**
     * 列表查询
     * @param req 参数
     * @return 分页结果
     */
    PageRes<SysRoleVO> queryPage(SysRoleReq req);

    /**
     * 新增
     * @param entity 实体类对象
     * @return true：成功，false：失败
     */
    Boolean createData(SysRole entity);

    /**
    * 修改
    * @param req 实体类对象
    * @return true：成功，false：失败
    */
    Boolean updateData(SysRoleReq req);

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
    SysRoleVO getData(Long id);

    /**
     * 获取角色列表
     * @param sysRoleReq
     * @return
     */
    List<SysRoleVO> getList(SysRoleReq sysRoleReq);
}
