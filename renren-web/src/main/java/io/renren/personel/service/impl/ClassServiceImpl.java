package io.renren.personel.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.personel.dao.ClassDao;
import io.renren.personel.entity.ClassEntity;
import io.renren.personel.service.ClassService;



@Service("classService")
public class ClassServiceImpl implements ClassService {
	@Autowired
	private ClassDao classDao;
	
	@Override
	public ClassEntity queryObject(Integer classId){
		return classDao.queryObject(classId);
	}
	
	@Override
	public List<ClassEntity> queryList(Map<String, Object> map){
		return classDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return classDao.queryTotal(map);
	}
	
	@Override
	public void save(ClassEntity myclass){
		classDao.save(myclass);
	}
	
	@Override
	public void update(ClassEntity myclass){
		classDao.update(myclass);
	}
	
	@Override
	public void delete(Integer classId){
		classDao.delete(classId);
	}
	
	@Override
	public void deleteBatch(Integer[] classIds){
		classDao.deleteBatch(classIds);
	}
	
}
