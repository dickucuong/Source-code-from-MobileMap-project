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
 * The Address object in Mobile Map system.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class Address {

    private long mId;
    private String mAddressString;

    /**
     * Get the address's ID.
     * 
     * @param
     * @return The address's ID
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long getId() {
        return this.mId;
    }

    /**
     * Set the address's ID.
     * 
     * @param id
     *            The address's ID
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setId(long id) {
        this.mId = id;
    }

    /**
     * Get the text which describes address.
     * 
     * @param
     * @return The text which describes address
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public String getAddressString() {
        return this.mAddressString;
    }

    /**
     * Set the text which describes address.
     * 
     * @param addressString
     *            The text which describes address
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setAddressString(String addressString) {
        if (null == addressString) {
            this.mAddressString = "";
        } else {
            this.mAddressString = addressString;
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
    public Address() {
        this.mId = 0;
        this.mAddressString = "";
    }

    /**
     * Copy constructor.
     * 
     * @param address
     *            The Address object
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Address(Address address) {
        if (null != address) {
            this.mId = address.getId();
            this.mAddressString = address.getAddressString();
        }
    }
}
