package io.renren.course.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.course.dao.CourseDao;
import io.renren.course.dao.CourseRecordDao;
import io.renren.course.entity.CourseEntity;
import io.renren.course.entity.CourseRecordEntity;
import io.renren.course.service.CourseRecordService;
import io.renren.fin.dao.FinanceDao;
import io.renren.fin.entity.FinanceEntity;
import io.renren.personel.dao.StudentDao;
import io.renren.personel.entity.StudentEntity;



@Service("courseRecordService")
public class CourseRecordServiceImpl implements CourseRecordService {
	@Autowired
	private CourseRecordDao courseRecordDao;
	
	@Autowired
	private CourseDao courseDao;
	
	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private FinanceDao financeDao;
	
	@Override
	public CourseRecordEntity queryObject(Integer courseRecordId){
		return courseRecordDao.queryObject(courseRecordId);
	}
	
	@Override
	public List<CourseRecordEntity> queryList(Map<String, Object> map){
		return courseRecordDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return courseRecordDao.queryTotal(map);
	}
	
	@Override
	public void save(CourseRecordEntity courseRecord){
		//courseRecordDao.save(courseRecord);
		
		//1、插入fin_finance表
		CourseEntity cEntity=courseDao.queryObject(courseRecord.getCourseId());
		StudentEntity sEntity=studentDao.queryObject(courseRecord.getStudentId());
		FinanceEntity fEntity=new FinanceEntity();
		
		fEntity.setPayOrIncome(1);
		fEntity.setFinType("小班学费");
		fEntity.setFinDate(new Date());
		fEntity.setFinAmount(courseRecord.getActualPrice());
		fEntity.setFinQuarter(cEntity.getQuarter());
		fEntity.setFinYear(cEntity.getYear());
		fEntity.setRemarks(sEntity.getName()+"报名了"+cEntity.getCourseName());
		
		financeDao.save(fEntity);
		
		//2、插入c_course_record表
		courseRecord.setFinanceId(financeDao.selectMaxId());
		courseRecordDao.save(courseRecord);
	}
	
	@Override
	public void update(CourseRecordEntity courseRecord){
		courseRecordDao.update(courseRecord);
	}
	
	@Override
	public void delete(Integer courseRecordId){
		courseRecordDao.delete(courseRecordId);
	}
	
	@Override
	public void deleteBatch(Integer[] courseRecordIds){
		courseRecordDao.deleteBatch(courseRecordIds);
	}
	
}
