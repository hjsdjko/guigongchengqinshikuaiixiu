package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 在线报修
 *
 * @author 
 * @email
 */
@TableName("guzhangshangbao")
public class GuzhangshangbaoEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public GuzhangshangbaoEntity() {

	}

	public GuzhangshangbaoEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 学生
     */
    @ColumnInfo(comment="学生",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 报修编号
     */
    @ColumnInfo(comment="报修编号",type="varchar(200)")
    @TableField(value = "guzhangshangbao_uuid_number")

    private String guzhangshangbaoUuidNumber;


    /**
     * 区域
     */
    @ColumnInfo(comment="区域",type="int(11)")
    @TableField(value = "quyu_types")

    private Integer quyuTypes;


    /**
     * 详细地址
     */
    @ColumnInfo(comment="详细地址",type="varchar(200)")
    @TableField(value = "guzhangshangbao_address")

    private String guzhangshangbaoAddress;


    /**
     * 故障信息
     */
    @ColumnInfo(comment="故障信息",type="varchar(200)")
    @TableField(value = "guzhangshangbao_guzhang")

    private String guzhangshangbaoGuzhang;


    /**
     * 故障类型
     */
    @ColumnInfo(comment="故障类型",type="int(11)")
    @TableField(value = "guzhangshangbao_types")

    private Integer guzhangshangbaoTypes;


    /**
     * 维修人员
     */
    @ColumnInfo(comment="维修人员",type="int(11)")
    @TableField(value = "weixiuyuan_id")

    private Integer weixiuyuanId;


    /**
     * 处理结果
     */
    @ColumnInfo(comment="处理结果",type="int(11)")
    @TableField(value = "guzhangshangbao_yesno_types")

    private Integer guzhangshangbaoYesnoTypes;


    /**
     * 处理回复
     */
    @ColumnInfo(comment="处理回复",type="longtext")
    @TableField(value = "guzhangshangbao_yesno_text")

    private String guzhangshangbaoYesnoText;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "guzhangshangbao_delete")

    private Integer guzhangshangbaoDelete;


    /**
     * 创建时间  listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：学生
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }
    /**
	 * 设置：学生
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：报修编号
	 */
    public String getGuzhangshangbaoUuidNumber() {
        return guzhangshangbaoUuidNumber;
    }
    /**
	 * 设置：报修编号
	 */

    public void setGuzhangshangbaoUuidNumber(String guzhangshangbaoUuidNumber) {
        this.guzhangshangbaoUuidNumber = guzhangshangbaoUuidNumber;
    }
    /**
	 * 获取：区域
	 */
    public Integer getQuyuTypes() {
        return quyuTypes;
    }
    /**
	 * 设置：区域
	 */

    public void setQuyuTypes(Integer quyuTypes) {
        this.quyuTypes = quyuTypes;
    }
    /**
	 * 获取：详细地址
	 */
    public String getGuzhangshangbaoAddress() {
        return guzhangshangbaoAddress;
    }
    /**
	 * 设置：详细地址
	 */

    public void setGuzhangshangbaoAddress(String guzhangshangbaoAddress) {
        this.guzhangshangbaoAddress = guzhangshangbaoAddress;
    }
    /**
	 * 获取：故障信息
	 */
    public String getGuzhangshangbaoGuzhang() {
        return guzhangshangbaoGuzhang;
    }
    /**
	 * 设置：故障信息
	 */

    public void setGuzhangshangbaoGuzhang(String guzhangshangbaoGuzhang) {
        this.guzhangshangbaoGuzhang = guzhangshangbaoGuzhang;
    }
    /**
	 * 获取：故障类型
	 */
    public Integer getGuzhangshangbaoTypes() {
        return guzhangshangbaoTypes;
    }
    /**
	 * 设置：故障类型
	 */

    public void setGuzhangshangbaoTypes(Integer guzhangshangbaoTypes) {
        this.guzhangshangbaoTypes = guzhangshangbaoTypes;
    }
    /**
	 * 获取：维修人员
	 */
    public Integer getWeixiuyuanId() {
        return weixiuyuanId;
    }
    /**
	 * 设置：维修人员
	 */

    public void setWeixiuyuanId(Integer weixiuyuanId) {
        this.weixiuyuanId = weixiuyuanId;
    }
    /**
	 * 获取：处理结果
	 */
    public Integer getGuzhangshangbaoYesnoTypes() {
        return guzhangshangbaoYesnoTypes;
    }
    /**
	 * 设置：处理结果
	 */

    public void setGuzhangshangbaoYesnoTypes(Integer guzhangshangbaoYesnoTypes) {
        this.guzhangshangbaoYesnoTypes = guzhangshangbaoYesnoTypes;
    }
    /**
	 * 获取：处理回复
	 */
    public String getGuzhangshangbaoYesnoText() {
        return guzhangshangbaoYesnoText;
    }
    /**
	 * 设置：处理回复
	 */

    public void setGuzhangshangbaoYesnoText(String guzhangshangbaoYesnoText) {
        this.guzhangshangbaoYesnoText = guzhangshangbaoYesnoText;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getGuzhangshangbaoDelete() {
        return guzhangshangbaoDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setGuzhangshangbaoDelete(Integer guzhangshangbaoDelete) {
        this.guzhangshangbaoDelete = guzhangshangbaoDelete;
    }
    /**
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Guzhangshangbao{" +
            ", id=" + id +
            ", yonghuId=" + yonghuId +
            ", guzhangshangbaoUuidNumber=" + guzhangshangbaoUuidNumber +
            ", quyuTypes=" + quyuTypes +
            ", guzhangshangbaoAddress=" + guzhangshangbaoAddress +
            ", guzhangshangbaoGuzhang=" + guzhangshangbaoGuzhang +
            ", guzhangshangbaoTypes=" + guzhangshangbaoTypes +
            ", weixiuyuanId=" + weixiuyuanId +
            ", guzhangshangbaoYesnoTypes=" + guzhangshangbaoYesnoTypes +
            ", guzhangshangbaoYesnoText=" + guzhangshangbaoYesnoText +
            ", guzhangshangbaoDelete=" + guzhangshangbaoDelete +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
