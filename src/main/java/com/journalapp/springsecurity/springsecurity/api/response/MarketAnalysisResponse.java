package com.journalapp.springsecurity.springsecurity.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class APIResult {
    private Result result;

    @Data
    public static class Result {
        @JsonProperty("cash_amount")
        private double cashAmount;

        @JsonProperty("currency")
        private String currency;

        @JsonProperty("dividend_type")
        private String dividendType;

        @JsonProperty("ex_dividend_date")
        private String exDividendDate;

        @JsonProperty("frequency")
        private int frequency;

        @JsonProperty("id")
        private String id;

        @JsonProperty("pay_date")
        private String payDate;

        @JsonProperty("record_date")
        private String recordDate;

        @JsonProperty("ticker")
        private String ticker;
    }
}






