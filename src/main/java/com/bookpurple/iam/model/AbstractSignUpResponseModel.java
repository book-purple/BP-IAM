package com.bookpurple.iam.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.persistence.MappedSuperclass;

@JsonIgnoreProperties(ignoreUnknown = true)
@MappedSuperclass
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractSignUpResponseModel {

    @JsonProperty("authToken")
    private String authToken;

    @JsonProperty("userUid")
    private String userUid;

    @JsonProperty("error")
    private AbstractErrorModel abstractErrorModel;
}
