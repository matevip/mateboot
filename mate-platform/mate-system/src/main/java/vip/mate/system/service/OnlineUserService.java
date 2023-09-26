package vip.mate.system.service;

import vip.mate.core.mybatis.res.PageRes;
import vip.mate.core.satoken.entity.LoginUser;
import vip.mate.system.req.OnlineUserReq;
import vip.mate.system.vo.OnlineUserVO;

import java.util.List;

/**
 * 在线用户服务
 *
 * @author matevip
 * @since 2023/9/26
 */
public interface OnlineUserService {

    PageRes<OnlineUserVO> page(OnlineUserReq req);


    List<LoginUser> list(OnlineUserReq req);


}
