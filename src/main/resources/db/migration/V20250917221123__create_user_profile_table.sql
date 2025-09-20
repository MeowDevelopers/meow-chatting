DROP TABLE IF EXISTS user_profile;
CREATE TABLE user_profile
(
    user_profile_id      BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '카카오 프로필 이미지 ID',
    user_id              BIGINT UNSIGNED NOT NULL UNIQUE COMMENT '유저 ID',
		user_profile_url     VARCHAR(500) NOT NULL COMMENT '프로필 이미지 경로',

		created_at           DATETIME(6) NOT NULL COMMENT '생성일',
		modified_at          DATETIME(6) NOT NULL COMMENT '수정일',
		deleted_at           DATETIME(6) NOT NULL COMMENT '삭제일'
) ENGINE = InnoDB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci
  COMMENT = '유저 카카오 프로필 테이블';
