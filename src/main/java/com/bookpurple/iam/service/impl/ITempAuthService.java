package com.bookpurple.iam.service.impl;

import com.bookpurple.iam.bo.AuthRequestBo;
import com.bookpurple.iam.bo.TempAuthBo;

/*
 * Created by gauravsharma on 2019-03-10.
 */
public interface ITempAuthService {

    TempAuthBo createTempAuth(AuthRequestBo authRequestBo);

    TempAuthBo findTempAuth(AuthRequestBo authRequestBo, int status);

    void updateTempAuth(TempAuthBo tempAuthBo);

    void deleteTempAuth(long id);
}
