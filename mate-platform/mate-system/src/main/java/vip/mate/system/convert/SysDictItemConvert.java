package vip.mate.system.convert;

import vip.mate.system.entity.SysDictItem;
import vip.mate.system.vo.SysDictItemVO;
import vip.mate.system.req.SysDictItemReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * SysDictItemConverter
 * </p>
 *
 * @author matevip
 * @since 2023-09-03
 */
@Mapper
public interface SysDictItemConvert{

    SysDictItemConvert INSTANCE = Mappers.getMapper(SysDictItemConvert.class);
    /**
     * 实体类转VO
     * @param bean entity
     * @return SysDictItemVO
     */
    SysDictItemVO convertVo(SysDictItem bean);

    /**
     * 实体类转VO -集合
     * @param entityList entityList
     * @return list
     */
    List<SysDictItemVO> toVo(List<SysDictItem> entityList);

    /**
    * vo转实体类
    * @param vo vo
    * @return SysDictItem
    */
    SysDictItem convert(SysDictItemVO vo);

    /**
     * 实体类转req
     * @param bean entity
     * @return SysDictItemReq
     */
    SysDictItemReq convertReq(SysDictItem bean);

    /**
     * 实体类转req -集合
     * @param entityList entityList
     * @return list
     */
    List<SysDictItemReq> toReq(List<SysDictItem> entityList);

    /**
    * req转实体类
    * @param req req
    * @return SysDictItem
    */
    SysDictItem convert(SysDictItemReq req);

    /**
    * req转VO
    * @param req req
    * @return SysDictItemReq
    */
    SysDictItemVO toVo(SysDictItemReq req);

    /**
    * vo转req
    * @param vo vo
    * @return SysDictItemReq
    */
    SysDictItemReq toReq(SysDictItemVO vo);

}
