package org.jeecg.modules.yydmh.mhHaveBuyRelics.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.system.service.ISysDictService;
import org.jeecg.modules.yydmh.mhBlindBoxInfoVindicate.entity.MhBlindBoxInfoVindicate;
import org.jeecg.modules.yydmh.mhBlindBoxInfoVindicate.service.IMhBlindBoxInfoVindicateService;
import org.jeecg.modules.yydmh.mhHaveBuyRelics.entity.MhHaveBuyRelics;
import org.jeecg.modules.yydmh.mhHaveBuyRelics.service.IMhHaveBuyRelicsService;
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
 * @Description: 已买文物
 * @Author: jeecg-boot
 * @Date:   2021-11-14
 * @Version: V1.0
 */
@Api(tags="已买文物")
@RestController
@RequestMapping("/mhHaveBuyRelics/mhHaveBuyRelics")
@Slf4j
public class MhHaveBuyRelicsController extends JeecgController<MhHaveBuyRelics, IMhHaveBuyRelicsService> {
	@Autowired
	private IMhHaveBuyRelicsService mhHaveBuyRelicsService;
	 @org.springframework.beans.factory.annotation.Value(value = "${yydmh.imagePath}")
	 private String imagePath;
	 @Autowired
	 private ISysDictService sysDictService;
	 @Autowired
	 private IMhBlindBoxInfoVindicateService mhBlindBoxInfoVindicateService;
	
	/**
	 * 分页列表查询
	 *
	 * @param mhHaveBuyRelics
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "已买文物-分页列表查询")
	@ApiOperation(value="已买文物-分页列表查询", notes="已买文物-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MhHaveBuyRelics mhHaveBuyRelics,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MhHaveBuyRelics> queryWrapper = QueryGenerator.initQueryWrapper(mhHaveBuyRelics, req.getParameterMap());
		Page<MhHaveBuyRelics> page = new Page<MhHaveBuyRelics>(pageNo, pageSize);
		IPage<MhHaveBuyRelics> pageList = mhHaveBuyRelicsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	 @AutoLog(value = "已买文物-我购买的盲盒")
	 @ApiOperation(value="已买文物-我购买的盲盒", notes="已买文物-我购买的盲盒")
	 @GetMapping(value = "/myBuyRelics")
	public Result<?> myBuyRelics(@RequestParam(name="id",required=true) String id){
		 LambdaQueryWrapper<MhHaveBuyRelics> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		 lambdaQueryWrapper.eq(MhHaveBuyRelics::getMhUserId,id);
		 List<MhHaveBuyRelics> mhHaveBuyRelicsList = mhHaveBuyRelicsService.list(lambdaQueryWrapper);
		 for (MhHaveBuyRelics mhHaveBuyRelics: mhHaveBuyRelicsList){
		 	mhHaveBuyRelics.setPicture(imagePath+mhHaveBuyRelics.getPicture());
			 mhHaveBuyRelics.setCulturalRelicClassify(sysDictService.queryDictTextByKey("cultural_relic_classify",mhHaveBuyRelics.getCulturalRelicClassify()));


		 }

		return Result.OK(mhHaveBuyRelicsList);
	}

	@AutoLog(value = "开启盲盒")
	@ApiOperation(value = "开启盲盒")
	@GetMapping(value = "/getBuyDetailByQrcode")
	public Result<?> getBuyDetailByQrcode(@RequestParam(name = "qrcode",required = true) String qrcode){
		LambdaQueryWrapper<MhBlindBoxInfoVindicate> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(MhBlindBoxInfoVindicate::getId,qrcode);
		List<MhBlindBoxInfoVindicate> mhBlindBoxInfoVindicateList = mhBlindBoxInfoVindicateService.list(lambdaQueryWrapper);
		for (MhBlindBoxInfoVindicate mhBlindBoxInfoVindicate: mhBlindBoxInfoVindicateList){
			mhBlindBoxInfoVindicate.setPicture(imagePath+mhBlindBoxInfoVindicate.getPicture());
			mhBlindBoxInfoVindicate.setCulturalRelicClassify(sysDictService.queryDictTextByKey("cultural_relic_classify",mhBlindBoxInfoVindicate.getCulturalRelicClassify()));
		}
		if (mhBlindBoxInfoVindicateList.size()>0){
			return Result.OK(mhBlindBoxInfoVindicateList.get(0));
		}else{
			return Result.error("未发现盲盒信息");
		}
	}

	//
	@AutoLog(value = "添加到我的个人博物馆")
	@ApiOperation(value = "添加到我的个人博物馆")
	@PostMapping(value = "/addToMyMuseum")
	public Result<?> addToMyMuseum(@RequestParam(name = "qrcode",required = true) String qrcode,@RequestParam(name = "userId",required = true) String userId){

		LambdaQueryWrapper<MhBlindBoxInfoVindicate> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		lambdaQueryWrapper.eq(MhBlindBoxInfoVindicate::getId,qrcode);
		List<MhBlindBoxInfoVindicate> mhBlindBoxInfoVindicateList = mhBlindBoxInfoVindicateService.list(lambdaQueryWrapper);
		if (mhBlindBoxInfoVindicateList.size()>0){
			MhBlindBoxInfoVindicate mhBlindBoxInfoVindicate = mhBlindBoxInfoVindicateList.get(0);
			//判断是否添加了
			LambdaQueryWrapper<MhHaveBuyRelics> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
			lambdaQueryWrapper1.eq(MhHaveBuyRelics::getMhUserId,userId);
			lambdaQueryWrapper1.eq(MhHaveBuyRelics::getQrcode,qrcode);
			List<MhHaveBuyRelics>  mhHaveBuyRelicsList = mhHaveBuyRelicsService.list(lambdaQueryWrapper1);
			if (mhHaveBuyRelicsList.size()>0){
				return Result.OK("已经添加");
			}else{
				MhHaveBuyRelics mhHaveBuyRelics = new MhHaveBuyRelics();
				mhHaveBuyRelics.setCulturalRelicClassify(mhBlindBoxInfoVindicate.getCulturalRelicClassify());
				mhHaveBuyRelics.setPicture(mhBlindBoxInfoVindicate.getPicture());
				mhHaveBuyRelics.setDelFlag(mhBlindBoxInfoVindicate.getDelFlag());
				mhHaveBuyRelics.setQrcode(mhBlindBoxInfoVindicate.getId());
				mhHaveBuyRelics.setCreateBy(mhBlindBoxInfoVindicate.getCreateBy());
				mhHaveBuyRelics.setCulturalRelicIntroduce(mhBlindBoxInfoVindicate.getCulturalRelicIntroduce());
				mhHaveBuyRelics.setName(mhBlindBoxInfoVindicate.getName());
				mhHaveBuyRelics.setMhUserId(userId);
				mhHaveBuyRelics.setSysOrgCode(mhBlindBoxInfoVindicate.getSysOrgCode());
				mhHaveBuyRelics.setCreateTime(new Date());
				mhHaveBuyRelics.setQrcode(qrcode);
				mhHaveBuyRelicsService.save(mhHaveBuyRelics);
				return Result.OK("添加完成");
			}

		}else{
			return Result.error("未发现盲盒信息");
		}
	}

	 /**
	  * 通过id查询
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "已买文物-通过id查询")
	 @ApiOperation(value="已买文物-通过id查询", notes="已买文物-通过id查询")
	 @GetMapping(value = "/getBuyDetailByID")
	 public Result<?> getBuyDetailByID(@RequestParam(name="id",required=true) String id) {
		 MhHaveBuyRelics mhHaveBuyRelics = mhHaveBuyRelicsService.getById(id);
		 if(mhHaveBuyRelics==null) {
			 return Result.error("未找到对应数据");
		 }
		 mhHaveBuyRelics.setPicture(imagePath+mhHaveBuyRelics.getPicture());
		 mhHaveBuyRelics.setCulturalRelicClassify(sysDictService.queryDictTextByKey("cultural_relic_classify",mhHaveBuyRelics.getCulturalRelicClassify()));

		 return Result.OK(mhHaveBuyRelics);
	 }
	
	/**
	 *   添加
	 *
	 * @param mhHaveBuyRelics
	 * @return
	 */
	@AutoLog(value = "已买文物-添加")
	@ApiOperation(value="已买文物-添加", notes="已买文物-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MhHaveBuyRelics mhHaveBuyRelics) {
		mhHaveBuyRelicsService.save(mhHaveBuyRelics);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mhHaveBuyRelics
	 * @return
	 */
	@AutoLog(value = "已买文物-编辑")
	@ApiOperation(value="已买文物-编辑", notes="已买文物-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MhHaveBuyRelics mhHaveBuyRelics) {
		mhHaveBuyRelicsService.updateById(mhHaveBuyRelics);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "已买文物-通过id删除")
	@ApiOperation(value="已买文物-通过id删除", notes="已买文物-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		mhHaveBuyRelicsService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "已买文物-批量删除")
	@ApiOperation(value="已买文物-批量删除", notes="已买文物-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.mhHaveBuyRelicsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "已买文物-通过id查询")
	@ApiOperation(value="已买文物-通过id查询", notes="已买文物-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MhHaveBuyRelics mhHaveBuyRelics = mhHaveBuyRelicsService.getById(id);
		if(mhHaveBuyRelics==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(mhHaveBuyRelics);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mhHaveBuyRelics
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MhHaveBuyRelics mhHaveBuyRelics) {
        return super.exportXls(request, mhHaveBuyRelics, MhHaveBuyRelics.class, "已买文物");
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
        return super.importExcel(request, response, MhHaveBuyRelics.class);
    }

}
