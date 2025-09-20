DROP TABLE IF EXISTS room_users;
CREATE TABLE room_users
(
    room_users_id        BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '채팅방 참여 ID',
    room_id              BIGINT UNSIGNED NOT NULL COMMENT '채팅방 ID',
    user_id              BIGINT UNSIGNED NOT NULL COMMENT '사용자 ID',
    is_active            BOOLEAN NULL DEFAULT TRUE COMMENT '채팅방 활성화 여부',
    last_read_message_id BIGINT UNSIGNED NULL COMMENT '마지막 읽은 메시지 ID',

		created_at           DATETIME(6) NOT NULL COMMENT '생성일',
		modified_at          DATETIME(6) NOT NULL COMMENT '수정일',
		deleted_at           DATETIME(6) NOT NULL COMMENT '삭제일',

		UNIQUE KEY           uniq_room_users (room_id, user_id),
		INDEX                idx_user_id (user_id) COMMENT '유저 ID index'
) ENGINE = InnoDB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci
  COMMENT = '채팅방 참여 사용자 테이블';
