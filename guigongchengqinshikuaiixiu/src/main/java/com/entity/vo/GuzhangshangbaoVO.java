package com.entity.vo;

import com.entity.GuzhangshangbaoEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 在线报修
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("guzhangshangbao")
public class GuzhangshangbaoVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 学生
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 报修编号
     */

    @TableField(value = "guzhangshangbao_uuid_number")
    private String guzhangshangbaoUuidNumber;


    /**
     * 区域
     */

    @TableField(value = "quyu_types")
    private Integer quyuTypes;


    /**
     * 详细地址
     */

    @TableField(value = "guzhangshangbao_address")
    private String guzhangshangbaoAddress;


    /**
     * 故障信息
     */

    @TableField(value = "guzhangshangbao_guzhang")
    private String guzhangshangbaoGuzhang;


    /**
     * 故障类型
     */

    @TableField(value = "guzhangshangbao_types")
    private Integer guzhangshangbaoTypes;


    /**
     * 维修人员
     */

    @TableField(value = "weixiuyuan_id")
    private Integer weixiuyuanId;


    /**
     * 处理结果
     */

    @TableField(value = "guzhangshangbao_yesno_types")
    private Integer guzhangshangbaoYesnoTypes;


    /**
     * 处理回复
     */

    @TableField(value = "guzhangshangbao_yesno_text")
    private String guzhangshangbaoYesnoText;


    /**
     * 逻辑删除
     */

    @TableField(value = "guzhangshangbao_delete")
    private Integer guzhangshangbaoDelete;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：学生
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：学生
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：报修编号
	 */
    public String getGuzhangshangbaoUuidNumber() {
        return guzhangshangbaoUuidNumber;
    }


    /**
	 * 获取：报修编号
	 */

    public void setGuzhangshangbaoUuidNumber(String guzhangshangbaoUuidNumber) {
        this.guzhangshangbaoUuidNumber = guzhangshangbaoUuidNumber;
    }
    /**
	 * 设置：区域
	 */
    public Integer getQuyuTypes() {
        return quyuTypes;
    }


    /**
	 * 获取：区域
	 */

    public void setQuyuTypes(Integer quyuTypes) {
        this.quyuTypes = quyuTypes;
    }
    /**
	 * 设置：详细地址
	 */
    public String getGuzhangshangbaoAddress() {
        return guzhangshangbaoAddress;
    }


    /**
	 * 获取：详细地址
	 */

    public void setGuzhangshangbaoAddress(String guzhangshangbaoAddress) {
        this.guzhangshangbaoAddress = guzhangshangbaoAddress;
    }
    /**
	 * 设置：故障信息
	 */
    public String getGuzhangshangbaoGuzhang() {
        return guzhangshangbaoGuzhang;
    }


    /**
	 * 获取：故障信息
	 */

    public void setGuzhangshangbaoGuzhang(String guzhangshangbaoGuzhang) {
        this.guzhangshangbaoGuzhang = guzhangshangbaoGuzhang;
    }
    /**
	 * 设置：故障类型
	 */
    public Integer getGuzhangshangbaoTypes() {
        return guzhangshangbaoTypes;
    }


    /**
	 * 获取：故障类型
	 */

    public void setGuzhangshangbaoTypes(Integer guzhangshangbaoTypes) {
        this.guzhangshangbaoTypes = guzhangshangbaoTypes;
    }
    /**
	 * 设置：维修人员
	 */
    public Integer getWeixiuyuanId() {
        return weixiuyuanId;
    }


    /**
	 * 获取：维修人员
	 */

    public void setWeixiuyuanId(Integer weixiuyuanId) {
        this.weixiuyuanId = weixiuyuanId;
    }
    /**
	 * 设置：处理结果
	 */
    public Integer getGuzhangshangbaoYesnoTypes() {
        return guzhangshangbaoYesnoTypes;
    }


    /**
	 * 获取：处理结果
	 */

    public void setGuzhangshangbaoYesnoTypes(Integer guzhangshangbaoYesnoTypes) {
        this.guzhangshangbaoYesnoTypes = guzhangshangbaoYesnoTypes;
    }
    /**
	 * 设置：处理回复
	 */
    public String getGuzhangshangbaoYesnoText() {
        return guzhangshangbaoYesnoText;
    }


    /**
	 * 获取：处理回复
	 */

    public void setGuzhangshangbaoYesnoText(String guzhangshangbaoYesnoText) {
        this.guzhangshangbaoYesnoText = guzhangshangbaoYesnoText;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getGuzhangshangbaoDelete() {
        return guzhangshangbaoDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setGuzhangshangbaoDelete(Integer guzhangshangbaoDelete) {
        this.guzhangshangbaoDelete = guzhangshangbaoDelete;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
