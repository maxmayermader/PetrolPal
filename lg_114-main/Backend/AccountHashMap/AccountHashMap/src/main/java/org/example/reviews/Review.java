package org.example.reviews;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import org.example.account.accountInfo;
import org.example.gas.GasInfo;

import javax.persistence.*;

@Entity
@Table(
        name = "reviews"
)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ApiModelProperty(notes = "description of review",name="description",required=true)
    @Column(name = "description")
    private String description;

    @ApiModelProperty(notes = "rating of review",name="rating",required=true)
    @Column(name = "rating")
    private double rating;

    @ApiModelProperty(notes = "station which review is written about",name="gasID",required=true)
    @ManyToOne
    @JoinColumn(name = "gas_id")
    @JsonIgnore
    private GasInfo gasID;

    @ApiModelProperty(notes = "user that wrote review",name="userID",required=true)
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private accountInfo userID;

    public Review (){}



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public double getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUserID(accountInfo userID) {
        this.userID = userID;
    }

    public accountInfo getUserID() {
        return userID;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public GasInfo getGasID() {
        return gasID;
    }

    public void setGasID(GasInfo gasID) {
        this.gasID = gasID;
    }
}
