package io.renren.personel.service;

import java.util.List;
import java.util.Map;

import io.renren.personel.entity.SubjectEntity;

/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-04-29 09:43:34
 */
public interface SubjectService {
	
	SubjectEntity queryObject(Integer subjectId);
	
	List<SubjectEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SubjectEntity subject);
	
	void update(SubjectEntity subject);
	
	void delete(Integer subjectId);
	
	void deleteBatch(Integer[] subjectIds);
}
