package com.efreight.base.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.druid.pool.DruidDataSource;
import com.efreight.common.constants.BaseResultCode;
import com.efreight.common.exception.EfreightBizException;
import com.efreight.common.utils.RsaUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 数据源
 * @author ZhangYongWei
 * @since 2023/12/22
 */
@Slf4j
public class ViewsDruidDataSource {
    /** 数据源集合 */
    private static final Map<String, DruidDataSource> DRUID_DATA_SOURCE_MAP = new ConcurrentHashMap<>();
    
    public DruidDataSource getDruidDataSource(String key, String url, String userName, String password){
        log.info("==> 自定义数据源连接池请求 --> 方法名:【getDruidDataSource】--> 参数:key = {}, map = {}", key, DRUID_DATA_SOURCE_MAP.keySet());
        DruidDataSource source = DRUID_DATA_SOURCE_MAP.get(key);
        if(source == null){
            source = createDruidDataSource(url, userName, password);
        }
        DRUID_DATA_SOURCE_MAP.put(key, source);
        return source;
    }
    
    public DruidDataSource createDruidDataSource(String url, String userName, String password) {
        //连接池对象
        try(DruidDataSource druidDataSource = new DruidDataSource()){
            //设置参数
            log.info("==> 初始化自定义数据源连接池 --> 参数:url = {}", url);
            druidDataSource.setUrl(url);
            druidDataSource.setUsername(userName);
            druidDataSource.setPassword(RsaUtil.defaultDecryptByPublicKey(password));
            //非必须 初始化连接数量，最大的连接数量
            druidDataSource.setInitialSize(16);
            druidDataSource.setMaxActive(32);
            druidDataSource.setMaxWait(60000);
            druidDataSource.setConnectionErrorRetryAttempts(0);
            druidDataSource.setBreakAfterAcquireFailure(true);
            return druidDataSource;
        }catch (Exception e){
            throw new EfreightBizException(BaseResultCode.BASE_GLOBAL_MATE_DATA_ERROR);
        }
    }
    
}
