package com.zjsq.community.controller;

import com.zjsq.community.entity.DiscussPost;
import com.zjsq.community.entity.Page;
import com.zjsq.community.entity.User;
import com.zjsq.community.service.DiscussPostService;
import com.zjsq.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
//@RequestMapping("/HomeController")
public class HomeController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page){
        page.setRows(discussPostService.findDiscussPostRows(0));
        page.setPath("/index");//页面可以复用这个路径
        //获取前10条帖子
        List<DiscussPost> list = discussPostService.findDiscussPost(0, page.getOffset(), page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        //遍历list针对每一个DiscussPost对象中的userId找到对应的username
        // 组装
        if(list != null){
            for(DiscussPost post : list){
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.findUserById(post.getUserId());
                map.put("user", user);

                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts", discussPosts);
        //model.addAttribute("page", page);这一步可以省略,page作为参数,mvc框架已经实例化page并注入model中了
        return "/index";//最终返回模板的路径
    }
}
