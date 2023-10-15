package org.jeecg.modules.yydmh.mhWenboConsulting.service;

import org.jeecg.modules.yydmh.mhWenboConsulting.entity.MhWenboConsulting;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 文博咨询
 * @Author: jeecg-boot
 * @Date:   2021-11-13
 * @Version: V1.0
 */
public interface IMhWenboConsultingService extends IService<MhWenboConsulting> {

    /**
     * 发布内容
     * @param id
     */
    public void pubContent(String id);


    /**
     * 撤回内容
     * @param id
     */
    public void backContent(String id);

}
