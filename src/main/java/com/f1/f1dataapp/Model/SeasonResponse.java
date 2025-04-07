package com.f1.f1dataapp.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class SeasonResponse {

    @JsonProperty("MRData")
    private MRData mrData;

    @Data
    public static class MRData{
        @JsonProperty("SeasonTable")
        private SeasonTable seasonTable;
    }

    @Data
    public static class SeasonTable{
        @JsonProperty("Seasons")
        private List<Season> seasons;
    }

    @Data
    public static class Season {
        @JsonProperty("season")
        private String season;
    }
}
