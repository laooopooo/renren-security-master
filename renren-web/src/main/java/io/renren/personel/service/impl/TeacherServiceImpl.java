package io.renren.personel.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.personel.dao.TeacherDao;
import io.renren.personel.entity.TeacherEntity;
import io.renren.personel.service.TeacherService;



@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	private TeacherDao teacherDao;
	
	@Override
	public TeacherEntity queryObject(Integer teacherId){
		return teacherDao.queryObject(teacherId);
	}
	
	@Override
	public List<TeacherEntity> queryList(Map<String, Object> map){
		return teacherDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return teacherDao.queryTotal(map);
	}
	
	@Override
	public void save(TeacherEntity teacher){
		teacherDao.save(teacher);
	}
	
	@Override
	public void update(TeacherEntity teacher){
		teacherDao.update(teacher);
	}
	
	@Override
	public void delete(Integer teacherId){
		teacherDao.delete(teacherId);
	}
	
	@Override
	public void deleteBatch(Integer[] teacherIds){
		teacherDao.deleteBatch(teacherIds);
	}
	
}
