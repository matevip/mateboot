package vip.mate.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import vip.mate.core.common.constant.MateConstant;
import vip.mate.core.common.exception.ServerException;
import vip.mate.core.common.utils.TreeUtils;
import vip.mate.system.entity.SysMenu;
import vip.mate.system.enums.MenuTypeEnum;
import vip.mate.system.enums.QueryMenuTypeEnum;
import vip.mate.system.mapper.SysMenuMapper;
import vip.mate.system.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vip.mate.system.service.SysUserService;
import vip.mate.system.vo.Meta;
import vip.mate.system.vo.SysMenuVO;
import vip.mate.system.req.SysMenuReq;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollectionUtil;
import vip.mate.system.convert.SysMenuConvert;
import vip.mate.core.mybatis.res.PageRes;
import vip.mate.system.vo.SysUserVO;


/**
 * <p>
 * 菜单数据 服务实现类
 * </p>
 *
 * @author matevip
 * @since 2023-08-22
 */
@Service
@RequiredArgsConstructor
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    private final SysUserService sysUserService;

    @Override
    public PageRes<SysMenuVO> queryPage(SysMenuReq req) {
        SysMenu info = SysMenuConvert.INSTANCE.convert(req);
        Page<SysMenu> pageData = baseMapper.selectPage(new Page<>(req.getPageNo(), req.getPageSize()), Wrappers.query(info));
        if (CollectionUtil.isEmpty(pageData.getRecords())) {
            return PageRes.empty();
        }
        List<SysMenuVO> vos = SysMenuConvert.INSTANCE.toVo(pageData.getRecords());
        return new PageRes<>(vos, pageData.getTotal(), req);
    }

    @Override
    public Boolean createData(SysMenu entity) {
        return baseMapper.insert(entity) > 0;
    }

    @Override
    public Boolean updateData(SysMenu entity) {
        return baseMapper.updateById(entity) > 0;
    }

    @Override
    public Boolean removeData(Long id) {
        return baseMapper.deleteById(id) > 0;
    }


    @Override
    public SysMenuVO getData(Long id) {
        final SysMenu info = baseMapper.selectById(id);
        return SysMenuConvert.INSTANCE.convertVo(info);
    }

    @Override
    public List<SysMenuVO> route(String loginId) {
        if (ObjectUtil.isEmpty(loginId)) {
            throw new ServerException("用户ID不能为空");
        }
        long userId = Long.parseLong(loginId);
        SysUserVO userData = sysUserService.getData(userId);
        List<SysMenu> userMenuList = null;
        if (userData.getSuperAdmin() == 1) {
            userMenuList = baseMapper.getMenuList(QueryMenuTypeEnum.EXCEPT_BUTTON.getValue());
        } else {
            userMenuList = baseMapper.getUserMenuList(userId, QueryMenuTypeEnum.EXCEPT_BUTTON.getValue());
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

    @Override
    public Set<String> authority(String loginId) {
        // 系统管理员，拥有最高权限
        List<String> authorityList;
//        if (user.getSuperAdmin().equals(SuperAdminEnum.YES.getValue())) {
        authorityList = baseMapper.getAuthorityList();
//        } else {
//            authorityList = baseMapper.getUserAuthorityList(loginId);
//        }
        // 用户权限列表
        Set<String> authSet = new HashSet<>();
        for (String authority : authorityList) {
            if (StrUtil.isBlank(authority)) {
                continue;
            }
            authSet.addAll(Arrays.asList(authority.trim().split(",")));
        }
        return authSet;
    }

    @Override
    public List<SysMenuVO> getMenuList(Integer type) {
        List<SysMenu> menuList = baseMapper.getMenuList(type);
        return TreeUtils.build(SysMenuConvert.INSTANCE.convertList(menuList), MateConstant.ROOT);
    }

    @Override
    public List<SysMenuVO> getButtonList(Long id, Integer type) {
        List<SysMenu> menuList = this.baseMapper.selectList(
                Wrappers.<SysMenu>lambdaQuery().eq(SysMenu::getPid, id)
                        .eq(SysMenu::getType, type));
        return SysMenuConvert.INSTANCE.convertList(menuList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String saveAll(SysMenuReq req) {
        SysMenu sysMenu = SysMenuConvert.INSTANCE.convert(req);
        // 给pid设置默认值
        if (ObjectUtil.isEmpty(sysMenu.getPid())) {
            sysMenu.setPid(0L);
        }
        // 判断别名是否重复
        if (ObjectUtil.isNotEmpty(sysMenu.getName())) {
            QueryWrapper<SysMenu> queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(SysMenu::getName, sysMenu.getName());
            if (ObjectUtil.isNotEmpty(sysMenu.getId())) {
                queryWrapper.lambda().ne(SysMenu::getId, sysMenu.getId());
            }
            SysMenu entity = baseMapper.selectOne(queryWrapper);
            if (ObjectUtil.isNotEmpty(entity)) {
                throw new ServerException("菜单别名已存在");
            }
        }
        this.saveOrUpdate(sysMenu);
        // 判断是否需要对按钮操作
        if (ObjectUtil.isNotEmpty(req.getButtonList())) {
            // 删除所有按钮
            this.baseMapper.delete(Wrappers.<SysMenu>lambdaUpdate()
                    .eq(SysMenu::getPid, sysMenu.getId())
                    .eq(SysMenu::getType, MenuTypeEnum.BUTTON.getValue()));

            // 设置按钮
            req.getButtonList().forEach(buttons -> {
                SysMenu buttonMenu = SysMenuConvert.INSTANCE.convert(buttons);
                if (buttonMenu.getId() == 0L) {
                    buttonMenu.setType(Byte.parseByte(String.valueOf(MenuTypeEnum.BUTTON.getValue())));
                    buttonMenu.setPid(sysMenu.getId());
                }
                this.saveOrUpdate(buttonMenu);
            });
        }
        return sysMenu.getId().toString();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteAll(Long[] ids) {
        for (Long id : ids) {

            // 先删除菜单下面的所有按钮
            this.baseMapper.delete(Wrappers.<SysMenu>lambdaUpdate()
                    .eq(SysMenu::getPid, id)
                    .eq(SysMenu::getType, MenuTypeEnum.BUTTON.getValue()));

            // 检查该ID下是否有子菜单
            List<SysMenu> subMenuList = this.baseMapper.selectList(Wrappers.<SysMenu>lambdaQuery()
                    .eq(SysMenu::getPid, id));

            // 如果存在子菜单，则不删除
            if (subMenuList.size() > 0) {
                throw new ServerException("该菜单存在子菜单不能删除");
            }
        }

        // 删除所有菜单
        this.removeBatchByIds(Arrays.asList(ids));
    }
}

