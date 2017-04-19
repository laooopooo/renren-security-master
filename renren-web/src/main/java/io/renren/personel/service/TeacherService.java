package io.renren.personel.service;

import java.util.List;
import java.util.Map;

import io.renren.personel.entity.TeacherEntity;

/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-04-11 13:30:01
 */
public interface TeacherService {
	
	TeacherEntity queryObject(Integer teacherId);
	
	List<TeacherEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TeacherEntity teacher);
	
	void update(TeacherEntity teacher);
	
	void delete(Integer teacherId);
	
	void deleteBatch(Integer[] teacherIds);
}
