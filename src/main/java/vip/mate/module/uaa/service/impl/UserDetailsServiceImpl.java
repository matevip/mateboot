package vip.mate.module.uaa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import vip.mate.core.common.constant.Oauth2Constant;
import vip.mate.core.common.exception.BaseException;
import vip.mate.core.security.userdetails.MateUser;
import vip.mate.core.security.userdetails.MateUserDetailsService;
import vip.mate.module.system.dto.UserInfo;
import vip.mate.module.system.entity.SysUser;
import vip.mate.module.system.service.ISysUserService;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * 用户详情实现类
 * @author pangu
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements MateUserDetailsService {

    @Resource
    private ISysUserService sysUserService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        SysUser sysUser = sysUserService.getOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getAccount, userName));
       UserInfo userInfo = sysUserService.getUserInfo(sysUser);
        if (userInfo == null) {
            throw new UsernameNotFoundException("该用户：" + userName + "不存在");
        }
        userInfo.setType(Oauth2Constant.LOGIN_USERNAME_TYPE);
        userInfo.setUserName(userName);
        return getUserDetails(userInfo);

    }

    @Override
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        SysUser sysUser = sysUserService.getOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getTelephone, mobile));
        UserInfo userInfo = sysUserService.getUserInfo(sysUser);
        if (userInfo == null) {
            throw new UsernameNotFoundException("该用户：" + mobile + "不存在");
        }
        userInfo.setType(Oauth2Constant.LOGIN_MOBILE_TYPE);
        userInfo.setUserName(mobile);
        return getUserDetails(userInfo);
    }

    @Override
    public UserDetails loadUserBySocial(String openId) throws UsernameNotFoundException {
        String userName = "admin";
        SysUser sysUser = sysUserService.getOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getAccount, userName));
        UserInfo userInfo = sysUserService.getUserInfo(sysUser);
        if (userInfo == null) {
            throw new UsernameNotFoundException("该用户：" + userName + "不存在");
        }
        userInfo.setType(Oauth2Constant.LOGIN_USERNAME_TYPE);
        userInfo.setUserName(userName);
        return getUserDetails(userInfo);
    }


    private UserDetails getUserDetails(UserInfo userInfo) {
        if (ObjectUtils.isEmpty(userInfo)) {
            log.info("该用户：{} 不存在！", userInfo.getUserName());
            throw new UsernameNotFoundException("该用户：" + userInfo.getUserName() + "不存在");
        } else if (userInfo.getSysUser().getStatus().equals("1")) {
            log.info("该用户：{} 已被停用!", userInfo.getUserName());
            throw new BaseException("对不起，您的账号：" + userInfo.getUserName() + " 已停用");
        }
        SysUser user = userInfo.getSysUser();
        log.info("用户名：{}", userInfo.getSysUser().getAccount());
        Collection<? extends GrantedAuthority> authorities
                = AuthorityUtils.createAuthorityList("*:*:*");
        log.info("authorities: {}", authorities);
        return new MateUser(user.getId(), userInfo.getType(), user.getDepartId(), user.getRoleId(), user.getTelephone(), user.getAvatar(),
                user.getTenantId(), userInfo.getUserName(), user.getPassword(), user.getStatus().equals("0") ? true : false,
                true, true, true,
                authorities);
    }

}
