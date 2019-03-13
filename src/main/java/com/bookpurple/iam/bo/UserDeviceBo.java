package com.bookpurple.iam.bo;

import com.bookpurple.iam.model.AbstractUserDeviceModel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/*
 * Created by gauravsharma on 2019-03-10.
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class UserDeviceBo extends AbstractUserDeviceModel {

    @Builder
    public UserDeviceBo(Long id,
                        @NotNull @NotBlank @NotEmpty String deviceId,
                        @NotNull @NotBlank @NotEmpty Integer userId,
                        @NotNull @NotBlank @NotEmpty String userUId,
                        String mobileVersion,
                        String appVersion,
                        int appVersionCode,
                        String deviceType,
                        String osVersion,
                        Date createdAt,
                        Date modifiedAt,
                        int status) {
        super(id, deviceId, userId, userUId, mobileVersion, appVersion, appVersionCode, deviceType, osVersion, createdAt, modifiedAt, status);
    }
}
