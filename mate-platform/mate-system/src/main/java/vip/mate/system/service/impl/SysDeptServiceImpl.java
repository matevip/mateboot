package vip.mate.system.service.impl;

import vip.mate.core.common.constant.MateConstant;
import vip.mate.core.common.utils.TreeUtils;
import vip.mate.system.entity.SysDept;
import vip.mate.system.mapper.SysDeptMapper;
import vip.mate.system.service.SysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vip.mate.system.vo.SysDeptVO;
import vip.mate.system.req.SysDeptReq;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import java.util.*;
import cn.hutool.core.collection.CollectionUtil;
import vip.mate.system.convert.SysDeptConvert;
import vip.mate.core.mybatis.res.PageRes;


/**
 * <p>
 * 部门 服务实现类
 * </p>
 *
 * @author matevip
 * @since 2023-08-24
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements SysDeptService {

    @Override
    public PageRes<SysDeptVO> queryPage(SysDeptReq req) {
        SysDept info = SysDeptConvert.INSTANCE.convert(req);
        Page<SysDept> pageData = baseMapper.selectPage(new Page<>(req.getPageNo(), req.getPageSize()), Wrappers.query(info));
        if(CollectionUtil.isEmpty(pageData.getRecords())){
            return PageRes.empty();
        }
        List<SysDeptVO> vos = SysDeptConvert.INSTANCE.toVo(pageData.getRecords());
        return new PageRes<>(vos, pageData.getTotal(),req);
    }

    @Override
    public Boolean createData(SysDept entity) {
        return baseMapper.insert(entity) > 0;
    }

    @Override
    public Boolean updateData(SysDept entity) {
        return baseMapper.updateById(entity) > 0;
    }

    @Override
    public Boolean removeData(Long id) {
        return baseMapper.deleteById(id) > 0;
    }


    @Override
    public SysDeptVO getData(Long id) {
        final SysDept info = baseMapper.selectById(id);
        return SysDeptConvert.INSTANCE.convertVo(info);
    }

    @Override
    public List<SysDeptVO> getList() {
        Map<String, Object> params = new HashMap<>();

        // 机构列表
        List<SysDept> entityList = baseMapper.getList(params);

        return TreeUtils.build(SysDeptConvert.INSTANCE.toVo(entityList));
    }

}

