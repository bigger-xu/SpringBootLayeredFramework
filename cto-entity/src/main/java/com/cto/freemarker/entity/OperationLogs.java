/*
 * @(#)  OperationLogsVo.java    2019-11-20 22:41:51
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.entity;

import com.cto.freemarker.entity.base.BaseEntity;
import java.util.Date;

/**
 * 文件名OperationLogs.java
 * @author Zhang Yongwei
 * @date 2019-11-20 22:41:51
 *  
 */
public class OperationLogs extends BaseEntity{
    
	/**
	 * 默认构造函数
	 * @author Zhang Yongwei
	 * @date 2019-11-20 22:41:51
	 */
	public OperationLogs() {

	}
	/**
	 * 属性IpAddress(IP地址)
	 */
	private String ipAddress;
	/**
	 * 属性Type(日志类型)
	 */
	private String type;
	/**
	 * 属性ReturnValue(返回数据)
	 */
	private String returnValue;
	/**
	 * 属性RunTime(运行时长)
	 */
	private Long runTime;
	/**
	 * 属性Leaves(日志级别)
	 */
	private Integer leaves;
	/**
	 * 属性RequestParams(请求参数)
	 */
	private String requestParams;
	/**
	 * 属性Description(日志描述)
	 */
	private String description;
	/**
	 * 属性RequestType(请求方式)
	 */
	private String requestType;
	/**
	 * 属性RequestUrl(请求URL)
	 */
	private String requestUrl;
	/**
	 * 属性UpdateTime(更新时间)
	 */
	private Date updateTime;
	/**
	 * 属性CreateTime(添加时间)
	 */
	private Date createTime;
	/**
	 * 属性CreateUserId(添加人ID)
	 */
	private Long createUserId;
	/**
	 * 属性Uuid(UUID)
	 */
	private String uuid;
	/**
	 * 属性Id()
	 */
	private Long id;

	/**
	 *  获取属性IpAddress(IP地址)
	 * @return String
	 * @author Zhang Yongwei
	 * @date 2019-11-20 22:41:51
	*/
	public String getIpAddress(){
		return ipAddress;
	}
	/**
	* 设置属性IpAddress(IP地址)
	* @param ipAddress
	* @return void
	* @author Zhang Yongwei
	* @date  2019-11-20 22:41:51
	*/
	public void setIpAddress(String ipAddress ){
		this.ipAddress = ipAddress ;
	}
	/**
	 *  获取属性Type(日志类型)
	 * @return String
	 * @author Zhang Yongwei
	 * @date 2019-11-20 22:41:51
	*/
	public String getType(){
		return type;
	}
	/**
	* 设置属性Type(日志类型)
	* @param type
	* @return void
	* @author Zhang Yongwei
	* @date  2019-11-20 22:41:51
	*/
	public void setType(String type ){
		this.type = type ;
	}
	/**
	 *  获取属性ReturnValue(返回数据)
	 * @return String
	 * @author Zhang Yongwei
	 * @date 2019-11-20 22:41:51
	*/
	public String getReturnValue(){
		return returnValue;
	}
	/**
	* 设置属性ReturnValue(返回数据)
	* @param returnValue
	* @return void
	* @author Zhang Yongwei
	* @date  2019-11-20 22:41:51
	*/
	public void setReturnValue(String returnValue ){
		this.returnValue = returnValue ;
	}
	/**
	 *  获取属性RunTime(运行时长)
	 * @return Long
	 * @author Zhang Yongwei
	 * @date 2019-11-20 22:41:51
	*/
	public Long getRunTime(){
		return runTime;
	}
	/**
	* 设置属性RunTime(运行时长)
	* @param runTime
	* @return void
	* @author Zhang Yongwei
	* @date  2019-11-20 22:41:51
	*/
	public void setRunTime(Long runTime ){
		this.runTime = runTime ;
	}
	/**
	 *  获取属性Leaves(日志级别)
	 * @return Integer
	 * @author Zhang Yongwei
	 * @date 2019-11-20 22:41:51
	*/
	public Integer getLeaves(){
		return leaves;
	}
	/**
	* 设置属性Leaves(日志级别)
	* @param leaves
	* @return void
	* @author Zhang Yongwei
	* @date  2019-11-20 22:41:51
	*/
	public void setLeaves(Integer leaves ){
		this.leaves = leaves ;
	}
	/**
	 *  获取属性RequestParams(请求参数)
	 * @return String
	 * @author Zhang Yongwei
	 * @date 2019-11-20 22:41:51
	*/
	public String getRequestParams(){
		return requestParams;
	}
	/**
	* 设置属性RequestParams(请求参数)
	* @param requestParams
	* @return void
	* @author Zhang Yongwei
	* @date  2019-11-20 22:41:51
	*/
	public void setRequestParams(String requestParams ){
		this.requestParams = requestParams ;
	}
	/**
	 *  获取属性Description(日志描述)
	 * @return String
	 * @author Zhang Yongwei
	 * @date 2019-11-20 22:41:51
	*/
	public String getDescription(){
		return description;
	}
	/**
	* 设置属性Description(日志描述)
	* @param description
	* @return void
	* @author Zhang Yongwei
	* @date  2019-11-20 22:41:51
	*/
	public void setDescription(String description ){
		this.description = description ;
	}
	/**
	 *  获取属性RequestType(请求方式)
	 * @return String
	 * @author Zhang Yongwei
	 * @date 2019-11-20 22:41:51
	*/
	public String getRequestType(){
		return requestType;
	}
	/**
	* 设置属性RequestType(请求方式)
	* @param requestType
	* @return void
	* @author Zhang Yongwei
	* @date  2019-11-20 22:41:51
	*/
	public void setRequestType(String requestType ){
		this.requestType = requestType ;
	}
	/**
	 *  获取属性RequestUrl(请求URL)
	 * @return String
	 * @author Zhang Yongwei
	 * @date 2019-11-20 22:41:51
	*/
	public String getRequestUrl(){
		return requestUrl;
	}
	/**
	* 设置属性RequestUrl(请求URL)
	* @param requestUrl
	* @return void
	* @author Zhang Yongwei
	* @date  2019-11-20 22:41:51
	*/
	public void setRequestUrl(String requestUrl ){
		this.requestUrl = requestUrl ;
	}
	/**
	 *  获取属性UpdateTime(更新时间)
	 * @return Date
	 * @author Zhang Yongwei
	 * @date 2019-11-20 22:41:51
	*/
	public Date getUpdateTime(){
		return updateTime;
	}
	/**
	* 设置属性UpdateTime(更新时间)
	* @param updateTime
	* @return void
	* @author Zhang Yongwei
	* @date  2019-11-20 22:41:51
	*/
	public void setUpdateTime(Date updateTime ){
		this.updateTime = updateTime ;
	}
	/**
	 *  获取属性CreateTime(添加时间)
	 * @return Date
	 * @author Zhang Yongwei
	 * @date 2019-11-20 22:41:51
	*/
	public Date getCreateTime(){
		return createTime;
	}
	/**
	* 设置属性CreateTime(添加时间)
	* @param createTime
	* @return void
	* @author Zhang Yongwei
	* @date  2019-11-20 22:41:51
	*/
	public void setCreateTime(Date createTime ){
		this.createTime = createTime ;
	}
	/**
	 *  获取属性CreateUserId(添加人ID)
	 * @return Long
	 * @author Zhang Yongwei
	 * @date 2019-11-20 22:41:51
	*/
	public Long getCreateUserId(){
		return createUserId;
	}
	/**
	* 设置属性CreateUserId(添加人ID)
	* @param createUserId
	* @return void
	* @author Zhang Yongwei
	* @date  2019-11-20 22:41:51
	*/
	public void setCreateUserId(Long createUserId ){
		this.createUserId = createUserId ;
	}
	/**
	 *  获取属性Uuid(UUID)
	 * @return String
	 * @author Zhang Yongwei
	 * @date 2019-11-20 22:41:51
	*/
	public String getUuid(){
		return uuid;
	}
	/**
	* 设置属性Uuid(UUID)
	* @param uuid
	* @return void
	* @author Zhang Yongwei
	* @date  2019-11-20 22:41:51
	*/
	public void setUuid(String uuid ){
		this.uuid = uuid ;
	}
	/**
	 *  获取属性Id()
	 * @return Long
	 * @author Zhang Yongwei
	 * @date 2019-11-20 22:41:51
	*/
	public Long getId(){
		return id;
	}
	/**
	* 设置属性Id()
	* @param id
	* @return void
	* @author Zhang Yongwei
	* @date  2019-11-20 22:41:51
	*/
	public void setId(Long id ){
		this.id = id ;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("IpAddress:").append(ipAddress).append(" ");
		sb.append("Type:").append(type).append(" ");
		sb.append("ReturnValue:").append(returnValue).append(" ");
		sb.append("RunTime:").append(runTime).append(" ");
		sb.append("Leaves:").append(leaves).append(" ");
		sb.append("RequestParams:").append(requestParams).append(" ");
		sb.append("Description:").append(description).append(" ");
		sb.append("RequestType:").append(requestType).append(" ");
		sb.append("RequestUrl:").append(requestUrl).append(" ");
		sb.append("UpdateTime:").append(updateTime).append(" ");
		sb.append("CreateTime:").append(createTime).append(" ");
		sb.append("CreateUserId:").append(createUserId).append(" ");
		sb.append("Uuid:").append(uuid).append(" ");
		sb.append("Id:").append(id).append(" ");
		return sb.toString();
	}
}
