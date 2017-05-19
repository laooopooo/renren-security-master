package io.renren.course.controller;

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
import io.renren.course.entity.ClasstimeEntity;
import io.renren.course.service.ClasstimeService;
import io.renren.utils.Constant;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-19 16:24:29
 */
@RestController
@RequestMapping("classtime")
public class ClasstimeController extends AbstractController{
	@Autowired
	private ClasstimeService classtimeService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("classtime:list")
	public R list(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("tenantId", getTenantId());
		}
		//查询列表数据
        Query query = new Query(params);

		List<ClasstimeEntity> classtimeList = classtimeService.queryList(query);
		int total = classtimeService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(classtimeList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{classtimeId}")
	@RequiresPermissions("classtime:info")
	public R info(@PathVariable("classtimeId") Integer classtimeId){
		ClasstimeEntity classtime = classtimeService.queryObject(classtimeId);
		
		return R.ok().put("classtime", classtime);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("classtime:save")
	public R save(@RequestBody ClasstimeEntity classtime){
		classtime.setTenantId(getTenantId());
		classtimeService.save(classtime);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("classtime:update")
	public R update(@RequestBody ClasstimeEntity classtime){
		classtimeService.update(classtime);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("classtime:delete")
	public R delete(@RequestBody Integer[] classtimeIds){
		classtimeService.deleteBatch(classtimeIds);
		
		return R.ok();
	}
	
}
