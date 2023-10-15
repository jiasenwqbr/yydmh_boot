package org.jeecg.modules.yydmh.mhUserRegisterInfo.mapper;

import org.apache.ibatis.annotations.Param;
import org.jeecg.modules.yydmh.mhUserRegisterInfo.entity.MhUserRegisterInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Description: 用户注册信息维护
 * @Author: jeecg-boot
 * @Date:   2021-11-14
 * @Version: V1.0
 */
public interface MhUserRegisterInfoMapper extends BaseMapper<MhUserRegisterInfo> {

    /**
     * 根据手机号查询用户信息
     * @param phone
     * @return
     */
    public MhUserRegisterInfo getUserByPhone(@Param("phone") String phone);

    /**
     * 根据微信openId获取用户
     * @param openId
     * @return
     */
    public MhUserRegisterInfo getUserByOpenId(@Param("openId") String openId);

}
