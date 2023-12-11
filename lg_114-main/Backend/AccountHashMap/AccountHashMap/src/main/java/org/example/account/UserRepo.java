package org.example.account;

import org.example.account.accountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<accountInfo, Integer> {
    accountInfo findByUsernameAndPassword(String username, String password);
    List<accountInfo> findAllByUsername(String un);

    List<accountInfo> findByUsername(String username);
    accountInfo findById(Long id);

    }
