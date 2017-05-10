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

import io.renren.course.entity.CourseEntity;
import io.renren.personel.entity.StudentEntity;
import io.renren.personel.service.StudentService;
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
@RequestMapping("student")
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("student:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<StudentEntity> studentList = studentService.queryList(query);
		int total = studentService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(studentList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{studentId}")
	@RequiresPermissions("student:info")
	public R info(@PathVariable("studentId") Integer studentId){
		StudentEntity student = studentService.queryObject(studentId);
		
		return R.ok().put("student", student);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("student:save")
	public R save(@RequestBody StudentEntity student){
		studentService.save(student);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("student:update")
	public R update(@RequestBody StudentEntity student){
		studentService.update(student);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("student:delete")
	public R delete(@RequestBody Integer[] studentIds){
		studentService.deleteBatch(studentIds);
		
		return R.ok();
	}
	
	@RequestMapping("/courseList")
	@RequiresPermissions("student:list")
	public R courseList(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<CourseEntity> courseEntities = studentService.queryCourseList(query);
		
		PageUtils pageUtil = new PageUtils(courseEntities, 0, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
}
