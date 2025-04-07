package com.f1.f1dataapp.controller;

import com.f1.f1dataapp.Model.DriverResponse;
import com.f1.f1dataapp.Model.RaceResponse;
import com.f1.f1dataapp.service.F1Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class F1Controller {

    private final F1Service f1Service;

    public F1Controller(F1Service f1Service) {
        this.f1Service = f1Service;
    }

    @GetMapping
    public String index(Model model,
                        @RequestParam(required = false) String year,
                        @RequestParam(required = false) String round,
                        @RequestParam(required = false) String driverId) {

        year = (year != null) ? year : "2022";

        model.addAttribute("years", f1Service.getSeasons());
        model.addAttribute("races", f1Service.getRaces(year));
        model.addAttribute("drivers", f1Service.getDrivers(year));

        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedRound", round);
        model.addAttribute("selectedDriverId", driverId);

        if (year != null && round != null && driverId != null) {
            List<RaceResponse.LapTiming> lapTimes = f1Service.getLapTimes(year, round, driverId);
            model.addAttribute("lapTimes", lapTimes);
        }

        return "index"; //  Always return the same view
    }

    @GetMapping("/races")
    @ResponseBody
    public List<RaceResponse.Race> getRaces(@RequestParam String year) {
        return f1Service.getRaces(year);
    }

    @GetMapping("/drivers")
    @ResponseBody
    public List<DriverResponse.Driver> getDrivers(@RequestParam String year) {
        return f1Service.getDrivers(year);
    }

    @GetMapping("/laps")
    @ResponseBody
    public List<RaceResponse.LapTiming> getLapTimes(@RequestParam String year,
                                                    @RequestParam String round,
                                                    @RequestParam String driverId) {
        return f1Service.getLapTimes(year, round, driverId);
    }
}
