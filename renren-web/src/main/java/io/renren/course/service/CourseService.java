package io.renren.course.service;

import java.util.List;
import java.util.Map;

import io.renren.course.entity.CourseEntity;

/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-03 14:22:27
 */
public interface CourseService {
	
	CourseEntity queryObject(Integer courseId);
	
	List<CourseEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CourseEntity course);
	
	void update(CourseEntity course);
	
	void delete(Integer courseId);
	
	void deleteBatch(Integer[] courseIds);
}
