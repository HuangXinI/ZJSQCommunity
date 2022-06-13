package com.zjsq.community.service;

import com.zjsq.community.dao.AlphaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
//@Scope("prototype")//加这个注解，每访问一次bean就实例化一次，而不是默认的单例了(但通常是单例)
public class AlphaService {
    @Autowired
    private AlphaDao alphaDao;

    public AlphaService(){
        System.out.println("实例化AlphaService");
    }

    @PostConstruct//加这个注解会让容器管理这个bean的初始化(在构造器之后调用的)
    public void init(){
        System.out.println("初始化AlphaService");
    }

    @PreDestroy//加这个注解可以在实例销毁(对象销毁)之前调用，一般用来释放资源
    public void destroy(){
        System.out.println("销毁AlphaService");
    }

    public String find(){
        return alphaDao.select();
    }
}
