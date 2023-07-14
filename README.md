# 前后端分离的火车票管理系统
# 项目简介

## 0.简介

为本人软件工程项目实训阶段所写，由于项目的开发周期为3天，所以有些地方写的就比较的粗糙，比如在涉及到登录部分的时候，在密码校验等等的上面都是直接使用的明文传输，没有加密等等，所涉及的后端功能也就是对乘客的增删改查，以及对车票信息的增删改查，在增删改查的过程中很多地方也是比较的粗糙，比如在增加车票信息的时候对于发车时间和到站时候是在sql中直接写死的.

前端界面展示

![image-20230714220641264](C:\Users\XIAOXIN\AppData\Roaming\Typora\typora-user-images\image-20230714220641264.png)

![image-20230714220832089](C:\Users\XIAOXIN\AppData\Roaming\Typora\typora-user-images\image-20230714220832089.png)

![image-20230714220853005](C:\Users\XIAOXIN\AppData\Roaming\Typora\typora-user-images\image-20230714220853005.png)



## 1.使用工具版本

jdk1.8.idea2020，tomcat8以上，mysql5.7，nodejs8

## 2.技术栈

sevlet+jdbc+vue2+elementUI

本项目的后端使用的技术栈是比较底层的sevlet和jdbc，个人觉得在学习使用springboot和mybaties框架的时候，得首先会使用这两个底层的东西，理解他们是怎么工作的。对于后续这个项目的优化和更新会在后续的时间里面来完成

由于前端技术的更新迭代比较块，所以在这里我就没有使用easyui和jsp，而是使用了大家都比价树枝的vue和elmentUI来实现前端部分

## 3.配置步骤

### 后端

需要的所有的jar包都是直接存放在lib库里面，这些库在这里加上之后

![image-20230714215757232](C:\Users\XIAOXIN\AppData\Roaming\Typora\typora-user-images\image-20230714215757232.png)

需要点击jar包让项目可以能够识别这些依赖

![image-20230714220407521](C:\Users\XIAOXIN\AppData\Roaming\Typora\typora-user-images\image-20230714220407521.png)

之后后端基本上就可以跑起来

### 前端

前端安装配置的过程

```
npm install
npm run serve
```

前端实用的是vue，以及axios这个ajax框架来实现的



