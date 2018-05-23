/**
  * Copyright 2018 bejson.com 
  */
package cole.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Id;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
/**
 * Auto-generated: 2018-05-23 0:27:19
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    private String date;
    private String sunrise;
    private String high;
    private String low;
    private String sunset;
    private int aqi;
    private String fx;
    private String fl;
    private String type;
    private String notice;
    public void setDate(String date) {
         this.date = date;
     }

    @Override
    public String toString() {
        return "Forecast{" +
                "date='" + date + '\'' +
                ", sunrise='" + sunrise + '\'' +
                ", high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", sunset='" + sunset + '\'' +
                ", aqi=" + aqi +
                ", fx='" + fx + '\'' +
                ", fl='" + fl + '\'' +
                ", type='" + type + '\'' +
                ", notice='" + notice + '\'' +
                '}';
    }

    public String getDate() {
         return date;
     }

    public void setSunrise(String sunrise) {
         this.sunrise = sunrise;
     }
     public String getSunrise() {
         return sunrise;
     }

    public void setHigh(String high) {
         this.high = high;
     }
     public String getHigh() {
         return high;
     }

    public void setLow(String low) {
         this.low = low;
     }
     public String getLow() {
         return low;
     }

    public void setSunset(String sunset) {
         this.sunset = sunset;
     }
     public String getSunset() {
         return sunset;
     }

    public void setAqi(int aqi) {
         this.aqi = aqi;
     }
     public int getAqi() {
         return aqi;
     }

    public void setFx(String fx) {
         this.fx = fx;
     }
     public String getFx() {
         return fx;
     }

    public void setFl(String fl) {
         this.fl = fl;
     }
     public String getFl() {
         return fl;
     }

    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setNotice(String notice) {
         this.notice = notice;
     }
     public String getNotice() {
         return notice;
     }

}