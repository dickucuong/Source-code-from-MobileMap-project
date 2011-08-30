/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.DataAccess.DbAdapters;

import vn.s3corp.android.MobileMap.DataAccess.DataObjects.LoginSession;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * The local database adapter for 'login_sessions' table.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class LoginSessionDbAccess {

    public static final String TABLE_NAME = "login_sessions";

    public static final String TABLE_KEY_SESSION_ID = "_id";
    public static final String TABLE_KEY_USER_ID = "user_id";
    public static final String TABLE_KEY_LOGIN_DATETIME = "login_datetime";
    public static final String TABLE_KEY_LOGOUT_DATETIME = "logout_datetime";
    public static final String TABLE_KEY_IS_CLOSED = "is_closed";

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
    public LoginSessionDbAccess(SQLiteDatabase db) {
        this.mDb = db;
    }

    /**
     * Create a new session value in Mobile Map database. If the session is
     * successfully created, this method will return the new rowId value,
     * otherwise return a '-1' value to indicate failure.
     * 
     * @param session
     *            The data of input session
     * @return The session ID or -1 if failed.
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long createSession(LoginSession session) {

        ContentValues values = new ContentValues();

        values.put(LoginSessionDbAccess.TABLE_KEY_USER_ID, session.getUser().getId());
        values.put(LoginSessionDbAccess.TABLE_KEY_LOGIN_DATETIME, session.getLoginDate().getTime());
        values.put(LoginSessionDbAccess.TABLE_KEY_IS_CLOSED, session.isClosed());

        return this.mDb.insert(LoginSessionDbAccess.TABLE_NAME, null, values);
    }

    /**
     * Delete session with the given session ID.
     * 
     * @param sessionId
     *            The ID of session which is deleted
     * @return true if user is deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deleteSession(long sessionId) {

        String table = LoginSessionDbAccess.TABLE_NAME;
        String whereClause = LoginSessionDbAccess.TABLE_KEY_SESSION_ID + "=?";
        String[] whereArgs = new String[] { "" + sessionId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Get a Cursor of a list of all sessions in the database.
     * 
     * @param
     * @return Cursor over all sessions, null if no session could be found or
     *         retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllSessions() {

        String table = LoginSessionDbAccess.TABLE_NAME;
        String[] columns = new String[] { LoginSessionDbAccess.TABLE_KEY_SESSION_ID,
                LoginSessionDbAccess.TABLE_KEY_USER_ID,
                LoginSessionDbAccess.TABLE_KEY_LOGIN_DATETIME,
                LoginSessionDbAccess.TABLE_KEY_LOGOUT_DATETIME,
                LoginSessionDbAccess.TABLE_KEY_IS_CLOSED };
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
     * Get a Cursor of a list of all user's sessions in the database
     * 
     * @param userId
     *            The user's ID
     * @return Cursor over all user's sessions, null if no session could be
     *         found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllUserSessions(long userId) {

        String table = LoginSessionDbAccess.TABLE_NAME;
        String[] columns = new String[] { LoginSessionDbAccess.TABLE_KEY_SESSION_ID,
                LoginSessionDbAccess.TABLE_KEY_USER_ID,
                LoginSessionDbAccess.TABLE_KEY_LOGIN_DATETIME,
                LoginSessionDbAccess.TABLE_KEY_LOGOUT_DATETIME,
                LoginSessionDbAccess.TABLE_KEY_IS_CLOSED };
        String selection = LoginSessionDbAccess.TABLE_KEY_USER_ID + "=?";
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
     * Get a Cursor which is positioned at the session that has the the given id
     * 
     * @param sessionId
     *            The session's id
     * @return The cursor which is positioned to matching one, null if no
     *         session could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchSession(long sessionId) {

        String table = LoginSessionDbAccess.TABLE_NAME;
        String[] columns = new String[] { LoginSessionDbAccess.TABLE_KEY_SESSION_ID,
                LoginSessionDbAccess.TABLE_KEY_USER_ID,
                LoginSessionDbAccess.TABLE_KEY_LOGIN_DATETIME,
                LoginSessionDbAccess.TABLE_KEY_LOGOUT_DATETIME,
                LoginSessionDbAccess.TABLE_KEY_IS_CLOSED };
        String selection = LoginSessionDbAccess.TABLE_KEY_SESSION_ID + "=?";
        String[] selectionArgs = new String[] { "" + sessionId };
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
     * Update session using the details provided.
     * 
     * @param session
     *            The session which is updated.
     * @return true if session was successfully updated, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean updateSession(LoginSession session) {

        ContentValues values = new ContentValues();

        values.put(LoginSessionDbAccess.TABLE_KEY_LOGOUT_DATETIME, session.getLogoutDate()
                .getTime());
        values.put(LoginSessionDbAccess.TABLE_KEY_IS_CLOSED, session.isClosed());

        String table = LoginSessionDbAccess.TABLE_NAME;
        String whereClause = LoginSessionDbAccess.TABLE_KEY_SESSION_ID + "=?" + " AND "
                + LoginSessionDbAccess.TABLE_KEY_IS_CLOSED + "=?";
        String[] whereArgs = new String[] { "" + session.getId(), "false" };

        final int rowCnt = this.mDb.update(table, values, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Update all user's opening sessions using the details provided.
     * 
     * @param session
     *            The session which is updated.
     * @return true if sessions were successfully updated, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean updateAllUserOpenSessions(LoginSession session) {

        ContentValues values = new ContentValues();

        values.put(LoginSessionDbAccess.TABLE_KEY_LOGOUT_DATETIME, session.getLogoutDate()
                .getTime());
        values.put(LoginSessionDbAccess.TABLE_KEY_IS_CLOSED, "true");

        String table = LoginSessionDbAccess.TABLE_NAME;
        String whereClause = LoginSessionDbAccess.TABLE_KEY_USER_ID + "=?" + " AND "
                + LoginSessionDbAccess.TABLE_KEY_IS_CLOSED + "=?";
        String[] whereArgs = new String[] { "" + session.getUser().getId(), "false" };

        final int rowCnt = mDb.update(table, values, whereClause, whereArgs);

        return (rowCnt > 0);
    }
}
