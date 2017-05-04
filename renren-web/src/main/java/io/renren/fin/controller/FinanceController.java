package io.renren.fin.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.fin.entity.FinanceEntity;
import io.renren.fin.service.FinanceService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-04 14:57:23
 */
@RestController
@RequestMapping("finance")
public class FinanceController {
	@Autowired
	private FinanceService financeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("finance:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<FinanceEntity> financeList = financeService.queryList(query);
		int total = financeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(financeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{financeId}")
	@RequiresPermissions("finance:info")
	public R info(@PathVariable("financeId") Integer financeId){
		FinanceEntity finance = financeService.queryObject(financeId);
		
		return R.ok().put("finance", finance);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("finance:save")
	public R save(@RequestBody FinanceEntity finance){
		financeService.save(finance);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("finance:update")
	public R update(@RequestBody FinanceEntity finance){
		financeService.update(finance);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("finance:delete")
	public R delete(@RequestBody Integer[] financeIds){
		financeService.deleteBatch(financeIds);
		
		return R.ok();
	}
	
}
