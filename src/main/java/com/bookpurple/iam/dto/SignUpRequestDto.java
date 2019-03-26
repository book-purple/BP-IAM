package com.bookpurple.iam.dto;

import com.bookpurple.iam.model.AbstractSignUpRequest;
import lombok.Builder;

public class SignUpRequestDto extends AbstractSignUpRequest {

    @Builder
    public SignUpRequestDto(String deviceType, String appVersion, String appVersionCode, String osVersion, String deviceName) {
        super(deviceType, appVersion, appVersionCode, osVersion, deviceName);
    }
}
