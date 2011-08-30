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
 * The User Type enumeration.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public enum UserType {
    /**
     * The unknown user.
     */
    UNKNOWN_USER(0),

    /**
     * The normal user.
     */
    NORMAL_USER(1),

    /**
     * The VIP user.
     */
    VIP_USER(2),

    /**
     * System user.
     */
    SYSTEM_USER(3),

    /**
     * Administrator.
     */
    ADMINISTRATOR(4);

    private final int value;

    private UserType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}