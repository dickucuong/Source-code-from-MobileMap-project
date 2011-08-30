/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.DataAccess.Providers;

import java.util.Date;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import vn.s3corp.android.MobileMap.DataAccess.DataObjects.*;
import vn.s3corp.android.MobileMap.DataAccess.DbAdapters.*;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.*;

/**
 * The local content provider for Mobile Map data.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class LocalContentProvider implements IContentProvider {

    private final Context  mCtx;

    private SQLiteDatabase mDb;
    private DbOpenHelper   mDbOpen;

    private UsersDbAccess  mUserDb;

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
    public LocalContentProvider(Context context) {
        this.mCtx = context;

        this.mDb = null;
        this.mDbOpen = null;

        this.mUserDb = null;
    }

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
    public IContentProvider open() throws DataAccessException, DatabaseException {

        if (null == this.mDbOpen) {
            this.mDbOpen = new DbOpenHelper(mCtx);
        }

        if (null == this.mDb) {
            try {
                this.mDb = this.mDbOpen.getWritableDatabase();
            } catch (SQLiteException e) {
                this.mDb = null;
                throw new DatabaseException("", e.getMessage(), e);
            }
        }

        if (null == this.mUserDb) {
            this.mUserDb = new UsersDbAccess(this.mDb);
        }

        return this;
    }

    /**
     * Close the Moblie Map data provider.
     * 
     * @param
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void close() {

        if (null != this.mDb) {
            this.mDb.close();
            this.mDb = null;
        }

        if (null != this.mDbOpen) {
            this.mDbOpen.close();
            this.mDbOpen = null;
        }

        this.mUserDb = null;
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

        // Check internal variable.
        if ((null == this.mDb) || (null == this.mUserDb)) {
            throw new DataAccessException("initialization", "The content provider was not opened.");
        }

        // Check input data.
        if (null == user) {
            throw new InvalidDataException("user", "Input data is null.");
        }

        if (user.getLoginname().equals("")) {
            throw new InvalidDataException("user", "login name is empty.");
        }

        if (user.getPassword().equals("")) {
            throw new InvalidDataException("user", "password is empty.");
        }

        // Update user data before add to the database.
        user.setUserType(UserType.NORMAL_USER);
        user.setCreationDate(new Date());
        user.setActivated(true);

        // Check existing user.
        Cursor userCursor = this.mUserDb.fetchUser(user.getLoginname());
        if (0 == userCursor.getCount()) {
            // TODO: add address.

            long userId = this.mUserDb.createUser(user);
            if (0 > userId) {
                throw new DatabaseException("", "Internal SQL error.");
            }
            user.setId(userId);

            return true;
        }

        return false;
    }

}
