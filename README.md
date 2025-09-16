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

### 📁 프로젝트 구조
   ```
   meow-chatting/
 ├── src/main/kotlin/com/meow/meowchatting/
 │    ├── config/
 │    │    ├── SecurityConfig.kt
 │    └── jwt/
 │         ├── JwtAuthenticationFilter.kt
 │         └── JwtProvider.kt
 │
 ├── src/main/java/com/meow/meowchatting/
 │    ├── user/
 │    │    ├── command/
 │    │    │    ├── service/
 │    │    │    │    └── UserCommandService.java
 │    │    │    ├── domain/
 │    │    │    │    └── User.java
 │    │    │    ├── controller/
 │    │    │    │    └── UserCommandController.java
 │    │    │    ├── repository/
 │    │    │    │    └── UserCommandRepository.java
 │    │    │    └── dto/
 │    │    │         └── UserRequest.java
 │    │    ├── query/
 │    │    │    ├── service/
 │    │    │    │    └── UserQueryService.java
 │    │    │    ├── dto/
 │    │    │    │    └── UserResponse.java
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
 │    │    │    │    └── RefreshToken.java
 │    │    │    ├── controller/
 │    │    │    │    └── RefreshTokenCommandController.java
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
 │    ├── profile/
 │    │    ├── command/
 │    │    │    ├── service/
 │    │    │    │    └── UserProfileCommandService.java
 │    │    │    ├── domain/
 │    │    │    │    └── UserProfile.java
 │    │    │    ├── controller/
 │    │    │    │    └── UserProfileCommandController.java
 │    │    │    ├── repository/
 │    │    │    │    └── UserProfileCommandRepository.java
 │    │    │    └── dto/
 │    │    │         └── UserProfileRequest.java
 │    │    ├── query/
 │    │    │    ├── service/
 │    │    │    │    └── UserProfileQueryService.java
 │    │    │    ├── dto/
 │    │    │    │    └── UserProfileResponse.java
 │    │    │    ├── controller/
 │    │    │    │    └── UserProfileQueryController.java
 │    │    │    └── repository/
 │    │    │         └── UserProfileQueryRepository.java
 │    │
 │    ├── friend/
 │    │    ├── command/
 │    │    │    ├── service/
 │    │    │    │    └── FriendCommandService.java
 │    │    │    ├── domain/
 │    │    │    │    └── Friend.java
 │    │    │    ├── controller/
 │    │    │    │    └── FriendCommandController.java
 │    │    │    ├── repository/
 │    │    │    │    └── FriendCommandRepository.java
 │    │    │    └── dto/
 │    │    │         └── FriendRequest.java
 │    │    ├── query/
 │    │    │    ├── service/
 │    │    │    │    └── FriendQueryService.java
 │    │    │    ├── dto/
 │    │    │    │    └── FriendResponse.java
 │    │    │    ├── controller/
 │    │    │    │    └── FriendQueryController.java
 │    │    │    └── repository/
 │    │    │         └── FriendQueryRepository.java
 │    │
 │    ├── room/
 │    │    ├── command/
 │    │    │    ├── service/
 │    │    │    │    └── RoomCommandService.java
 │    │    │    ├── domain/
 │    │    │    │    ├── Room.java
 │    │    │    │    ├── RoomUser.java
 │    │    │    │    └── DirectRoom.java
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
 │    ├── message/
 │    │    ├── command/
 │    │    │    ├── service/
 │    │    │    │    └── MessageCommandService.java
 │    │    │    ├── domain/
 │    │    │    │    └── Message.java
 │    │    │    ├── controller/
 │    │    │    │    └── MessageCommandController.java
 │    │    │    ├── repository/
 │    │    │    │    └── MessageCommandRepository.java
 │    │    │    └── dto/
 │    │    │         └── MessageRequest.java
 │    │    ├── query/
 │    │    │    ├── service/
 │    │    │    │    └── MessageQueryService.java
 │    │    │    ├── dto/
 │    │    │    │    └── MessageResponse.java
 │    │    │    ├── controller/
 │    │    │    │    └── MessageQueryController.java
 │    │    │    └── repository/
 │    │    │         └── MessageQueryRepository.java
 │    │
 │    └── common/
 │         ├── exception/
 │         └── utils/
 │
 └── build.gradle

   ```
