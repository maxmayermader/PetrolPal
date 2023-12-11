package org.example.websocket;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long>{
    boolean existsByUserName(String userName);

}
