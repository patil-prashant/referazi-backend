package com.referazi.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class SecurityManager {

    /**
     * To log messages
     */
    private static final Logger log = LoggerFactory.getLogger(SecurityManager.class);

    public String generateAccessToken() {
        return UUID.randomUUID().toString();
    }


}
