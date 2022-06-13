package com.zjsq.community.dao;

import com.zjsq.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    //userId不传入时查询所有帖子，传入时查询对应用户的帖子
    //支持分页
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    //查询表中一共多少条数据
    int selectDiscussPostRows(@Param("userId") int userId);//只有一个参数,并且在<if>里使用，必须加注解(起别名)

}
