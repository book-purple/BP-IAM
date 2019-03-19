package com.bookpurple.iam.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestBo {

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
