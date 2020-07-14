package com.example.springdemo.demo_springboot2020706.biz;

import com.example.springdemo.demo_springboot2020706.dao.UsertImp;
import com.example.springdemo.demo_springboot2020706.pojo.Uset;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@CacheConfig(cacheNames = "user")//注意,用于同一配置给其它注解配置名称
@Service
public class UserBiz {

    @Resource
    UsertImp dao;


    public Uset loginBypassword(String acction, String password){

       return  dao.getLogin(acction,password);
    }

     @Cacheable(key="#id")//该注解用于向缓存中存入数据
    public PageInfo<Uset>  userALL(Integer id){
        PageHelper.startPage(1, 5);
        return new PageInfo<Uset>(dao.getUserALL());
    }
}
