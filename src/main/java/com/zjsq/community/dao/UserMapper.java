package com.zjsq.community.dao;

import com.zjsq.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

//@Repository//spring用这个注解来标识bean
@Mapper//mybatis用这个注解来标识bean
public interface UserMapper {

    //根据用户id查user
    User selectById(int id);

    //根据用户名查user
    User selectByName(String username);

    //根据邮箱查user
    User selectByEmail(String email);

    //新增一个用户
    int insertUser(User user);

    //修改用户状态
    int updateStatus(int id, int status);

    //更新头像
    int updateHeader(int id, String headerUrl);

    //修改密码
    int updatePassword(int id, String password);
}
