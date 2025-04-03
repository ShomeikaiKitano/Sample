# Spring Boot サンプルアプリケーション

シンプルなREST APIを提供するSpring Bootサンプルアプリケーションです。

## 機能

- Spring Boot 3.2.4を使用
- H2インメモリデータベース
- REST APIエンドポイント
- JPA/Hibernateによるデータアクセス

## エンドポイント

- `GET /`: "Hello Spring Boot!"という文字列を返す
- `GET /api/messages`: 全てのメッセージを返す
- `POST /api/messages`: 新しいメッセージを作成する

## 設定

主な設定は`application.properties`ファイルで管理されています。

```properties
# サーバーポート設定
server.port=8080

# H2データベース設定
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
```

## ビルドと実行

```bash
# ビルド
./gradlew build

# 実行
./gradlew bootRun
```

## テスト

```bash
./gradlew test
```

## H2コンソール

アプリケーション実行中に以下のURLでH2データベースコンソールにアクセスできます：
http://localhost:8080/h2-console

- JDBC URL: `jdbc:h2:mem:testdb`
- ユーザー名: `sa`
- パスワード: `password`