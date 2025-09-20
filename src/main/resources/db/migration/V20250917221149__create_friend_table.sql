DROP TABLE IF EXISTS friend;
CREATE TABLE friend
(
    friend_id          BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '친구 ID',
    user_id            BIGINT UNSIGNED NOT NULL COMMENT '유저 ID',
    friend_user_id     BIGINT UNSIGNED NOT NULL COMMENT '상대방 유저 ID',

		created_at         DATETIME(6) NOT NULL COMMENT '생성일',
		modified_at        DATETIME(6) NOT NULL COMMENT '수정일',
		deleted_at         DATETIME(6) NOT NULL COMMENT '삭제일',

		UNIQUE KEY uniq_friend (user_id, friend_user_id)
) ENGINE = InnoDB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci
  COMMENT = '친구 테이블';
