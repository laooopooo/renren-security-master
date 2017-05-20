package io.renren.course.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.renren.controllers.AbstractController;
import io.renren.course.entity.ArrClassEntity;
import io.renren.course.service.ArrClassService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-20 12:35:54
 */
@RestController
@RequestMapping("arrclass")
public class ArrClassController extends AbstractController{
	@Autowired
	private ArrClassService arrClassService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("arrclass:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ArrClassEntity> arrClassList = arrClassService.queryList(query);
		int total = arrClassService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(arrClassList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{arrClassId}")
	@RequiresPermissions("arrclass:info")
	public R info(@PathVariable("arrClassId") Integer arrClassId){
		ArrClassEntity arrClass = arrClassService.queryObject(arrClassId);
		
		return R.ok().put("arrClass", arrClass);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("arrclass:save")
	public R save(@RequestBody ArrClassEntity arrClass){
		arrClassService.save(arrClass);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("arrclass:update")
	public R update(@RequestBody ArrClassEntity arrClass){
		arrClassService.update(arrClass);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("arrclass:delete")
	public R delete(@RequestBody Integer[] arrClassIds){
		arrClassService.deleteBatch(arrClassIds);
		
		return R.ok();
	}
	
	/**
	 * 列表
	 */
	@RequestMapping("/allweek")
	@RequiresPermissions("arrclass:list")
	@ResponseBody
	public R allweek(@RequestParam Map<String, Object> params){
		//查询列表数据
		
		List<ArrClassEntity> arrClassEntitys = arrClassService.selectAllWeek(getTenantId());
		
		return R.ok().put("arrClass", arrClassEntitys);
	}
	
}
