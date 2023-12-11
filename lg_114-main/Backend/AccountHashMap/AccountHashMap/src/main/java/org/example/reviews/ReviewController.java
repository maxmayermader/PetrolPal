package org.example.reviews;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.example.account.UserRepo;
import org.example.account.accountInfo;
import org.example.gas.GasInfo;
import org.example.gas.GasRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "ReviewController", description = "REST Apis related to Review")
@RestController
public class ReviewController {

    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private GasRepo gasRepo;

    @ApiOperation(value = "Get list of Reviews in the System ", response = Iterable.class, tags = "getAllReviews")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/review/all")
    List<Review> getAllReviews(){
        return reviewRepo.findAll();
    }


    @ApiOperation(value = "Post a review given gas station id and user id", response = Review.class, tags = "makeReview")
    @PostMapping("/review/post/{gasid}/{userid}")
    Review makeReview(@PathVariable long gasid, @PathVariable long userid, @RequestBody Review review){
        GasInfo station = gasRepo.getByGasId(gasid);
        accountInfo user = userRepo.findById(userid);

        review.setGasID(station);
        review.setUserID(user);

        reviewRepo.save(review);
        return review;
    }

    @ApiOperation(value = "Get a review given review id", response = Review.class, tags = "getByReviewID")
    @GetMapping("/review/get/{id}")
    Review getByReviewID(@PathVariable long id){
        return reviewRepo.findById(id);
    }

    @ApiOperation(value = "Get accountInfo given review id", response = accountInfo.class, tags = "getUserByReviewID")
    @GetMapping("/review/user/{id}")
    accountInfo getUserByReviewID(@PathVariable long id){
        return reviewRepo.findById(id).getUserID();
    }

    @ApiOperation(value = "Get a gas station given review id", response = GasInfo.class, tags = "getStationByReviewID")
    @GetMapping("/review/station/{id}")
    GasInfo getStationByReviewID(@PathVariable long id){
        return reviewRepo.findById(id).getGasID();
    }

    @ApiOperation(value = "Get all reviews on a station given station id", response = Review.class, tags = "makeReview")
    @GetMapping("/reviews/station/{id}")
    List<Review> getAllReviewsByGasID(@PathVariable long id){
        GasInfo station = gasRepo.getByGasId(id);
        return reviewRepo.findAllByGasID(station);
    }

}
