package org.example.gas;

import org.example.account.accountInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface GasRepo extends JpaRepository<GasInfo, Integer> {
    GasInfo getByGasId(Long id);



    List<GasInfo> findByUsername(String username);

    List<GasInfo> findByGasId(Long gasId);

}