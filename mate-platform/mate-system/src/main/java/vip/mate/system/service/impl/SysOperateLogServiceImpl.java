package vip.mate.system.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vip.mate.core.log.req.SysOperateLogReq;
import vip.mate.system.entity.SysOperateLog;
import vip.mate.system.mapper.SysOperateLogMapper;
import vip.mate.system.service.SysOperateLogService;
import vip.mate.system.vo.SysOperateLogVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import java.util.*;
import cn.hutool.core.collection.CollectionUtil;
import vip.mate.system.convert.SysOperateLogConvert;
import vip.mate.core.mybatis.res.PageRes;


/**
 * <p>
 * 操作日志 服务实现类
 * </p>
 *
 * @author matevip
 * @since 2023-09-25
 */
@Service
public class SysOperateLogServiceImpl extends ServiceImpl<SysOperateLogMapper, SysOperateLog> implements SysOperateLogService {

    @Override
    public PageRes<SysOperateLogVO> queryPage(SysOperateLogReq req) {
        SysOperateLog info = SysOperateLogConvert.INSTANCE.convert(req);
        Page<SysOperateLog> pageData = baseMapper.selectPage(new Page<>(req.getPageNo(), req.getPageSize()), Wrappers.query(info));
        if(CollectionUtil.isEmpty(pageData.getRecords())){
            return PageRes.empty();
        }
        List<SysOperateLogVO> vos = SysOperateLogConvert.INSTANCE.toVo(pageData.getRecords());
        return new PageRes<>(vos, pageData.getTotal(),req);
    }

    @Override
    public Boolean createData(SysOperateLog entity) {
        return baseMapper.insert(entity) > 0;
    }

    @Override
    public Boolean updateData(SysOperateLog entity) {
        return baseMapper.updateById(entity) > 0;
    }

    @Override
    public Boolean removeData(Long id) {
        return baseMapper.deleteById(id) > 0;
    }


    @Override
    public SysOperateLogVO getData(Long id) {
        final SysOperateLog info = baseMapper.selectById(id);
        return SysOperateLogConvert.INSTANCE.convertVo(info);
    }

}

