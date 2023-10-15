package org.jeecg.modules.yydmh.mhWenboKnowledge.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.yydmh.mhWenboKnowledge.entity.MhWenboKnowledge;

/**
 * @Description: 文博知识讲堂
 * @Author: jeecg-boot
 * @Date:   2021-11-13
 * @Version: V1.0
 */
public interface IMhWenboKnowledgeService extends IService<MhWenboKnowledge> {

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
