<div align="center">
<img src="https://cdn.mate.vip/matecloud.svg" width="220">

[![个人信息](https://img.shields.io/badge/author-迈特云-blue.svg)](http://www.mate.vip/)
[![项目交流群](https://img.shields.io/badge/chat-项目交流群-green.svg)](https://jq.qq.com/?_wv=1027&k=oYxVM3uV)
![JDK Version](https://img.shields.io/badge/JAVA-JDK17+-red.svg)
<br/>
[![MateBoot](https://img.shields.io/badge/MateBoot-1.0.8-green.svg?lable=mateboot&logo=mega)](https://gitee.com/matevip/mateboot)
[![Spring Boot](https://img.shields.io/maven-central/v/org.springframework.boot/spring-boot-dependencies.svg?label=Spring%20Boot&logo=Spring)](https://search.maven.org/artifact/org.springframework.boot/spring-boot-dependencies)
[![Spring Cloud](https://img.shields.io/maven-central/v/cn.dev33/sa-token-spring-boot3-starter.svg?label=Sa%20Token&logo=springsecurity)](https://search.maven.org/artifact/cn.dev33/sa-token-spring-boot3-starter)
[![Spring Cloud Alibaba](https://img.shields.io/maven-central/v/com.baomidou/mybatis-plus-boot-starter.svg?label=Mybatis%20Plus&logo=qlik)](https://search.maven.org/artifact/com.baomidou/mybatis-plus-boot-starter)

[🔥 演示地址](https://boot.mate.vip) • [⚙️ 安装帮助](https://docs.mate.vip) • [📚 在线文档](https://docs.mate.vip) • [💬 需求收集](https://gitee.com/matevip/mateboot/issues/new)
</div>

--------------------------

<div align="center">⭐️ 如果你喜欢 MateBoot，请给它一个 Star，您的支持将是我们前行的动力。</div>

--------------------------
## 平台说明

MateBoot是一个基于SpringBoot3的快速开发平台，采用前后端分离的模式，前端采用Element Plus组件，后端采用SpringBoot3、Sa-token、Mybatis-Plus、Redis、RabbitMQ、FastDFS等核心技术，同时提供了代码生成器，能够快速生成前后端代码，减少开发量，提高开发效率。

## 项目特点
1. 极简的代码，强大的功能，开箱即用，开发效率高，学习成本低。
2. 主流技术栈，SpringBoot3、SA-TOKEN认证、Mybatis-Plus、Redis、RabbitMQ、FastDFS等。
3. 强大的本地代码生成器，后端代码快速生成，降低代码量。
4. 前端采用Element Plus组件，简单易用，开发效率高。

## 后端项目启动
1. 创建数据库，导入`mate-bootstrap/src/main/resources/_sql`目录下的SQL文件。
2. 修改`mate-bootstrap/src/main/resources/application-dev.yml`文件中的数据库配置。
3. 安装Redis，修改`mate-bootstrap/src/main/resources/application-dev.yml`文件中的Redis配置。
4. 启动`mate-bootstrap/src/main/java/vip/mate/bootstrap/MateBootstrapApplication.java`文件。
5. 后台访问地址：http://localhost:8888/doc.html 查看文档

## 前端项目启动
1. 安装Node.js，推荐使用最新稳定版14+。
2. 进入目录mate-ui-admin，执行`yarn install`安装依赖。
3. 执行`yarn serve`启动项目。

## 项目结构
``` lua
mateboot
├── mate-core -- 核心模块
├── mate-platform -- 平台模块
│   ├─mate-system -- 系统模块
│   ├─mate-demo -- 演示模块
├── mate-plugin -- 插件模块
│   ├─mate-code -- 代码生成插件  
├── mate-bootstrap -- 启动模块   
├── mate-ui-admin -- 后台管理前端
```

## 项目文档
- [项目文档](https://docs.mate.vip)
- [在线演示](https://boot.mate.vip)
- [项目交流群](https://jq.qq.com/?_wv=1027&k=oYxVM3uV)
- [需求收集](https://gitee.com/matevip/mateboot/issues/new)
- [更新日志](https://gitee.com/matevip/mateboot/releases)

## GIT Commit 提交规范

- feat: 新功能、新特性
- fix: 修改 bug
- perf: 更改代码，以提高性能（在不影响代码内部行为的前提下，对程序性能进行优化）
- refactor: 代码重构（重构，在不影响代码内部行为、功能下的代码修改）
- docs: 文档修改
- style: 代码格式修改, 注意不是 css 修改（例如分号修改）
- test: 测试用例新增、修改
- build: 影响项目构建或依赖项修改
- revert: 恢复上一次提交
- ci: 持续集成相关文件修改
- chore: 其他修改（不在上述类型中的修改）
- release: 发布新版本
- workflow: 工作流相关文件修改

