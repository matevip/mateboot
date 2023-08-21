package vip.mate.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import vip.mate.system.entity.SysClient;
import vip.mate.system.mapper.SysClientMapper;
import vip.mate.system.service.SysClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vip.mate.system.vo.SysClientVO;
import vip.mate.system.req.SysClientReq;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import java.util.*;

import cn.hutool.core.collection.CollectionUtil;
import vip.mate.system.convert.SysClientConvert;
import vip.mate.core.mybatis.res.PageRes;


/**
 * <p>
 * 系统授权表 服务实现类
 * </p>
 *
 * @author matevip
 * @since 2023-08-21
 */
@Service
public class SysClientServiceImpl extends ServiceImpl<SysClientMapper, SysClient> implements SysClientService {

    @Override
    public PageRes<SysClientVO> queryPage(SysClientReq req) {
        SysClient info = SysClientConvert.INSTANCE.convert(req);
        Page<SysClient> pageData = baseMapper.selectPage(new Page<>(req.getPageNo(), req.getPageSize()), Wrappers.query(info));
        if (CollectionUtil.isEmpty(pageData.getRecords())) {
            return PageRes.empty();
        }
        List<SysClientVO> vos = SysClientConvert.INSTANCE.toVo(pageData.getRecords());
        return new PageRes<>(vos, pageData.getTotal(), req);
    }

    @Override
    public Boolean createData(SysClient entity) {
        return baseMapper.insert(entity) > 0;
    }

    @Override
    public Boolean updateData(SysClient entity) {
        return baseMapper.updateById(entity) > 0;
    }

    @Override
    public Boolean removeData(Long id) {
        return baseMapper.deleteById(id) > 0;
    }


    @Override
    public SysClientVO getData(Long id) {
        final SysClient info = baseMapper.selectById(id);
        return SysClientConvert.INSTANCE.convertVo(info);
    }

    @Override
    public SysClientVO queryByClientId(String clientId) {
        final SysClient info = baseMapper.selectOne(new LambdaQueryWrapper<SysClient>().eq(SysClient::getClientId, clientId));
        return SysClientConvert.INSTANCE.convertVo(info);
    }

}

