package io.renren.fin.dao;

import java.util.List;
import java.util.Map;

import io.renren.dao.BaseDao;
import io.renren.fin.entity.AnalyzeEntity;

/**
 * 
 * 
 * @author chenyuliao
 * @param <T>
 * @email 949395746@qq.com
 * @date 2017-05-04 14:57:23
 */
public interface AnalyzeDao<T> extends BaseDao<AnalyzeEntity> {
	List<T> analyzeList(Map<String, Object> map);
	int myQueryTotal(Map<String, Object> map);
}
