package pl.jsystems.qa.qaapi.model.error;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {

    @JsonProperty("Error")
    public SingleError error;
}