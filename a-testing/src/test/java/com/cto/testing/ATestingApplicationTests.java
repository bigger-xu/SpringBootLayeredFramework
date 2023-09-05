package com.cto.testing;

import com.cto.testing.operator.EfreightExpressRunner;
import com.ql.util.express.DefaultContext;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ATestingApplicationTests {

    @Test
    void contextLoads() {
    }


    @Test
    public void testMethod() throws Exception {
        EfreightExpressRunner runner = new EfreightExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        Object r1 = runner.execute("IF(SUM(1,2,3) < 5){RETURN '海运'}ELSE{return '空运'}", context, null, false, false);
        Object r2 = runner.execute("SUM(1,2.44,3.11)", context, null, false, false);
        Object r3 = runner.execute("ROUND(VERAGE(1,2.44,3.11), 2)", context, null, false, false);
        Object r4 = runner.execute("NOW()", context, null, false, false);
        Object r5 = runner.execute("DATE()", context, null, false, false);
        Object r6 = runner.execute("YEAR()", context, null, false, false);
        Object r7 = runner.execute("WEEKDAY()", context, null, false, false);
        Object r8 = runner.execute("DATE('2022-08-01 12:00:00')", context, null, false, false);
        Object r9 = runner.execute("DATEDIF('2022-08-01','2023-08-01','Y')", context, null, false, false);
        Object r10 = runner.execute("DATEDIF('2022-08-01','2023-08-11','D')", context, null, false, false);
        Object r11 = runner.execute("MAX(1,244,3,11)", context, null, false, false);
        Object r12 = runner.execute("MIN(1,244,3,11)", context, null, false, false);
        Object r13 = runner.execute("LEFT('我是中国人', 2)", context, null, false, false);
        Object r14 = runner.execute("RIGHT('我是中国人', 3)", context, null, false, false);
        Object r15 = runner.execute("LEN('我是中国人')", context, null, false, false);
        System.out.println("条件返回结果==>  " + r1);
        System.out.println("求和返回结果==>  " + r2);
        System.out.println("平均值后四舍五入返回结果==>  " + r3);
        System.out.println("当前时间返回结果==>  " + r4);
        System.out.println("当前日期返回结果==>  " + r5);
        System.out.println("当前年返回结果==>  " + r6);
        System.out.println("当前星期返回结果==>  " + r7);
        System.out.println("指定日期返回结果==>  " + r8);
        System.out.println("年差数返回结果==>  " + r9);
        System.out.println("日差数返回结果==>  " + r10);
        System.out.println("最大值返回结果==>  " + r11);
        System.out.println("最小值返回结果==>  " + r12);
        System.out.println("左截取返回结果==>  " + r13);
        System.out.println("右截取返回结果==>  " + r14);
        System.out.println("右截取返回结果==>  " + r15);
    }

    @Test
    public void testLong() throws Exception {
        EfreightExpressRunner runner = new EfreightExpressRunner();
        DefaultContext<String, Object> expressContext = new DefaultContext<String, Object>();
        expressContext.put("##始发港##","4");
        expressContext.put("##出发港##","ABC");
        expressContext.put("##目的港##","PPP1");
        /*String express = "IF(##始发港## == 'ABC' || ##出发港## == 'ABC'){\n"
                +        "    IF(SUM(1,2,3,4) >= 10 && ##目的港## == 'PPP'){\n"
                +        "        RETURN ROUND(VERAGE(3.33,1.231,33,11,56.11), 2)\n"
                +        "    }ELSE{\n"
                +        "        RETURN ROUND(12.33 * 9.87 + 3 - 10 / 5, 2)\n"
                +        "    }\n"
                +        "}ELSE{\n"
                +        "    RETURN WEEKDAY()\n"
                +        "}";
        System.out.println("需要执行的公式是===> \n" + express);
        Object result = runner.execute(express, expressContext, null, false, false);
        System.out.println("返回的结果是===> " + result);*/

        //Object result1 = runner.execute("IF(##始发港## == 'ABC'){RETURN 2.00}ELSE{RETURN 0} + IF(##始发港## == 'ABCD'){RETURN 2.00}ELSE{RETURN 1}", expressContext, null, false, false);
        //Object result1 = runner.execute("ROUND((##始发港## == 'ABC'?2.14:4) + (##目的港## == 'PPP'?2.18:4), 1)", expressContext, null, false, false);
        Object result1 = runner.execute("##始发港## > 10", expressContext, null, false, false);
        System.out.println("返回的结果是===> " + result1);

    }
}
