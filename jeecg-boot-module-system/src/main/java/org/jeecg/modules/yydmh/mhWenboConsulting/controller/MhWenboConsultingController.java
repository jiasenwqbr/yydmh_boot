package org.jeecg.modules.yydmh.mhWenboConsulting.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import lombok.Value;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.system.service.ISysDictService;
import org.jeecg.modules.yydmh.mhWenboConsulting.entity.MhWenboConsulting;
import org.jeecg.modules.yydmh.mhWenboConsulting.entity.MhWenboConsultingVO;
import org.jeecg.modules.yydmh.mhWenboConsulting.service.IMhWenboConsultingService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.yydmh.mhWenboConsultingClassify.entity.MhWenboConsultingClassify;
import org.jeecg.modules.yydmh.mhWenboConsultingClassify.service.IMhWenboConsultingClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 文博咨询
 * @Author: jeecg-boot
 * @Date:   2021-11-13
 * @Version: V1.0
 */
@Api(tags="文博咨询")
@RestController
@RequestMapping("/mhWenboConsulting/mhWenboConsulting")
@Slf4j
public class MhWenboConsultingController extends JeecgController<MhWenboConsulting, IMhWenboConsultingService> {
	@Autowired
	private IMhWenboConsultingService mhWenboConsultingService;
	@org.springframework.beans.factory.annotation.Value(value = "${yydmh.imagePath}")
	private String imagePath;
	@Autowired
	private ISysDictService sysDictService;
	@Autowired
	private IMhWenboConsultingClassifyService mhWenboConsultingClassifyService;
	
	/**
	 * 分页列表查询
	 *
	 * @param mhWenboConsulting
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "文博咨询-分页列表查询")
	@ApiOperation(value="文博咨询-分页列表查询", notes="文博咨询-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MhWenboConsulting mhWenboConsulting,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		mhWenboConsulting.setDelFlag(0);
		//mhWenboConsulting.setIsTop("0");
		QueryWrapper<MhWenboConsulting> queryWrapper = QueryGenerator.initQueryWrapper(mhWenboConsulting, req.getParameterMap());
		queryWrapper.orderByDesc("is_top","pub_date");
		Page<MhWenboConsulting> page = new Page<MhWenboConsulting>(pageNo, pageSize);
		IPage<MhWenboConsulting> pageList = mhWenboConsultingService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	@AutoLog("查询文博资讯置顶文章")
	@ApiOperation(value = "查询文博资讯置顶文章",notes = "查询文博资讯置顶")
	@GetMapping(value = "/queryTop")
	public Result<?> queryTop(HttpServletRequest req){
		LambdaQueryWrapper<MhWenboConsulting> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(MhWenboConsulting::getDelFlag,"0");
		lambdaQueryWrapper.eq(MhWenboConsulting::getIsTop,"1");
		lambdaQueryWrapper.orderByDesc(true,MhWenboConsulting::getCreateTime);
		List<MhWenboConsulting> mhWenboConsultingList = mhWenboConsultingService.list(lambdaQueryWrapper);
		for (MhWenboConsulting mhWenboConsulting: mhWenboConsultingList){
			mhWenboConsulting.setPicture(imagePath+mhWenboConsulting.getPicture());
		}
		return Result.OK(mhWenboConsultingList);

	}

	 /**
	  * 分页列表查询
	  *
	  * @param mhWenboConsulting
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "文博咨询-分页列表查询")
	 @ApiOperation(value="文博咨询-分页列表查询", notes="文博咨询-分页列表查询")
	 @GetMapping(value = "/listByPub")
	 public Result<?> queryPageListByPub(MhWenboConsulting mhWenboConsulting,
									@RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
									@RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
									HttpServletRequest req) {
		 mhWenboConsulting.setDelFlag(0);
		 //mhWenboConsulting.setStatus(1);
		 QueryWrapper<MhWenboConsulting> queryWrapper = QueryGenerator.initQueryWrapper(mhWenboConsulting, req.getParameterMap());
		 queryWrapper.orderByDesc("is_top","pub_date");
		 Page<MhWenboConsulting> page = new Page<MhWenboConsulting>(pageNo, pageSize);
		 IPage<MhWenboConsulting> pageList = mhWenboConsultingService.page(page, queryWrapper);
		 for (MhWenboConsulting mhWenboConsulting1:pageList.getRecords()){
			 mhWenboConsulting1.setPicture(imagePath+mhWenboConsulting1.getPicture());
		 }
		 return Result.OK(pageList);
	 }


	 @AutoLog(value = "文博咨询-分页列表查询")
	 @ApiOperation(value="文博咨询-分页列表查询", notes="文博咨询-分页列表查询")
	 @GetMapping(value = "/queryByKeywords")
	 public Result<?> queryByKeywords(@RequestParam(name="keywords",required=true) String keywords,
										 @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
										 @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
										 HttpServletRequest req) {
		 MhWenboConsulting mhWenboConsulting = new MhWenboConsulting();
		 mhWenboConsulting.setDelFlag(0);
		 //mhWenboConsulting.setStatus(1);
		 QueryWrapper<MhWenboConsulting> queryWrapper = QueryGenerator.initQueryWrapper(mhWenboConsulting, req.getParameterMap());
		 System.out.println("keywords:"+keywords);
		 queryWrapper.like("title",keywords);
		 queryWrapper.orderByDesc("is_top","pub_date");
		 Page<MhWenboConsulting> page = new Page<MhWenboConsulting>(pageNo, pageSize);
		 IPage<MhWenboConsulting> pageList = mhWenboConsultingService.page(page, queryWrapper);
		 for (MhWenboConsulting mhWenboConsulting1:pageList.getRecords()){
			 mhWenboConsulting1.setPicture(imagePath+mhWenboConsulting1.getPicture());
		 }
		 return Result.OK(pageList);
	 }


	
	/**
	 *   添加
	 *
	 * @param mhWenboConsulting
	 * @return
	 */
	@AutoLog(value = "文博咨询-添加")
	@ApiOperation(value="文博咨询-添加", notes="文博咨询-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MhWenboConsulting mhWenboConsulting) {
		mhWenboConsulting.setStatus(0);
		mhWenboConsultingService.save(mhWenboConsulting);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mhWenboConsulting
	 * @return
	 */
	@AutoLog(value = "文博咨询-编辑")
	@ApiOperation(value="文博咨询-编辑", notes="文博咨询-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MhWenboConsulting mhWenboConsulting) {
		mhWenboConsultingService.updateById(mhWenboConsulting);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "文博咨询-通过id删除")
	@ApiOperation(value="文博咨询-通过id删除", notes="文博咨询-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		MhWenboConsulting mhWenboConsulting = mhWenboConsultingService.getById(id);
		mhWenboConsulting.setDelFlag(1);
		mhWenboConsultingService.updateById(mhWenboConsulting);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "文博咨询-批量删除")
	@ApiOperation(value="文博咨询-批量删除", notes="文博咨询-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		for(String id : Arrays.asList(ids.split(","))){
			MhWenboConsulting mhWenboConsulting = mhWenboConsultingService.getById(id);
			mhWenboConsulting.setDelFlag(1);
			mhWenboConsultingService.updateById(mhWenboConsulting);
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
	@AutoLog(value = "文博咨询-通过id查询")
	@ApiOperation(value="文博咨询-通过id查询", notes="文博咨询-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MhWenboConsulting mhWenboConsulting = mhWenboConsultingService.getById(id);

		if(mhWenboConsulting==null) {
			return Result.error("未找到对应数据");
		}
		MhWenboConsultingClassify mhWenboConsultingClassify = mhWenboConsultingClassifyService.getById(mhWenboConsulting.getWenboConsultingClassify());
		mhWenboConsulting.setPicture(imagePath+mhWenboConsulting.getPicture());
		mhWenboConsulting.setWenboConsultingClassify(mhWenboConsultingClassify.getTitle());


		return Result.OK(mhWenboConsulting);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mhWenboConsulting
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MhWenboConsulting mhWenboConsulting) {
        return super.exportXls(request, mhWenboConsulting, MhWenboConsulting.class, "文博咨询");
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
        return super.importExcel(request, response, MhWenboConsulting.class);
    }

	 /**
	  * 通过id发布内容
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "文博咨询-通过id发布内容")
	 @ApiOperation(value="文博咨询-通过id发布内容", notes="文博咨询-通过id发布内容")
	 @GetMapping(value = "/pubContent")
	 public Result<?> pubContent(@RequestParam(name="id",required=true) String id) {
		  mhWenboConsultingService.pubContent(id);

		  return Result.OK("发布成功");
	 }


	 /**
	  * 通过id撤回内容
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "文博咨询-通过id撤回内容")
	 @ApiOperation(value="文博咨询-通过id撤回内容", notes="文博咨询-通过id撤回内容")
	 @GetMapping(value = "/backContent")
	 public Result<?> backContent(@RequestParam(name="id",required=true) String id) {
		 mhWenboConsultingService.backContent(id);
		 return Result.OK("撤回成功");
	 }
	 //unTopContent
	 @AutoLog("置顶资讯")
	 @ApiOperation(value = "置顶资讯",notes = "置顶资讯")
	 @GetMapping(value = "toTopContent")
	 public Result<?>  toTopContent(@RequestParam(name = "id",required = true) String id){
		 MhWenboConsulting mhWenboConsulting = mhWenboConsultingService.getById(id);
		 mhWenboConsulting.setIsTop("1");
		 mhWenboConsultingService.updateById(mhWenboConsulting);
		 return Result.OK("置顶成功！");
	 }
	 @AutoLog("取消置顶资讯")
	 @ApiOperation(value = "取消置顶资讯",notes = "取消置顶资讯")
	 @GetMapping(value = "unTopContent")
	 public Result<?>  unTopContent(@RequestParam(name = "id",required = true) String id){
		 MhWenboConsulting mhWenboConsulting = mhWenboConsultingService.getById(id);
		 mhWenboConsulting.setIsTop("0");
		 mhWenboConsultingService.updateById(mhWenboConsulting);
		 return Result.OK("取消置顶成功！");
	 }



}
