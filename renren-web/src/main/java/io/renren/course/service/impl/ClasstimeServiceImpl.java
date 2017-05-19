package io.renren.course.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.course.dao.ClasstimeDao;
import io.renren.course.entity.ClasstimeEntity;
import io.renren.course.service.ClasstimeService;



@Service("classtimeService")
public class ClasstimeServiceImpl implements ClasstimeService {
	@Autowired
	private ClasstimeDao classtimeDao;
	
	@Override
	public ClasstimeEntity queryObject(Integer classtimeId){
		return classtimeDao.queryObject(classtimeId);
	}
	
	@Override
	public List<ClasstimeEntity> queryList(Map<String, Object> map){
		return classtimeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return classtimeDao.queryTotal(map);
	}
	
	@Override
	public void save(ClasstimeEntity classtime){
		classtimeDao.save(classtime);
	}
	
	@Override
	public void update(ClasstimeEntity classtime){
		classtimeDao.update(classtime);
	}
	
	@Override
	public void delete(Integer classtimeId){
		classtimeDao.delete(classtimeId);
	}
	
	@Override
	public void deleteBatch(Integer[] classtimeIds){
		classtimeDao.deleteBatch(classtimeIds);
	}
	
}
