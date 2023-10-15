package org.jeecg.modules.yydmh.mhWenboKnowledge.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.yydmh.mhWenboKnowledge.entity.MhWenboKnowledge;
import org.jeecg.modules.yydmh.mhWenboKnowledge.service.IMhWenboKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 文博知识讲堂
 * @Author: jeecg-boot
 * @Date:   2021-11-13
 * @Version: V1.0
 */
@Api(tags="文博知识讲堂")
@RestController
@RequestMapping("/mhWenboKnowledge/mhWenboKnowledge")
@Slf4j
public class MhWenboKnowledgeController extends JeecgController<MhWenboKnowledge, IMhWenboKnowledgeService> {
	@Autowired
	private IMhWenboKnowledgeService mhWenboKnowledgeService;
	 @org.springframework.beans.factory.annotation.Value(value = "${yydmh.imagePath}")
	 private String imagePath;
	
	/**
	 * 分页列表查询
	 *
	 * @param mhWenboKnowledge
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "文博知识讲堂-分页列表查询")
	@ApiOperation(value="文博知识讲堂-分页列表查询", notes="文博知识讲堂-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MhWenboKnowledge mhWenboKnowledge,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		mhWenboKnowledge.setDelFlag(0);
		QueryWrapper<MhWenboKnowledge> queryWrapper = QueryGenerator.initQueryWrapper(mhWenboKnowledge, req.getParameterMap());
		Page<MhWenboKnowledge> page = new Page<MhWenboKnowledge>(pageNo, pageSize);
		IPage<MhWenboKnowledge> pageList = mhWenboKnowledgeService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	 /**
	  * 分页列表查询
	  *
	  * @param mhWenboKnowledge
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "文博知识讲堂-分页列表查询")
	 @ApiOperation(value="文博知识讲堂-分页列表查询", notes="文博知识讲堂-分页列表查询")
	 @GetMapping(value = "/listByPub")
	 public Result<?> queryPageListByPub(MhWenboKnowledge mhWenboKnowledge,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		 mhWenboKnowledge.setDelFlag(0);
		 mhWenboKnowledge.setStatus(1);
		 QueryWrapper<MhWenboKnowledge> queryWrapper = QueryGenerator.initQueryWrapper(mhWenboKnowledge, req.getParameterMap());
		 Page<MhWenboKnowledge> page = new Page<MhWenboKnowledge>(pageNo, pageSize);
		 IPage<MhWenboKnowledge> pageList = mhWenboKnowledgeService.page(page, queryWrapper);
		 return Result.OK(pageList);
	 }
	 @AutoLog(value = "文博知识讲堂-分页列表查询")
	 @ApiOperation(value="文博知识讲堂-分页列表查询", notes="文博知识讲堂-分页列表查询")
	 @GetMapping(value = "/listByClassify")
	 public Result<?> listByClassify(@RequestParam(name="id",required=true) String id ) {
		 LambdaQueryWrapper<MhWenboKnowledge> queryWrapper = new LambdaQueryWrapper<>();
		 queryWrapper.eq(MhWenboKnowledge::getWenboKnowledgeClassify, id);
		 List<MhWenboKnowledge> mhWenboKnowledgeList  = mhWenboKnowledgeService.list(queryWrapper);
		 for (MhWenboKnowledge mhWenboKnowledge1 : mhWenboKnowledgeList){
		 	mhWenboKnowledge1.setPicture(imagePath+mhWenboKnowledge1.getPicture());
		 }


		 return Result.OK(mhWenboKnowledgeList);
	 }

	
	/**
	 *   添加
	 *
	 * @param mhWenboKnowledge
	 * @return
	 */
	@AutoLog(value = "文博知识讲堂-添加")
	@ApiOperation(value="文博知识讲堂-添加", notes="文博知识讲堂-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MhWenboKnowledge mhWenboKnowledge) {
		mhWenboKnowledge.setStatus(0);
		mhWenboKnowledgeService.save(mhWenboKnowledge);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mhWenboKnowledge
	 * @return
	 */
	@AutoLog(value = "文博知识讲堂-编辑")
	@ApiOperation(value="文博知识讲堂-编辑", notes="文博知识讲堂-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MhWenboKnowledge mhWenboKnowledge) {
		mhWenboKnowledgeService.updateById(mhWenboKnowledge);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "文博知识讲堂-通过id删除")
	@ApiOperation(value="文博知识讲堂-通过id删除", notes="文博知识讲堂-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		MhWenboKnowledge mhWenboKnowledge = mhWenboKnowledgeService.getById(id);
		mhWenboKnowledge.setDelFlag(1);
		mhWenboKnowledgeService.updateById(mhWenboKnowledge);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "文博知识讲堂-批量删除")
	@ApiOperation(value="文博知识讲堂-批量删除", notes="文博知识讲堂-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		for(String id : Arrays.asList(ids.split(","))){
			MhWenboKnowledge mhWenboKnowledge = mhWenboKnowledgeService.getById(id);
			mhWenboKnowledge.setDelFlag(1);
			mhWenboKnowledgeService.updateById(mhWenboKnowledge);
		}
/*		this.mhWenboConsultingService.removeByIds(Arrays.asList(ids.split(",")));*/
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "文博知识讲堂-通过id查询")
	@ApiOperation(value="文博知识讲堂-通过id查询", notes="文博知识讲堂-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MhWenboKnowledge mhWenboKnowledge = mhWenboKnowledgeService.getById(id);
		if(mhWenboKnowledge == null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(mhWenboKnowledge);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mhWenboKnowledge
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MhWenboKnowledge mhWenboKnowledge) {
        return super.exportXls(request, mhWenboKnowledge, MhWenboKnowledge.class, "文博知识讲堂");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, MhWenboKnowledge.class);
    }

	 /**
	  * 通过id发布内容
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "文博知识讲堂-通过id发布内容")
	 @ApiOperation(value="文博知识讲堂-通过id发布内容", notes="文博知识讲堂-通过id发布内容")
	 @GetMapping(value = "/pubContent")
	 public Result<?> pubContent(@RequestParam(name="id",required=true) String id) {
		 mhWenboKnowledgeService.pubContent(id);
		  return Result.OK("发布成功");
	 }


	 /**
	  * 通过id撤回内容
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "文博知识讲堂-通过id撤回内容")
	 @ApiOperation(value="文博知识讲堂-通过id撤回内容", notes="文博知识讲堂-通过id撤回内容")
	 @GetMapping(value = "/backContent")
	 public Result<?> backContent(@RequestParam(name="id",required=true) String id) {
		 mhWenboKnowledgeService.backContent(id);
		 return Result.OK("撤回成功");
	 }

}
