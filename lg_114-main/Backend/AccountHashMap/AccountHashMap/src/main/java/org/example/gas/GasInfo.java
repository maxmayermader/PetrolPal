package org.example.gas;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiModelProperty;
import org.example.account.accountInfo;

import org.example.map.Geoapify;
import org.springframework.web.client.RestTemplate;


import javax.persistence.*;
import java.io.IOException;

@Entity
@Table(
        name = "GasInfo"
)
public class GasInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long gasId;

    @ApiModelProperty(notes = "username tied to gas station",name="username",required=true)
    @Column(name = "username")
    private String username;

    @ApiModelProperty(notes = "station name",name="stationName",required=true)
    @Column(name = "stationName")
    private String stationName;

    @ApiModelProperty(notes = "price of gas",name="price",required=true)
    @Column(name = "price")
    private double price;

    @ApiModelProperty(notes = "address of station",name="address",required=true)
    @Column(name = "address")
    private String address;

    @ApiModelProperty(notes = "description of gas station",name="description",required=true)
    @Column(name = "description")
    private String description;

    @ApiModelProperty(notes = "status message for http requests",name="status",required=true)
    @Column(name = "status")
    private String status;

    @ApiModelProperty(notes = "longitude of gas station",name="lon",required=true)
    @Column(name = "lon")
    private double lon;

    @ApiModelProperty(notes = "latitude of gas station",name="lat",required=true)
    @Column(name = "lat")
    private double lat;

    @ApiModelProperty(notes = "used that owns gas station",name="userID",required=true)
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private accountInfo userID;

    @ApiModelProperty(notes = "distance calculated",name="dist",required=true)
    private double dist;


    public GasInfo(){}
    public GasInfo(Long gasId, String username, String stationName, double price, String address, String description, double lon, double lat, double dist){
        this.gasId = gasId;
        this.username = username;
        this.stationName = stationName;
        this.price = price;
        this.address = address;
        this.description = description;
        this.status = status;
        this.lon = lon;
        this.lat = lat;
        this.dist = dist;



    }



    public Long getGasId(){
        return gasId;
    }
    public void setGasId(Long id){
        this.gasId = gasId;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getStationName(){
        return stationName;
    }
    public void setStationName(String stationName){
        this.stationName = stationName;
    }

    public Double getPrice(){
        return price;
    }
    public void setPrice(Double price){
        this.price = price;
    }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }

    public Double getLon(){
        return lon;}
    public void setLon(Double lon){
        this.lon = lon;
    }

    public Double getLat(){
        return lat;}
    public void setLat(Double lat){
        this.lat = lat;
    }

    public Double getDist(){
        return dist;}
    public void setDist(Double dist){
        this.dist = dist;
    }




    public accountInfo getUserID(){
        return userID;
    }

    public void setUserID(accountInfo userID) {
        this.userID = userID;
    }



}
