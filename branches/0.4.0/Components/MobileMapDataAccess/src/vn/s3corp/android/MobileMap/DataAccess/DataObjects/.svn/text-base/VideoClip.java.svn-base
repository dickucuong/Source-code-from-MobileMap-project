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
 * The VideoClip object in Mobile Map system.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class VideoClip {

    private long mId;
    private String mLocation;
    private String mDescription;
    private boolean mIsBadReport;
    private boolean mIsActivated;

    /**
     * Get the video's ID.
     * 
     * @param
     * @return The video's ID
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long getId() {
        return this.mId;
    }

    /**
     * Set the video's ID.
     * 
     * @param id
     *            The video's ID
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setId(long id) {
        this.mId = id;
    }

    /**
     * Get the text which describes location of video clip.
     * 
     * @param
     * @return The text which describes location of video clip
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public String getLocation() {
        return this.mLocation;
    }

    /**
     * Set the text which describes location of video clip.
     * 
     * @param location
     *            The text which describes location of video clip
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
     * Get the description of video clip.
     * 
     * @param
     * @return The description of video clip
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public String getDescription() {
        return this.mDescription;
    }

    /**
     * Set the description of video clip.
     * 
     * @param description
     *            The description of video clip
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
     * Check whether this video clip was reported as a bad one or not.
     * 
     * @param
     * @return true if the video clip was reported as a bad one, false if not
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
     * Check whether the video clip was activated or not.
     * 
     * @param
     * @return true if video clip was activated, false if not
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
    public VideoClip() {
        this.mId = 0;
        this.mLocation = "";
        this.mDescription = "";
        this.mIsBadReport = false;
        this.mIsActivated = false;
    }

    /**
     * Copy constructor.
     * 
     * @param videoClip
     *            The VideoClip object
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public VideoClip(VideoClip videoClip) {
        if (null != videoClip) {
            this.mId = videoClip.getId();
            this.mLocation = videoClip.getLocation();
            this.mDescription = videoClip.getDescription();
            this.mIsBadReport = videoClip.isBadReport();
            this.mIsActivated = videoClip.isActivated();
        } else {
            this.mId = 0;
            this.mLocation = "";
            this.mDescription = "";
            this.mIsBadReport = false;
            this.mIsActivated = false;
        }
    }
}
