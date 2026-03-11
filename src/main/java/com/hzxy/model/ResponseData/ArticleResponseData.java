//package com.hzxy.model.ResponseData;
//
//import java.util.Stack;
//
///**
// * 前端请求响应的封装类
// */
//public class ArticleResponseData<T> {
//
//    //private T payload;//响应的数据
//    private boolean success;//请求是否成功 true/false
//    //private String msg;//错误信息
//    //private int code;//状态码 404/500
//    //private long timestamp;//响应时间
//
//    //无参构造
//    public ArticleResponseData() {
//    }
//
//    //添加一个代success的构造方法
//    public ArticleResponseData(boolean success) {
//        this.success = success;
//    }
//
//    //添加get和set方法
//
//    public boolean isSuccess() {
//        return success;
//    }
//
//    public void setSuccess(boolean success) {
//        this.success = success;
//    }
//
//    //静态方法
//    //响应成功的方法
//    public static ArticleResponseData  ok(){
//
//        return new ArticleResponseData(true);
//    }
//    //响应失败的方法
//    public static ArticleResponseData fail(){
//
//        return new ArticleResponseData(false);
//    }
//}



package com.hzxy.model.ResponseData;

/**
 * 前端请求响应的封装类
 */
public class ArticleResponseData<T> {

    private boolean success; // 请求是否成功 true/false
    private String msg;      // 提示信息（成功/失败描述）
    private int code;        // 状态码（200成功/500失败/400参数错误）
    private T data;          // 响应的附加数据（可选，比如返回文章ID）
    private long timestamp;  // 响应时间戳

    // 无参构造
    public ArticleResponseData() {
        this.timestamp = System.currentTimeMillis(); // 默认赋值时间戳
    }

    // 仅含success的构造方法
    public ArticleResponseData(boolean success) {
        this(); // 调用无参构造初始化时间戳
        this.success = success;
    }

    // 含success+msg的构造方法（最常用）
    public ArticleResponseData(boolean success, String msg) {
        this(success);
        this.msg = msg;
    }

    // 全参构造（适配更多场景）
    public ArticleResponseData(boolean success, String msg, int code, T data) {
        this(success, msg);
        this.code = code;
        this.data = data;
    }

    public static ArticleResponseData fail() {
        return new ArticleResponseData(false);
    }

    // Getter & Setter 方法
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    // 静态工具方法（简化响应创建）
    // 成功响应（无附加数据）
    public static ArticleResponseData ok() {
        return new ArticleResponseData(true, "操作成功", 200, null);
    }

    // 成功响应（带提示信息）
    public static ArticleResponseData ok(String msg) {
        return new ArticleResponseData(true, msg, 200, null);
    }

    // 成功响应（带提示+附加数据）
    public static <T> ArticleResponseData<T> ok(String msg, T data) {
        return new ArticleResponseData<>(true, msg, 200, data);
    }

    // 失败响应（带提示信息）
    public static ArticleResponseData fail(String msg) {
        return new ArticleResponseData(false, msg, 500, null);
    }

    // 失败响应（带提示+状态码）
    public static ArticleResponseData fail(String msg, int code) {
        return new ArticleResponseData(false, msg, code, null);
    }
}
