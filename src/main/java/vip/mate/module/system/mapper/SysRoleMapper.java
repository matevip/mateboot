package vip.mate.module.system.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import vip.mate.module.system.entity.SysRole;
import vip.mate.module.system.vo.SysRoleVO;


import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author xuzf
 * @since 2020-06-28
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<SysRoleVO> tree();

}
