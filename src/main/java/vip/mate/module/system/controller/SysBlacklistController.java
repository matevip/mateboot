package vip.mate.module.system.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import vip.mate.core.common.api.Result;
import vip.mate.core.database.entity.Search;
import vip.mate.core.web.controller.BaseController;
import vip.mate.core.web.util.CollectionUtil;
import vip.mate.module.system.entity.SysBlacklist;
import vip.mate.module.system.service.ISysBlacklistService;

import javax.validation.Valid;

/**
 * <p>
 * 系统黑名单表 前端控制器
 * </p>
 *
 * @author pangu
 * @since 2020-08-26
 */
@RestController
@AllArgsConstructor
@RequestMapping("/mate-system/blacklist")
@Api(tags = "黑名单管理")
public class SysBlacklistController extends BaseController {

    private final ISysBlacklistService sysBlacklistService;


    /**
     * 黑名单分页
     * @param page　分页参数
     * @param search　关键词
     * @return Result
     */
    @GetMapping("/page")
    @ApiOperation(value = "黑名单分页", notes = "黑名单分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", required = true, value = "当前页", paramType = "form"),
            @ApiImplicitParam(name = "size", required = true, value = "每页显示数据", paramType = "form"),
            @ApiImplicitParam(name = "keyword", required = true, value = "模糊查询关键词", paramType = "form"),
            @ApiImplicitParam(name = "startDate", required = true, value = "创建开始日期", paramType = "form"),
            @ApiImplicitParam(name = "endDate", required = true, value = "创建结束日期", paramType = "form"),
    })
    public Result<?> page(Page page, Search search) {
        return Result.data(sysBlacklistService.listPage(page, search));
    }

    /**
     * 黑名单设置
     * @param sysBlacklist SysBlacklist对象
     * @return Result
     */
    @PostMapping("/set")
    @ApiOperation(value = "黑名单设置", notes = "黑名单设置,支持新增或修改")
    public Result<?> set(@Valid @RequestBody SysBlacklist sysBlacklist) {
        return Result.condition(sysBlacklistService.saveOrUpdate(sysBlacklist));
    }

    /**
     * 黑名单信息
     * @param id　id
     * @return Result
     */
    @GetMapping("/get")
    @ApiOperation(value = "黑名单信息", notes = "黑名单信息,根据ID查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", required = true, value = "主键ID", paramType = "form"),
    })
    public Result<?> info(@RequestParam String id) {
        return Result.data(sysBlacklistService.getById(id));
    }

    /**
     * 黑名单删除
     * @param ids　多个id采用逗号分隔
     * @return Result
     */
    @PostMapping("/del")
    @ApiOperation(value = "黑名单删除", notes = "黑名单删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", required = true, value = "多个用,号隔开", paramType = "form")
    })
    @Transactional(rollbackFor = Exception.class)
    public Result<?> del(@RequestParam String ids) {
        return Result.condition(sysBlacklistService.removeByIds(CollectionUtil.stringToCollection(ids)));
    }
}

