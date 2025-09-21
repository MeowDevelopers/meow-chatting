DROP TABLE IF EXISTS message;
CREATE TABLE message
(
    message_id         BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '메세지 ID',
    room_id            BIGINT UNSIGNED NOT NULL COMMENT '채팅방 ID',
    sender_id          BIGINT UNSIGNED NOT NULL COMMENT '보낸 유저 ID',
    message_type       ENUM('TEXT', 'IMAGE', 'VIDEO', 'FILE') NOT NULL COMMENT '메세지 타입',
    message_text       TEXT NULL COMMENT '메세지 내용',
    message_url        VARCHAR(500) NULL COMMENT '메세지 파일 경로',

		created_at         DATETIME(6) NOT NULL COMMENT '생성일',
		modified_at        DATETIME(6) NOT NULL COMMENT '수정일',
		deleted_at         DATETIME(6) NOT NULL COMMENT '삭제일',

		INDEX idx_room_created_at (room_id, created_at)
) ENGINE = InnoDB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci
  COMMENT = '채팅 메세지 테이블';
