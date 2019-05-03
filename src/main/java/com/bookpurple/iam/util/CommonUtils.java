package com.bookpurple.iam.util;

import java.time.Instant;
import java.util.Date;

/*
 * Created by gauravsharma on 2019-03-10.
 */
public class CommonUtils {

    public static Date getDate() {
        return Date.from(Instant.now());
    }

    public static Date getDateWithAddedMinutes(int minutes) {
        return Date.from(Instant.now().plusSeconds(minutes * 60));

    }
}
