package io.renren.personel.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import io.renren.dao.BaseDao;
import io.renren.personel.entity.StudentEntity;

/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-04-11 13:30:01
 */
public interface StudentDao extends BaseDao<StudentEntity> {
	
	
	/**
	 * 根据listIds 查出所有在该id组中的学生信息
	 * @param studentIds
	 * @return
	 */
	List<String> queryNamesInStudentIds(@Param("studentIds")List<Integer> studentIds);
}
