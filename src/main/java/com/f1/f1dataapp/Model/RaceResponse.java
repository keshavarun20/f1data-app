package com.f1.f1dataapp.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class RaceResponse {
    @JsonProperty("MRData")
    private MRData mrData;

    @Data
    public static class MRData {
        @JsonProperty("RaceTable")
        private RaceTable raceTable;
    }

    @Data
    public static class RaceTable {
        @JsonProperty("Races")
        private List<Race> races;
    }

    @Data
    public static class LapTiming {
        @JsonProperty("driverId")  // Use @JsonProperty to match API response
        private String driverId;
        @JsonProperty("position")
        private String position;
        @JsonProperty("time")
        private String time;
    }

    @Data
    public static class Lap {
        @JsonProperty("number")
        private String number;
        @JsonProperty("Timings") // Corrected field name
        private List<LapTiming> Timings;
    }

    @Data
    public static class Race {
        @JsonProperty("raceName")
        private String raceName;
        @JsonProperty("round")
        private String round;
        @JsonProperty("date")
        private String date;
        @JsonProperty("time")
        private String time;
        @JsonProperty("season")
        private String season;
        @JsonProperty("Laps")
        private List<Lap> Laps;
    }

}


