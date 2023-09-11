package vip.mate.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import vip.mate.system.entity.SysUserRole;
import vip.mate.system.mapper.SysUserRoleMapper;
import vip.mate.system.service.SysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vip.mate.system.vo.SysUserRoleVO;
import vip.mate.system.req.SysUserRoleReq;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import java.util.*;

import cn.hutool.core.collection.CollectionUtil;
import vip.mate.system.convert.SysUserRoleConvert;
import vip.mate.core.mybatis.res.PageRes;


/**
 * <p>
 * 用户角色 服务实现类
 * </p>
 *
 * @author matevip
 * @since 2023-08-22
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements SysUserRoleService {

    @Override
    public PageRes<SysUserRoleVO> queryPage(SysUserRoleReq req) {
        SysUserRole info = SysUserRoleConvert.INSTANCE.convert(req);
        Page<SysUserRole> pageData = baseMapper.selectPage(new Page<>(req.getPageNo(), req.getPageSize()), Wrappers.query(info));
        if (CollectionUtil.isEmpty(pageData.getRecords())) {
            return PageRes.empty();
        }
        List<SysUserRoleVO> vos = SysUserRoleConvert.INSTANCE.toVo(pageData.getRecords());
        return new PageRes<>(vos, pageData.getTotal(), req);
    }

    @Override
    public Boolean createData(SysUserRole entity) {
        return baseMapper.insert(entity) > 0;
    }

    @Override
    public Boolean updateData(SysUserRole entity) {
        return baseMapper.updateById(entity) > 0;
    }

    @Override
    public Boolean removeData(Long id) {
        return baseMapper.deleteById(id) > 0;
    }


    @Override
    public SysUserRoleVO getData(Long id) {
        final SysUserRole info = baseMapper.selectById(id);
        return SysUserRoleConvert.INSTANCE.convertVo(info);
    }

    @Override
    public Boolean removeByUserId(Long userId) {
        return baseMapper.delete(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, userId)) > 0;
    }

    @Override
    public Boolean saveBatch(Long userId, List<Long> roleIds) {
        if (CollectionUtil.isNotEmpty(roleIds)) {
            List<SysUserRole> sysUserRoleList = new ArrayList<>();
            roleIds.forEach(roleId -> {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(userId);
                sysUserRole.setRoleId(roleId);
                sysUserRoleList.add(sysUserRole);
            });
            return this.saveBatch(sysUserRoleList);
        }
        return Boolean.FALSE;
    }

    @Override
    public List<Long> getRoleIdsByUserId(Long userId) {
        List<SysUserRole> sysUserRoleList = baseMapper.selectList(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, userId));
        if (ObjectUtil.isNotEmpty(sysUserRoleList)) {
            List<Long> roleIds = new ArrayList<>();
            sysUserRoleList.forEach(sysUserRole -> {
                roleIds.add(sysUserRole.getRoleId());
            });
            return roleIds;
        }
        return null;
    }

}

