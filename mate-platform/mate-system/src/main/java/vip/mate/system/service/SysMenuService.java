package vip.mate.system.service;

import vip.mate.system.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.system.vo.SysMenuVO;
import vip.mate.system.req.SysMenuReq;
import vip.mate.core.mybatis.res.PageRes;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单数据 服务类
 * </p>
 *
 * @author matevip
 * @since 2023-08-22
 */
public interface SysMenuService extends IService<SysMenu> {

    /**
     * 列表查询
     *
     * @param req 参数
     * @return 分页结果
     */
    PageRes<SysMenuVO> queryPage(SysMenuReq req);

    /**
     * 新增
     *
     * @param entity 实体类对象
     * @return true：成功，false：失败
     */
    Boolean createData(SysMenu entity);

    /**
     * 修改
     *
     * @param entity 实体类对象
     * @return true：成功，false：失败
     */
    Boolean updateData(SysMenu entity);

    /**
     * 删除
     *
     * @param id 主键
     * @return true：成功，false：失败
     */
    Boolean removeData(Long id);

    /**
     * 单条查询
     *
     * @param id 主键
     * @return
     */
    SysMenuVO getData(Long id);

    /**
     * 根据用户ID查询权限
     *
     * @param loginId
     * @return
     */
    Set<String> authority(String loginId);


    /**
     * 菜单列表
     *
     * @param type 菜单类型
     */
    List<SysMenuVO> getMenuList(Integer type);

    /**
     * 用户按钮列表
     *
     * @param id 菜单ID
     */
    List<SysMenuVO> getButtonList(Long id, Integer type);

    /**
     * 保存菜单和按钮
     *
     * @param req 菜单列表
     */
    String saveAll(SysMenuReq req);

    /**
     * 删除菜单和按钮
     *
     * @param ids 菜单ID
     */
    void deleteAll(Long[] ids);

    /**
     * 获取本地菜单列表
     *
     * @param type 菜单类型
     */
    List<SysMenu> getLocalMenuList(Integer type);

    /**
     * 获取用户菜单列表
     *
     * @param userId 用户ID
     * @param type   菜单类型
     */
    List<SysMenu> getUserMenuList(Long userId, Integer type);

}
