/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.DataAccess.DbAdapters;

import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Rating;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * The local database adapter for 'ratings' table.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class RatingsDbAccess {

    public static final String TABLE_NAME = "ratings";

    public static final String TABLE_KEY_RATING_ID = "_id";
    public static final String TABLE_KEY_USER_ID = "user_id";
    public static final String TABLE_KEY_POI_ID = "poi_id";
    public static final String TABLE_KEY_RATING_VALUE = "rating_value";

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
    public RatingsDbAccess(SQLiteDatabase db) {
        this.mDb = db;
    }

    /**
     * Create a new rating value in Mobile Map database. If the rating is
     * successfully created, this method will return the new rowId value,
     * otherwise return a '-1' value to indicate failure.
     * 
     * @param rating
     *            The data of input rating
     * @param poiId
     *            The ID of POI which rating is belong to
     * @return The rating's ID or -1 if failed.
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long createRating(Rating rating, long poiId) {

        ContentValues values = new ContentValues();

        values.put(RatingsDbAccess.TABLE_KEY_USER_ID, rating.getUser().getId());
        values.put(RatingsDbAccess.TABLE_KEY_POI_ID, poiId);
        values.put(RatingsDbAccess.TABLE_KEY_RATING_VALUE, rating.getRatingValue());

        return this.mDb.insert(RatingsDbAccess.TABLE_NAME, null, values);
    }

    /**
     * Delete rating with the given id.
     * 
     * @param ratingId
     *            The ID of rating value which is deleted
     * @return true if rating value is deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deleteRating(long ratingId) {

        String table = RatingsDbAccess.TABLE_NAME;
        String whereClause = RatingsDbAccess.TABLE_KEY_RATING_ID + "=?";
        String[] whereArgs = new String[] { "" + ratingId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Delete all ratings which are belong to a specific user.
     * 
     * @param userId
     *            The ID of user
     * @return true if ratings are deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deleteAllUserRatings(long userId) {

        String table = RatingsDbAccess.TABLE_NAME;
        String whereClause = RatingsDbAccess.TABLE_KEY_USER_ID + "=?";
        String[] whereArgs = new String[] { "" + userId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Delete all ratings which are belong to a specific POI.
     * 
     * @param poiId
     *            The ID of POI
     * @return true if ratings are deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deleteAllPoiRatings(long poiId) {

        String table = RatingsDbAccess.TABLE_NAME;
        String whereClause = RatingsDbAccess.TABLE_KEY_POI_ID + "=?";
        String[] whereArgs = new String[] { "" + poiId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Get a Cursor of a list of all ratings in the database.
     * 
     * @param
     * @return Cursor over all ratings
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllRatings() {

        String table = RatingsDbAccess.TABLE_NAME;
        String[] columns = new String[] { RatingsDbAccess.TABLE_KEY_RATING_ID,
                RatingsDbAccess.TABLE_KEY_USER_ID, RatingsDbAccess.TABLE_KEY_POI_ID,
                RatingsDbAccess.TABLE_KEY_RATING_VALUE };
        String selection = null;
        String[] selectionArgs = null;
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
     * Get a Cursor which is positioned at the rating that matches the given id.
     * 
     * @param ratingId
     *            The rating's ID
     * @return The cursor which is positioned to matching one, null if no rating
     *         could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchRating(long ratingId) {

        String table = RatingsDbAccess.TABLE_NAME;
        String[] columns = new String[] { RatingsDbAccess.TABLE_KEY_RATING_ID,
                RatingsDbAccess.TABLE_KEY_USER_ID, RatingsDbAccess.TABLE_KEY_POI_ID,
                RatingsDbAccess.TABLE_KEY_RATING_VALUE };
        String selection = RatingsDbAccess.TABLE_KEY_RATING_ID + "=?";
        String[] selectionArgs = new String[] { "" + ratingId };
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
     * Get a Cursor which is positioned at all the ratings of a user.
     * 
     * @param userId
     *            The user's ID
     * @return The cursor which is positioned to matching one, null if no rating
     *         could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllUserRatings(long userId) {

        String table = RatingsDbAccess.TABLE_NAME;
        String[] columns = new String[] { RatingsDbAccess.TABLE_KEY_RATING_ID,
                RatingsDbAccess.TABLE_KEY_USER_ID, RatingsDbAccess.TABLE_KEY_POI_ID,
                RatingsDbAccess.TABLE_KEY_RATING_VALUE };
        String selection = RatingsDbAccess.TABLE_KEY_USER_ID + "=?";
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
     * Get a Cursor which is positioned at all the ratings of a POI.
     * 
     * @param poiId
     *            The POI's ID
     * @return The cursor which is positioned to matching one, null if no rating
     *         could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllPoiRatings(long poiId) {

        String table = RatingsDbAccess.TABLE_NAME;
        String[] columns = new String[] { RatingsDbAccess.TABLE_KEY_RATING_ID,
                RatingsDbAccess.TABLE_KEY_USER_ID, RatingsDbAccess.TABLE_KEY_POI_ID,
                RatingsDbAccess.TABLE_KEY_RATING_VALUE };
        String selection = RatingsDbAccess.TABLE_KEY_POI_ID + "=?";
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
     * Get a Cursor which is positioned at all the ratings of a POI.
     * 
     * @param userId, poiId
     *            The POI's ID
     * @return The cursor which is positioned to matching one, null if no rating
     *         could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchUserRatingPOI(long userId, long poiId) {

        String table = RatingsDbAccess.TABLE_NAME;
        String[] columns = new String[] { RatingsDbAccess.TABLE_KEY_RATING_ID,
                RatingsDbAccess.TABLE_KEY_USER_ID, RatingsDbAccess.TABLE_KEY_POI_ID,
                RatingsDbAccess.TABLE_KEY_RATING_VALUE };
        String selection = RatingsDbAccess.TABLE_KEY_USER_ID + "=? and "
                           + RatingsDbAccess.TABLE_KEY_POI_ID + "=?";
        String[] selectionArgs = new String[] { "" + userId, ""+ poiId};
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
     * Update rating using the details provided.
     * 
     * @param rating
     *            The rating which is updated.
     * @return true if rating was successfully updated, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean updateRating(Rating rating) {

        ContentValues values = new ContentValues();

        values.put(RatingsDbAccess.TABLE_KEY_RATING_VALUE, rating.getRatingValue());

        String table = RatingsDbAccess.TABLE_NAME;
        String whereClause = RatingsDbAccess.TABLE_KEY_RATING_ID + "=?";
        String[] whereArgs = new String[] { "" + rating.getId() };

        final int rowCnt = this.mDb.update(table, values, whereClause, whereArgs);

        return (rowCnt > 0);
    }
    
    
}
