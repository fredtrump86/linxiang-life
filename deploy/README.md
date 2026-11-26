# 邻享生活｜服务器部署

本文是通用部署模板，不包含固定服务器地址或账号。完整的 Kafka、RocketMQ、Canal 链路使用根目录的 `dev/docker-compose.yml`；本目录的轻量部署默认关闭这三个组件，保留 MySQL、Redis、Memcached、Java、Nginx 和网页。

## 1. 本地构建发布包

在项目根目录执行：

```bash
mvn -DskipTests package
python deploy/package-release.py
```

没有本地 JDK 8 和 Maven 时，可使用开发镜像：

```bash
docker run --rm -v "$PWD:/workspace" -v hm-dianping_maven-cache:/root/.m2 -w /workspace maven:3.9-eclipse-temurin-8 mvn -DskipTests package
python deploy/package-release.py
```

生成 `target/linxiang-life-cloud-release.tar.gz`，包含 Java 程序、网页、初始化 SQL、演示图片、部署模板和 SHA-256 清单，不包含真实 `.env`、私钥、日志或本机数据库备份。修改源码或配置后需重新构建，脚本会拒绝过期 JAR。

## 2. 上传到自己的服务器

以下账号和主机是占位值，请替换成实际信息：

```bash
scp target/linxiang-life-cloud-release.tar.gz deploy-user@your-server:/tmp/
```

在服务器执行。已有部署时先备份并确认目标目录，不覆盖其他服务：

```bash
mkdir -p "$HOME/apps/linxiang-life"
tar -xzf /tmp/linxiang-life-cloud-release.tar.gz -C "$HOME/apps/linxiang-life" --strip-components=1
cd "$HOME/apps/linxiang-life"
```

## 3. 初始化私有配置并启动

服务器需安装 Docker、Compose 和 OpenSSL：

```bash
sh init-env.sh
docker compose --env-file .env -f compose.yml config --quiet
docker compose --env-file .env -f compose.yml up -d --build
docker compose --env-file .env -f compose.yml ps -a
docker compose --env-file .env -f compose.yml logs --tail=100 java
```

`init-env.sh` 生成随机数据库、Redis 和监控凭证，文件权限为 600，已有 `.env` 时不覆盖。字段见 [.env.example](.env.example)。保留 `.env` 与数据卷的对应关系；重建配置不会自动修改旧数据库密码。

`image-init` 退出码为 0 表示图片初始化完成。Java 使用预编译 JAR，空数据库卷首次导入演示数据，后续由 Flyway 执行增量迁移。配置包含资源限制，目标机器容量仍需实际验证。

## 4. 验证与访问

```bash
curl -i 'http://127.0.0.1:8080/api/actuator/health'
curl -i 'http://127.0.0.1:8080/api/shop-type/list'
```

健康接口应返回 UP，分类接口应返回业务数据。网页端口默认 8080，可在 `.env` 中配置 `HMDP_WEB_PORT`。按自己的环境配置安全组、防火墙、域名和 HTTPS；只开放需要的网页入口。数据库与缓存不发布宿主端口，Java 8081 仅绑定本机，公网 Nginx 不提供管理运维和指标入口。

使用自己的服务器地址访问 `/` 和 `/experience.html`。部分店铺图片来自第三方 URL，其可用性取决于外站。本地分类、笔记图片随包提供。

## 5. 登录与管理

当前没有真实短信服务。网页登录获取验证码后，由部署者在对应时段日志中查看六位验证码，不公开整个日志或管理员令牌：

```bash
docker compose --env-file .env -f compose.yml logs --since=2m java
```

验证码两分钟内有效。正常登录后，从 `/api/user/me` 获取用户 ID，填入私有 `.env` 的 `HMDP_ADMIN_IDS`；多个 ID 用英文逗号分隔。模拟付款默认关闭，需要时设置 `HMDP_PAYMENT_MOCK_ENABLED=true`，它不代表真实收款。

修改环境后执行以下命令重新创建 Java，单纯 restart 不刷新环境变量：

```bash
docker compose --env-file .env -f compose.yml up -d --no-deps java
```

## 6. 停止与更新

```bash
docker compose --env-file .env -f compose.yml down
docker compose --env-file .env -f compose.yml up -d
```

日常不要加 `-v`。升级前备份数据库、Redis、上传图片和 `.env`；保留数据卷，更新包后执行 `up -d --build`。初始化 SQL 不会自动覆盖旧数据库。

为兼容已有部署，数据卷使用固定名称；同一服务器运行多个独立环境时需分别规划卷名。发布包不包含现有用户、订单或服务器业务数据的迁移备份。

## 7. 验收清单

- 首页、分类、店铺详情及本地图片可以访问。
- 正常登录后能查询本人资料、签到和订单。
- 未登录不能查询本人数据，普通账号不能操作管理接口。
- 体验页显示与实际开关一致的组件状态；轻量版不等于完整消息链路已验证。
- 从允许的外部网络验证访问，检查容器重启、内存和磁盘使用情况。

这些是部署步骤与验收标准，不代表某台服务器已经通过验证。个人部署记录、机器审计和原始日志保留在本地，不随公开仓库发布。
