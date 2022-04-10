package com.hx.base.filter;

import com.hx.base.exception.NoAuthenticationException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @author hexian
 * @date 2022/4/10 14:54
 */
@Slf4j
@Component
public class LogInInterceptor implements HandlerInterceptor {

    /**
     * token
     */
    static String TOKEN = "token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(TOKEN);
        log.info("当前上下文中的token为:{}", token);
        if (StringUtils.isBlank(token)) {
            throw new NoAuthenticationException("无效身份凭证");
        }
//        String requestId = UUID.randomUUID().toString();
//        ContextIdentityInfoDTO contextIdentityInfoDTO = salonThirdPartyService.checkToken(tenwitToken,requestId);
//         通过token获取对应上下文信息
//        if (null == contextIdentityInfoDTO) {
//            throw new SessionTimeoutException("请重新登录");
//        }
//        GlobeContext.setRequestId(requestId);
//        GlobeContext.setCorpId(contextIdentityInfoDTO.getCorpId());
//        GlobeContext.setStaffId(contextIdentityInfoDTO.getStaffId());
//        GlobeContext.setUnionId(contextIdentityInfoDTO.getUnionId());
//        GlobeContext.setVisitorId(contextIdentityInfoDTO.getVisitorId());
//        GlobeContext.setUserId(contextIdentityInfoDTO.getUserId());
//        GlobeContext.setSharedStaffId(contextIdentityInfoDTO.getSharedStaffId());
//        GlobeContext.setOpenId(contextIdentityInfoDTO.getOpenId());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
