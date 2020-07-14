package com.example.springdemo.demo_springboot2020706.biz;

import com.example.springdemo.demo_springboot2020706.dao.UsertImp;
import com.example.springdemo.demo_springboot2020706.pojo.Uset;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@CacheConfig(cacheNames = "user")//注意,用于同一配置给其它注解配置名称
@Service
public class UserBiz {

    @Resource
    UsertImp dao;

    @Autowired
    RedisTemplate redisTemplate;


    public Uset loginBypassword(String acction, String password){

       return  dao.getLogin(acction,password);
    }

    @Cacheable(key="#id+'_'+#num+'_'+#size")//该注解用于向缓存中存入数据   unless = "#info==null"
    public PageInfo<Uset>  userALL(Integer id,Integer num,Integer size){
        PageHelper.startPage(num, size);
        PageInfo<Uset> info =   new PageInfo<Uset>(dao.getUserALL());
        redisTemplate.opsForValue().set("001","河北省");
        return info;
    }

}
