/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.DataAccess.DataObjects;

import java.util.Date;

/**
 * The Comment object in Mobile Map system.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class Comment {

    private long mId;
    private String mCommentString;
    private boolean mIsBadReport;
    private Date mCreationDate;
    private User mCreationUser;
    private boolean mIsActivated;
    private long mPoiId;

    /**
     * Get the comment's ID.
     * 
     * @param
     * @return The comment's ID
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long getId() {
        return this.mId;
    }

    /**
     * Set the Poi's ID.
     * 
     * @param id
     *            The poi's ID
     * @return
     * @author Cuong.Le
     * @version 0.4.0
     * 
     */
    public void setPoiId(long id) {
        this.mPoiId = id;
    }
    /**
     * Get the Poi's ID.
     * 
     * @param
     * @return The poi's ID
     * @author Cuong.Le
     * @version 0.4.0
     * 
     */
    public long getPoiId() {
        return this.mPoiId;
    }

    /**
     * Set the comment's ID.
     * 
     * @param id
     *            The comment's ID
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setId(long id) {
        this.mId = id;
    }

    /**
     * Get the text which describes the content of comment.
     * 
     * @param
     * @return The text which describes the content of comment
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public String getCommentString() {
        return this.mCommentString;
    }

    /**
     * Set the text which describes the content of comment.
     * 
     * @param commentString
     *            The text which describes the content of comment
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setAddressString(String commentString) {
        if (null == commentString) {
            this.mCommentString = "";
        } else {
            this.mCommentString = commentString;
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
     * Get the user which creates this comment.
     * 
     * @param
     * @return The user which creates this comment
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public User getCreationUser() {
        return this.mCreationUser;
    }

    /**
     * Set the user which creates this comment.
     * 
     * @param user
     *            The user which creates this comment
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setCreationUser(User user) {
        this.mCreationUser = user;
    }

    /**
     * Check whether this comment was reported as a bad comment or not.
     * 
     * @param
     * @return true if the comment was reported as a bad one, false if not
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
     * Check whether the comment was activated or not.
     * 
     * @param
     * @return true if comment was activated, false if not
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
    public Comment() {
        this.mId = 0;
        this.mCommentString = "";
        this.mIsBadReport = false;
        this.mCreationDate = new Date(0L);
        this.mCreationUser = new User();
        this.mIsActivated = false;
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
    public Comment(Comment comment) {
        if (null != comment) {
            this.mId = comment.getId();
            this.mCommentString = comment.getCommentString();
            this.mIsBadReport = comment.isBadReport();
            this.mCreationDate = comment.getCreationDate();
            this.mCreationUser = comment.getCreationUser();
            this.mIsActivated = comment.isActivated();
        } else {
            this.mId = 0;
            this.mCommentString = "";
            this.mIsBadReport = false;
            this.mCreationDate = new Date(0L);
            this.mCreationUser = new User();
            this.mIsActivated = false;
        }
    }
}
