package com.zjsq.community.controller;

import com.zjsq.community.service.AlphaService;
import com.zjsq.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

//controller可以看作前后端交互的部分
@Controller
@RequestMapping("/alpha")//取一个访问名,服务器通过这个名字来访问此类(申明路径)
public class AlphaController {
    @Autowired
    private AlphaService alphaService;

    @RequestMapping("/hello")//服务器通过这个名字来访问这个方法
    @ResponseBody
    public String sayHello() {
        return "Hello Spring Boot";
    }

    @RequestMapping("/getData")//服务器通过这个名字来访问这个方法
    @ResponseBody
    public String getData() {
        return alphaService.find();
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //底层:
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter writer = response.getWriter();
            writer.write("<h1>牛客网<h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //mvc框架:
    //GET请求(默认)
    // /student?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)//指定清楚要处理什么样的请求
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,//这个注解：这个变量由前端以参数形式传入@Re,同时对传入参数做更详尽的声明
            @RequestParam(name = "limit", required = false, defaultValue = "1")int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }

    // /student/123    //当参数成为路径的一部分而不是以参数的方式传入时
    @RequestMapping(path = "/students/{id}", method = RequestMethod.GET)//指定清楚要处理什么样的请求
    @ResponseBody
    public String getStudent(@PathVariable("id") int id){//这个注解:参数是路径的一部分,自动识别获取
        System.out.println(id);
        return"a student";
    }

    //POST请求
    //POST请求和GET请求都可以处理前端数据但GET请求的数据在url中可以看见，不保密
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){//处理post请求时需要获取前端数据，只需要把参数的名字设置为和前端一样即可
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    //响应HTML数据
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    //默认返回html不加responsebody注解
    //给Servlet返回model和view的信息
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "张三");
        mav.addObject("age", 30);
        mav.setViewName("/demo/view");//mav信息存放在这个路径下的名为view的模板中
        return mav;
    }

    //上面例子的简化(推荐)
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model){
        //然后再往model中装数据
        model.addAttribute("name", "北京大学");
        model.addAttribute("age", 80);
        return "/demo/view";
    }

    //响应json数据(异步请求:网页不刷新，但访问了服务器,说明服务器返回的不是html页面而是其他数据)
    //Java对象 ——> JSON字符串 ---> JS对象
    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getEmp(){
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        return emp;//会自动转为json格式的数据
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmps(){
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "张三");
        emp.put("age", 23);
        emp.put("salary", 8000.00);
        list.add(emp);
        return list;//会自动转为json格式的数据
    }

    //cookie示例
    @RequestMapping(path = "/cookie/set", method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response){
        //创建cookie
        Cookie cookie = new Cookie("code", CommunityUtil.generateUUID());
        //指定Cookie的生效范围
        cookie.setPath("/community/alpha");
        //持久化cookie
        cookie.setMaxAge(60 * 10);
        //放到response的头部
        response.addCookie(cookie);

        return "set cookie";
    }

    //cookie示例
    @RequestMapping(path = "/cookie/get", method = RequestMethod.GET)
    @ResponseBody
    public String getCookie(@CookieValue("code") String code){//加上此注解,表示需要传入key为code的cookie
        System.out.println(code);
        return "get cookie";
    }

    //session示例
    @RequestMapping(path = "/session/set", method = RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session){//只要声明mvc就会自动注入
        session.setAttribute("id", 1);
        session.setAttribute("name", "test");
        return "set session";
    }

    @RequestMapping(path = "/session/get", method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session){
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "get session";
    }
}
