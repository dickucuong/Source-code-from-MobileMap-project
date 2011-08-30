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
 * The POI object in Mobile Map system.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class Poi {

    /**
     * The unknown POI type.
     */
    public static long POI_TYPE_UNKNOWN = 0;

    /**
     * The public POI.
     */
    public static long POI_TYPE_PUBLIC = 1;

    /**
     * The private POI.
     */
    public static long POI_TYPE_PRIVATE = 2;

    private long mId;
    private long mLongitude;
    private long mLatitude;
    private long mType;
    private String mName;
    private String mDescriptions;
    private long mRating;
    private Address mAddress;
    private Date mCreationDate;
    private User mCreationUser;
    private boolean mIsApproved;
    private Date mApproveDate;
    private User mApproveUser;
    private boolean mIsActivated;

    private ArrayList<Category> mCategories;

    /**
     * Get the POI's ID.
     * 
     * @param
     * @return The POI's ID
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long getId() {
        return this.mId;
    }

    /**
     * Set the POI's ID.
     * 
     * @param id
     *            The POI's ID
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setId(long id) {
        this.mId = id;
    }

    /**
     * Get longitude value.
     * 
     * @param
     * @return Longitude value
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long getLongitude() {
        return this.mLongitude;
    }

    /**
     * Set longitude value.
     * 
     * @param logitude
     *            Longitude value
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setLongitude(long longitude) {
        this.mLongitude = longitude;
    }

    /**
     * Get latitude value.
     * 
     * @param
     * @return Latitude value
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long getLatitude() {
        return this.mLatitude;
    }

    /**
     * Set latitude value.
     * 
     * @param latitude
     *            Latitude value
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setLatitude(long latitude) {
        this.mLatitude = latitude;
    }

    /**
     * Get POI's type.
     * 
     * @param
     * @return The POI's type
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long getPoiType() {
        return this.mType;
    }

    /**
     * Set POI's type.
     * 
     * @param poiType
     *            The POI's type
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setPoiType(long poiType) {
        this.mType = poiType;
    }

    /**
     * Get POI's name.
     * 
     * @param
     * @return The POI's name
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public String getName() {
        return this.mName;
    }

    /**
     * Set POI's name.
     * 
     * @param name
     *            The POI's name
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setName(String name) {
        if (null == name) {
            this.mName = "";
        } else {
            this.mName = name;
        }
    }

    /**
     * Get POI's descriptions.
     * 
     * @param
     * @return The POI's descriptions
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public String getDescriptions() {
        return this.mDescriptions;
    }

    /**
     * Set POI's descriptions.
     * 
     * @param descriptions
     *            The POI's descriptions
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setDescriptions(String descriptions) {
        if (null == descriptions) {
            this.mDescriptions = "";
        } else {
            this.mDescriptions = descriptions;
        }
    }

    /**
     * Get rating value.
     * 
     * @param
     * @return Rating value
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long getRating() {
        return this.mRating;
    }

    /**
     * Set rating value.
     * 
     * @param rating
     *            Rating value
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setRating(long rating) {
        this.mRating = rating;
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
     * Get the user which creates POI.
     * 
     * @param
     * @return The user which creates POI
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public User getCreationUser() {
        return this.mCreationUser;
    }

    /**
     * Set the user which creates POI
     * 
     * @param user
     *            The user which creates POI
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setCreationUser(User user) {
        this.mCreationUser = user;
    }

    /**
     * Check whether this POI was approved or not.
     * 
     * @param
     * @return true if this POI was approved, false if not
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean isApproved() {
        return this.mIsApproved;
    }

    /**
     * Set the Approved value.
     * 
     * @param isApproved
     *            The Approved value
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setApproved(boolean isApproved) {
        this.mIsApproved = isApproved;
    }

    /**
     * Get the approve date.
     * 
     * @param
     * @return The approve date
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Date getApproveDate() {
        return this.mApproveDate;
    }

    /**
     * Set the approve date.
     * 
     * @param approveDate
     *            The approve date
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setApproveDate(Date approveDate) {
        this.mApproveDate = approveDate;
    }

    /**
     * Get the user which approves POI.
     * 
     * @param
     * @return The user which approves POI
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public User getApproveUser() {
        return this.mApproveUser;
    }

    /**
     * Set the user which approves POI
     * 
     * @param user
     *            The user which approves POI
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setApproveUser(User user) {
        this.mApproveUser = user;
    }

    /**
     * Check whether the POI was activated or not.
     * 
     * @param
     * @return true if POI was activated, false if not
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
     * Get the list of categories.
     * 
     * @param
     * @return The list of categories
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public ArrayList<Category> getCategories() {
        return this.mCategories;
    }

    /**
     * Set the list of categories.
     * 
     * @param categories
     *            The list of categories.
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setCategories(ArrayList<Category> categories) {
        this.mCategories = categories;
    }

    /**
     * Add a category for POI.
     * 
     * @param category
     *            The category which is added.
     * @return true if the category was added successfully, false otherwise.
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean addCategory(Category category) {
        return this.mCategories.add(category);
    }

    /**
     * Add a category for POI.
     * 
     * @param
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void removeAllCategories() {
        this.mCategories.clear();
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
    public Poi() {
        this.mId = 0;
        this.mLongitude = 0;
        this.mLatitude = 0;
        this.mType = Poi.POI_TYPE_UNKNOWN;
        this.mName = "";
        this.mDescriptions = "";
        this.mRating = 0;
        this.mAddress = new Address();
        this.mCreationDate = new Date(0L);
        this.mCreationUser = new User();
        this.mIsApproved = false;
        this.mApproveDate = new Date(0L);
        this.mApproveUser = new User();
        this.mIsActivated = false;
        this.mCategories = new ArrayList<Category>();
    }

    /**
     * Copy constructor.
     * 
     * @param poi
     *            The POI object
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Poi(Poi poi) {
        this.mId = poi.getId();
        this.mLongitude = poi.getLongitude();
        this.mLatitude = poi.getLatitude();
        this.mType = poi.getPoiType();
        this.mName = poi.getName();
        this.mDescriptions = poi.getDescriptions();
        this.mRating = poi.getRating();
        this.mAddress = poi.getAddress();
        this.mCreationDate = poi.getCreationDate();
        this.mCreationUser = poi.getCreationUser();
        this.mIsApproved = poi.isApproved();
        this.mApproveDate = poi.getApproveDate();
        this.mApproveUser = poi.getApproveUser();
        this.mIsActivated = poi.isActivated();
        this.mCategories = poi.getCategories();
    }
}
