package com.ling.handler.auth;/*
 * @ Created with IntelliJ IDEA
 * @ Author Ky
 * @ Date 2021/4/17
 * @ Time 12:58
 */

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;


public class MyaccessDeniedException extends AccessDeniedException {

    /**
     * 自定义无权限信息
     * @param msg
     */

    public MyaccessDeniedException(String msg) {
        super(msg);
    }
}
