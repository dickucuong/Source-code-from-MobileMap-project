/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.DataAccess.DbAdapters;

import vn.s3corp.android.MobileMap.DataAccess.DataObjects.User;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.UserType;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * The local database adapter for 'users' table.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class UsersDbAccess {

    public static final String TABLE_NAME                  = "users";

    public static final String TABLE_KEY_USER_ID           = "_id";
    public static final String TABLE_KEY_LOGINNAME         = "loginname";
    public static final String TABLE_KEY_PASSWORD          = "password";
    public static final String TABLE_KEY_USER_TYPE         = "user_type";
    public static final String TABLE_KEY_CREATION_DATETIME = "creation_datetime";
    public static final String TABLE_KEY_IS_ACTIVATED      = "is_activated";
    public static final String TABLE_KEY_ADDRESS_ID        = "address_id";

    private SQLiteDatabase     mDb;

    /**
     * Constructor - This constructor takes the context to allow the database to
     * be opened/created
     * 
     * @param db
     *            The opened SQLite database.
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public UsersDbAccess(SQLiteDatabase db) {
        this.mDb = db;
    }

    /**
     * Create a new user value in Mobile Map database. If the user is
     * successfully created, this method will return the new rowId value,
     * otherwise return a '-1' value to indicate failure.
     * 
     * @param user
     *            The data of input user
     * @return The user ID or -1 if failed.
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long createUser(User user) {
        ContentValues values = new ContentValues();
        values.put(UsersDbAccess.TABLE_KEY_LOGINNAME, user.getLoginname());
        values.put(UsersDbAccess.TABLE_KEY_PASSWORD, user.getPassword());
        values.put(UsersDbAccess.TABLE_KEY_USER_TYPE, user.getUserType().ordinal());
        values.put(UsersDbAccess.TABLE_KEY_CREATION_DATETIME, user.getCreationDate().getTime());
        values.put(UsersDbAccess.TABLE_KEY_IS_ACTIVATED, user.isActivated());
        values.put(UsersDbAccess.TABLE_KEY_ADDRESS_ID, user.getAddress().getId());

        return this.mDb.insert(UsersDbAccess.TABLE_NAME, null, values);
    }

    /**
     * Delete user with the given userId.
     * 
     * @param userId
     *            The ID of user which is deleted
     * @return true if user is deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deleteUser(long userId) {

        String table = UsersDbAccess.TABLE_NAME;
        String whereClause = UsersDbAccess.TABLE_KEY_USER_ID + "=?";
        String[] whereArgs = new String[] { "" + userId };
        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Get a Cursor of a list of all users in the database.
     * 
     * @param
     * @return Cursor over all users, null if no users could be found or
     *         retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllUsers() {

        String table = UsersDbAccess.TABLE_NAME;
        String[] columns = new String[] { UsersDbAccess.TABLE_KEY_USER_ID,
                UsersDbAccess.TABLE_KEY_LOGINNAME, UsersDbAccess.TABLE_KEY_PASSWORD,
                UsersDbAccess.TABLE_KEY_USER_TYPE, UsersDbAccess.TABLE_KEY_CREATION_DATETIME,
                UsersDbAccess.TABLE_KEY_IS_ACTIVATED, UsersDbAccess.TABLE_KEY_ADDRESS_ID };
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;

        return mDb.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    /**
     * Get a Cursor which is positioned at the user that matches the given
     * userId.
     * 
     * @param userId
     *            The user's ID
     * @return The cursor which is positioned to matching one, null if no users
     *         could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchUser(long userId) {

        String table = UsersDbAccess.TABLE_NAME;
        String[] columns = new String[] { UsersDbAccess.TABLE_KEY_USER_ID,
                UsersDbAccess.TABLE_KEY_LOGINNAME, UsersDbAccess.TABLE_KEY_PASSWORD,
                UsersDbAccess.TABLE_KEY_USER_TYPE, UsersDbAccess.TABLE_KEY_CREATION_DATETIME,
                UsersDbAccess.TABLE_KEY_IS_ACTIVATED, UsersDbAccess.TABLE_KEY_ADDRESS_ID };
        String selection = UsersDbAccess.TABLE_KEY_USER_ID + "=?";
        String[] selectionArgs = new String[] { "" + userId };
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = "1";

        Cursor result = mDb.query(true, table, columns, selection, selectionArgs, groupBy, having,
                orderBy, limit);
        if (null != result) {
            result.moveToFirst();
        }
        return result;
    }

    /**
     * Get a Cursor which is positioned at the user that has the the given
     * login name.
     * 
     * @param loginname
     *            The user's login name
     * @return The cursor which is positioned to matching one, null if no users
     *         could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchUser(String loginname) {

        String table = UsersDbAccess.TABLE_NAME;
        String[] columns = new String[] { UsersDbAccess.TABLE_KEY_USER_ID,
                UsersDbAccess.TABLE_KEY_LOGINNAME, UsersDbAccess.TABLE_KEY_PASSWORD,
                UsersDbAccess.TABLE_KEY_USER_TYPE, UsersDbAccess.TABLE_KEY_CREATION_DATETIME,
                UsersDbAccess.TABLE_KEY_IS_ACTIVATED, UsersDbAccess.TABLE_KEY_ADDRESS_ID };
        String selection = UsersDbAccess.TABLE_KEY_LOGINNAME + "=?";
        String[] selectionArgs = new String[] { loginname };
        String groupBy = null;
        String having = null;
        String orderBy = null;
        String limit = "1";

        Cursor result = mDb.query(true, table, columns, selection, selectionArgs, groupBy, having,
                orderBy, limit);
        if (null != result) {
            result.moveToFirst();
        }
        return result;
    }

    /**
     * Update user using the details provided.
     * 
     * @param user
     *            The user which is updated.
     * @return true if user was successfully updated, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean updateUser(User user) {

        ContentValues values = new ContentValues();

        if ("" != user.getLoginname()) {
            values.put(UsersDbAccess.TABLE_KEY_LOGINNAME, user.getLoginname());
        }

        if ("" != user.getPassword()) {
            values.put(UsersDbAccess.TABLE_KEY_PASSWORD, user.getPassword());
        }

        if (UserType.UNKNOWN_USER != user.getUserType()) {
            values.put(UsersDbAccess.TABLE_KEY_USER_TYPE, user.getUserType().getValue());
        }

        values.put(UsersDbAccess.TABLE_KEY_IS_ACTIVATED, user.isActivated());

        if (0 < user.getAddress().getId()) {
            values.put(UsersDbAccess.TABLE_KEY_ADDRESS_ID, user.getAddress().getId());
        }

        String table = UsersDbAccess.TABLE_NAME;
        String whereClause = UsersDbAccess.TABLE_KEY_USER_ID + "=?";
        String[] whereArgs = new String[] { "" + user.getId() };

        final int rowCnt = mDb.update(table, values, whereClause, whereArgs);

        return (rowCnt > 0);
    }
}
