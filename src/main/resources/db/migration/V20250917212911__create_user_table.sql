DROP TABLE IF EXISTS user;
CREATE TABLE user
(
		user_id              BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '유저 ID',
		user_email           VARCHAR(100) NOT NULL UNIQUE COMMENT '카카오 이메일',
		user_name            VARCHAR(30) NOT NULL COMMENT '카카오 이름',
		provider_type        ENUM('KAKAO') NOT NULL COMMENT '로그인 타입(KAKAO)',
		provider_id          VARCHAR(255) NULL COMMENT 'OAuth2 유저 고유 식별자',
		dormancy             BOOLEAN NOT NULL DEFAULT false COMMENT '휴면 계정 여부',
		created_at           DATETIME(6) NOT NULL COMMENT '생성일',
		modified_at          DATETIME(6) NOT NULL COMMENT '수정일',
		deleted_at           DATETIME(6) NOT NULL COMMENT '삭제일',

		UNIQUE KEY           uk_provider (provider_type, provider_id),
		INDEX                idx_email (user_email) COMMENT '카카오 이메일 index',
		INDEX                idx_name (user_name) COMMENT '카카오 이름 index'
) ENGINE = InnoDB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci
  COMMENT = '유저 테이블';
