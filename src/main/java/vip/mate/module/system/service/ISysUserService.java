package vip.mate.module.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.core.database.entity.Search;
import vip.mate.module.system.dto.UserInfo;
import vip.mate.module.system.entity.SysUser;
import vip.mate.module.system.poi.SysUserPOI;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author xuzf
 * @since 2020-06-18
 */
public interface ISysUserService extends IService<SysUser> {

    boolean status(String ids, String status);

    IPage<SysUser> listPage(Page page, Search search);

    List<SysUserPOI> export();

    /**
     * 将sysUser转换为UserInfo
     * @param sysUser SysUser对象
     * @return UserInfo
     */
    UserInfo getUserInfo(SysUser sysUser);

}
