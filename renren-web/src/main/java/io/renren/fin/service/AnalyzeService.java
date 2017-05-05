package io.renren.fin.service;

import java.util.List;
import java.util.Map;

import io.renren.fin.entity.AnalyzeEntity;

/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-04 14:57:23
 */
public interface AnalyzeService {
	
	List<AnalyzeEntity> analyzeList(Map<String, Object> map);
	
	int myQueryTotal(Map<String, Object> map);
}
