package com.bookpurple.iam.constant;

/*
 * Created by gauravsharma on 2019-03-09.
 */
public class Constants {

    public static class SecurityConstants {
        public static final String X_GBP_AUTH = "X-GBP-AUTH";
        public static final String AUTHORIZATION = "Authorization";
    }

    public static class UriConstants {
        public static final String OTP_GENERATE = "/otp/generate";
        public static final String SIGNUP = "/signup";
        public static final String DEVICE_REGISTRATION = "/device";
    }

    public static class AuthConstants {
        public static final int OTP_EXPIRY_OFFSET = 2;
        public static final int OTP_LENGTH = 6;
        public static final int OTP_RESEND_LIMIT = 3;

        public static final int TEMP_AUTH_ACTIVE = 1;
        public static final int TEMP_AUTH_INACTIVE = 0;

        public static final int USER_ID_LENGTH = 20;

        public static final int STATUS_ACTIVE = 1;
        public static final int STATUS_INACTIVE = 0;

        public static final int AUTH_TOKEN_ACTIVE = 1;
        public static final int AUTH_TOKEN_INACTIVE = 0;
        public static final int AUTH_TOKEN_ACTIVE_TIME = 90 * 24 * 60;
        public static final int OTP_DEV = 123456;
    }

    public static class Errors {
        public static final String INCORRECT_OTP_ERROR = "Incorrect OTP";
        public static final String INCORRECT_OTP_ERROR_MSG = "OTP entered is incorrect. Please try again!";
    }

    public static class RequestHeaders {
        public static final String APP_VERSION = "app_version";
        public static final String APP_VERSION_CODE = "app_version_code";
        public static final String device_type = "device_type";
        public static final String device_name = "device_name";
        public static final String os_version = "os_version";
    }
}
