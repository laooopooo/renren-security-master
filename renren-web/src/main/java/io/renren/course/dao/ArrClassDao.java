package io.renren.course.dao;

import java.util.List;
import java.util.Map;

import io.renren.course.entity.ArrClassEntity;
import io.renren.course.entity.CourseTableEntity;
import io.renren.course.entity.RemainRoomEntity;
import io.renren.dao.BaseDao;

/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-20 12:35:54
 */
public interface ArrClassDao extends BaseDao<ArrClassEntity> {
	/**
	 * 查询剩余的教室数量
	 * @param map
	 * @return
	 */
	List<RemainRoomEntity> selectbyWeek(Map<String, Object> map);
	/**
	 * 查询老师的课程，后期需要处理数据
	 * @param map
	 * @return 排课表
	 */
	List<CourseTableEntity> queryTeacherCourse(Map<String, Object> map);
	
}
