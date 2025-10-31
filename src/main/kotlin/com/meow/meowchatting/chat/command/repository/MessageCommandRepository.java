package com.meow.meowchatting.chat.command.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meow.meowchatting.chat.command.domain.Message;

public interface MessageCommandRepository extends JpaRepository<Message, Long> {
}
