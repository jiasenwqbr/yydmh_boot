package org.jeecg.modules.system.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: jeecg-boot-parent
 * @description: 微信获取openId从前端获取参数
 * @author: zz
 * @create: 2021/11/25 17:32
 */
@Data
public class WeChatParamVO implements Serializable {
    /**
     * 临时标识
     */
    private String code;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * logo地址
     */
    private String avatarUrl;


}
