package com.f1.f1dataapp.service;

import com.f1.f1dataapp.Model.DriverResponse;
import com.f1.f1dataapp.Model.RaceResponse;
import com.f1.f1dataapp.Model.SeasonResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class F1Service {

    private final ObjectMapper objectMapper;
    private final WebClient webClient;

    public F1Service(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.webClient = WebClient.builder()
                .baseUrl("https://api.jolpi.ca/ergast")
                .build();
    }

    public List<String> getSeasons() {
        String url = "/f1/seasons.json?limit=100";

        var response = webClient.get().uri(url).retrieve()
                .bodyToMono(SeasonResponse.class).block();

        if (response == null || response.getMrData() == null) return new ArrayList<>();

        return response.getMrData().getSeasonTable().getSeasons()
                .stream()
                .map(season -> season.getSeason())
                .collect(Collectors.toList());
    }

    public List<RaceResponse.Race> getRaces(String year) {
        String url = "/f1/" + year + ".json?limit=100";

        var response = webClient.get().uri(url).retrieve()
                .bodyToMono(RaceResponse.class).block();

        if (response == null || response.getMrData() == null ||
                response.getMrData().getRaceTable() == null) return new ArrayList<>();

        return response.getMrData().getRaceTable().getRaces();
    }

    public List<DriverResponse.Driver> getDrivers(String year) {
        String url = "/f1/" + year + "/drivers.json?limit=100";

        var response = webClient.get().uri(url).retrieve()
                .bodyToMono(DriverResponse.class).block();

        if (response == null || response.getMrData() == null ||
                response.getMrData().getDriverTable() == null) return new ArrayList<>();

        return response.getMrData().getDriverTable().getDrivers();
    }

    public List<RaceResponse.LapTiming> getLapTimes(String year, String round, String driverID) {
        String url = "/f1/" + year + "/" + round + "/drivers/" + driverID + "/laps.json?limit=1200";

        var rawJson = webClient.get().uri(url).retrieve()
                .bodyToMono(String.class).block();

        if (rawJson == null || rawJson.isBlank()) return new ArrayList<>();

        RaceResponse response;
        try {
            response = objectMapper.readValue(rawJson, RaceResponse.class);
        } catch (Exception e) {
            return new ArrayList<>();
        }

        if (response == null || response.getMrData() == null ||
                response.getMrData().getRaceTable() == null ||
                response.getMrData().getRaceTable().getRaces().isEmpty()) {
            return new ArrayList<>();
        }

        var laps = response.getMrData().getRaceTable().getRaces().get(0).getLaps();
        if (laps == null || laps.isEmpty()) return new ArrayList<>();

        return getLapTimings(driverID, laps);
    }

    private static List<RaceResponse.LapTiming> getLapTimings(String driverID, List<RaceResponse.Lap> laps) {
        List<RaceResponse.LapTiming> driverLapTimes = new ArrayList<>();

        for (RaceResponse.Lap lap : laps) {
            if (lap.getTimings() == null) continue;

            for (RaceResponse.LapTiming timing : lap.getTimings()) {
                if (timing != null && driverID.equals(timing.getDriverId())) {
                    RaceResponse.LapTiming lapTime = new RaceResponse.LapTiming();
                    lapTime.setDriverId(driverID);
                    lapTime.setTime(timing.getTime());
                    lapTime.setPosition(timing.getPosition());
                    driverLapTimes.add(lapTime);
                }
            }
        }
        return driverLapTimes;
    }

}
