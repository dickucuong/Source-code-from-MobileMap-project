/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.DataAccess.DbAdapters;

import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Email;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * The local database adapter for 'emails' table.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class EmailsDbAccess {

    public static final String TABLE_NAME = "emails";

    public static final String TABLE_KEY_EMAIL_ID = "_id";
    public static final String TABLE_KEY_USER_ID = "user_id";
    public static final String TABLE_KEY_POI_ID = "poi_id";
    public static final String TABLE_KEY_EMAIL_STRING = "email_string";

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
    public EmailsDbAccess(SQLiteDatabase db) {
        this.mDb = db;
    }

    /**
     * Create a new email value in Mobile Map database. If the email is
     * successfully created, this method will return the new rowId value,
     * otherwise return a '-1' value to indicate failure.
     * 
     * @param email
     *            The data of input email
     * @param userId
     *            The ID of user which email is belong to
     * @param poiId
     *            The ID of POI which email is belong to
     * @return The email's ID or -1 if failed.
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long createEmail(Email email, long userId, long poiId) {

        ContentValues values = new ContentValues();

        values.put(EmailsDbAccess.TABLE_KEY_USER_ID, userId);
        values.put(EmailsDbAccess.TABLE_KEY_POI_ID, poiId);
        values.put(EmailsDbAccess.TABLE_KEY_EMAIL_STRING, email.getEmailString());

        return this.mDb.insert(EmailsDbAccess.TABLE_NAME, null, values);
    }

    /**
     * Delete email with the given id.
     * 
     * @param emailId
     *            The ID of email which is deleted
     * @return true if email is deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deleteEmail(long emailId) {

        String table = EmailsDbAccess.TABLE_NAME;
        String whereClause = EmailsDbAccess.TABLE_KEY_EMAIL_ID + "=?";
        String[] whereArgs = new String[] { "" + emailId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Delete all emails which are belong to a specific user.
     * 
     * @param userId
     *            The ID of user
     * @return true if emails are deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deleteAllUserEmails(long userId) {

        String table = EmailsDbAccess.TABLE_NAME;
        String whereClause = EmailsDbAccess.TABLE_KEY_USER_ID + "=?";
        String[] whereArgs = new String[] { "" + userId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Delete all emails which are belong to a specific POI.
     * 
     * @param poiId
     *            The ID of POI
     * @return true if emails are deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deleteAllPoiEmails(long poiId) {

        String table = EmailsDbAccess.TABLE_NAME;
        String whereClause = EmailsDbAccess.TABLE_KEY_POI_ID + "=?";
        String[] whereArgs = new String[] { "" + poiId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Get a Cursor of a list of all emails in the database.
     * 
     * @param
     * @return Cursor over all emails
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllEmails() {

        String table = EmailsDbAccess.TABLE_NAME;
        String[] columns = new String[] { EmailsDbAccess.TABLE_KEY_EMAIL_ID,
                EmailsDbAccess.TABLE_KEY_USER_ID, EmailsDbAccess.TABLE_KEY_POI_ID,
                EmailsDbAccess.TABLE_KEY_EMAIL_STRING };
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
     * Get a Cursor which is positioned at the email that matches the given id.
     * 
     * @param emailId
     *            The email's ID
     * @return The cursor which is positioned to matching one, null if no email
     *         could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchEmail(long emailId) {

        String table = EmailsDbAccess.TABLE_NAME;
        String[] columns = new String[] { EmailsDbAccess.TABLE_KEY_EMAIL_ID,
                EmailsDbAccess.TABLE_KEY_USER_ID, EmailsDbAccess.TABLE_KEY_POI_ID,
                EmailsDbAccess.TABLE_KEY_EMAIL_STRING };
        String selection = EmailsDbAccess.TABLE_KEY_EMAIL_ID + "=?";
        String[] selectionArgs = new String[] { "" + emailId };
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
     * Get a Cursor which is positioned at the emails of a user.
     * 
     * @param userId
     *            The user's ID
     * @return The cursor which is positioned to matching one, null if no email
     *         could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllUserEmails(long userId) {

        String table = EmailsDbAccess.TABLE_NAME;
        String[] columns = new String[] { EmailsDbAccess.TABLE_KEY_EMAIL_ID,
                EmailsDbAccess.TABLE_KEY_USER_ID, EmailsDbAccess.TABLE_KEY_POI_ID,
                EmailsDbAccess.TABLE_KEY_EMAIL_STRING };
        String selection = EmailsDbAccess.TABLE_KEY_USER_ID + "=?";
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
     * Get a Cursor which is positioned at the emails of a POI.
     * 
     * @param poiId
     *            The POI's ID
     * @return The cursor which is positioned to matching one, null if no email
     *         could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllPoiEmails(long poiId) {

        String table = EmailsDbAccess.TABLE_NAME;
        String[] columns = new String[] { EmailsDbAccess.TABLE_KEY_EMAIL_ID,
                EmailsDbAccess.TABLE_KEY_USER_ID, EmailsDbAccess.TABLE_KEY_POI_ID,
                EmailsDbAccess.TABLE_KEY_EMAIL_STRING };
        String selection = EmailsDbAccess.TABLE_KEY_POI_ID + "=?";
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
     * Update email using the details provided.
     * 
     * @param email
     *            The email which is updated.
     * @return true if email was successfully updated, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean updateEmail(Email email) {

        ContentValues values = new ContentValues();

        if (!"".equals(email.getEmailString())) {
            values.put(EmailsDbAccess.TABLE_KEY_EMAIL_STRING, email.getEmailString());
        }

        String table = EmailsDbAccess.TABLE_NAME;
        String whereClause = EmailsDbAccess.TABLE_KEY_EMAIL_ID + "=?";
        String[] whereArgs = new String[] { "" + email.getId() };

        final int rowCnt = this.mDb.update(table, values, whereClause, whereArgs);

        return (rowCnt > 0);
    }
}
