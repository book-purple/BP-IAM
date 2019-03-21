package com.bookpurple.iam.bo;

import com.bookpurple.iam.model.AbstractSignUpRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestBo extends AbstractSignUpRequest {

    @Builder
    public SignUpRequestBo(String deviceType,
                           String appVersion,
                           String appVersionCode,
                           String osVersion,
                           String deviceName) {
        super(deviceType, appVersion, appVersionCode, osVersion, deviceName);
    }
}
