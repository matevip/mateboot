package vip.mate.module.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.core.common.api.Result;
import vip.mate.core.database.entity.Search;
import vip.mate.module.system.entity.SysDict;

import java.util.List;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author xuzf
 * @since 2020-07-01
 */
public interface ISysDictService extends IService<SysDict> {

	/**
	 * 获取值
	 *
	 * @param code    代码
	 * @param dictKey 键值
	 * @return String
	 */
	Result<String> getValue(String code, String dictKey);

	/**
	 * 获取列表
	 *
	 * @param code 　代码
	 * @return List
	 */
	Result<List<SysDict>> getList(String code);

	/**
	 * 获取分页
	 *
	 * @param page   　分页参数
	 * @param search 　搜索关键词
	 * @return SysDict
	 */
	IPage<SysDict> listPage(Page<SysDict> page, Search search);

}
