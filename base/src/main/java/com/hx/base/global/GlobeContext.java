package com.hx.base.global;

/**
 * @author Administrator
 */
public class GlobeContext {

    private static final ThreadLocal<Context> threadLocal = new ThreadLocal<>();


    public static void setSharedStaffId(String sharedStaffId) {
        Context ctx = threadLocal.get();
        if (ctx == null) {
            ctx = new Context();
        }
        ctx.setSharedStaffId(sharedStaffId);
        threadLocal.set(ctx);
    }

    public static String getSharedStaffId() {
        Context ctx = threadLocal.get();
        if (ctx == null) {
            return null;
        }
        return ctx.getSharedStaffId();
    }

    public static void setRequestId(String requestId) {
        Context ctx = threadLocal.get();
        if (ctx == null) {
            ctx = new Context();
        }
        ctx.setRequestId(requestId);
        threadLocal.set(ctx);
    }

    public static String getRequestId() {
        Context ctx = threadLocal.get();
        if (ctx == null) {
            return null;
        }
        return ctx.getRequestId();
    }

    //    public static String getSessionKey() {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            return null;
//        }
//        return ctx.getSessionKey();
//    }
//
    public static String getStaffId() {
        Context ctx = threadLocal.get();
        if (ctx == null) {
            return null;
        }
        return ctx.getStaffId();
    }

    public static String getUnionId() {
        Context ctx = threadLocal.get();
        if (ctx == null) {
            return null;
        }
        return ctx.getUnionId();
    }

    //
//    public static String getIP() {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            return null;
//        }
//        return ctx.getIP();
//    }
//
//    public static int getPlatform() {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            return 0;
//        }
//        return ctx.getPlatform();
//    }
//
//    public static void setSessionKey(String SessionKey) {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            ctx = new Context();
//        }
//        ctx.setSessionKey(SessionKey);
//        threadLocal.set(ctx);
//    }
//
    public static void setUserId(String userId) {
        Context ctx = threadLocal.get();
        if (ctx == null) {
            ctx = new Context();
        }
        ctx.setUserId(userId);
        threadLocal.set(ctx);
    }

    public static void setStaffId(String staffId) {
        Context ctx = threadLocal.get();
        if (ctx == null) {
            ctx = new Context();
        }
        ctx.setStaffId(staffId);
        threadLocal.set(ctx);
    }

    public static void setUnionId(String unionId) {
        Context ctx = threadLocal.get();
        if (ctx == null) {
            ctx = new Context();
        }
        ctx.setUnionId(unionId);
        threadLocal.set(ctx);
    }

    public static void setOpenId(String openId) {
        Context ctx = threadLocal.get();
        if (ctx == null) {
            ctx = new Context();
        }
        ctx.setOpenId(openId);
        threadLocal.set(ctx);
    }

    public static String getOpenId() {
        Context ctx = threadLocal.get();
        if (ctx == null) {
            return null;
        }
        return ctx.getOpenId();
    }

    public static String getUserId() {
        Context ctx = threadLocal.get();
        if (ctx == null) {
            return null;
        }
        return ctx.getUserId();
    }

    //    public static void setStaffVisitSource(Integer staffVisitSource) {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            ctx = new Context();
//        }
//        ctx.setStaffVisitSource(staffVisitSource);
//        threadLocal.set(ctx);
//    }
//
//    public static Integer getStaffVisitSource() {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            return null;
//        }
//        return ctx.getStaffVisitSource();
//    }
//
//    public static String getWechatKey() {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            return null;
//        }
//        return ctx.getWechatKey();
//    }
//
//    public static void setWechatKey(String wechatKey) {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            ctx = new Context();
//        }
//        ctx.setWechatKey(wechatKey);
//        threadLocal.set(ctx);
//    }
//
//    public static Integer getVerifyStatus() {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            return null;
//        }
//        return ctx.getVerifyStatus();
//    }
//
//    public static void setVerifyStatus(Integer verifyStatus) {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            ctx = new Context();
//        }
//        ctx.setVerifyStatus(verifyStatus);
//        threadLocal.set(ctx);
//    }
//
    public static String getCorpId() {
        Context ctx = threadLocal.get();
        if (ctx == null) {
            return null;
        }
        return ctx.getCorpId();
    }

    public static void setCorpId(String corpId) {
        Context ctx = threadLocal.get();
        if (ctx == null) {
            ctx = new Context();
        }
        ctx.setCorpId(corpId);
        threadLocal.set(ctx);
    }

    //
//
//    public static void setIP(String IP) {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            ctx = new Context();
//        }
//        ctx.setIP(IP);
//        threadLocal.set(ctx);
//    }
//
//    public static void setPlatform(Integer platform) {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            ctx = new Context();
//        }
//        ctx.setPlatform(platform);
//        threadLocal.set(ctx);
//    }
//
//    public static void setLastTouchStaffId(String staffId) {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            ctx = new Context();
//        }
//        ctx.setLastTouchStaffId(staffId);
//        threadLocal.set(ctx);
//    }
//
//
//    public static String getLastTouchStaffId() {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            ctx = new Context();
//        }
//        return ctx.getLastTouchStaffId();
//    }
//
//    public static void setAppId(String appId) {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            ctx = new Context();
//        }
//        ctx.setAppId(appId);
//        threadLocal.set(ctx);
//    }
//
//    public static String getAppId() {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            return null;
//        }
//        return ctx.getAppId();
//    }
//
//    public static void setUserType(Integer userType) {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            ctx = new Context();
//        }
//        ctx.setUserType(userType);
//        threadLocal.set(ctx);
//    }
//
//    public static Integer getUserType() {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            return null;
//        }
//        return ctx.getUserType();
//    }
//
//    public static void setVisitUserId(String visitUserId) {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            ctx = new Context();
//        }
//        ctx.setVisitUserId(visitUserId);
//        threadLocal.set(ctx);
//    }
//
//    public static String getVisitUserId() {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            return null;
//        }
//        return ctx.getVisitUserId();
//    }
//
//    public static void setFromAppId(String fromAppId) {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            ctx = new Context();
//        }
//        ctx.setFromAppId(fromAppId);
//        threadLocal.set(ctx);
//    }
//
//    public static String getFromAppId() {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            return null;
//        }
//        return ctx.getFromAppId();
//    }
//
//
//    public static Context getContext() {
//        return threadLocal.get();
//    }
//
//    public static void setLoginFrom(String loginFrom) {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            ctx = new Context();
//        }
//        ctx.setLoginFrom(loginFrom);
//        threadLocal.set(ctx);
//    }
//
//    public static String getLoginFrom() {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            return null;
//        }
//        return ctx.getLoginFrom();
//    }
//
//    public static String getCustomerId() {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            return null;
//        }
//        return ctx.getCustomerId();
//    }
//
//    public static void setCustomerId(String customerId) {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            ctx = new Context();
//        }
//        ctx.setCustomerId(customerId);
//        threadLocal.set(ctx);
//    }
//
//    public static String getApplication() {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            return null;
//        }
//        return ctx.getApplication();
//    }
//
//    public static void setApplication(String application) {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            ctx = new Context();
//        }
//        ctx.setApplication(application);
//        threadLocal.set(ctx);
//    }
//
//    public static Integer getSameCorp() {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            return null;
//        }
//        return ctx.getSameCorp();
//    }
//
//    public static void setSameCorp(Integer sameCorp) {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            ctx = new Context();
//        }
//        ctx.setSameCorp(sameCorp);
//        threadLocal.set(ctx);
//    }
//
    public static Long getVisitorId() {
        Context ctx = threadLocal.get();
        if (ctx == null) {
            return null;
        }
        return ctx.getVisitorId();
    }

    public static void setVisitorId(Long visitorId) {
        Context ctx = threadLocal.get();
        if (ctx == null) {
            ctx = new Context();
        }
        ctx.setVisitorId(visitorId);
        threadLocal.set(ctx);
    }
//
//    public static Integer getSource() {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            return null;
//        }
//        return ctx.getSouce();
//    }
//
//    public static void setSource(Integer source) {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            ctx = new Context();
//        }
//        ctx.setSouce(source);
//        threadLocal.set(ctx);
//    }
//
//    public static String getExternalUserId() {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            return null;
//        }
//        return ctx.getExternalUserId();
//    }
//
//    public static void setExternalUserId(String externalUserId) {
//        Context ctx = threadLocal.get();
//        if (ctx == null) {
//            ctx = new Context();
//        }
//        ctx.setExternalUserId(externalUserId);
//        threadLocal.set(ctx);
//    }

    public static void removeThreadLocal() {
        threadLocal.remove();
    }
}
