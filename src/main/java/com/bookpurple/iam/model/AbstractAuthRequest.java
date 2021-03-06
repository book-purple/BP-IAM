package com.bookpurple.iam.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/*
 * Created by gauravsharma on 2019-03-10.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@MappedSuperclass
@Data
@NoArgsConstructor
public abstract class AbstractAuthRequest {

    @NotNull
    @NotBlank
    @NotEmpty
    @JsonProperty("mobile")
    private String mobile;

    @NotNull
    @NotBlank
    @NotEmpty
    @JsonProperty("deviceId")
    private String deviceId;

    @NotNull
    @NotBlank
    @NotEmpty
    @JsonProperty("countryCode")
    private String countryCode;

    @JsonProperty("otp")
    private String otp;
}
