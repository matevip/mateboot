package vip.mate.system.service.impl;

import vip.mate.system.entity.SysDictItem;
import vip.mate.system.mapper.SysDictItemMapper;
import vip.mate.system.service.SysDictItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vip.mate.system.vo.SysDictItemVO;
import vip.mate.system.req.SysDictItemReq;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import java.util.*;
import cn.hutool.core.collection.CollectionUtil;
import vip.mate.system.convert.SysDictItemConvert;
import vip.mate.core.mybatis.res.PageRes;


/**
 * <p>
 * 字典项 服务实现类
 * </p>
 *
 * @author matevip
 * @since 2023-09-03
 */
@Service
public class SysDictItemServiceImpl extends ServiceImpl<SysDictItemMapper, SysDictItem> implements SysDictItemService {

    @Override
    public PageRes<SysDictItemVO> queryPage(SysDictItemReq req) {
        SysDictItem info = SysDictItemConvert.INSTANCE.convert(req);
        Page<SysDictItem> pageData = baseMapper.selectPage(new Page<>(req.getPageNo(), req.getPageSize()), Wrappers.query(info));
        if(CollectionUtil.isEmpty(pageData.getRecords())){
            return PageRes.empty();
        }
        List<SysDictItemVO> vos = SysDictItemConvert.INSTANCE.toVo(pageData.getRecords());
        return new PageRes<>(vos, pageData.getTotal(),req);
    }

    @Override
    public Boolean createData(SysDictItem entity) {
        return baseMapper.insert(entity) > 0;
    }

    @Override
    public Boolean updateData(SysDictItem entity) {
        return baseMapper.updateById(entity) > 0;
    }

    @Override
    public Boolean removeData(Long id) {
        return baseMapper.deleteById(id) > 0;
    }


    @Override
    public SysDictItemVO getData(Long id) {
        final SysDictItem info = baseMapper.selectById(id);
        return SysDictItemConvert.INSTANCE.convertVo(info);
    }

}

