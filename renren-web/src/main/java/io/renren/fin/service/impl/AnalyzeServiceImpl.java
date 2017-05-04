package io.renren.fin.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.fin.dao.AnalyzeDao;
import io.renren.fin.entity.AnalyzeEntity;
import io.renren.fin.service.AnalyzeService;



@Service("analyzeService")
public class AnalyzeServiceImpl implements AnalyzeService {
	@Autowired
	private AnalyzeDao<AnalyzeEntity> analyzeDao;

	@Override
	public List<AnalyzeEntity> analyzeList(Map<String, Object> map) {
		
		return analyzeDao.analyzeList(map);
	}
	
	
}
