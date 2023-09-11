package vip.mate.system.service;

import vip.mate.system.entity.SysDict;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.system.vo.SysDictVO;
import vip.mate.system.req.SysDictReq;
import vip.mate.core.mybatis.res.PageRes;

import java.util.List;

/**
 * <p>
 * 字典类型 服务类
 * </p>
 *
 * @author matevip
 * @since 2023-09-03
 */
public interface SysDictService extends IService<SysDict> {

    /**
     * 列表查询
     *
     * @param req 参数
     * @return 分页结果
     */
    PageRes<SysDictVO> queryPage(SysDictReq req);

    /**
     * 新增
     *
     * @param entity 实体类对象
     * @return true：成功，false：失败
     */
    Boolean createData(SysDict entity);

    /**
     * 修改
     *
     * @param entity 实体类对象
     * @return true：成功，false：失败
     */
    Boolean updateData(SysDict entity);

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
     * @return true：成功，false：失败
     */
    SysDictVO getData(Long id);

    /**
     * 刷新字典缓存
     */
    void refreshTransCache();

    /**
     * 获取字典列表
     *
     * @param sysDictReq 查询参数
     * @return List<SysDictVO>
     */
    List<SysDictVO> getList(SysDictReq sysDictReq);
}
