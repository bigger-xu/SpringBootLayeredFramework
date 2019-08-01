/*
 * @(#)  RoleVo.java    2019-06-05 10:16:11
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.entity;

import com.cto.freemarker.entity.base.BaseEntity;

import java.util.Date;

/**
 * 文件名Role.java
 * @author 1
 * @date 2019-06-05 10:16:11
 *  
 */
public class Role extends BaseEntity {
    
	/**
	 * 默认构造函数
	 * @author 1
	 * @date 2019-06-05 10:16:11
	 */
	public Role() {

	}
	/**
	 * 属性Id(主键)
	 */
	private Long id;
	/**
	 * 属性Uuid()
	 */
	private String uuid;
	/**
	 * 属性AddTime(添加时间)
	 */
	private Date addTime;
	/**
	 * 属性AddUserId(添加人ID)
	 */
	private Long addUserId;
	/**
	 * 属性UpdateTime(更新时间)
	 */
	private Date updateTime;
	/**
	 * 属性UpdateUserId(更新人ID)
	 */
	private Long updateUserId;
	/**
	 * 属性Name(角色名称)
	 */
	private String name;
	/**
	 * 属性Code(角色编码)
	 */
	private String code;
	/**
	 * 属性Status(状态1,0)
	 */
	private String status;
	/**
	 * 属性Remark(注释)
	 */
	private String remark;
	/**
	 * 属性DeleteFlag(删除状态(0否1是))
	 */
	private String deleteFlag;

	/**
	 *  获取属性Id(主键)
	 * @return Long
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public Long getId(){
		return id;
	}
	/**
	* 设置属性Id(主键)
	* @param id
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setId(Long id ){
		this.id = id ;
	}
	/**
	 *  获取属性Uuid()
	 * @return String
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public String getUuid(){
		return uuid;
	}
	/**
	* 设置属性Uuid()
	* @param uuid
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setUuid(String uuid ){
		this.uuid = uuid ;
	}
	/**
	 *  获取属性AddTime(添加时间)
	 * @return Date
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public Date getAddTime(){
		return addTime;
	}
	/**
	* 设置属性AddTime(添加时间)
	* @param addTime
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setAddTime(Date addTime ){
		this.addTime = addTime ;
	}
	/**
	 *  获取属性AddUserId(添加人ID)
	 * @return Long
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public Long getAddUserId(){
		return addUserId;
	}
	/**
	* 设置属性AddUserId(添加人ID)
	* @param addUserId
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setAddUserId(Long addUserId ){
		this.addUserId = addUserId ;
	}
	/**
	 *  获取属性UpdateTime(更新时间)
	 * @return Date
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public Date getUpdateTime(){
		return updateTime;
	}
	/**
	* 设置属性UpdateTime(更新时间)
	* @param updateTime
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setUpdateTime(Date updateTime ){
		this.updateTime = updateTime ;
	}
	/**
	 *  获取属性UpdateUserId(更新人ID)
	 * @return Long
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public Long getUpdateUserId(){
		return updateUserId;
	}
	/**
	* 设置属性UpdateUserId(更新人ID)
	* @param updateUserId
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setUpdateUserId(Long updateUserId ){
		this.updateUserId = updateUserId ;
	}
	/**
	 *  获取属性Name(角色名称)
	 * @return String
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public String getName(){
		return name;
	}
	/**
	* 设置属性Name(角色名称)
	* @param name
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setName(String name ){
		this.name = name ;
	}
	/**
	 *  获取属性Code(角色编码)
	 * @return String
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public String getCode(){
		return code;
	}
	/**
	* 设置属性Code(角色编码)
	* @param code
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setCode(String code ){
		this.code = code ;
	}
	/**
	 *  获取属性Status(状态1,0)
	 * @return String
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public String getStatus(){
		return status;
	}
	/**
	* 设置属性Status(状态1,0)
	* @param status
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setStatus(String status ){
		this.status = status ;
	}
	/**
	 *  获取属性Remark(注释)
	 * @return String
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public String getRemark(){
		return remark;
	}
	/**
	* 设置属性Remark(注释)
	* @param remark
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setRemark(String remark ){
		this.remark = remark ;
	}
	/**
	 *  获取属性DeleteFlag(删除状态(0否1是))
	 * @return String
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public String getDeleteFlag(){
		return deleteFlag;
	}
	/**
	* 设置属性DeleteFlag(删除状态(0否1是))
	* @param deleteFlag
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setDeleteFlag(String deleteFlag ){
		this.deleteFlag = deleteFlag ;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Id(主键):").append(id).append(" ");
		sb.append("Uuid():").append(uuid).append(" ");
		sb.append("AddTime(添加时间):").append(addTime).append(" ");
		sb.append("AddUserId(添加人ID):").append(addUserId).append(" ");
		sb.append("UpdateTime(更新时间):").append(updateTime).append(" ");
		sb.append("UpdateUserId(更新人ID):").append(updateUserId).append(" ");
		sb.append("Name(角色名称):").append(name).append(" ");
		sb.append("Code(角色编码):").append(code).append(" ");
		sb.append("Status(状态1,0):").append(status).append(" ");
		sb.append("Remark(注释):").append(remark).append(" ");
		sb.append("DeleteFlag(删除状态(0否1是)):").append(deleteFlag).append(" ");
		return sb.toString();
	}
}
