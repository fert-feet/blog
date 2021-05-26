package com.ling.common;

public enum ResultInfo implements IResult {
    SUCCESS(200,"操作成功"),
    ERROR(400,"操作失败"),
    NOT_FOUND(404,"没有找到"),
    UPDATE_ERROR(678,"更新失败"),
    UPDATE_SUCCESS(679,"修改成功"),
    NO_DATA_FOUND(999,"没有找到相关内容"),
    LOGIN_SUCCESS(123,"登录成功"),
    LOGIN_FAILED(1232,"用户名或者密码错误"),
    VERIFY_SUCCESS(756,"登录验证成功"),
    VERIFY_FAILED(885,"登录验证失败"),
    ;
    private  Integer code;
    private  String message;

    ResultInfo(Integer code,String message){
        this.code=code;
        this.message=message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
