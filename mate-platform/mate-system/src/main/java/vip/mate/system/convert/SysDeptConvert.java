package vip.mate.system.convert;

import vip.mate.system.entity.SysDept;
import vip.mate.system.vo.SysDeptVO;
import vip.mate.system.req.SysDeptReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * SysDeptConverter
 * </p>
 *
 * @author matevip
 * @since 2023-08-24
 */
@Mapper
public interface SysDeptConvert{

    SysDeptConvert INSTANCE = Mappers.getMapper(SysDeptConvert.class);
    /**
     * 实体类转VO
     * @param bean entity
     * @return SysDeptVO
     */
    SysDeptVO convertVo(SysDept bean);

    /**
     * 实体类转VO -集合
     * @param entityList entityList
     * @return list
     */
    List<SysDeptVO> toVo(List<SysDept> entityList);

    /**
    * vo转实体类
    * @param vo vo
    * @return SysDept
    */
    SysDept convert(SysDeptVO vo);

    /**
     * 实体类转req
     * @param bean entity
     * @return SysDeptReq
     */
    SysDeptReq convertReq(SysDept bean);

    /**
     * 实体类转req -集合
     * @param entityList entityList
     * @return list
     */
    List<SysDeptReq> toReq(List<SysDept> entityList);

    /**
    * req转实体类
    * @param req req
    * @return SysDept
    */
    SysDept convert(SysDeptReq req);

    /**
    * req转VO
    * @param req req
    * @return SysDeptReq
    */
    SysDeptVO toVo(SysDeptReq req);

    /**
    * vo转req
    * @param vo vo
    * @return SysDeptReq
    */
    SysDeptReq toReq(SysDeptVO vo);

}
