package org.jeecg.modules.yydmh.mhMessageBoardAudit.mapper;

import org.jeecg.modules.yydmh.mhMessageBoardAudit.entity.MhMessageBoardAudit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @Description: 留言信息审核
 * @Author: jeecg-boot
 * @Date:   2021-11-14
 * @Version: V1.0
 */
public interface MhMessageBoardAuditMapper extends BaseMapper<MhMessageBoardAudit> {

    /**
     * 小程序评论列表
     * @param param
     * @return
     */
    public List<Map<String, Object>> queryCommentList(MhMessageBoardAudit mhMessageBoardAudit);

}
