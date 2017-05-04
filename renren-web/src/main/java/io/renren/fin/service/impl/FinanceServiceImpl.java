package io.renren.fin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import io.renren.fin.dao.FinanceDao;
import io.renren.fin.entity.FinanceEntity;
import io.renren.fin.service.FinanceService;



@Service("financeService")
public class FinanceServiceImpl implements FinanceService {
	@Autowired
	private FinanceDao financeDao;
	
	@Override
	public FinanceEntity queryObject(Integer financeId){
		return financeDao.queryObject(financeId);
	}
	
	@Override
	public List<FinanceEntity> queryList(Map<String, Object> map){
		return financeDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return financeDao.queryTotal(map);
	}
	
	@Override
	public void save(FinanceEntity finance){
		financeDao.save(finance);
	}
	
	@Override
	public void update(FinanceEntity finance){
		financeDao.update(finance);
	}
	
	@Override
	public void delete(Integer financeId){
		financeDao.delete(financeId);
	}
	
	@Override
	public void deleteBatch(Integer[] financeIds){
		financeDao.deleteBatch(financeIds);
	}
	
}
