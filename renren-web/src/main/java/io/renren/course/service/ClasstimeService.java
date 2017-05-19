package io.renren.course.service;

import io.renren.course.entity.ClasstimeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-19 16:24:29
 */
public interface ClasstimeService {
	
	ClasstimeEntity queryObject(Integer classtimeId);
	
	List<ClasstimeEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ClasstimeEntity classtime);
	
	void update(ClasstimeEntity classtime);
	
	void delete(Integer classtimeId);
	
	void deleteBatch(Integer[] classtimeIds);
}
