package com.example.springdemo.demo_springboot2020706.Controller;

import com.alibaba.fastjson.JSON;
import com.example.springdemo.demo_springboot2020706.biz.UserBiz;
import com.example.springdemo.demo_springboot2020706.pojo.Uset;
import com.example.springdemo.demo_springboot2020706.utilel.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Resource
    UserBiz biz;
    @Resource
    private RedisService redisService;
    @GetMapping("/login")
    public Map<String,Object>   getLogin(HttpServletRequest request){
        System.out.println(getIpAddress(request));
         Map<String,Object>   resultMap  = new HashMap<>();
        String data =  JSON.toJSONString(biz.userALL(1));
        resultMap.put("code",200);
        resultMap.put("resutl",data);
        return  resultMap;
    }

    private String getIpAddress(HttpServletRequest request) { 
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknow".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length () == 0 || "unknown".equalsIgnoreCase (ip)) {
            ip = request.getHeader ("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length () == 0 || "unknown".equalsIgnoreCase (ip)) {
            ip = request.getRemoteAddr ();
            if (ip.equals ("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost ();
                } catch (Exception e) {
                    e.printStackTrace ();
                }
                ip = inet.getHostAddress ();
            }
        }
        // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length () > 15) {
            if (ip.indexOf (",") > 0) {
                ip = ip.substring (0, ip.indexOf (","));
            }
        }
        return ip;
    }

}
