package vip.mate.system.vo;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.Accessors;

/**
 * <p>
 * 操作日志VO
 * </p>
 *
 * @author matevip
 * @since 2023-09-25
 */
@Data
@Accessors(chain = true)
@Schema(name = "SysOperateLogVO对象", description = "操作日志")
public class SysOperateLogVO {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "模块名")
    private String module;

    @Schema(description = "操作名")
    private String name;

    @Schema(description = "请求URI")
    private String reqUri;

    @Schema(description = "请求方法")
    private String reqMethod;

    @Schema(description = "请求参数")
    private String reqParams;

    @Schema(description = "方法名")
    private String method;

    @Schema(description = "操作IP")
    private String ip;

    @Schema(description = "登录地点")
    private String address;

    @Schema(description = "User Agent")
    private String userAgent;

    @Schema(description = "操作系统")
    private String os;

    @Schema(description = "浏览器")
    private String browser;

    @Schema(description = "操作类型")
    private int businessType;

    @Schema(description = "执行时长")
    private Integer duration;

    @Schema(description = "操作状态  0：失败   1：成功")
    private int status;

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "操作人")
    private String realName;

    @Schema(description = "返回消息")
    private String jsonResult;

    @Schema(description = "返回消息")
    private String errorMsg;

    @Schema(description = "创建时间")
    private Date createTime;

}
