package com.bookpurple.iam.enums;

import com.bookpurple.iam.constant.Constants;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 * Created by gauravsharma on 2019-03-13.
 */
public enum ProfilesEnum {

    DEV("dev", Constants.AuthConstants.OTP_DEV) {
        @Override
        public int getGeneratedOtp() {
            return this.getOtp();
        }
    },
    STAGING("staging", Constants.AuthConstants.OTP_DEV) {
        @Override
        public int getGeneratedOtp() {
            return this.getOtp();
        }
    },
    DOCK("dock", Constants.AuthConstants.OTP_DEV) {
        @Override
        public int getGeneratedOtp() {
            return this.getOtp();
        }
    },
    QA("qa", Constants.AuthConstants.OTP_DEV) {
        @Override
        public int getGeneratedOtp() {
            return this.getOtp();
        }
    },
    PROD("prod", Constants.AuthConstants.OTP_DEV) {
        @Override
        public int getGeneratedOtp() {
            return Integer.parseInt(RandomStringUtils.randomNumeric(Constants.AuthConstants.OTP_LENGTH));
        }
    };

    private String env;

    private int otp;

    private static final Map<String, ProfilesEnum> reverseProfileMap;

    static {
        reverseProfileMap = Arrays.stream(ProfilesEnum.values())
                .collect(Collectors.toMap(ProfilesEnum::getEnv, Function.identity()));
    }

    public String getEnv() {
        return env;
    }

    public int getOtp() {
        return otp;
    }

    ProfilesEnum(String env, int otp) {
        this.env = env;
        this.otp = otp;
    }

    public abstract int getGeneratedOtp();

    public static ProfilesEnum getProfilesByEnv(String environment) {
        return reverseProfileMap.get(environment);
    }

}
