package com.jt.sys.service.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.sys.dao.SysUserDao;
import com.jt.sys.entity.SysUser;
@Service
public class ShiroUserRealm extends AuthorizingRealm{
	@Autowired
	private SysUserDao sysUserDao;
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		HashedCredentialsMatcher hcm=new HashedCredentialsMatcher("MD5");//构建凭证匹配器
		hcm.setHashIterations(1);//加密次数，默认执行一次加密
		super.setCredentialsMatcher(hcm);
	}
	/**
	 * 完成用户信息获取及封装
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken uptoken=(UsernamePasswordToken) token;
		//验证用户是否存在
		String username = uptoken.getUsername();
		SysUser user=null;
		user=sysUserDao.findUserByUserName(username);//获取了数据库中的user信息
		if(user==null){
			throw new UnknownAccountException();
		}
		if(user.getValid()==0){
			throw new LockedAccountException();//如果valid=0，说明用户已经被禁用，抛出异常
		}
		ByteSource credentialsSalt=ByteSource.Util.bytes(user.getSalt());
		SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user,user.getPassword(), credentialsSalt, getName());
		return info;//返回给认证管理器
	}
	/**
	 * 完成用户授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		SysUser user = (SysUser)principal.getPrimaryPrincipal();
		SecurityUtils.getSubject().getPrincipal();
		return null;
	}

}
