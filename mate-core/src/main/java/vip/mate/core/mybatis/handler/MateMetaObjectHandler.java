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
        this.strictInsertFill(metaObject, CREATE_TIME, Date.class, new Date());
        strictInsertFill(metaObject, CREATE_BY, Long.class, StpUtil.getLoginIdAsLong());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, UPDATE_TIME, Date.class, new Date());
        strictInsertFill(metaObject, UPDATE_BY, Long.class, StpUtil.getLoginIdAsLong());
    }
}
