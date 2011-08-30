/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.DataAccess.DbAdapters;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * The local database adapter for 'pois_categories' table.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class FavoritePoisDbAccess {

    public static final String TABLE_NAME = "favorite_pois";

    public static final String TABLE_KEY_ID = "_id";
    public static final String TABLE_KEY_POI_ID = "poi_id";
    public static final String TABLE_KEY_USER_ID = "user_id";

    private SQLiteDatabase mDb;

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
    public FavoritePoisDbAccess(SQLiteDatabase db) {
        this.mDb = db;
    }

    /**
     * Create a new row which represents a POI is marked as a favorite POI by
     * user. If a new row is successfully created, this method will return the
     * new rowId value, otherwise return a '-1' value to indicate failure.
     * 
     * @param userId
     *            The ID of user
     * @param poiId
     *            The ID of POI
     * @return The new row ID or -1 if failed.
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long createFavoritePoi(long userId, long poiId) {

        ContentValues values = new ContentValues();

        values.put(FavoritePoisDbAccess.TABLE_KEY_USER_ID, userId);
        values.put(FavoritePoisDbAccess.TABLE_KEY_POI_ID, poiId);

        return this.mDb.insert(FavoritePoisDbAccess.TABLE_NAME, null, values);
    }

    /**
     * Delete 1 row with the given id.
     * 
     * @param userId
     *            The ID of user
     * @param poiId
     *            The ID of POI
     * @return true if the row is deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deleteFavoritePoi(long userId, long poiId) {

        String table = FavoritePoisDbAccess.TABLE_NAME;
        String whereClause = FavoritePoisDbAccess.TABLE_KEY_USER_ID + "=?" + " AND "
                + FavoritePoisDbAccess.TABLE_KEY_POI_ID + "=?";
        String[] whereArgs = new String[] { "" + userId, "" + poiId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Delete rows with the given POI's ID.
     * 
     * @param poiId
     *            The ID of POI
     * @return true if rows are deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deletePoi(long poiId) {

        String table = FavoritePoisDbAccess.TABLE_NAME;
        String whereClause = FavoritePoisDbAccess.TABLE_KEY_POI_ID + "=?";
        String[] whereArgs = new String[] { "" + poiId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Delete rows with the given user's ID.
     * 
     * @param userId
     *            The ID of user
     * @return true if rows are deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deleteUser(long userId) {

        String table = FavoritePoisDbAccess.TABLE_NAME;
        String whereClause = FavoritePoisDbAccess.TABLE_KEY_USER_ID + "=?";
        String[] whereArgs = new String[] { "" + userId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Get a Cursor of a list of all favorite POIs of user.
     * 
     * @param userId
     *            The ID of user
     * @return Cursor over a list of all favorite POIs of user
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchPois(long userId) {

        String table = FavoritePoisDbAccess.TABLE_NAME;
        String[] columns = new String[] { FavoritePoisDbAccess.TABLE_KEY_USER_ID,
                FavoritePoisDbAccess.TABLE_KEY_POI_ID };
        String selection = FavoritePoisDbAccess.TABLE_KEY_USER_ID + "=?";
        String[] selectionArgs = new String[] { "" + userId };
        String groupBy = null;
        String having = null;
        String orderBy = null;

        Cursor cursor = this.mDb.query(table, columns, selection, selectionArgs, groupBy, having,
                orderBy);
        if (null != cursor) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    /**
     * Get a Cursor of a list of all users which mark POI as favorite.
     * 
     * @param poiId
     *            The ID of POI
     * @return Cursor over a list of all users which mark POI as favorite
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchUsers(long poiId) {

        String table = FavoritePoisDbAccess.TABLE_NAME;
        String[] columns = new String[] { FavoritePoisDbAccess.TABLE_KEY_USER_ID,
                FavoritePoisDbAccess.TABLE_KEY_POI_ID };
        String selection = FavoritePoisDbAccess.TABLE_KEY_POI_ID + "=?";
        String[] selectionArgs = new String[] { "" + poiId };
        String groupBy = null;
        String having = null;
        String orderBy = null;

        Cursor cursor = this.mDb.query(table, columns, selection, selectionArgs, groupBy, having,
                orderBy);
        if (null != cursor) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    /**
     * Get a Cursor of a POI which is marked as favorite.
     * 
     * @param userId
     *            The ID of user
     * @param poiId
     *            The ID of POI
     * @return Cursor of a POI which is marked as favorite
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchFavoritePoi(long userId, long poiId) {

        String table = FavoritePoisDbAccess.TABLE_NAME;
        String[] columns = new String[] { FavoritePoisDbAccess.TABLE_KEY_USER_ID,
                FavoritePoisDbAccess.TABLE_KEY_POI_ID };
        String selection = FavoritePoisDbAccess.TABLE_KEY_USER_ID + "=?" + " AND " + FavoritePoisDbAccess.TABLE_KEY_POI_ID + "=?";
        String[] selectionArgs = new String[] { "" + userId, "" + poiId };
        String groupBy = null;
        String having = null;
        String orderBy = null;

        Cursor cursor = this.mDb.query(table, columns, selection, selectionArgs, groupBy, having,
                orderBy);
        if (null != cursor) {
            cursor.moveToFirst();
        }

        return cursor;
    }
}
