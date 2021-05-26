package com.ling.handler.exception;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/5
 * @ Time 16:51
 */


import com.ling.common.ResultInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyRuntimeException extends RuntimeException {
    @ApiModelProperty(value = "错误码")
    private  Integer code;
    @ApiModelProperty(value = "错误信息")
    private  String errMsg;

    public MyRuntimeException(String errMsg){
        this.errMsg=errMsg;
    }
}
