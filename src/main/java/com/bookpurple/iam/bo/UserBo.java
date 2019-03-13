package com.bookpurple.iam.bo;

import com.bookpurple.iam.model.AbstractUserModel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/*
 * Created by gauravsharma on 2019-03-10.
 */
@Data
@NoArgsConstructor
public class UserBo extends AbstractUserModel {

    @Builder
    public UserBo(Long id,
                  @NotNull @NotBlank @NotEmpty String mobile,
                  @NotNull @NotBlank @NotEmpty String deviceId,
                  @NotNull @NotBlank @NotEmpty String countryCode,
                  @NotNull @NotBlank @NotEmpty String userUId,
                  String firstName,
                  String lastName,
                  Date createdAt,
                  Date modifiedAt,
                  int status) {
        super(id, mobile, deviceId, countryCode, userUId, firstName, lastName, createdAt, modifiedAt, status);
    }
}
