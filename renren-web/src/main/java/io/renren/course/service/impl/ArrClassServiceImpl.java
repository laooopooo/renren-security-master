package io.renren.course.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.course.dao.ArrClassDao;
import io.renren.course.dao.ClasstimeDao;
import io.renren.course.entity.ArrClassEntity;
import io.renren.course.entity.ClasstimeEntity;
import io.renren.course.entity.CourseTableEntity;
import io.renren.course.entity.CourseTableFormatEntity;
import io.renren.course.entity.RemainRoomEntity;
import io.renren.course.service.ArrClassService;

@Service("arrClassService")
public class ArrClassServiceImpl implements ArrClassService {
	@Autowired
	private ArrClassDao arrClassDao;

	@Autowired
	private ClasstimeDao classtimeDao;

	@Override
	public ArrClassEntity queryObject(Integer arrClassId) {
		return arrClassDao.queryObject(arrClassId);
	}

	@Override
	public List<ArrClassEntity> queryList(Map<String, Object> map) {
		return arrClassDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return arrClassDao.queryTotal(map);
	}

	@Override
	public void save(ArrClassEntity arrClass) {
		arrClassDao.save(arrClass);
	}

	@Override
	public void update(ArrClassEntity arrClass) {
		arrClassDao.update(arrClass);
	}

	@Override
	public void delete(Integer arrClassId) {
		arrClassDao.delete(arrClassId);
	}

	@Override
	public void deleteBatch(Integer[] arrClassIds) {
		arrClassDao.deleteBatch(arrClassIds);
	}

	@Override
	public List<ArrClassEntity> selectAllWeek(int tenantId) {
		Map<String, Object> map;

		// 查找出所有的classtime，遍历classtimeId]
		List<ClasstimeEntity> classtimes = classtimeDao.queryList();
		List<ArrClassEntity> arrClassEntities = new ArrayList<ArrClassEntity>();
		ArrClassEntity arrClassEntity;
		for (int i = 0; i < classtimes.size(); i++) {
			map = new HashMap<String, Object>();
			map.put("classtimeId", classtimes.get(i).getClasstimeId());
			map.put("tenantId", tenantId);
			List<RemainRoomEntity> remainRoomEntities = arrClassDao.selectbyWeek(map);
			// 封装
			arrClassEntity = new ArrClassEntity();
			arrClassEntity.setClasstimeName(classtimes.get(i).getClasstimeName());
			arrClassEntity.setStartTime(classtimes.get(i).getStartTime());
			arrClassEntity.setEndTime(classtimes.get(i).getEndTime());
			arrClassEntity.setClasstimeId(classtimes.get(i).getClasstimeId());
			arrClassEntity.setMon(remainRoomEntities.get(0).getRemainNumber());
			arrClassEntity.setTues(remainRoomEntities.get(1).getRemainNumber());
			arrClassEntity.setWed(remainRoomEntities.get(2).getRemainNumber());
			arrClassEntity.setThur(remainRoomEntities.get(3).getRemainNumber());
			arrClassEntity.setFri(remainRoomEntities.get(4).getRemainNumber());
			arrClassEntity.setSat(remainRoomEntities.get(5).getRemainNumber());
			arrClassEntity.setSun(remainRoomEntities.get(6).getRemainNumber());

			arrClassEntities.add(arrClassEntity);
		}
		return arrClassEntities;
	}

	@Override
	public List<CourseTableFormatEntity> queryTeacherCourse(Map<String, Object> map) {
		
		// 查找出所有的classtime，遍历classtimeId]
		List<ClasstimeEntity> classtimes = classtimeDao.queryList();
		
		//开始格式化数据，将courseTables转化成CourseTableFormatEntity类型的数据
		CourseTableFormatEntity courseTableFormat;
		List<CourseTableFormatEntity> courseTableFormats = new ArrayList<CourseTableFormatEntity>();
		
		for (int i = 0; i < classtimes.size(); i++) {
			
			map.put("classtimeId", classtimes.get(i).getClasstimeId());
			List<CourseTableEntity> courseTables = arrClassDao.queryTeacherCourse(map);
			courseTableFormat = new CourseTableFormatEntity();
			courseTableFormat.setClasstimeId(classtimes.get(i).getClasstimeId());
			courseTableFormat.setClasstimeName(classtimes.get(i).getClasstimeName());
			courseTableFormat.setStartTime(classtimes.get(i).getStartTime());
			courseTableFormat.setEndTime(classtimes.get(i).getEndTime());
			courseTableFormat.setMon(courseTables.get(0));
			courseTableFormat.setTues(courseTables.get(1));
			courseTableFormat.setWed(courseTables.get(2));
			courseTableFormat.setThur(courseTables.get(3));
			courseTableFormat.setFri(courseTables.get(4));
			courseTableFormat.setSat(courseTables.get(5));
			courseTableFormat.setSun(courseTables.get(6));
			courseTableFormats.add(courseTableFormat);
		}
		return courseTableFormats;
	}

}
