package com.meow.meowchatting.chat.command.domain;

import com.meow.meowchatting.chat.command.enums.RoomType;
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
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false, updatable = false)
    private Long roomId;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", nullable = false, length = 10)
    private RoomType roomType;

    @Column(name = "room_name", nullable = false, length = 100)
    private String roomName;

    @Column(name = "room_owner_user_id", nullable = false)
    private Long roomOwnerUserId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Builder
    public Room(RoomType roomType, String roomName, Long roomOwnerUserId) {
        this.roomType = roomType;
        this.roomName = roomName;
        this.roomOwnerUserId = roomOwnerUserId;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.modifiedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifiedAt = LocalDateTime.now();
    }

    public void updateRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void deleteRoom() {
        this.deletedAt = LocalDateTime.now();
    }

}
