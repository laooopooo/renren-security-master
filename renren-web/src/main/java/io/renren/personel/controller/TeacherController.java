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

import io.renren.personel.entity.TeacherEntity;
import io.renren.personel.service.TeacherService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-04-11 13:30:01
 */
@RestController
@RequestMapping("teacher")
public class TeacherController {
	@Autowired
	private TeacherService teacherService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("teacher:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<TeacherEntity> teacherList = teacherService.queryList(query);
		int total = teacherService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(teacherList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{teacherId}")
	@RequiresPermissions("teacher:info")
	public R info(@PathVariable("teacherId") Integer teacherId){
		TeacherEntity teacher = teacherService.queryObject(teacherId);
		
		return R.ok().put("teacher", teacher);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("teacher:save")
	public R save(@RequestBody TeacherEntity teacher){
		teacherService.save(teacher);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("teacher:update")
	public R update(@RequestBody TeacherEntity teacher){
		teacherService.update(teacher);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("teacher:delete")
	public R delete(@RequestBody Integer[] teacherIds){
		teacherService.deleteBatch(teacherIds);
		
		return R.ok();
	}
	
}
