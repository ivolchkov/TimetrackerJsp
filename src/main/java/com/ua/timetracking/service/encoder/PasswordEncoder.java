package com.ua.timetracking.service.encoder;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class PasswordEncoder {
    private static final Logger LOGGER = Logger.getLogger(PasswordEncoder.class);

    public Optional<String> encode(String pass){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] passBytes = pass.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuilder sb = new StringBuilder();

            for (byte b : digested) {
                sb.append(Integer.toHexString(0xff & b));
            }

            return Optional.of(sb.toString());
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.warn("Encode process exception", ex);
            return Optional.empty();
        }
    }
}
