/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.DataAccess.DataObjects;

/**
 * The Email object in Mobile Map system.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class Email {

    private long mId;
    private String mEmailString;

    /**
     * Get the email's ID.
     * 
     * @param
     * @return The email's ID
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long getId() {
        return this.mId;
    }

    /**
     * Set the email's ID.
     * 
     * @param id
     *            The email's ID
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setId(long id) {
        this.mId = id;
    }

    /**
     * Get the text which describes email.
     * 
     * @param
     * @return The text which describes address
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public String getEmailString() {
        return this.mEmailString;
    }

    /**
     * Set the text which describes email.
     * 
     * @param emailString
     *            The text which describes email
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setEmailString(String emailString) {
        if (null == emailString) {
            this.mEmailString = "";
        } else {
            this.mEmailString = emailString;
        }
    }

    /**
     * Default constructor.
     * 
     * @param
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Email() {
        this.mId = 0;
        this.mEmailString = "";
    }

    /**
     * Copy constructor.
     * 
     * @param email
     *            The Email object
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Email(Email email) {
        if (null != email) {
            this.mId = email.getId();
            this.mEmailString = email.getEmailString();
        } else {
            this.mId = 0;
            this.mEmailString = "";
        }
    }
}
