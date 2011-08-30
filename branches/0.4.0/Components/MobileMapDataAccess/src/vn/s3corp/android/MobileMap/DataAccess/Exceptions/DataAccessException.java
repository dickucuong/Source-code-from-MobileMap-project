/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.DataAccess.Exceptions;

/**
 * The exception which indicates an error occurs while processing business rules
 * in DataAccess component.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class DataAccessException extends Exception {

    /**
     * Global serial version UID.
     */
    private static final long serialVersionUID = -9063375677029003820L;

    private String mError;

    /**
     * Get the error string of Mobile Map system.
     * 
     * @param
     * @return The error string of Mobile Map system
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public String getError() {
        return this.mError;
    }

    /**
     * The constructor.
     * 
     * @param error
     *            The error string of Mobile Map system
     * @param detailMsg
     *            The detail message.
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public DataAccessException(String error, String detailMsg) {
        super(detailMsg);

        if (null == error) {
            this.mError = "";
        } else {
            this.mError = error;
        }
    }

    /**
     * The constructor.
     * 
     * @param error
     *            The error string of Mobile Map system
     * @param detailMsg
     *            The detail message.
     * @param cause
     *            The cause of exception
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public DataAccessException(String error, String detailMsg, Exception cause) {
        super(detailMsg, cause);

        if (null == error) {
            this.mError = "";
        } else {
            this.mError = error;
        }
    }
}
