package io.renren.course.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.renren.course.dao.CourseDao;
import io.renren.course.dao.CourseQuitDao;
import io.renren.course.dao.CourseRecordDao;
import io.renren.course.entity.CourseEntity;
import io.renren.course.entity.CourseQuitEntity;
import io.renren.course.entity.CourseRecordEntity;
import io.renren.course.service.CourseRecordService;
import io.renren.fin.dao.FinanceDao;
import io.renren.fin.entity.FinanceEntity;
import io.renren.personel.dao.StudentDao;
import io.renren.personel.entity.StudentEntity;
import io.renren.utils.R;



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
	
	@Autowired
	private CourseQuitDao courseQuitDao;
	
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
	
	@Transactional
	@Override
	public R save(CourseRecordEntity courseRecord){
		
		//1、插入fin_finance表
		CourseEntity cEntity=courseDao.queryObject(courseRecord.getCourseId());
		StudentEntity sEntity=studentDao.queryObject(courseRecord.getStudentId());
		FinanceEntity fEntity=new FinanceEntity();
		if (courseRecord.getActualPrice()>cEntity.getOriginalPrice()) {
			return R.error("实收费用不能高于原价");
		}
		fEntity.setPayOrIncome(1);//这里1表示收入，0表示支出
		fEntity.setFinType("小班学费");
		fEntity.setFinDate(new Date());
		fEntity.setFinAmount(courseRecord.getActualPrice());
		fEntity.setFinQuarter(cEntity.getQuarter());
		fEntity.setFinYear(cEntity.getYear());
		fEntity.setRemarks(sEntity.getName()+"报名了"+cEntity.getCourseName());
		fEntity.setTenantId(cEntity.getTenantId());
		
		financeDao.save(fEntity);
		
		//2、插入c_course_record表
		courseRecord.setFinanceId(financeDao.selectMaxId());
		courseRecordDao.save(courseRecord);
		
		return R.ok();
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

	@Transactional
	public R quit(CourseRecordEntity courseRecord) {
		//1、插入fin_finance表
		FinanceEntity fEntity=new FinanceEntity();
		CourseEntity cEntity=courseDao.queryObject(courseRecord.getCourseId());
		StudentEntity sEntity=studentDao.queryObject(courseRecord.getStudentId());
		
		fEntity.setPayOrIncome(0);//这里1表示收入，0表示支出
		fEntity.setFinType("小班退课");
		fEntity.setFinDate(new Date());
		fEntity.setFinAmount(courseRecord.getQuitMoney());
		fEntity.setFinQuarter(cEntity.getQuarter());
		fEntity.setFinYear(cEntity.getYear());
		fEntity.setRemarks(sEntity.getName()+"退出了"+cEntity.getCourseName());
		fEntity.setTenantId(sEntity.getTenantId());
		
		financeDao.save(fEntity);
		
		//2、插入c_course_quit表
		CourseQuitEntity cQEntity=new CourseQuitEntity();
		cQEntity.setFinanceId(financeDao.selectMaxId());
		cQEntity.setCourseId(courseRecord.getCourseId());
		cQEntity.setStudentId(courseRecord.getStudentId());
		cQEntity.setQuitReason(courseRecord.getQuitReason());
		courseQuitDao.save(cQEntity);
		
		//3、删除c_course_record表中的数据
		int courseRecordId= courseRecordDao.selectIdbyStuCid(courseRecord.getCourseId(), courseRecord.getStudentId());
		courseRecordDao.delete(courseRecordId);
		return R.ok();
	}
	
}
