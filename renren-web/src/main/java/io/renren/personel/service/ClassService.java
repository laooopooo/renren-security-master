package io.renren.personel.service;

import java.util.List;
import java.util.Map;

import io.renren.personel.entity.ClassEntity;

/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-04-11 09:27:28
 */
public interface ClassService {
	
	ClassEntity queryObject(Integer classId);
	
	List<ClassEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ClassEntity myclass);
	
	void update(ClassEntity myclass);
	
	void delete(Integer classId);
	
	void deleteBatch(Integer[] classIds);
}
