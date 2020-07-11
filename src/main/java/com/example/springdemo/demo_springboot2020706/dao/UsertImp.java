package com.example.springdemo.demo_springboot2020706.dao;

import com.example.springdemo.demo_springboot2020706.pojo.Uset;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UsertImp {
    public   Uset  getLogin(@Param("acction") String acction,@Param("password") String password);
    public List<Uset>  getUserALL();
}
