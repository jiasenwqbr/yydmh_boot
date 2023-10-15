package org.jeecg.modules.yydmh.mhWenboKnowledge.service.impl;

import org.jeecg.modules.yydmh.mhWenboKnowledge.entity.MhWenboKnowledge;
import org.jeecg.modules.yydmh.mhWenboKnowledge.mapper.MhWenboKnowledgeMapper;
import org.jeecg.modules.yydmh.mhWenboKnowledge.service.IMhWenboKnowledgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Date;

/**
 * @Description: 文博知识讲堂
 * @Author: jeecg-boot
 * @Date:   2021-11-13
 * @Version: V1.0
 */
@Service
public class MhWenboKnowledgeServiceImpl extends ServiceImpl<MhWenboKnowledgeMapper, MhWenboKnowledge> implements IMhWenboKnowledgeService {

    @Autowired
    private MhWenboKnowledgeMapper mhWenboKnowledgeMapper;

    /**
     * 发布内容
     * @param id
     */
    @Override
    public void pubContent(String id){
        MhWenboKnowledge mhWenboKnowledge = new MhWenboKnowledge();
        mhWenboKnowledge.setId(id);
        mhWenboKnowledge.setStatus(1);
        mhWenboKnowledge.setPubDate(new Date());
        mhWenboKnowledgeMapper.updateById(mhWenboKnowledge);
    }

    /**
     * 撤回内容
     * @param id
     */
    public void backContent(String id){
        MhWenboKnowledge mhWenboKnowledge = new MhWenboKnowledge();
        mhWenboKnowledge.setId(id);
        mhWenboKnowledge.setStatus(2);
        mhWenboKnowledgeMapper.updateById(mhWenboKnowledge);
    }
}
