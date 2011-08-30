/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.DataAccess.Providers;

import java.util.ArrayList;

import vn.s3corp.android.MobileMap.DataAccess.DataObjects.*;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.*;

/**
 * The basic interface for Mobile Map content providers.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public interface IContentProvider {

	/**
	 * Open the Mobile Map data provider.
	 * 
	 * @param
	 * @return this (self reference, allowing this to be chained in an
	 *         initialization call)
	 * @throws DataAccessException
	 *             The DataAccess component has unexpected errors
	 * @throws DatabaseException
	 *             There are errors in database
	 * @author nam.tran
	 * @version 0.1.0
	 * 
	 */
	IContentProvider open() throws DataAccessException, DatabaseException;

	/**
	 * Close the Moblie Map data provider.
	 * 
	 * @param
	 * @return
	 * @author nam.tran
	 * @version 0.1.0
	 * 
	 */
	void close();

    ArrayList<Comment> getAllPoiComments(long poiId) throws DatabaseException, SystemException;

	
	/**
	 * @param comment
	 *            The data of comment which needs to add
	 * @return true if comment was added successfully, false if not
	 * @throws InvalidDataException
	 *             The input data has some invalid value such as login name is
	 *             empty
	 * @throws DataAccessException
	 *             The processing breaks some business rules
	 * @throws DatabaseException
	 *             There are errors in database
	 * @throws SystemException
	 *             The DataAccess component has unexpected errors
	 * @author Cuong.Le
	 * @version 0.4.0
	 * 
	 */
	boolean addComment(Comment comment, long poiId) throws InvalidDataException,
			DataAccessException, DatabaseException, SystemException;

	/**
	 * Register a user with Mobile Map system. User must have a login name which
	 * is different from other users's one.
	 * 
	 * @param user
	 *            The data of user which needs register
	 * @return true if user was registered successfully, false if not
	 * @throws InvalidDataException
	 *             The input data has some invalid value such as login name is
	 *             empty
	 * @throws DataAccessException
	 *             The processing breaks some business rules
	 * @throws DatabaseException
	 *             There are errors in database
	 * @throws SystemException
	 *             The DataAccess component has unexpected errors
	 * @author nam.tran
	 * @version 0.1.0
	 * 
	 */
	boolean registerUser(User user) throws InvalidDataException,
			DataAccessException, DatabaseException, SystemException;

	/**
	 * Update user information
	 * 
	 * @param user
	 *            The data of user which needs update
	 * @return true if user was updated successfully, false if not
	 * @throws SystemException
	 *             The DataAccess component has unexpected errors
	 * @author anh.tran
	 * @version 0.1.0
	 * 
	 */
	boolean updateUser(User user) throws InvalidDataException,
			DataAccessException, DatabaseException, SystemException;

	/**
	 * Get user information
	 * 
	 * @param userId
	 *            the Id of user
	 * @return User object with its information
	 * @throws Exception
	 *             The DataAccess component has unexpected errors
	 * @author anh.tran
	 * @version 0.1.0
	 * 
	 */
	User getUser(long userId) throws InvalidDataException, DataAccessException,
			DatabaseException, SystemException;

	/**
	 * Process the user's login request with Mobile Map system. User must
	 * provide a login name and a password.
	 * 
	 * @param session
	 *            The session data which contains login information
	 * @return true if user login successfully, false if not
	 * @throws InvalidDataException
	 *             The input data has some invalid value such as login name is
	 *             empty
	 * @throws DataAccessException
	 *             The processing breaks some business rules
	 * @throws DatabaseException
	 *             There are errors in database
	 * @throws SystemException
	 *             The DataAccess component has unexpected errors
	 * @author nam.tran
	 * @version 0.1.0
	 * 
	 */
	boolean login(LoginSession session) throws InvalidDataException,
			DataAccessException, DatabaseException, SystemException;

	/**
	 * Process the user's logout request with Mobile Map system.
	 * 
	 * @param session
	 *            The session data which contains login information
	 * @return true if user logout successfully, false if not
	 * @throws InvalidDataException
	 *             The input data has some invalid value such as session id is
	 *             zero
	 * @throws DataAccessException
	 *             The processing breaks some business rules
	 * @throws DatabaseException
	 *             There are errors in database
	 * @throws SystemException
	 *             The DataAccess component has unexpected errors
	 * @author nam.tran
	 * @version 0.1.0
	 * 
	 */
	boolean logout(LoginSession session) throws InvalidDataException,
			DataAccessException, DatabaseException, SystemException;

	/**
	 * Get the list of all categories.
	 * 
	 * @param
	 * @return the list of all categories. An empty list if there is no data.
	 * @throws DatabaseException
	 *             There are errors in database
	 * @throws SystemException
	 *             The DataAccess component has unexpected errors
	 * @author nam.tran
	 * @version 0.1.0
	 * 
	 */
	ArrayList<Category> getAllPoiCategories() throws DatabaseException,
			SystemException;

	/**
	 * Add a POI to Mobile Map system. The POI must has a unique location.
	 * 
	 * @param poi
	 *            The data of POI which will be added
	 * @return true if POI was added successfully, false if not
	 * @throws InvalidDataException
	 *             The input data has some invalid value such as name is empty
	 * @throws DataAccessException
	 *             The processing breaks some business rules
	 * @throws DatabaseException
	 *             There are errors in database
	 * @throws SystemException
	 *             The DataAccess component has unexpected errors
	 * @author nam.tran
	 * @version 0.1.0
	 * 
	 */
	boolean addPoi(Poi poi) throws InvalidDataException, DataAccessException,
			DatabaseException, SystemException;

	/**
	 * Remove a POI from Mobile Map system.
	 * 
	 * @param poiId
	 *            The ID of POI which will be removed
	 * @return true if POI was removed successfully, false if not
	 * @throws InvalidDataException
	 *             The input data has some invalid value such as name is empty
	 * @throws DataAccessException
	 *             The processing breaks some business rules
	 * @throws DatabaseException
	 *             There are errors in database
	 * @throws SystemException
	 *             The DataAccess component has unexpected errors
	 * @author nam.tran
	 * @version 0.1.0
	 * 
	 */
	boolean removePoi(long poiId) throws InvalidDataException,
			DataAccessException, DatabaseException, SystemException;

	/**
	 * Update a existing POI in Mobile Map system.
	 * 
	 * @param poi
	 *            The data of POI which will be updated
	 * @return true if POI was updated successfully, false if not
	 * @throws InvalidDataException
	 *             The input data has some invalid value such as name is empty
	 * @throws DataAccessException
	 *             The processing breaks some business rules
	 * @throws DatabaseException
	 *             There are errors in database
	 * @throws SystemException
	 *             The DataAccess component has unexpected errors
	 * @author nam.tran
	 * @version 0.1.0
	 * 
	 */
	boolean updatePoi(Poi poi) throws InvalidDataException,
			DataAccessException, DatabaseException, SystemException;

	/**
	 * Get the list of all public POIs with simple information such as name,
	 * longitude, latitude, rating, category, etc....
	 * 
	 * @param
	 * @return the list of all public POIs. An empty list if there is no data.
	 * @throws DatabaseException
	 *             There are errors in database
	 * @throws SystemException
	 *             The DataAccess component has unexpected errors
	 * @author nam.tran
	 * @version 0.1.0
	 * 
	 */
	ArrayList<Poi> getAllPublicPoisSimple() throws DatabaseException,
			SystemException;

	/**
	 * Get the list of all private POIs of a specific user with simple
	 * information such as name, longitude, latitude, rating, category, etc....
	 * 
	 * @param userId
	 *            The ID of user
	 * @return the list of all private POIs of a specific user. An empty list if
	 *         there is no data.
	 * @throws InvalidDataException
	 *             The input data has some invalid value such as user's ID is
	 *             wrong
	 * @throws DatabaseException
	 *             There are errors in database
	 * @throws SystemException
	 *             The DataAccess component has unexpected errors
	 * @author nam.tran
	 * @version 0.1.0
	 * 
	 */
	ArrayList<Poi> getAllPrivatePoisSimple(long userId)
			throws InvalidDataException, DatabaseException, SystemException;

	/**
	 * Get the detail information of POI.
	 * 
	 * @param poiId
	 *            The ID of POI
	 * @return the detail information of POI. Null if there is no data.
	 * @throws InvalidDataException
	 *             The input data has some invalid value such as POI's ID is
	 *             wrong
	 * @throws DataAccessException
	 *             The processing breaks some business rules
	 * @throws DatabaseException
	 *             There are errors in database
	 * @throws SystemException
	 *             The DataAccess component has unexpected errors
	 * @author nam.tran
	 * @version 0.1.0
	 * 
	 */
	Poi getDetailPoiInformation(long poiId) throws InvalidDataException,
			DataAccessException, DatabaseException, SystemException;

	/**
	 * Check whether a POI is marked as favorite or not.
	 * 
	 * @param userId
	 *            The ID of user
	 * @param poiId
	 *            The ID of POI
	 * @return true if the POI is marked as favorite. false otherwise
	 * @throws InvalidDataException
	 *             The input data has some invalid value such as POI's ID is
	 *             wrong
	 * @throws DataAccessException
	 *             The processing breaks some business rules
	 * @throws DatabaseException
	 *             There are errors in database
	 * @throws SystemException
	 *             The DataAccess component has unexpected errors
	 * @author nam.tran
	 * @version 0.1.0
	 * 
	 */
	boolean isFavoritePoi(long userId, long poiId) throws InvalidDataException,
			DataAccessException, DatabaseException, SystemException;

	/**
	 * Mark a POI is a favorite one.
	 * 
	 * @param userId
	 *            The ID of user
	 * @param poiId
	 *            The ID of POI
	 * @return true if the POI is marked as favorite. false otherwise
	 * @throws InvalidDataException
	 *             The input data has some invalid value such as POI's ID is
	 *             wrong
	 * @throws DataAccessException
	 *             The processing breaks some business rules
	 * @throws DatabaseException
	 *             There are errors in database
	 * @throws SystemException
	 *             The DataAccess component has unexpected errors
	 * @author nam.tran
	 * @version 0.1.0
	 * 
	 */
	boolean makeFavoritePoi(long userId, long poiId)
			throws InvalidDataException, DataAccessException,
			DatabaseException, SystemException;

	/**
	 * Mark a POI is a non-favorite one.
	 * 
	 * @param userId
	 *            The ID of user
	 * @param poiId
	 *            The ID of POI
	 * @return true if the POI is marked as non-favorite. false otherwise
	 * @throws InvalidDataException
	 *             The input data has some invalid value such as POI's ID is
	 *             wrong
	 * @throws DataAccessException
	 *             The processing breaks some business rules
	 * @throws DatabaseException
	 *             There are errors in database
	 * @throws SystemException
	 *             The DataAccess component has unexpected errors
	 * @author nam.tran
	 * @version 0.1.0
	 * 
	 */
	boolean removeFavoritePoi(long userId, long poiId)
			throws InvalidDataException, DataAccessException,
			DatabaseException, SystemException;

	/**
	 * Search for POIs which match input options.
	 * 
	 * @param option
	 *            The search option
	 * @return the list of all private POIs which match input options. An empty
	 *         list if there is no data.
	 * @throws DataAccessException
	 *             The processing breaks some business rules
	 * @throws DatabaseException
	 *             There are errors in database
	 * @throws SystemException
	 *             The DataAccess component has unexpected errors
	 * @author nam.tran
	 * @version 0.1.0
	 * 
	 */
	ArrayList<Poi> searchForPois(SearchOption option)
			throws DataAccessException, DatabaseException, SystemException;

	public void makeUserRatingPOI(Rating rating, Poi poi);

	public Rating getRating(User user, long poiId);
    /**
     * Get the list of all POI's picture.
     * 
     * @param
     * @return the list of POI's picture. An empty list if there is no data.
     * @throws DatabaseException
     *             There are errors in database
     * @throws SystemException
     *             The DataAccess component has unexpected errors
     * @author hieu.ha
     * @version 0.4.0
     * 
     */
    ArrayList<Picture> getAllPictures() throws DatabaseException, SystemException;

    /**
     * Get the list of all POI's picture.
     * 
     * @param
     * @return the list of POI's picture. An empty list if there is no data.
     * @throws DatabaseException
     *             There are errors in database
     * @throws SystemException
     *             The DataAccess component has unexpected errors
     * @author hieu.ha
     * @version 0.4.0
     * 
     */
    ArrayList<Picture> getAllPoiPictures(long poiId) throws DatabaseException, SystemException;

    /**
     * Get the list of all POI's picture.
     * 
     * @param
     * @return the list of POI's picture. An empty list if there is no data.
     * @throws DatabaseException
     *             There are errors in database
     * @throws SystemException
     *             The DataAccess component has unexpected errors
     * @author hieu.ha
     * @version 0.4.0
     * 
     */

    ArrayList<Picture> getPicture(long pictureId) throws DatabaseException, SystemException;

    /**
     * Add a POI's Picture to Mobile Map system.
     * 
     * @param picture
     *            The data of Picture which will be added
     * @param poi
     *            POI' picture
     * @return true if Picture was added successfully, false if not
     * @throws DataAccessException
     *             The processing breaks some business rules
     * @throws DatabaseException
     *             There are errors in database
     * @throws SystemException
     *             The DataAccess component has unexpected errors
     * @throws InvalidDataException
     *             The input data has some invalid value such as POI's ID is
     *             wrong
     * @author hieu.ha
     * @version 0.4.0
     * 
     */
    boolean addPicture(Picture picture, Poi poi) throws InvalidDataException, DataAccessException,
            DatabaseException, SystemException;

    /**
     * Update a POI's Picture to Mobile Map system.
     * 
     * @param picture
     *            The data of Picture which will be updated
     * @param poi
     *            POI' picture
     * @return true if Picture was added successfully, false if not
     * @throws DataAccessException
     *             The processing breaks some business rules
     * @throws DatabaseException
     *             There are errors in database
     * @throws SystemException
     *             The DataAccess component has unexpected errors
     * @throws InvalidDataException
     *             The input data has some invalid value such as POI's ID is
     *             wrong
     * @author hieu.ha
     * @version 0.4.0
     * 
     */
    boolean updatePicture(Picture picture) throws InvalidDataException, DataAccessException,
            DatabaseException, SystemException;

    /**
     * Remove a Puctire from Mobile Map system.
     * 
     * @param pictureId
     *            The ID of Puctire which will be removed
     * @return true if Puctire was removed successfully, false if not
     * @throws InvalidDataException
     *             The input data has some invalid value such as name is empty
     * @throws DataAccessException
     *             The processing breaks some business rules
     * @throws DatabaseException
     *             There are errors in database
     * @throws SystemException
     *             The DataAccess component has unexpected errors
     * @author hieu.ha
     * @version 0.4.0
     * 
     */
    public boolean removePicture(long pictureId) throws InvalidDataException, DataAccessException,
            DatabaseException, SystemException;

    /**
     * Remove a Puctire from Mobile Map system.
     * 
     * @param pictureId
     *            The ID of Puctire which will be removed
     * @return true if Puctire was removed successfully, false if not
     * @throws InvalidDataException
     *             The input data has some invalid value such as name is empty
     * @throws DataAccessException
     *             The processing breaks some business rules
     * @throws DatabaseException
     *             There are errors in database
     * @throws SystemException
     *             The DataAccess component has unexpected errors
     * @author hieu.ha
     * @version 0.4.0
     * 
     */
    boolean removeAllPoiPictures(long poiId) throws InvalidDataException, DataAccessException,
            DatabaseException, SystemException;
}
