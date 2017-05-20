package io.renren.course.dao;

import java.util.List;
import java.util.Map;

import io.renren.course.entity.ArrClassEntity;
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
	List<RemainRoomEntity> selectbyWeek(Map<String, Object> map);
}
