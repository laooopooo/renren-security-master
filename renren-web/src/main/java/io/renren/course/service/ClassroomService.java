package io.renren.course.service;

import io.renren.course.entity.ClassroomEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-18 19:15:21
 */
public interface ClassroomService {
	
	ClassroomEntity queryObject(Integer classroomId);
	
	List<ClassroomEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ClassroomEntity classroom);
	
	void update(ClassroomEntity classroom);
	
	void delete(Integer classroomId);
	
	void deleteBatch(Integer[] classroomIds);
	
	List<ClassroomEntity> queryCanArr(Map<String, Object> map);
}
