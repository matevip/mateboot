package vip.mate.system.convert;

import vip.mate.system.entity.SysRoleMenu;
import vip.mate.system.vo.SysRoleMenuVO;
import vip.mate.system.req.SysRoleMenuReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * SysRoleMenuConverter
 * </p>
 *
 * @author matevip
 * @since 2023-08-22
 */
@Mapper
public interface SysRoleMenuConvert{

    SysRoleMenuConvert INSTANCE = Mappers.getMapper(SysRoleMenuConvert.class);
    /**
     * 实体类转VO
     * @param bean entity
     * @return SysRoleMenuVO
     */
    SysRoleMenuVO convertVo(SysRoleMenu bean);

    /**
     * 实体类转VO -集合
     * @param entityList entityList
     * @return list
     */
    List<SysRoleMenuVO> toVo(List<SysRoleMenu> entityList);

    /**
    * vo转实体类
    * @param vo vo
    * @return SysRoleMenu
    */
    SysRoleMenu convert(SysRoleMenuVO vo);

    /**
     * 实体类转req
     * @param bean entity
     * @return SysRoleMenuReq
     */
    SysRoleMenuReq convertReq(SysRoleMenu bean);

    /**
     * 实体类转req -集合
     * @param entityList entityList
     * @return list
     */
    List<SysRoleMenuReq> toReq(List<SysRoleMenu> entityList);

    /**
    * req转实体类
    * @param req req
    * @return SysRoleMenu
    */
    SysRoleMenu convert(SysRoleMenuReq req);

    /**
    * req转VO
    * @param req req
    * @return SysRoleMenuReq
    */
    SysRoleMenuVO toVo(SysRoleMenuReq req);

    /**
    * vo转req
    * @param vo vo
    * @return SysRoleMenuReq
    */
    SysRoleMenuReq toReq(SysRoleMenuVO vo);

}
