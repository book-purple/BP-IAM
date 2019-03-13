package com.bookpurple.iam.bo;

import com.bookpurple.iam.model.AbstractTempAuthModel;
import lombok.*;

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
public class TempAuthBo extends AbstractTempAuthModel {

    @Builder
    public TempAuthBo(Long id,
                      @NotNull @NotBlank @NotEmpty String mobile,
                      @NotNull @NotBlank @NotEmpty String deviceId,
                      @NotNull @NotBlank @NotEmpty String countryCode,
                      String otp,
                      Date expiredAt,
                      Date createdAt,
                      Date modifiedAt,
                      int status) {
        super(id, mobile, deviceId, countryCode, otp, expiredAt, createdAt, modifiedAt, status);
    }
}
