package io.renren.course.service;

import java.util.List;
import java.util.Map;

import io.renren.course.entity.CourseSelectEntity;

/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-03 14:22:27
 */
public interface CourseSelectService {
	
	CourseSelectEntity queryObject(Integer courseSelectId);
	
	List<CourseSelectEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CourseSelectEntity courseSelect);
	
	void update(CourseSelectEntity courseSelect);
	
	void delete(Integer courseSelectId);
	
	void deleteBatch(Integer[] courseSelectIds);
}
