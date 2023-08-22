package vip.mate.system.convert;

import vip.mate.system.entity.SysMenu;
import vip.mate.system.vo.SysMenuVO;
import vip.mate.system.req.SysMenuReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * SysMenuConverter
 * </p>
 *
 * @author matevip
 * @since 2023-08-22
 */
@Mapper
public interface SysMenuConvert{

    SysMenuConvert INSTANCE = Mappers.getMapper(SysMenuConvert.class);
    /**
     * 实体类转VO
     * @param bean entity
     * @return SysMenuVO
     */
    SysMenuVO convertVo(SysMenu bean);

    /**
     * 实体类转VO -集合
     * @param entityList entityList
     * @return list
     */
    List<SysMenuVO> toVo(List<SysMenu> entityList);

    /**
    * vo转实体类
    * @param vo vo
    * @return SysMenu
    */
    SysMenu convert(SysMenuVO vo);

    /**
     * 实体类转req
     * @param bean entity
     * @return SysMenuReq
     */
    SysMenuReq convertReq(SysMenu bean);

    /**
     * 实体类转req -集合
     * @param entityList entityList
     * @return list
     */
    List<SysMenuReq> toReq(List<SysMenu> entityList);

    /**
    * req转实体类
    * @param req req
    * @return SysMenu
    */
    SysMenu convert(SysMenuReq req);

    /**
    * req转VO
    * @param req req
    * @return SysMenuReq
    */
    SysMenuVO toVo(SysMenuReq req);

    /**
    * vo转req
    * @param vo vo
    * @return SysMenuReq
    */
    SysMenuReq toReq(SysMenuVO vo);

    /**
     * list转vo list
     * @param list list
     * @return
     */
    List<SysMenuVO> convertList(List<SysMenu> list);

}
