package io.renren.personel.service;

import java.util.List;
import java.util.Map;

import io.renren.personel.entity.PositionEntity;

/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-04-11 13:29:59
 */
public interface PositionService {
	
	PositionEntity queryObject(Integer positionId);
	
	List<PositionEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(PositionEntity position);
	
	void update(PositionEntity position);
	
	void delete(Integer positionId);
	
	void deleteBatch(Integer[] positionIds);
}
