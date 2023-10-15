package org.jeecg.modules.yydmh.mhMessageBoardAudit.service.impl;

import org.jeecg.modules.yydmh.mhMessageBoardAudit.entity.MhMessageBoardAudit;
import org.jeecg.modules.yydmh.mhMessageBoardAudit.mapper.MhMessageBoardAuditMapper;
import org.jeecg.modules.yydmh.mhMessageBoardAudit.service.IMhMessageBoardAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 留言信息审核
 * @Author: jeecg-boot
 * @Date:   2021-11-14
 * @Version: V1.0
 */
@Service
public class MhMessageBoardAuditServiceImpl extends ServiceImpl<MhMessageBoardAuditMapper, MhMessageBoardAudit> implements IMhMessageBoardAuditService {

    @Autowired
    private MhMessageBoardAuditMapper mhMessageBoardAuditMapper;

    /**
     * 查询小程序评论列表
     * @param mhMessageBoardAudit
     * @return
     */
    @Override
    public List<Map<String, Object>> queryCommentList(MhMessageBoardAudit mhMessageBoardAudit){
        return mhMessageBoardAuditMapper.queryCommentList(mhMessageBoardAudit);
    }
}
