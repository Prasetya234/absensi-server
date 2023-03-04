package com.microservice.lab.utils;

import java.util.concurrent.TimeUnit;

public class Data {
    public static final Long TOKEN_EXPIRE = TimeUnit.HOURS.toMillis(5);
    public static final int REVERSE_TOKEN_EXPIRE = 1800000;
}
