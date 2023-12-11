package org.example.account;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.example.gas.GasController;
import org.example.gas.GasInfo;
import org.example.gas.GasRepo;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "accountController", description = "REST Apis related to the Account Information")
@RestController
public class  accountController {

    @Autowired
    private UserRepo accountInfoRepository;

    @Autowired
    private GasRepo gasRepo;

    public accountInfo currentUser;
  //  accountInfo deleteUser;

    List<accountInfo> deleteUser;
    accountInfo value;
    @Autowired
    private GasController gasController;

    private String status;

    @ApiOperation(value = "Get list of accounts in the tables ", response = accountInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "not authorized"),
            @ApiResponse(code = 403, message = "forbidden"),
            @ApiResponse(code = 404, message = "not found") })
    //displays all the accounts in map
    @GetMapping("/account")
    public List<accountInfo> getAllAccounts() {
        return accountInfoRepository.findAll();
    }

    //method used for adding another account
    //takes in json request {"username": "bob", "password": "123",.....}
    @ApiOperation(value = "Post create account in table ", response = accountInfo.class)

    @PostMapping("/account")
    public accountInfo createAccount(@RequestBody accountInfo info) {

        if (accountInfoRepository.findByUsername(info.getUsername()).isEmpty()) {
            accountInfoRepository.save(info);
            currentUser = info;

            currentUser.setStatus("good");
        }
        else{
            currentUser = new accountInfo(1L, "","","","","","duplicate");

        }
            return currentUser;
    }

public List<accountInfo> correctLogin;

    public accountInfo returnValue;

    @ApiOperation(value = "Post username and password to see if they are in table for login ", response = accountInfo.class)

    @PostMapping("/account/login/{username}/{password}")
    public accountInfo loginCheck(@PathVariable String username, @PathVariable String password){
        currentUser = null;
        correctLogin = null;

        correctLogin = accountInfoRepository.findByUsername(username);
        currentUser = accountInfoRepository.findByUsernameAndPassword(username, password);
        if (correctLogin.isEmpty()){
            currentUser = new accountInfo(1L, "","","","","","missing");
        }
        else if ( !correctLogin.get(0).getPassword().equals(password)) {
            currentUser = new accountInfo(1L, "","","","","","wrong");

        }
        else if (currentUser != null) {
            currentUser.setStatus("true");

        }

        return currentUser;
    }

    @PutMapping("/account/{id}/{newPassword}")
    public Map<String, String> setPassword(@PathVariable Long id, @PathVariable String newPassword){
        Map<String, String> response = new HashMap<>();

        currentUser = accountInfoRepository.findById(id);
        if(currentUser.getPassword().equals(newPassword)){
            response.put("key", "same");
            return response;
        }

        currentUser.setPassword(newPassword);
        accountInfoRepository.save(currentUser);
        response.put("key", "success");
        return response;
    }
    /**
     * public Map<String, String> delete(@PathVariable String username) {
     *
     *         deleteUser = accountInfoRepository.findByUsername(username);
     *
     *         accountInfoRepository.deleteAll(deleteUser);
     *         gasController.deleteGas(username);
     *         Map<String, String> response = new HashMap<>();
     *         response.put("username", username);
     *
     *         return response;
     * @return
     */

    //method return object information of login user
    @GetMapping("/account/login/info")
    public accountInfo getAllInfo() {
        return currentUser;
    }



    //deletes account and gas stations associated
    @ApiOperation(value = "Delete account by username and children associated ", response = accountInfo.class)

    @DeleteMapping("userDelete/{username}")
    public Map<String, String> delete(@PathVariable String username) {

        deleteUser = accountInfoRepository.findByUsername(username);

        accountInfoRepository.deleteAll(deleteUser);
        gasController.deleteGas(username);
        Map<String, String> response = new HashMap<>();
        response.put("username", username);

        return response;
        //returns as json { "username": "bob" }
    }

    @ApiOperation(value = "Put account to update account information ", response = accountInfo.class)

    @PostMapping("accountInfo/post/")
    accountInfo PostAccountInfoByPath( @RequestBody accountInfo newAccnt) {
        System.out.println(accountInfoRepository.findAllByUsername(newAccnt.getUsername()));
        if (accountInfoRepository.findAllByUsername(newAccnt.getUsername()).size() > 1 ){
            System.out.println("working!");
            return null;

        }
        //try {
//            System.out.println(accountInfoRepository.findByUsernameAndPassword(newAccnt.getUsername(), newAccnt.getPassword()));
//            if (accountInfoRepository.findByUsernameAndPassword(newAccnt.getUsername(), newAccnt.getPassword()) != null){
//                System.out.println("Username already exists");
//                return null;
//            }
//        //} catch (Exception e){
//            System.out.println("Exception caught");
//            System.out.println();
//        //}

        accountInfoRepository.save(newAccnt);
        return newAccnt;
    }

    @ApiOperation(value = "Put account to update account information by ids ", response = accountInfo.class)
    @PutMapping("/accountInfo/put/{id}/station/{id2}")
    public accountInfo updateAccount(@PathVariable long id,@PathVariable long id2, @RequestBody accountInfo info){
       accountInfo userRN = accountInfoRepository.findById(id);
       GasInfo station = gasRepo.getByGasId(id2);

       userRN.setFirstname(info.getFirstname());
       userRN.setLastname(info.getLastname());
       userRN.addGasStations(station);
       userRN.setAccessLevel(info.getAccessLevel());
       //userRN.set

        accountInfoRepository.save(userRN);
        currentUser = userRN;
       return userRN;
    }
    //@ApiOperation(value = "Get stations owned by account", response = accountInfo.class)

    //return a list of all gas stations owned by user
    @ApiOperation(value = "Get all stations owned by a user ", response = Iterable.class)
    @GetMapping("/accountInfo/{id}/allStations")
    public List<GasInfo> getAllUserStations(@PathVariable long id){
        return accountInfoRepository.findById(id).getGasStations();
    }

    //Edit a users staions given user id and station id
//    @PutMapping("/accountInfo/put/editUserStation/{idu}/{ids}")
//    GasInfo editUserStation(@PathVariable long idu, @PathVariable long ids, @RequestBody GasInfo staion){
//        accountInfo currUser = accountInfoRepository.findById(idu);
//        GasInfo currStation = GasController.putGas(ids, staion);
//
//    }

    //get all favorite stations
    @ApiOperation(value = "Get all favourited stations by a user ", response = Iterable.class)
    @GetMapping("/accountInfo/favStations/{id}/all")
    public List<GasInfo> getFavStations(@PathVariable Long id){
        return accountInfoRepository.findById(id).getFavStations();
    }


    //Put fav station
    @ApiOperation(value = "Put a favourited stations by a user ", response = accountInfo.class)
    @PutMapping("/accountInfo/{uid}/put/favStation/{sid}")
    public accountInfo putFavStations(@PathVariable long uid, @PathVariable long sid){
        accountInfo user = accountInfoRepository.findById(uid);
        user.addFavStation(gasRepo.getByGasId(sid));
        accountInfoRepository.save(user);
        return user;
    }

    @ApiOperation(value = "Delete a favourited stations by a user ", response = accountInfo.class)
    @DeleteMapping("/accountInfo/del/{uid}/favStation/{sid}")
    public accountInfo deleteFavStaion(@PathVariable long uid, @PathVariable long sid){
        accountInfo user = accountInfoRepository.findById(uid);
        user.delFavStation(gasRepo.getByGasId(sid));
        accountInfoRepository.save(user);
        return user;
    }

    //find station in favList
    @ApiOperation(value = "Get a favourited stations by a user if it exists ", response = boolean.class)
    @GetMapping("/accountInfo/get/isFav/{uid}/{sid}")
    public Map<String, Boolean> isInFav(@PathVariable long uid, @PathVariable long sid){
        Map<String, Boolean> val = new HashMap<>();

         val.put("val",accountInfoRepository.findById(uid).isFav(gasRepo.getByGasId(sid)));
        return val;
    }


    //Delete method
    @DeleteMapping("/account/delete/{id}")
    void deleteAccountByID(@PathVariable Long id){
        int ID = id.intValue();
        accountInfoRepository.deleteById(ID);
    }

}

