package cole.demo.model;

import javax.validation.constraints.Min;

public class Query {

    private String cityName;
    @Min(value = 5, message = "PM 不能小于5")
    private Integer maxPM;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getMaxPM() {
        return maxPM;
    }

    public void setMaxPM(Integer maxPM) {
        this.maxPM = maxPM;
    }
}
