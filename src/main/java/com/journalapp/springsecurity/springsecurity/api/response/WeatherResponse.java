package com.journalapp.springsecurity.springsecurity.api.response;

import lombok.Data;

import javax.xml.stream.Location;
import java.util.ArrayList;
import java.util.List;

@Data
public class WeatherResponse {

        public Location location;

        @Data
        public class Location{
                public String name;
                public String country;
                public String region;
                public String lat;
                public String lon;

        }
}
