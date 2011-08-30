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
 * The Rating object in Mobile Map system.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class Rating {

    private long mId;
    private long mRatingValue;
    private User mUser;

    /**
     * Get the rating's ID.
     * 
     * @param
     * @return The rating's ID
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long getId() {
        return this.mId;
    }

    /**
     * Set the rating's ID.
     * 
     * @param id
     *            The rating's ID
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setId(long id) {
        this.mId = id;
    }

    /**
     * Get the rating value.
     * 
     * @param
     * @return The rating value
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long getRatingValue() {
        return this.mRatingValue;
    }

    /**
     * Set the rating value.
     * 
     * @param ratingValue
     *            The rating value
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setRatingValue(long ratingValue) {
        this.mRatingValue = ratingValue;
    }

    /**
     * Get the user which creates this rating.
     * 
     * @param
     * @return The user which creates this rating
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public User getUser() {
        return this.mUser;
    }

    /**
     * Set the user which creates this rating.
     * 
     * @param user
     *            The user which creates this rating
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setUser(User user) {
        this.mUser = user;
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
    public Rating() {
        this.mId = 0;
        this.mRatingValue = 0;
        this.mUser = new User();
    }

    /**
     * Copy constructor.
     * 
     * @param rating
     *            The Rating object
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Rating(Rating rating) {
        if (null != rating) {
            this.mId = rating.getId();
            this.mRatingValue = rating.getRatingValue();
            this.mUser = rating.getUser();
        } else {
            this.mId = 0;
            this.mRatingValue = 0;
            this.mUser = new User();
        }
    }
}
