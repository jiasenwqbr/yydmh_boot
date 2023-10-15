package org.jeecg.modules.yydmh.mhUserRegisterInfo.entity;

import java.io.Serializable;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.dialect.Ingres9Dialect;
import org.jeecg.common.aspect.annotation.Dict;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 用户注册信息维护
 * @Author: jeecg-boot
 * @Date:   2021-11-14
 * @Version: V1.0
 */
@Data
@TableName("mh_user_register_info")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="mh_user_register_info对象", description="用户注册信息维护")
public class MhUserRegisterInfo implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**姓名*/
	@Excel(name = "姓名", width = 15)
    @ApiModelProperty(value = "姓名")
    private String name;
	/**手机*/
	@Excel(name = "手机", width = 15)
    @ApiModelProperty(value = "手机")
    private String phone;
	/**昵称*/
	@Excel(name = "昵称", width = 15)
    @ApiModelProperty(value = "昵称")
    private String nickName;
	/**头像*/
	@Excel(name = "头像", width = 15)
    @ApiModelProperty(value = "头像")
    private String headPortrait;
	/**邮寄地址*/
	@Excel(name = "邮寄地址", width = 15)
    @ApiModelProperty(value = "邮寄地址")
    private String mailingAddress;
    /**出生日期*/
    @ApiModelProperty(value = "出生日期")
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dateOfBirth;
	/**密码*/
    @ApiModelProperty(value = "密码")
	private String  password;
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
    @ApiModelProperty(value = "状态")
    @Dict(dicCode = "user_status")
    private Integer status;
    /**删除标识*/
    @ApiModelProperty(value = "删除标识")
    private Integer delFlag;
    /**md5秘钥盐*/
    @ApiModelProperty(value = "md5秘钥盐")
    private String salt;
    /**微信openId*/
    @ApiModelProperty(value = "微信openId")
    private String openId;

}
