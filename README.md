# Meow 채팅 시스템

### 컨테이너 실행 방법
1. 실행
   ```shell
   make start
   ```

2. 종료
   ```shell
   make stop
   ```
### Flyway 사용 방법
- DB 초기화 및 새로 적용 : `flywayClean` -> `flywayMigrate`
- 새로 적용 : `flywayMigrate`

### 사용 중인 포트 번호
1. `8080` : Tomcat
2. `3400` : MySQL

### 프로젝트 파일명 규칙
- 파일명은 도메인 + 역할을 기준으로 작성합니다.
- 필요에 따라 세분화된 목적을 반영하여 구체적인 이름을 사용합니다.

  - EX) UserCommandService → 기능에 따라 UserLoginCommandService, UserListCommandService 등으로 분리

### Meow Code 명명 규칙
- 클래스 : {도메인명}ResponseCode
- Code : {코드로 표현하고 싶은 상태}

### 📁 프로젝트 구조
   ```
meow-chatting/
├── src/main/kotlin/com/meow/meowchatting/
│    ├── config/
│    │    ├── SecurityConfig.kt
│    ├── jwt/
│    │    ├── JwtAuthenticationFilter.kt
│    │    └── JwtProvider.kt
│    ├── user/
│    │    ├── command/
│    │    │    ├── service/
│    │    │    │    └── UserCommandService.kt
│    │    │    ├── domain/
│    │    │    │    ├── User.kt
│    │    │    │    ├── UserProfile.kt
│    │    │    │    └── Friend.java
│    │    │    ├── controller/
│    │    │    │    └── UserCommandController.java
│    │    │    ├── repository/
│    │    │    │    └── UserCommandRepository.kt
│    │    │    └── dto/
│    │    │         └── UserRequest.kt
│    │    ├── query/
│    │    │    ├── service/
│    │    │    │    └── UserQueryService.kt
│    │    │    ├── dto/
│    │    │    │    └── UserResponse.kt
│    │    │    ├── controller/
│    │    │    │    └── UserQueryController.java
│    │    │    └── repository/
│    │    │         └── UserQueryRepository.java
│    │
│    ├── auth/
│    │    ├── command/
│    │    │    ├── service/
│    │    │    │    └── RefreshTokenCommandService.java
│    │    │    ├── domain/
│    │    │    │    └── RefreshToken.kt
│    │    │    ├── controller/
│    │    │    │    └── RefreshTokenCommandController.kt
│    │    │    ├── repository/
│    │    │    │    └── RefreshTokenCommandRepository.java
│    │    │    └── dto/
│    │    │         └── RefreshTokenRequest.java
│    │    ├── query/
│    │    │    ├── service/
│    │    │    │    └── RefreshTokenQueryService.java
│    │    │    ├── dto/
│    │    │    │    └── RefreshTokenResponse.java
│    │    │    ├── controller/
│    │    │    │    └── RefreshTokenQueryController.java
│    │    │    └── repository/
│    │    │         └── RefreshTokenQueryRepository.java
│    │
│    ├── room/
│    │    ├── command/
│    │    │    ├── service/
│    │    │    │    └── RoomCommandService.java
│    │    │    ├── domain/
│    │    │    │    ├── Room.java
│    │    │    │    ├── RoomUser.java
│    │    │    │    ├── DirectRoom.java
│    │    │    │    └── Message.java
│    │    │    ├── controller/
│    │    │    │    └── RoomCommandController.java
│    │    │    ├── repository/
│    │    │    │    └── RoomCommandRepository.java
│    │    │    └── dto/
│    │    │         └── RoomRequest.java
│    │    ├── query/
│    │    │    ├── service/
│    │    │    │    └── RoomQueryService.java
│    │    │    ├── dto/
│    │    │    │    └── RoomResponse.java
│    │    │    ├── controller/
│    │    │    │    └── RoomQueryController.java
│    │    │    └── repository/
│    │    │         └── RoomQueryRepository.java
│    │
│    └── common/
│         ├── exception/
│         └── utils/
│
└── build.gradle

   ```
