/*
 * @(#)  MenuVo.java    2019-06-05 14:44:13
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.entity;

import com.cto.freemarker.entity.base.BaseEntity;

import java.util.Date;

/**
 * 文件名Menu.java
 * @author 1
 * @date 2019-06-05 14:44:13
 *  
 */
public class Menu extends BaseEntity {
    
	/**
	 * 默认构造函数
	 * @author 1
	 * @date 2019-06-05 14:44:13
	 */
	public Menu() {

	}
	/**
	 * 属性Uuid()
	 */
	private String uuid;
	/**
	 * 属性Url(系统url)
	 */
	private String url;
	/**
	 * 属性UpdateUserId(更新人ID)
	 */
	private Long updateUserId;
	/**
	 * 属性UpdateTime(更新时间)
	 */
	private Date updateTime;
	/**
	 * 属性State(是否显示（0：是 1：否）)
	 */
	private Integer state;
	/**
	 * 属性Sort(排序)
	 */
	private Integer sort;
	/**
	 * 属性Permission(权限标识符)
	 */
	private String permission;
	/**
	 * 属性ParentId(父id 关联sys_menu.id)
	 */
	private Long parentId;
	/**
	 * 属性Id(主键)
	 */
	private Long id;
	/**
	 * 属性Name(菜单名称)
	 */
	private String name;
	/**
	 * 属性DeleteFlag(是否删除,0=未删除，1=已删除)
	 */
	private Integer deleteFlag;
	/**
	 * 属性AddUserId(添加人ID)
	 */
	private Long addUserId;
	/**
	 * 属性AddTime(添加时间)
	 */
	private Date addTime;

	/**
	 *  获取属性Uuid()
	 * @return String
	 * @author 1
	 * @date 2019-06-05 14:44:13
	*/
	public String getUuid(){
		return uuid;
	}
	/**
	* 设置属性Uuid()
	* @param uuid
	* @return void
	* @author 1
	* @date  2019-06-05 14:44:13
	*/
	public void setUuid(String uuid ){
		this.uuid = uuid ;
	}
	/**
	 *  获取属性Url(系统url)
	 * @return String
	 * @author 1
	 * @date 2019-06-05 14:44:13
	*/
	public String getUrl(){
		return url;
	}
	/**
	* 设置属性Url(系统url)
	* @param url
	* @return void
	* @author 1
	* @date  2019-06-05 14:44:13
	*/
	public void setUrl(String url ){
		this.url = url ;
	}
	/**
	 *  获取属性UpdateUserId(更新人ID)
	 * @return Long
	 * @author 1
	 * @date 2019-06-05 14:44:13
	*/
	public Long getUpdateUserId(){
		return updateUserId;
	}
	/**
	* 设置属性UpdateUserId(更新人ID)
	* @param updateUserId
	* @return void
	* @author 1
	* @date  2019-06-05 14:44:13
	*/
	public void setUpdateUserId(Long updateUserId ){
		this.updateUserId = updateUserId ;
	}
	/**
	 *  获取属性UpdateTime(更新时间)
	 * @return Date
	 * @author 1
	 * @date 2019-06-05 14:44:13
	*/
	public Date getUpdateTime(){
		return updateTime;
	}
	/**
	* 设置属性UpdateTime(更新时间)
	* @param updateTime
	* @return void
	* @author 1
	* @date  2019-06-05 14:44:13
	*/
	public void setUpdateTime(Date updateTime ){
		this.updateTime = updateTime ;
	}
	/**
	 *  获取属性State(是否显示（0：是 1：否）)
	 * @return Integer
	 * @author 1
	 * @date 2019-06-05 14:44:13
	*/
	public Integer getState(){
		return state;
	}
	/**
	* 设置属性State(是否显示（0：是 1：否）)
	* @param state
	* @return void
	* @author 1
	* @date  2019-06-05 14:44:13
	*/
	public void setState(Integer state ){
		this.state = state ;
	}
	/**
	 *  获取属性Sort(排序)
	 * @return Integer
	 * @author 1
	 * @date 2019-06-05 14:44:13
	*/
	public Integer getSort(){
		return sort;
	}
	/**
	* 设置属性Sort(排序)
	* @param sort
	* @return void
	* @author 1
	* @date  2019-06-05 14:44:13
	*/
	public void setSort(Integer sort ){
		this.sort = sort ;
	}
	/**
	 *  获取属性Permission(权限标识符)
	 * @return String
	 * @author 1
	 * @date 2019-06-05 14:44:13
	*/
	public String getPermission(){
		return permission;
	}
	/**
	* 设置属性Permission(权限标识符)
	* @param permission
	* @return void
	* @author 1
	* @date  2019-06-05 14:44:13
	*/
	public void setPermission(String permission ){
		this.permission = permission ;
	}
	/**
	 *  获取属性ParentId(父id 关联sys_menu.id)
	 * @return Integer
	 * @author 1
	 * @date 2019-06-05 14:44:13
	*/
	public Long getParentId(){
		return parentId;
	}
	/**
	* 设置属性ParentId(父id 关联sys_menu.id)
	* @param parentId
	* @return void
	* @author 1
	* @date  2019-06-05 14:44:13
	*/
	public void setParentId(Long parentId ){
		this.parentId = parentId ;
	}
	/**
	 *  获取属性Id(主键)
	 * @return Long
	 * @author 1
	 * @date 2019-06-05 14:44:13
	*/
	public Long getId(){
		return id;
	}
	/**
	* 设置属性Id(主键)
	* @param id
	* @return void
	* @author 1
	* @date  2019-06-05 14:44:13
	*/
	public void setId(Long id ){
		this.id = id ;
	}
	/**
	 *  获取属性Name(菜单名称)
	 * @return String
	 * @author 1
	 * @date 2019-06-05 14:44:13
	*/
	public String getName(){
		return name;
	}
	/**
	* 设置属性Name(菜单名称)
	* @param name
	* @return void
	* @author 1
	* @date  2019-06-05 14:44:13
	*/
	public void setName(String name ){
		this.name = name ;
	}
	/**
	 *  获取属性DeleteFlag(是否删除,0=未删除，1=已删除)
	 * @return Integer
	 * @author 1
	 * @date 2019-06-05 14:44:13
	*/
	public Integer getDeleteFlag(){
		return deleteFlag;
	}
	/**
	* 设置属性DeleteFlag(是否删除,0=未删除，1=已删除)
	* @param deleteFlag
	* @return void
	* @author 1
	* @date  2019-06-05 14:44:13
	*/
	public void setDeleteFlag(Integer deleteFlag ){
		this.deleteFlag = deleteFlag ;
	}
	/**
	 *  获取属性AddUserId(添加人ID)
	 * @return Long
	 * @author 1
	 * @date 2019-06-05 14:44:13
	*/
	public Long getAddUserId(){
		return addUserId;
	}
	/**
	* 设置属性AddUserId(添加人ID)
	* @param addUserId
	* @return void
	* @author 1
	* @date  2019-06-05 14:44:13
	*/
	public void setAddUserId(Long addUserId ){
		this.addUserId = addUserId ;
	}
	/**
	 *  获取属性AddTime(添加时间)
	 * @return Date
	 * @author 1
	 * @date 2019-06-05 14:44:13
	*/
	public Date getAddTime(){
		return addTime;
	}
	/**
	* 设置属性AddTime(添加时间)
	* @param addTime
	* @return void
	* @author 1
	* @date  2019-06-05 14:44:13
	*/
	public void setAddTime(Date addTime ){
		this.addTime = addTime ;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Uuid():").append(uuid).append(" ");
		sb.append("Url(系统url):").append(url).append(" ");
		sb.append("UpdateUserId(更新人ID):").append(updateUserId).append(" ");
		sb.append("UpdateTime(更新时间):").append(updateTime).append(" ");
		sb.append("State(是否显示（0：是 1：否）):").append(state).append(" ");
		sb.append("Sort(排序):").append(sort).append(" ");
		sb.append("Permission(权限标识符):").append(permission).append(" ");
		sb.append("ParentId(父id 关联sys_menu.id):").append(parentId).append(" ");
		sb.append("Id(主键):").append(id).append(" ");
		sb.append("Name(菜单名称):").append(name).append(" ");
		sb.append("DeleteFlag(是否删除,0=未删除，1=已删除):").append(deleteFlag).append(" ");
		sb.append("AddUserId(添加人ID):").append(addUserId).append(" ");
		sb.append("AddTime(添加时间):").append(addTime).append(" ");
		return sb.toString();
	}
}
