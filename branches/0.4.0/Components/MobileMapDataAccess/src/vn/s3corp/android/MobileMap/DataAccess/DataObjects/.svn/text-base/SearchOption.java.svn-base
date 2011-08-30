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
 * The SearchOption object which is used in Search methods.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class SearchOption {

    private String mSearchString;
    private Category mCategory;

    private long mCenterLongitude;
    private long mCenterLatitude;
    private long mDistance;

    /**
     * Get the search string.
     * 
     * @param
     * @return The search string
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public String getSearchString() {
        return this.mSearchString;
    }

    /**
     * Set the search string.
     * 
     * @param searchString
     *            The search string
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setSearchString(String searchString) {
        if (null == searchString) {
            this.mSearchString = "";
        } else {
            this.mSearchString = searchString;
        }
    }

    /**
     * Get category.
     * 
     * @param
     * @return Category
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Category getCategory() {
        return this.mCategory;
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
    public void setCategory(Category category) {
        this.mCategory = category;
    }

    /**
     * Get the center longitude value.
     * 
     * @param
     * @return The center longitude value
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long getCenterLongitude() {
        return this.mCenterLongitude;
    }

    /**
     * Set the center longitude value.
     * 
     * @param longitude
     *            The longitude value
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setCenterLongitude(long longitude) {
        this.mCenterLongitude = longitude;
    }

    /**
     * Get the center latitude value.
     * 
     * @param
     * @return The center latitude value
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long getCenterLatitude() {
        return this.mCenterLatitude;
    }

    /**
     * Set the center latitude value.
     * 
     * @param latitude
     *            The latitude value
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setCenterLatitude(long latitude) {
        this.mCenterLatitude = latitude;
    }

    /**
     * Get the distance from the center point.
     * 
     * @param
     * @return The distance from the center point (in meters)
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long getDistance() {
        return this.mDistance;
    }

    /**
     * Set the distance from the center point.
     * 
     * @param distance
     *            The distance (in meters)
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setDistance(long distance) {
        this.mDistance = distance;
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
    public SearchOption() {
        this.mSearchString = "";
        this.mCategory = new Category();

        this.mCenterLongitude = 0;
        this.mCenterLatitude = 0;
        this.mDistance = 0;
    }

    /**
     * Copy constructor.
     * 
     * @param searchOption
     *            The SearchOption object
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public SearchOption(SearchOption searchOption) {
        if (null != searchOption) {
            this.mSearchString = searchOption.getSearchString();
            this.mCategory = searchOption.getCategory();
            this.mCenterLongitude = searchOption.getCenterLongitude();
            this.mCenterLatitude = searchOption.getCenterLatitude();
            this.mDistance = searchOption.getDistance();
        } else {
            this.mSearchString = "";
            this.mCategory = new Category();

            this.mCenterLongitude = 0;
            this.mCenterLatitude = 0;
            this.mDistance = 0;
        }
    }
}
