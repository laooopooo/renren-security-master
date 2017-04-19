package io.renren.personel.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.renren.personel.dao.PositionDao;
import io.renren.personel.entity.PositionEntity;
import io.renren.personel.service.PositionService;



@Service("positionService")
public class PositionServiceImpl implements PositionService {
	@Autowired
	private PositionDao positionDao;
	
	@Override
	public PositionEntity queryObject(Integer positionId){
		return positionDao.queryObject(positionId);
	}
	
	@Override
	public List<PositionEntity> queryList(Map<String, Object> map){
		return positionDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return positionDao.queryTotal(map);
	}
	
	@Override
	public void save(PositionEntity position){
		positionDao.save(position);
	}
	
	@Override
	public void update(PositionEntity position){
		positionDao.update(position);
	}
	
	@Override
	public void delete(Integer positionId){
		positionDao.delete(positionId);
	}
	
	@Override
	public void deleteBatch(Integer[] positionIds){
		positionDao.deleteBatch(positionIds);
	}
	
}
