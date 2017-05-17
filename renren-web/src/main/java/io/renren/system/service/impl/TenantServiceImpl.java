package io.renren.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.renren.dao.SysUserDao;
import io.renren.dao.SysUserRoleDao;
import io.renren.entity.SysUserEntity;
import io.renren.system.dao.TenantDao;
import io.renren.system.entity.TenantEntity;
import io.renren.system.service.TenantService;



@Service("tenantService")
public class TenantServiceImpl implements TenantService {
	@Autowired
	private TenantDao tenantDao;
	
	@Autowired
	private SysUserDao sysUserDao;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	

	
	@Override
	public TenantEntity queryObject(Integer tenantId){
		return tenantDao.queryObject(tenantId);
	}
	
	@Override
	public List<TenantEntity> queryList(Map<String, Object> map){
		return tenantDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return tenantDao.queryTotal(map);
	}
	
	@Transactional
	@Override
	public void save(TenantEntity tenant){
		//1、添加租户
		tenantDao.save(tenant);
		
		//2.为租户添加登陆的用户账号，并赋予管理员权限
		SysUserEntity user=new SysUserEntity();
		user.setUsername(tenant.getTenantPhone());
		user.setMobile(tenant.getTenantPhone());
		user.setCreateTime(new Date());
		user.setTenantId(tenant.getTenantId());
		user.setStatus(1);
		user.setEmail(tenant.getEmail());
		
		//租户的初始密码为省份证的后四位
		String str=tenant.getTenantIdcard();
		user.setPassword(str.substring(str.length()-4, str.length()));
		ByteSource credentialsSalt =ByteSource.Util.bytes(user.getUsername());
		Object result= new  SimpleHash("MD5", user.getPassword(),credentialsSalt, 3);
		user.setPassword(result.toString());
		
		sysUserDao.save(user);
		
		//3、设置用户权限为租户管理员(角色管理ID为1)  保存用户与角色关系
		Map<String, Object> map = new HashMap<>();
		List<Long> roleIdList =new ArrayList<Long>();
		roleIdList.add(1L);
		map.put("userId", user.getUserId());
		map.put("roleIdList", roleIdList);
		sysUserRoleDao.save(map);
		
		
		
		
	}
	
	@Override
	public void update(TenantEntity tenant){
		tenantDao.update(tenant);
	}
	
	@Override
	public void delete(Integer tenantId){
		tenantDao.delete(tenantId);
	}
	
	@Override
	public void deleteBatch(Integer[] tenantIds){
		tenantDao.deleteBatch(tenantIds);
	}
	
}
