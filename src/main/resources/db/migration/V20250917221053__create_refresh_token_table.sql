DROP TABLE IF EXISTS refresh_token;
CREATE TABLE refresh_token
(
		refresh_token_id     BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'RefreshToken ID',
		user_id              BIGINT UNSIGNED NOT NULL UNIQUE COMMENT '유저 ID',
		refresh_token        VARCHAR(255) NULL COMMENT 'RefreshToken',
		created_at           DATETIME(6) NOT NULL COMMENT '생성일',
		modified_at          DATETIME(6) NOT NULL COMMENT '수정일',
		deleted_at           DATETIME(6) NOT NULL COMMENT '삭제일',

		INDEX                idx_user_id (user_id) COMMENT '유저 ID index'
) ENGINE = InnoDB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci
  COMMENT = 'RefreshToken 테이블';
