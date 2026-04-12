package com.journalapp.springsecurity.springsecurity.api.response;

import lombok.Data;

import javax.xml.stream.Location;
import java.util.List;

@Data
public class WeatherResponse {

        private Location location;
        private Current current;

        @Data
        public static class Location {
                private String name;
                private String country;
                private String region;
                private String lat;
                private String lon;
                private String timezone_id;
                private String localtime;
                private int localtime_epoch;
                private String utc_offset;
        }

        @Data
        public static class Current {
                private int temperature;
                private List<String> weather_descriptions;
                private int wind_speed;
        }
}
