/*
 * @(#)  RoleUserVo.java    2019-06-05 10:16:11
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.entity;

import com.cto.freemarker.entity.base.BaseEntity;

import java.util.Date;

/**
 * 文件名RoleUser.java
 * @author 1
 * @date 2019-06-05 10:16:11
 *  
 */
public class RoleUser extends BaseEntity {
    
	/**
	 * 默认构造函数
	 * @author 1
	 * @date 2019-06-05 10:16:11
	 */
	public RoleUser() {

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
	 * 属性RoleId(角色ID)
	 */
	private Long roleId;
	/**
	 * 属性UserId(用户ID)
	 */
	private Long userId;

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
	 *  获取属性RoleId(角色ID)
	 * @return Long
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public Long getRoleId(){
		return roleId;
	}
	/**
	* 设置属性RoleId(角色ID)
	* @param roleId
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setRoleId(Long roleId ){
		this.roleId = roleId ;
	}
	/**
	 *  获取属性UserId(用户ID)
	 * @return Long
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public Long getUserId(){
		return userId;
	}
	/**
	* 设置属性UserId(用户ID)
	* @param userId
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setUserId(Long userId ){
		this.userId = userId ;
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
		sb.append("RoleId(角色ID):").append(roleId).append(" ");
		sb.append("UserId(用户ID):").append(userId).append(" ");
		return sb.toString();
	}
}
