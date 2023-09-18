package vip.mate.system.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.fhs.common.spring.SpringContextUtil;
import vip.mate.system.entity.SysMenu;
import vip.mate.system.entity.SysUser;
import vip.mate.system.enums.QueryMenuTypeEnum;
import vip.mate.system.mapper.SysMenuMapper;
import vip.mate.system.service.SysUserService;

import java.util.ArrayList;
import java.util.List;

/**
 * sa-token 权限管理实现类
 *
 * @author matevip
 * @date 2023/9/13
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
public class SaInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object o, String s) {
        SysUserService sysUserService = SpringContextUtil.getBeanByClass(SysUserService.class);
        SysMenuMapper sysMenuMapper = SpringContextUtil.getBeanByClass(SysMenuMapper.class);
        Long userId = StpUtil.getLoginIdAsLong();
        SysUser byId = sysUserService.getById(userId);
        List<SysMenu> menuList;
        if (byId.getSuperAdmin() == 1) {
            menuList = sysMenuMapper.getMenuList(QueryMenuTypeEnum.BUTTON.getValue());
            return menuList.stream().map(SysMenu::getAuthority).distinct().toList();
        }
        menuList = sysMenuMapper.getUserMenuList(userId, QueryMenuTypeEnum.BUTTON.getValue());
        return menuList.stream().map(SysMenu::getAuthority).distinct().toList();
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        return null;
    }
}
