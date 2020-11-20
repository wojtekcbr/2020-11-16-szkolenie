package pl.jsystems.qa.qaapi.model.azure;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AzureAuthor {

    @JsonProperty(required = true)
    public long id;

    @JsonProperty(required = true)
    public long idBook;

    @JsonProperty(required = true)
    public String firstName;

    @JsonProperty(required = true)
    public String lastName;
}