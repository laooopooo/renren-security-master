package io.renren.fin.dao;

import io.renren.dao.BaseDao;
import io.renren.fin.entity.FinanceEntity;

/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-04 14:57:23
 */
public interface FinanceDao extends BaseDao<FinanceEntity> {
	int selectMaxId();
	
}
