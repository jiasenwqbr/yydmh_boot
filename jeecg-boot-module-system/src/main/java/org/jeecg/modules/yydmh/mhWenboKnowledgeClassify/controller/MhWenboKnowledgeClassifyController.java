package org.jeecg.modules.yydmh.mhWenboKnowledgeClassify.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.yydmh.mhWenboKnowledgeClassify.entity.MhWenboKnowledgeClassify;
import org.jeecg.modules.yydmh.mhWenboKnowledgeClassify.service.IMhWenboKnowledgeClassifyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 文博知识讲堂分类
 * @Author: jeecg-boot
 * @Date:   2021-11-23
 * @Version: V1.0
 */
@Api(tags="文博知识讲堂分类")
@RestController
@RequestMapping("/mhWenboKnowledgeClassify/mhWenboKnowledgeClassify")
@Slf4j
public class MhWenboKnowledgeClassifyController extends JeecgController<MhWenboKnowledgeClassify, IMhWenboKnowledgeClassifyService> {
	@Autowired
	private IMhWenboKnowledgeClassifyService mhWenboKnowledgeClassifyService;
	 @org.springframework.beans.factory.annotation.Value(value = "${yydmh.imagePath}")
	 private String imagePath;
	
	/**
	 * 分页列表查询
	 *
	 * @param mhWenboKnowledgeClassify
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "文博知识讲堂分类-分页列表查询")
	@ApiOperation(value="文博知识讲堂分类-分页列表查询", notes="文博知识讲堂分类-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MhWenboKnowledgeClassify mhWenboKnowledgeClassify,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		mhWenboKnowledgeClassify.setDelFlag(0);
		QueryWrapper<MhWenboKnowledgeClassify> queryWrapper = QueryGenerator.initQueryWrapper(mhWenboKnowledgeClassify, req.getParameterMap());
		Page<MhWenboKnowledgeClassify> page = new Page<MhWenboKnowledgeClassify>(pageNo, pageSize);
		IPage<MhWenboKnowledgeClassify> pageList = mhWenboKnowledgeClassifyService.page(page, queryWrapper);
		for (MhWenboKnowledgeClassify mhWenboKnowledgeClassify1:pageList.getRecords()){
			mhWenboKnowledgeClassify1.setPicture(imagePath+mhWenboKnowledgeClassify1.getPicture());
		}
		return Result.OK(pageList);
	}
	 @AutoLog(value = "文博知识讲堂分类-分页列表查询")
	 @ApiOperation(value="文博知识讲堂分类-分页列表查询", notes="文博知识讲堂分类-分页列表查询")
	 @GetMapping(value = "/listAll")
	 public Result<?> listAll (HttpServletRequest req){
		List<MhWenboKnowledgeClassify> mhWenboKnowledgeClassifyList = mhWenboKnowledgeClassifyService.list();
		for (MhWenboKnowledgeClassify mhWenboKnowledgeClassify:mhWenboKnowledgeClassifyList){
			mhWenboKnowledgeClassify.setPicture(imagePath+mhWenboKnowledgeClassify.getPicture());
		}
		return Result.OK(mhWenboKnowledgeClassifyList);
	 }
	
	/**
	 *   添加
	 *
	 * @param mhWenboKnowledgeClassify
	 * @return
	 */
	@AutoLog(value = "文博知识讲堂分类-添加")
	@ApiOperation(value="文博知识讲堂分类-添加", notes="文博知识讲堂分类-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MhWenboKnowledgeClassify mhWenboKnowledgeClassify) {
		mhWenboKnowledgeClassify.setDelFlag(0);
		mhWenboKnowledgeClassifyService.save(mhWenboKnowledgeClassify);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mhWenboKnowledgeClassify
	 * @return
	 */
	@AutoLog(value = "文博知识讲堂分类-编辑")
	@ApiOperation(value="文博知识讲堂分类-编辑", notes="文博知识讲堂分类-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MhWenboKnowledgeClassify mhWenboKnowledgeClassify) {
		mhWenboKnowledgeClassifyService.updateById(mhWenboKnowledgeClassify);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "文博知识讲堂分类-通过id删除")
	@ApiOperation(value="文博知识讲堂分类-通过id删除", notes="文博知识讲堂分类-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		MhWenboKnowledgeClassify mhWenboKnowledgeClassify = mhWenboKnowledgeClassifyService.getById(id);
		mhWenboKnowledgeClassify.setDelFlag(1);
		mhWenboKnowledgeClassifyService.updateById(mhWenboKnowledgeClassify);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "文博知识讲堂分类-批量删除")
	@ApiOperation(value="文博知识讲堂分类-批量删除", notes="文博知识讲堂分类-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		for(String id : Arrays.asList(ids.split(","))){
			MhWenboKnowledgeClassify mhWenboKnowledgeClassify = mhWenboKnowledgeClassifyService.getById(id);
			mhWenboKnowledgeClassify.setDelFlag(1);
			mhWenboKnowledgeClassifyService.updateById(mhWenboKnowledgeClassify);
		}
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "文博知识讲堂分类-通过id查询")
	@ApiOperation(value="文博知识讲堂分类-通过id查询", notes="文博知识讲堂分类-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MhWenboKnowledgeClassify mhWenboKnowledgeClassify = mhWenboKnowledgeClassifyService.getById(id);
		if(mhWenboKnowledgeClassify==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(mhWenboKnowledgeClassify);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mhWenboKnowledgeClassify
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MhWenboKnowledgeClassify mhWenboKnowledgeClassify) {
        return super.exportXls(request, mhWenboKnowledgeClassify, MhWenboKnowledgeClassify.class, "文博知识讲堂分类");
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
        return super.importExcel(request, response, MhWenboKnowledgeClassify.class);
    }

}
