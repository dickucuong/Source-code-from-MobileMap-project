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
 * The exception which indicates an error in SQL database.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class DatabaseException extends Exception {

    /**
     * Global serial version UID.
     */
    private static final long serialVersionUID = 8304448425131574977L;

    private String            mSqlError;

    /**
     * Get the SQL error string.
     * 
     * @param
     * @return The SQL error string
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public String getSqlError() {
        return this.mSqlError;
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
    public DatabaseException(String sqlError, String detailMsg) {
        super(detailMsg);

        if (null == sqlError) {
            this.mSqlError = "";
        } else {
            this.mSqlError = sqlError;
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
    public DatabaseException(String sqlError, String detailMsg, Exception cause) {
        super(detailMsg, cause);

        if (null == sqlError) {
            this.mSqlError = "";
        } else {
            this.mSqlError = sqlError;
        }
    }
}
