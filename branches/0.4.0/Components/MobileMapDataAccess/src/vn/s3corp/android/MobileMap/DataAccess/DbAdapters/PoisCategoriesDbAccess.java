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
public class PoisCategoriesDbAccess {

    public static final String TABLE_NAME = "pois_categories";

    public static final String TABLE_KEY_ID = "_id";
    public static final String TABLE_KEY_POI_ID = "poi_id";
    public static final String TABLE_KEY_CATEGORY_ID = "category_id";

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
    public PoisCategoriesDbAccess(SQLiteDatabase db) {
        this.mDb = db;
    }

    /**
     * Create a new row which represents a POI belong to a category in Mobile
     * Map database. If the category is successfully created, this method will
     * return the new rowId value, otherwise return a '-1' value to indicate
     * failure.
     * 
     * @param categoryId
     *            The ID of category
     * @param poiId
     *            The ID of POI
     * @return The new row ID or -1 if failed.
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long createPoiCategory(long categoryId, long poiId) {

        ContentValues values = new ContentValues();

        values.put(PoisCategoriesDbAccess.TABLE_KEY_CATEGORY_ID, categoryId);
        values.put(PoisCategoriesDbAccess.TABLE_KEY_POI_ID, poiId);

        return this.mDb.insert(PoisCategoriesDbAccess.TABLE_NAME, null, values);
    }

    /**
     * Delete 1 row with the given id.
     * 
     * @param categoryId
     *            The ID of category
     * @param poiId
     *            The ID of POI
     * @return true if the row is deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deletePoiCategory(long categoryId, long poiId) {

        String table = PoisCategoriesDbAccess.TABLE_NAME;
        String whereClause = PoisCategoriesDbAccess.TABLE_KEY_CATEGORY_ID + "=?" + " AND "
                + PoisCategoriesDbAccess.TABLE_KEY_POI_ID + "=?";
        String[] whereArgs = new String[] { "" + categoryId, "" + poiId };

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

        String table = PoisCategoriesDbAccess.TABLE_NAME;
        String whereClause = PoisCategoriesDbAccess.TABLE_KEY_POI_ID + "=?";
        String[] whereArgs = new String[] { "" + poiId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Delete rows with the given category's ID.
     * 
     * @param categoryId
     *            The ID of category
     * @return true if rows are deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deleteCategory(long categoryId) {

        String table = PoisCategoriesDbAccess.TABLE_NAME;
        String whereClause = PoisCategoriesDbAccess.TABLE_KEY_CATEGORY_ID + "=?";
        String[] whereArgs = new String[] { "" + categoryId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Get a Cursor of a list of all POIs in a category.
     * 
     * @param categoryId
     *            The ID of category
     * @return Cursor over a list of all POIs in a category
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchPois(long categoryId) {

        String table = PoisCategoriesDbAccess.TABLE_NAME;
        String[] columns = new String[] { PoisCategoriesDbAccess.TABLE_KEY_CATEGORY_ID,
                PoisCategoriesDbAccess.TABLE_KEY_POI_ID };
        String selection = PoisCategoriesDbAccess.TABLE_KEY_CATEGORY_ID + "=?";
        String[] selectionArgs = new String[] { "" + categoryId };
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
     * Get a Cursor of a list of all categories of a POI.
     * 
     * @param poiId
     *            The ID of POI
     * @return Cursor over a list of all categories of a POI
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchCategories(long poiId) {

        String table = PoisCategoriesDbAccess.TABLE_NAME;
        String[] columns = new String[] { PoisCategoriesDbAccess.TABLE_KEY_CATEGORY_ID,
                PoisCategoriesDbAccess.TABLE_KEY_POI_ID };
        String selection = PoisCategoriesDbAccess.TABLE_KEY_POI_ID + "=?";
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
}
