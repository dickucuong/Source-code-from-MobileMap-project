/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.DataAccess.DbAdapters;

import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Poi;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * The local database adapter for 'pois' table.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class PoisDbAccess {

    public static final String TABLE_NAME = "pois";

    public static final String TABLE_KEY_POI_ID = "_id";
    public static final String TABLE_KEY_LONGITUDE = "longitude";
    public static final String TABLE_KEY_LATITUDE = "latitude";
    public static final String TABLE_KEY_POI_TYPE = "poi_type";
    public static final String TABLE_KEY_POI_NAME = "name";
    public static final String TABLE_KEY_DESCRIPTIONS = "descriptions";
    public static final String TABLE_KEY_RATING_VALUE = "rating_value";
    public static final String TABLE_KEY_ADDRESS_ID = "address_id";
    public static final String TABLE_KEY_CREATION_DATETIME = "creation_datetime";
    public static final String TABLE_KEY_CREATION_USER_ID = "creation_user_id";
    public static final String TABLE_KEY_IS_APPROVED = "is_approved";
    public static final String TABLE_KEY_APPROVE_DATETIME = "approve_datetime";
    public static final String TABLE_KEY_APPROVE_USER_ID = "approve_user_id";
    public static final String TABLE_KEY_IS_ACTIVATED = "is_activated";

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
    public PoisDbAccess(SQLiteDatabase db) {
        this.mDb = db;
    }

    /**
     * Create a new POI in Mobile Map database. If the POI is successfully
     * created, this method will return the new rowId value, otherwise return a
     * '-1' value to indicate failure.
     * 
     * @param poi
     *            The data of input POI
     * @return The category ID or -1 if failed.
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long createPoi(Poi poi) {

        ContentValues values = new ContentValues();

        values.put(PoisDbAccess.TABLE_KEY_LONGITUDE, poi.getLongitude());
        values.put(PoisDbAccess.TABLE_KEY_LATITUDE, poi.getLatitude());
        values.put(PoisDbAccess.TABLE_KEY_POI_TYPE, poi.getPoiType());
        values.put(PoisDbAccess.TABLE_KEY_POI_NAME, poi.getName());
        values.put(PoisDbAccess.TABLE_KEY_DESCRIPTIONS, poi.getDescriptions());
        values.put(PoisDbAccess.TABLE_KEY_RATING_VALUE, poi.getRating());
        values.put(PoisDbAccess.TABLE_KEY_ADDRESS_ID, poi.getAddress().getId());
        values.put(PoisDbAccess.TABLE_KEY_CREATION_DATETIME, poi.getCreationDate().getTime());
        values.put(PoisDbAccess.TABLE_KEY_CREATION_USER_ID, poi.getCreationUser().getId());
        values.put(PoisDbAccess.TABLE_KEY_IS_APPROVED, poi.isApproved());
        values.put(PoisDbAccess.TABLE_KEY_APPROVE_DATETIME, poi.getApproveDate().getTime());
        values.put(PoisDbAccess.TABLE_KEY_APPROVE_USER_ID, poi.getApproveUser().getId());
        values.put(PoisDbAccess.TABLE_KEY_IS_ACTIVATED, poi.isActivated());

        return this.mDb.insert(PoisDbAccess.TABLE_NAME, null, values);
    }

    /**
     * Delete POI with the given id.
     * 
     * @param poiId
     *            The ID of POI which is deleted
     * @return true if POI is deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deletePoi(long poiId) {

        String table = PoisDbAccess.TABLE_NAME;
        String whereClause = PoisDbAccess.TABLE_KEY_POI_ID + "=?";
        String[] whereArgs = new String[] { "" + poiId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Get a Cursor of a list of all POIs in the database.
     * 
     * @param
     * @return Cursor over all POIs
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllPois() {

        String table = PoisDbAccess.TABLE_NAME;
        String[] columns = new String[] { PoisDbAccess.TABLE_KEY_POI_ID,
                PoisDbAccess.TABLE_KEY_LONGITUDE, PoisDbAccess.TABLE_KEY_LATITUDE,
                PoisDbAccess.TABLE_KEY_POI_TYPE, PoisDbAccess.TABLE_KEY_POI_NAME,
                PoisDbAccess.TABLE_KEY_DESCRIPTIONS, PoisDbAccess.TABLE_KEY_RATING_VALUE,
                PoisDbAccess.TABLE_KEY_ADDRESS_ID, PoisDbAccess.TABLE_KEY_CREATION_DATETIME,
                PoisDbAccess.TABLE_KEY_CREATION_USER_ID, PoisDbAccess.TABLE_KEY_IS_APPROVED,
                PoisDbAccess.TABLE_KEY_APPROVE_DATETIME, PoisDbAccess.TABLE_KEY_APPROVE_USER_ID,
                PoisDbAccess.TABLE_KEY_IS_ACTIVATED };
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
     * Get a Cursor of a list of all POIs in the database. Each POI has simple
     * information such as: longitude, latitude, Type, name
     * 
     * @param
     * @return Cursor over all POIs
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllPoisSimple() {

        String table = PoisDbAccess.TABLE_NAME;
        String[] columns = new String[] { PoisDbAccess.TABLE_KEY_POI_ID,
                PoisDbAccess.TABLE_KEY_LONGITUDE, PoisDbAccess.TABLE_KEY_LATITUDE,
                PoisDbAccess.TABLE_KEY_POI_TYPE, PoisDbAccess.TABLE_KEY_POI_NAME,
                PoisDbAccess.TABLE_KEY_RATING_VALUE, PoisDbAccess.TABLE_KEY_CREATION_USER_ID,
                PoisDbAccess.TABLE_KEY_IS_APPROVED, PoisDbAccess.TABLE_KEY_IS_ACTIVATED };
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
     * Get a Cursor of a list of all private POIs in the database. Each POI has
     * simple information such as: longitude, latitude, Type, name
     * 
     * @param
     * @return Cursor over all POIs
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllPrivatePoisSimple() {

        String table = PoisDbAccess.TABLE_NAME;
        String[] columns = new String[] { PoisDbAccess.TABLE_KEY_POI_ID,
                PoisDbAccess.TABLE_KEY_LONGITUDE, PoisDbAccess.TABLE_KEY_LATITUDE,
                PoisDbAccess.TABLE_KEY_POI_TYPE, PoisDbAccess.TABLE_KEY_POI_NAME,
                PoisDbAccess.TABLE_KEY_RATING_VALUE, PoisDbAccess.TABLE_KEY_CREATION_USER_ID,
                PoisDbAccess.TABLE_KEY_IS_APPROVED, PoisDbAccess.TABLE_KEY_IS_ACTIVATED };
        String selection = PoisDbAccess.TABLE_KEY_POI_TYPE + "=?";
        String[] selectionArgs = new String[] { "" + Poi.POI_TYPE_PRIVATE };
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
     * Get a Cursor of a list of all private POIs of a specific user in the
     * database. Each POI has simple information such as: longitude, latitude,
     * Type, name
     * 
     * @param creationUserId
     *            The ID of user which create POIs
     * @return Cursor over all POIs
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllPrivatePoisSimple(long creationUserId) {

        String table = PoisDbAccess.TABLE_NAME;
        String[] columns = new String[] { PoisDbAccess.TABLE_KEY_POI_ID,
                PoisDbAccess.TABLE_KEY_LONGITUDE, PoisDbAccess.TABLE_KEY_LATITUDE,
                PoisDbAccess.TABLE_KEY_POI_TYPE, PoisDbAccess.TABLE_KEY_POI_NAME,
                PoisDbAccess.TABLE_KEY_RATING_VALUE, PoisDbAccess.TABLE_KEY_CREATION_USER_ID,
                PoisDbAccess.TABLE_KEY_IS_APPROVED, PoisDbAccess.TABLE_KEY_IS_ACTIVATED };
        String selection = PoisDbAccess.TABLE_KEY_POI_TYPE + "=?" + " AND "
                + PoisDbAccess.TABLE_KEY_CREATION_USER_ID + "=?";
        String[] selectionArgs = new String[] { "" + Poi.POI_TYPE_PRIVATE, "" + creationUserId };
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
     * Get a Cursor of a list of all public POIs in the database. Each POI has
     * simple information such as: longitude, latitude, Type, name
     * 
     * @param
     * @return Cursor over all POIs
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllPublicPoisSimple() {

        String table = PoisDbAccess.TABLE_NAME;
        String[] columns = new String[] { PoisDbAccess.TABLE_KEY_POI_ID,
                PoisDbAccess.TABLE_KEY_LONGITUDE, PoisDbAccess.TABLE_KEY_LATITUDE,
                PoisDbAccess.TABLE_KEY_POI_TYPE, PoisDbAccess.TABLE_KEY_POI_NAME,
                PoisDbAccess.TABLE_KEY_RATING_VALUE, PoisDbAccess.TABLE_KEY_CREATION_USER_ID,
                PoisDbAccess.TABLE_KEY_IS_APPROVED, PoisDbAccess.TABLE_KEY_IS_ACTIVATED };
        String selection = PoisDbAccess.TABLE_KEY_POI_TYPE + "=?";
        String[] selectionArgs = new String[] { "" + Poi.POI_TYPE_PUBLIC };
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
     * Get a Cursor of a list of all public POIs of a specific user in the
     * database. Each POI has simple information such as: longitude, latitude,
     * Type, name
     * 
     * @param creationUserId
     *            The ID of user which create POIs
     * @return Cursor over all POIs
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllPublicPoisSimple(long creationUserId) {

        String table = PoisDbAccess.TABLE_NAME;
        String[] columns = new String[] { PoisDbAccess.TABLE_KEY_POI_ID,
                PoisDbAccess.TABLE_KEY_LONGITUDE, PoisDbAccess.TABLE_KEY_LATITUDE,
                PoisDbAccess.TABLE_KEY_POI_TYPE, PoisDbAccess.TABLE_KEY_POI_NAME,
                PoisDbAccess.TABLE_KEY_RATING_VALUE, PoisDbAccess.TABLE_KEY_CREATION_USER_ID,
                PoisDbAccess.TABLE_KEY_IS_APPROVED, PoisDbAccess.TABLE_KEY_IS_ACTIVATED };
        String selection = PoisDbAccess.TABLE_KEY_POI_TYPE + "=?" + " AND "
                + PoisDbAccess.TABLE_KEY_CREATION_USER_ID + "=?";
        String[] selectionArgs = new String[] { "" + Poi.POI_TYPE_PUBLIC, "" + creationUserId };
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
     * Get a Cursor which is positioned at the POI that matches the given id.
     * 
     * @param poiId
     *            The POI's ID
     * @return The cursor which is positioned to matching one, null if no POI
     *         could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchPoi(long poiId) {

        String table = PoisDbAccess.TABLE_NAME;
        String[] columns = new String[] { PoisDbAccess.TABLE_KEY_POI_ID,
                PoisDbAccess.TABLE_KEY_LONGITUDE, PoisDbAccess.TABLE_KEY_LATITUDE,
                PoisDbAccess.TABLE_KEY_POI_TYPE, PoisDbAccess.TABLE_KEY_POI_NAME,
                PoisDbAccess.TABLE_KEY_DESCRIPTIONS, PoisDbAccess.TABLE_KEY_RATING_VALUE,
                PoisDbAccess.TABLE_KEY_ADDRESS_ID, PoisDbAccess.TABLE_KEY_CREATION_DATETIME,
                PoisDbAccess.TABLE_KEY_CREATION_USER_ID, PoisDbAccess.TABLE_KEY_IS_APPROVED,
                PoisDbAccess.TABLE_KEY_APPROVE_DATETIME, PoisDbAccess.TABLE_KEY_APPROVE_USER_ID,
                PoisDbAccess.TABLE_KEY_IS_ACTIVATED };
        String selection = PoisDbAccess.TABLE_KEY_POI_ID + "=?";
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
     * Get a Cursor which is positioned at the POI that matches the given
     * longitude and latitude.
     * 
     * @param longitude
     *            The longitude value.
     * @param latitude
     *            The latitude value.
     * @return The cursor which is positioned to matching one, null if no POI
     *         could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchPoi(long longitude, long latitude) {

        String table = PoisDbAccess.TABLE_NAME;
        String[] columns = new String[] { PoisDbAccess.TABLE_KEY_POI_ID,
                PoisDbAccess.TABLE_KEY_LONGITUDE, PoisDbAccess.TABLE_KEY_LATITUDE,
                PoisDbAccess.TABLE_KEY_POI_TYPE, PoisDbAccess.TABLE_KEY_POI_NAME,
                PoisDbAccess.TABLE_KEY_DESCRIPTIONS, PoisDbAccess.TABLE_KEY_RATING_VALUE,
                PoisDbAccess.TABLE_KEY_ADDRESS_ID, PoisDbAccess.TABLE_KEY_CREATION_DATETIME,
                PoisDbAccess.TABLE_KEY_CREATION_USER_ID, PoisDbAccess.TABLE_KEY_IS_APPROVED,
                PoisDbAccess.TABLE_KEY_APPROVE_DATETIME, PoisDbAccess.TABLE_KEY_APPROVE_USER_ID,
                PoisDbAccess.TABLE_KEY_IS_ACTIVATED };
        String selection = PoisDbAccess.TABLE_KEY_LONGITUDE + "=?" + " AND "
                + PoisDbAccess.TABLE_KEY_LATITUDE + "=?";
        String[] selectionArgs = new String[] { "" + longitude, "" + latitude };
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
     * Update POI using the details provided.
     * 
     * @param poi
     *            The POI which is updated.
     * @return true if POI was successfully updated, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean updatePoi(Poi poi) {

        ContentValues values = new ContentValues();

        if (Poi.POI_TYPE_UNKNOWN != poi.getPoiType()) {
            values.put(PoisDbAccess.TABLE_KEY_POI_TYPE, poi.getPoiType());
        }

        if (!"".equals(poi.getName())) {
            values.put(PoisDbAccess.TABLE_KEY_POI_NAME, poi.getName());
        }

        if (!"".equals(poi.getDescriptions())) {
            values.put(PoisDbAccess.TABLE_KEY_DESCRIPTIONS, poi.getDescriptions());
        }

        if (0 < poi.getRating()) {
            values.put(PoisDbAccess.TABLE_KEY_RATING_VALUE, poi.getRating());
        }

        if (0 < poi.getAddress().getId()) {
            values.put(PoisDbAccess.TABLE_KEY_ADDRESS_ID, poi.getAddress().getId());
        }

        values.put(PoisDbAccess.TABLE_KEY_IS_APPROVED, poi.isApproved());

        if (0 < poi.getApproveDate().getTime()) {
            values.put(PoisDbAccess.TABLE_KEY_APPROVE_DATETIME, poi.getApproveDate().getTime());
        }

        if (0 < poi.getApproveUser().getId()) {
            values.put(PoisDbAccess.TABLE_KEY_APPROVE_USER_ID, poi.getApproveUser().getId());
        }

        values.put(PoisDbAccess.TABLE_KEY_IS_ACTIVATED, poi.isActivated());

        String table = PoisDbAccess.TABLE_NAME;
        String whereClause = PoisDbAccess.TABLE_KEY_POI_ID + "=?";
        String[] whereArgs = new String[] { "" + poi.getId() };

        final int rowCnt = this.mDb.update(table, values, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Get a Cursor of search result.
     * 
     * @param searchString
     *            The string used to search in the text fields of POI table
     * @return The cursor which is positioned to matching one, null if no POI
     *         could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor searchPoi(String searchString) {

        String table = PoisDbAccess.TABLE_NAME;
        String[] columns = new String[] { PoisDbAccess.TABLE_KEY_POI_ID,
                PoisDbAccess.TABLE_KEY_LONGITUDE, PoisDbAccess.TABLE_KEY_LATITUDE,
                PoisDbAccess.TABLE_KEY_POI_TYPE, PoisDbAccess.TABLE_KEY_POI_NAME,
                PoisDbAccess.TABLE_KEY_DESCRIPTIONS, PoisDbAccess.TABLE_KEY_RATING_VALUE,
                PoisDbAccess.TABLE_KEY_ADDRESS_ID, PoisDbAccess.TABLE_KEY_CREATION_DATETIME,
                PoisDbAccess.TABLE_KEY_CREATION_USER_ID, PoisDbAccess.TABLE_KEY_IS_APPROVED,
                PoisDbAccess.TABLE_KEY_APPROVE_DATETIME, PoisDbAccess.TABLE_KEY_APPROVE_USER_ID,
                PoisDbAccess.TABLE_KEY_IS_ACTIVATED };
        String selection = PoisDbAccess.TABLE_KEY_POI_NAME + " LIKE ?";
        String[] selectionArgs = new String[] { "%" + searchString + "%" };
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
     * Get a Cursor of search result.
     * 
     * @param searchString
     *            The string used to search in the text fields of POI table
     * @param categoryId
     *            The ID of category which POI is belong to
     * @return The cursor which is positioned to matching one, null if no POI
     *         could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor searchPoi(String searchString, long categoryId) {

        String sql = "SELECT a." + PoisDbAccess.TABLE_KEY_POI_ID + ", a."
                + PoisDbAccess.TABLE_KEY_LONGITUDE + ", a." + PoisDbAccess.TABLE_KEY_LATITUDE
                + ", a." + PoisDbAccess.TABLE_KEY_POI_TYPE + ", a."
                + PoisDbAccess.TABLE_KEY_POI_NAME + ", a." + PoisDbAccess.TABLE_KEY_RATING_VALUE
                + ", a." + PoisDbAccess.TABLE_KEY_CREATION_USER_ID + ", a."
                + PoisDbAccess.TABLE_KEY_IS_APPROVED + ", a." + PoisDbAccess.TABLE_KEY_IS_ACTIVATED
                + " FROM " + PoisDbAccess.TABLE_NAME + " a INNER JOIN "
                + PoisCategoriesDbAccess.TABLE_NAME + " b ON (b."
                + PoisCategoriesDbAccess.TABLE_KEY_CATEGORY_ID + "=? AND a."
                + PoisDbAccess.TABLE_KEY_POI_ID + "=b." + PoisCategoriesDbAccess.TABLE_KEY_POI_ID
                + ") WHERE a." + PoisDbAccess.TABLE_KEY_POI_NAME + " LIKE ?;";
        String[] selectionArgs = new String[] { "" + categoryId, "%" + searchString + "%" };

        Cursor cursor = this.mDb.rawQuery(sql, selectionArgs);
        if (null != cursor) {
            cursor.moveToFirst();
        }

        return cursor;
    }

}
