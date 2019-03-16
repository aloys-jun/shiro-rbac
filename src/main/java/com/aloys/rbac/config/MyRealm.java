package com.aloys.rbac.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.aloys.rbac.bean.Permission;
import com.aloys.rbac.bean.Role;
import com.aloys.rbac.bean.UserInfo;
import com.aloys.rbac.service.UserService;

public class MyRealm extends AuthorizingRealm{

	@Autowired
	private UserService userService;
	
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo autho = new SimpleAuthorizationInfo();
		UserInfo user = (UserInfo) principals.getPrimaryPrincipal();
		for (Role role : user.getRoleList()) {
			autho.addRole(role.getRoleName());
			for (Permission permission : role.getPermissions()) {
				autho.addStringPermission(permission.getPermission());
			}
		}
		return autho;
	}

	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		if (token.getPrincipal() == null) {
			return null;
		}
		
		String userName = (String) token.getPrincipal();
		UserInfo user = userService.getUserInfoByName(userName);
		
		if (user == null) {
			return null;
		}
		
		SimpleAuthenticationInfo authen = new SimpleAuthenticationInfo(user, user.getPassword(), getName());
		
		
		return authen;
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
