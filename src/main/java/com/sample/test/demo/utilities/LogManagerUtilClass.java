package com.sample.test.demo.utilities;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LogManagerUtilClass {
    private static volatile Logger logger = null;

    private LogManagerUtilClass() {
    }

    public static Logger getInstance() {
        if (logger == null) {
            logger = LogManager.getRootLogger();
        }
        return logger;
    }
}
