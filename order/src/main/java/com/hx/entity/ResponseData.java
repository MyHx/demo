package com.hx.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


/**
 * 通用响应类
 *
 * @author hexian
 */
@Data
@ToString
public class ResponseData implements Serializable {

    public final static int INT_RET_SUCCESS = 0;
    public final static int INT_RET_FAILED = -1;
    public final static int INT_RET_BAD_REQUEST = 400;
    public final static int INT_RET_NOTFOUND = 404;
    public final static String STR_RET_MSG_SUCCESS = "ok";
    public final static String STR_RET_MSG_FAILED = "failed";
    public final static String STR_RET_MSG_NOTFOUND = "resource not found";
    public final static String STR_RET_MSG_BAD_REQUEST = "bad request";

    private int ret;

    private String retmsg;

    private Object retdata;

    public ResponseData() {
        this.ret = INT_RET_SUCCESS;
        this.retmsg = STR_RET_MSG_SUCCESS;
    }

    public ResponseData(String msg) {
        this.ret = INT_RET_FAILED;
        this.retmsg = msg;
    }

    public ResponseData(Object data) {
        this.ret = INT_RET_SUCCESS;
        this.retmsg = STR_RET_MSG_SUCCESS;
        this.retdata = data;
    }

    public boolean isSuccess() {
        return this.ret == INT_RET_SUCCESS;
    }

    public static ResponseData buildFail() {
        ResponseData res = new ResponseData();
        res.ret = INT_RET_FAILED;
        res.retmsg = STR_RET_MSG_FAILED;
        return res;
    }

    public static ResponseData buildFail(String msg) {
        ResponseData res = new ResponseData();
        res.ret = INT_RET_FAILED;
        res.retmsg = msg;
        return res;
    }

    public static ResponseData buildSuccess() {
        ResponseData res = new ResponseData();
        res.ret = INT_RET_SUCCESS;
        res.retmsg = STR_RET_MSG_SUCCESS;
        return res;
    }

    public static ResponseData buildSuccess(Object data) {
        ResponseData res = new ResponseData();
        res.ret = INT_RET_SUCCESS;
        res.retmsg = STR_RET_MSG_SUCCESS;
        res.retdata = data;
        return res;
    }

    public static ResponseData buildNotFound() {
        return buildNotFound(STR_RET_MSG_NOTFOUND);
    }

    public static ResponseData buildNotFound(String msg) {
        ResponseData res = new ResponseData();
        res.ret = INT_RET_NOTFOUND;
        res.retmsg = msg;
        return res;
    }

    public static ResponseData buildBadRequest() {
        return buildBadRequest(STR_RET_MSG_BAD_REQUEST);
    }

    public static ResponseData buildBadRequest(String msg) {
        ResponseData res = new ResponseData();
        res.ret = INT_RET_BAD_REQUEST;
        res.retmsg = msg;
        return res;
    }

    public ResponseData(int ret, String retmsg) {
        this.ret = ret;
        this.retmsg = retmsg;
    }
}
