package io.renren.personel.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.controllers.AbstractController;
import io.renren.personel.entity.PositionEntity;
import io.renren.personel.service.PositionService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-04-11 13:29:59
 */
@RestController
@RequestMapping("position")
public class PositionController extends AbstractController{
	@Autowired
	private PositionService positionService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("position:list")
	public R list(@RequestParam Map<String, Object> params){
		
		params.put("tenantId", getTenantId());
		//查询列表数据
        Query query = new Query(params);

		List<PositionEntity> positionList = positionService.queryList(query);
		int total = positionService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(positionList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{positionId}")
	@RequiresPermissions("position:info")
	public R info(@PathVariable("positionId") Integer positionId){
		PositionEntity position = positionService.queryObject(positionId);
		
		return R.ok().put("position", position);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("position:save")
	public R save(@RequestBody PositionEntity position){
		position.setTenantId(getTenantId());
		positionService.save(position);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("position:update")
	public R update(@RequestBody PositionEntity position){
		positionService.update(position);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("position:delete")
	public R delete(@RequestBody Integer[] positionIds){
		positionService.deleteBatch(positionIds);
		
		return R.ok();
	}
	
}
