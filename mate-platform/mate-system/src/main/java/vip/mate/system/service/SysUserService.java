package vip.mate.system.service;

import vip.mate.core.mybatis.service.BaseService;
import vip.mate.system.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.system.vo.SysUserVO;
import vip.mate.system.req.SysUserReq;
import vip.mate.core.mybatis.res.PageRes;

/**
* <p>
* 用户管理 服务类
* </p>
*
* @author matevip
* @since 2023-08-21
*/
public interface SysUserService extends BaseService<SysUser> {

    /**
     * 列表查询
     * @param req 参数
     * @return 分页结果
     */
    PageRes<SysUserVO> queryPage(SysUserReq req);

    /**
     * 新增
     * @param req 实体类对象
     * @return true：成功，false：失败
     */
    Boolean createData(SysUserReq req);

    /**
    * 修改
    * @param req 实体类对象
    * @return true：成功，false：失败
    */
    Boolean updateData(SysUserReq req);

    /**
     * 删除
     * @param id 主键
     * @return true：成功，false：失败
     */
    Boolean removeData(Long id);

    /**
     * 单条查询
     * @param id 主键
     * @return true：成功，false：失败
     */
    SysUserVO getData(Long id);

    /**
     * 根据用户名查询
     * @param username 用户名
     * @return
     */
    SysUser queryByUsername(String username);

    /**
     * 判断用户是否满足条件
     * @param user 用户实体
     * @return
     */
    Boolean userCondition(SysUser user);
}
