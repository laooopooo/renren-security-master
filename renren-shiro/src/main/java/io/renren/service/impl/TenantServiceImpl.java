package io.renren.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.renren.dao.SysUserDao;
import io.renren.dao.TenantDao;
import io.renren.entity.SysUserEntity;
import io.renren.entity.TenantEntity;
import io.renren.service.SysRoleService;
import io.renren.service.SysUserRoleService;
import io.renren.service.TenantService;



@Service("tenantService")
public class TenantServiceImpl implements TenantService {
	@Autowired
	private TenantDao tenantDao;
	
	@Autowired
	private SysUserDao sysUserDao;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysRoleService sysRoleService;

	
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
		//添加租户
		tenantDao.save(tenant);
		//2.为租户添加登陆的用户账号，并赋予管理员权限
		SysUserEntity user=new SysUserEntity();
		user.setUsername(tenant.getTenantPhone());
		
		user.setCreateTime(new Date());
		//sha256加密
		//user.setPassword(new Sha256Hash(user.getPassword()).toHex());
		
		//使用MD5盐值加密3次
		ByteSource credentialsSalt =ByteSource.Util.bytes(user.getUsername());
		Object result= new  SimpleHash("MD5", user.getPassword(),credentialsSalt, 3);
		user.setPassword(result.toString());
		
		sysUserDao.save(user);
		
		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
		
		sysUserDao.save(user);
		
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
