一、后台框架使用技术
    zookeeper  --服务治理
    dubbo-admin --服务管理  http://localhost:82

    springboot   --集成框架
    dubbo   --微服务开发框架
    swagger2  --接口文档管理、接口测试  http://localhost:81/swagger-ui.html

    mybatis-plus --数据库操作框架
    durid --连接池管理

    lombok --代码简化框架

    todo shiro --权限管理
    todo redis --jwt session缓存、数据缓存
    todo quartz --定时任务
    todo fastDFS --分布式文件存储


二、项目目录构成
    generate --代码生成项目
    sysmanage  --系统管理项目  http://localhost:81
        sysmanage-common 存放bean与service接口
        sysmanage-controller 存放controller，为服务消费者
        sysmanage-service 存放service实现，mapper数据操作，为服务提供者