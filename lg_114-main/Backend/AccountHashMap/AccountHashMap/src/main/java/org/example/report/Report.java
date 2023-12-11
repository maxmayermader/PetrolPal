package org.example.report;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.example.account.accountInfo;
import org.example.gas.GasInfo;

import javax.persistence.*;

@Entity
@Table(
        name = "report"
)
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(notes = "price of report",name="price",required=true)
    @Column(name = "price")
    private double price;

    @ApiModelProperty(notes = "date of report",name="date",required=true)
    @Column(name = "date")
    private String date;

    @ApiModelProperty(notes = "station which report is written about",name="gasID",required=true)
    @ManyToOne
    @JoinColumn(name = "gas_id")
    @JsonIgnore
    private GasInfo gasID;

    @ApiModelProperty(notes = "user that wrote report",name="userID",required=true)
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private accountInfo userID;

    public Report(){};

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public GasInfo getGasID() {
        return gasID;
    }

    public accountInfo getUserID() {
        return userID;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setGasID(GasInfo gasID) {
        this.gasID = gasID;
    }

    public void setUserID(accountInfo userID) {
        this.userID = userID;
    }

    public double getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
