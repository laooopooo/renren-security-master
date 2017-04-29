package io.renren.personel.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.personel.dao.SubjectDao;
import io.renren.personel.entity.SubjectEntity;
import io.renren.personel.service.SubjectService;



@Service("subjectService")
public class SubjectServiceImpl implements SubjectService {
	@Autowired
	private SubjectDao subjectDao;
	
	@Override
	public SubjectEntity queryObject(Integer subjectId){
		return subjectDao.queryObject(subjectId);
	}
	
	@Override
	public List<SubjectEntity> queryList(Map<String, Object> map){
		return subjectDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return subjectDao.queryTotal(map);
	}
	
	@Override
	public void save(SubjectEntity subject){
		subjectDao.save(subject);
	}
	
	@Override
	public void update(SubjectEntity subject){
		subjectDao.update(subject);
	}
	
	@Override
	public void delete(Integer subjectId){
		subjectDao.delete(subjectId);
	}
	
	@Override
	public void deleteBatch(Integer[] subjectIds){
		subjectDao.deleteBatch(subjectIds);
	}
	
}
