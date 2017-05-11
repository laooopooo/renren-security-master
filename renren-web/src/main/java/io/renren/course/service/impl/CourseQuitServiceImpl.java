package io.renren.course.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.course.dao.CourseQuitDao;
import io.renren.course.entity.CourseQuitEntity;
import io.renren.course.service.CourseQuitService;



@Service("courseQuitService")
public class CourseQuitServiceImpl implements CourseQuitService {
	@Autowired
	private CourseQuitDao courseQuitDao;
	
	@Override
	public CourseQuitEntity queryObject(Integer quitCourseId){
		return courseQuitDao.queryObject(quitCourseId);
	}
	
	@Override
	public List<CourseQuitEntity> queryList(Map<String, Object> map){
		return courseQuitDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return courseQuitDao.queryTotal(map);
	}
	
	@Override
	public void save(CourseQuitEntity courseQuit){
		courseQuitDao.save(courseQuit);
	}
	
	@Override
	public void update(CourseQuitEntity courseQuit){
		courseQuitDao.update(courseQuit);
	}
	
	@Override
	public void delete(Integer quitCourseId){
		courseQuitDao.delete(quitCourseId);
	}
	
	@Override
	public void deleteBatch(Integer[] quitCourseIds){
		courseQuitDao.deleteBatch(quitCourseIds);
	}
	
}
