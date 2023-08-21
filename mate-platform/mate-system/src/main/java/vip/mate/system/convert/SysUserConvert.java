package vip.mate.system.convert;

import vip.mate.system.entity.SysUser;
import vip.mate.system.vo.SysUserVO;
import vip.mate.system.req.SysUserReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * SysUserConverter
 * </p>
 *
 * @author matevip
 * @since 2023-08-21
 */
@Mapper
public interface SysUserConvert{

    SysUserConvert INSTANCE = Mappers.getMapper(SysUserConvert.class);
    /**
     * 实体类转VO
     * @param bean entity
     * @return SysUserVO
     */
    SysUserVO convertVo(SysUser bean);

    /**
     * 实体类转VO -集合
     * @param entityList entityList
     * @return list
     */
    List<SysUserVO> toVo(List<SysUser> entityList);

    /**
    * vo转实体类
    * @param vo vo
    * @return SysUser
    */
    SysUser convert(SysUserVO vo);

    /**
     * 实体类转req
     * @param bean entity
     * @return SysUserReq
     */
    SysUserReq convertReq(SysUser bean);

    /**
     * 实体类转req -集合
     * @param entityList entityList
     * @return list
     */
    List<SysUserReq> toReq(List<SysUser> entityList);

    /**
    * req转实体类
    * @param req req
    * @return SysUser
    */
    SysUser convert(SysUserReq req);

    /**
    * req转VO
    * @param req req
    * @return SysUserReq
    */
    SysUserVO toVo(SysUserReq req);

    /**
    * vo转req
    * @param vo vo
    * @return SysUserReq
    */
    SysUserReq toReq(SysUserVO vo);

}
