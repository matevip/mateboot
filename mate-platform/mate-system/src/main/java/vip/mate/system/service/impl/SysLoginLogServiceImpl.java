package vip.mate.system.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import net.dreamlu.mica.core.utils.JsonUtil;
import vip.mate.core.common.utils.IpUtils;
import vip.mate.core.common.utils.ServletUtils;
import vip.mate.core.satoken.utils.UserInfoHelper;
import vip.mate.system.entity.SysLoginLog;
import vip.mate.system.mapper.SysLoginLogMapper;
import vip.mate.system.service.SysLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import vip.mate.system.vo.SysLoginLogVO;
import vip.mate.system.req.SysLoginLogReq;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.springframework.stereotype.Service;

import java.util.*;

import cn.hutool.core.collection.CollectionUtil;
import vip.mate.system.convert.SysLoginLogConvert;
import vip.mate.core.mybatis.res.PageRes;


/**
 * <p>
 * 登录日志 服务实现类
 * </p>
 *
 * @author matevip
 * @since 2023-09-27
 */
@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements SysLoginLogService {

    @Override
    public PageRes<SysLoginLogVO> queryPage(SysLoginLogReq req) {
        // 查询条件
        LambdaQueryWrapper<SysLoginLog> queryWrapper =
                Wrappers.<SysLoginLog>query().lambda().like(StrUtil.isNotBlank(req.getRealName()), SysLoginLog::getRealName, req.getRealName());
        // 查询时间范围
        if (req.getCreateTime() !=null && req.getCreateTime().size() > 0) {
            queryWrapper
                    .ge(SysLoginLog::getCreateTime, req.getCreateTime().get(0))
                    .le(SysLoginLog::getCreateTime, req.getCreateTime().get(1));
        }
        Page<SysLoginLog> pageData = baseMapper.selectPage(new Page<>(req.getPageNo(), req.getPageSize()), queryWrapper);
        if (CollectionUtil.isEmpty(pageData.getRecords())) {
            return PageRes.empty();
        }
        List<SysLoginLogVO> vos = SysLoginLogConvert.INSTANCE.toVo(pageData.getRecords());
        return new PageRes<>(vos, pageData.getTotal(), req);
    }

    @Override
    public void saveLog(String username, int status, int businessType) {
        HttpServletRequest request = ServletUtils.getRequest();
        SysLoginLog loginLog = new SysLoginLog();
        loginLog.setUsername(username);
        loginLog.setStatus(status);
        loginLog.setBusinessType(businessType);
        loginLog.setIp(IpUtils.getIpAddr(request));
        loginLog.setAddress(IpUtils.getCityInfo(loginLog.getIp()));
        loginLog.setBrowser(ServletUtils.getBrowser(request));
        loginLog.setOs(ServletUtils.getOs(request));

        if (StpUtil.isLogin() && StrUtil.isNotBlank(UserInfoHelper.getRealName())) {
            loginLog.setRealName(UserInfoHelper.getRealName());
        }

        baseMapper.insert(loginLog);
    }

}

