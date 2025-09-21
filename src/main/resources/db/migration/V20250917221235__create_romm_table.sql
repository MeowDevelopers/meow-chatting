DROP TABLE IF EXISTS room;
CREATE TABLE room
(
    room_id            BIGINT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT '채팅방 ID',
    room_type          ENUM('DIRECT', 'GROUP') NOT NULL COMMENT '채팅방 타입(1:1, 그룹)',
    room_name          VARCHAR(100) NOT NULL COMMENT '채팅방 이름',
    room_owner_user_id BIGINT UNSIGNED NOT NULL COMMENT '채팅방 생성자 ID',

		created_at         DATETIME(6) NOT NULL COMMENT '생성일',
		modified_at        DATETIME(6) NOT NULL COMMENT '수정일',
		deleted_at         DATETIME(6) NOT NULL COMMENT '삭제일'
) ENGINE = InnoDB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci
  COMMENT = '채팅방 테이블';
