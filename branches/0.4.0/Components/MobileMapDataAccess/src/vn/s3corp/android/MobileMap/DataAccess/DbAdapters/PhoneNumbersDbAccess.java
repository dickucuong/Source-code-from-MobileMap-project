/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.DataAccess.DbAdapters;

import vn.s3corp.android.MobileMap.DataAccess.DataObjects.PhoneNumber;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * The local database adapter for 'phonenumbers' table.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class PhoneNumbersDbAccess {

    public static final String TABLE_NAME = "phonenumbers";

    public static final String TABLE_KEY_PHONENUMBER_ID = "_id";
    public static final String TABLE_KEY_USER_ID = "user_id";
    public static final String TABLE_KEY_POI_ID = "poi_id";
    public static final String TABLE_KEY_PHONENUMBER_STRING = "phonenumber_string";

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
    public PhoneNumbersDbAccess(SQLiteDatabase db) {
        this.mDb = db;
    }

    /**
     * Create a new phone number value in Mobile Map database. If the phone
     * number is successfully created, this method will return the new rowId
     * value, otherwise return a '-1' value to indicate failure.
     * 
     * @param phoneNumber
     *            The data of input phone number
     * @param userId
     *            The ID of user which phone number is belong to
     * @param poiId
     *            The ID of POI which phone number is belong to
     * @return The phone number's ID or -1 if failed.
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long createPhoneNumber(PhoneNumber phoneNumber, long userId, long poiId) {

        ContentValues values = new ContentValues();

        values.put(PhoneNumbersDbAccess.TABLE_KEY_USER_ID, userId);
        values.put(PhoneNumbersDbAccess.TABLE_KEY_POI_ID, poiId);
        values.put(PhoneNumbersDbAccess.TABLE_KEY_PHONENUMBER_STRING,
                phoneNumber.getPhoneNumberString());

        return this.mDb.insert(PhoneNumbersDbAccess.TABLE_NAME, null, values);
    }

    /**
     * Delete phone number with the given id.
     * 
     * @param phoneNumberId
     *            The ID of phone number which is deleted
     * @return true if phone number is deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deletePhoneNumber(long phoneNumberId) {

        String table = PhoneNumbersDbAccess.TABLE_NAME;
        String whereClause = PhoneNumbersDbAccess.TABLE_KEY_PHONENUMBER_ID + "=?";
        String[] whereArgs = new String[] { "" + phoneNumberId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Delete all phone numbers which are belong to a specific user.
     * 
     * @param userId
     *            The ID of user
     * @return true if phone numbers are deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deleteAllUserPhoneNumbers(long userId) {

        String table = PhoneNumbersDbAccess.TABLE_NAME;
        String whereClause = PhoneNumbersDbAccess.TABLE_KEY_USER_ID + "=?";
        String[] whereArgs = new String[] { "" + userId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Delete all phone numbers which are belong to a specific POI.
     * 
     * @param poiId
     *            The ID of POI
     * @return true if phone numbers are deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deleteAllPoiPhoneNumbers(long poiId) {

        String table = PhoneNumbersDbAccess.TABLE_NAME;
        String whereClause = PhoneNumbersDbAccess.TABLE_KEY_POI_ID + "=?";
        String[] whereArgs = new String[] { "" + poiId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Get a Cursor of a list of all phone numbers in the database.
     * 
     * @param
     * @return Cursor over all phone numbers
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllPhoneNumbers() {

        String table = PhoneNumbersDbAccess.TABLE_NAME;
        String[] columns = new String[] { PhoneNumbersDbAccess.TABLE_KEY_PHONENUMBER_ID,
                PhoneNumbersDbAccess.TABLE_KEY_USER_ID, PhoneNumbersDbAccess.TABLE_KEY_POI_ID,
                PhoneNumbersDbAccess.TABLE_KEY_PHONENUMBER_STRING };
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
     * Get a Cursor which is positioned at the phone number that matches the
     * given id.
     * 
     * @param phoneNumberId
     *            The phone number's ID
     * @return The cursor which is positioned to matching one, null if no phone
     *         number could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchPhoneNumber(long phoneNumberId) {

        String table = PhoneNumbersDbAccess.TABLE_NAME;
        String[] columns = new String[] { PhoneNumbersDbAccess.TABLE_KEY_PHONENUMBER_ID,
                PhoneNumbersDbAccess.TABLE_KEY_USER_ID, PhoneNumbersDbAccess.TABLE_KEY_POI_ID,
                PhoneNumbersDbAccess.TABLE_KEY_PHONENUMBER_STRING };
        String selection = PhoneNumbersDbAccess.TABLE_KEY_PHONENUMBER_ID + "=?";
        String[] selectionArgs = new String[] { "" + phoneNumberId };
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
     * Get a Cursor which is positioned at all phone numbers of a user.
     * 
     * @param userId
     *            The user's ID
     * @return The cursor which is positioned to matching one, null if no phone
     *         number could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllUserPhoneNumbers(long userId) {

        String table = PhoneNumbersDbAccess.TABLE_NAME;
        String[] columns = new String[] { PhoneNumbersDbAccess.TABLE_KEY_PHONENUMBER_ID,
                PhoneNumbersDbAccess.TABLE_KEY_USER_ID, PhoneNumbersDbAccess.TABLE_KEY_POI_ID,
                PhoneNumbersDbAccess.TABLE_KEY_PHONENUMBER_STRING };
        String selection = PhoneNumbersDbAccess.TABLE_KEY_USER_ID + "=?";
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
     * Get a Cursor which is positioned at all phone numbers of a POI.
     * 
     * @param poiId
     *            The POI's ID
     * @return The cursor which is positioned to matching one, null if no phone
     *         number could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllPoiPhoneNumbers(long poiId) {

        String table = PhoneNumbersDbAccess.TABLE_NAME;
        String[] columns = new String[] { PhoneNumbersDbAccess.TABLE_KEY_PHONENUMBER_ID,
                PhoneNumbersDbAccess.TABLE_KEY_USER_ID, PhoneNumbersDbAccess.TABLE_KEY_POI_ID,
                PhoneNumbersDbAccess.TABLE_KEY_PHONENUMBER_STRING };
        String selection = PhoneNumbersDbAccess.TABLE_KEY_POI_ID + "=?";
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
     * Update phone number using the details provided.
     * 
     * @param phoneNumber
     *            The phone number which is updated.
     * @return true if phone number was successfully updated, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean updatePhoneNumber(PhoneNumber phoneNumber) {

        ContentValues values = new ContentValues();

        if (!"".equals(phoneNumber.getPhoneNumberString())) {
            values.put(PhoneNumbersDbAccess.TABLE_KEY_PHONENUMBER_STRING,
                    phoneNumber.getPhoneNumberString());
        }

        String table = PhoneNumbersDbAccess.TABLE_NAME;
        String whereClause = PhoneNumbersDbAccess.TABLE_KEY_PHONENUMBER_ID + "=?";
        String[] whereArgs = new String[] { "" + phoneNumber.getId() };

        final int rowCnt = this.mDb.update(table, values, whereClause, whereArgs);

        return (rowCnt > 0);
    }
}
