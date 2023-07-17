/*
 * @(#)  FrontUserVo.java    2019-12-29 23:25:23
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.cloud.entity;

import java.util.Date;

/**
 * 文件名FrontUser.java
 * @author 594cto版权所有
 * @date 2019-12-29 23:25:23
 *  
 */
public class FrontUser{
    
	/**
	 * 默认构造函数
	 * @author 594cto版权所有
	 * @date 2019-12-29 23:25:23
	 */
	public FrontUser() {

	}
	/**
	 * 属性DeleteFlag(删除状态)
	 */
	private String deleteFlag;
	/**
	 * 属性LoginCount(登录次数)
	 */
	private Integer loginCount;
	/**
	 * 属性LastLoginIp(最后一次登录IP)
	 */
	private String lastLoginIp;
	/**
	 * 属性LastLoginTime(最后一次登录时间)
	 */
	private Date lastLoginTime;
	/**
	 * 属性Mobile(联系电话)
	 */
	private String mobile;
	/**
	 * 属性Email(电子邮箱)
	 */
	private String email;
	/**
	 * 属性Sex(性别)
	 */
	private String sex;
	/**
	 * 属性Avator(用户头像)
	 */
	private String avator;
	/**
	 * 属性NickName(昵称)
	 */
	private String nickName;
	/**
	 * 属性Salt(盐值)
	 */
	private String salt;
	/**
	 * 属性Password(密码校验规则md5(md5(password)+salt))
	 */
	private String password;
	/**
	 * 属性UserName(用户名)
	 */
	private String userName;
	/**
	 * 属性Status(用户状态(0锁定1正常))
	 */
	private String status;
	/**
	 * 属性UpdateTime(更新时间)
	 */
	private Date updateTime;
	/**
	 * 属性CreateTime(添加时间)
	 */
	private Date createTime;
	/**
	 * 属性Uuid(UUID)
	 */
	private String uuid;
	/**
	 * 属性Id(ID编号)
	 */
	private Long id;

	/**
	 *  获取属性DeleteFlag(删除状态)
	 * @return String
	 * @author 594cto版权所有
	 * @date 2019-12-29 23:25:23
	*/
	public String getDeleteFlag(){
		return deleteFlag;
	}
	/**
	* 设置属性DeleteFlag(删除状态)
	* @param deleteFlag
	* @return void
	* @author 594cto版权所有
	* @date  2019-12-29 23:25:23
	*/
	public void setDeleteFlag(String deleteFlag ){
		this.deleteFlag = deleteFlag ;
	}
	/**
	 *  获取属性LoginCount(登录次数)
	 * @return Integer
	 * @author 594cto版权所有
	 * @date 2019-12-29 23:25:23
	*/
	public Integer getLoginCount(){
		return loginCount;
	}
	/**
	* 设置属性LoginCount(登录次数)
	* @param loginCount
	* @return void
	* @author 594cto版权所有
	* @date  2019-12-29 23:25:23
	*/
	public void setLoginCount(Integer loginCount ){
		this.loginCount = loginCount ;
	}
	/**
	 *  获取属性LastLoginIp(最后一次登录IP)
	 * @return String
	 * @author 594cto版权所有
	 * @date 2019-12-29 23:25:23
	*/
	public String getLastLoginIp(){
		return lastLoginIp;
	}
	/**
	* 设置属性LastLoginIp(最后一次登录IP)
	* @param lastLoginIp
	* @return void
	* @author 594cto版权所有
	* @date  2019-12-29 23:25:23
	*/
	public void setLastLoginIp(String lastLoginIp ){
		this.lastLoginIp = lastLoginIp ;
	}
	/**
	 *  获取属性LastLoginTime(最后一次登录时间)
	 * @return Date
	 * @author 594cto版权所有
	 * @date 2019-12-29 23:25:23
	*/
	public Date getLastLoginTime(){
		return lastLoginTime;
	}
	/**
	* 设置属性LastLoginTime(最后一次登录时间)
	* @param lastLoginTime
	* @return void
	* @author 594cto版权所有
	* @date  2019-12-29 23:25:23
	*/
	public void setLastLoginTime(Date lastLoginTime ){
		this.lastLoginTime = lastLoginTime ;
	}
	/**
	 *  获取属性Mobile(联系电话)
	 * @return String
	 * @author 594cto版权所有
	 * @date 2019-12-29 23:25:23
	*/
	public String getMobile(){
		return mobile;
	}
	/**
	* 设置属性Mobile(联系电话)
	* @param mobile
	* @return void
	* @author 594cto版权所有
	* @date  2019-12-29 23:25:23
	*/
	public void setMobile(String mobile ){
		this.mobile = mobile ;
	}
	/**
	 *  获取属性Email(电子邮箱)
	 * @return String
	 * @author 594cto版权所有
	 * @date 2019-12-29 23:25:23
	*/
	public String getEmail(){
		return email;
	}
	/**
	* 设置属性Email(电子邮箱)
	* @param email
	* @return void
	* @author 594cto版权所有
	* @date  2019-12-29 23:25:23
	*/
	public void setEmail(String email ){
		this.email = email ;
	}
	/**
	 *  获取属性Sex(性别)
	 * @return String
	 * @author 594cto版权所有
	 * @date 2019-12-29 23:25:23
	*/
	public String getSex(){
		return sex;
	}
	/**
	* 设置属性Sex(性别)
	* @param sex
	* @return void
	* @author 594cto版权所有
	* @date  2019-12-29 23:25:23
	*/
	public void setSex(String sex ){
		this.sex = sex ;
	}
	/**
	 *  获取属性Avator(用户头像)
	 * @return String
	 * @author 594cto版权所有
	 * @date 2019-12-29 23:25:23
	*/
	public String getAvator(){
		return avator;
	}
	/**
	* 设置属性Avator(用户头像)
	* @param avator
	* @return void
	* @author 594cto版权所有
	* @date  2019-12-29 23:25:23
	*/
	public void setAvator(String avator ){
		this.avator = avator ;
	}
	/**
	 *  获取属性NickName(昵称)
	 * @return String
	 * @author 594cto版权所有
	 * @date 2019-12-29 23:25:23
	*/
	public String getNickName(){
		return nickName;
	}
	/**
	* 设置属性NickName(昵称)
	* @param nickName
	* @return void
	* @author 594cto版权所有
	* @date  2019-12-29 23:25:23
	*/
	public void setNickName(String nickName ){
		this.nickName = nickName ;
	}
	/**
	 *  获取属性Salt(盐值)
	 * @return String
	 * @author 594cto版权所有
	 * @date 2019-12-29 23:25:23
	*/
	public String getSalt(){
		return salt;
	}
	/**
	* 设置属性Salt(盐值)
	* @param salt
	* @return void
	* @author 594cto版权所有
	* @date  2019-12-29 23:25:23
	*/
	public void setSalt(String salt ){
		this.salt = salt ;
	}
	/**
	 *  获取属性Password(密码校验规则md5(md5(password)+salt))
	 * @return String
	 * @author 594cto版权所有
	 * @date 2019-12-29 23:25:23
	*/
	public String getPassword(){
		return password;
	}
	/**
	* 设置属性Password(密码校验规则md5(md5(password)+salt))
	* @param password
	* @return void
	* @author 594cto版权所有
	* @date  2019-12-29 23:25:23
	*/
	public void setPassword(String password ){
		this.password = password ;
	}
	/**
	 *  获取属性UserName(用户名)
	 * @return String
	 * @author 594cto版权所有
	 * @date 2019-12-29 23:25:23
	*/
	public String getUserName(){
		return userName;
	}
	/**
	* 设置属性UserName(用户名)
	* @param userName
	* @return void
	* @author 594cto版权所有
	* @date  2019-12-29 23:25:23
	*/
	public void setUserName(String userName ){
		this.userName = userName ;
	}
	/**
	 *  获取属性Status(用户状态(0锁定1正常))
	 * @return String
	 * @author 594cto版权所有
	 * @date 2019-12-29 23:25:23
	*/
	public String getStatus(){
		return status;
	}
	/**
	* 设置属性Status(用户状态(0锁定1正常))
	* @param status
	* @return void
	* @author 594cto版权所有
	* @date  2019-12-29 23:25:23
	*/
	public void setStatus(String status ){
		this.status = status ;
	}
	/**
	 *  获取属性UpdateTime(更新时间)
	 * @return Date
	 * @author 594cto版权所有
	 * @date 2019-12-29 23:25:23
	*/
	public Date getUpdateTime(){
		return updateTime;
	}
	/**
	* 设置属性UpdateTime(更新时间)
	* @param updateTime
	* @return void
	* @author 594cto版权所有
	* @date  2019-12-29 23:25:23
	*/
	public void setUpdateTime(Date updateTime ){
		this.updateTime = updateTime ;
	}
	/**
	 *  获取属性CreateTime(添加时间)
	 * @return Date
	 * @author 594cto版权所有
	 * @date 2019-12-29 23:25:23
	*/
	public Date getCreateTime(){
		return createTime;
	}
	/**
	* 设置属性CreateTime(添加时间)
	* @param createTime
	* @return void
	* @author 594cto版权所有
	* @date  2019-12-29 23:25:23
	*/
	public void setCreateTime(Date createTime ){
		this.createTime = createTime ;
	}
	/**
	 *  获取属性Uuid(UUID)
	 * @return String
	 * @author 594cto版权所有
	 * @date 2019-12-29 23:25:23
	*/
	public String getUuid(){
		return uuid;
	}
	/**
	* 设置属性Uuid(UUID)
	* @param uuid
	* @return void
	* @author 594cto版权所有
	* @date  2019-12-29 23:25:23
	*/
	public void setUuid(String uuid ){
		this.uuid = uuid ;
	}
	/**
	 *  获取属性Id(ID编号)
	 * @return Long
	 * @author 594cto版权所有
	 * @date 2019-12-29 23:25:23
	*/
	public Long getId(){
		return id;
	}
	/**
	* 设置属性Id(ID编号)
	* @param id
	* @return void
	* @author 594cto版权所有
	* @date  2019-12-29 23:25:23
	*/
	public void setId(Long id ){
		this.id = id ;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("DeleteFlag:").append(deleteFlag).append(" ");
		sb.append("LoginCount:").append(loginCount).append(" ");
		sb.append("LastLoginIp:").append(lastLoginIp).append(" ");
		sb.append("LastLoginTime:").append(lastLoginTime).append(" ");
		sb.append("Mobile:").append(mobile).append(" ");
		sb.append("Email:").append(email).append(" ");
		sb.append("Sex:").append(sex).append(" ");
		sb.append("Avator:").append(avator).append(" ");
		sb.append("NickName:").append(nickName).append(" ");
		sb.append("Salt:").append(salt).append(" ");
		sb.append("Password:").append(password).append(" ");
		sb.append("UserName:").append(userName).append(" ");
		sb.append("Status:").append(status).append(" ");
		sb.append("UpdateTime:").append(updateTime).append(" ");
		sb.append("CreateTime:").append(createTime).append(" ");
		sb.append("Uuid:").append(uuid).append(" ");
		sb.append("Id:").append(id).append(" ");
		return sb.toString();
	}
}
