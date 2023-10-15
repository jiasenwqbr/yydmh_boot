package org.jeecg.modules.yydmh.mhWenboConsulting.service.impl;

import org.jeecg.modules.yydmh.mhWenboConsulting.entity.MhWenboConsulting;
import org.jeecg.modules.yydmh.mhWenboConsulting.mapper.MhWenboConsultingMapper;
import org.jeecg.modules.yydmh.mhWenboConsulting.service.IMhWenboConsultingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.Date;

/**
 * @Description: 文博咨询
 * @Author: jeecg-boot
 * @Date:   2021-11-13
 * @Version: V1.0
 */
@Service
public class MhWenboConsultingServiceImpl extends ServiceImpl<MhWenboConsultingMapper, MhWenboConsulting> implements IMhWenboConsultingService {

    @Autowired
    private MhWenboConsultingMapper mhWenboConsultingMapper;


    /**
     * 发布内容
     * @param id
     */
    @Override
    public void pubContent(String id){
        MhWenboConsulting mhWenboConsulting = new MhWenboConsulting();
        mhWenboConsulting.setId(id);
        mhWenboConsulting.setStatus(1);
        mhWenboConsulting.setPubDate(new Date());
        mhWenboConsultingMapper.updateById(mhWenboConsulting);
    }

    /**
     * 撤回内容
     * @param id
     */
    public void backContent(String id){
        MhWenboConsulting mhWenboConsulting = new MhWenboConsulting();
        mhWenboConsulting.setId(id);
        mhWenboConsulting.setStatus(2);
        mhWenboConsultingMapper.updateById(mhWenboConsulting);
    }
}
