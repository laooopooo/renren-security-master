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

import io.renren.course.entity.CourseEntity;
import io.renren.course.service.CourseService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-03 14:22:27
 */
@RestController
@RequestMapping("course")
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("course:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<CourseEntity> courseList = courseService.queryList(query);
		int total = courseService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(courseList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{courseId}")
	@RequiresPermissions("course:info")
	public R info(@PathVariable("courseId") Integer courseId){
		CourseEntity course = courseService.queryObject(courseId);
		return R.ok().put("course", course);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("course:save")
	public R save(@RequestBody CourseEntity course){
		if (course.getActualPrice()>course.getOriginalPrice()) {
			return R.error("现价不能比原价高！");
		}
		courseService.save(course);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("course:update")
	public R update(@RequestBody CourseEntity course){
		if (course.getActualPrice()>course.getOriginalPrice()) {
			return R.error("现价不能比原价高！");
		}
		courseService.update(course);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("course:delete")
	public R delete(@RequestBody Integer[] courseIds){
		courseService.deleteBatch(courseIds);
		return R.ok();
	}
	/**
	 * 退课
	 */
	
	
	
}
