package org.jeecg.modules.yydmh.mhUserRegisterInfo.service.impl;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.util.PasswordUtil;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.yydmh.mhUserRegisterInfo.entity.MhUserRegisterInfo;
import org.jeecg.modules.yydmh.mhUserRegisterInfo.mapper.MhUserRegisterInfoMapper;
import org.jeecg.modules.yydmh.mhUserRegisterInfo.service.IMhUserRegisterInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 用户注册信息维护
 * @Author: jeecg-boot
 * @Date:   2021-11-14
 * @Version: V1.0
 */
@Service
public class MhUserRegisterInfoServiceImpl extends ServiceImpl<MhUserRegisterInfoMapper, MhUserRegisterInfo> implements IMhUserRegisterInfoService {

    @Autowired
    private MhUserRegisterInfoMapper mhUserRegisterInfoMapper;

    /**
     * 冻结账号
     * @param id
     */
    @Override
    public void accountFreeze(String id){
        MhUserRegisterInfo mhUserRegisterInfo = new MhUserRegisterInfo();
        mhUserRegisterInfo.setId(id);
        mhUserRegisterInfo.setStatus(2);
        mhUserRegisterInfoMapper.updateById(mhUserRegisterInfo);
    }


    /**
     * 恢复账号
     * @param id
     */
    @Override
    public void accountRecover(String id){
        MhUserRegisterInfo mhUserRegisterInfo = new MhUserRegisterInfo();
        mhUserRegisterInfo.setId(id);
        mhUserRegisterInfo.setStatus(1);
        mhUserRegisterInfoMapper.updateById(mhUserRegisterInfo);
    }

    /**
     * 根据手机号获取用户
     */
    @Override
    public MhUserRegisterInfo getUserByPhone(String phone){
        return mhUserRegisterInfoMapper.getUserByPhone(phone);
    }

    /**
     * 根据微信openId获取用户
     * @param openId
     * @return
     */
    @Override
    public MhUserRegisterInfo getUserByOpenId(String openId){
        return mhUserRegisterInfoMapper.getUserByOpenId(openId);
    }

    /**
     * 修改密码
     *
     * @param mhUserRegisterInfo
     * @return
     */
    @Override
    public Result<?> changePassword(MhUserRegisterInfo mhUserRegisterInfo){
        String salt = oConvertUtils.randomGen(8);
        mhUserRegisterInfo.setSalt(salt);
        String password = mhUserRegisterInfo.getPassword();
        String passwordEncode = PasswordUtil.encrypt(mhUserRegisterInfo.getPhone(), password, salt);
        mhUserRegisterInfo.setPassword(passwordEncode);
        this.mhUserRegisterInfoMapper.updateById(mhUserRegisterInfo);
        return Result.ok("密码修改成功!");
    }
}
