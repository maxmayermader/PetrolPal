package org.example.report;

import org.example.gas.GasInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface ReportRepo extends JpaRepository<Report, Integer> {
    List<Report> getAllByGasID(GasInfo id);
    Report findById(long id);

    //@Transactional
    void removeAllByGasID(GasInfo id);


}
