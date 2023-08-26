package vip.mate.system.service;

import vip.mate.system.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.system.vo.SysDeptVO;
import vip.mate.system.req.SysDeptReq;
import vip.mate.core.mybatis.res.PageRes;

import java.util.List;

/**
* <p>
* 部门 服务类
* </p>
*
* @author matevip
* @since 2023-08-24
*/
public interface SysDeptService extends IService<SysDept> {

    /**
     * 列表查询
     * @param req 参数
     * @return 分页结果
     */
    PageRes<SysDeptVO> queryPage(SysDeptReq req);

    /**
     * 新增
     * @param entity 实体类对象
     * @return true：成功，false：失败
     */
    Boolean createData(SysDept entity);

    /**
    * 修改
    * @param entity 实体类对象
    * @return true：成功，false：失败
    */
    Boolean updateData(SysDept entity);

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
    SysDeptVO getData(Long id);

    /**
     * 部门列表
     * @return
     */
    List<SysDeptVO> getList();
}
