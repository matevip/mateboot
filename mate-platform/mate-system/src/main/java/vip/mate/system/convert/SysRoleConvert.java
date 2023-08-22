package vip.mate.system.convert;

import vip.mate.system.entity.SysRole;
import vip.mate.system.vo.SysRoleVO;
import vip.mate.system.req.SysRoleReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * SysRoleConverter
 * </p>
 *
 * @author matevip
 * @since 2023-08-22
 */
@Mapper
public interface SysRoleConvert{

    SysRoleConvert INSTANCE = Mappers.getMapper(SysRoleConvert.class);
    /**
     * 实体类转VO
     * @param bean entity
     * @return SysRoleVO
     */
    SysRoleVO convertVo(SysRole bean);

    /**
     * 实体类转VO -集合
     * @param entityList entityList
     * @return list
     */
    List<SysRoleVO> toVo(List<SysRole> entityList);

    /**
    * vo转实体类
    * @param vo vo
    * @return SysRole
    */
    SysRole convert(SysRoleVO vo);

    /**
     * 实体类转req
     * @param bean entity
     * @return SysRoleReq
     */
    SysRoleReq convertReq(SysRole bean);

    /**
     * 实体类转req -集合
     * @param entityList entityList
     * @return list
     */
    List<SysRoleReq> toReq(List<SysRole> entityList);

    /**
    * req转实体类
    * @param req req
    * @return SysRole
    */
    SysRole convert(SysRoleReq req);

    /**
    * req转VO
    * @param req req
    * @return SysRoleReq
    */
    SysRoleVO toVo(SysRoleReq req);

    /**
    * vo转req
    * @param vo vo
    * @return SysRoleReq
    */
    SysRoleReq toReq(SysRoleVO vo);

}
