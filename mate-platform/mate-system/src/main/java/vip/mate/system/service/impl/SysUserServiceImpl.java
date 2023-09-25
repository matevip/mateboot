package vip.mate.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import vip.mate.core.common.context.UserInfoContextHolder;
import vip.mate.core.common.exception.ServerException;
import vip.mate.core.common.req.UserVO;
import vip.mate.core.common.utils.CryptoUtils;
import vip.mate.core.common.utils.TreeUtils;
import vip.mate.core.mybatis.service.impl.BaseServiceImpl;
import vip.mate.system.convert.SysMenuConvert;
import vip.mate.system.entity.SysMenu;
import vip.mate.system.entity.SysUser;
import vip.mate.system.enums.MenuTypeEnum;
import vip.mate.system.enums.QueryMenuTypeEnum;
import vip.mate.system.enums.SystemCodeEnum;
import vip.mate.system.mapper.SysMenuMapper;
import vip.mate.system.mapper.SysUserMapper;
import vip.mate.system.service.SysMenuService;
import vip.mate.system.service.SysUserRoleService;
import vip.mate.system.service.SysUserService;
import vip.mate.system.vo.Meta;
import vip.mate.system.vo.SysMenuVO;
import vip.mate.system.vo.SysUserVO;
import vip.mate.system.req.SysUserReq;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollectionUtil;
import vip.mate.system.convert.SysUserConvert;
import vip.mate.core.mybatis.res.PageRes;


/**
 * <p>
 * 用户管理 服务实现类
 * </p>
 *
 * @author matevip
 * @since 2023-08-21
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private final SysUserRoleService sysUserRoleService;
    private final SysMenuService sysMenuService;

    @Override
    public PageRes<SysUserVO> queryPage(SysUserReq req) {
        IPage<SysUser> pageData = baseMapper.selectPage(getPage(req), getWrapper(req));
        if (CollectionUtil.isEmpty(pageData.getRecords())) {
            return PageRes.empty();
        }
        List<SysUserVO> vos = SysUserConvert.INSTANCE.toVo(pageData.getRecords());
        return new PageRes<>(vos, pageData.getTotal(), req);
    }

    /**
     * 自定义查询功能
     *
     * @param req 请求对象
     * @return
     */
    private LambdaQueryWrapper<SysUser> getWrapper(SysUserReq req) {
        LambdaQueryWrapper<SysUser> wrapper = Wrappers.lambdaQuery();
        // 右上角模糊查询
        wrapper.like(StrUtil.isNotBlank(req.getKeyword()), SysUser::getUsername, req.getKeyword())
                .or()
                .like(StrUtil.isNotBlank(req.getKeyword()), SysUser::getRealName, req.getKeyword());
        // 左侧根据部门筛选
        wrapper.eq(ObjectUtil.isNotEmpty(req.getDeptId()), SysUser::getDeptId, req.getDeptId());
        return wrapper;
    }

    @Override
    public Boolean createData(SysUserReq req) {
        SysUser entity = SysUserConvert.INSTANCE.convert(req);
        userCondition(entity);
        // 加密密码
        entity.setPassword(CryptoUtils.doHashValue(CryptoUtils.doSm4CbcEncrypt(entity.getPassword())));
        return baseMapper.insert(entity) > 0;
    }

    @Override
    public Boolean updateData(SysUserReq req) {
        SysUser entity = SysUserConvert.INSTANCE.convert(req);
        userCondition(entity);
        // 更新密码
        if (ObjectUtil.isNotNull(entity.getPassword())) {
            // 加密密码
            entity.setPassword(CryptoUtils.doHashValue(CryptoUtils.doSm4CbcEncrypt(entity.getPassword())));
        }
        // 更新用户角色关系
        if (ObjectUtil.isNotNull(req.getRoleIdList())) {
            // 删除用户角色关系
            sysUserRoleService.removeByUserId(req.getId());
            // 保存用户角色关系
            sysUserRoleService.saveBatch(req.getId(), req.getRoleIdList());
        }
        return baseMapper.updateById(entity) > 0;
    }

    @Override
    public Boolean removeData(Long id) {
        return baseMapper.deleteById(id) > 0;
    }


    @Override
    public SysUserVO getData(Long id) {
        final SysUser info = baseMapper.selectById(id);
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(info, userVO);
        userVO.setPermissionIdList(this.getPermissionByUserId(id));
        UserInfoContextHolder.refresh(StpUtil.getLoginIdAsLong(), userVO);
        return SysUserConvert.INSTANCE.convertVo(info);
    }

    @Override
    public SysUser queryByUsername(String username) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, username));
    }

    @Override
    public Boolean userCondition(SysUser user) {
        SysUser usernameCondition = this.baseMapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getUsername, user.getUsername()));
        if (usernameCondition != null) {
            // 更新操作判断
            if (ObjectUtil.isNotNull(user.getId()) && !usernameCondition.getId().equals(user.getId())) {
                throw new ServerException(SystemCodeEnum.USERNAME_EXIST);
                // 添加操作判断
            } else if (ObjectUtil.isNull(user.getId())) {
                throw new ServerException(SystemCodeEnum.USERNAME_EXIST);
            }
        }
        SysUser mobileCondition = this.baseMapper.selectOne(new QueryWrapper<SysUser>().lambda().eq(SysUser::getMobile, user.getMobile()));
        if (mobileCondition != null) {
            // 更新操作判断
            if (ObjectUtil.isNotNull(user.getId()) && !usernameCondition.getId().equals(user.getId())) {
                throw new ServerException(SystemCodeEnum.MOBILE_EXIST);
                // 添加操作判断
            } else if (ObjectUtil.isNull(user.getId())) {
                throw new ServerException(SystemCodeEnum.MOBILE_EXIST);
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public List<String> getPermissionByUserId(Long userId) {
        SysUser byId = baseMapper.selectById(userId);
        List<SysMenu> menuList;
        if (byId.getSuperAdmin() == 1) {
            menuList = sysMenuService.getLocalMenuList(QueryMenuTypeEnum.BUTTON.getValue());
            return menuList.stream().map(SysMenu::getAuthority).distinct().toList();
        }
        menuList = sysMenuService.getUserMenuList(userId, QueryMenuTypeEnum.BUTTON.getValue());
        return menuList.stream().map(SysMenu::getAuthority).distinct().toList();
    }

    @Override
    public List<SysMenuVO> route(String loginId) {
        if (ObjectUtil.isEmpty(loginId)) {
            throw new ServerException(SystemCodeEnum.USER_ID_NULL_ERROR);
        }
        long userId = Long.parseLong(loginId);
        SysUserVO userData = this.getData(userId);
        List<SysMenu> userMenuList = null;
        if (userData.getSuperAdmin() == 1) {
            userMenuList = sysMenuService.getLocalMenuList(QueryMenuTypeEnum.EXCEPT_BUTTON.getValue());
        } else {
            userMenuList = sysMenuService.getUserMenuList(userId, QueryMenuTypeEnum.EXCEPT_BUTTON.getValue());
        }

        List<SysMenuVO> sysMenuVOS = SysMenuConvert.INSTANCE.convertList(userMenuList);

        // 转换增加meta元数据
        List<SysMenuVO> collect = sysMenuVOS.stream().map(sysMenuVO -> {
            Meta meta = new Meta();
            meta.setTitle(sysMenuVO.getTitle());
            meta.setIcon(sysMenuVO.getIcon());
            meta.setType(MenuTypeEnum.getCode(sysMenuVO.getType()).getCode());
            meta.setAffix(sysMenuVO.getAffix() == 1 ? "true" : "");
            meta.setHidden(sysMenuVO.getHidden());
            meta.setHiddenBreadcrumb(sysMenuVO.getHiddenBreadcrumb());
            meta.setTag(sysMenuVO.getTag());
            sysMenuVO.setMeta(meta);
            return sysMenuVO;
        }).collect(Collectors.toList());

        return TreeUtils.build(collect);
    }
}

