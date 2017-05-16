package io.renren.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.dao.TenantDao;
import io.renren.entity.TenantEntity;
import io.renren.service.TenantService;



@Service("tenantService")
public class TenantServiceImpl implements TenantService {
	@Autowired
	private TenantDao tenantDao;
	
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
	
	@Override
	public void save(TenantEntity tenant){
		tenantDao.save(tenant);
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
