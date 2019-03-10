package com.bookpurple.iam.constant;

/*
 * Created by gauravsharma on 2019-03-09.
 */
public class Constants {

    public static class SecurityConstants {
        public static final String X_GBP_AUTH = "X-GBP-AUTH";
        public static final String AUTHORIZATION = "Authorization";
    }

    public static class AuthConstants {
        public static final int OTP_EXPIRY_OFFSET = 2;
        public static final int OTP_LENGTH = 6;
        public static final int OTP_RESEND_LIMIT = 3;
        public static final int TEMP_AUTH_ACTIVE = 1;
        public static final int TEMP_AUTH_INACTIVE = 0;
        public static final int USER_ID_LENGTH = 20;
    }
}
