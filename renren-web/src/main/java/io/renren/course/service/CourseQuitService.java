package io.renren.course.service;

import io.renren.course.entity.CourseQuitEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-11 23:02:53
 */
public interface CourseQuitService {
	
	CourseQuitEntity queryObject(Integer quitCourseId);
	
	List<CourseQuitEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CourseQuitEntity courseQuit);
	
	void update(CourseQuitEntity courseQuit);
	
	void delete(Integer quitCourseId);
	
	void deleteBatch(Integer[] quitCourseIds);
}
