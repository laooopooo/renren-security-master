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

import io.renren.course.entity.CourseRecordEntity;
import io.renren.course.service.CourseRecordService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-05-09 18:43:47
 */
@RestController
@RequestMapping("courserecord")
public class CourseRecordController {
	@Autowired
	private CourseRecordService courseRecordService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("courserecord:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<CourseRecordEntity> courseRecordList = courseRecordService.queryList(query);
		int total = courseRecordService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(courseRecordList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{courseRecordId}")
	@RequiresPermissions("courserecord:info")
	public R info(@PathVariable("courseRecordId") Integer courseRecordId){
		CourseRecordEntity courseRecord = courseRecordService.queryObject(courseRecordId);
		
		return R.ok().put("courseRecord", courseRecord);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("courserecord:save")
	public R save(@RequestBody CourseRecordEntity courseRecord){
		return courseRecordService.save(courseRecord);
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("courserecord:update")
	public R update(@RequestBody CourseRecordEntity courseRecord){
		courseRecordService.update(courseRecord);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("courserecord:delete")
	public R delete(@RequestBody Integer[] courseRecordIds){
		courseRecordService.deleteBatch(courseRecordIds);
		return R.ok();
	}
	
	
	
}
