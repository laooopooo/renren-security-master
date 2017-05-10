package io.renren.personel.service;

import java.util.List;
import java.util.Map;

import io.renren.course.entity.CourseEntity;
import io.renren.personel.entity.StudentEntity;

/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-04-11 13:30:01
 */
public interface StudentService {
	
	StudentEntity queryObject(Integer studentId);
	
	List<StudentEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(StudentEntity student);
	
	void update(StudentEntity student);
	
	void delete(Integer studentId);
	
	void deleteBatch(Integer[] studentIds);
	
	List<CourseEntity> queryCourseList(Map<String, Object> map);
	
	List<String> queryStudentNames(Integer courseId);
}
