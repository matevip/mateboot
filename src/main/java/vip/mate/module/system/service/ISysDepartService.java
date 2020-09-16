package vip.mate.module.system.service;


import com.baomidou.mybatisplus.extension.service.IService;
import vip.mate.module.system.entity.SysDepart;
import vip.mate.module.system.poi.SysDepartPOI;
import vip.mate.module.system.vo.SysDepartVO;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 组织机构表 服务类
 * </p>
 *
 * @author xuzf
 * @since 2020-06-28
 */
public interface ISysDepartService extends IService<SysDepart> {

    List<SysDepartVO> tree();

    List<SysDepartVO> searchList(Map<String, Object> search);

    List<SysDepartPOI> export();

}
