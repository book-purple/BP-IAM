package com.bookpurple.iam.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@Builder
@AllArgsConstructor
public class SignUpResponseBo {

    @JsonProperty("authToken")
    private String authToken;
}
