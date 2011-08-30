/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.DataAccess.DataObjects;

import java.util.Date;

/**
 * The Login Session object in Mobile Map system.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class LoginSession {

    private long mId;
    private User mUser;
    private Date mLoginDate;
    private Date mLogoutDate;
    private boolean mIsClosed;

    /**
     * Get the session's ID.
     * 
     * @param
     * @return The session's ID
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long getId() {
        return this.mId;
    }

    /**
     * Set the session's ID.
     * 
     * @param id
     *            The session's ID
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setId(long id) {
        this.mId = id;
    }

    /**
     * Get the user's data.
     * 
     * @param
     * @return The users's data
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public User getUser() {
        return this.mUser;
    }

    /**
     * Set the user's data.
     * 
     * @param id
     *            The user's data
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setUser(User user) {
        this.mUser = user;
    }

    /**
     * Get the login date.
     * 
     * @param
     * @return The login date
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Date getLoginDate() {
        return this.mLoginDate;
    }

    /**
     * Set the login date.
     * 
     * @param loginDate
     *            The login date
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setLoginDate(Date loginDate) {
        this.mLoginDate = loginDate;
    }

    /**
     * Get the logout date.
     * 
     * @param
     * @return The logout date
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Date getLogoutDate() {
        return this.mLogoutDate;
    }

    /**
     * Set the logout date.
     * 
     * @param logoutDate
     *            The logout date
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setLogoutDate(Date logoutDate) {
        this.mLogoutDate = logoutDate;
    }

    /**
     * Check whether the Session was closed or not.
     * 
     * @param
     * @return true if session was closed, false if not
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean isClosed() {
        return this.mIsClosed;
    }

    /**
     * Set the Closed value.
     * 
     * @param isClosed
     *            The Closed value
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public void setClosed(boolean isClosed) {
        this.mIsClosed = isClosed;
    }

    /**
     * Default constructor.
     * 
     * @param
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public LoginSession() {
        this.mId = 0;
        this.mUser = new User();
        this.mLoginDate = new Date(0L);
        this.mLogoutDate = null;
        this.mIsClosed = false;
    }

    /**
     * Copy constructor.
     * 
     * @param session
     *            The Login Session object
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public LoginSession(LoginSession session) {
        if (null != session) {
            this.mId = session.getId();
            this.mUser = session.getUser();
            this.mLoginDate = session.getLoginDate();
            this.mLogoutDate = session.getLogoutDate();
            this.mIsClosed = session.isClosed();
        } else {
            this.mId = 0;
            this.mUser = new User();
            this.mLoginDate = new Date(0L);
            this.mLogoutDate = null;
            this.mIsClosed = false;
        }
    }
}
