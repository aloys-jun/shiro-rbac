package com.aloys.rbac.controller;



import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {

	
	
	@GetMapping("/login")
	public ModelAndView login() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}
	
	@GetMapping("/index")
	public String index() {
		System.out.println("认证成功。。。。。。");
		return "admin";
	}
	
	
	@GetMapping("/403")
	public String error() {
		System.out.println("越权操作。。。。。");
		return "403";
	}
	
	@PostMapping("/login")
	public String login(String userName,String password,Model model,HttpServletResponse response){
		System.out.println("开始认证。。。。。。。");
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
		subject.login(token); //登陆失败抛出异常
		return "ok";
	}
	
	@RequiresRoles(value={"admin","vip"},logical=Logical.OR)
	@GetMapping("/list")
    public String list(){
		
		return "查看用户";
	}
	
	
	@RequiresRoles(value="admin")
	@GetMapping("/user/create")
	public String create(){
		
		return "创建用户";
	}
	
	@RequiresRoles(value={"admin","vip"},logical=Logical.OR)
	@GetMapping("/user/delete")
	public String delete(){
		
		return "删除用户";
	}
	
	
}
