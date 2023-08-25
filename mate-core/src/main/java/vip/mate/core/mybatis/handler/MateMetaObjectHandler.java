package vip.mate.core.mybatis.handler;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Mybatis-Plus 自动填充字段
 *
 * @author matevip
 * @since 2023/8/13
 */
public class MateMetaObjectHandler implements MetaObjectHandler {
    private final static String CREATE_TIME = "createTime";
    private final static String CREATE_BY = "createBy";
    private final static String UPDATE_TIME = "updateTime";
    private final static String UPDATE_BY = "updateBy";
    private final static String ORG_ID = "orgId";
    private final static String VERSION = "version";
    private final static String DELETED = "deleted";

    @Override
    public void insertFill(MetaObject metaObject) {
        // 创建时间
        strictInsertFill(metaObject, CREATE_TIME, Date.class, new Date());
        // 创建者
        strictInsertFill(metaObject, CREATE_BY, Long.class, StpUtil.getLoginIdAsLong());
        // 版本号
        strictInsertFill(metaObject, VERSION, Integer.class, 0);
        // 删除标识
        strictInsertFill(metaObject, DELETED, Integer.class, 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间
        strictUpdateFill(metaObject, UPDATE_TIME, Date.class, new Date());
        // 更新者
        strictInsertFill(metaObject, UPDATE_BY, Long.class, StpUtil.getLoginIdAsLong());
    }
}
