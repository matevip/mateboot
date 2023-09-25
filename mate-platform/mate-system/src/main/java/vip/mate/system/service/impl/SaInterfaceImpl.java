package vip.mate.system.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.extra.spring.SpringUtil;
import vip.mate.system.entity.SysMenu;
import vip.mate.system.entity.SysUser;
import vip.mate.system.enums.QueryMenuTypeEnum;
import vip.mate.system.mapper.SysMenuMapper;
import vip.mate.system.service.SysUserService;

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
        SysUserService sysUserService = SpringUtil.getBean(SysUserService.class);
        Long userId = StpUtil.getLoginIdAsLong();
        return sysUserService.getPermissionByUserId(userId);
    }

    @Override
    public List<String> getRoleList(Object o, String s) {
        return null;
    }
}
