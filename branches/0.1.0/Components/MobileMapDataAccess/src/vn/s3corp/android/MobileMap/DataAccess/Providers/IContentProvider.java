/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.DataAccess.Providers;

import vn.s3corp.android.MobileMap.DataAccess.DataObjects.*;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.*;

/**
 * The basic interface for Mobile Map content providers.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public interface IContentProvider {

    /**
     * Open the Mobile Map data provider.
     * 
     * @param
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws DataAccessException
     *             The DataAccess component has unexpected errors
     * @throws DatabaseException
     *             There are errors in database
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    IContentProvider open() throws DataAccessException, DatabaseException;

    /**
     * Close the Moblie Map data provider.
     * 
     * @param
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    void close();

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
    boolean registerUser(User user) throws InvalidDataException, DataAccessException,
            DatabaseException;
}
