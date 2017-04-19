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

import io.renren.personel.entity.ClassEntity;
import io.renren.personel.service.ClassService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-04-11 09:27:28
 */
@RestController
@RequestMapping("class")
public class ClassController {
	@Autowired
	private ClassService classService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("class:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ClassEntity> classList = classService.queryList(query);
		int total = classService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(classList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{classId}")
	@RequiresPermissions("class:info")
	public R info(@PathVariable("classId") Integer classId){
		ClassEntity myclass = classService.queryObject(classId);
		
		return R.ok().put("class", myclass);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("class:save")
	public R save(@RequestBody ClassEntity myclass){
		classService.save(myclass);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("class:update")
	public R update(@RequestBody ClassEntity myclass){
		classService.update(myclass);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("class:delete")
	public R delete(@RequestBody Integer[] classIds){
		classService.deleteBatch(classIds);
		
		return R.ok();
	}
	
}
