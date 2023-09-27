package vip.mate.system.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import vip.mate.system.entity.SysRole;
import vip.mate.system.mapper.SysRoleMapper;
import vip.mate.system.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vip.mate.system.vo.SysRoleVO;
import vip.mate.system.req.SysRoleReq;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import java.util.*;

import cn.hutool.core.collection.CollectionUtil;
import vip.mate.system.convert.SysRoleConvert;
import vip.mate.core.mybatis.res.PageRes;


/**
 * <p>
 * 角色管理 服务实现类
 * </p>
 *
 * @author matevip
 * @since 2023-08-22
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public PageRes<SysRoleVO> queryPage(SysRoleReq req) {
        SysRole info = SysRoleConvert.INSTANCE.convert(req);
        Page<SysRole> pageData = baseMapper.selectPage(new Page<>(req.getPageNo(), req.getPageSize()), Wrappers.query(info));
        if (CollectionUtil.isEmpty(pageData.getRecords())) {
            return PageRes.empty();
        }
        List<SysRoleVO> vos = SysRoleConvert.INSTANCE.toVo(pageData.getRecords());
        return new PageRes<>(vos, pageData.getTotal(), req);
    }

    @Override
    public Boolean createData(SysRole entity) {
        return baseMapper.insert(entity) > 0;
    }

    @Override
    public Boolean updateData(SysRoleReq req) {
        // 更新角色信息
        SysRole entity = SysRoleConvert.INSTANCE.convert(req);
        return baseMapper.updateById(entity) > 0;
    }

    @Override
    public Boolean removeData(Long id) {
        return baseMapper.deleteById(id) > 0;
    }


    @Override
    public SysRoleVO getData(Long id) {
        final SysRole info = baseMapper.selectById(id);
        return SysRoleConvert.INSTANCE.convertVo(info);
    }

    @Override
    public List<SysRoleVO> getList(SysRoleReq sysRoleReq) {
        List<SysRole> list = baseMapper.selectList(getWrapper(sysRoleReq));
        return SysRoleConvert.INSTANCE.toVo(list);
    }

    private Wrapper<SysRole> getWrapper(SysRoleReq req) {
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(req.getName()), SysRole::getName, req.getName());
        return wrapper;
    }

}

