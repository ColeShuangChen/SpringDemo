package cole.demo.controller;

import cole.demo.model.City;
import cole.demo.model.Query;
import cole.demo.service.CityService;
import cole.demo.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value="/weather")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private CityService cityService;

    @GetMapping()
    public String weather(Model model) {
        List<String> cities = cityService.findAllCityNames();
        model.addAttribute("cities", cities);
        return "weather";
    }

    @PostMapping()
    public String weatherQuery(@Validated Query query, BindingResult result, Model model ) {
        if (result.hasErrors()){
            List<String> cities = cityService.findAllCityNames();
            model.addAttribute("cities", cities);
            model.addAttribute("errors", result.getAllErrors());
            return "weather";
        }
        List<City> cityList=cityService.findByDynamicCases(query);
        model.addAttribute("result", cityList);
        return "result";
    }

}
