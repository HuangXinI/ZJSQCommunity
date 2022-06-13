package com.zjsq.community.util;

import com.zjsq.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * 持有用户信息，用于代替session对象实现线程隔离
 */
@Component
public class HostHolder {

    private ThreadLocal<User> users = new ThreadLocal<>();

    public void setUser(User user){
        users.set(user);//存放到[当前线程]唯一对应的map中
    }

    public User getUser(){
        return users.get();//是从【当前线程】中取
    }

    public void clear(){
        users.remove();
    }
}
