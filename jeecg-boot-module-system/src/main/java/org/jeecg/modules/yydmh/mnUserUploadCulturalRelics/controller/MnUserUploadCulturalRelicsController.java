package org.jeecg.modules.yydmh.mnUserUploadCulturalRelics.controller;

import java.util.Arrays;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.system.service.ISysDictService;
import org.jeecg.modules.system.service.impl.SysDictItemServiceImpl;
import org.jeecg.modules.yydmh.mnUserUploadCulturalRelics.entity.MnUserUploadCulturalRelics;
import org.jeecg.modules.yydmh.mnUserUploadCulturalRelics.service.IMnUserUploadCulturalRelicsService;
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
 * @Description: 用户上传文物
 * @Author: jeecg-boot
 * @Date:   2021-11-14
 * @Version: V1.0
 */
@Api(tags="用户上传文物")
@RestController
@RequestMapping("/mnUserUploadCulturalRelics/mnUserUploadCulturalRelics")
@Slf4j
public class MnUserUploadCulturalRelicsController extends JeecgController<MnUserUploadCulturalRelics, IMnUserUploadCulturalRelicsService> {
	@Autowired
	private IMnUserUploadCulturalRelicsService mnUserUploadCulturalRelicsService;
	@org.springframework.beans.factory.annotation.Value(value = "${yydmh.imagePath}")
	private String imagePath;
	@Autowired
	private ISysDictService sysDictService;
	
	/**
	 * 分页列表查询
	 *
	 * @param mnUserUploadCulturalRelics
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "用户上传文物-分页列表查询")
	@ApiOperation(value="用户上传文物-分页列表查询", notes="用户上传文物-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MnUserUploadCulturalRelics mnUserUploadCulturalRelics,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<MnUserUploadCulturalRelics> queryWrapper = QueryGenerator.initQueryWrapper(mnUserUploadCulturalRelics, req.getParameterMap());
		Page<MnUserUploadCulturalRelics> page = new Page<MnUserUploadCulturalRelics>(pageNo, pageSize);
		IPage<MnUserUploadCulturalRelics> pageList = mnUserUploadCulturalRelicsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	 @AutoLog(value = "用户上传文物-分页列表查询")
	 @ApiOperation(value="用户上传文物-分页列表查询", notes="用户上传文物-分页列表查询")
	 @GetMapping(value = "/myUploadCulturalRelics")
	 public Result<?> queryPageList(@RequestParam(name="id",required=true) String id){
		 LambdaQueryWrapper<MnUserUploadCulturalRelics> lambdaQueryWrapper = new LambdaQueryWrapper<>();
		 lambdaQueryWrapper.eq(MnUserUploadCulturalRelics::getMhUserId,id);
		 List<MnUserUploadCulturalRelics> mnUserUploadCulturalRelicsList = mnUserUploadCulturalRelicsService.list(lambdaQueryWrapper);
		 for(MnUserUploadCulturalRelics mnUserUploadCulturalRelics : mnUserUploadCulturalRelicsList){
		 	String pictures = mnUserUploadCulturalRelics.getPicture();
		 	String picture = "";
		 	if (pictures!=null){
		 		String[] s = pictures.split(",");
				picture = s[0];
			}
		 	mnUserUploadCulturalRelics.setPicture(imagePath+picture);
		 	mnUserUploadCulturalRelics.setCulturalRelicClassify(sysDictService.queryDictTextByKey("cultural_relic_classify",mnUserUploadCulturalRelics.getCulturalRelicClassify()));
		 }
	 	return Result.OK(mnUserUploadCulturalRelicsList);
	 }

	 @AutoLog(value = "用户上传文物-通过id查询")
	 @ApiOperation(value="用户上传文物-通过id查询", notes="用户上传文物-通过id查询")
	 @GetMapping(value = "/getUploadDetailByID")
	 public Result<?> getUploadDetailByID(@RequestParam(name="id",required=true) String id) {
		 MnUserUploadCulturalRelics mnUserUploadCulturalRelics = mnUserUploadCulturalRelicsService.getById(id);
		 if(mnUserUploadCulturalRelics==null) {
			 return Result.error("未找到对应数据");
		 }
		// mnUserUploadCulturalRelics.setPicture(imagePath+mnUserUploadCulturalRelics.getPicture());
		 mnUserUploadCulturalRelics.setCulturalRelicClassify(sysDictService.queryDictTextByKey("cultural_relic_classify",mnUserUploadCulturalRelics.getCulturalRelicClassify()));
		 return Result.OK(mnUserUploadCulturalRelics);
	 }
	 @AutoLog(value = "用户上传文物-添加")
	 @ApiOperation(value="用户上传文物-添加", notes="用户上传文物-添加")
	 @PostMapping(value = "/addCulturalRelics")
	 public Result<?> addCulturalRelics(@RequestBody MnUserUploadCulturalRelics mnUserUploadCulturalRelics) {
		 mnUserUploadCulturalRelicsService.save(mnUserUploadCulturalRelics);
		 return Result.OK("添加成功！");
	 }


	/**
	 *   添加
	 *
	 * @param mnUserUploadCulturalRelics
	 * @return
	 */
	@AutoLog(value = "用户上传文物-添加")
	@ApiOperation(value="用户上传文物-添加", notes="用户上传文物-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MnUserUploadCulturalRelics mnUserUploadCulturalRelics) {
		mnUserUploadCulturalRelicsService.save(mnUserUploadCulturalRelics);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mnUserUploadCulturalRelics
	 * @return
	 */
	@AutoLog(value = "用户上传文物-编辑")
	@ApiOperation(value="用户上传文物-编辑", notes="用户上传文物-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MnUserUploadCulturalRelics mnUserUploadCulturalRelics) {
		mnUserUploadCulturalRelicsService.updateById(mnUserUploadCulturalRelics);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "用户上传文物-通过id删除")
	@ApiOperation(value="用户上传文物-通过id删除", notes="用户上传文物-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		mnUserUploadCulturalRelicsService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "用户上传文物-批量删除")
	@ApiOperation(value="用户上传文物-批量删除", notes="用户上传文物-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.mnUserUploadCulturalRelicsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "用户上传文物-通过id查询")
	@ApiOperation(value="用户上传文物-通过id查询", notes="用户上传文物-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MnUserUploadCulturalRelics mnUserUploadCulturalRelics = mnUserUploadCulturalRelicsService.getById(id);
		if(mnUserUploadCulturalRelics==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(mnUserUploadCulturalRelics);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mnUserUploadCulturalRelics
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MnUserUploadCulturalRelics mnUserUploadCulturalRelics) {
        return super.exportXls(request, mnUserUploadCulturalRelics, MnUserUploadCulturalRelics.class, "用户上传文物");
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
        return super.importExcel(request, response, MnUserUploadCulturalRelics.class);
    }

}
