package org.example.account;

import javax.persistence.Entity;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import org.example.gas.GasInfo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(
        name = "account"
)

public class accountInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "username of account",name="username",required=true)
    @Column(name = "username")
    private String username;
    @ApiModelProperty(notes = "password of account",name="password",required=true)
    @Column(name = "password")
    private String password;

    @ApiModelProperty(notes = "access level of account",name="accessLevel",required=true)
    @Column(name = "accessLevel")
    private String accessLevel;
    @ApiModelProperty(notes = "first name of account",name="firstname",required=true)

    @Column(name = "firstname")
    private String firstname;
    @ApiModelProperty(notes = "last name name of account",name="lastname",required=true)

    @Column(name = "lastname")
    private String lastname;
    @ApiModelProperty(notes = "whether account has been login in",name="login",required=false)

    @Column(name = "status")
    private String status;

//    @OneToOne
//    @JoinColumn(name = "gasId")
//    private GasInfo gasInfo;

    @OneToMany
    private List<GasInfo> gasStations;

    @OneToMany
    private List<GasInfo> favStations;


    public accountInfo(){gasStations = new ArrayList<>();}
    public accountInfo(Long id, String username, String password, String firstname, String lastname, String accessLevel, String status ){
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.accessLevel = accessLevel;
        this.status = status;
        gasStations = new ArrayList<>();



    }




        public Long getId(){
            return id;
        }
        public void setId(Long id){
            this.id = id;
        }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getAccessLevel(){
        return accessLevel;
    }
    public void setAccessLevel(String accessLevel){
        this.accessLevel = accessLevel;
    }

    public String getFirstname(){
        return firstname;
    }
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getLastname(){
        return lastname;
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getStatus(){
        return status;
    }
    public void setStatus(String status){
        this.status = status;
    }

//    public GasInfo getGasInfo() {
//        return gasInfo;
//    }
//
//    public void setGasInfo(GasInfo gasInfo) {
//        this.gasInfo = gasInfo;
//    }

    public List<GasInfo> getGasStations() {
        return gasStations;
    }

//    public void setGasStations(List<GasInfo> gasStations) {
//        this.gasStations = gasStations;
//    }

    public void addGasStations(GasInfo station){
        this.gasStations.add(station);
    }

    public List<GasInfo> getFavStations() {
        return favStations;
    }

    public void setFavStations(List<GasInfo> favStations) {
        this.favStations = favStations;
    }

    public void addFavStation(GasInfo station){
        this.favStations.add(station);
    }

    public void setGasStations(List<GasInfo> gasStations) {
        this.gasStations = gasStations;
    }

    public void delFavStation(GasInfo station){
        favStations.remove(station);
    }

    public boolean isFav(GasInfo station){
        return favStations.contains(station);
    }
}