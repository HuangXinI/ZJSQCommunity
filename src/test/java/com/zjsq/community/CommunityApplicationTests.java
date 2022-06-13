package com.zjsq.community;

import com.zjsq.community.dao.AlphaDao;
import com.zjsq.community.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest//测试
@ContextConfiguration(classes = CommunityApplication.class)//设置，以这个类为配置类
class CommunityApplicationTests implements ApplicationContextAware {//实现这个接口，容器会检测到，可以获取SpringIOC容器
	/**
	 * 常规的方式使用Ioc容器
	 */
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		//捕获一下springIOC容器
		this.applicationContext = applicationContext;
	}

	@Test
	public void testApplicationContext(){
		System.out.println(applicationContext);

		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);//从容器中获取这个类型的bean,类型是一个接口,接口有多个bean的在希望获取的bean中加注解
		//从而降低bean之间的耦合度
		System.out.println(alphaDao.select());//成功获取bean并得到方法结果

		alphaDao = applicationContext.getBean("alphaHibernate", AlphaDao.class);
		System.out.println(alphaDao.select());
	}

	@Test
	public void testApplicationManager(){
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);//底层实例化和初始化了这个类【并且只实例化一次(默认单例)】
		System.out.println(alphaService);
	}

	@Test
	public void testBeanConfig(){
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	/**
	 * 【依赖注入】的方式使用Ioc容器：写一个属性加一个注释
	 */
	@Autowired//直接注入方式，把AlphaDao的bean直接加进到当前
	@Qualifier("alphaHibernate")//某个接口有多个bean，加这个注解指定要加的bean
	private AlphaDao alphaDao;

	@Autowired//直接注入方式
	private AlphaService alphaService;

	@Autowired
	private SimpleDateFormat simpleDateFormat;

	@Test
	public void testDI(){
		System.out.println(alphaDao);
		System.out.println(alphaService);
		System.out.println(simpleDateFormat);
	}
}
