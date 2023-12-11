package org.example.gas;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.example.account.UserRepo;
import org.example.account.accountInfo;
import org.example.map.Geoapify;
import org.example.reviews.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URLEncoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@Api(value = "GasController", description = "REST Apis related to GasInfo")
@RestController
public class GasController {



    @Autowired
    private GasRepo gasControllerRepo;
    @Autowired
    private UserRepo userRepo;

    public List<GasInfo> deleteGas;

    public List<GasInfo> deleteGasID;



    GasInfo deleteID;
    GasInfo currentGas;

    @ApiOperation(value = "Get list of stations in the system", response = Iterable.class, tags = "getAllAccounts")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Suceess|OK"),
            @ApiResponse(code = 401, message = "not authorized!"),
            @ApiResponse(code = 403, message = "forbidden!!!"),
            @ApiResponse(code = 404, message = "not found!!!") })
    @GetMapping("/station/all")
    public List<GasInfo> getAllAccounts() {
        return gasControllerRepo.findAll();
    }

    //creates new gas station
    @ApiOperation(value = "Post new station", response = GasInfo.class, tags = "createGas")
    @PostMapping("/station/post")
    public GasInfo createGas(@RequestBody GasInfo info) {


        gasControllerRepo.save(info);
        currentGas = info;

        Geoapify.addessConvert(currentGas);
        gasControllerRepo.save(currentGas);
        this.currentGas = currentGas;
        return currentGas;
    }


    //Edits a new station
    @ApiOperation(value = "Put to gas station", response = GasInfo.class, tags = "putGas")
    @PutMapping("/station/put/{id}")
    public GasInfo putGas(@PathVariable Long id, @RequestBody GasInfo info) {
        GasInfo station = gasControllerRepo.getByGasId(id);
        if (station == null){
            return null;
        }

        station.setAddress(info.getAddress());
        station.setUsername(info.getUsername());
        station.setUserID(info.getUserID());
        station.setPrice(info.getPrice());
        station.setDescription(info.getDescription());
        station.setStatus(info.getStatus());

        gasControllerRepo.save(station);
        currentGas = station;
        return currentGas;
    }


    //returns object
    @GetMapping("/station")
    public GasInfo getCurrStation(){
        return currentGas;
    }


//    //returns object
//    @GetMapping("/station")
//    public GasInfo getGas() {
//
//        return currentGas;
//    }


    @ApiOperation(value = "Get username tied to a gas station", response = GasInfo.class, tags = "getStaionUN")
    @GetMapping("/station/username")
    String getStationUN(){
        return currentGas.getUsername();
    }

    @ApiOperation(value = "Get station name", response = String.class, tags = "getStationName")
    @GetMapping("/station/name")
    String getStationName(){
        return currentGas.getStationName();
    }
    @ApiOperation(value = "Get station address", response = String.class, tags = "getStationAddress")
    @GetMapping("/station/address")
    String getStationAddress(){
        return currentGas.getAddress();
    }

    @ApiOperation(value = "Get gas price", response = double.class, tags = "getStationPrice")
    @GetMapping("/station/price")
    double getStationPrice(){
        return currentGas.getPrice();
    }

    @ApiOperation(value = "Get station description", response = String.class, tags = "getStationDesc")
    @GetMapping("/station/desc")
    String getStationDesc(){
        return currentGas.getDescription();
    }


    //search
    @ApiOperation(value = "Get station by user id", response = GasInfo.class, tags = "searchStations")
    @GetMapping("station/search/{id}")
    GasInfo searchStations(@PathVariable Long id){
        GasInfo station = gasControllerRepo.getByGasId(id);
        System.out.println(station.getUserID());
        return station;
    }

    //Put user in station
    @ApiOperation(value = "Post user to station", response = accountInfo.class, tags = "putUserInStation")
    @PostMapping("/station/postUser/{id}")
    accountInfo putUserInStation(@PathVariable long id){
        accountInfo user = userRepo.findById(id);
        currentGas.setUserID(user);
        gasControllerRepo.save(currentGas);

        return user;
    }

    //getUserID
    @ApiOperation(value = "Get user of station", response = accountInfo.class, tags = "getUserIDFromStation")
    @GetMapping("/station/get/{id}/userID")
    accountInfo getUserIDFromStation(@PathVariable long id){
        GasInfo currGas = gasControllerRepo.getByGasId(id);

        return currGas.getUserID();
    }
    //deletes by ID
    @ApiOperation(value = "Delete a station by ID", response = GasInfo.class, tags = "deleteGasID")
    @DeleteMapping("/StationDelete/{id}")
    public Map<String, String> deleteGasID(@PathVariable Long id){
        deleteGasID = gasControllerRepo.findByGasId(id);
        gasControllerRepo.deleteAll(deleteGasID);
        Map<String, String> response = new HashMap<>();
        response.put("id", id +"");

        return response;
        //returns as json { "id": "1" }
        /**
         * deleteUser = accountInfoRepository.findByUsername(username);
         *
         *         accountInfoRepository.deleteAll(deleteUser);
         *         gasController.deleteGas(username);
         *         Map<String, String> response = new HashMap<>();
         *         response.put("username", username);
         *
         *         return response;
         */

    }

    @ApiOperation(value = "Put to gas station", tags = "deleteGas")
    public void deleteGas(String username){

        deleteGas = gasControllerRepo.findByUsername(username);
        gasControllerRepo.deleteAll(deleteGas);
    }

    @ApiOperation(value = "Get distance of station", response = Iterable.class, tags = "getStationDistances")
    @GetMapping("/station/{lat}/{lon}")
    public  List<GasInfo> getStationDistances(@PathVariable Double lat, @PathVariable Double lon) {
        double lat1 = lat;
        double lon1 = lon;
        List<GasInfo> gasInfos = gasControllerRepo.findAll();


        for (GasInfo current : gasInfos) {
            if (current.getLat() != 0.0) {
                double theta = lon1 - current.getLon(); // Formula of distance in miles
                double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(current.getLat())) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(current.getLat())) * Math.cos(Math.toRadians(theta));
                dist = Math.acos(dist);
                dist = Math.toDegrees(dist);
                dist = dist * 60 * 1.1515;
                dist = dist * 0.8684;
                current.setDist(dist);

            }
        }

        Collections.sort(gasInfos, Comparator.comparing(GasInfo::getDist));

        return gasInfos.subList(0, Math.min(20, gasInfos.size()));
    }

    @PutMapping("/station/put/price/{sid}/{price}")
    public GasInfo setStationPrice(@PathVariable long sid, @PathVariable double price){
        GasInfo station = gasControllerRepo.getByGasId(sid);
        station.setPrice(price);
        gasControllerRepo.save(station);
        return station;
    }

}
