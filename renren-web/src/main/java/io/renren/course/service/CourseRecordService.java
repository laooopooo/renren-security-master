package io.renren.course.service;

import io.renren.course.entity.CourseRecordEntity;
import io.renren.utils.R;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-09 18:43:47
 */
public interface CourseRecordService {
	
	CourseRecordEntity queryObject(Integer courseRecordId);
	
	List<CourseRecordEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	R save(CourseRecordEntity courseRecord);
	
	void update(CourseRecordEntity courseRecord);
	
	void delete(Integer courseRecordId);
	
	void deleteBatch(Integer[] courseRecordIds);
}
