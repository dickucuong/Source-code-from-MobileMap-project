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
 * The PhoneNumber object in Mobile Map system.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class PhoneNumber {

    private long mId;
    private String mPhoneNumberString;

    /**
     * Get the phone number's ID.
     * 
     * @param
     * @return The phone number's ID
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long getId() {
        return this.mId;
    }

    /**
     * Set the phone number's ID.
     * 
     * @param id
     *            The phone number's ID
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setId(long id) {
        this.mId = id;
    }

    /**
     * Get the text which describes phone number.
     * 
     * @param
     * @return The text which describes phone number
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public String getPhoneNumberString() {
        return this.mPhoneNumberString;
    }

    /**
     * Set the text which describes phone number.
     * 
     * @param phoneNumberString
     *            The text which describes phone number
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setPhoneNumberString(String phoneNumberString) {
        if (null == phoneNumberString) {
            this.mPhoneNumberString = "";
        } else {
            this.mPhoneNumberString = phoneNumberString;
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
    public PhoneNumber() {
        this.mId = 0;
        this.mPhoneNumberString = "";
    }

    /**
     * Copy constructor.
     * 
     * @param phoneNumber
     *            The PhoneNumber object
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public PhoneNumber(PhoneNumber phoneNumber) {
        if (null != phoneNumber) {
            this.mId = phoneNumber.getId();
            this.mPhoneNumberString = phoneNumber.getPhoneNumberString();
        } else {
            this.mId = 0;
            this.mPhoneNumberString = "";
        }
    }
}
