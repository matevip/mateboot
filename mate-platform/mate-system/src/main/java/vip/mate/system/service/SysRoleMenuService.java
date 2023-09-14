package vip.mate.system.service;

import vip.mate.system.entity.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.core.mybatis.res.PageRes;

import java.util.List;

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
     * 保存角色菜单
     * @param id
     * @param menuIds
     */
    Boolean saveRoleMenu(Long id, List<Long> menuIds);

    /**
     * 根据角色ID获取菜单ID集合
     * @param roleId 角色ID
     * @return 菜单ID集合
     */
    List<Long> getMenuIdsByRoleId(Long roleId);
}
