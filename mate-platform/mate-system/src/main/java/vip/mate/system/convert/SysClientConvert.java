package vip.mate.system.convert;

import vip.mate.system.entity.SysClient;
import vip.mate.system.vo.SysClientVO;
import vip.mate.system.req.SysClientReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * SysClientConverter
 * </p>
 *
 * @author matevip
 * @since 2023-08-21
 */
@Mapper
public interface SysClientConvert{

    SysClientConvert INSTANCE = Mappers.getMapper(SysClientConvert.class);
    /**
     * 实体类转VO
     * @param bean entity
     * @return SysClientVO
     */
    SysClientVO convertVo(SysClient bean);

    /**
     * 实体类转VO -集合
     * @param entityList entityList
     * @return list
     */
    List<SysClientVO> toVo(List<SysClient> entityList);

    /**
    * vo转实体类
    * @param vo vo
    * @return SysClient
    */
    SysClient convert(SysClientVO vo);

    /**
     * 实体类转req
     * @param bean entity
     * @return SysClientReq
     */
    SysClientReq convertReq(SysClient bean);

    /**
     * 实体类转req -集合
     * @param entityList entityList
     * @return list
     */
    List<SysClientReq> toReq(List<SysClient> entityList);

    /**
    * req转实体类
    * @param req req
    * @return SysClient
    */
    SysClient convert(SysClientReq req);

    /**
    * req转VO
    * @param req req
    * @return SysClientReq
    */
    SysClientVO toVo(SysClientReq req);

    /**
    * vo转req
    * @param vo vo
    * @return SysClientReq
    */
    SysClientReq toReq(SysClientVO vo);

}
