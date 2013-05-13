/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sol.neptune.seneca.exceptions;

/**
 *
 * @author murdoc
 */
public class ManagerException extends Exception {

    private static final long serialVersionUID = 1L;

    public ManagerException(Throwable cause) {
        super(cause);
    }

    public ManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}