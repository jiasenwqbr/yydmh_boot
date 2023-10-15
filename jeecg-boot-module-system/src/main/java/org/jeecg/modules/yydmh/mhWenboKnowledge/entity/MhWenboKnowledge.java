package org.jeecg.modules.yydmh.mhWenboKnowledge.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
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
 * @Description: 文博知识讲堂
 * @Author: jeecg-boot
 * @Date:   2021-11-13
 * @Version: V1.0
 */
@Data
@TableName("mh_wenbo_knowledge")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="mh_wenbo_knowledge对象", description="文博知识讲堂")
public class MhWenboKnowledge implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**标题*/
	@Excel(name = "标题", width = 15)
    @ApiModelProperty(value = "标题")
    private String title;
    /**文博知识讲堂分类*/
    @ApiModelProperty(value = "文博知识讲堂分类")
    @Autowired
    @Dict(dictTable = "mh_wenbo_knowledge_classify" ,dicCode = "id", dicText = "title")
    private String wenboKnowledgeClassify;
	/**图片*/
	@Excel(name = "图片", width = 15)
    @ApiModelProperty(value = "图片")
    private String picture;
	/**摘要*/
	@Excel(name = "摘要", width = 15)
    @ApiModelProperty(value = "摘要")
    private String digest;
	/**内容*/
	@Excel(name = "内容", width = 15)
    @ApiModelProperty(value = "内容")
    private String content;
	/**状态*/
	@Excel(name = "状态", width = 15, dicCode = "pub_status")
	@Dict(dicCode = "pub_status")
    @ApiModelProperty(value = "状态")
    private Integer status;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**发布时间*/
	@Excel(name = "发布时间", width = 20, format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "发布时间")
    private Date pubDate;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
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
    /**标签*/
    @ApiModelProperty(value = "标签")
    private String label;
}
