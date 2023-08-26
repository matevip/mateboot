package vip.mate.system.mapper;

import vip.mate.system.entity.SysDept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 部门 Mapper 接口
 * </p>
 *
 * @author matevip
 * @since 2023-08-24
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * 获取部门列表
     *
     * @param params 参数
     * @return
     */
    List<SysDept> getList(Map<String, Object> params);

    /**
     * 获取所有机构的id、pid列表
     */
    List<SysDept> getIdAndPidList();

}
