package io.renren.course.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.course.dao.CourseDao;
import io.renren.course.entity.CourseEntity;
import io.renren.course.service.CourseService;



@Service("courseService")
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDao courseDao;
	
	@Override
	public CourseEntity queryObject(Integer courseId){
		return courseDao.queryObject(courseId);
	}
	
	@Override
	public List<CourseEntity> queryList(Map<String, Object> map){
		return courseDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return courseDao.queryTotal(map);
	}
	
	@Override
	public void save(CourseEntity course){
		courseDao.save(course);
	}
	
	@Override
	public void update(CourseEntity course){
		courseDao.update(course);
	}
	
	@Override
	public void delete(Integer courseId){
		courseDao.delete(courseId);
	}
	
	@Override
	public void deleteBatch(Integer[] courseIds){
		courseDao.deleteBatch(courseIds);
	}
	
}
