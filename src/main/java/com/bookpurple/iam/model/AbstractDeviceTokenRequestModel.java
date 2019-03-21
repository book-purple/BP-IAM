package com.bookpurple.iam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.MappedSuperclass;

/*
 * Created by Gaurav Sharma on 21 Mar 2019
 */
@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractDeviceTokenRequestModel {

    @JsonProperty("deviceToken")
    private String deviceToken;

    @JsonProperty("userUid")
    private String useruUid;
}
