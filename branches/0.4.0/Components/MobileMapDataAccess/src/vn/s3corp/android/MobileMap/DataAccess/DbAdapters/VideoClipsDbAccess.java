/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.DataAccess.DbAdapters;

import vn.s3corp.android.MobileMap.DataAccess.DataObjects.VideoClip;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * The local database adapter for 'video_clips' table.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class VideoClipsDbAccess {

    public static final String TABLE_NAME = "video_clips";

    public static final String TABLE_KEY_VIDEO_CLIP_ID = "_id";
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
    public VideoClipsDbAccess(SQLiteDatabase db) {
        this.mDb = db;
    }

    /**
     * Create a new video clip value in Mobile Map database. If the video clip
     * is successfully created, this method will return the new rowId value,
     * otherwise return a '-1' value to indicate failure.
     * 
     * @param videoClip
     *            The data of input video clip
     * @param poiId
     *            The ID of POI which video clip is belong to
     * @return The video clip's ID or -1 if failed.
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long createVideoClip(VideoClip videoClip, long poiId) {

        ContentValues values = new ContentValues();

        values.put(VideoClipsDbAccess.TABLE_KEY_POI_ID, poiId);
        values.put(VideoClipsDbAccess.TABLE_KEY_LOCATION, videoClip.getLocation());
        values.put(VideoClipsDbAccess.TABLE_KEY_DESCRIPTIONS, videoClip.getDescription());
        values.put(VideoClipsDbAccess.TABLE_KEY_IS_BAD_REPORTED, videoClip.isBadReport());
        values.put(VideoClipsDbAccess.TABLE_KEY_IS_ACTIVATED, videoClip.isActivated());

        return this.mDb.insert(VideoClipsDbAccess.TABLE_NAME, null, values);
    }

    /**
     * Delete video clip with the given id.
     * 
     * @param videoClipId
     *            The ID of video clip which is deleted
     * @return true if video clip is deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deleteVideoClip(long videoClipId) {

        String table = VideoClipsDbAccess.TABLE_NAME;
        String whereClause = VideoClipsDbAccess.TABLE_KEY_VIDEO_CLIP_ID + "=?";
        String[] whereArgs = new String[] { "" + videoClipId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Delete all video clips which are belong to a specific POI.
     * 
     * @param poiId
     *            The ID of POI
     * @return true if video clips are deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deleteAllPoiVideoClips(long poiId) {

        String table = VideoClipsDbAccess.TABLE_NAME;
        String whereClause = VideoClipsDbAccess.TABLE_KEY_POI_ID + "=?";
        String[] whereArgs = new String[] { "" + poiId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Get a Cursor of a list of all video clips in the database.
     * 
     * @param
     * @return Cursor over all video clips
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllVideoClips() {

        String table = VideoClipsDbAccess.TABLE_NAME;
        String[] columns = new String[] { VideoClipsDbAccess.TABLE_KEY_VIDEO_CLIP_ID,
                VideoClipsDbAccess.TABLE_KEY_POI_ID, VideoClipsDbAccess.TABLE_KEY_LOCATION,
                VideoClipsDbAccess.TABLE_KEY_DESCRIPTIONS,
                VideoClipsDbAccess.TABLE_KEY_IS_BAD_REPORTED,
                VideoClipsDbAccess.TABLE_KEY_IS_ACTIVATED };
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
     * Get a Cursor which is positioned at the video clip that matches the given
     * id.
     * 
     * @param videoClipId
     *            The video clip's ID
     * @return The cursor which is positioned to matching one, null if no video
     *         clip could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchVideoClip(long videoClipId) {

        String table = VideoClipsDbAccess.TABLE_NAME;
        String[] columns = new String[] { VideoClipsDbAccess.TABLE_KEY_VIDEO_CLIP_ID,
                VideoClipsDbAccess.TABLE_KEY_POI_ID, VideoClipsDbAccess.TABLE_KEY_LOCATION,
                VideoClipsDbAccess.TABLE_KEY_DESCRIPTIONS,
                VideoClipsDbAccess.TABLE_KEY_IS_BAD_REPORTED,
                VideoClipsDbAccess.TABLE_KEY_IS_ACTIVATED };
        String selection = VideoClipsDbAccess.TABLE_KEY_VIDEO_CLIP_ID + "=?";
        String[] selectionArgs = new String[] { "" + videoClipId };
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
     * Get a Cursor which is positioned at all the video clips of a POI.
     * 
     * @param poiId
     *            The POI's ID
     * @return The cursor which is positioned to matching one, null if no video
     *         clips could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllPoiVideoClips(long poiId) {

        String table = VideoClipsDbAccess.TABLE_NAME;
        String[] columns = new String[] { VideoClipsDbAccess.TABLE_KEY_VIDEO_CLIP_ID,
                VideoClipsDbAccess.TABLE_KEY_POI_ID, VideoClipsDbAccess.TABLE_KEY_LOCATION,
                VideoClipsDbAccess.TABLE_KEY_DESCRIPTIONS,
                VideoClipsDbAccess.TABLE_KEY_IS_BAD_REPORTED,
                VideoClipsDbAccess.TABLE_KEY_IS_ACTIVATED };
        String selection = VideoClipsDbAccess.TABLE_KEY_POI_ID + "=?";
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
     * Update video clip using the details provided.
     * 
     * @param videoClip
     *            The video clip which is updated.
     * @return true if video clip was successfully updated, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean updateVideoClip(VideoClip videoClip) {

        ContentValues values = new ContentValues();

        if (!"".equals(videoClip.getLocation())) {
            values.put(VideoClipsDbAccess.TABLE_KEY_LOCATION, videoClip.getLocation());
        }
        if (!"".equals(videoClip.getDescription())) {
            values.put(VideoClipsDbAccess.TABLE_KEY_DESCRIPTIONS, videoClip.getDescription());
        }
        values.put(VideoClipsDbAccess.TABLE_KEY_IS_BAD_REPORTED, videoClip.isBadReport());
        values.put(VideoClipsDbAccess.TABLE_KEY_IS_ACTIVATED, videoClip.isActivated());

        String table = VideoClipsDbAccess.TABLE_NAME;
        String whereClause = VideoClipsDbAccess.TABLE_KEY_VIDEO_CLIP_ID + "=?";
        String[] whereArgs = new String[] { "" + videoClip.getId() };

        final int rowCnt = this.mDb.update(table, values, whereClause, whereArgs);

        return (rowCnt > 0);
    }
}
