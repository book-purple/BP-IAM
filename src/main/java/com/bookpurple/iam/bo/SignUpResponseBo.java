package com.bookpurple.iam.bo;

import com.bookpurple.iam.model.AbstractErrorModel;
import com.bookpurple.iam.model.AbstractSignUpResponseModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponseBo extends AbstractSignUpResponseModel {

    @Builder
    public SignUpResponseBo(String authToken, String userUid, AbstractErrorModel abstractErrorModel) {
        super(authToken, userUid, abstractErrorModel);
    }
}
