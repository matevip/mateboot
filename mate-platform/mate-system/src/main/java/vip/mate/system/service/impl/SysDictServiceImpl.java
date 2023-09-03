package vip.mate.system.service.impl;

import vip.mate.system.entity.SysDict;
import vip.mate.system.mapper.SysDictMapper;
import vip.mate.system.service.SysDictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vip.mate.system.vo.SysDictVO;
import vip.mate.system.req.SysDictReq;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import java.util.*;
import cn.hutool.core.collection.CollectionUtil;
import vip.mate.system.convert.SysDictConvert;
import vip.mate.core.mybatis.res.PageRes;


/**
 * <p>
 * 字典类型 服务实现类
 * </p>
 *
 * @author matevip
 * @since 2023-09-03
 */
@Service
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    @Override
    public PageRes<SysDictVO> queryPage(SysDictReq req) {
        SysDict info = SysDictConvert.INSTANCE.convert(req);
        Page<SysDict> pageData = baseMapper.selectPage(new Page<>(req.getPageNo(), req.getPageSize()), Wrappers.query(info));
        if(CollectionUtil.isEmpty(pageData.getRecords())){
            return PageRes.empty();
        }
        List<SysDictVO> vos = SysDictConvert.INSTANCE.toVo(pageData.getRecords());
        return new PageRes<>(vos, pageData.getTotal(),req);
    }

    @Override
    public Boolean createData(SysDict entity) {
        return baseMapper.insert(entity) > 0;
    }

    @Override
    public Boolean updateData(SysDict entity) {
        return baseMapper.updateById(entity) > 0;
    }

    @Override
    public Boolean removeData(Long id) {
        return baseMapper.deleteById(id) > 0;
    }


    @Override
    public SysDictVO getData(Long id) {
        final SysDict info = baseMapper.selectById(id);
        return SysDictConvert.INSTANCE.convertVo(info);
    }

}

