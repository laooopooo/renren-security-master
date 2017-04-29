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

import io.renren.personel.entity.SubjectEntity;
import io.renren.personel.service.SubjectService;
import io.renren.utils.PageUtils;
import io.renren.utils.Query;
import io.renren.utils.R;


/**
 * 
 * 
 * @author chenyuliao
 * @email 949395746@qq.com
 * @date 2017-04-29 09:43:34
 */
@RestController
@RequestMapping("subject")
public class SubjectController {
	@Autowired
	private SubjectService subjectService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("subject:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<SubjectEntity> subjectList = subjectService.queryList(query);
		int total = subjectService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(subjectList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{subjectId}")
	@RequiresPermissions("subject:info")
	public R info(@PathVariable("subjectId") Integer subjectId){
		SubjectEntity subject = subjectService.queryObject(subjectId);
		
		return R.ok().put("subject", subject);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("subject:save")
	public R save(@RequestBody SubjectEntity subject){
		subjectService.save(subject);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("subject:update")
	public R update(@RequestBody SubjectEntity subject){
		subjectService.update(subject);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("subject:delete")
	public R delete(@RequestBody Integer[] subjectIds){
		subjectService.deleteBatch(subjectIds);
		
		return R.ok();
	}
	
}
