package vip.mate.system.convert;

import vip.mate.core.log.req.SysOperateLogReq;
import vip.mate.system.entity.SysOperateLog;
import vip.mate.system.vo.SysOperateLogVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * <p>
 * SysOperateLogConverter
 * </p>
 *
 * @author matevip
 * @since 2023-09-25
 */
@Mapper
public interface SysOperateLogConvert{

    SysOperateLogConvert INSTANCE = Mappers.getMapper(SysOperateLogConvert.class);
    /**
     * 实体类转VO
     * @param bean entity
     * @return SysOperateLogVO
     */
    SysOperateLogVO convertVo(SysOperateLog bean);

    /**
     * 实体类转VO -集合
     * @param entityList entityList
     * @return list
     */
    List<SysOperateLogVO> toVo(List<SysOperateLog> entityList);

    /**
    * vo转实体类
    * @param vo vo
    * @return SysOperateLog
    */
    SysOperateLog convert(SysOperateLogVO vo);

    /**
     * 实体类转req
     * @param bean entity
     * @return SysOperateLogReq
     */
    SysOperateLogReq convertReq(SysOperateLog bean);

    /**
     * 实体类转req -集合
     * @param entityList entityList
     * @return list
     */
    List<SysOperateLogReq> toReq(List<SysOperateLog> entityList);

    /**
    * req转实体类
    * @param req req
    * @return SysOperateLog
    */
    SysOperateLog convert(SysOperateLogReq req);

    /**
    * req转VO
    * @param req req
    * @return SysOperateLogReq
    */
    SysOperateLogVO toVo(SysOperateLogReq req);

    /**
    * vo转req
    * @param vo vo
    * @return SysOperateLogReq
    */
    SysOperateLogReq toReq(SysOperateLogVO vo);

}
