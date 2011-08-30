/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.DataAccess.DataObjects;

import java.util.ArrayList;
import java.util.Date;

/**
 * The User object in Mobile Map system.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class User {
    /**
     * The unknown user.
     */
    public static long USER_TYPE_UNKNOWN = 0;

    /**
     * The normal user.
     */
    public static long USER_TYPE_NORMAL = 1;

    /**
     * The VIP user.
     */
    public static long USER_TYPE_VIP = 2;

    /**
     * System user.
     */
    public static long USER_TYPE_SYSTEM = 3;

    /**
     * Administrator.
     */
    public static long USER_TYPE_ADMINISTRATOR = 4;

    private long mId;
    private String mLoginname;
    private String mPassword;
    private long mUserType;
    private Address mAddress;
    private Date mCreationDate;
    private boolean mIsActivated;

    private ArrayList<Email> mEmails;
    private ArrayList<PhoneNumber> mPhoneNumbers;

    /**
     * Get the user's ID.
     * 
     * @param
     * @return The user's ID
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long getId() {
        return this.mId;
    }

    /**
     * Set the user's ID.
     * 
     * @param id
     *            The user's ID
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setId(long id) {
        this.mId = id;
    }

    /**
     * Get the login name.
     * 
     * @param
     * @return The login name
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public String getLoginname() {
        return this.mLoginname;
    }

    /**
     * Set the login name.
     * 
     * @param loginname
     *            The login name
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setLoginname(String loginname) {
        if (null == loginname) {
            this.mLoginname = "";
        } else {
            this.mLoginname = loginname;
        }
    }

    /**
     * Get the password.
     * 
     * @param
     * @return The password
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public String getPassword() {
        return this.mPassword;
    }

    /**
     * Set the password.
     * 
     * @param password
     *            The password
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setPassword(String password) {
        if (null == password) {
            this.mPassword = "";
        } else {
            this.mPassword = password;
        }
    }

    /**
     * Get user's type.
     * 
     * @param
     * @return The user's type
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long getUserType() {
        return this.mUserType;
    }

    /**
     * Set user's type.
     * 
     * @param userType
     *            The user's type
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setUserType(long userType) {
        this.mUserType = userType;
    }

    /**
     * Get address.
     * 
     * @param
     * @return The address
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Address getAddress() {
        return this.mAddress;
    }

    /**
     * Set address.
     * 
     * @param address
     *            The address
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setAddress(Address address) {
        if (null != address) {
            this.mAddress = address;
        }
    }

    /**
     * Get the creation date.
     * 
     * @param
     * @return The creation date
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Date getCreationDate() {
        return this.mCreationDate;
    }

    /**
     * Set the creation date.
     * 
     * @param creationDate
     *            The creation date
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setCreationDate(Date creationDate) {
        this.mCreationDate = creationDate;
    }

    /**
     * Check whether the User was activated or not.
     * 
     * @param
     * @return true if user was activated, false if not
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean isActivated() {
        return this.mIsActivated;
    }

    /**
     * Set the Activated value.
     * 
     * @param isActivated
     *            The Activated value
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setActivated(boolean isActivated) {
        this.mIsActivated = isActivated;
    }

    /**
     * Check whether the user is a power user or not.
     * 
     * @param
     * @return true if user is a power user, false if not
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean isPowerUser() {
        return ((User.USER_TYPE_SYSTEM == this.mUserType) || (User.USER_TYPE_ADMINISTRATOR == this.mUserType));
    }

    /**
     * Get the list of all emails.
     * 
     * @param
     * @return The list of all emails
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public ArrayList<Email> getEmails() {
        return this.mEmails;
    }

    /**
     * Set the list of emails.
     * 
     * @param emails
     *            The list of emails.
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setEmails(ArrayList<Email> emails) {
        this.mEmails = emails;
    }

    /**
     * Add a email of user.
     * 
     * @param email
     *            The email which is added.
     * @return true if the email was added successfully, false otherwise.
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean addEmail(Email email) {
        return this.mEmails.add(email);
    }

    /**
     * Remove all emails of user.
     * 
     * @param
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void removeAllEmails() {
        this.mEmails.clear();
    }

    /**
     * Get the list of all phone numbers.
     * 
     * @param
     * @return The list of all phone numbers
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public ArrayList<PhoneNumber> getPhoneNumbers() {
        return this.mPhoneNumbers;
    }

    /**
     * Set the list of phone numbers.
     * 
     * @param phoneNumbers
     *            The list of phone numbers.
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setPhoneNumbers(ArrayList<PhoneNumber> phoneNumbers) {
        this.mPhoneNumbers = phoneNumbers;
    }

    /**
     * Add a phone number of user.
     * 
     * @param phoneNumber
     *            The phone number which is added.
     * @return true if the phone number was added successfully, false otherwise.
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean addPhoneNumber(PhoneNumber phoneNumber) {
        return this.mPhoneNumbers.add(phoneNumber);
    }

    /**
     * Remove all phone numbers of user.
     * 
     * @param
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void removeAllPhoneNumbers() {
        this.mPhoneNumbers.clear();
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
    public User() {
        this.mId = 0;
        this.mLoginname = "";
        this.mPassword = "";
        this.mUserType = User.USER_TYPE_UNKNOWN;
        this.mCreationDate = new Date(0L);
        this.mIsActivated = false;
        this.mAddress = new Address();

        this.mEmails = new ArrayList<Email>();
        this.mPhoneNumbers = new ArrayList<PhoneNumber>();
    }

    /**
     * Copy constructor.
     * 
     * @param user
     *            The User object
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public User(User user) {
        if (null != user) {
            this.mId = user.getId();
            this.mLoginname = user.getLoginname();
            this.mPassword = user.getPassword();
            this.mUserType = user.getUserType();
            this.mCreationDate = user.getCreationDate();
            this.mIsActivated = user.isActivated();
            this.mAddress = user.getAddress();

            this.mEmails = user.getEmails();
            this.mPhoneNumbers = user.getPhoneNumbers();
        } else {
            this.mId = 0;
            this.mLoginname = "";
            this.mPassword = "";
            this.mUserType = User.USER_TYPE_UNKNOWN;
            this.mCreationDate = new Date(0L);
            this.mIsActivated = false;
            this.mAddress = new Address();

            this.mEmails = new ArrayList<Email>();
            this.mPhoneNumbers = new ArrayList<PhoneNumber>();
        }
    }
}
