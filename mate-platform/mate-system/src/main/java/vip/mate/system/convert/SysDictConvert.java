package vip.mate.system.convert;

import vip.mate.system.entity.SysDict;
import vip.mate.system.vo.SysDictVO;
import vip.mate.system.req.SysDictReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * SysDictConverter
 * </p>
 *
 * @author matevip
 * @since 2023-09-03
 */
@Mapper
public interface SysDictConvert{

    SysDictConvert INSTANCE = Mappers.getMapper(SysDictConvert.class);
    /**
     * 实体类转VO
     * @param bean entity
     * @return SysDictVO
     */
    SysDictVO convertVo(SysDict bean);

    /**
     * 实体类转VO -集合
     * @param entityList entityList
     * @return list
     */
    List<SysDictVO> toVo(List<SysDict> entityList);

    /**
    * vo转实体类
    * @param vo vo
    * @return SysDict
    */
    SysDict convert(SysDictVO vo);

    /**
     * 实体类转req
     * @param bean entity
     * @return SysDictReq
     */
    SysDictReq convertReq(SysDict bean);

    /**
     * 实体类转req -集合
     * @param entityList entityList
     * @return list
     */
    List<SysDictReq> toReq(List<SysDict> entityList);

    /**
    * req转实体类
    * @param req req
    * @return SysDict
    */
    SysDict convert(SysDictReq req);

    /**
    * req转VO
    * @param req req
    * @return SysDictReq
    */
    SysDictVO toVo(SysDictReq req);

    /**
    * vo转req
    * @param vo vo
    * @return SysDictReq
    */
    SysDictReq toReq(SysDictVO vo);

}
