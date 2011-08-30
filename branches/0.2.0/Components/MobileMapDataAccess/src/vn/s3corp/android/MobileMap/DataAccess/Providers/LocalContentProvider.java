/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.DataAccess.Providers;

import java.util.ArrayList;
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

    private final Context mCtx;

    private SQLiteDatabase mDb;
    private DbOpenHelper mDbOpen;

    private UsersDbAccess mUserDb;
    private LoginSessionDbAccess mSessionDb;
    private CategoriesDbAccess mCategoryDb;
    private AddressesDbAccess mAddressDb;
    private PoisCategoriesDbAccess mPoiCategoryDb;
    private PoisDbAccess mPoiDb;

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
        this.mSessionDb = null;
        this.mCategoryDb = null;
        this.mAddressDb = null;
        this.mPoiCategoryDb = null;
        this.mPoiDb = null;
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

        return this;
    }

    /**
     * Close the Mobile Map data provider.
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
        this.mSessionDb = null;
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
     *             The processing breaks some business rules
     * @throws DatabaseException
     *             There are errors in database
     * @throws SystemException
     *             The DataAccess component has unexpected errors
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean registerUser(User user) throws InvalidDataException, DataAccessException,
            DatabaseException, SystemException {

        // Check internal variable.
        if (null == this.mDb) {
            throw new SystemException("NotInitialized", "The content provider was not opened.");
        }

        // Check input data.
        if (null == user) {
            throw new InvalidDataException("user", "Input data is null.");
        }

        if ("".equals(user.getLoginname())) {
            throw new InvalidDataException("user", "login name is empty.");
        }

        if ("".equals(user.getPassword())) {
            throw new InvalidDataException("user", "password is empty.");
        }

        // Update user data before add to the database.
        user.setUserType(User.USER_TYPE_NORMAL);
        user.setCreationDate(new Date());
        user.setActivated(true);

        // Initialize the User database if needed.
        if (null == this.mUserDb) {
            this.mUserDb = new UsersDbAccess(this.mDb);
        }

        if (null == this.mAddressDb) {
            this.mAddressDb = new AddressesDbAccess(this.mDb);
        }

        Cursor cursor = null;

        try {
            // Check existing user.
            cursor = this.mUserDb.fetchUser(user.getLoginname());

            if (0 < cursor.getCount()) {
                throw new DataAccessException("UserExist", "The input login name is exist.");
            }

            cursor.close();
            cursor = null;

            // Add address.
            if (!"".equals(user.getAddress().getAddressString())) {
                final long addressId = this.mAddressDb.createAddress(user.getAddress());
                if (0 >= addressId) {
                    throw new DatabaseException("", "Internal SQL error.");
                }
                user.getAddress().setId(addressId);
            }

            final long userId = this.mUserDb.createUser(user);
            if (0 >= userId) {
                throw new DatabaseException("", "Internal SQL error.");
            }
            user.setId(userId);

        } catch (DataAccessException e) {
            if (null != cursor) {
                cursor.close();
            }
            throw e;
        } catch (DatabaseException e) {
            throw e;
        } catch (SQLiteException e) {
            if (null != cursor) {
                cursor.close();
            }
            throw new DatabaseException("", e.getMessage(), e);
        } catch (Exception e) {
            if (null != cursor) {
                cursor.close();
            }
            throw new SystemException("UnexpectedError", e.getMessage(), e);
        }

        return true;
    }

    /**
     * Process the user's login request with Mobile Map system. User must
     * provide a login name and a password.
     * 
     * @param session
     *            The session data which contains login information
     * @return true if user login successfully, false if not
     * @throws InvalidDataException
     *             The input data has some invalid value such as login name is
     *             empty
     * @throws DataAccessException
     *             The processing breaks some business rules
     * @throws DatabaseException
     *             There are errors in database
     * @throws SystemException
     *             The DataAccess component has unexpected errors
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean login(LoginSession session) throws InvalidDataException, DataAccessException,
            DatabaseException, SystemException {

        // Check internal variable.
        if (null == this.mDb) {
            throw new SystemException("NotInitialized", "The content provider was not opened.");
        }

        // Check input data.
        if (null == session) {
            throw new InvalidDataException("session", "Input data is null.");
        }

        if (null == session.getUser()) {
            throw new InvalidDataException("session", "Input user data is null.");
        }

        if ("".equals(session.getUser().getLoginname())) {
            throw new InvalidDataException("session", "login name is empty.");
        }

        if ("".equals(session.getUser().getPassword())) {
            throw new InvalidDataException("session", "password is empty.");
        }

        // Initialize the User database if needed.
        if (null == this.mUserDb) {
            this.mUserDb = new UsersDbAccess(this.mDb);
        }

        Cursor cursor = null;
        try {
            // Check valid user and password.
            // Check existing user.
            cursor = this.mUserDb.fetchUser(session.getUser().getLoginname());
            if (0 == cursor.getCount()) {
                throw new DataAccessException("UserNotExist", "The login name is not exist.");
            }

            if (1 != cursor.getCount()) {
                throw new DatabaseException("", "The database has wrong data.");
            }

            // Check password.
            String password = cursor.getString(cursor
                    .getColumnIndex(UsersDbAccess.TABLE_KEY_PASSWORD));
            if (!session.getUser().getPassword().equals(password)) {
                throw new DataAccessException("WrongPassword", "The input password is wrong.");
            }

            // Update session data before add to the database.
            session.getUser().setId(
                    cursor.getLong(cursor.getColumnIndex(UsersDbAccess.TABLE_KEY_USER_ID)));
            session.setLoginDate(new Date());
            session.setLogoutDate(new Date());
            session.setClosed(false);

            cursor.close();
            cursor = null;

            // Initialize the Session database if needed.
            if (null == this.mSessionDb) {
                this.mSessionDb = new LoginSessionDbAccess(this.mDb);
            }

            // Update old sessions.
            this.mSessionDb.updateAllUserOpenSessions(session);

            // Create new session.
            final long sessionId = this.mSessionDb.createSession(session);
            if (0 >= sessionId) {
                throw new DatabaseException("", "Internal SQL error.");
            }
            session.setId(sessionId);

        } catch (DataAccessException e) {
            if (null != cursor) {
                cursor.close();
            }
            throw e;
        } catch (DatabaseException e) {
            if (null != cursor) {
                cursor.close();
            }
            throw e;
        } catch (SQLiteException e) {
            if (null != cursor) {
                cursor.close();
            }
            throw new DatabaseException("", e.getMessage(), e);
        } catch (Exception e) {
            if (null != cursor) {
                cursor.close();
            }
            throw new SystemException("UnexpectedError", e.getMessage(), e);
        }

        return true;
    }

    /**
     * Process the user's logout request with Mobile Map system.
     * 
     * @param session
     *            The session data which contains login information
     * @return true if user logout successfully, false if not
     * @throws InvalidDataException
     *             The input data has some invalid value such as session id is
     *             zero
     * @throws DataAccessException
     *             The processing breaks some business rules
     * @throws DatabaseException
     *             There are errors in database
     * @throws SystemException
     *             The DataAccess component has unexpected errors
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean logout(LoginSession session) throws InvalidDataException, DataAccessException,
            DatabaseException, SystemException {

        // Check internal variable.
        if (null == this.mDb) {
            throw new SystemException("NotInitialized", "The content provider was not opened.");
        }

        // Check input data.
        if (null == session) {
            throw new InvalidDataException("session", "Input data is null.");
        }

        if (0 == session.getId()) {
            throw new InvalidDataException("session", "Session ID is zero.");
        }

        // Update session data before add to the database.
        session.setLogoutDate(new Date());
        session.setClosed(true);

        // Initialize the Session database if needed.
        if (null == this.mSessionDb) {
            this.mSessionDb = new LoginSessionDbAccess(this.mDb);
        }

        boolean result = false;
        try {
            // Update session.
            result = this.mSessionDb.updateSession(session);

        } catch (SQLiteException e) {
            throw new DatabaseException("", e.getMessage(), e);
        } catch (Exception e) {
            throw new SystemException("UnexpectedError", e.getMessage(), e);
        }

        return result;
    }

    /**
     * Get the list of all categories.
     * 
     * @param
     * @return the list of all categories. An empty list if there is no data.
     * @throws DatabaseException
     *             There are errors in database
     * @throws SystemException
     *             The DataAccess component has unexpected errors
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public ArrayList<Category> getAllPoiCategories() throws DatabaseException, SystemException {

        // Check internal variable.
        if (null == this.mDb) {
            throw new SystemException("NotInitialized", "The content provider was not opened.");
        }

        // Initialize the POI Category database if needed.
        if (null == this.mCategoryDb) {
            this.mCategoryDb = new CategoriesDbAccess(this.mDb);
        }

        Cursor cursor = null;
        ArrayList<Category> result = new ArrayList<Category>();

        try {
            // Get all categories.
            cursor = this.mCategoryDb.fetchAllCategories();

            if ((null == cursor) || (0 == cursor.getCount())) {
                return result;
            }

            while (!cursor.isAfterLast()) {
                Category category = new Category();

                category.setId(cursor.getLong(cursor
                        .getColumnIndex(CategoriesDbAccess.TABLE_KEY_CATEGORY_ID)));
                category.setName(cursor.getString(cursor
                        .getColumnIndex(CategoriesDbAccess.TABLE_KEY_CATEGORY_NAME_EN)));

                result.add(category);

                cursor.moveToNext();
            }

            cursor.close();
            cursor = null;

        } catch (SQLiteException e) {
            if (null != cursor) {
                cursor.close();
            }
            throw new DatabaseException("", e.getMessage(), e);
        } catch (Exception e) {
            if (null != cursor) {
                cursor.close();
            }
            throw new SystemException("UnexpectedError", e.getMessage(), e);
        }

        return result;
    }

    /**
     * Add a POI to Mobile Map system. The POI must has a unique location.
     * 
     * @param poi
     *            The data of POI which will be added
     * @return true if POI was added successfully, false if not
     * @throws InvalidDataException
     *             The input data has some invalid value such as name is empty
     * @throws DataAccessException
     *             The processing breaks some business rules
     * @throws DatabaseException
     *             There are errors in database
     * @throws SystemException
     *             The DataAccess component has unexpected errors
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean addPoi(Poi poi) throws InvalidDataException, DataAccessException,
            DatabaseException, SystemException {

        // Check internal variable.
        if (null == this.mDb) {
            throw new SystemException("NotInitialized", "The content provider was not opened.");
        }

        // Check input data.
        if (null == poi) {
            throw new InvalidDataException("poi", "Input data is null.");
        }

        if ("".equals(poi.getName())) {
            throw new InvalidDataException("poi", "POI's name is empty.");
        }

        // Update user data before add to the database.
        poi.setCreationDate(new Date());
        poi.setActivated(true);
        poi.setApproved(true);
        poi.setApproveDate(new Date());
        poi.setApproveUser(poi.getCreationUser());

        // Initialize the databases if needed.
        if (null == this.mPoiDb) {
            this.mPoiDb = new PoisDbAccess(this.mDb);
        }

        if (null == this.mAddressDb) {
            this.mAddressDb = new AddressesDbAccess(this.mDb);
        }

        if (null == this.mPoiCategoryDb) {
            this.mPoiCategoryDb = new PoisCategoriesDbAccess(this.mDb);
        }

        Cursor cursor = null;
        try {
            // Check existing poi.
            cursor = this.mPoiDb.fetchPoi(poi.getLongitude(), poi.getLatitude());

            if (0 < cursor.getCount()) {
                cursor.close();
                throw new DataAccessException("POIExist",
                        "The input POI has the same location with an existing POI.");
            }

            cursor.close();
            cursor = null;

            // Add addresses
            if (!"".equals(poi.getAddress().getAddressString())) {
                final long addressId = this.mAddressDb.createAddress(poi.getAddress());
                if (0 >= addressId) {
                    throw new DatabaseException("", "Internal SQL error.");
                }
                poi.getAddress().setId(addressId);
            }

            // Add POI
            final long poiId = this.mPoiDb.createPoi(poi);
            if (0 >= poiId) {
                throw new DatabaseException("", "Internal SQL error.");
            }
            poi.setId(poiId);

            // Add category for POI.
            Object[] categories = poi.getCategories().toArray();
            for (int i = 0; i < categories.length; i++) {
                this.mPoiCategoryDb.createPoiCategory(((Category) categories[i]).getId(),
                        poi.getId());
            }

        } catch (DataAccessException e) {
            if (null != cursor) {
                cursor.close();
            }
            throw e;
        } catch (DatabaseException e) {
            throw e;
        } catch (SQLiteException e) {
            if (null != cursor) {
                cursor.close();
            }
            throw new DatabaseException("", e.getMessage(), e);
        } catch (Exception e) {
            if (null != cursor) {
                cursor.close();
            }
            throw new SystemException("UnexpectedError", e.getMessage(), e);
        }

        return true;
    }

    /**
     * Remove a POI from Mobile Map system.
     * 
     * @param poiId
     *            The ID of POI which will be removed
     * @return true if POI was removed successfully, false if not
     * @throws InvalidDataException
     *             The input data has some invalid value such as name is empty
     * @throws DataAccessException
     *             The processing breaks some business rules
     * @throws DatabaseException
     *             There are errors in database
     * @throws SystemException
     *             The DataAccess component has unexpected errors
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean removePoi(long poiId) throws InvalidDataException, DataAccessException,
            DatabaseException, SystemException {

        // Check internal variable.
        if (null == this.mDb) {
            throw new SystemException("NotInitialized", "The content provider was not opened.");
        }

        // Check input data.
        if (0 == poiId) {
            throw new InvalidDataException("poiId", "POI's ID is zero.");
        }

        // Initialize the databases if needed.
        if (null == this.mPoiDb) {
            this.mPoiDb = new PoisDbAccess(this.mDb);
        }

        if (null == this.mAddressDb) {
            this.mAddressDb = new AddressesDbAccess(this.mDb);
        }

        if (null == this.mPoiCategoryDb) {
            this.mPoiCategoryDb = new PoisCategoriesDbAccess(this.mDb);
        }

        boolean result = false;
        Cursor cursor = null;

        try {
            // Check existing POI.
            cursor = this.mPoiDb.fetchPoi(poiId);
            if (0 == cursor.getCount()) {
                throw new DataAccessException("POINotExist", "The POI is not exist.");
            }

            if (1 != cursor.getCount()) {
                throw new DatabaseException("", "The database has wrong data.");
            }

            // Get Address ID
            long addressId = cursor.getLong(cursor
                    .getColumnIndex(PoisDbAccess.TABLE_KEY_ADDRESS_ID));
            // Delete Address
            if (0 < addressId) {
                this.mAddressDb.deleteAddress(addressId);
            }

            cursor.close();
            cursor = null;

            // Delete category of POI.
            this.mPoiCategoryDb.deletePoi(poiId);

            // Delete POI
            result = this.mPoiDb.deletePoi(poiId);

        } catch (DataAccessException e) {
            if (null != cursor) {
                cursor.close();
            }
            throw e;
        } catch (DatabaseException e) {
            if (null != cursor) {
                cursor.close();
            }
            throw e;
        } catch (SQLiteException e) {
            if (null != cursor) {
                cursor.close();
            }
            throw new DatabaseException("", e.getMessage(), e);
        } catch (Exception e) {
            if (null != cursor) {
                cursor.close();
            }
            throw new SystemException("UnexpectedError", e.getMessage(), e);
        }

        return result;
    }

    /**
     * Update a existing POI in Mobile Map system.
     * 
     * @param poi
     *            The data of POI which will be updated
     * @return true if POI was updated successfully, false if not
     * @throws InvalidDataException
     *             The input data has some invalid value such as name is empty
     * @throws DataAccessException
     *             The processing breaks some business rules
     * @throws DatabaseException
     *             There are errors in database
     * @throws SystemException
     *             The DataAccess component has unexpected errors
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean updatePoi(Poi poi) throws InvalidDataException, DataAccessException,
            DatabaseException, SystemException {

        // Check internal variable.
        if (null == this.mDb) {
            throw new SystemException("NotInitialized", "The content provider was not opened.");
        }

        // Check input data.
        if (null == poi) {
            throw new InvalidDataException("poi", "Input data is null.");
        }

        if (0 >= poi.getId()) {
            throw new InvalidDataException("poi", "POI's ID is wrong.");
        }

        // Initialize the databases if needed.
        if (null == this.mPoiDb) {
            this.mPoiDb = new PoisDbAccess(this.mDb);
        }

        if (null == this.mAddressDb) {
            this.mAddressDb = new AddressesDbAccess(this.mDb);
        }

        if (null == this.mPoiCategoryDb) {
            this.mPoiCategoryDb = new PoisCategoriesDbAccess(this.mDb);
        }

        boolean result = false;
        Cursor cursor = null;

        try {
            // Update address.
            Address address = poi.getAddress();
            if (!"".equals(address.getAddressString())) {
                if (0 == address.getId()) {
                    // Check old address.
                    // Check existing POI.
                    cursor = this.mPoiDb.fetchPoi(poi.getId());
                    if (0 == cursor.getCount()) {
                        throw new DataAccessException("POINotExist", "The POI is not exist.");
                    }

                    if (1 != cursor.getCount()) {
                        throw new DatabaseException("", "The database has wrong data.");
                    }

                    // Get Address ID
                    final long addressId = cursor.getLong(cursor
                            .getColumnIndex(PoisDbAccess.TABLE_KEY_ADDRESS_ID));
                    address.setId(addressId);

                    cursor.close();
                    cursor = null;
                }

                if (0 == address.getId()) {
                    // Create new address.
                    final long addressId = this.mAddressDb.createAddress(address);
                    if (0 >= addressId) {
                        throw new DatabaseException("", "Internal SQL error.");
                    }
                    address.setId(addressId);
                } else {
                    // Update old address.
                    this.mAddressDb.updateAddress(address);
                }
            }

            // Update category.
            Object[] categories = poi.getCategories().toArray();

            if (0 < categories.length) {
                // Remove old categories.
                this.mPoiCategoryDb.deletePoi(poi.getId());

                // Create categories.
                for (int i = 0; i < categories.length; i++) {
                    this.mPoiCategoryDb.createPoiCategory(((Category) categories[i]).getId(),
                            poi.getId());
                }
            }

            // Update other fields
            result = this.mPoiDb.updatePoi(poi);

        } catch (DataAccessException e) {
            if (null != cursor) {
                cursor.close();
            }
            throw e;
        } catch (DatabaseException e) {
            if (null != cursor) {
                cursor.close();
            }
            throw e;
        } catch (SQLiteException e) {
            if (null != cursor) {
                cursor.close();
            }
            throw new DatabaseException("", e.getMessage(), e);
        } catch (Exception e) {
            if (null != cursor) {
                cursor.close();
            }
            throw new SystemException("UnexpectedError", e.getMessage(), e);
        }

        return result;
    }

    /**
     * Get the list of all public POIs with simple information such as name,
     * longitude, latitude, rating, category, etc....
     * 
     * @param
     * @return the list of all public POIs. An empty list if there is no data.
     * @throws DatabaseException
     *             There are errors in database
     * @throws SystemException
     *             The DataAccess component has unexpected errors
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public ArrayList<Poi> getAllPublicPoisSimple() throws DatabaseException, SystemException {

        // Check internal variable.
        if (null == this.mDb) {
            throw new SystemException("NotInitialized", "The content provider was not opened.");
        }

        // Initialize the POI database if needed.
        if (null == this.mPoiDb) {
            this.mPoiDb = new PoisDbAccess(this.mDb);
        }

        if (null == this.mPoiCategoryDb) {
            this.mPoiCategoryDb = new PoisCategoriesDbAccess(this.mDb);
        }

        Cursor cursor = null;
        Cursor categoryCursor = null;
        ArrayList<Poi> result = new ArrayList<Poi>();

        try {
            // Get all public POIs.
            cursor = this.mPoiDb.fetchAllPublicPoisSimple();

            if ((null == cursor) || (0 == cursor.getCount())) {
                return result;
            }

            while (!cursor.isAfterLast()) {
                Poi poi = new Poi();

                poi.setId(cursor.getLong(cursor.getColumnIndex(PoisDbAccess.TABLE_KEY_POI_ID)));
                poi.setLongitude(cursor.getLong(cursor
                        .getColumnIndex(PoisDbAccess.TABLE_KEY_LONGITUDE)));
                poi.setLatitude(cursor.getLong(cursor
                        .getColumnIndex(PoisDbAccess.TABLE_KEY_LATITUDE)));
                poi.setPoiType(cursor.getLong(cursor
                        .getColumnIndex(PoisDbAccess.TABLE_KEY_POI_TYPE)));
                poi.setName(cursor.getString(cursor.getColumnIndex(PoisDbAccess.TABLE_KEY_POI_NAME)));
                poi.setRating(cursor.getLong(cursor
                        .getColumnIndex(PoisDbAccess.TABLE_KEY_RATING_VALUE)));
                poi.getCreationUser().setId(
                        cursor.getLong(cursor
                                .getColumnIndex(PoisDbAccess.TABLE_KEY_CREATION_USER_ID)));
                poi.setApproved(0 != cursor.getLong(cursor
                        .getColumnIndex(PoisDbAccess.TABLE_KEY_IS_APPROVED)));
                poi.setActivated(0 != cursor.getLong(cursor
                        .getColumnIndex(PoisDbAccess.TABLE_KEY_IS_ACTIVATED)));

                // Get category.
                categoryCursor = this.mPoiCategoryDb.fetchCategories(poi.getId());
                if (null != categoryCursor) {
                    if (0 < categoryCursor.getCount()) {
                        while (!categoryCursor.isAfterLast()) {
                            Category category = new Category();

                            category.setId(categoryCursor.getLong(categoryCursor
                                    .getColumnIndex(PoisCategoriesDbAccess.TABLE_KEY_CATEGORY_ID)));

                            poi.addCategory(category);

                            categoryCursor.moveToNext();
                        }
                    }

                    categoryCursor.close();
                    categoryCursor = null;
                }

                result.add(poi);

                cursor.moveToNext();
            }

            cursor.close();
            cursor = null;

        } catch (SQLiteException e) {
            if (null != cursor) {
                cursor.close();
            }
            if (null != categoryCursor) {
                categoryCursor.close();
            }
            throw new DatabaseException("", e.getMessage(), e);
        } catch (Exception e) {
            if (null != cursor) {
                cursor.close();
            }
            if (null != categoryCursor) {
                categoryCursor.close();
            }
            throw new SystemException("UnexpectedError", e.getMessage(), e);
        }

        return result;
    }

    /**
     * Get the list of all private POIs of a specific user with simple
     * information such as name, longitude, latitude, rating, category, etc....
     * 
     * @param userId
     *            The ID of user
     * @return the list of all private POIs of a specific user. An empty list if
     *         there is no data.
     * @throws InvalidDataException
     *             The input data has some invalid value such as user's ID is
     *             wrong
     * @throws DatabaseException
     *             There are errors in database
     * @throws SystemException
     *             The DataAccess component has unexpected errors
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public ArrayList<Poi> getAllPrivatePoisSimple(long userId) throws InvalidDataException,
            DatabaseException, SystemException {

        // Check internal variable.
        if (null == this.mDb) {
            throw new SystemException("NotInitialized", "The content provider was not opened.");
        }

        if (0 >= userId) {
            throw new InvalidDataException("userId", "User's ID is wrong.");
        }

        // Initialize the POI database if needed.
        if (null == this.mPoiDb) {
            this.mPoiDb = new PoisDbAccess(this.mDb);
        }

        if (null == this.mPoiCategoryDb) {
            this.mPoiCategoryDb = new PoisCategoriesDbAccess(this.mDb);
        }

        Cursor cursor = null;
        Cursor categoryCursor = null;
        ArrayList<Poi> result = new ArrayList<Poi>();

        try {
            // Get all public POIs.
            cursor = this.mPoiDb.fetchAllPrivatePoisSimple(userId);

            if ((null == cursor) || (0 == cursor.getCount())) {
                return result;
            }

            while (!cursor.isAfterLast()) {
                Poi poi = new Poi();

                poi.setId(cursor.getLong(cursor.getColumnIndex(PoisDbAccess.TABLE_KEY_POI_ID)));
                poi.setLongitude(cursor.getLong(cursor
                        .getColumnIndex(PoisDbAccess.TABLE_KEY_LONGITUDE)));
                poi.setLatitude(cursor.getLong(cursor
                        .getColumnIndex(PoisDbAccess.TABLE_KEY_LATITUDE)));
                poi.setPoiType(cursor.getLong(cursor
                        .getColumnIndex(PoisDbAccess.TABLE_KEY_POI_TYPE)));
                poi.setName(cursor.getString(cursor.getColumnIndex(PoisDbAccess.TABLE_KEY_POI_NAME)));
                poi.setRating(cursor.getLong(cursor
                        .getColumnIndex(PoisDbAccess.TABLE_KEY_RATING_VALUE)));
                poi.getCreationUser().setId(
                        cursor.getLong(cursor
                                .getColumnIndex(PoisDbAccess.TABLE_KEY_CREATION_USER_ID)));
                poi.setApproved(0 != cursor.getLong(cursor
                        .getColumnIndex(PoisDbAccess.TABLE_KEY_IS_APPROVED)));
                poi.setActivated(0 != cursor.getLong(cursor
                        .getColumnIndex(PoisDbAccess.TABLE_KEY_IS_ACTIVATED)));

                // Get category.
                categoryCursor = this.mPoiCategoryDb.fetchCategories(poi.getId());
                if (null != categoryCursor) {
                    if (0 < categoryCursor.getCount()) {
                        while (!categoryCursor.isAfterLast()) {
                            Category category = new Category();

                            category.setId(categoryCursor.getLong(categoryCursor
                                    .getColumnIndex(PoisCategoriesDbAccess.TABLE_KEY_CATEGORY_ID)));

                            poi.addCategory(category);

                            categoryCursor.moveToNext();
                        }
                    }

                    categoryCursor.close();
                    categoryCursor = null;
                }

                result.add(poi);

                cursor.moveToNext();
            }

            cursor.close();
            cursor = null;

        } catch (SQLiteException e) {
            if (null != cursor) {
                cursor.close();
            }
            if (null != categoryCursor) {
                categoryCursor.close();
            }
            throw new DatabaseException("", e.getMessage(), e);
        } catch (Exception e) {
            if (null != cursor) {
                cursor.close();
            }
            if (null != categoryCursor) {
                categoryCursor.close();
            }
            throw new SystemException("UnexpectedError", e.getMessage(), e);
        }

        return result;
    }

    /**
     * Get the detail information of POI.
     * 
     * @param poiId
     *            The ID of POI
     * @return the detail information of POI. Null if there is no data.
     * @throws InvalidDataException
     *             The input data has some invalid value such as POI's ID is
     *             wrong
     * @throws DataAccessException
     *             The processing breaks some business rules
     * @throws DatabaseException
     *             There are errors in database
     * @throws SystemException
     *             The DataAccess component has unexpected errors
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Poi getDetailPoiInformation(long poiId) throws InvalidDataException,
            DataAccessException, DatabaseException, SystemException {

        // Check internal variable.
        if (null == this.mDb) {
            throw new SystemException("NotInitialized", "The content provider was not opened.");
        }

        if (0 >= poiId) {
            throw new InvalidDataException("poiId", "POI's ID is wrong.");
        }

        // Initialize the POI database if needed.
        if (null == this.mPoiDb) {
            this.mPoiDb = new PoisDbAccess(this.mDb);
        }

        if (null == this.mPoiCategoryDb) {
            this.mPoiCategoryDb = new PoisCategoriesDbAccess(this.mDb);
        }

        if (null == this.mAddressDb) {
            this.mAddressDb = new AddressesDbAccess(this.mDb);
        }

        Cursor cursor = null;
        Cursor categoryCursor = null;
        Cursor addressCursor = null;
        Poi result = null;

        try {
            // Get all public POIs.
            cursor = this.mPoiDb.fetchPoi(poiId);

            if ((null == cursor) || (0 == cursor.getCount())) {
                throw new DataAccessException("POINotExist", "The POI is not exist.");
            }

            if (1 != cursor.getCount()) {
                throw new DatabaseException("", "The database has wrong data.");
            }

            result = new Poi();

            result.setId(cursor.getLong(cursor.getColumnIndex(PoisDbAccess.TABLE_KEY_POI_ID)));
            result.setLongitude(cursor.getLong(cursor
                    .getColumnIndex(PoisDbAccess.TABLE_KEY_LONGITUDE)));
            result.setLatitude(cursor.getLong(cursor
                    .getColumnIndex(PoisDbAccess.TABLE_KEY_LATITUDE)));
            result.setPoiType(cursor.getLong(cursor.getColumnIndex(PoisDbAccess.TABLE_KEY_POI_TYPE)));
            result.setName(cursor.getString(cursor.getColumnIndex(PoisDbAccess.TABLE_KEY_POI_NAME)));
            result.setDescriptions(cursor.getString(cursor
                    .getColumnIndex(PoisDbAccess.TABLE_KEY_DESCRIPTIONS)));
            result.setRating(cursor.getLong(cursor
                    .getColumnIndex(PoisDbAccess.TABLE_KEY_RATING_VALUE)));
            result.getAddress().setId(
                    cursor.getLong(cursor.getColumnIndex(PoisDbAccess.TABLE_KEY_ADDRESS_ID)));
            result.setCreationDate(new Date(cursor.getLong(cursor
                    .getColumnIndex(PoisDbAccess.TABLE_KEY_CREATION_DATETIME))));
            result.getCreationUser().setId(
                    cursor.getLong(cursor.getColumnIndex(PoisDbAccess.TABLE_KEY_CREATION_USER_ID)));
            result.setApproved(0 != cursor.getLong(cursor
                    .getColumnIndex(PoisDbAccess.TABLE_KEY_IS_APPROVED)));
            result.setApproveDate(new Date(cursor.getLong(cursor
                    .getColumnIndex(PoisDbAccess.TABLE_KEY_APPROVE_DATETIME))));
            result.getApproveUser().setId(
                    cursor.getLong(cursor.getColumnIndex(PoisDbAccess.TABLE_KEY_APPROVE_USER_ID)));
            result.setActivated(0 != cursor.getLong(cursor
                    .getColumnIndex(PoisDbAccess.TABLE_KEY_IS_ACTIVATED)));

            // Get category.
            categoryCursor = this.mPoiCategoryDb.fetchCategories(result.getId());
            if (null != categoryCursor) {
                if (0 < categoryCursor.getCount()) {
                    while (!categoryCursor.isAfterLast()) {
                        Category category = new Category();

                        category.setId(categoryCursor.getLong(categoryCursor
                                .getColumnIndex(PoisCategoriesDbAccess.TABLE_KEY_CATEGORY_ID)));

                        result.addCategory(category);

                        categoryCursor.moveToNext();
                    }
                }

                categoryCursor.close();
                categoryCursor = null;
            }

            // Get address.
            if (0 < result.getAddress().getId()) {
                addressCursor = this.mAddressDb.fetchAddress(result.getAddress().getId());

                if ((null == addressCursor) || (0 == addressCursor.getCount())) {
                    throw new DataAccessException("AddressNotExist", "The Address is not exist.");
                }

                if (1 != addressCursor.getCount()) {
                    throw new DatabaseException("", "The database has wrong data.");
                }

                result.getAddress().setAddressString(
                        addressCursor.getString(addressCursor
                                .getColumnIndex(AddressesDbAccess.TABLE_KEY_ADDRESS_STRING)));

                addressCursor.close();
                addressCursor = null;
            }

            cursor.close();
            cursor = null;

        } catch (DataAccessException e) {
            if (null != cursor) {
                cursor.close();
            }
            throw e;
        } catch (DatabaseException e) {
            if (null != cursor) {
                cursor.close();
            }
            throw e;
        } catch (SQLiteException e) {
            if (null != cursor) {
                cursor.close();
            }
            if (null != categoryCursor) {
                categoryCursor.close();
            }
            throw new DatabaseException("", e.getMessage(), e);
        } catch (Exception e) {
            if (null != cursor) {
                cursor.close();
            }
            if (null != categoryCursor) {
                categoryCursor.close();
            }
            throw new SystemException("UnexpectedError", e.getMessage(), e);
        }

        return result;
    }

}
