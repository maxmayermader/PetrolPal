package org.example.reviews;

import org.example.account.accountInfo;
import org.example.gas.GasInfo;
import org.example.websocket.Message;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepo extends JpaRepository<Review, Integer> {

    Review findById(long id);

    List<Review> findAllByGasID(GasInfo id);
}
