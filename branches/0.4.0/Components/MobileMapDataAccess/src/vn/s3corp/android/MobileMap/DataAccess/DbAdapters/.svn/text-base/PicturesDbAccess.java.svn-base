/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.DataAccess.DbAdapters;

import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Picture;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * The local database adapter for 'pictures' table.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class PicturesDbAccess {

    public static final String TABLE_NAME = "pictures";

    public static final String TABLE_KEY_PICTURE_ID = "_id";
    public static final String TABLE_KEY_POI_ID = "poi_id";
    public static final String TABLE_KEY_LOCATION = "location";
    public static final String TABLE_KEY_DESCRIPTIONS = "descriptions";
    public static final String TABLE_KEY_IS_BAD_REPORTED = "is_badreported";
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
    public PicturesDbAccess(SQLiteDatabase db) {
        this.mDb = db;
    }

    /**
     * Create a new picture value in Mobile Map database. If the picture is
     * successfully created, this method will return the new rowId value,
     * otherwise return a '-1' value to indicate failure.
     * 
     * @param picture
     *            The data of input picture
     * @param poiId
     *            The ID of POI which picture is belong to
     * @return The picture's ID or -1 if failed.
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long createPicture(Picture picture, long poiId) {

        ContentValues values = new ContentValues();

        values.put(PicturesDbAccess.TABLE_KEY_POI_ID, poiId);
        values.put(PicturesDbAccess.TABLE_KEY_LOCATION, picture.getLocation());
        values.put(PicturesDbAccess.TABLE_KEY_DESCRIPTIONS, picture.getDescription());
        values.put(PicturesDbAccess.TABLE_KEY_IS_BAD_REPORTED, picture.isBadReport());
        values.put(PicturesDbAccess.TABLE_KEY_IS_ACTIVATED, picture.isActivated());

        return this.mDb.insert(PicturesDbAccess.TABLE_NAME, null, values);
    }

    /**
     * Delete picture with the given id.
     * 
     * @param pictureId
     *            The ID of picture which is deleted
     * @return true if picture is deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deletePicture(long pictureId) {

        String table = PicturesDbAccess.TABLE_NAME;
        String whereClause = PicturesDbAccess.TABLE_KEY_PICTURE_ID + "=?";
        String[] whereArgs = new String[] { "" + pictureId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Delete all pictures which are belong to a specific POI.
     * 
     * @param poiId
     *            The ID of POI
     * @return true if pictures are deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deleteAllPoiPictures(long poiId) {

        String table = PicturesDbAccess.TABLE_NAME;
        String whereClause = PicturesDbAccess.TABLE_KEY_POI_ID + "=?";
        String[] whereArgs = new String[] { "" + poiId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Get a Cursor of a list of all pictures in the database.
     * 
     * @param
     * @return Cursor over all pictures
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllPictures() {

        String table = PicturesDbAccess.TABLE_NAME;
        String[] columns = new String[] { PicturesDbAccess.TABLE_KEY_PICTURE_ID,
                PicturesDbAccess.TABLE_KEY_POI_ID, PicturesDbAccess.TABLE_KEY_LOCATION,
                PicturesDbAccess.TABLE_KEY_DESCRIPTIONS,
                PicturesDbAccess.TABLE_KEY_IS_BAD_REPORTED, PicturesDbAccess.TABLE_KEY_IS_ACTIVATED };
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
     * Get a Cursor which is positioned at the picture that matches the given
     * id.
     * 
     * @param pictureId
     *            The picture's ID
     * @return The cursor which is positioned to matching one, null if no
     *         picture could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchPicture(long pictureId) {

        String table = PicturesDbAccess.TABLE_NAME;
        String[] columns = new String[] { PicturesDbAccess.TABLE_KEY_PICTURE_ID,
                PicturesDbAccess.TABLE_KEY_POI_ID, PicturesDbAccess.TABLE_KEY_LOCATION,
                PicturesDbAccess.TABLE_KEY_DESCRIPTIONS,
                PicturesDbAccess.TABLE_KEY_IS_BAD_REPORTED, PicturesDbAccess.TABLE_KEY_IS_ACTIVATED };
        String selection = PicturesDbAccess.TABLE_KEY_PICTURE_ID + "=?";
        String[] selectionArgs = new String[] { "" + pictureId };
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
     * Get a Cursor which is positioned at all the pictures of a POI.
     * 
     * @param poiId
     *            The POI's ID
     * @return The cursor which is positioned to matching one, null if no
     *         picture could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllPoiPictures(long poiId) {

        String table = PicturesDbAccess.TABLE_NAME;
        String[] columns = new String[] { PicturesDbAccess.TABLE_KEY_PICTURE_ID,
                PicturesDbAccess.TABLE_KEY_POI_ID, PicturesDbAccess.TABLE_KEY_LOCATION,
                PicturesDbAccess.TABLE_KEY_DESCRIPTIONS,
                PicturesDbAccess.TABLE_KEY_IS_BAD_REPORTED, PicturesDbAccess.TABLE_KEY_IS_ACTIVATED };
        String selection = PicturesDbAccess.TABLE_KEY_POI_ID + "=?";
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
     * Update picture using the details provided.
     * 
     * @param picture
     *            The picture which is updated.
     * @return true if picture was successfully updated, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean updatePicture(Picture picture) {

        ContentValues values = new ContentValues();

        if (!"".equals(picture.getLocation())) {
            values.put(PicturesDbAccess.TABLE_KEY_LOCATION, picture.getLocation());
        }
        if (!"".equals(picture.getDescription())) {
            values.put(PicturesDbAccess.TABLE_KEY_DESCRIPTIONS, picture.getDescription());
        }
        values.put(PicturesDbAccess.TABLE_KEY_IS_BAD_REPORTED, picture.isBadReport());
        values.put(PicturesDbAccess.TABLE_KEY_IS_ACTIVATED, picture.isActivated());

        String table = PicturesDbAccess.TABLE_NAME;
        String whereClause = PicturesDbAccess.TABLE_KEY_PICTURE_ID + "=?";
        String[] whereArgs = new String[] { "" + picture.getId() };

        final int rowCnt = this.mDb.update(table, values, whereClause, whereArgs);

        return (rowCnt > 0);
    }
}
