# 📌 Board REST API Server

Spring Boot + MyBatis + MySQL 기반의 게시판 REST API 서버입니다.  
회원가입, 로그인, 게시글 CRUD 기능을 제공합니다.

---

## 📁 프로젝트 구조
```
server-impl/
├── src/
│ ├── main/
│ │ ├── java/com/koreait/server/
│ │ │ ├── controller/ # API 컨트롤러
│ │ │ ├── dto/ # 데이터 전송 객체
│ │ │ ├── mapper/ # MyBatis 매퍼 인터페이스
│ │ │ ├── model/ # 모델 클래스
│ │ │ ├── service/ # 서비스 클래스
│ │ │ └── ServerImplApplication.java
│ │ └── resources/
│ │ ├── application.properties # DB, 서버 설정
│ │ ├── mapper/*.xml # MyBatis 매퍼 XML
│ │ └── static/, templates/ # (해당 없음 또는 선택)
└── pom.xml
```

---

## ⚙️ 기술 스택

- Java 17
- Spring Boot 3.2.0
- MyBatis 3.0.3
- MySQL 8.1
- Maven

---

## 🔧 실행 방법

### ✅ 1. MySQL 설정

MySQL에 아래와 같이 데이터베이스를 생성하세요:

```sql
CREATE DATABASE board_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

비밀번호는 1234, 사용자명은 root로 설정된 상태입니다.
변경 시 application.properties 파일도 함께 수정해야 합니다.

### ✅ 2. application.properties 확인

src/main/resources/application.properties 파일이 아래와 같이 설정되어 있어야 합니다:

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/board_db?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8
spring.datasource.username=root
spring.datasource.password=1234
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# MyBatis Configuration
mybatis.mapper-locations=classpath:mapper/*.xml
mybatis.type-aliases-package=com.koreait.server.model
mybatis.configuration.map-underscore-to-camel-case=true

# Server Configuration
server.port=8080

# Logging (선택)
logging.level.com.koreait.server.mapper=DEBUG
logging.level.org.springframework.web=DEBUG

✅ 3. 빌드 및 실행

# Maven 빌드
./mvnw clean package

# 실행
java -jar target/server-impl-0.0.1-SNAPSHOT.jar

또는 IntelliJ에서 ServerImplApplication.java 실행

📌 참고
포트: 8080

API 테스트는 Postman 또는 Swagger 사용 권장

세션 기반 인증 (HttpSession) 사용
