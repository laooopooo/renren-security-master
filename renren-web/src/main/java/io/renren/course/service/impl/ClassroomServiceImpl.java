package io.renren.course.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.course.dao.ClassroomDao;
import io.renren.course.entity.ClassroomEntity;
import io.renren.course.service.ClassroomService;



@Service("classroomService")
public class ClassroomServiceImpl implements ClassroomService {
	@Autowired
	private ClassroomDao classroomDao;
	
	@Override
	public ClassroomEntity queryObject(Integer classroomId){
		return classroomDao.queryObject(classroomId);
	}
	
	@Override
	public List<ClassroomEntity> queryList(Map<String, Object> map){
		return classroomDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return classroomDao.queryTotal(map);
	}
	
	@Override
	public void save(ClassroomEntity classroom){
		if (classroom.getRoomCapacity()==null) {
			classroom.setRoomCapacity(30);
		}
		classroomDao.save(classroom);
	}
	
	@Override
	public void update(ClassroomEntity classroom){
		classroomDao.update(classroom);
	}
	
	@Override
	public void delete(Integer classroomId){
		classroomDao.delete(classroomId);
	}
	
	@Override
	public void deleteBatch(Integer[] classroomIds){
		classroomDao.deleteBatch(classroomIds);
	}

	@Override
	public List<ClassroomEntity> queryCanArr(Map<String, Object> map) {
		return classroomDao.queryCanArr(map);
	}
	
}
