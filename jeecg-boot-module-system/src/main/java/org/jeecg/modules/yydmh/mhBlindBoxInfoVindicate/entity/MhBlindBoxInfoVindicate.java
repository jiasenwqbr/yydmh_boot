package org.jeecg.modules.yydmh.mhBlindBoxInfoVindicate.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.jeecg.common.aspect.annotation.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 盲盒信息维护
 * @Author: jeecg-boot
 * @Date:   2021-11-14
 * @Version: V1.0
 */
@Data
@TableName("mh_blind_box_info_vindicate")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="mh_blind_box_info_vindicate对象", description="盲盒信息维护")
public class MhBlindBoxInfoVindicate implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**名称*/
	@Excel(name = "文物名称", width = 15)
    @ApiModelProperty(value = "文物名称")
    private String name;
    /**文物分类*/
    @Excel(name = "文物分类", width = 15)
    @ApiModelProperty(value = "文物分类")
    @Dict(dicCode = "cultural_relic_classify")
    private String culturalRelicClassify;
	/**二维码*/
	@Excel(name = "二维码", width = 15)
    @ApiModelProperty(value = "二维码")
    private String qrcode;
	/**图片*/
	@Excel(name = "图片", width = 15)
    @ApiModelProperty(value = "图片")
    private String picture;
	/**文物介绍*/
	@Excel(name = "文物介绍", width = 15)
    @ApiModelProperty(value = "文物介绍")
    private String culturalRelicIntroduce;
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
	@Excel(name = "删除标识", width = 15)
    @ApiModelProperty(value = "删除标识")
    private Integer delFlag;
    /**年代*/
    @Excel(name = "年代", width = 15)
    @ApiModelProperty(value = "年代")
	private String period;

}
