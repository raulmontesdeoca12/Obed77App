/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package core.logger;

import java.security.Principal;


/**
 *
 * @author Raul.MONTES
 */
public class LogMessageFormatHelper {
    
     public static String formatMessage(String userPrincipalName, String msg) {
	userPrincipalName = userPrincipalName;
	StringBuffer sb = new StringBuffer();
	sb.append("[");
	if (userPrincipalName != null) {
	    sb.append(userPrincipalName);
	} else {
	    sb.append("NO ID");
	}
	sb.append("] ");
	sb.append(msg);
	return sb.toString();
    }

    public static String formatMessage(String userPrincipalName, Object message) {
	String msg = (message != null ? message.toString() : null);
	return formatMessage(userPrincipalName, msg);
    }

    public static String formatMessage(String userPrincipalName, String id, Object message) {
	String msg = (message != null ? message.toString() : null);
	return formatMessage(userPrincipalName, formatMessage(id, msg));
    }

    public static String formatMessage(Principal userPrincipal, Object message) {
	return formatMessage(userPrincipal != null ? userPrincipal.getName() : null, (message != null ? message.toString() : null));
    }

    public static String formatMessage(Principal userPrincipal, String id, Object message) {
	return formatMessage(userPrincipal != null ? userPrincipal.getName() : null, formatMessage(id, (message != null ? message.toString() : null)));
    }
    
}
