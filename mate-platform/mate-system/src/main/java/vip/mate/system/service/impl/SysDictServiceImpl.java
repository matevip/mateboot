package vip.mate.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import vip.mate.core.common.exception.ServerException;
import vip.mate.system.entity.SysDict;
import vip.mate.system.mapper.SysDictMapper;
import vip.mate.system.service.SysDictItemService;
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
@RequiredArgsConstructor
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    private final SysDictItemService sysDictItemService;

    @Override
    public PageRes<SysDictVO> queryPage(SysDictReq req) {
        SysDict info = SysDictConvert.INSTANCE.convert(req);
        Page<SysDict> pageData = baseMapper.selectPage(new Page<>(req.getPageNo(), req.getPageSize()), Wrappers.query(info));
        if (CollectionUtil.isEmpty(pageData.getRecords())) {
            return PageRes.empty();
        }
        List<SysDictVO> vos = SysDictConvert.INSTANCE.toVo(pageData.getRecords());
        return new PageRes<>(vos, pageData.getTotal(), req);
    }

    @Override
    public Boolean createData(SysDict entity) {
        dictCodeIsExist(entity);
        return baseMapper.insert(entity) > 0;
    }

    @Override
    public Boolean updateData(SysDict entity) {
        dictCodeIsExist(entity);
        return baseMapper.updateById(entity) > 0;
    }

    /**
     * 判断字典类型编码是否存在
     *
     * @param entity 字典类型实体类
     */
    public void dictCodeIsExist(SysDict entity) {
        LambdaQueryWrapper<SysDict> wrapper = Wrappers.<SysDict>lambdaQuery().eq(SysDict::getDictCode, entity.getDictCode());
        if (ObjectUtil.isNotEmpty(entity.getId())) {
            wrapper.ne(SysDict::getId, entity.getId());
        }
        List<SysDict> sysDicts = baseMapper.selectList(wrapper);
        if (sysDicts.size() > 0) {
            throw new ServerException("字典类型编码已存在");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean removeData(Long id) {
        // 删除字典项
        sysDictItemService.deleteByDictId(id);
        // 删除字典类型
        return baseMapper.deleteById(id) > 0;
    }


    @Override
    public SysDictVO getData(Long id) {
        final SysDict info = baseMapper.selectById(id);
        return SysDictConvert.INSTANCE.convertVo(info);
    }

}

