package vip.mate.module.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import vip.mate.module.system.entity.SysDepart;
import vip.mate.module.system.vo.SysDepartVO;

import java.util.List;

/**
 * <p>
 * 组织机构表 Mapper 接口
 * </p>
 *
 * @author xuzf
 * @since 2020-06-28
 */
public interface SysDepartMapper extends BaseMapper<SysDepart> {

    List<SysDepartVO> tree();

}
