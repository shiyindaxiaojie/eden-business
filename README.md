<img src="https://cdn.jsdelivr.net/gh/eden-lab/eden-lab-images/readme/icon.png" align="right" />

[license-apache2.0]:https://www.apache.org/licenses/LICENSE-2.0.html

[github-action]:https://github.com/eden-lab/eden-solution/actions

[sonarcloud-dashboard]:https://sonarcloud.io/dashboard?id=eden-lab_eden-solution

# Eden* Architect

![](https://cdn.jsdelivr.net/gh/eden-lab/eden-lab-images/readme/language-java-blue.svg) [![](https://cdn.jsdelivr.net/gh/eden-lab/eden-lab-images/readme/license-apache2.0-red.svg)][license-apache2.0] [![](https://github.com/eden-lab/eden-solution/workflows/build/badge.svg)][github-action] [![](https://sonarcloud.io/api/project_badges/measure?project=eden-lab_eden-solution&metric=alert_status)][sonarcloud-dashboard]

Eden* Solution 致力于提供企业开发的一站式解决方案。此项目包含开发分布式应用服务的必需组件，您只需要添加一些注解和少量配置，就可以将 Spring Boot 应用接入微服务解决方案，通过中间件来迅速搭建分布式应用系统。

> 参考文档请查看 [WIKI](https://github.com/eden-lab/eden-solution/wiki) 。

## 主要功能

* **腾讯云组件集成**：提供了腾讯云产品常用的 `COS 对象存储`、`VOD 云点播`、`TMS 敏感词过滤`、`TPNS 推送`、`CMQ 消息队列`等组件的集成，引入相关 `Spring Boot Starter`
  开启自动装配能力。

## 组件构成

![](https://cdn.jsdelivr.net/gh/eden-lab/eden-lab-images/eden-solution/component.png)

* **eden-qcloud-spring-boot-starters**: 腾讯云组件
* **eden-aliyun-spring-boot-starters**: 阿里云集成

## 如何构建

* master 分支对应的是 `Spring Boot 2.4.x`，最低支持 JDK 1.8。
* 1.5.x 分支对应的是 `Spring Boot 1.5.x`，最低支持 JDK 1.8。
* 2.4.x 分支对应的是 `Spring Boot 2.4.x`，最低支持 JDK 1.8。

本项目使用 Maven 来构建，最快的使用方式是 clone 到本地，然后执行以下命令：

```bash
./mvnw install
```

执行完毕后，项目将被安装到本地 Maven 仓库。

## 如何使用

### 如何集成到您的服务

如果需要使用已发布的版本，在 `parent` 中添加如下配置。

```xml
<parent>
    <groupId>org.ylzl</groupId>
    <artifactId>eden-parent</artifactId>
    <version>2.4.13.RELEASE</version>
    <relativePath/>
</parent>
```

然后在 `dependencies` 中添加自己所需使用的依赖即可使用，例如，引入腾讯云 COS 组件。

```xml
<dependency>
    <groupId>org.ylzl</groupId>
    <artifactId>eden-cos-spring-boot-starter</artifactId>
</dependency>
```

> 请注意，我们已经把常用的依赖纳入 eden-dependencies 管理，不建议带版本号覆盖原有的依赖。

## 版本规范

项目的版本号格式为 x.x.x 的形式，其中 x 的数值类型为数字，从 0 开始取值，且不限于 0~9 这个范围。项目处于孵化器阶段时，第一位版本号固定使用 0，即版本号为 0.x.x 的格式。

由于 `Spring Boot 1.5.x` 和 `Spring Boot 2.4.x` 在架构层面有很大的变更，因此我们采取跟 Spring Boot 版本号一致的版本:

* 1.5.x 版本适用于 `Spring Boot 1.5.x`
* 2.4.x 版本适用于 `Spring Boot 2.4.x`
