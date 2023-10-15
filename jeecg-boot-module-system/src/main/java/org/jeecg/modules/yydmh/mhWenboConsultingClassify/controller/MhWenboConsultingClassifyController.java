package org.jeecg.modules.yydmh.mhWenboConsultingClassify.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.yydmh.mhWenboConsultingClassify.entity.MhWenboConsultingClassify;
import org.jeecg.modules.yydmh.mhWenboConsultingClassify.service.IMhWenboConsultingClassifyService;
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
 * @Description: 文博咨询分类
 * @Author: jeecg-boot
 * @Date:   2021-11-23
 * @Version: V1.0
 */
@Api(tags="文博咨询分类")
@RestController
@RequestMapping("/mhWenboConsultingClassify/mhWenboConsultingClassify")
@Slf4j
public class MhWenboConsultingClassifyController extends JeecgController<MhWenboConsultingClassify, IMhWenboConsultingClassifyService> {
	@Autowired
	private IMhWenboConsultingClassifyService mhWenboConsultingClassifyService;
	
	/**
	 * 分页列表查询
	 *
	 * @param mhWenboConsultingClassify
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "文博咨询分类-分页列表查询")
	@ApiOperation(value="文博咨询分类-分页列表查询", notes="文博咨询分类-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MhWenboConsultingClassify mhWenboConsultingClassify,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		mhWenboConsultingClassify.setDelFlag(0);
		QueryWrapper<MhWenboConsultingClassify> queryWrapper = QueryGenerator.initQueryWrapper(mhWenboConsultingClassify, req.getParameterMap());
		Page<MhWenboConsultingClassify> page = new Page<MhWenboConsultingClassify>(pageNo, pageSize);
		IPage<MhWenboConsultingClassify> pageList = mhWenboConsultingClassifyService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param mhWenboConsultingClassify
	 * @return
	 */
	@AutoLog(value = "文博咨询分类-添加")
	@ApiOperation(value="文博咨询分类-添加", notes="文博咨询分类-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MhWenboConsultingClassify mhWenboConsultingClassify) {
		mhWenboConsultingClassify.setDelFlag(0);
		mhWenboConsultingClassifyService.save(mhWenboConsultingClassify);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mhWenboConsultingClassify
	 * @return
	 */
	@AutoLog(value = "文博咨询分类-编辑")
	@ApiOperation(value="文博咨询分类-编辑", notes="文博咨询分类-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MhWenboConsultingClassify mhWenboConsultingClassify) {
		mhWenboConsultingClassifyService.updateById(mhWenboConsultingClassify);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "文博咨询分类-通过id删除")
	@ApiOperation(value="文博咨询分类-通过id删除", notes="文博咨询分类-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		MhWenboConsultingClassify mhWenboConsultingClassify = mhWenboConsultingClassifyService.getById(id);
		mhWenboConsultingClassify.setDelFlag(1);
		mhWenboConsultingClassifyService.updateById(mhWenboConsultingClassify);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "文博咨询分类-批量删除")
	@ApiOperation(value="文博咨询分类-批量删除", notes="文博咨询分类-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		for(String id : Arrays.asList(ids.split(","))){
			MhWenboConsultingClassify mhWenboConsultingClassify = mhWenboConsultingClassifyService.getById(id);
			mhWenboConsultingClassify.setDelFlag(1);
			mhWenboConsultingClassifyService.updateById(mhWenboConsultingClassify);
		}
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "文博咨询分类-通过id查询")
	@ApiOperation(value="文博咨询分类-通过id查询", notes="文博咨询分类-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MhWenboConsultingClassify mhWenboConsultingClassify = mhWenboConsultingClassifyService.getById(id);
		if(mhWenboConsultingClassify==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(mhWenboConsultingClassify);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mhWenboConsultingClassify
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MhWenboConsultingClassify mhWenboConsultingClassify) {
        return super.exportXls(request, mhWenboConsultingClassify, MhWenboConsultingClassify.class, "文博咨询分类");
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
        return super.importExcel(request, response, MhWenboConsultingClassify.class);
    }

}
