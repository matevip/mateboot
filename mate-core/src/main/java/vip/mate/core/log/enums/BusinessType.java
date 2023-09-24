package vip.mate.core.log.enums;


/**
 * 业务操作类型
 *
 * @author matevip
 * @date 2023/9/22
 * @webchat MateCloud
 * @email 7333791@qq.com
 * @copyright <a href="https://mate.vip">MateBoot</a>
 */
public enum BusinessType {
    /**
     * 其它
     */
    OTHER,

    /**
     * 新增
     */
    INSERT,

    /**
     * 修改
     */
    UPDATE,

    /**
     * 删除
     */
    DELETE,

    /**
     * 授权
     */
    GRANT,

    /**
     * 导出
     */
    EXPORT,

    /**
     * 导入
     */
    IMPORT,

    /**
     * 强退
     */
    FORCE,

    /**
     * 生成代码
     */
    GENCODE,

    /**
     * 清空数据
     */
    CLEAN,

    /**
     * 上传
     */
    UPLOAD,

    /**
     * 下载
     */
    DOWNLOAD,

    /**
     * 查询
     */
    QUERY,

    /**
     * remote op
     */
    REMOTE_OPERATION,
}
