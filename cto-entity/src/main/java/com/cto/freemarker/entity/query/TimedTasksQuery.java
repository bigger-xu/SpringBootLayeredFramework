/*
 * @(#)  TimedTasksVo.java    2020-10-02 01:13:26
 * Project  :Spring boot 代码生产系统
 * Company  :http://www.594cto.com
 */
package com.cto.freemarker.entity.query;

import com.cto.freemarker.entity.TimedTasks;
import lombok.Data;

/**
 * 系统菜单表 TimedTasksQuery.java 检索类
 * 
 * @author 594cto版权所有
 * @date 2020-10-02 01:13:26
 */
@Data
public class TimedTasksQuery extends TimedTasks {
    private Integer pageNum = 1;
    private Integer pageSize = 10;

}
