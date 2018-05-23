/**
  * Copyright 2018 bejson.com 
  */
package cole.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;
/**
 * Auto-generated: 2018-05-23 0:27:19
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    private String shidu;
    private int pm25;
    private int pm10;
    private String quality;
    private String wendu;
    private String ganmao;
    @JoinColumn(name="DATA_ID")
    @OneToOne
    private Yesterday yesterday;

    @Override
    public String toString() {
        return "Data{" +
                "shidu='" + shidu + '\'' +
                ", pm25=" + pm25 +
                ", pm10=" + pm10 +
                ", quality='" + quality + '\'' +
                ", wendu='" + wendu + '\'' +
                ", ganmao='" + ganmao + '\'' +
                ", yesterday=" + yesterday +
                ", forecast=" + forecast +
                '}';
    }
    @JoinColumn(name="DATA_FOR_ID")
    @OneToMany
    private List<Forecast> forecast;
    public void setShidu(String shidu) {
         this.shidu = shidu;
     }
     public String getShidu() {
         return shidu;
     }

    public void setPm25(int pm25) {
         this.pm25 = pm25;
     }
     public int getPm25() {
         return pm25;
     }

    public void setPm10(int pm10) {
         this.pm10 = pm10;
     }
     public int getPm10() {
         return pm10;
     }

    public void setQuality(String quality) {
         this.quality = quality;
     }
     public String getQuality() {
         return quality;
     }

    public void setWendu(String wendu) {
         this.wendu = wendu;
     }
     public String getWendu() {
         return wendu;
     }

    public void setGanmao(String ganmao) {
         this.ganmao = ganmao;
     }
     public String getGanmao() {
         return ganmao;
     }

    public void setYesterday(Yesterday yesterday) {
         this.yesterday = yesterday;
     }
     public Yesterday getYesterday() {
         return yesterday;
     }

    public void setForecast(List<Forecast> forecast) {
         this.forecast = forecast;
     }
     public List<Forecast> getForecast() {
         return forecast;
     }

}