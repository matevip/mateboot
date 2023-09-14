package vip.mate.system.service;

import vip.mate.system.entity.SysUserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.core.mybatis.res.PageRes;

import java.util.List;

/**
 * <p>
 * 用户角色 服务类
 * </p>
 *
 * @author matevip
 * @since 2023-08-22
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 根据用户ID删除用户角色
     *
     * @param userId
     * @return
     */
    Boolean removeByUserId(Long userId);

    /**
     * 批量保存用户角色
     *
     * @param userId  用户ID
     * @param roleIds 角色ID集合
     * @return
     */
    Boolean saveBatch(Long userId, List<Long> roleIds);

    /**
     * 根据用户ID获取角色ID集合
     *
     * @param userId 用户ID
     * @return 角色ID集合
     */
    List<Long> getRoleIdsByUserId(Long userId);
}
