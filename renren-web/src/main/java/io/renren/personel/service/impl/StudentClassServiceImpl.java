package io.renren.personel.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.personel.dao.StudentClassDao;
import io.renren.personel.entity.StudentClassEntity;
import io.renren.personel.service.StudentClassService;



@Service("studentClassService")
public class StudentClassServiceImpl implements StudentClassService {
	@Autowired
	private StudentClassDao studentClassDao;
	
	@Override
	public StudentClassEntity queryObject(Integer studentClassId){
		return studentClassDao.queryObject(studentClassId);
	}
	
	@Override
	public List<StudentClassEntity> queryList(Map<String, Object> map){
		return studentClassDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return studentClassDao.queryTotal(map);
	}
	
	@Override
	public void save(StudentClassEntity studentClass){
		studentClassDao.save(studentClass);
	}
	
	@Override
	public void update(StudentClassEntity studentClass){
		studentClassDao.update(studentClass);
	}
	
	@Override
	public void delete(Integer studentClassId){
		studentClassDao.delete(studentClassId);
	}
	
	@Override
	public void deleteBatch(Integer[] studentClassIds){
		studentClassDao.deleteBatch(studentClassIds);
	}
	
}
