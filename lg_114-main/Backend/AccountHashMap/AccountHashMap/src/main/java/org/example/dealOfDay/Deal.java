package org.example.dealOfDay;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Deal_of_day")
@Data
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(notes = "station name of deal",name="stationName",required=true)
    @Column
    private String stationName;

    @ApiModelProperty(notes = "content of deal",name="content",required=true)
    @Lob
    private String content;

    @ApiModelProperty(notes = "date of deal",name="sent",required=true)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sent")
    private Date sent = new Date();

    public Deal() {}

    public Deal(String stationName, String content){
        this.stationName = stationName;
        this.content = content;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Date getSent() {
        return sent;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getStationName() {
        return stationName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSent(Date sent) {
        this.sent = sent;
    }
}
