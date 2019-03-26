package com.bookpurple.iam.bo;

import com.bookpurple.iam.model.AbstractErrorModel;
import com.bookpurple.iam.model.AbstractSignUpResponseModel;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class SignUpResponseBo extends AbstractSignUpResponseModel {

    @Builder
    public SignUpResponseBo(String authToken, String userUid, AbstractErrorModel abstractErrorModel) {
        super(authToken, userUid, abstractErrorModel);
    }
}
