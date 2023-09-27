package vip.mate.system.convert;

import vip.mate.system.entity.SysLoginLog;
import vip.mate.system.vo.SysLoginLogVO;
import vip.mate.system.req.SysLoginLogReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * SysLoginLogConverter
 * </p>
 *
 * @author matevip
 * @since 2023-09-27
 */
@Mapper
public interface SysLoginLogConvert{

    SysLoginLogConvert INSTANCE = Mappers.getMapper(SysLoginLogConvert.class);
    /**
     * 实体类转VO
     * @param bean entity
     * @return SysLoginLogVO
     */
    SysLoginLogVO convertVo(SysLoginLog bean);

    /**
     * 实体类转VO -集合
     * @param entityList entityList
     * @return list
     */
    List<SysLoginLogVO> toVo(List<SysLoginLog> entityList);

    /**
    * vo转实体类
    * @param vo vo
    * @return SysLoginLog
    */
    SysLoginLog convert(SysLoginLogVO vo);

    /**
     * 实体类转req
     * @param bean entity
     * @return SysLoginLogReq
     */
    SysLoginLogReq convertReq(SysLoginLog bean);

    /**
     * 实体类转req -集合
     * @param entityList entityList
     * @return list
     */
    List<SysLoginLogReq> toReq(List<SysLoginLog> entityList);

    /**
    * req转实体类
    * @param req req
    * @return SysLoginLog
    */
    SysLoginLog convert(SysLoginLogReq req);

    /**
    * req转VO
    * @param req req
    * @return SysLoginLogReq
    */
    SysLoginLogVO toVo(SysLoginLogReq req);

    /**
    * vo转req
    * @param vo vo
    * @return SysLoginLogReq
    */
    SysLoginLogReq toReq(SysLoginLogVO vo);

}
