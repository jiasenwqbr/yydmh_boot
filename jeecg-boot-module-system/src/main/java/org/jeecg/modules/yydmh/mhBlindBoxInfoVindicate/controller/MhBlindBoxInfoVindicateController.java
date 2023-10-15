package org.jeecg.modules.yydmh.mhBlindBoxInfoVindicate.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.encryption.AesEncryptUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.system.controller.CommonController;
import org.jeecg.modules.system.service.ISysDictService;
import org.jeecg.modules.yydmh.mhBlindBoxInfoVindicate.entity.MhBlindBoxInfoVindicate;
import org.jeecg.modules.yydmh.mhBlindBoxInfoVindicate.service.IMhBlindBoxInfoVindicateService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.modules.yydmh.mhHaveBuyRelics.entity.MhHaveBuyRelics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 盲盒信息维护
 * @Author: jeecg-boot
 * @Date:   2021-11-14
 * @Version: V1.0
 */
@Api(tags="盲盒信息维护")
@RestController
@RequestMapping("/mhBlindBoxInfoVindicate/mhBlindBoxInfoVindicate")
@Slf4j
public class MhBlindBoxInfoVindicateController extends JeecgController<MhBlindBoxInfoVindicate, IMhBlindBoxInfoVindicateService> {
	@Autowired
	private IMhBlindBoxInfoVindicateService mhBlindBoxInfoVindicateService;

	@Autowired
	private CommonController commonController;

	 @org.springframework.beans.factory.annotation.Value(value = "${yydmh.imagePath}")
	 private String imagePath;
	 @Autowired
	 private ISysDictService sysDictService;
	
	/**
	 * 分页列表查询
	 *
	 * @param mhBlindBoxInfoVindicate
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "盲盒信息维护-分页列表查询")
	@ApiOperation(value="盲盒信息维护-分页列表查询", notes="盲盒信息维护-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MhBlindBoxInfoVindicate mhBlindBoxInfoVindicate,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		mhBlindBoxInfoVindicate.setDelFlag(0);
		QueryWrapper<MhBlindBoxInfoVindicate> queryWrapper = QueryGenerator.initQueryWrapper(mhBlindBoxInfoVindicate, req.getParameterMap());
		Page<MhBlindBoxInfoVindicate> page = new Page<MhBlindBoxInfoVindicate>(pageNo, pageSize);
		IPage<MhBlindBoxInfoVindicate> pageList = mhBlindBoxInfoVindicateService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	 @AutoLog(value = "开启盲盒")
	 @ApiOperation(value = "开启盲盒")
	 @GetMapping(value = "/getBuyDetailByQrcode")
	 public Result<?> getBuyDetailByQrcode(@RequestParam(name = "qrcode",required = true) String qrcode){
		 LambdaQueryWrapper<MhBlindBoxInfoVindicate> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		 lambdaQueryWrapper.eq(MhBlindBoxInfoVindicate::getId,qrcode);
		 List<MhBlindBoxInfoVindicate> mhHaveBuyRelicsList = mhBlindBoxInfoVindicateService.list(lambdaQueryWrapper);
		 for (MhBlindBoxInfoVindicate mhBlindBoxInfoVindicate: mhHaveBuyRelicsList){
			 mhBlindBoxInfoVindicate.setPicture(imagePath+mhBlindBoxInfoVindicate.getPicture());
			 mhBlindBoxInfoVindicate.setCulturalRelicClassify(sysDictService.queryDictTextByKey("cultural_relic_classify",mhBlindBoxInfoVindicate.getCulturalRelicClassify()));
		 }
		 if (mhHaveBuyRelicsList.size()>0){
			 return Result.OK(mhHaveBuyRelicsList.get(0));
		 }else{
			 return Result.error("未发现盲盒信息");
		 }
	 }

	
	/**
	 *   添加
	 *
	 * @param mhBlindBoxInfoVindicate
	 * @return
	 */
	@AutoLog(value = "盲盒信息维护-添加")
	@ApiOperation(value="盲盒信息维护-添加", notes="盲盒信息维护-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MhBlindBoxInfoVindicate mhBlindBoxInfoVindicate) {
		mhBlindBoxInfoVindicate.setDelFlag(0);
		//生成二维码
		ClassPathResource classPathResource = new ClassPathResource("qrcode/logo.png");
		QrConfig qrConfig = QrConfig.create();
		qrConfig.setImg(classPathResource.getFile());
        mhBlindBoxInfoVindicateService.save(mhBlindBoxInfoVindicate);
		String id  = mhBlindBoxInfoVindicate.getId();
		try {
			byte[] qrCodeByte  = QrCodeUtil. generatePng(id, qrConfig);
			MultipartFile file = new  MockMultipartFile(mhBlindBoxInfoVindicate.getName()+".png",mhBlindBoxInfoVindicate.getName()+".png",ImgUtil.IMAGE_TYPE_PNG,qrCodeByte);
			String qrCodePath =  commonController.uploadQrCode("qrcode",file);
			if(oConvertUtils.isNotEmpty(qrCodePath)){
				mhBlindBoxInfoVindicate.setQrcode(qrCodePath);
			}else{
				Result.error("二维码生成失败！");
				mhBlindBoxInfoVindicateService.removeById(id);
			}
			mhBlindBoxInfoVindicateService.updateById(mhBlindBoxInfoVindicate);
		}catch (Exception e){
			e.printStackTrace();
			mhBlindBoxInfoVindicateService.removeById(id);
			Result.error("二维码生成失败！");
		}
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mhBlindBoxInfoVindicate
	 * @return
	 */
	@AutoLog(value = "盲盒信息维护-编辑")
	@ApiOperation(value="盲盒信息维护-编辑", notes="盲盒信息维护-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MhBlindBoxInfoVindicate mhBlindBoxInfoVindicate) {
		mhBlindBoxInfoVindicateService.updateById(mhBlindBoxInfoVindicate);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "盲盒信息维护-通过id删除")
	@ApiOperation(value="盲盒信息维护-通过id删除", notes="盲盒信息维护-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		MhBlindBoxInfoVindicate mhBlindBoxInfoVindicate = mhBlindBoxInfoVindicateService.getById(id);
		mhBlindBoxInfoVindicate.setDelFlag(1);
		mhBlindBoxInfoVindicateService.updateById(mhBlindBoxInfoVindicate);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "盲盒信息维护-批量删除")
	@ApiOperation(value="盲盒信息维护-批量删除", notes="盲盒信息维护-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		for (String id : Arrays.asList(ids.split(","))){
			MhBlindBoxInfoVindicate mhBlindBoxInfoVindicate = mhBlindBoxInfoVindicateService.getById(id);
			mhBlindBoxInfoVindicate.setDelFlag(1);
			mhBlindBoxInfoVindicateService.updateById(mhBlindBoxInfoVindicate);
		}
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "盲盒信息维护-通过id查询")
	@ApiOperation(value="盲盒信息维护-通过id查询", notes="盲盒信息维护-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MhBlindBoxInfoVindicate mhBlindBoxInfoVindicate = mhBlindBoxInfoVindicateService.getById(id);
		if(mhBlindBoxInfoVindicate==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(mhBlindBoxInfoVindicate);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mhBlindBoxInfoVindicate
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MhBlindBoxInfoVindicate mhBlindBoxInfoVindicate) {
        return super.exportXls(request, mhBlindBoxInfoVindicate, MhBlindBoxInfoVindicate.class, "盲盒信息维护");
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
        return super.importExcel(request, response, MhBlindBoxInfoVindicate.class);
    }





    public static void main(String[] args){
		File file = new File("E:/360MoveData/Users/37543/Desktop/aaa.png");
		String imgPath = "E:/360MoveData/Users/37543/Desktop/1637225428(1).jpg";
		QrConfig qrConfig = QrConfig.create();
		qrConfig.setImg(imgPath);
		try {
			QrCodeUtil.generate("测试测试测试",qrConfig,file);
		}catch (Exception e){
			e.printStackTrace();
		}
    }

}
