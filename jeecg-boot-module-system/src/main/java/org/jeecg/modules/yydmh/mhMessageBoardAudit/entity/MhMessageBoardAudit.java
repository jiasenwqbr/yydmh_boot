package org.jeecg.modules.yydmh.mhMessageBoardAudit.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 留言信息审核
 * @Author: jeecg-boot
 * @Date:   2021-11-14
 * @Version: V1.0
 */
@Data
@TableName("mh_message_board_audit")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="mh_message_board_audit对象", description="留言信息审核")
public class MhMessageBoardAudit implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**板块*/
	@Excel(name = "板块", width = 15)
    @Dict(dicCode = "plate")
    @ApiModelProperty(value = "板块")

    private String plate;
    /**留言文章id*/
    @ApiModelProperty(value = "留言文章id")
    @Autowired
    private String mhArticleId;
    /**留言用户id*/
    @ApiModelProperty(value = "留言用户id")
	@Autowired
	private String mhUserId;
	/**留言内容*/
	@Excel(name = "留言内容", width = 15)
    @ApiModelProperty(value = "留言内容")
    private String content;
	/**留言时间*/
	@Excel(name = "留言时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "留言时间")
    private Date leaveWordTime;
	/**状态*/
	@Excel(name = "状态", width = 15, dicCode = "leave_word")
	@Dict(dicCode = "leave_word")
    @ApiModelProperty(value = "状态")
    private Integer status;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    /**更新日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
    /**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
    /**删除标识*/
    @ApiModelProperty(value = "删除标识")
    private Integer delFlag;

    /**审核时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "审核时间")
    private Date auditTime;

    //审核时使用
    @TableField(exist = false)
    private Integer auditStatus;
}
