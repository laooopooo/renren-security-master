package io.renren.fin.service;

import io.renren.fin.entity.FinanceEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-04 14:57:23
 */
public interface FinanceService {
	
	FinanceEntity queryObject(Integer financeId);
	
	List<FinanceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(FinanceEntity finance);
	
	void update(FinanceEntity finance);
	
	void delete(Integer financeId);
	
	void deleteBatch(Integer[] financeIds);
}
