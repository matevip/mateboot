package vip.mate.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fhs.trans.service.impl.DictionaryTransService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.transaction.annotation.Transactional;
import vip.mate.core.common.exception.ServerException;
import vip.mate.system.convert.SysRoleConvert;
import vip.mate.system.entity.SysDict;
import vip.mate.system.entity.SysDictItem;
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
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

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
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService, InitializingBean {
    private final SysDictItemService sysDictItemService;
    private final DictionaryTransService dictionaryTransService;

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

    @Override
    public void afterPropertiesSet() {
        refreshTransCache();
    }

    @Override
    public void refreshTransCache() {
        System.out.println("-----------刷新字典缓存-----------");
        // 异步不阻塞主线程，不会 增加启动用时
        CompletableFuture.supplyAsync(() -> {
            // 获取所有的字典项数据
            List<SysDictItem> dataList = sysDictItemService.list(new LambdaQueryWrapper<>());
            // 根据类型分组
            Map<Long, List<SysDictItem>> dictData = dataList.stream().collect(Collectors
                    .groupingBy(SysDictItem::getDictId));
            List<SysDict> dictEntities = super.list();
            for (SysDict dictEntity : dictEntities) {
                if (dictData.containsKey(dictEntity.getId())) {
                    dictionaryTransService.refreshCache(dictEntity.getDictCode(), dictData.get(dictEntity.getId())
                            .stream().collect(Collectors.toMap(SysDictItem::getDictValue, SysDictItem::getDictName)));
                }
            }
            return null;
        });
    }

    @Override
    public List<SysDictVO> getList(SysDictReq sysDictReq) {
        List<SysDict> sysDicts = baseMapper.selectList(getWrapper(sysDictReq));
        return SysDictConvert.INSTANCE.toVo(sysDicts);
    }

    private Wrapper getWrapper(SysDictReq req) {
        LambdaQueryWrapper<SysDict> wrapper = Wrappers.<SysDict>lambdaQuery();
        wrapper.like(StrUtil.isNotBlank(req.getDictName()), SysDict::getDictName, req.getDictName());
        wrapper.like(StrUtil.isNotBlank(req.getDictCode()), SysDict::getDictCode, req.getDictCode());
        return wrapper;
    }
}

