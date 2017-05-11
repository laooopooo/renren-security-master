package io.renren.course.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import io.renren.course.entity.CourseEntity;
import io.renren.dao.BaseDao;

/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-03 14:22:27
 */
public interface CourseDao extends BaseDao<CourseEntity> {
	 List<CourseEntity> queryListInCourseIds(@Param("courseIds")List<Integer> courseIds);
	 
}
