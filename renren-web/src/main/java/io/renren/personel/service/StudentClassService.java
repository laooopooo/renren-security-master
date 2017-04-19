package io.renren.personel.service;

import java.util.List;
import java.util.Map;

import io.renren.personel.entity.StudentClassEntity;

/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-04-11 09:27:29
 */
public interface StudentClassService {
	
	StudentClassEntity queryObject(Integer studentClassId);
	
	List<StudentClassEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(StudentClassEntity studentClass);
	
	void update(StudentClassEntity studentClass);
	
	void delete(Integer studentClassId);
	
	void deleteBatch(Integer[] studentClassIds);
}
