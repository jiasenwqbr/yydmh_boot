package org.jeecg.modules.yydmh.mhUserRegisterInfo.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.PasswordUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.yydmh.mhUserRegisterInfo.entity.MhUserRegisterInfo;
import org.jeecg.modules.yydmh.mhUserRegisterInfo.service.IMhUserRegisterInfoService;
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
 * @Description: 用户注册信息维护
 * @Author: jeecg-boot
 * @Date:   2021-11-14
 * @Version: V1.0
 */
@Api(tags="用户注册信息维护")
@RestController
@RequestMapping("/mhUserRegisterInfo/mhUserRegisterInfo")
@Slf4j
public class MhUserRegisterInfoController extends JeecgController<MhUserRegisterInfo, IMhUserRegisterInfoService> {
	@Autowired
	private IMhUserRegisterInfoService mhUserRegisterInfoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param mhUserRegisterInfo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "用户注册信息维护-分页列表查询")
	@ApiOperation(value="用户注册信息维护-分页列表查询", notes="用户注册信息维护-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(MhUserRegisterInfo mhUserRegisterInfo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		mhUserRegisterInfo.setDelFlag(0);
		QueryWrapper<MhUserRegisterInfo> queryWrapper = QueryGenerator.initQueryWrapper(mhUserRegisterInfo, req.getParameterMap());
		Page<MhUserRegisterInfo> page = new Page<MhUserRegisterInfo>(pageNo, pageSize);
		IPage<MhUserRegisterInfo> pageList = mhUserRegisterInfoService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param mhUserRegisterInfo
	 * @return
	 */
	@AutoLog(value = "用户注册信息维护-添加")
	@ApiOperation(value="用户注册信息维护-添加", notes="用户注册信息维护-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody MhUserRegisterInfo mhUserRegisterInfo) {
		String phone = mhUserRegisterInfo.getPhone();
		MhUserRegisterInfo mhUserRegisterInfo2 = mhUserRegisterInfoService.getUserByPhone(phone);
		if (mhUserRegisterInfo2 != null) {
			return Result.error("该手机号已注册");
		}
		mhUserRegisterInfo.setDelFlag(0);
		mhUserRegisterInfo.setStatus(1);
		String salt = oConvertUtils.randomGen(8);
		mhUserRegisterInfo.setSalt(salt);
		String passwordEncode = PasswordUtil.encrypt(mhUserRegisterInfo.getPhone(), mhUserRegisterInfo.getPassword(), salt);
		mhUserRegisterInfo.setPassword(passwordEncode);
		mhUserRegisterInfoService.save(mhUserRegisterInfo);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param mhUserRegisterInfo
	 * @return
	 */
	@AutoLog(value = "用户注册信息维护-编辑")
	@ApiOperation(value="用户注册信息维护-编辑", notes="用户注册信息维护-编辑")
	@PostMapping(value = "/edit")
	public Result<?> edit(@RequestBody MhUserRegisterInfo mhUserRegisterInfo) {
		mhUserRegisterInfoService.updateById(mhUserRegisterInfo);
		return Result.OK(mhUserRegisterInfo);
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "用户注册信息维护-通过id删除")
	@ApiOperation(value="用户注册信息维护-通过id删除", notes="用户注册信息维护-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		MhUserRegisterInfo mhUserRegisterInfo = mhUserRegisterInfoService.getById(id);
		mhUserRegisterInfo.setDelFlag(1);
		mhUserRegisterInfoService.updateById(mhUserRegisterInfo);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "用户注册信息维护-批量删除")
	@ApiOperation(value="用户注册信息维护-批量删除", notes="用户注册信息维护-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		for(String id : Arrays.asList(ids.split(","))){
			MhUserRegisterInfo mhUserRegisterInfo = mhUserRegisterInfoService.getById(id);
			mhUserRegisterInfo.setDelFlag(1);
			mhUserRegisterInfoService.updateById(mhUserRegisterInfo);
		}
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "用户注册信息维护-通过id查询")
	@ApiOperation(value="用户注册信息维护-通过id查询", notes="用户注册信息维护-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		MhUserRegisterInfo mhUserRegisterInfo = mhUserRegisterInfoService.getById(id);
		if(mhUserRegisterInfo==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(mhUserRegisterInfo);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param mhUserRegisterInfo
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, MhUserRegisterInfo mhUserRegisterInfo) {
        return super.exportXls(request, mhUserRegisterInfo, MhUserRegisterInfo.class, "用户注册信息维护");
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
        return super.importExcel(request, response, MhUserRegisterInfo.class);
    }

	 /**
	  * 通过id冻结账号
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "用户注册信息维护-通过id冻结账号")
	 @ApiOperation(value="用户注册信息维护-通过id冻结账号", notes="用户注册信息维护-通过id冻结账号")
	 @GetMapping(value = "/accountFreeze")
	 public Result<?> accountFreeze(@RequestParam(name="id",required=true) String id) {
		 mhUserRegisterInfoService.accountFreeze(id);
		 return Result.OK("冻结账号成功");
	 }


	 /**
	  * 通过id恢复账号
	  *
	  * @param id
	  * @return
	  */
	 @AutoLog(value = "用户注册信息维护-通过id恢复账号")
	 @ApiOperation(value="用户注册信息维护-通过id恢复账号", notes="用户注册信息维护-通过id恢复账号")
	 @GetMapping(value = "/accountRecover")
	 public Result<?> accountRecover(@RequestParam(name="id",required=true) String id) {
		 mhUserRegisterInfoService.accountRecover(id);
		 return Result.OK("恢复账号成功");
	 }

	 /**
	  * 修改密码
	  */
	 @AutoLog(value = "用户注册信息维护-通过手机号码修改密码")
	 @ApiOperation(value="用户注册信息维护-通过手机号码修改密码", notes="用户注册信息维护-通过手机号码修改密码")
	 @RequestMapping(value = "/changePassword", method = RequestMethod.PUT)
	 public Result<?> changePassword(@RequestBody MhUserRegisterInfo mhUserRegisterInfo) {
		 MhUserRegisterInfo u = this.mhUserRegisterInfoService.getUserByPhone(mhUserRegisterInfo.getPhone());
		 if (u == null) {
			 return Result.error("用户不存在！");
		 }
		 mhUserRegisterInfo.setId(u.getId());
		 return mhUserRegisterInfoService.changePassword(mhUserRegisterInfo);
	 }

}
