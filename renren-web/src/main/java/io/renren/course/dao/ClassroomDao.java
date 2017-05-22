package io.renren.course.dao;

import java.util.List;
import java.util.Map;

import io.renren.course.entity.ClassroomEntity;
import io.renren.dao.BaseDao;

/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-18 19:15:21
 */
public interface ClassroomDao extends BaseDao<ClassroomEntity> {
	List<ClassroomEntity> queryCanArr(Map<String, Object> map);
}
