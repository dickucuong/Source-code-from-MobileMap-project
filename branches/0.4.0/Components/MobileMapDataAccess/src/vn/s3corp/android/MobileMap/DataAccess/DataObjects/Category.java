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
 * The Category object in Mobile Map system.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class Category {

    private long mId;
    private String mName;

    /**
     * Get the category's ID.
     * 
     * @param
     * @return The category's ID
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long getId() {
        return this.mId;
    }

    /**
     * Set the category's ID.
     * 
     * @param id
     *            The category's ID
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setId(long id) {
        this.mId = id;
    }

    /**
     * Get the text which describes name of category.
     * 
     * @param
     * @return The text which describes name of category
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public String getName() {
        return this.mName;
    }

    /**
     * Set the text which describes name of category.
     * 
     * @param name
     *            The text which describes name of category
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
     * Default constructor.
     * 
     * @param
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Category() {
        this.mId = 0;
        this.mName = "";
    }

    /**
     * Copy constructor.
     * 
     * @param category
     *            The Category object
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Category(Category category) {
        if (null != category) {
            this.mId = category.getId();
            this.mName = category.getName();
        } else {
            this.mId = 0;
            this.mName = "";
        }
    }
}
