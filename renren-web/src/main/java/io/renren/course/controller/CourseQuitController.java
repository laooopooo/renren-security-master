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

import io.renren.course.entity.CourseQuitEntity;
import io.renren.course.service.CourseQuitService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-11 23:02:53
 */
@RestController
@RequestMapping("coursequit")
public class CourseQuitController {
	@Autowired
	private CourseQuitService courseQuitService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("coursequit:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<CourseQuitEntity> courseQuitList = courseQuitService.queryList(query);
		int total = courseQuitService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(courseQuitList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{quitCourseId}")
	@RequiresPermissions("coursequit:info")
	public R info(@PathVariable("quitCourseId") Integer quitCourseId){
		CourseQuitEntity courseQuit = courseQuitService.queryObject(quitCourseId);
		
		return R.ok().put("courseQuit", courseQuit);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("coursequit:save")
	public R save(@RequestBody CourseQuitEntity courseQuit){
		courseQuitService.save(courseQuit);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("coursequit:update")
	public R update(@RequestBody CourseQuitEntity courseQuit){
		courseQuitService.update(courseQuit);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("coursequit:delete")
	public R delete(@RequestBody Integer[] quitCourseIds){
		courseQuitService.deleteBatch(quitCourseIds);
		
		return R.ok();
	}
	
}
