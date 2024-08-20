package com.efreight.common.config;

import com.efreight.common.constants.ResultCode;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author 张永伟
 * @since 2023/5/4
 */
@Component
public class ResultCodeRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        ResultCode.init();
    }
}
