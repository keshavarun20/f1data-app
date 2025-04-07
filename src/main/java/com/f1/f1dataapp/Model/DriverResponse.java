package com.f1.f1dataapp.Model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class DriverResponse {

    @JsonProperty("MRData")
    private MRData mrData;

    @Data
    public static class MRData {
        @JsonProperty("DriverTable")
        private DriverTable driverTable;
    }

    @Data
    public static class DriverTable {
        @JsonProperty("Drivers")
        private List<Driver> drivers;
    }

    @Data
    public static class Driver {
        @JsonProperty("driverId")  // Unique ID of the driver (e.g., "hamilton")
        private String driverId;

        @JsonProperty("code")  // Short code for the driver (e.g., "HAM")
        private String code;

        @JsonProperty("givenName")  // Driver's first name (e.g., "Lewis")
        private String givenName;

        @JsonProperty("familyName")  // Driver's last name (e.g., "Hamilton")
        private String familyName;

        @JsonProperty("nationality")  // Driver's nationality (e.g., "British")
        private String nationality;
    }
}

