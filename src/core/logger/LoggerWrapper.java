/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.logger;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 *
 * @author Raul.MONTES
 */
public class LoggerWrapper {
    
    
    private Logger logger;
    private static String FQCN = LoggerWrapper.class.getName();

    private LoggerWrapper() {
    }

    public static LoggerWrapper getLogger(String name) {
	LoggerWrapper wrapperInstance = new LoggerWrapper();
	wrapperInstance.logger = Logger.getLogger(name);
	return wrapperInstance;

    }

    public void setLevel(Level level) {
	logger.setLevel(level);
    }

    // debug

    public void debug(String user ,Object message) {
        message="["+user+"]"+message;
	logger.log(FQCN, Level.DEBUG, message, null);
    }

    public void debug(String user ,String message, Throwable t) {
        message="["+user+"]"+message;
	logger.log(FQCN, Level.DEBUG, message, t);
    }
    // info

    public void info(String user ,String message) {
        message="["+user+"]"+message;
	logger.log(FQCN, Level.INFO, message, null);
    }

    public void info(String user ,String message, Throwable t) {
        message="["+user+"]"+message;
	logger.log(FQCN, Level.INFO, message, t);
    }

    

    // warn

    public void warn(String user ,String message) {
        message="["+user+"]"+message;
	logger.log(FQCN, Level.WARN, message, null);
    }

    public void warn(String user ,String message, Throwable t) {
        message="["+user+"]"+message;
	logger.log(FQCN, Level.WARN, message, t);
    }

   
    // error

    public void error(String user ,String message) {
        message="["+user+"]"+message;
	logger.log(FQCN, Level.ERROR, message, null);
    }

    public void error(String user ,String message, Throwable t) {
        message="["+user+"]"+message;
	logger.log(FQCN, Level.ERROR, message, t);
    }

   
    // fatal

    public void fatal(String user ,String message) {
        message="["+user+"]"+message;
	logger.log(FQCN, Level.FATAL, message, null);
    }

    public void fatal(String user ,String message, Throwable t) {
        message="["+user+"]"+message;
	logger.log(FQCN, Level.FATAL, message, t);
    }

   
    // trace

    public void trace(String user ,String message) {
        message="["+user+"]"+message;
	logger.log(FQCN, Level.TRACE, message, null);
    }

    public void trace(String user ,String message, Throwable t) {
        message="["+user+"]"+message;
	logger.log(FQCN, Level.TRACE, message, t);
    }

   

    // log
    public void log(String user ,Level level, String message) {
        message="["+user+"]"+message;
	logger.log(FQCN, level, message, null);
    }

    public void log(String user ,Level level, String message, Throwable t) {
        message="["+user+"]"+message;
	logger.log(FQCN, level, message, t);
    }


    // utilities

    public boolean isInfoEnabled() {
	return logger.isInfoEnabled();
    }

    public boolean isDebugEnabled() {
	return logger.isDebugEnabled();
    }

    public boolean isTraceEnabled() {
	return logger.isTraceEnabled();
    }

    public boolean isEnabledFor(Priority level) {
	return logger.isEnabledFor(level);
    }
    
}
