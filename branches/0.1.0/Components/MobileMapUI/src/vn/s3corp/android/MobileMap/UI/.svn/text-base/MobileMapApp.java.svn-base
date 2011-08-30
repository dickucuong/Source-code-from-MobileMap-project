/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.UI;

import vn.s3corp.android.MobileMap.DataAccess.ContentManager;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DataAccessException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DatabaseException;

import android.app.Application;

/**
 * The main application of Mobile Map client.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class MobileMapApp extends Application {

    private ContentManager mManager;

    /**
     * Get the content manager object which is used to access Mobile Map data.
     * This method will initialize the manager if needed.
     * 
     * 
     * @param
     * @return The content manager object which is used to access Mobile Map
     *         data
     * @throws DataAccessException
     *             The DataAccess component has unexpected errors
     * @throws DatabaseException
     *             There are errors in database
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public ContentManager getContentManager() throws DataAccessException, DatabaseException {
        if (null == this.mManager) {
            ContentManager manager = new ContentManager(this);

            if (true == manager.open()) {
                this.mManager = manager;
            }
        }

        return this.mManager;
    }
}
