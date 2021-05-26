package com.ling.common;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class Result implements Serializable {

    @ApiModelProperty(value = "是否操作成功")
    private  boolean success;
    @ApiModelProperty(value = "操作码")
    private  Integer code;
    @ApiModelProperty(value = "操作信息")
    private  String message;
    @ApiModelProperty(value = "操作数据")
    private Map<String,Object> data=new HashMap<>();

    private  Result(){}

    public  static Result ok(){
        Result result=new Result();
        result.setSuccess(true);
        result.setCode(ResultInfo.SUCCESS.getCode());
        result.setMessage(ResultInfo.SUCCESS.getMessage());
        return  result;
    }
    public  static Result error(){
        Result result=new Result();
        result.setSuccess(false);
        result.setCode(ResultInfo.ERROR.getCode());
        result.setMessage(ResultInfo.ERROR.getMessage());
        return  result;
    }
    public Result success(Boolean success){
        this.setSuccess(success);
        return  this;

    }

    public Result code(Integer code){
        this.setCode(code);
        return  this;
    }

    public Result message(String message){
        this.setMessage(message);
        return  this;
    }

    public Result data(String key,Object data){
        this.data.put(key, data);
        return  this;
    }
}