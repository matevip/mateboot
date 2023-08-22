package vip.mate.system.service.impl;

import vip.mate.system.entity.SysRoleMenu;
import vip.mate.system.mapper.SysRoleMenuMapper;
import vip.mate.system.service.SysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vip.mate.system.vo.SysRoleMenuVO;
import vip.mate.system.req.SysRoleMenuReq;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;
import java.util.*;
import cn.hutool.core.collection.CollectionUtil;
import vip.mate.system.convert.SysRoleMenuConvert;
import vip.mate.core.mybatis.res.PageRes;


/**
 * <p>
 * 角色菜单 服务实现类
 * </p>
 *
 * @author matevip
 * @since 2023-08-22
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Override
    public PageRes<SysRoleMenuVO> queryPage(SysRoleMenuReq req) {
        SysRoleMenu info = SysRoleMenuConvert.INSTANCE.convert(req);
        Page<SysRoleMenu> pageData = baseMapper.selectPage(new Page<>(req.getPageNo(), req.getPageSize()), Wrappers.query(info));
        if(CollectionUtil.isEmpty(pageData.getRecords())){
            return PageRes.empty();
        }
        List<SysRoleMenuVO> vos = SysRoleMenuConvert.INSTANCE.toVo(pageData.getRecords());
        return new PageRes<>(vos, pageData.getTotal(),req);
    }

    @Override
    public Boolean createData(SysRoleMenu entity) {
        return baseMapper.insert(entity) > 0;
    }

    @Override
    public Boolean updateData(SysRoleMenu entity) {
        return baseMapper.updateById(entity) > 0;
    }

    @Override
    public Boolean removeData(Long id) {
        return baseMapper.deleteById(id) > 0;
    }


    @Override
    public SysRoleMenuVO getData(Long id) {
        final SysRoleMenu info = baseMapper.selectById(id);
        return SysRoleMenuConvert.INSTANCE.convertVo(info);
    }

}

