package org.jeecg.modules.yydmh.mhUserRegisterInfo.service;

import org.jeecg.common.api.vo.Result;
import org.jeecg.modules.yydmh.mhUserRegisterInfo.entity.MhUserRegisterInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 用户注册信息维护
 * @Author: jeecg-boot
 * @Date:   2021-11-14
 * @Version: V1.0
 */
public interface IMhUserRegisterInfoService extends IService<MhUserRegisterInfo> {


    /**
     * 冻结账号
     * @param id
     */
    public void accountFreeze(String id);


    /**
     * 恢复账号
     * @param id
     */
    public void accountRecover(String id);

    /**
     * 根据手机号获取用户
     */
    public MhUserRegisterInfo getUserByPhone(String phone);

    /**
     * 根据微信openId获取用户
     * @param openId
     * @return
     */
    public MhUserRegisterInfo getUserByOpenId(String openId);

    /**
     * 修改密码
     *
     * @param mhUserRegisterInfo
     * @return
     */
    public Result<?> changePassword(MhUserRegisterInfo mhUserRegisterInfo);

}
