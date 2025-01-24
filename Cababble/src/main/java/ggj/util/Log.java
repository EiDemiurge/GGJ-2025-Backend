package ggj.util;


import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;
// import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {
    static Logger log = LoggerFactory.getLogger(Log.class);

    static{
        // LogManager.getRootLogger().isErrorEnabled()
    }

    public static void info(String s) {
        log.info(s);
    }

    public static void error(String s) {
        log.error(s);
    }
}
