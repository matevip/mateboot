package vip.mate.system.service.impl;

import vip.mate.system.entity.SysRoleMenu;
import vip.mate.system.mapper.SysRoleMenuMapper;
import vip.mate.system.service.SysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import java.util.*;
import cn.hutool.core.collection.CollectionUtil;

/**
 * <p>
 * 角色菜单 服务实现类
 * </p>
 *
 * @author matevip
 * @since 2023-08-22
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Override
    public Boolean saveRoleMenu(Long id, List<Long> menuIds) {
        //先删除原有的角色菜单
        baseMapper.delete(Wrappers.<SysRoleMenu>lambdaQuery().eq(SysRoleMenu::getRoleId, id));
        //保存新的角色菜单
        if (CollectionUtil.isNotEmpty(menuIds)) {
            List<SysRoleMenu> sysRoleMenuList = new ArrayList<>();
            menuIds.forEach(menuId -> {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setRoleId(id);
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenuList.add(sysRoleMenu);
            });
            return this.saveBatch(sysRoleMenuList);
        }
        return Boolean.TRUE;
    }

    @Override
    public List<Long> getMenuIdsByRoleId(Long roleId) {
        List<SysRoleMenu> sysRoleMenuList = baseMapper.selectList(Wrappers.<SysRoleMenu>lambdaQuery().eq(SysRoleMenu::getRoleId, roleId));
        if (CollectionUtil.isNotEmpty(sysRoleMenuList)) {
            List<Long> menuIds = new ArrayList<>();
            sysRoleMenuList.forEach(sysRoleMenu -> {
                menuIds.add(sysRoleMenu.getMenuId());
            });
            return menuIds;
        }
        return null;
    }

}

