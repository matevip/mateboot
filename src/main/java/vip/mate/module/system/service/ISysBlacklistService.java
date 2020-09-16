package vip.mate.module.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import vip.mate.core.database.entity.Search;

import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.module.system.entity.SysBlacklist;

/**
 * <p>
 * 系统黑名单表 服务类
 * </p>
 *
 * @author pangu
 * @since 2020-08-26
 */
public interface ISysBlacklistService extends IService<SysBlacklist> {

    IPage<SysBlacklist> listPage(Page page, Search search);

}
