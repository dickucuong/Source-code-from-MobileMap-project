/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.DataAccess.DbAdapters;

import java.text.SimpleDateFormat;
import java.util.Date;

import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Comment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * The local database adapter for 'comments' table.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class CommentsDbAccess {

    public static final String TABLE_NAME = "comments";

    public static final String TABLE_KEY_COMMENT_ID = "_id";
    public static final String TABLE_KEY_USER_ID = "user_id";
    public static final String TABLE_KEY_POI_ID = "poi_id";
    public static final String TABLE_KEY_COMMENT_STRING = "comment";
    public static final String TABLE_KEY_IS_BAD_REPORTED = "is_badreported";
    public static final String TABLE_KEY_CREATION_DATETIME = "creation_datetime";
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
    public CommentsDbAccess(SQLiteDatabase db) {
        this.mDb = db;
    }

    /**
     * Create a new comment value in Mobile Map database. If the comment is
     * successfully created, this method will return the new rowId value,
     * otherwise return a '-1' value to indicate failure.
     * 
     * @param comment
     *            The data of input comment
     * @param poiId
     *            The ID of POI which comment is belong to
     * @return The comment's ID or -1 if failed.
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long createComment(Comment comment, long poiId) {

        ContentValues values = new ContentValues();

        values.put(CommentsDbAccess.TABLE_KEY_USER_ID, comment.getCreationUser().getId());
        values.put(CommentsDbAccess.TABLE_KEY_POI_ID, poiId);
        values.put(CommentsDbAccess.TABLE_KEY_COMMENT_STRING, comment.getCommentString());
        values.put(CommentsDbAccess.TABLE_KEY_IS_BAD_REPORTED, comment.isBadReport());
        values.put(CommentsDbAccess.TABLE_KEY_CREATION_DATETIME, comment.getCreationDate().getTime());
        values.put(CommentsDbAccess.TABLE_KEY_IS_ACTIVATED, comment.isActivated());

        return this.mDb.insert(CommentsDbAccess.TABLE_NAME, null, values);
    }

    /**
     * Delete comment with the given id.
     * 
     * @param commentId
     *            The ID of comment which is deleted
     * @return true if comment is deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deleteComment(long commentId) {

        String table = CommentsDbAccess.TABLE_NAME;
        String whereClause = CommentsDbAccess.TABLE_KEY_COMMENT_ID + "=?";
        String[] whereArgs = new String[] { "" + commentId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Delete all comments which are belong to a specific user.
     * 
     * @param userId
     *            The ID of user
     * @return true if comments are deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deleteAllUserComments(long userId) {

        String table = CommentsDbAccess.TABLE_NAME;
        String whereClause = CommentsDbAccess.TABLE_KEY_USER_ID + "=?";
        String[] whereArgs = new String[] { "" + userId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Delete all comments which are belong to a specific POI.
     * 
     * @param poiId
     *            The ID of POI
     * @return true if comments are deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deleteAllPoiComments(long poiId) {

        String table = CommentsDbAccess.TABLE_NAME;
        String whereClause = CommentsDbAccess.TABLE_KEY_POI_ID + "=?";
        String[] whereArgs = new String[] { "" + poiId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Get a Cursor of a list of all comments in the database.
     * 
     * @param
     * @return Cursor over all comments
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllComments() {

        String table = CommentsDbAccess.TABLE_NAME;
        String[] columns = new String[] { CommentsDbAccess.TABLE_KEY_COMMENT_ID,
                CommentsDbAccess.TABLE_KEY_USER_ID, CommentsDbAccess.TABLE_KEY_POI_ID,
                CommentsDbAccess.TABLE_KEY_COMMENT_STRING,
                CommentsDbAccess.TABLE_KEY_IS_BAD_REPORTED,
                CommentsDbAccess.TABLE_KEY_CREATION_DATETIME,
                CommentsDbAccess.TABLE_KEY_IS_ACTIVATED };
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
     * Get a Cursor which is positioned at the comment that matches the given
     * id.
     * 
     * @param commentId
     *            The comment's ID
     * @return The cursor which is positioned to matching one, null if no
     *         comment could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchComment(long commentId) {

        String table = CommentsDbAccess.TABLE_NAME;
        String[] columns = new String[] { CommentsDbAccess.TABLE_KEY_COMMENT_ID,
                CommentsDbAccess.TABLE_KEY_USER_ID, CommentsDbAccess.TABLE_KEY_POI_ID,
                CommentsDbAccess.TABLE_KEY_COMMENT_STRING,
                CommentsDbAccess.TABLE_KEY_IS_BAD_REPORTED,
                CommentsDbAccess.TABLE_KEY_CREATION_DATETIME,
                CommentsDbAccess.TABLE_KEY_IS_ACTIVATED };
        String selection = CommentsDbAccess.TABLE_KEY_COMMENT_ID + "=?";
        String[] selectionArgs = new String[] { "" + commentId };
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
     * Get a Cursor which is positioned at all the comments of a user.
     * 
     * @param userId
     *            The user's ID
     * @return The cursor which is positioned to matching one, null if no
     *         comment could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllUserComments(long userId) {

        String table = CommentsDbAccess.TABLE_NAME;
        String[] columns = new String[] { CommentsDbAccess.TABLE_KEY_COMMENT_ID,
                CommentsDbAccess.TABLE_KEY_USER_ID, CommentsDbAccess.TABLE_KEY_POI_ID,
                CommentsDbAccess.TABLE_KEY_COMMENT_STRING,
                CommentsDbAccess.TABLE_KEY_IS_BAD_REPORTED,
                CommentsDbAccess.TABLE_KEY_CREATION_DATETIME,
                CommentsDbAccess.TABLE_KEY_IS_ACTIVATED };
        String selection = CommentsDbAccess.TABLE_KEY_USER_ID + "=?";
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
     * Get a Cursor which is positioned at all the comments of a POI.
     * 
     * @param poiId
     *            The POI's ID
     * @return The cursor which is positioned to matching one, null if no
     *         comment could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllPoiComments(long poiId) {

        String table = CommentsDbAccess.TABLE_NAME;
        String[] columns = new String[] { CommentsDbAccess.TABLE_KEY_COMMENT_ID,
                CommentsDbAccess.TABLE_KEY_USER_ID, CommentsDbAccess.TABLE_KEY_POI_ID,
                CommentsDbAccess.TABLE_KEY_COMMENT_STRING,
                CommentsDbAccess.TABLE_KEY_IS_BAD_REPORTED,
                CommentsDbAccess.TABLE_KEY_CREATION_DATETIME,
                CommentsDbAccess.TABLE_KEY_IS_ACTIVATED };
        String selection = CommentsDbAccess.TABLE_KEY_POI_ID + "=?";
        String[] selectionArgs = new String[] { "" + poiId };
        String groupBy = null;
        String having = null;
        String orderBy = "_id DESC";

        Cursor cursor = this.mDb.query(table, columns, selection, selectionArgs, groupBy, having,
                orderBy);
        if (null != cursor) {
            cursor.moveToFirst();
        }

        return cursor;
    }

    /**
     * Update comment using the details provided.
     * 
     * @param comment
     *            The comment which is updated.
     * @return true if comment was successfully updated, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean updateComment(Comment comment) {

        ContentValues values = new ContentValues();

        if (!"".equals(comment.getCommentString())) {
            values.put(CommentsDbAccess.TABLE_KEY_COMMENT_STRING, comment.getCommentString());
        }
        values.put(CommentsDbAccess.TABLE_KEY_IS_BAD_REPORTED, comment.isBadReport());
        values.put(CommentsDbAccess.TABLE_KEY_IS_ACTIVATED, comment.isActivated());

        String table = CommentsDbAccess.TABLE_NAME;
        String whereClause = CommentsDbAccess.TABLE_KEY_COMMENT_ID + "=?";
        String[] whereArgs = new String[] { "" + comment.getId() };

        final int rowCnt = this.mDb.update(table, values, whereClause, whereArgs);

        return (rowCnt > 0);
    }
    /**
     * @author Cuong.Le
     * @version 0.4.0
     */
	public long addComment(Comment comment) {
		ContentValues values = new ContentValues();
		values.put(CommentsDbAccess.TABLE_KEY_COMMENT_STRING, comment.getCommentString());
		values.put(CommentsDbAccess.TABLE_KEY_POI_ID, comment.getPoiId());
		values.put(CommentsDbAccess.TABLE_KEY_USER_ID, comment.getCreationUser().getId());
		values.put(CommentsDbAccess.TABLE_KEY_CREATION_DATETIME, (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(new Date()));
		values.put(CommentsDbAccess.TABLE_KEY_IS_ACTIVATED, comment.isActivated());
		values.put(CommentsDbAccess.TABLE_KEY_IS_BAD_REPORTED, comment.isBadReport());
		return this.mDb.insert(CommentsDbAccess.TABLE_NAME, null, values);
	}
}
