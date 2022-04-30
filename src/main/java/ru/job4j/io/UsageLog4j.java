package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ilya Kaltygin
 * @version 1.0
 */
public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        byte b = 127;
        short s = 32767;
        int i = 2147483647;
        long l = 9223372036854775807L;
        float f = 3.4e+38f;
        double d = 1.7e+308;
        char c = 'A';
        boolean r = true;
        LOG.debug("byte: {}, short: {}, int: {}; long: {}, float: {}, double: {}, char: {}, boolean: {}",
                b, s, i, l, f, d, c, r);
    }
}