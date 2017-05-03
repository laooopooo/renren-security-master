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

import io.renren.course.entity.CourseSelectEntity;
import io.renren.course.service.CourseSelectService;
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
@RequestMapping("courseselect")
public class CourseSelectController {
	@Autowired
	private CourseSelectService courseSelectService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("courseselect:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<CourseSelectEntity> courseSelectList = courseSelectService.queryList(query);
		int total = courseSelectService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(courseSelectList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{courseSelectId}")
	@RequiresPermissions("courseselect:info")
	public R info(@PathVariable("courseSelectId") Integer courseSelectId){
		CourseSelectEntity courseSelect = courseSelectService.queryObject(courseSelectId);
		
		return R.ok().put("courseSelect", courseSelect);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("courseselect:save")
	public R save(@RequestBody CourseSelectEntity courseSelect){
		courseSelectService.save(courseSelect);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("courseselect:update")
	public R update(@RequestBody CourseSelectEntity courseSelect){
		courseSelectService.update(courseSelect);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("courseselect:delete")
	public R delete(@RequestBody Integer[] courseSelectIds){
		courseSelectService.deleteBatch(courseSelectIds);
		
		return R.ok();
	}
	
}
