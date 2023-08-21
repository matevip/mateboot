package vip.mate.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import vip.mate.system.entity.SysUser;
import vip.mate.system.mapper.SysUserMapper;
import vip.mate.system.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vip.mate.system.vo.SysUserVO;
import vip.mate.system.req.SysUserReq;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import java.util.*;
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
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public PageRes<SysUserVO> queryPage(SysUserReq req) {
        SysUser info = SysUserConvert.INSTANCE.convert(req);
        Page<SysUser> pageData = baseMapper.selectPage(new Page<>(req.getPageNo(), req.getPageSize()), Wrappers.query(info));
        if(CollectionUtil.isEmpty(pageData.getRecords())){
            return PageRes.empty();
        }
        List<SysUserVO> vos = SysUserConvert.INSTANCE.toVo(pageData.getRecords());
        return new PageRes<>(vos, pageData.getTotal(),req);
    }

    @Override
    public Boolean createData(SysUser entity) {
        return baseMapper.insert(entity) > 0;
    }

    @Override
    public Boolean updateData(SysUser entity) {
        return baseMapper.updateById(entity) > 0;
    }

    @Override
    public Boolean removeData(Long id) {
        return baseMapper.deleteById(id) > 0;
    }


    @Override
    public SysUserVO getData(Long id) {
        final SysUser info = baseMapper.selectById(id);
        return SysUserConvert.INSTANCE.convertVo(info);
    }

    @Override
    public SysUser queryByUsername(String username) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<SysUser>()
                .eq(SysUser::getUsername, username));
    }

}

