/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.UI;

import vn.s3corp.android.MobileMap.DataAccess.ContentManager;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.LoginSession;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DataAccessException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DatabaseException;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * The main application of Mobile Map client.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class MobileMapApp extends Application {

    private static final String SHARED_DATA_SESSION_ID = "sessionId";
    private static final String SHARED_DATA_USER_ID = "userId";
    private static final String SHARED_DATA_LOGINNAME = "loginname";

    private ContentManager mManager = null;
    private SharedPreferences mSettings = null;

    /**
     * Get the content manager object which is used to access Mobile Map data.
     * This method will initialize the manager if needed.
     * 
     * 
     * @param
     * @return The content manager object which is used to access Mobile Map
     *         data
     * @throws DataAccessException
     *             The DataAccess component has unexpected errors
     * @throws DatabaseException
     *             There are errors in database
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public ContentManager getContentManager() throws DataAccessException, DatabaseException {

        if (null == this.mManager) {
            ContentManager manager = new ContentManager(this);

            if (manager.open()) {
                this.mManager = manager;
            }
        }

        return this.mManager;
    }

    /**
     * Save session data into the application's SharedPreferences.
     * 
     * @param session
     *            The session data
     * @return true if session data was saved successfully
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean saveSession(LoginSession session) {

        if (null == this.mSettings) {
            this.mSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        }
        SharedPreferences.Editor editor = this.mSettings.edit();

        editor.putLong(MobileMapApp.SHARED_DATA_SESSION_ID, session.getId());
        editor.putLong(MobileMapApp.SHARED_DATA_USER_ID, session.getUser().getId());
        editor.putString(MobileMapApp.SHARED_DATA_LOGINNAME, session.getUser().getLoginname());

        editor.commit();

        return true;
    }

    /**
     * Get session data which was saved in the application's SharedPreferences.
     * 
     * @param
     * @return The session data, null value if there is no session data or the
     *         data is wrong.
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public LoginSession getSession() {

        if (null == this.mSettings) {
            this.mSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        }

        long sessionId = this.mSettings.getLong(MobileMapApp.SHARED_DATA_SESSION_ID, 0);

        if (0 < sessionId) {
            LoginSession session = new LoginSession();

            session.setId(sessionId);
            session.getUser().setId(this.mSettings.getLong(MobileMapApp.SHARED_DATA_USER_ID, 0));
            session.getUser().setLoginname(
                    this.mSettings.getString(MobileMapApp.SHARED_DATA_LOGINNAME, ""));

            return session;
        } else {
            return null;
        }
    }

    /**
     * Remove old session data.
     * 
     * @param
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void removeSession() {

        if (null == this.mSettings) {
            this.mSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        }
        SharedPreferences.Editor editor = this.mSettings.edit();

        editor.remove(MobileMapApp.SHARED_DATA_SESSION_ID);
        editor.remove(MobileMapApp.SHARED_DATA_USER_ID);
        editor.remove(MobileMapApp.SHARED_DATA_LOGINNAME);

        editor.commit();
    }

    /**
     * Check the login status of current user.
     * 
     * @param
     * @return true if application is being used by a login user, false
     *         otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean checkLoginStatus() {

        if (null == this.mSettings) {
            this.mSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        }

        long sessionId = this.mSettings.getLong(MobileMapApp.SHARED_DATA_SESSION_ID, 0);

        return (0 < sessionId);
    }

    /**
     * Get the ID of current login user.
     * 
     * @param
     * @return The user's ID. 0 if there is no login user
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long getLoginUserId() {

        if (null == this.mSettings) {
            this.mSettings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        }

        return this.mSettings.getLong(MobileMapApp.SHARED_DATA_USER_ID, 0);
    }
}
