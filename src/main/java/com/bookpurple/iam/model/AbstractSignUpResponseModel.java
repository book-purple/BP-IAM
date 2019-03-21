package com.bookpurple.iam.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractSignUpResponseModel {

    @JsonProperty("authToken")
    private String authToken;

    @JsonProperty("error")
    private AbstractErrorModel abstractErrorModel;
}
