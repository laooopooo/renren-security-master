package io.renren.personel.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.personel.dao.StudentDao;
import io.renren.personel.entity.StudentEntity;
import io.renren.personel.service.StudentService;



@Service("studentService")
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentDao studentDao;
	
	@Override
	public StudentEntity queryObject(Integer studentId){
		return studentDao.queryObject(studentId);
	}
	
	@Override
	public List<StudentEntity> queryList(Map<String, Object> map){
		return studentDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return studentDao.queryTotal(map);
	}
	
	@Override
	public void save(StudentEntity student){
		studentDao.save(student);
	}
	
	@Override
	public void update(StudentEntity student){
		studentDao.update(student);
	}
	
	@Override
	public void delete(Integer studentId){
		studentDao.delete(studentId);
	}
	
	@Override
	public void deleteBatch(Integer[] studentIds){
		studentDao.deleteBatch(studentIds);
	}
	
}
