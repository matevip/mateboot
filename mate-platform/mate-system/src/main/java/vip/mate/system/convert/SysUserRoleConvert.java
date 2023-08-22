package vip.mate.system.convert;

import vip.mate.system.entity.SysUserRole;
import vip.mate.system.vo.SysUserRoleVO;
import vip.mate.system.req.SysUserRoleReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * SysUserRoleConverter
 * </p>
 *
 * @author matevip
 * @since 2023-08-22
 */
@Mapper
public interface SysUserRoleConvert{

    SysUserRoleConvert INSTANCE = Mappers.getMapper(SysUserRoleConvert.class);
    /**
     * 实体类转VO
     * @param bean entity
     * @return SysUserRoleVO
     */
    SysUserRoleVO convertVo(SysUserRole bean);

    /**
     * 实体类转VO -集合
     * @param entityList entityList
     * @return list
     */
    List<SysUserRoleVO> toVo(List<SysUserRole> entityList);

    /**
    * vo转实体类
    * @param vo vo
    * @return SysUserRole
    */
    SysUserRole convert(SysUserRoleVO vo);

    /**
     * 实体类转req
     * @param bean entity
     * @return SysUserRoleReq
     */
    SysUserRoleReq convertReq(SysUserRole bean);

    /**
     * 实体类转req -集合
     * @param entityList entityList
     * @return list
     */
    List<SysUserRoleReq> toReq(List<SysUserRole> entityList);

    /**
    * req转实体类
    * @param req req
    * @return SysUserRole
    */
    SysUserRole convert(SysUserRoleReq req);

    /**
    * req转VO
    * @param req req
    * @return SysUserRoleReq
    */
    SysUserRoleVO toVo(SysUserRoleReq req);

    /**
    * vo转req
    * @param vo vo
    * @return SysUserRoleReq
    */
    SysUserRoleReq toReq(SysUserRoleVO vo);

}
