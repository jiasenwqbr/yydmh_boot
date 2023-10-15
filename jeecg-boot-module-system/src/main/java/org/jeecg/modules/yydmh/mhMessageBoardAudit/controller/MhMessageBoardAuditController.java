package org.jeecg.modules.yydmh.mhMessageBoardAudit.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.yydmh.mhMessageBoardAudit.entity.MhMessageBoardAudit;
import org.jeecg.modules.yydmh.mhMessageBoardAudit.service.IMhMessageBoardAuditService;
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
 * @Description: 留言信息审核
 * @Author: jeecg-boot
 * @Date:   2021-11-14
 * @Version: V1.0
 */
@Api(tags="留言信息审核")
@RestController
@RequestMapping("/mhMessageBoardAudit/mhMessageBoardAudit")
@Slf4j
public class MhMessageBoardAuditController extends JeecgController<MhMessageBoardAudit, IMhMessageBoardAuditService> {
	@Autowired
	private IMhMessageBoardAuditService mhMessageBoardAuditService;
	
	/**
	 * 分页列表查询
	 *
	 * @param mhMessageBoardAudit
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "留言信息审核-分页列表查询")
	@ApiOperation(value="留言信息审核-分页列表查询", notes="留言信息审核-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MhMessageBoardAudit mhMessageBoardAudit,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		mhMessageBoardAudit.setDelFlag(0);
		QueryWrapper<MhMessageBoardAudit> queryWrapper = QueryGenerator.initQueryWrapper(mhMessageBoardAudit, req.getParameterMap());
		Page<MhMessageBoardAudit> page = new Page<MhMessageBoardAudit>(pageNo, pageSize);
		IPage<MhMessageBoardAudit> pageList = mhMessageBoardAuditService.page(page, queryWrapper);
		return Result.OK(pageList);

	}


	 /**
	  * 分页列表查询-小程序
	  *
	  * @param mhMessageBoardAudit
	  * @param pageNo
	  * @param pageSize
	  * @param req
	  * @return
	  */
	 @AutoLog(value = "留言信息审核-分页列表查询")
	 @ApiOperation(value="留言信息审核-分页列表查询", notes="留言信息审核-分页列表查询")
	 @GetMapping(value = "/listMiniProgram")
	 public Result<?> queryPageListMiniProgram(MhMessageBoardAudit mhMessageBoardAudit,
									HttpServletRequest req) {
		 List<Map<String, Object>> pageList =  mhMessageBoardAuditService.queryCommentList(mhMessageBoardAudit);
		 return Result.OK(pageList);
	 }
	
	/**
	 *   添加
	 *
	 * @param mhMessageBoardAudit
	 * @return
	 */
	@AutoLog(value = "留言信息审核-添加")
	@ApiOperation(value="留言信息审核-添加", notes="留言信息审核-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MhMessageBoardAudit mhMessageBoardAudit) {
		mhMessageBoardAudit.setDelFlag(0);
		mhMessageBoardAudit.setLeaveWordTime(new Date());
		mhMessageBoardAudit.setStatus(0);
		mhMessageBoardAuditService.save(mhMessageBoardAudit);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mhMessageBoardAudit
	 * @return
	 */
	@AutoLog(value = "留言信息审核-编辑")
	@ApiOperation(value="留言信息审核-编辑", notes="留言信息审核-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody MhMessageBoardAudit mhMessageBoardAudit) {
		mhMessageBoardAudit.setStatus(mhMessageBoardAudit.getAuditStatus());
		mhMessageBoardAudit.setAuditTime(new Date());
		mhMessageBoardAuditService.updateById(mhMessageBoardAudit);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "留言信息审核-通过id删除")
	@ApiOperation(value="留言信息审核-通过id删除", notes="留言信息审核-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		MhMessageBoardAudit mhMessageBoardAudit = mhMessageBoardAuditService.getById(id);
		mhMessageBoardAudit.setDelFlag(1);
		mhMessageBoardAuditService.updateById(mhMessageBoardAudit);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "留言信息审核-批量删除")
	@ApiOperation(value="留言信息审核-批量删除", notes="留言信息审核-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		for(String id :Arrays.asList(ids.split(","))){
			MhMessageBoardAudit mhMessageBoardAudit = mhMessageBoardAuditService.getById(id);
			mhMessageBoardAudit.setDelFlag(1);
			mhMessageBoardAuditService.updateById(mhMessageBoardAudit);
		}
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "留言信息审核-通过id查询")
	@ApiOperation(value="留言信息审核-通过id查询", notes="留言信息审核-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MhMessageBoardAudit mhMessageBoardAudit = mhMessageBoardAuditService.getById(id);
		if(mhMessageBoardAudit==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(mhMessageBoardAudit);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mhMessageBoardAudit
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MhMessageBoardAudit mhMessageBoardAudit) {
        return super.exportXls(request, mhMessageBoardAudit, MhMessageBoardAudit.class, "留言信息审核");
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
        return super.importExcel(request, response, MhMessageBoardAudit.class);
    }

}
