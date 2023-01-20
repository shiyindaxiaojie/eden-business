<img src="https://cdn.jsdelivr.net/gh/shiyindaxiaojie/eden-images/readme/icon.png" align="right" />

[license-apache2.0]:https://www.apache.org/licenses/LICENSE-2.0.html

[github-action]:https://github.com/shiyindaxiaojie/eden-business/actions

[sonarcloud-dashboard]:https://sonarcloud.io/dashboard?id=shiyindaxiaojie_eden-business

# Eden* Business

![](https://cdn.jsdelivr.net/gh/shiyindaxiaojie/eden-images/readme/language-java-blue.svg) [![](https://cdn.jsdelivr.net/gh/shiyindaxiaojie/eden-images/readme/license-apache2.0-red.svg)][license-apache2.0] [![](https://github.com/shiyindaxiaojie/eden-business/workflows/build/badge.svg)][github-action] [![](https://sonarcloud.io/api/project_badges/measure?project=shiyindaxiaojie_eden-business&metric=alert_status)][sonarcloud-dashboard]

Eden* Business 致力于提供企业开发的一站式解决方案。此项目包含开发分布式应用服务的必需组件，您只需要添加一些注解和少量配置，就可以将 Spring Boot 应用接入微服务解决方案，通过中间件来迅速搭建分布式应用系统。

> 参考文档请查看 [WIKI](https://github.com/shiyindaxiaojie/eden-business/wiki) 。

## 主要功能

* **阿里巴巴组件集成**：提供了支付宝、阿里云产品组件的集成，例如支付宝、阿里云短信平台。
* **腾讯组件集成**：提供了微信、腾讯云产品组件的集成，例如微信支付、腾讯云短信平台、对象存储、云点播。
* **梦网组件集成**：提供了梦网短信平台组件的集成。
* **亿美云组件集成**：提供了亿美短信平台组件的集成。

## 组件构成

![](https://cdn.jsdelivr.net/gh/shiyindaxiaojie/eden-images/eden-business/component.png)

* **eden-alibaba-spring-boot-starters**: 阿里巴巴商业组件
* **eden-tencent-spring-boot-starters**: 腾讯商业组件
* **eden-emay-spring-boot-starters**: 亿美商业组件
* **eden-montnets-spring-boot-starters**: 梦网商业组件

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
    <version>2.4.13-SNAPSHOT</version>
    <relativePath/>
</parent>
```

然后在 `dependencies` 中添加自己所需使用的依赖即可使用，例如，引入阿里云的短信组件。

```xml
<dependency>
    <groupId>org.ylzl</groupId>
    <artifactId>eden-aliyun-sms-spring-boot-starter</artifactId>
</dependency>
```

> 请注意，我们已经把常用的依赖纳入 eden-dependencies 管理，不建议带版本号覆盖原有的依赖。

## 版本规范

项目的版本号格式为 x.x.x 的形式，其中 x 的数值类型为数字，从 0 开始取值，且不限于 0~9 这个范围。项目处于孵化器阶段时，第一位版本号固定使用 0，即版本号为 0.x.x 的格式。

由于 `Spring Boot 1.5.x` 和 `Spring Boot 2.4.x` 在架构层面有很大的变更，因此我们采取跟 Spring Boot 版本号一致的版本:

* 1.5.x 版本适用于 `Spring Boot 1.5.x`
* 2.4.x 版本适用于 `Spring Boot 2.4.x`
