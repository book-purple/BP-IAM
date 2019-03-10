package com.bookpurple.iam.bo;

import com.bookpurple.iam.model.AbstractUserModel;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/*
 * Created by gauravsharma on 2019-03-10.
 */
@Data
public class UserBo extends AbstractUserModel {

    @Builder
    public UserBo(Long id,
                  @NotNull @NotBlank @NotEmpty String deviceId,
                  @NotNull @NotBlank @NotEmpty Integer userId,
                  @NotNull @NotBlank @NotEmpty String userUId,
                  String firstName,
                  String lastName,
                  Date createdAt,
                  Date modifiedAt,
                  int status) {
        super(id, deviceId, userId, userUId, firstName, lastName, createdAt, modifiedAt, status);
    }
}
