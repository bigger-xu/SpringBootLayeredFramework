
### 系统架构版本说明

| 名称                   | JAVA版本         | 服务端版本 |
|:---------------------|:---------------|:------|
| spring-cloud         | Hoxton.SR12    | -     |
| Spring Cloud alibaba | 2.2.9.RELEASE  | -     |
| spring-boot          | 2.3.12.RELEASE | -     |
| nacos                | 2.2.9.RELEASE  | 2.1.0 |
| sentinel             | 2.2.9.RELEASE  | 1.8.5 |
| rocketmq             | 4.9.4          | 4.9.4 |
| xxx-job              | 2.3.1          | 2.3.1 |

---

### 模块介绍

##### e-base  AE/AI订单相关

> - ###### base-feign  对外提供的Feign接口，其他服务若需要，只需引用该模块
> - ###### base-model  base服务涉及到的扩展实体类信息
> - ###### base-server  base服务端，业务逻辑都在此模块

##### e-common  全局公共的东西

> - ###### e-common-core  核心公共组件及配置类，必须引用
> - ###### e-common-message 邮件/短信发送工具类，同步调用需引入该模块
> - ###### e-common-mq  异步消息模块，涉及到Mq的服务需要引用，MQ消息非公共的在服务内编写，公共的MQ消息写在该模块，比如异步发送邮件、短信等

##### e-crm  CRM

> ###### 模块结构同e-base

##### e-ffm  舱单

> ###### 模块结构同e-base

##### e-gateway  网关

> ###### 模块结构同e-base

##### e-hrs  用户权限/菜单

> ###### 模块结构同e-base

##### e-tms

> ###### 模块结构同e-base

##### e-wms  货站

> ###### 模块结构同e-base

##### generator  代码生成

> ###### Mybatis-Plus代码生成
---

### 打包方式

```javascript
mvn - Dmaven.test.skip = true clean install
```