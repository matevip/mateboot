package vip.mate.system.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
* <p>
* 操作日志
* </p>
*
* @author matevip
* @since 2023-09-25
*/
@Data
@Accessors(chain = true)
@TableName("mate_sys_operate_log")
@Schema(name = "SysOperateLog对象", description = "操作日志")
public class SysOperateLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id",type = IdType.ASSIGN_ID)
    @Schema(description = "ID")
    private Long id;

    @Schema(description = "模块名")
    @TableField("module")
    private String module;

    @Schema(description = "操作名")
    @TableField("name")
    private String name;

    @Schema(description = "请求URI")
    @TableField("req_uri")
    private String reqUri;

    @Schema(description = "请求方法")
    @TableField("req_method")
    private String reqMethod;

    @Schema(description = "请求参数")
    @TableField("req_params")
    private String reqParams;

    @Schema(description = "方法名")
    @TableField("method")
    private String method;

    @Schema(description = "操作IP")
    @TableField("ip")
    private String ip;

    @Schema(description = "登录地点")
    @TableField("address")
    private String address;

    @Schema(description = "User Agent")
    @TableField("user_agent")
    private String userAgent;

    @Schema(description = "操作类型")
    @TableField("business_type")
    private int businessType;

    @Schema(description = "执行时长")
    @TableField("duration")
    private Integer duration;

    @Schema(description = "操作状态  0：失败   1：成功")
    @TableField("status")
    private int status;

    @Schema(description = "用户ID")
    @TableField("user_id")
    private Long userId;

    @Schema(description = "操作人")
    @TableField("real_name")
    private String realName;

    @Schema(description = "返回消息")
    @TableField("json_result")
    private String jsonResult;

    @Schema(description = "返回消息")
    @TableField("error_msg")
    private String errorMsg;

    @TableField(fill = FieldFill.INSERT)
    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "操作系统")
    @TableField("os")
    private String os;

    @Schema(description = "浏览器")
    @TableField("browser")
    private String browser;

}
