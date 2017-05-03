package io.renren.course.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.course.dao.CourseSelectDao;
import io.renren.course.entity.CourseSelectEntity;
import io.renren.course.service.CourseSelectService;



@Service("courseSelectService")
public class CourseSelectServiceImpl implements CourseSelectService {
	@Autowired
	private CourseSelectDao courseSelectDao;
	
	@Override
	public CourseSelectEntity queryObject(Integer courseSelectId){
		return courseSelectDao.queryObject(courseSelectId);
	}
	
	@Override
	public List<CourseSelectEntity> queryList(Map<String, Object> map){
		return courseSelectDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return courseSelectDao.queryTotal(map);
	}
	
	@Override
	public void save(CourseSelectEntity courseSelect){
		courseSelectDao.save(courseSelect);
	}
	
	@Override
	public void update(CourseSelectEntity courseSelect){
		courseSelectDao.update(courseSelect);
	}
	
	@Override
	public void delete(Integer courseSelectId){
		courseSelectDao.delete(courseSelectId);
	}
	
	@Override
	public void deleteBatch(Integer[] courseSelectIds){
		courseSelectDao.deleteBatch(courseSelectIds);
	}
	
}
