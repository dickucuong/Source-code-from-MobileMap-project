/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.DataAccess;

import java.util.ArrayList;

import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Category;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Comment;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.LoginSession;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Picture;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Poi;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Rating;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.SearchOption;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.User;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DataAccessException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DatabaseException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.InvalidDataException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.SystemException;
import vn.s3corp.android.MobileMap.DataAccess.Providers.IContentProvider;
import vn.s3corp.android.MobileMap.DataAccess.Providers.LocalContentProvider;
import android.content.Context;

/**
 * The content manager for Mobile Map data.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class ContentManager {

	private final Context mCtx;

	private IContentProvider mProvider;

	/**
	 * Constructor.
	 * 
	 * @param context
	 *            The Context which is provided to work with
	 * @return
	 * @author nam.tran
	 * @version 0.1.0
	 * 
	 */
	public ContentManager(Context context) {
		this.mCtx = context;
		this.mProvider = new LocalContentProvider(this.mCtx);
	}

	/**
	 * Open the Mobile Map data content manager.
	 * 
	 * @param
	 * @return true If the manager was opened successfully, false otherwise
	 * @throws DataAccessException
	 *             The DataAccess component has unexpected errors
	 * @throws DatabaseException
	 *             There are errors in database
	 * @author nam.tran
	 * @version 0.1.0
	 * 
	 */
	public boolean open() throws DataAccessException, DatabaseException {
		return (null != this.mProvider.open());
	}

	/**
	 * Close the Mobile Map data content manager.
	 * 
	 * @param
	 * @return
	 * @author nam.tran
	 * @version 0.1.0
	 * 
	 */
	public void close() {
		this.mProvider.close();
	}

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
	public boolean registerUser(User user) throws InvalidDataException,
			DataAccessException, DatabaseException, SystemException {
		return this.mProvider.registerUser(user);
	}

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
	public boolean updateUser(User user) throws InvalidDataException,
			DataAccessException, DatabaseException, SystemException {
		return this.mProvider.updateUser(user);
	}

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
	public User getUser(long userId) throws InvalidDataException,
			DataAccessException, DatabaseException, SystemException {
		return this.mProvider.getUser(userId);
	}

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
	public boolean login(LoginSession session) throws InvalidDataException,
			DataAccessException, DatabaseException, SystemException {
		return this.mProvider.login(session);
	}

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
	public boolean logout(LoginSession session) throws InvalidDataException,
			DataAccessException, DatabaseException, SystemException {
		return this.mProvider.logout(session);
	}

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
	public ArrayList<Category> getAllPoiCategories() throws DatabaseException,
			SystemException {
		return this.mProvider.getAllPoiCategories();
	}

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
	public boolean addPoi(Poi poi) throws InvalidDataException,
			DataAccessException, DatabaseException, SystemException {
		return this.mProvider.addPoi(poi);
	}

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
	public boolean removePoi(long poiId) throws InvalidDataException,
			DataAccessException, DatabaseException, SystemException {
		return this.mProvider.removePoi(poiId);
	}

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
	public boolean updatePoi(Poi poi) throws InvalidDataException,
			DataAccessException, DatabaseException, SystemException {
		return this.mProvider.updatePoi(poi);
	}

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
	public ArrayList<Poi> getAllPublicPoisSimple() throws DatabaseException,
			SystemException {
		return this.mProvider.getAllPublicPoisSimple();
	}

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
	public ArrayList<Poi> getAllPrivatePoisSimple(long userId)
			throws InvalidDataException, DatabaseException, SystemException {
		return this.mProvider.getAllPrivatePoisSimple(userId);
	}

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
	public Poi getDetailPoiInformation(long poiId) throws InvalidDataException,
			DataAccessException, DatabaseException, SystemException {
		return this.mProvider.getDetailPoiInformation(poiId);
	}

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
	public boolean isFavoritePoi(long userId, long poiId)
			throws InvalidDataException, DataAccessException,
			DatabaseException, SystemException {
		return this.mProvider.isFavoritePoi(userId, poiId);
	}

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
	public boolean makeFavoritePoi(long userId, long poiId)
			throws InvalidDataException, DataAccessException,
			DatabaseException, SystemException {
		return this.mProvider.makeFavoritePoi(userId, poiId);
	}

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
	public ArrayList<Picture> getAllPictures() throws DatabaseException,
			SystemException {
		return this.mProvider.getAllPictures();
	}

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
	public ArrayList<Picture> getAllPoiPictures(long poiId)
			throws DatabaseException, SystemException {
		return this.mProvider.getAllPoiPictures(poiId);
	}

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
	public ArrayList<Picture> getPicture(long pictureId)
			throws DatabaseException, SystemException {
		return this.mProvider.getPicture(pictureId);
	}

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
	public boolean removeFavoritePoi(long userId, long poiId)
			throws InvalidDataException, DataAccessException,
			DatabaseException, SystemException {
		return this.mProvider.removeFavoritePoi(userId, poiId);
	}

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
	public ArrayList<Poi> searchForPois(SearchOption option)
			throws DataAccessException, DatabaseException, SystemException {
		return this.mProvider.searchForPois(option);
	}

	public void makeUserRatingPOI(Rating rating, Poi poi) {
		this.mProvider.makeUserRatingPOI(rating, poi);
	}

	public Rating getRating(User user, long poiId) {
		return this.mProvider.getRating(user, poiId);
	}

	public ArrayList<Comment> getAllPoiComments(long poiId)
			throws DatabaseException, SystemException {
		return this.mProvider.getAllPoiComments(poiId);
	}

	/**
	 * @author Cuong.Le
	 * @version 0.4.0
	 * 
	 */
	public boolean addCommet(Comment comment, long poiId)
			throws InvalidDataException, DataAccessException,
			DatabaseException, SystemException {
		return this.mProvider.addComment(comment, poiId);
	}

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
	public boolean addPicture(Picture picture, Poi poi)
			throws InvalidDataException, DataAccessException,
			DatabaseException, SystemException {
		return this.mProvider.addPicture(picture, poi);
	}

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
	public boolean updatePicture(Picture picture) throws InvalidDataException,
			DataAccessException, DatabaseException, SystemException {
		return this.mProvider.updatePicture(picture);
	}

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
	public boolean removePicture(long pictureId) throws InvalidDataException,
			DataAccessException, DatabaseException, SystemException {
		return this.mProvider.removePicture(pictureId);
	}

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
	public boolean removeAllPoiPictures(long poiId)
			throws InvalidDataException, DataAccessException,
			DatabaseException, SystemException {
		return this.mProvider.removeAllPoiPictures(poiId);
	}
}
