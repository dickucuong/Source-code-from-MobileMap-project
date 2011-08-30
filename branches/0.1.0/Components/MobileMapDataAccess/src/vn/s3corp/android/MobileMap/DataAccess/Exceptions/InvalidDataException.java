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
 * The exception which indicates an invalid input data.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class InvalidDataException extends Exception {

    /**
     * Global serial version UID.
     */
    private static final long serialVersionUID = 8820520927984559789L;

    private String            mParameterName;

    /**
     * Get the name of parameter which has invalid data.
     * 
     * @param
     * @return The name of parameter which has invalid data
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public String getParameterName() {
        return this.mParameterName;
    }

    /**
     * The constructor.
     * 
     * @param parameterName
     *            The name of parameter which has invalid data.
     * @param detailMsg
     *            The detail message.
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public InvalidDataException(String parameterName, String detailMsg) {
        super(detailMsg);

        if (null == parameterName) {
            this.mParameterName = "";
        } else {
            this.mParameterName = parameterName;
        }
    }

    /**
     * The constructor.
     * 
     * @param parameterName
     *            The name of parameter which has invalid data.
     * @param detailMsg
     *            The detail message.
     * @param cause
     *            The cause of exception
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public InvalidDataException(String parameterName, String detailMsg, Exception cause) {
        super(detailMsg, cause);

        if (null == parameterName) {
            this.mParameterName = "";
        } else {
            this.mParameterName = parameterName;
        }
    }
}
