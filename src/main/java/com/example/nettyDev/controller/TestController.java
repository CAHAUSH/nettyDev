package com.example.nettyDev.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class TestController {

    @RequestMapping("flowengine/testsession")
    public String test(HttpServletRequest request, HttpServletResponse response){

        String sessionid = "";
        Cookie cookie = new Cookie("flow-engine-sessionid","test46451315-flowengine");
        response.addCookie(cookie);
        return "success";
    }

    @RequestMapping("flowengine/request")
    public String request(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cookies = request.getCookies();
        String sessionid = "";
        return "ol";
    }
}
