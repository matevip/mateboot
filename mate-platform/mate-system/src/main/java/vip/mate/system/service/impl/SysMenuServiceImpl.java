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
import vip.mate.system.enums.SystemCodeEnum;
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
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

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
                throw new ServerException(SystemCodeEnum.MENU_NAME_EXIST);
            }
        }
        this.saveOrUpdate(sysMenu);
        // 判断是否需要对按钮操作
        if (ObjectUtil.isNotEmpty(req.getButtonList())) {
            // 设置按钮
            req.getButtonList().forEach(buttons -> {
                SysMenu buttonMenu = SysMenuConvert.INSTANCE.convert(buttons);
                if (buttonMenu.getId() == 0L) {
                    buttonMenu.setId(null);
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
                throw new ServerException(SystemCodeEnum.SUB_MENU_DEL_ERROR);
            }
        }

        // 删除所有菜单
        this.removeBatchByIds(Arrays.asList(ids));
    }

    @Override
    public List<SysMenu> getLocalMenuList(Integer type) {
        return baseMapper.getMenuList(type);
    }

    @Override
    public List<SysMenu> getUserMenuList(Long userId, Integer type) {
        return baseMapper.getUserMenuList(userId, type);
    }

}

