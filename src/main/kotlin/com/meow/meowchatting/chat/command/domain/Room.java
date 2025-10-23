package com.meow.meowchatting.chat.command.domain;

import com.meow.meowchatting.chat.command.enums.RoomType;
import com.meow.meowchatting.common.base.AbstractBaseUserByEntity;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "room")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted_at IS NULL")
@AttributeOverride(name = "id", column = @Column(name = "room_id"))
public class Room extends AbstractBaseUserByEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false, length = 10)
    private RoomType roomType;

    @Column(name = "room_name", nullable = false, length = 100)
    private String roomName;

    @Column(name = "room_owner_user_id", nullable = false)
    private Long roomOwnerUserId;


    @Builder
    public Room(RoomType roomType, String roomName, Long roomOwnerUserId) {
        this.roomType = roomType;
        this.roomName = roomName;
        this.roomOwnerUserId = roomOwnerUserId;
    }

    public void updateRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void deleteRoom() {
        setDeletedAt(LocalDateTime.now());
    }

}
