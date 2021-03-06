package io.renren.system.service;

import java.util.List;
import java.util.Map;

import io.renren.system.entity.TenantEntity;

/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-16 08:09:52
 */
public interface TenantService {
	
	TenantEntity queryObject(Integer tenantId);
	
	List<TenantEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TenantEntity tenant);
	
	void update(TenantEntity tenant);
	
	void delete(Integer tenantId);
	
	void deleteBatch(Integer[] tenantIds);
}
