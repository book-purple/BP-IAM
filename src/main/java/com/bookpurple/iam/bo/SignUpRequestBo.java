package com.bookpurple.iam.bo;

import com.bookpurple.iam.model.AbstractSignUpRequest;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
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
