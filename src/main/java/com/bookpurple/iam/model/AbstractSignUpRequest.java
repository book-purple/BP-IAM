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
public abstract class AbstractSignUpRequest {

    @JsonProperty("deviceType")
    private String deviceType;

    @JsonProperty("appVersion")
    private String appVersion;

    @JsonProperty("appVersionCode")
    private String appVersionCode;

    @JsonProperty("osVersion")
    private String osVersion;

    @JsonProperty("deviceName")
    private String deviceName;
}
