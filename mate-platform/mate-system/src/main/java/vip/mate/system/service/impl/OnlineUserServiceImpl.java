package vip.mate.system.service.impl;

import cn.dev33.satoken.dao.SaTokenDao;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vip.mate.core.mybatis.res.PageRes;
import vip.mate.core.satoken.entity.LoginUser;
import vip.mate.core.satoken.utils.UserInfoHelper;
import vip.mate.system.req.OnlineUserReq;
import vip.mate.system.service.OnlineUserService;
import vip.mate.system.vo.OnlineUserVO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * 在线用户服务实现
 *
 * @author matevip
 * @since 2023/9/26
 */
@Service
@RequiredArgsConstructor
public class OnlineUserServiceImpl implements OnlineUserService {
    @Override
    public PageRes<OnlineUserVO> page(OnlineUserReq req) {
        List<LoginUser> loginUserList = this.list(req);
        List<OnlineUserVO> list = BeanUtil.copyToList(loginUserList, OnlineUserVO.class);
        PageRes<OnlineUserVO> pageRes = new PageRes(list, (long) list.size(), req);
        return pageRes;
    }

    @Override
    public List<LoginUser> list(OnlineUserReq req) {
        List<LoginUser> loginUserList = new ArrayList<>();
        List<String> tokenKeyList = StpUtil.searchTokenValue("", 0, -1, false);
        for (String tokenKey : tokenKeyList) {
            String token = StrUtil.subAfter(tokenKey, ":", true);
            // 忽略已过期或失效 Token
            if (StpUtil.stpLogic.getTokenActiveTimeoutByToken(token) < SaTokenDao.NEVER_EXPIRE) {
                continue;
            }
            LoginUser loginUser = UserInfoHelper.getLoginUser(token);
            if (this.CheckReq(req, loginUser)) {
                loginUserList.add(loginUser);
            }
        }
        // 设置排序
        CollUtil.sort(loginUserList, Comparator.comparing(LoginUser::getLoginTime).reversed());
        return loginUserList;
    }

    private boolean CheckReq(OnlineUserReq req, LoginUser loginUser) {
        boolean flag1 = true;
        String realName = req.getRealName();
        if (StrUtil.isNotBlank(realName)) {
            flag1 = StrUtil.contains(loginUser.getUsername(), realName)
                    || StrUtil.contains(UserInfoHelper.getRealName(), realName);
        }

        boolean flag2 = true;
        List<Date> loginTime = req.getLoginTime();
        if (CollUtil.isNotEmpty(loginTime)) {
            flag2 =
                    DateUtil.isIn(DateUtil.date(loginUser.getLoginTime()).toJdkDate(), loginTime.get(0), loginTime.get(1));
        }
        return flag1 && flag2;
    }
}
