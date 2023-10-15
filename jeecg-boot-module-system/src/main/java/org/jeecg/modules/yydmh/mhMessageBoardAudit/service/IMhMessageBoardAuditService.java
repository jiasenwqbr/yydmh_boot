package org.jeecg.modules.yydmh.mhMessageBoardAudit.service;

import org.jeecg.modules.yydmh.mhMessageBoardAudit.entity.MhMessageBoardAudit;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @Description: 留言信息审核
 * @Author: jeecg-boot
 * @Date:   2021-11-14
 * @Version: V1.0
 */
public interface IMhMessageBoardAuditService extends IService<MhMessageBoardAudit> {
    /**
     * 查询小程序评论列表
     * @param mhMessageBoardAudit
     * @return
     */
    public List<Map<String, Object>> queryCommentList(MhMessageBoardAudit mhMessageBoardAudit);

}
