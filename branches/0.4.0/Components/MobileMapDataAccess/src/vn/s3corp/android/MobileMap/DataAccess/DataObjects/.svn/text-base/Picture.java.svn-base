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
 * The Picture object in Mobile Map system.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class Picture {

    private long mId;
    private String mLocation;
    private String mDescription;
    private boolean mIsBadReport;
    private boolean mIsActivated;

    /**
     * Get the picture's ID.
     * 
     * @param
     * @return The picture's ID
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long getId() {
        return this.mId;
    }

    /**
     * Set the picture's ID.
     * 
     * @param id
     *            The picture's ID
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setId(long id) {
        this.mId = id;
    }

    /**
     * Get the text which describes location of picture.
     * 
     * @param
     * @return The text which describes location of picture
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public String getLocation() {
        return this.mLocation;
    }

    /**
     * Set the text which describes location of picture.
     * 
     * @param location
     *            The text which describes location of picture
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setLocation(String location) {
        if (null == location) {
            this.mLocation = "";
        } else {
            this.mLocation = location;
        }
    }

    /**
     * Get the description of picture.
     * 
     * @param
     * @return The description of picture
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public String getDescription() {
        return this.mDescription;
    }

    /**
     * Set the description of picture.
     * 
     * @param description
     *            The description of picture
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setDescription(String description) {
        if (null == description) {
            this.mDescription = "";
        } else {
            this.mDescription = description;
        }
    }

    /**
     * Check whether this picture was reported as a bad one or not.
     * 
     * @param
     * @return true if the picture was reported as a bad one, false if not
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean isBadReport() {
        return this.mIsBadReport;
    }

    /**
     * Set the BadReport value.
     * 
     * @param isBadReport
     *            The BadReport value
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setBadReport(boolean isBadReport) {
        this.mIsBadReport = isBadReport;
    }

    /**
     * Check whether the picture was activated or not.
     * 
     * @param
     * @return true if picture was activated, false if not
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
     * Default constructor.
     * 
     * @param
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Picture() {
        this.mId = 0;
        this.mLocation = "";
        this.mDescription = "";
        this.mIsBadReport = false;
        this.mIsActivated = false;
    }

    /**
     * Copy constructor.
     * 
     * @param picture
     *            The Picture object
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Picture(Picture picture) {
        if (null != picture) {
            this.mId = picture.getId();
            this.mLocation = picture.getLocation();
            this.mDescription = picture.getDescription();
            this.mIsBadReport = picture.isBadReport();
            this.mIsActivated = picture.isActivated();
        } else {
            this.mId = 0;
            this.mLocation = "";
            this.mDescription = "";
            this.mIsBadReport = false;
            this.mIsActivated = false;
        }
    }
}
