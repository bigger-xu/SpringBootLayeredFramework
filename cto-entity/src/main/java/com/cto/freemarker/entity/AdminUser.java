/*
 * @(#)  AdminUserVo.java    2019-06-05 10:16:11
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.entity;

import com.cto.freemarker.entity.base.BaseEntity;

import java.util.Date;

/**
 * 文件名AdminUser.java
 * @author 1
 * @date 2019-06-05 10:16:11
 *  
 */
public class AdminUser extends BaseEntity {
    
	/**
	 * 默认构造函数
	 * @author 1
	 * @date 2019-06-05 10:16:11
	 */
	public AdminUser() {

	}
	/**
	 * 属性Id(ID编号)
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
	 * 属性UserType(用户类型(1个人；2餐饮机构 ；3分销商))
	 */
	private String userType;
	/**
	 * 属性Status(用户状态(0锁定1正常))
	 */
	private String status;
	/**
	 * 属性UserName(用户名)
	 */
	private String userName;
	/**
	 * 属性Password(密码校验规则md5(md5(password)+salt))
	 */
	private String password;
	/**
	 * 属性Salt(盐值)
	 */
	private String salt;
	/**
	 * 属性NickName(昵称)
	 */
	private String nickName;
	/**
	 * 属性Avator(用户头像)
	 */
	private String avator;
	/**
	 * 属性LastLoginTime(最后一次登录时间)
	 */
	private Date lastLoginTime;
	/**
	 * 属性LastLoginIp(最后一次登录IP)
	 */
	private String lastLoginIp;
	/**
	 * 属性LoginCount(登录次数)
	 */
	private Integer loginCount;
	/**
	 * 属性Province(省)
	 */
	private Integer province;
	/**
	 * 属性City(市)
	 */
	private Integer city;
	/**
	 * 属性District(县)
	 */
	private Integer district;
	/**
	 * 属性Sex(性别)
	 */
	private String sex;
	/**
	 * 属性Email()
	 */
	private String email;
	/**
	 * 属性Mobile()
	 */
	private String mobile;
	/**
	 * 属性Address()
	 */
	private String address;
	/**
	 * 属性DeleteFlag(删除状态)
	 */
	private String deleteFlag;

	/**
	 *  获取属性Id(ID编号)
	 * @return Long
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public Long getId(){
		return id;
	}
	/**
	* 设置属性Id(ID编号)
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
	 *  获取属性UserType(用户类型(1个人；2餐饮机构 ；3分销商))
	 * @return String
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public String getUserType(){
		return userType;
	}
	/**
	* 设置属性UserType(用户类型(1个人；2餐饮机构 ；3分销商))
	* @param userType
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setUserType(String userType ){
		this.userType = userType ;
	}
	/**
	 *  获取属性Status(用户状态(0锁定1正常))
	 * @return String
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public String getStatus(){
		return status;
	}
	/**
	* 设置属性Status(用户状态(0锁定1正常))
	* @param status
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setStatus(String status ){
		this.status = status ;
	}
	/**
	 *  获取属性UserName(用户名)
	 * @return String
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public String getUserName(){
		return userName;
	}
	/**
	* 设置属性UserName(用户名)
	* @param userName
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setUserName(String userName ){
		this.userName = userName ;
	}
	/**
	 *  获取属性Password(密码校验规则md5(md5(password)+salt))
	 * @return String
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public String getPassword(){
		return password;
	}
	/**
	* 设置属性Password(密码校验规则md5(md5(password)+salt))
	* @param password
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setPassword(String password ){
		this.password = password ;
	}
	/**
	 *  获取属性Salt(盐值)
	 * @return String
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public String getSalt(){
		return salt;
	}
	/**
	* 设置属性Salt(盐值)
	* @param salt
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setSalt(String salt ){
		this.salt = salt ;
	}
	/**
	 *  获取属性NickName(昵称)
	 * @return String
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public String getNickName(){
		return nickName;
	}
	/**
	* 设置属性NickName(昵称)
	* @param nickName
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setNickName(String nickName ){
		this.nickName = nickName ;
	}
	/**
	 *  获取属性Avator(用户头像)
	 * @return String
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public String getAvator(){
		return avator;
	}
	/**
	* 设置属性Avator(用户头像)
	* @param avator
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setAvator(String avator ){
		this.avator = avator ;
	}
	/**
	 *  获取属性LastLoginTime(最后一次登录时间)
	 * @return Date
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public Date getLastLoginTime(){
		return lastLoginTime;
	}
	/**
	* 设置属性LastLoginTime(最后一次登录时间)
	* @param lastLoginTime
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setLastLoginTime(Date lastLoginTime ){
		this.lastLoginTime = lastLoginTime ;
	}
	/**
	 *  获取属性LastLoginIp(最后一次登录IP)
	 * @return String
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public String getLastLoginIp(){
		return lastLoginIp;
	}
	/**
	* 设置属性LastLoginIp(最后一次登录IP)
	* @param lastLoginIp
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setLastLoginIp(String lastLoginIp ){
		this.lastLoginIp = lastLoginIp ;
	}
	/**
	 *  获取属性LoginCount(登录次数)
	 * @return Integer
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public Integer getLoginCount(){
		return loginCount;
	}
	/**
	* 设置属性LoginCount(登录次数)
	* @param loginCount
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setLoginCount(Integer loginCount ){
		this.loginCount = loginCount ;
	}
	/**
	 *  获取属性Province(省)
	 * @return Integer
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public Integer getProvince(){
		return province;
	}
	/**
	* 设置属性Province(省)
	* @param province
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setProvince(Integer province ){
		this.province = province ;
	}
	/**
	 *  获取属性City(市)
	 * @return Integer
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public Integer getCity(){
		return city;
	}
	/**
	* 设置属性City(市)
	* @param city
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setCity(Integer city ){
		this.city = city ;
	}
	/**
	 *  获取属性District(县)
	 * @return Integer
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public Integer getDistrict(){
		return district;
	}
	/**
	* 设置属性District(县)
	* @param district
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setDistrict(Integer district ){
		this.district = district ;
	}
	/**
	 *  获取属性Sex(性别)
	 * @return String
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public String getSex(){
		return sex;
	}
	/**
	* 设置属性Sex(性别)
	* @param sex
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setSex(String sex ){
		this.sex = sex ;
	}
	/**
	 *  获取属性Email()
	 * @return String
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public String getEmail(){
		return email;
	}
	/**
	* 设置属性Email()
	* @param email
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setEmail(String email ){
		this.email = email ;
	}
	/**
	 *  获取属性Mobile()
	 * @return String
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public String getMobile(){
		return mobile;
	}
	/**
	* 设置属性Mobile()
	* @param mobile
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setMobile(String mobile ){
		this.mobile = mobile ;
	}
	/**
	 *  获取属性Address()
	 * @return String
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public String getAddress(){
		return address;
	}
	/**
	* 设置属性Address()
	* @param address
	* @return void
	* @author 1
	* @date  2019-06-05 10:16:11
	*/
	public void setAddress(String address ){
		this.address = address ;
	}
	/**
	 *  获取属性DeleteFlag(删除状态)
	 * @return String
	 * @author 1
	 * @date 2019-06-05 10:16:11
	*/
	public String getDeleteFlag(){
		return deleteFlag;
	}
	/**
	* 设置属性DeleteFlag(删除状态)
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
		sb.append("Id(ID编号):").append(id).append(" ");
		sb.append("Uuid():").append(uuid).append(" ");
		sb.append("AddTime(添加时间):").append(addTime).append(" ");
		sb.append("AddUserId(添加人ID):").append(addUserId).append(" ");
		sb.append("UpdateTime(更新时间):").append(updateTime).append(" ");
		sb.append("UpdateUserId(更新人ID):").append(updateUserId).append(" ");
		sb.append("UserType(用户类型(1个人；2餐饮机构 ；3分销商)):").append(userType).append(" ");
		sb.append("Status(用户状态(0锁定1正常)):").append(status).append(" ");
		sb.append("UserName(用户名):").append(userName).append(" ");
		sb.append("Password(密码校验规则md5(md5(password)+salt)):").append(password).append(" ");
		sb.append("Salt(盐值):").append(salt).append(" ");
		sb.append("NickName(昵称):").append(nickName).append(" ");
		sb.append("Avator(用户头像):").append(avator).append(" ");
		sb.append("LastLoginTime(最后一次登录时间):").append(lastLoginTime).append(" ");
		sb.append("LastLoginIp(最后一次登录IP):").append(lastLoginIp).append(" ");
		sb.append("LoginCount(登录次数):").append(loginCount).append(" ");
		sb.append("Province(省):").append(province).append(" ");
		sb.append("City(市):").append(city).append(" ");
		sb.append("District(县):").append(district).append(" ");
		sb.append("Sex(性别):").append(sex).append(" ");
		sb.append("Email():").append(email).append(" ");
		sb.append("Mobile():").append(mobile).append(" ");
		sb.append("Address():").append(address).append(" ");
		sb.append("DeleteFlag(删除状态):").append(deleteFlag).append(" ");
		return sb.toString();
	}
}
