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

import io.renren.personel.entity.StudentClassEntity;
import io.renren.personel.service.StudentClassService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-04-11 09:27:29
 */
@RestController
@RequestMapping("studentclass")
public class StudentClassController {
	@Autowired
	private StudentClassService studentClassService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("studentclass:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<StudentClassEntity> studentClassList = studentClassService.queryList(query);
		int total = studentClassService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(studentClassList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{studentClassId}")
	@RequiresPermissions("studentclass:info")
	public R info(@PathVariable("studentClassId") Integer studentClassId){
		StudentClassEntity studentClass = studentClassService.queryObject(studentClassId);
		
		return R.ok().put("studentClass", studentClass);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("studentclass:save")
	public R save(@RequestBody StudentClassEntity studentClass){
		studentClassService.save(studentClass);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("studentclass:update")
	public R update(@RequestBody StudentClassEntity studentClass){
		studentClassService.update(studentClass);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("studentclass:delete")
	public R delete(@RequestBody Integer[] studentClassIds){
		studentClassService.deleteBatch(studentClassIds);
		
		return R.ok();
	}
	
}
