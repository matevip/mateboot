package vip.mate.system.service;

import vip.mate.system.entity.SysDictItem;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.system.vo.SysDictItemVO;
import vip.mate.system.req.SysDictItemReq;
import vip.mate.core.mybatis.res.PageRes;

/**
* <p>
* 字典项 服务类
* </p>
*
* @author matevip
* @since 2023-09-03
*/
public interface SysDictItemService extends IService<SysDictItem> {

    /**
     * 列表查询
     * @param req 参数
     * @return 分页结果
     */
    PageRes<SysDictItemVO> queryPage(SysDictItemReq req);

    /**
     * 新增
     * @param entity 实体类对象
     * @return true：成功，false：失败
     */
    Boolean createData(SysDictItem entity);

    /**
    * 修改
    * @param entity 实体类对象
    * @return true：成功，false：失败
    */
    Boolean updateData(SysDictItem entity);

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
    SysDictItemVO getData(Long id);

    /**
     * 删除字典类型同时删除字典项
     * @param dictId 字典类型ID
     * @return
     */
    Boolean deleteByDictId(Long dictId);
}
