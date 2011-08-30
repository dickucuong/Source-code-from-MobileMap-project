/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.DataAccess.DbAdapters;

import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Category;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * The local database adapter for 'categories' table.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class CategoriesDbAccess {

    public static final String TABLE_NAME = "categories";

    public static final String TABLE_KEY_CATEGORY_ID = "_id";
    public static final String TABLE_KEY_CATEGORY_NAME_EN = "name_en";

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
    public CategoriesDbAccess(SQLiteDatabase db) {
        this.mDb = db;
    }

    /**
     * Create a new category value in Mobile Map database. If the category is
     * successfully created, this method will return the new rowId value,
     * otherwise return a '-1' value to indicate failure.
     * 
     * @param category
     *            The data of input category
     * @return The category ID or -1 if failed.
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long createCategory(Category category) {

        ContentValues values = new ContentValues();

        values.put(CategoriesDbAccess.TABLE_KEY_CATEGORY_NAME_EN, category.getName());

        return this.mDb.insert(CategoriesDbAccess.TABLE_NAME, null, values);
    }

    /**
     * Delete category with the given id.
     * 
     * @param categoryId
     *            The ID of category which is deleted
     * @return true if category is deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deleteCategory(long categoryId) {

        String table = CategoriesDbAccess.TABLE_NAME;
        String whereClause = CategoriesDbAccess.TABLE_KEY_CATEGORY_ID + "=?";
        String[] whereArgs = new String[] { "" + categoryId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Get a Cursor of a list of all categories in the database.
     * 
     * @param
     * @return Cursor over all categories
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllCategories() {

        String table = CategoriesDbAccess.TABLE_NAME;
        String[] columns = new String[] { CategoriesDbAccess.TABLE_KEY_CATEGORY_ID,
                CategoriesDbAccess.TABLE_KEY_CATEGORY_NAME_EN };
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
     * Get a Cursor which is positioned at the category that matches the given
     * id.
     * 
     * @param categoryId
     *            The category's ID
     * @return The cursor which is positioned to matching one, null if no category
     *         could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchCategory(long categoryId) {

        String table = CategoriesDbAccess.TABLE_NAME;
        String[] columns = new String[] { CategoriesDbAccess.TABLE_KEY_CATEGORY_ID,
                CategoriesDbAccess.TABLE_KEY_CATEGORY_NAME_EN };
        String selection = CategoriesDbAccess.TABLE_KEY_CATEGORY_ID + "=?";
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
     * Update category using the details provided.
     * 
     * @param category
     *            The category which is updated.
     * @return true if category was successfully updated, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean updateCategory(Category category) {

        ContentValues values = new ContentValues();

        if (!"".equals(category.getName())) {
            values.put(CategoriesDbAccess.TABLE_KEY_CATEGORY_NAME_EN, category.getName());
        }

        String table = CategoriesDbAccess.TABLE_NAME;
        String whereClause = CategoriesDbAccess.TABLE_KEY_CATEGORY_ID + "=?";
        String[] whereArgs = new String[] { "" + category.getId() };

        final int rowCnt = this.mDb.update(table, values, whereClause, whereArgs);

        return (rowCnt > 0);
    }
}
