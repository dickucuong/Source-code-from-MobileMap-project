/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.DataAccess;

import android.content.Context;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.*;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.*;
import vn.s3corp.android.MobileMap.DataAccess.Providers.*;

/**
 * The content manager for Mobile Map data.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class ContentManager {

    private final Context    mCtx;

    private IContentProvider mProvider;

    /**
     * Constructor.
     * 
     * @param context
     *            The Context which is provided to work with
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public ContentManager(Context context) {
        this.mCtx = context;
        this.mProvider = new LocalContentProvider(this.mCtx);
    }

    /**
     * Open the Mobile Map data content manager.
     * 
     * @param
     * @return true If the manager was opened successfully, false otherwise
     * @throws DataAccessException
     *             The DataAccess component has unexpected errors
     * @throws DatabaseException
     *             There are errors in database
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean open() throws DataAccessException, DatabaseException {
        return (null != this.mProvider.open());
    }

    /**
     * Close the Mobile Map data content manager.
     * 
     * @param
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void close() {
        this.mProvider.close();
    }

    /**
     * Register a user with Mobile Map system. User must have a login name which
     * is different from other users's one.
     * 
     * @param user
     *            The data of user which needs register
     * @return true if user was registered successfully, false if not
     * @throws InvalidDataException
     *             The input data has some invalid value such as login name is
     *             empty
     * @throws DataAccessException
     *             The DataAccess component has unexpected errors
     * @throws DatabaseException
     *             There are errors in database
     * @author nam.tran
     * @version 0.1.0
     */
    public boolean registerUser(User user) throws InvalidDataException, DataAccessException,
            DatabaseException {
        return this.mProvider.registerUser(user);
    }
}
