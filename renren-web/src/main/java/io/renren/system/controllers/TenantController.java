package io.renren.system.controllers;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.controllers.AbstractController;
import io.renren.system.entity.TenantEntity;
import io.renren.system.service.TenantService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-16 08:09:52
 */
@RestController
@RequestMapping("tenant")
public class TenantController extends AbstractController{
	@Autowired
	private TenantService tenantService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("tenant:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TenantEntity> tenantList = tenantService.queryList(query);
		int total = tenantService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(tenantList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{tenantId}")
	@RequiresPermissions("tenant:info")
	public R info(@PathVariable("tenantId") Integer tenantId){
		TenantEntity tenant = tenantService.queryObject(tenantId);
		
		return R.ok().put("tenant", tenant);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("tenant:save")
	public R save(@RequestBody TenantEntity tenant){
		tenantService.save(tenant);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("tenant:update")
	public R update(@RequestBody TenantEntity tenant){
		tenantService.update(tenant);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("tenant:delete")
	public R delete(@RequestBody Integer[] tenantIds){
		if(ArrayUtils.contains(tenantIds, 1)){
			return R.error("系统租户不能删除");
		}
		if(ArrayUtils.contains(tenantIds, getTenantId())){
			return R.error("当前租户不能删除");
		}
		
		tenantService.deleteBatch(tenantIds);
		
		return R.ok();
	}
	
}
