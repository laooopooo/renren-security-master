package io.renren.course.service;

import java.util.List;
import java.util.Map;

import io.renren.course.entity.ArrClassEntity;

/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-20 12:35:54
 */
public interface ArrClassService {
	
	ArrClassEntity queryObject(Integer arrClassId);
	
	List<ArrClassEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ArrClassEntity arrClass);
	
	void update(ArrClassEntity arrClass);
	
	void delete(Integer arrClassId);
	
	void deleteBatch(Integer[] arrClassIds);
	
	List<ArrClassEntity> selectAllWeek(int tenantId);
}
