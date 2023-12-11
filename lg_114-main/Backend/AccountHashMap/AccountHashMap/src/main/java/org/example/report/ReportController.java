package org.example.report;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.example.account.UserRepo;
import org.example.account.accountInfo;
import org.example.gas.GasInfo;
import org.example.gas.GasRepo;
import org.example.reviews.Review;
import org.example.reviews.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Api(value = "ReportController", description = "REST Apis related to Report")
@RestController
public class ReportController {
    @Autowired
    private ReportRepo reportRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private GasRepo gasRepo;

    @ApiOperation(value = "Get list of Reports in the System ", response = Iterable.class, tags = "getAllReports")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/report/all")
    List<Report> getAllReports(){
        return reportRepo.findAll();
    }

    @PostMapping("/report/post/{uid}/{sid}")
    Report postReport(@PathVariable long uid, @PathVariable long sid, @RequestBody Report r){
        GasInfo station = gasRepo.getByGasId(sid);
        accountInfo user = userRepo.findById(uid);

        r.setUserID(user);
        r.setGasID(station);

        reportRepo.save(r);
        return  r;
    }

    @GetMapping("/report/station/{sid}")
    List<Report> getByGas(@PathVariable long sid){
        GasInfo station = gasRepo.getByGasId(sid);
        return reportRepo.getAllByGasID(station);
    }

    @DeleteMapping("/report/delete/all")
    void delReport(){
        reportRepo.deleteAll();
    }

    @DeleteMapping("/report/delete/{sid}")
    @Transactional
    public void delReportByGas(@PathVariable long sid){
        reportRepo.removeAllByGasID(gasRepo.getByGasId(sid));
    }
}
