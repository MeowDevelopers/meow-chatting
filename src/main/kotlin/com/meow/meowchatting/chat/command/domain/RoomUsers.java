package com.meow.meowchatting.chat.command.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Table(name = "room_users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uniq_room_users", columnNames = {"room_id", "user_id"})
        },
        indexes = {
                @Index(name = "idx_user_id", columnList = "user_id")
        })
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Where(clause = "deleted_at IS NULL")
public class RoomUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_users_id", nullable = false, updatable = false)
    private Long roomUsersId;

    @Column(name = "room_id", nullable = false)
    private Long roomId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "is_active", nullable = true)
    private Boolean isActive;

    @Column(name = "last_read_message_id")
    private Long lastReadMessageId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @Builder
    public RoomUsers(Long roomId, Long userId, Boolean isActive, Long lastReadMessageId) {
        this.roomId = roomId;
        this.userId = userId;
        this.isActive = isActive != null ? isActive : true;
        this.lastReadMessageId = lastReadMessageId;
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

    public void updateLastReadMessageId(Long lastReadMessageId) {
        this.lastReadMessageId = lastReadMessageId;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void activate() {
        this.isActive = true;
    }
}
