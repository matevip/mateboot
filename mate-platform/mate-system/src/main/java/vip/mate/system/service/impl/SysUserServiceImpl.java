package vip.mate.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.dynamic.datasource.toolkit.CryptoUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import vip.mate.core.common.exception.ServerException;
import vip.mate.core.common.utils.CryptoUtil;
import vip.mate.core.mybatis.service.impl.BaseServiceImpl;
import vip.mate.system.entity.SysUser;
import vip.mate.system.enums.SystemCodeEnum;
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
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

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
        entity.setPassword(CryptoUtil.doHashValue(CryptoUtil.doSm4CbcEncrypt(entity.getPassword())));
        return baseMapper.insert(entity) > 0;
    }

    @Override
    public Boolean updateData(SysUserReq req) {
        SysUser entity = SysUserConvert.INSTANCE.convert(req);
        userCondition(entity);
        if (ObjectUtil.isNotNull(entity.getPassword())) {
            // 加密密码
            entity.setPassword(CryptoUtil.doHashValue(CryptoUtil.doSm4CbcEncrypt(entity.getPassword())));
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

}

