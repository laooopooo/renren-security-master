package io.renren.fin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.renren.controllers.AbstractController;
import io.renren.fin.entity.AnalyzeEntity;
import io.renren.fin.service.AnalyzeService;
import io.renren.utils.Constant;
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
public class AnalyzeController extends AbstractController{
	@Autowired
	private AnalyzeService analyzeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	// @RequiresPermissions("analyze:list")
	public R list(@RequestParam Map<String, Object> params){
		params.put("tenantId", getTenantId());
		//查询列表数据
        Query query = new Query(params);

		List<AnalyzeEntity> analyzeList = analyzeService.analyzeList(query);
		int total = analyzeService.myQueryTotal(query);
		
		PageUtils pageUtil = new PageUtils(analyzeList, total, query.getLimit(), query.getPage());
		return R.ok().put("page", pageUtil);
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/profit")
	// @RequiresPermissions("analyze:list")
	@ResponseBody
	public AnalyzeEntity profit(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("tenantId", getTenantId());
		}
		//查询列表数据
        Query query = new Query(params);

		AnalyzeEntity analyze = analyzeService.getProfit(query);
		
		return analyze;
	}
	
}
