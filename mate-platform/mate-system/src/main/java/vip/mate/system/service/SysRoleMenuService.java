package vip.mate.system.service;

import vip.mate.system.entity.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.system.vo.SysRoleMenuVO;
import vip.mate.system.req.SysRoleMenuReq;
import vip.mate.core.mybatis.res.PageRes;

/**
* <p>
* 角色菜单 服务类
* </p>
*
* @author matevip
* @since 2023-08-22
*/
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    /**
     * 列表查询
     * @param req 参数
     * @return 分页结果
     */
    PageRes<SysRoleMenuVO> queryPage(SysRoleMenuReq req);

    /**
     * 新增
     * @param entity 实体类对象
     * @return true：成功，false：失败
     */
    Boolean createData(SysRoleMenu entity);

    /**
    * 修改
    * @param entity 实体类对象
    * @return true：成功，false：失败
    */
    Boolean updateData(SysRoleMenu entity);

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
    SysRoleMenuVO getData(Long id);
}
