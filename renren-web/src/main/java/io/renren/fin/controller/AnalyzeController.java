package io.renren.fin.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.fin.entity.AnalyzeEntity;
import io.renren.fin.entity.FinanceEntity;
import io.renren.fin.service.AnalyzeService;
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
@RequestMapping("analyze")
public class AnalyzeController {
	@Autowired
	private AnalyzeService analyzeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	// @RequiresPermissions("analyze:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<AnalyzeEntity> analyzeList = analyzeService.analyzeList(query);
		int total = analyzeService.myQueryTotal(query);
		
		PageUtils pageUtil = new PageUtils(analyzeList, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
	
}
