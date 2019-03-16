package com.aloys.rbac.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {

	
	//配置过滤器
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager manager) {
		ShiroFilterFactoryBean filterBean = new ShiroFilterFactoryBean();
		filterBean.setSecurityManager(manager);
		
		Map<String,String> filterChainMap = new LinkedHashMap<>();// 过滤链从上而下执行
		
		filterChainMap.put("/css/**", "anon");   //静态资源可以匿名访问
		filterChainMap.put("/js/**", "anon");
		filterChainMap.put("/img/**", "anon");
		filterChainMap.put("/layui/**", "anon");
		
		
		
		
		filterChainMap.put("/logout", "logout");
		//filterChainMap.put("/user/**", "authc,roles[admin]");
		filterChainMap.put("/**", "authc");
		
		filterBean.setLoginUrl("/login");
		filterBean.setSuccessUrl("/index");
		filterBean.setUnauthorizedUrl("/403");
		filterBean.setFilterChainDefinitionMap(filterChainMap);
		return filterBean;
	}
	
	@Bean
	public MyRealm myRealm() {
		return new MyRealm();
	}
	
	@Bean
	public SecurityManager securityManager() {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(myRealm());
		return manager;
	}
	
	//注解生效
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager());
		return advisor;
	}
}
