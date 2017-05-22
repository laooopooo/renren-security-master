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
import io.renren.course.entity.ClassroomEntity;
import io.renren.course.service.ClassroomService;
import io.renren.utils.Constant;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-18 19:15:21
 */
@RestController
@RequestMapping("classroom")
public class ClassroomController extends AbstractController{
	@Autowired
	private ClassroomService classroomService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("classroom:list")
	public R list(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("tenantId", getTenantId());
		}
		//查询列表数据
        Query query = new Query(params);

		List<ClassroomEntity> classroomList = classroomService.queryList(query);
		int total = classroomService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(classroomList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{classroomId}")
	@RequiresPermissions("classroom:info")
	public R info(@PathVariable("classroomId") Integer classroomId){
		ClassroomEntity classroom = classroomService.queryObject(classroomId);
		
		return R.ok().put("classroom", classroom);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("classroom:save")
	public R save(@RequestBody ClassroomEntity classroom){
		classroom.setTenantId(getTenantId());
		classroomService.save(classroom);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("classroom:update")
	public R update(@RequestBody ClassroomEntity classroom){
		classroomService.update(classroom);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("classroom:delete")
	public R delete(@RequestBody Integer[] classroomIds){
		classroomService.deleteBatch(classroomIds);
		
		return R.ok();
	}
	
	/**
	 * 查看能够排课的教室
	 * @param params
	 * @return
	 */
	@RequestMapping("/canarr")
	@RequiresPermissions("classroom:list")
	public R canarr(@RequestParam Map<String, Object> params){
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("tenantId", getTenantId());
		}
		List<ClassroomEntity> classroomList = classroomService.queryCanArr(params);
		return R.ok().put("page", classroomList);
	}
	
}
