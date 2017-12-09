/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.logger;

import org.apache.log4j.Level;

/**
 *
 * @author Raul.MONTES
 */
public class LogService {
    
    private static final String LOGGER_NAME = "Obed77";

    private static final Level LOG_LEVEL = Level.DEBUG;

    public static LoggerWrapper logger = LoggerWrapper.getLogger(LOGGER_NAME);

    static {
	try {
	    System.out.println("[" + LOGGER_NAME + "] INFO: LogService: Initializing log service...");
	    logger.setLevel(LOG_LEVEL);
	    System.out.println("[" + LOGGER_NAME + "] INFO: LogService: Log service level : " + LOG_LEVEL.toString());
	} catch (Exception e) {
	    System.err.println("[" + LOGGER_NAME + "] ERROR: LogService: Can't initialize log service : " + e);
	}
    }
    
}
