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
import java.util.Date;

import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Address;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Category;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Comment;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Email;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.LoginSession;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.PhoneNumber;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Picture;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Poi;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Rating;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.SearchOption;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.User;
import vn.s3corp.android.MobileMap.DataAccess.DbAdapters.AddressesDbAccess;
import vn.s3corp.android.MobileMap.DataAccess.DbAdapters.CategoriesDbAccess;
import vn.s3corp.android.MobileMap.DataAccess.DbAdapters.CommentsDbAccess;
import vn.s3corp.android.MobileMap.DataAccess.DbAdapters.DbOpenHelper;
import vn.s3corp.android.MobileMap.DataAccess.DbAdapters.EmailsDbAccess;
import vn.s3corp.android.MobileMap.DataAccess.DbAdapters.FavoritePoisDbAccess;
import vn.s3corp.android.MobileMap.DataAccess.DbAdapters.LoginSessionDbAccess;
import vn.s3corp.android.MobileMap.DataAccess.DbAdapters.PhoneNumbersDbAccess;
import vn.s3corp.android.MobileMap.DataAccess.DbAdapters.PicturesDbAccess;
import vn.s3corp.android.MobileMap.DataAccess.DbAdapters.PoisCategoriesDbAccess;
import vn.s3corp.android.MobileMap.DataAccess.DbAdapters.PoisDbAccess;
import vn.s3corp.android.MobileMap.DataAccess.DbAdapters.RatingsDbAccess;
import vn.s3corp.android.MobileMap.DataAccess.DbAdapters.UsersDbAccess;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DataAccessException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DatabaseException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.InvalidDataException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.SystemException;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

/**
 * The local content provider for Mobile Map data.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class LocalContentProvider implements IContentProvider {

	private final Context mCtx;

	private SQLiteDatabase mDb;
	private DbOpenHelper mDbOpen;

	private UsersDbAccess mUserDb;
	private LoginSessionDbAccess mSessionDb;
	private CategoriesDbAccess mCategoryDb;
	private AddressesDbAccess mAddressDb;
	private PoisCategoriesDbAccess mPoiCategoryDb;
	private PoisDbAccess mPoiDb;
	private FavoritePoisDbAccess mFavoritePoiDb;
	private CommentsDbAccess mCommentDb;
	private EmailsDbAccess mEmailDb;
	private PhoneNumbersDbAccess mPhoneNumberDb;
	private RatingsDbAccess mRatingDb;
	/**
	 * @author hieu.ha
	 */
	private PicturesDbAccess mPictureDb;

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
	public LocalContentProvider(Context context) {
		this.mCtx = context;

		this.mDb = null;
		this.mDbOpen = null;
		this.mCommentDb = null;
		this.mUserDb = null;
		this.mSessionDb = null;
		this.mCategoryDb = null;
		this.mAddressDb = null;
		this.mPoiCategoryDb = null;
		this.mPoiDb = null;
		this.mFavoritePoiDb = null;

		this.mEmailDb = null;
		this.mPhoneNumberDb = null;
		this.mRatingDb = null;
	}

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
	public IContentProvider open() throws DataAccessException,
			DatabaseException {

		if (null == this.mDbOpen) {
			this.mDbOpen = new DbOpenHelper(mCtx);
		}

		if (null == this.mDb) {
			try {
				this.mDb = this.mDbOpen.getWritableDatabase();
			} catch (SQLiteException e) {
				this.mDb = null;
				throw new DatabaseException("", e.getMessage(), e);
			}
		}

		return this;
	}

	/**
	 * Close the Mobile Map data provider.
	 * 
	 * @param
	 * @return
	 * @author nam.tran
	 * @version 0.1.0
	 * 
	 */
	public void close() {

		if (null != this.mDb) {
			this.mDb.close();
			this.mDb = null;
		}

		if (null != this.mDbOpen) {
			this.mDbOpen.close();
			this.mDbOpen = null;
		}

		this.mUserDb = null;
		this.mSessionDb = null;
		this.mCategoryDb = null;
		this.mAddressDb = null;
		this.mPoiCategoryDb = null;
		this.mPoiDb = null;
		this.mFavoritePoiDb = null;
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

		// Check internal variable.
		if (null == this.mDb) {
			throw new SystemException("NotInitialized",
					"The content provider was not opened.");
		}

		// Check input data.
		if (null == user) {
			throw new InvalidDataException("user", "Input data is null.");
		}

		if ("".equals(user.getLoginname())) {
			throw new InvalidDataException("user", "login name is empty.");
		}

		if ("".equals(user.getPassword())) {
			throw new InvalidDataException("user", "password is empty.");
		}

		// Update user data before add to the database.
		user.setUserType(User.USER_TYPE_NORMAL);
		user.setCreationDate(new Date());
		user.setActivated(true);

		// Initialize the User database if needed.
		if (null == this.mUserDb) {
			this.mUserDb = new UsersDbAccess(this.mDb);
		}

		if (null == this.mAddressDb) {
			this.mAddressDb = new AddressesDbAccess(this.mDb);
		}

		Cursor cursor = null;

		try {
			// Check existing user.
			cursor = this.mUserDb.fetchUser(user.getLoginname());

			if (0 < cursor.getCount()) {
				throw new DataAccessException("UserExist",
						"The input login name is exist.");
			}

			cursor.close();
			cursor = null;

			// Add address.
			if (!"".equals(user.getAddress().getAddressString())) {
				final long addressId = this.mAddressDb.createAddress(user
						.getAddress());
				if (0 >= addressId) {
					throw new DatabaseException("", "Internal SQL error.");
				}
				user.getAddress().setId(addressId);
			}

			final long userId = this.mUserDb.createUser(user);
			if (0 >= userId) {
				throw new DatabaseException("", "Internal SQL error.");
			}
			user.setId(userId);

		} catch (DataAccessException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw e;
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLiteException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new DatabaseException("", e.getMessage(), e);
		} catch (Exception e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new SystemException("UnexpectedError", e.getMessage(), e);
		}

		return true;
	}

	public ArrayList<Comment> getAllPoiComments(long poiId)
			throws DatabaseException, SystemException {
		// Check internal variable.
		if (null == this.mDb) {
			throw new SystemException("NotInitialized",
					"The content provider was not opened.");
		}

		// Initialize the POI Category database if needed.
		if (null == this.mCommentDb) {
			this.mCommentDb = new CommentsDbAccess(this.mDb);
		}

		if (null == this.mUserDb) {
			this.mUserDb = new UsersDbAccess(this.mDb);
		}

		Cursor cursor = null;
		Cursor userCursor = null;
		ArrayList<Comment> result = new ArrayList<Comment>();

		try {
			// Get all categories.
			cursor = this.mCommentDb.fetchAllPoiComments(poiId);

			if ((null == cursor) || (0 == cursor.getCount())) {
				return result;
			}

			while (!cursor.isAfterLast()) {
				Comment comment = new Comment();

				comment
						.setId(cursor
								.getLong(cursor
										.getColumnIndex(CommentsDbAccess.TABLE_KEY_COMMENT_ID)));
				comment
						.setAddressString(cursor
								.getString(cursor
										.getColumnIndex(CommentsDbAccess.TABLE_KEY_COMMENT_STRING)));
				comment
						.setBadReport(0 != cursor
								.getLong(cursor
										.getColumnIndex(CommentsDbAccess.TABLE_KEY_IS_BAD_REPORTED)));
				comment
						.setCreationDate(new Date(
								cursor
										.getLong(cursor
												.getColumnIndex(CommentsDbAccess.TABLE_KEY_CREATION_DATETIME))));
				comment
						.setActivated(0 != cursor
								.getLong(cursor
										.getColumnIndex(CommentsDbAccess.TABLE_KEY_IS_ACTIVATED)));

				comment
						.getCreationUser()
						.setId(
								cursor
										.getLong(cursor
												.getColumnIndex(CommentsDbAccess.TABLE_KEY_USER_ID)));

				userCursor = this.mUserDb.fetchUser(comment.getCreationUser()
						.getId());
				comment
						.getCreationUser()
						.setLoginname(
								userCursor
										.getString(userCursor
												.getColumnIndex(UsersDbAccess.TABLE_KEY_LOGINNAME)));

				result.add(comment);

				cursor.moveToNext();
			}

			cursor.close();
			cursor = null;

		} catch (SQLiteException e) {
			if (null != cursor) {
				cursor.close();
			}
			if (null != userCursor) {
				cursor.close();
			}
			throw new DatabaseException("", e.getMessage(), e);
		} catch (Exception e) {
			if (null != cursor) {
				cursor.close();
			}
			if (null != userCursor) {
				cursor.close();
			}
			throw new SystemException("UnexpectedError", e.getMessage(), e);
		}

		return result;
	}

	/**
	 * @author Cuong.Le
	 * @version 0.4.0
	 */
	public boolean addComment(Comment comment, long poiId)
			throws InvalidDataException, DataAccessException,
			DatabaseException, SystemException {

		if (null == this.mCommentDb) {
			this.mCommentDb = new CommentsDbAccess(this.mDb);
		}
		Cursor cursor = null;

		try {
			final long commentId = this.mCommentDb
					.createComment(comment, poiId);
			if (0 >= commentId) {
				throw new DatabaseException("", "Internal SQL error.");
			}
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLiteException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new DatabaseException("", e.getMessage(), e);
		} catch (Exception e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new SystemException("UnexpectedError", e.getMessage(), e);
		}

		return true;
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
		// Check internal variable.
		if (null == this.mDb) {
			throw new SystemException("NotInitialized",
					"The content provider was not opened.");
		}

		// Initialize the User database if needed.
		if (null == this.mUserDb) {
			this.mUserDb = new UsersDbAccess(this.mDb);
		}

		// Initialize the Email database if needed.
		if (null == this.mEmailDb) {
			this.mEmailDb = new EmailsDbAccess(this.mDb);
		}

		if (null == this.mPhoneNumberDb)
			this.mPhoneNumberDb = new PhoneNumbersDbAccess(this.mDb);

		// Check LoginName
		Cursor userCursor = this.mUserDb.fetchUser(user.getLoginname());
		if ((userCursor.getCount() > 0)
				&& (user.getId() != userCursor.getLong(userCursor
						.getColumnIndex(this.mUserDb.TABLE_KEY_USER_ID))))
			return false;

		boolean result = false;
		// Update address.
		Address address = user.getAddress();
		if (!"".equals(address.getAddressString())) {
			if (0 == address.getId()) {
				// Create new address.
				final long addressId = this.mAddressDb.createAddress(address);
				if (0 >= addressId) {
					throw new DatabaseException("", "Internal SQL error.");
				}
				user.getAddress().setId(addressId);
			} else {
				// Update old address.
				this.mAddressDb.updateAddress(address);
			}
		}

		// Update Emails
		this.mEmailDb.deleteAllUserEmails(user.getId());
		for (Email email : user.getEmails()) {
			this.mEmailDb.createEmail(email, user.getId(), 0);
		}

		// Update PhoneNumbers
		this.mPhoneNumberDb.deleteAllUserPhoneNumbers(user.getId());
		for (PhoneNumber phone : user.getPhoneNumbers()) {
			this.mPhoneNumberDb.createPhoneNumber(phone, user.getId(), 0);
		}

		try {
			result = this.mUserDb.updateUser(user);
		} catch (Exception e) {
			throw new SystemException("UnexpectedError", e.getMessage(), e);
		}

		return result;
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
		// Initialize the User database if needed.
		if (null == this.mUserDb) {
			this.mUserDb = new UsersDbAccess(this.mDb);
		}

		// Initialize the User database if needed.
		if (null == this.mEmailDb) {
			this.mEmailDb = new EmailsDbAccess(this.mDb);
		}

		// Initialize the User database if needed.
		if (null == this.mPhoneNumberDb) {
			this.mPhoneNumberDb = new PhoneNumbersDbAccess(this.mDb);
		}

		Cursor cursor = null;
		User user = new User();
		try {
			// Get user.
			cursor = this.mUserDb.fetchUser(userId);
			user.setId(cursor.getLong(cursor
					.getColumnIndex(UsersDbAccess.TABLE_KEY_USER_ID)));
			user.setLoginname(cursor.getString(cursor
					.getColumnIndex(UsersDbAccess.TABLE_KEY_LOGINNAME)));
			user.setPassword(cursor.getString(cursor
					.getColumnIndex(UsersDbAccess.TABLE_KEY_PASSWORD)));

			if (cursor.getCount() > 0) {
				if (null == this.mAddressDb) {
					this.mAddressDb = new AddressesDbAccess(this.mDb);
				}
				Address address = new Address();
				Cursor addressCursor = this.mAddressDb
						.fetchAddress(cursor
								.getLong(cursor
										.getColumnIndex(UsersDbAccess.TABLE_KEY_ADDRESS_ID)));
				if (addressCursor.getCount() > 0) {
					address
							.setId(addressCursor
									.getLong(addressCursor
											.getColumnIndex(AddressesDbAccess.TABLE_KEY_ADDRESS_ID)));
					address
							.setAddressString(addressCursor
									.getString(addressCursor
											.getColumnIndex(AddressesDbAccess.TABLE_KEY_ADDRESS_STRING)));
				}
				user.setAddress(address);

				// Get all User Emails
				user.removeAllEmails();
				Cursor emailCursor = this.mEmailDb.fetchAllUserEmails(user
						.getId());
				for (emailCursor.moveToFirst(); !emailCursor.isAfterLast(); emailCursor
						.moveToNext()) {
					Email email = new Email();
					email.setId(emailCursor.getLong(emailCursor
							.getColumnIndex(this.mEmailDb.TABLE_KEY_EMAIL_ID)));
					email
							.setEmailString(emailCursor
									.getString(emailCursor
											.getColumnIndex(this.mEmailDb.TABLE_KEY_EMAIL_STRING)));
					user.addEmail(email);
				}

				// Get User PhoneNumbers
				user.removeAllPhoneNumbers();
				Cursor phoneCursor = this.mPhoneNumberDb
						.fetchAllUserPhoneNumbers(user.getId());
				for (phoneCursor.moveToFirst(); !phoneCursor.isAfterLast(); phoneCursor
						.moveToNext()) {
					PhoneNumber phone = new PhoneNumber();
					phone
							.setId(phoneCursor
									.getLong(phoneCursor
											.getColumnIndex(this.mPhoneNumberDb.TABLE_KEY_PHONENUMBER_ID)));
					phone
							.setPhoneNumberString(phoneCursor
									.getString(phoneCursor
											.getColumnIndex(this.mPhoneNumberDb.TABLE_KEY_PHONENUMBER_STRING)));
					user.addPhoneNumber(phone);
				}
			}
			cursor.close();
		} catch (SQLiteException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new DatabaseException("", e.getMessage(), e);
		} catch (Exception e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new SystemException("UnexpectedError", e.getMessage(), e);
		}
		return user;
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

		// Check internal variable.
		if (null == this.mDb) {
			throw new SystemException("NotInitialized",
					"The content provider was not opened.");
		}

		// Check input data.
		if (null == session) {
			throw new InvalidDataException("session", "Input data is null.");
		}

		if (null == session.getUser()) {
			throw new InvalidDataException("session",
					"Input user data is null.");
		}

		if ("".equals(session.getUser().getLoginname())) {
			throw new InvalidDataException("session", "login name is empty.");
		}

		if ("".equals(session.getUser().getPassword())) {
			throw new InvalidDataException("session", "password is empty.");
		}

		// Initialize the User database if needed.
		if (null == this.mUserDb) {
			this.mUserDb = new UsersDbAccess(this.mDb);
		}

		Cursor cursor = null;
		try {
			// Check valid user and password.
			// Check existing user.
			cursor = this.mUserDb.fetchUser(session.getUser().getLoginname());
			if (0 == cursor.getCount()) {
				throw new DataAccessException("UserNotExist",
						"The login name is not exist.");
			}

			if (1 != cursor.getCount()) {
				throw new DatabaseException("", "The database has wrong data.");
			}

			// Check password.
			String password = cursor.getString(cursor
					.getColumnIndex(UsersDbAccess.TABLE_KEY_PASSWORD));
			if (!session.getUser().getPassword().equals(password)) {
				throw new DataAccessException("WrongPassword",
						"The input password is wrong.");
			}

			// Update session data before add to the database.
			session.getUser().setId(
					cursor.getLong(cursor
							.getColumnIndex(UsersDbAccess.TABLE_KEY_USER_ID)));
			session.setLoginDate(new Date());
			session.setLogoutDate(new Date());
			session.setClosed(false);

			cursor.close();
			cursor = null;

			// Initialize the Session database if needed.
			if (null == this.mSessionDb) {
				this.mSessionDb = new LoginSessionDbAccess(this.mDb);
			}

			// Update old sessions.
			this.mSessionDb.updateAllUserOpenSessions(session);

			// Create new session.
			final long sessionId = this.mSessionDb.createSession(session);
			if (0 >= sessionId) {
				throw new DatabaseException("", "Internal SQL error.");
			}
			session.setId(sessionId);

		} catch (DataAccessException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw e;
		} catch (DatabaseException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw e;
		} catch (SQLiteException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new DatabaseException("", e.getMessage(), e);
		} catch (Exception e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new SystemException("UnexpectedError", e.getMessage(), e);
		}

		return true;
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

		// Check internal variable.
		if (null == this.mDb) {
			throw new SystemException("NotInitialized",
					"The content provider was not opened.");
		}

		// Check input data.
		if (null == session) {
			throw new InvalidDataException("session", "Input data is null.");
		}

		if (0 == session.getId()) {
			throw new InvalidDataException("session", "Session ID is zero.");
		}

		// Update session data before add to the database.
		session.setLogoutDate(new Date());
		session.setClosed(true);

		// Initialize the Session database if needed.
		if (null == this.mSessionDb) {
			this.mSessionDb = new LoginSessionDbAccess(this.mDb);
		}

		boolean result = false;
		try {
			// Update session.
			result = this.mSessionDb.updateSession(session);

		} catch (SQLiteException e) {
			throw new DatabaseException("", e.getMessage(), e);
		} catch (Exception e) {
			throw new SystemException("UnexpectedError", e.getMessage(), e);
		}

		return result;
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

		// Check internal variable.
		if (null == this.mDb) {
			throw new SystemException("NotInitialized",
					"The content provider was not opened.");
		}

		// Initialize the POI Category database if needed.
		if (null == this.mCategoryDb) {
			this.mCategoryDb = new CategoriesDbAccess(this.mDb);
		}

		Cursor cursor = null;
		ArrayList<Category> result = new ArrayList<Category>();

		try {
			// Get all categories.
			cursor = this.mCategoryDb.fetchAllCategories();

			if ((null == cursor) || (0 == cursor.getCount())) {
				return result;
			}

			while (!cursor.isAfterLast()) {
				Category category = new Category();

				category
						.setId(cursor
								.getLong(cursor
										.getColumnIndex(CategoriesDbAccess.TABLE_KEY_CATEGORY_ID)));
				category
						.setName(cursor
								.getString(cursor
										.getColumnIndex(CategoriesDbAccess.TABLE_KEY_CATEGORY_NAME_EN)));

				result.add(category);

				cursor.moveToNext();
			}

			cursor.close();
			cursor = null;

		} catch (SQLiteException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new DatabaseException("", e.getMessage(), e);
		} catch (Exception e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new SystemException("UnexpectedError", e.getMessage(), e);
		}

		return result;
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

		// Check internal variable.
		if (null == this.mDb) {
			throw new SystemException("NotInitialized",
					"The content provider was not opened.");
		}

		// Check input data.
		if (null == poi) {
			throw new InvalidDataException("poi", "Input data is null.");
		}

		if ("".equals(poi.getName())) {
			throw new InvalidDataException("poi", "POI's name is empty.");
		}

		// Update user data before add to the database.
		poi.setCreationDate(new Date());
		poi.setActivated(true);
		poi.setApproved(true);
		poi.setApproveDate(new Date());
		poi.setApproveUser(poi.getCreationUser());

		// Initialize the databases if needed.
		if (null == this.mPoiDb) {
			this.mPoiDb = new PoisDbAccess(this.mDb);
		}

		if (null == this.mAddressDb) {
			this.mAddressDb = new AddressesDbAccess(this.mDb);
		}

		if (null == this.mPoiCategoryDb) {
			this.mPoiCategoryDb = new PoisCategoriesDbAccess(this.mDb);
		}
		/**
		 * @author hieu.ha
		 */
		if (null == this.mPictureDb) {
			this.mPictureDb = new PicturesDbAccess(this.mDb);
		}
		Cursor cursor = null;
		try {
			// Check existing poi.
			cursor = this.mPoiDb
					.fetchPoi(poi.getLongitude(), poi.getLatitude());

			if (0 < cursor.getCount()) {
				cursor.close();
				throw new DataAccessException("POIExist",
						"The input POI has the same location with an existing POI.");
			}

			cursor.close();
			cursor = null;

			// Add addresses
			if (!"".equals(poi.getAddress().getAddressString())) {
				final long addressId = this.mAddressDb.createAddress(poi
						.getAddress());
				if (0 >= addressId) {
					throw new DatabaseException("", "Internal SQL error.");
				}
				poi.getAddress().setId(addressId);
			}

			// Add POI
			final long poiId = this.mPoiDb.createPoi(poi);
			if (0 >= poiId) {
				throw new DatabaseException("", "Internal SQL error.");
			}
			poi.setId(poiId);

			// Add category for POI.
			Object[] categories = poi.getCategories().toArray();
			for (int i = 0; i < categories.length; i++) {
				this.mPoiCategoryDb.createPoiCategory(
						((Category) categories[i]).getId(), poi.getId());
			}
			/**
			 * Add picture for POI
			 * 
			 * @author hieu.ha
			 */
			Object[] pictures = poi.getPictures().toArray();
			for (int i = 0; i < pictures.length; i++) {
				this.addPicture(((Picture) pictures[i]), poi);
			}

		} catch (DataAccessException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw e;
		} catch (DatabaseException e) {
			throw e;
		} catch (SQLiteException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new DatabaseException("", e.getMessage(), e);
		} catch (Exception e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new SystemException("UnexpectedError", e.getMessage(), e);
		}

		return true;
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

		// Check internal variable.
		if (null == this.mDb) {
			throw new SystemException("NotInitialized",
					"The content provider was not opened.");
		}

		// Check input data.
		if (0 == poiId) {
			throw new InvalidDataException("poiId", "POI's ID is zero.");
		}

		// Initialize the databases if needed.
		if (null == this.mPoiDb) {
			this.mPoiDb = new PoisDbAccess(this.mDb);
		}

		if (null == this.mAddressDb) {
			this.mAddressDb = new AddressesDbAccess(this.mDb);
		}

		if (null == this.mPoiCategoryDb) {
			this.mPoiCategoryDb = new PoisCategoriesDbAccess(this.mDb);
		}
		/**
		 * Delete Picture of POI
		 * 
		 * @author hieu.ha
		 */
		if (null == this.mPictureDb) {
			this.mPictureDb = new PicturesDbAccess(this.mDb);
		}
		boolean result = false;
		Cursor cursor = null;

		try {
			// Check existing POI.
			cursor = this.mPoiDb.fetchPoi(poiId);
			if (0 == cursor.getCount()) {
				throw new DataAccessException("POINotExist",
						"The POI is not exist.");
			}

			if (1 != cursor.getCount()) {
				throw new DatabaseException("", "The database has wrong data.");
			}

			// Get Address ID
			long addressId = cursor.getLong(cursor
					.getColumnIndex(PoisDbAccess.TABLE_KEY_ADDRESS_ID));
			// Delete Address
			if (0 < addressId) {
				this.mAddressDb.deleteAddress(addressId);
			}

			cursor.close();
			cursor = null;

			// Delete category of POI.
			this.mPoiCategoryDb.deletePoi(poiId);
			/**
			 * Delete Picture of POI
			 * 
			 * @author hieu.ha
			 */
			this.mPictureDb.deleteAllPoiPictures(poiId);
			// Delete POI
			result = this.mPoiDb.deletePoi(poiId);

		} catch (DataAccessException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw e;
		} catch (DatabaseException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw e;
		} catch (SQLiteException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new DatabaseException("", e.getMessage(), e);
		} catch (Exception e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new SystemException("UnexpectedError", e.getMessage(), e);
		}

		return result;
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

		// Check internal variable.
		if (null == this.mDb) {
			throw new SystemException("NotInitialized",
					"The content provider was not opened.");
		}

		// Check input data.
		if (null == poi) {
			throw new InvalidDataException("poi", "Input data is null.");
		}

		if (0 >= poi.getId()) {
			throw new InvalidDataException("poi", "POI's ID is wrong.");
		}

		// Initialize the databases if needed.
		if (null == this.mPoiDb) {
			this.mPoiDb = new PoisDbAccess(this.mDb);
		}

		if (null == this.mAddressDb) {
			this.mAddressDb = new AddressesDbAccess(this.mDb);
		}

		if (null == this.mPoiCategoryDb) {
			this.mPoiCategoryDb = new PoisCategoriesDbAccess(this.mDb);
		}
		/**
		 * @author hieu.ha
		 */
		if (null == this.mPictureDb) {
			this.mPictureDb = new PicturesDbAccess(this.mDb);
		}
		boolean result = false;
		Cursor cursor = null;

		try {
			// Update address.
			Address address = poi.getAddress();
			if (!"".equals(address.getAddressString())) {
				if (0 == address.getId()) {
					// Check old address.
					// Check existing POI.
					cursor = this.mPoiDb.fetchPoi(poi.getId());
					if (0 == cursor.getCount()) {
						throw new DataAccessException("POINotExist",
								"The POI is not exist.");
					}

					if (1 != cursor.getCount()) {
						throw new DatabaseException("",
								"The database has wrong data.");
					}

					// Get Address ID
					final long addressId = cursor.getLong(cursor
							.getColumnIndex(PoisDbAccess.TABLE_KEY_ADDRESS_ID));
					address.setId(addressId);

					cursor.close();
					cursor = null;
				}

				if (0 == address.getId()) {
					// Create new address.
					final long addressId = this.mAddressDb
							.createAddress(address);
					if (0 >= addressId) {
						throw new DatabaseException("", "Internal SQL error.");
					}
					address.setId(addressId);
				} else {
					// Update old address.
					this.mAddressDb.updateAddress(address);
				}
			}

			// Update category.
			Object[] categories = poi.getCategories().toArray();

			if (0 < categories.length) {
				// Remove old categories.
				this.mPoiCategoryDb.deletePoi(poi.getId());

				// Create categories.
				for (int i = 0; i < categories.length; i++) {
					this.mPoiCategoryDb.createPoiCategory(
							((Category) categories[i]).getId(), poi.getId());
				}
			}
			/**
			 * Update picture of POI
			 * 
			 * @author hieu.ha
			 */
			// Update category.
			Object[] pictures = poi.getPictures().toArray();

			if (0 < pictures.length) {
				// Remove old pictures.
				this.mPictureDb.deleteAllPoiPictures(poi.getId());

				// Create pictures.
				for (int i = 0; i < pictures.length; i++) {
					this.addPicture(((Picture) pictures[i]), poi);
				}
			}
			// Update other fields
			result = this.mPoiDb.updatePoi(poi);

		} catch (DataAccessException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw e;
		} catch (DatabaseException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw e;
		} catch (SQLiteException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new DatabaseException("", e.getMessage(), e);
		} catch (Exception e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new SystemException("UnexpectedError", e.getMessage(), e);
		}

		return result;
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

		// Check internal variable.
		if (null == this.mDb) {
			throw new SystemException("NotInitialized",
					"The content provider was not opened.");
		}

		// Initialize the POI database if needed.
		if (null == this.mPoiDb) {
			this.mPoiDb = new PoisDbAccess(this.mDb);
		}

		if (null == this.mPoiCategoryDb) {
			this.mPoiCategoryDb = new PoisCategoriesDbAccess(this.mDb);
		}
		/**
		 * Get picture.
		 * 
		 * @author hieu.ha
		 */
		if (null == this.mPictureDb) {
			this.mPictureDb = new PicturesDbAccess(this.mDb);
		}
		Cursor cursor = null;
		Cursor categoryCursor = null;
		ArrayList<Poi> result = new ArrayList<Poi>();

		try {
			// Get all public POIs.
			cursor = this.mPoiDb.fetchAllPublicPoisSimple();

			if ((null == cursor) || (0 == cursor.getCount())) {
				return result;
			}

			while (!cursor.isAfterLast()) {
				Poi poi = new Poi();

				poi.setId(cursor.getLong(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_POI_ID)));
				poi.setLongitude(cursor.getLong(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_LONGITUDE)));
				poi.setLatitude(cursor.getLong(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_LATITUDE)));
				poi.setPoiType(cursor.getLong(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_POI_TYPE)));
				poi.setName(cursor.getString(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_POI_NAME)));
				poi.setRating(cursor.getLong(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_RATING_VALUE)));
				poi
						.getCreationUser()
						.setId(
								cursor
										.getLong(cursor
												.getColumnIndex(PoisDbAccess.TABLE_KEY_CREATION_USER_ID)));
				poi.setApproved(0 != cursor.getLong(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_IS_APPROVED)));
				poi.setActivated(0 != cursor.getLong(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_IS_ACTIVATED)));

				// Get category.
				categoryCursor = this.mPoiCategoryDb.fetchCategories(poi
						.getId());
				if (null != categoryCursor) {
					if (0 < categoryCursor.getCount()) {
						while (!categoryCursor.isAfterLast()) {
							Category category = new Category();

							category
									.setId(categoryCursor
											.getLong(categoryCursor
													.getColumnIndex(PoisCategoriesDbAccess.TABLE_KEY_CATEGORY_ID)));

							poi.addCategory(category);

							categoryCursor.moveToNext();
						}
					}

					categoryCursor.close();
					categoryCursor = null;
				}
				/**
				 * Get picture.
				 * 
				 * @author hieu.ha
				 */
				Cursor pictureCursor = null;
				pictureCursor = this.mPictureDb
						.fetchAllPoiPictures(poi.getId());
				if (null != pictureCursor) {
					if (0 < pictureCursor.getCount()) {
						while (!pictureCursor.isAfterLast()) {
							Picture picture = new Picture();

							picture
									.setId(pictureCursor
											.getLong(pictureCursor
													.getColumnIndex(PicturesDbAccess.TABLE_KEY_PICTURE_ID)));
							picture
									.setLocation(pictureCursor
											.getString(pictureCursor
													.getColumnIndex(PicturesDbAccess.TABLE_KEY_LOCATION)));
							picture
									.setDescription(pictureCursor
											.getString(pictureCursor
													.getColumnIndex(PicturesDbAccess.TABLE_KEY_DESCRIPTIONS)));
							poi.addPicture(picture);

							pictureCursor.moveToNext();
						}
					}

					pictureCursor.close();
					pictureCursor = null;
				}
				result.add(poi);

				cursor.moveToNext();
			}

			cursor.close();
			cursor = null;

		} catch (SQLiteException e) {
			if (null != cursor) {
				cursor.close();
			}
			if (null != categoryCursor) {
				categoryCursor.close();
			}
			throw new DatabaseException("", e.getMessage(), e);
		} catch (Exception e) {
			if (null != cursor) {
				cursor.close();
			}
			if (null != categoryCursor) {
				categoryCursor.close();
			}
			throw new SystemException("UnexpectedError", e.getMessage(), e);
		}

		return result;
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

		// Check internal variable.
		if (null == this.mDb) {
			throw new SystemException("NotInitialized",
					"The content provider was not opened.");
		}

		if (0 >= userId) {
			throw new InvalidDataException("userId", "User's ID is wrong.");
		}

		// Initialize the POI database if needed.
		if (null == this.mPoiDb) {
			this.mPoiDb = new PoisDbAccess(this.mDb);
		}

		if (null == this.mPoiCategoryDb) {
			this.mPoiCategoryDb = new PoisCategoriesDbAccess(this.mDb);
		}

		Cursor cursor = null;
		Cursor categoryCursor = null;
		ArrayList<Poi> result = new ArrayList<Poi>();

		try {
			// Get all public POIs.
			cursor = this.mPoiDb.fetchAllPrivatePoisSimple(userId);

			if ((null == cursor) || (0 == cursor.getCount())) {
				return result;
			}

			while (!cursor.isAfterLast()) {
				Poi poi = new Poi();

				poi.setId(cursor.getLong(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_POI_ID)));
				poi.setLongitude(cursor.getLong(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_LONGITUDE)));
				poi.setLatitude(cursor.getLong(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_LATITUDE)));
				poi.setPoiType(cursor.getLong(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_POI_TYPE)));
				poi.setName(cursor.getString(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_POI_NAME)));
				poi.setRating(cursor.getLong(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_RATING_VALUE)));
				poi
						.getCreationUser()
						.setId(
								cursor
										.getLong(cursor
												.getColumnIndex(PoisDbAccess.TABLE_KEY_CREATION_USER_ID)));
				poi.setApproved(0 != cursor.getLong(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_IS_APPROVED)));
				poi.setActivated(0 != cursor.getLong(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_IS_ACTIVATED)));

				// Get category.
				categoryCursor = this.mPoiCategoryDb.fetchCategories(poi
						.getId());
				if (null != categoryCursor) {
					if (0 < categoryCursor.getCount()) {
						while (!categoryCursor.isAfterLast()) {
							Category category = new Category();

							category
									.setId(categoryCursor
											.getLong(categoryCursor
													.getColumnIndex(PoisCategoriesDbAccess.TABLE_KEY_CATEGORY_ID)));

							poi.addCategory(category);

							categoryCursor.moveToNext();
						}
					}

					categoryCursor.close();
					categoryCursor = null;
				}
				/**
				 * Get picture.
				 * 
				 * @author hieu.ha
				 */
				Cursor pictureCursor = null;
				pictureCursor = this.mPictureDb
						.fetchAllPoiPictures(poi.getId());
				if (null != pictureCursor) {
					if (0 < pictureCursor.getCount()) {
						while (!pictureCursor.isAfterLast()) {
							Picture picture = new Picture();

							picture
									.setId(pictureCursor
											.getLong(pictureCursor
													.getColumnIndex(PicturesDbAccess.TABLE_KEY_PICTURE_ID)));
							picture
									.setLocation(pictureCursor
											.getString(pictureCursor
													.getColumnIndex(PicturesDbAccess.TABLE_KEY_LOCATION)));
							picture
									.setDescription(pictureCursor
											.getString(pictureCursor
													.getColumnIndex(PicturesDbAccess.TABLE_KEY_DESCRIPTIONS)));
							poi.addPicture(picture);

							pictureCursor.moveToNext();
						}
					}

					pictureCursor.close();
					pictureCursor = null;
				}
				result.add(poi);

				cursor.moveToNext();
			}

			cursor.close();
			cursor = null;

		} catch (SQLiteException e) {
			if (null != cursor) {
				cursor.close();
			}
			if (null != categoryCursor) {
				categoryCursor.close();
			}
			throw new DatabaseException("", e.getMessage(), e);
		} catch (Exception e) {
			if (null != cursor) {
				cursor.close();
			}
			if (null != categoryCursor) {
				categoryCursor.close();
			}
			throw new SystemException("UnexpectedError", e.getMessage(), e);
		}

		return result;
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

		// Check internal variable.
		if (null == this.mDb) {
			throw new SystemException("NotInitialized",
					"The content provider was not opened.");
		}

		if (0 >= poiId) {
			throw new InvalidDataException("poiId", "POI's ID is wrong.");
		}

		// Initialize the POI database if needed.
		if (null == this.mPoiDb) {
			this.mPoiDb = new PoisDbAccess(this.mDb);
		}

		if (null == this.mPoiCategoryDb) {
			this.mPoiCategoryDb = new PoisCategoriesDbAccess(this.mDb);
		}

		if (null == this.mAddressDb) {
			this.mAddressDb = new AddressesDbAccess(this.mDb);
		}

		Cursor cursor = null;
		Cursor categoryCursor = null;
		Cursor addressCursor = null;
		Poi result = null;

		try {
			// Get all public POIs.
			cursor = this.mPoiDb.fetchPoi(poiId);

			if ((null == cursor) || (0 == cursor.getCount())) {
				throw new DataAccessException("POINotExist",
						"The POI is not exist.");
			}

			if (1 != cursor.getCount()) {
				throw new DatabaseException("", "The database has wrong data.");
			}

			result = new Poi();

			result.setId(cursor.getLong(cursor
					.getColumnIndex(PoisDbAccess.TABLE_KEY_POI_ID)));
			result.setLongitude(cursor.getLong(cursor
					.getColumnIndex(PoisDbAccess.TABLE_KEY_LONGITUDE)));
			result.setLatitude(cursor.getLong(cursor
					.getColumnIndex(PoisDbAccess.TABLE_KEY_LATITUDE)));
			result.setPoiType(cursor.getLong(cursor
					.getColumnIndex(PoisDbAccess.TABLE_KEY_POI_TYPE)));
			result.setName(cursor.getString(cursor
					.getColumnIndex(PoisDbAccess.TABLE_KEY_POI_NAME)));
			result.setDescriptions(cursor.getString(cursor
					.getColumnIndex(PoisDbAccess.TABLE_KEY_DESCRIPTIONS)));
			result.setRating(cursor.getLong(cursor
					.getColumnIndex(PoisDbAccess.TABLE_KEY_RATING_VALUE)));
			result
					.getAddress()
					.setId(
							cursor
									.getLong(cursor
											.getColumnIndex(PoisDbAccess.TABLE_KEY_ADDRESS_ID)));
			result
					.setCreationDate(new Date(
							cursor
									.getLong(cursor
											.getColumnIndex(PoisDbAccess.TABLE_KEY_CREATION_DATETIME))));
			result
					.getCreationUser()
					.setId(
							cursor
									.getLong(cursor
											.getColumnIndex(PoisDbAccess.TABLE_KEY_CREATION_USER_ID)));
			result.setApproved(0 != cursor.getLong(cursor
					.getColumnIndex(PoisDbAccess.TABLE_KEY_IS_APPROVED)));
			result.setApproveDate(new Date(cursor.getLong(cursor
					.getColumnIndex(PoisDbAccess.TABLE_KEY_APPROVE_DATETIME))));
			result
					.getApproveUser()
					.setId(
							cursor
									.getLong(cursor
											.getColumnIndex(PoisDbAccess.TABLE_KEY_APPROVE_USER_ID)));
			result.setActivated(0 != cursor.getLong(cursor
					.getColumnIndex(PoisDbAccess.TABLE_KEY_IS_ACTIVATED)));

			// Get category.
			categoryCursor = this.mPoiCategoryDb
					.fetchCategories(result.getId());
			if (null != categoryCursor) {
				if (0 < categoryCursor.getCount()) {
					while (!categoryCursor.isAfterLast()) {
						Category category = new Category();

						category
								.setId(categoryCursor
										.getLong(categoryCursor
												.getColumnIndex(PoisCategoriesDbAccess.TABLE_KEY_CATEGORY_ID)));

						result.addCategory(category);

						categoryCursor.moveToNext();
					}
				}

				categoryCursor.close();
				categoryCursor = null;
			}
			/**
			 * Get picture.
			 * 
			 * @author hieu.ha
			 */
			Cursor pictureCursor = null;
			pictureCursor = this.mPictureDb.fetchAllPoiPictures(result.getId());
			if (null != pictureCursor) {
				if (0 < pictureCursor.getCount()) {
					while (!pictureCursor.isAfterLast()) {
						Picture picture = new Picture();

						picture
								.setId(pictureCursor
										.getLong(pictureCursor
												.getColumnIndex(PicturesDbAccess.TABLE_KEY_PICTURE_ID)));
						picture
								.setLocation(pictureCursor
										.getString(pictureCursor
												.getColumnIndex(PicturesDbAccess.TABLE_KEY_LOCATION)));
						picture
								.setDescription(pictureCursor
										.getString(pictureCursor
												.getColumnIndex(PicturesDbAccess.TABLE_KEY_DESCRIPTIONS)));
						result.addPicture(picture);

						pictureCursor.moveToNext();
					}
				}

				pictureCursor.close();
				pictureCursor = null;
			}
			// Get address.
			if (0 < result.getAddress().getId()) {
				addressCursor = this.mAddressDb.fetchAddress(result
						.getAddress().getId());

				if ((null == addressCursor) || (0 == addressCursor.getCount())) {
					throw new DataAccessException("AddressNotExist",
							"The Address is not exist.");
				}

				if (1 != addressCursor.getCount()) {
					throw new DatabaseException("",
							"The database has wrong data.");
				}

				result
						.getAddress()
						.setAddressString(
								addressCursor
										.getString(addressCursor
												.getColumnIndex(AddressesDbAccess.TABLE_KEY_ADDRESS_STRING)));

				addressCursor.close();
				addressCursor = null;
			}

			cursor.close();
			cursor = null;

		} catch (DataAccessException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw e;
		} catch (DatabaseException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw e;
		} catch (SQLiteException e) {
			if (null != cursor) {
				cursor.close();
			}
			if (null != categoryCursor) {
				categoryCursor.close();
			}
			throw new DatabaseException("", e.getMessage(), e);
		} catch (Exception e) {
			if (null != cursor) {
				cursor.close();
			}
			if (null != categoryCursor) {
				categoryCursor.close();
			}
			throw new SystemException("UnexpectedError", e.getMessage(), e);
		}

		return result;
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

		// Check internal variable.
		if (null == this.mDb) {
			throw new SystemException("NotInitialized",
					"The content provider was not opened.");
		}

		if (0 >= poiId) {
			throw new InvalidDataException("poiId", "POI's ID is wrong.");
		}

		if (0 >= userId) {
			throw new InvalidDataException("userId", "User's ID is wrong.");
		}

		// Initialize the POI database if needed.
		if (null == this.mFavoritePoiDb) {
			this.mFavoritePoiDb = new FavoritePoisDbAccess(this.mDb);
		}

		Cursor cursor = null;
		boolean result = false;

		try {
			// Get favorite POI.
			cursor = this.mFavoritePoiDb.fetchFavoritePoi(userId, poiId);

			if ((null == cursor) || (0 == cursor.getCount())) {
				result = false;
			} else if (1 != cursor.getCount()) {
				throw new DatabaseException("", "The database has wrong data.");
			} else {
				result = true;
			}

			cursor.close();
			cursor = null;

		} catch (DatabaseException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw e;
		} catch (SQLiteException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new DatabaseException("", e.getMessage(), e);
		} catch (Exception e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new SystemException("UnexpectedError", e.getMessage(), e);
		}

		return result;
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

		// Check internal variable.
		if (null == this.mDb) {
			throw new SystemException("NotInitialized",
					"The content provider was not opened.");
		}

		if (0 >= poiId) {
			throw new InvalidDataException("poiId", "POI's ID is wrong.");
		}

		if (0 >= userId) {
			throw new InvalidDataException("userId", "User's ID is wrong.");
		}

		// Initialize the database if needed.
		if (null == this.mUserDb) {
			this.mUserDb = new UsersDbAccess(this.mDb);
		}

		if (null == this.mPoiDb) {
			this.mPoiDb = new PoisDbAccess(this.mDb);
		}

		if (null == this.mFavoritePoiDb) {
			this.mFavoritePoiDb = new FavoritePoisDbAccess(this.mDb);
		}

		Cursor cursor = null;
		boolean result = false;

		try {
			// Check user's ID
			cursor = this.mUserDb.fetchUser(userId);

			if ((null == cursor) || (0 == cursor.getCount())) {
				throw new DataAccessException("UserNotExist",
						"The User is not exist.");
			} else if (1 != cursor.getCount()) {
				throw new DatabaseException("", "The database has wrong data.");
			}

			cursor.close();
			cursor = null;

			// Check POI's ID
			cursor = this.mPoiDb.fetchPoi(poiId);

			if ((null == cursor) || (0 == cursor.getCount())) {
				throw new DataAccessException("POINotExist",
						"The POI is not exist.");
			} else if (1 != cursor.getCount()) {
				throw new DatabaseException("", "The database has wrong data.");
			}

			cursor.close();
			cursor = null;

			// Get favorite POI.
			cursor = this.mFavoritePoiDb.fetchFavoritePoi(userId, poiId);

			if ((null == cursor) || (0 == cursor.getCount())) {
				// Add favorite POI.
				if (0 < this.mFavoritePoiDb.createFavoritePoi(userId, poiId)) {
					result = true;
				}
			} else if (1 != cursor.getCount()) {
				throw new DatabaseException("", "The database has wrong data.");
			} else {
				result = true;
			}

			cursor.close();
			cursor = null;

		} catch (DataAccessException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw e;
		} catch (DatabaseException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw e;
		} catch (SQLiteException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new DatabaseException("", e.getMessage(), e);
		} catch (Exception e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new SystemException("UnexpectedError", e.getMessage(), e);
		}

		return result;
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

		// Check internal variable.
		if (null == this.mDb) {
			throw new SystemException("NotInitialized",
					"The content provider was not opened.");
		}

		if (0 >= poiId) {
			throw new InvalidDataException("poiId", "POI's ID is wrong.");
		}

		if (0 >= userId) {
			throw new InvalidDataException("userId", "User's ID is wrong.");
		}

		// Initialize the database if needed.
		if (null == this.mFavoritePoiDb) {
			this.mFavoritePoiDb = new FavoritePoisDbAccess(this.mDb);
		}

		boolean result = false;

		try {
			// Get favorite POI.
			result = this.mFavoritePoiDb.deleteFavoritePoi(userId, poiId);

		} catch (SQLiteException e) {
			throw new DatabaseException("", e.getMessage(), e);
		} catch (Exception e) {
			throw new SystemException("UnexpectedError", e.getMessage(), e);
		}

		return result;
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

		// Check internal variable.
		if (null == this.mDb) {
			throw new SystemException("NotInitialized",
					"The content provider was not opened.");
		}

		// Initialize the POI database if needed.
		if (null == this.mPoiDb) {
			this.mPoiDb = new PoisDbAccess(this.mDb);
		}

		if (null == this.mPoiCategoryDb) {
			this.mPoiCategoryDb = new PoisCategoriesDbAccess(this.mDb);
		}

		Cursor cursor = null;
		Cursor categoryCursor = null;
		ArrayList<Poi> result = new ArrayList<Poi>();

		try {
			if (0 < option.getCategory().getId()) {
				cursor = this.mPoiDb.searchPoi(option.getSearchString(), option
						.getCategory().getId());
			} else {
				cursor = this.mPoiDb.searchPoi(option.getSearchString());
			}

			if ((null == cursor) || (0 == cursor.getCount())) {
				return result;
			}

			while (!cursor.isAfterLast()) {
				Poi poi = new Poi();

				poi.setId(cursor.getLong(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_POI_ID)));
				poi.setLongitude(cursor.getLong(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_LONGITUDE)));
				poi.setLatitude(cursor.getLong(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_LATITUDE)));
				poi.setPoiType(cursor.getLong(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_POI_TYPE)));
				poi.setName(cursor.getString(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_POI_NAME)));
				poi.setRating(cursor.getLong(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_RATING_VALUE)));
				poi
						.getCreationUser()
						.setId(
								cursor
										.getLong(cursor
												.getColumnIndex(PoisDbAccess.TABLE_KEY_CREATION_USER_ID)));
				poi.setApproved(0 != cursor.getLong(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_IS_APPROVED)));
				poi.setActivated(0 != cursor.getLong(cursor
						.getColumnIndex(PoisDbAccess.TABLE_KEY_IS_ACTIVATED)));

				// Get category.
				categoryCursor = this.mPoiCategoryDb.fetchCategories(poi
						.getId());
				if (null != categoryCursor) {
					if (0 < categoryCursor.getCount()) {
						while (!categoryCursor.isAfterLast()) {
							Category category = new Category();

							category
									.setId(categoryCursor
											.getLong(categoryCursor
													.getColumnIndex(PoisCategoriesDbAccess.TABLE_KEY_CATEGORY_ID)));

							poi.addCategory(category);

							categoryCursor.moveToNext();
						}
					}

					categoryCursor.close();
					categoryCursor = null;
				}

				result.add(poi);

				cursor.moveToNext();
			}

			cursor.close();
			cursor = null;

		} catch (SQLiteException e) {
			if (null != cursor) {
				cursor.close();
			}
			if (null != categoryCursor) {
				categoryCursor.close();
			}
			throw new DatabaseException("", e.getMessage(), e);
		} catch (Exception e) {
			if (null != cursor) {
				cursor.close();
			}
			if (null != categoryCursor) {
				categoryCursor.close();
			}
			throw new SystemException("UnexpectedError", e.getMessage(), e);
		}

		return result;
	}

	public void makeUserRatingPOI(Rating rating, Poi poi) {
		// Check if this rating exist to add new one
		if (this.mRatingDb == null)
			this.mRatingDb = new RatingsDbAccess(this.mDb);

		Cursor cursorRating = this.mRatingDb.fetchUserRatingPOI(rating
				.getUser().getId(), poi.getId());
		if (cursorRating.getCount() > 0) {
			// Update rating value of rating object if it exist
			rating.setId(cursorRating.getLong(cursorRating
					.getColumnIndex(mRatingDb.TABLE_KEY_RATING_ID)));
			this.mRatingDb.updateRating(rating);
		} else {
			// Add new
			this.mRatingDb.createRating(rating, poi.getId());
		}

		// Calculate the new value for POI rating and update to POI
		// TODO: calculate new rating value
		poi.setRating(rating.getRatingValue());
	}

	public Rating getRating(User user, long poiId) {
		// Check if this rating exist to add new one
		if (this.mRatingDb == null)
			this.mRatingDb = new RatingsDbAccess(this.mDb);
		Rating rating = new Rating();
		Cursor cursorRating = this.mRatingDb.fetchUserRatingPOI(user.getId(),
				poiId);
		if ((cursorRating != null) && (cursorRating.getCount() > 0)) {
			cursorRating.moveToFirst();
			rating.setUser(user);
			rating.setId(cursorRating.getLong(cursorRating
					.getColumnIndex(this.mRatingDb.TABLE_KEY_RATING_ID)));
			rating.setRatingValue(cursorRating.getLong(cursorRating
					.getColumnIndex(this.mRatingDb.TABLE_KEY_RATING_VALUE)));
		}
		return rating;
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

		// Check internal variable.
		if (null == this.mDb) {
			throw new SystemException("NotInitialized",
					"The content provider was not opened.");
		}

		// Initialize the POI Category database if needed.
		if (null == this.mCategoryDb) {
			this.mPictureDb = new PicturesDbAccess(this.mDb);
		}

		Cursor cursor = null;
		ArrayList<Picture> result = new ArrayList<Picture>();

		try {
			// Get all categories.
			cursor = this.mPictureDb.fetchAllPictures();

			if ((null == cursor) || (0 == cursor.getCount())) {
				return result;
			}

			while (!cursor.isAfterLast()) {
				Picture picture = new Picture();

				picture
						.setId(cursor
								.getLong(cursor
										.getColumnIndex(PicturesDbAccess.TABLE_KEY_PICTURE_ID)));
				picture.setLocation(cursor.getString(cursor
						.getColumnIndex(PicturesDbAccess.TABLE_KEY_LOCATION)));
				picture
						.setDescription(cursor
								.getString(cursor
										.getColumnIndex(PicturesDbAccess.TABLE_KEY_DESCRIPTIONS)));
				result.add(picture);

				cursor.moveToNext();
			}

			cursor.close();
			cursor = null;

		} catch (SQLiteException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new DatabaseException("", e.getMessage(), e);
		} catch (Exception e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new SystemException("UnexpectedError", e.getMessage(), e);
		}

		return result;
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

		// Check internal variable.
		if (null == this.mDb) {
			throw new SystemException("NotInitialized",
					"The content provider was not opened.");
		}

		// Initialize the POI Category database if needed.
		if (null == this.mPictureDb) {
			this.mPictureDb = new PicturesDbAccess(this.mDb);
		}

		Cursor cursor = null;
		ArrayList<Picture> result = new ArrayList<Picture>();

		try {
			// Get all categories.
			cursor = this.mPictureDb.fetchAllPoiPictures(poiId);

			if ((null == cursor) || (0 == cursor.getCount())) {
				return result;
			}

			while (!cursor.isAfterLast()) {
				Picture picture = new Picture();

				picture
						.setId(cursor
								.getLong(cursor
										.getColumnIndex(PicturesDbAccess.TABLE_KEY_PICTURE_ID)));
				picture.setLocation(cursor.getString(cursor
						.getColumnIndex(PicturesDbAccess.TABLE_KEY_LOCATION)));
				picture
						.setDescription(cursor
								.getString(cursor
										.getColumnIndex(PicturesDbAccess.TABLE_KEY_DESCRIPTIONS)));
				result.add(picture);

				cursor.moveToNext();
			}

			cursor.close();
			cursor = null;

		} catch (SQLiteException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new DatabaseException("", e.getMessage(), e);
		} catch (Exception e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new SystemException("UnexpectedError", e.getMessage(), e);
		}

		return result;
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

		// Check internal variable.
		if (null == this.mDb) {
			throw new SystemException("NotInitialized",
					"The content provider was not opened.");
		}

		// Initialize the POI Category database if needed.
		if (null == this.mPictureDb) {
			this.mPictureDb = new PicturesDbAccess(this.mDb);
		}

		Cursor cursor = null;
		ArrayList<Picture> result = new ArrayList<Picture>();

		try {
			// Get all categories.
			cursor = this.mPictureDb.fetchPicture(pictureId);

			if ((null == cursor) || (0 == cursor.getCount())) {
				return result;
			}

			while (!cursor.isAfterLast()) {
				Picture picture = new Picture();

				picture
						.setId(cursor
								.getLong(cursor
										.getColumnIndex(PicturesDbAccess.TABLE_KEY_PICTURE_ID)));
				picture.setLocation(cursor.getString(cursor
						.getColumnIndex(PicturesDbAccess.TABLE_KEY_LOCATION)));
				picture
						.setDescription(cursor
								.getString(cursor
										.getColumnIndex(PicturesDbAccess.TABLE_KEY_DESCRIPTIONS)));
				result.add(picture);

				cursor.moveToNext();
			}

			cursor.close();
			cursor = null;

		} catch (SQLiteException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new DatabaseException("", e.getMessage(), e);
		} catch (Exception e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new SystemException("UnexpectedError", e.getMessage(), e);
		}

		return result;
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
			throws InvalidDataException, DatabaseException, SystemException {

		// Check internal variable.
		if (null == this.mDb) {
			throw new SystemException("NotInitialized",
					"The content provider was not opened.");
		}

		// Check input data.
		if (null == picture) {
			throw new InvalidDataException("picture", "Input data is null.");
		}

		if ("".equals(picture.getLocation())) {
			throw new InvalidDataException("picture",
					"Picture's location is empty.");
		}

		picture.setActivated(true);
		picture.setBadReport(false);

		// Initialize the databases if needed.
		if (null == this.mPictureDb) {
			this.mPictureDb = new PicturesDbAccess(this.mDb);
		}
		try {
			// Add Picture
			final long pictureId = this.mPictureDb.createPicture(picture, poi
					.getId());
			if (0 >= pictureId) {
				throw new DatabaseException("", "Internal SQL error.");
			}
			picture.setId(pictureId);

		} catch (DatabaseException e) {
			throw e;
		}

		return true;
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
			DatabaseException, SystemException {

		// Check internal variable.
		if (null == this.mDb) {
			throw new SystemException("NotInitialized",
					"The content provider was not opened.");
		}

		// Check input data.
		if (null == picture) {
			throw new InvalidDataException("Picture", "Input data is null.");
		}

		if ("".equals(picture.getLocation())) {
			throw new InvalidDataException("Picture",
					"Picture's location is empty.");
		}

		picture.setActivated(true);
		picture.setBadReport(false);
		boolean result = false;
		// Initialize the databases if needed.
		if (null == this.mPictureDb) {
			this.mPictureDb = new PicturesDbAccess(this.mDb);
		}
		try {
			// Add Picture
			result = this.mPictureDb.updatePicture(picture);
			if (!result) {
				throw new DatabaseException("", "Internal SQL error.");
			}

		} catch (DatabaseException e) {
			throw e;
		}

		return result;
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

		// Check internal variable.
		if (null == this.mDb) {
			throw new SystemException("NotInitialized",
					"The content provider was not opened.");
		}

		// Check input data.
		if (0 == pictureId) {
			throw new InvalidDataException("pictureId", "Picture's ID is zero.");
		}

		// Initialize the databases if needed.
		if (null == this.mPictureDb) {
			this.mPictureDb = new PicturesDbAccess(this.mDb);
		}

		boolean result = false;
		Cursor cursor = null;

		try {
			// Check existing Picture.
			cursor = this.mPictureDb.fetchPicture(pictureId);
			if (0 == cursor.getCount()) {
				throw new DataAccessException("PictureNotExist",
						"The Picture is not exist.");
			}

			if (1 != cursor.getCount()) {
				throw new DatabaseException("", "The database has wrong data.");
			}

			// Delete Picture
			if (0 < pictureId) {
				this.mPictureDb.deletePicture(pictureId);
			}

			cursor.close();
			cursor = null;

		} catch (DataAccessException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw e;
		} catch (DatabaseException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw e;
		} catch (SQLiteException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new DatabaseException("", e.getMessage(), e);
		} catch (Exception e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new SystemException("UnexpectedError", e.getMessage(), e);
		}

		return result;
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

		// Check internal variable.
		if (null == this.mDb) {
			throw new SystemException("NotInitialized",
					"The content provider was not opened.");
		}

		// Check input data.
		if (0 == poiId) {
			throw new InvalidDataException("poiId", "POI's ID is zero.");
		}

		// Initialize the databases if needed.
		if (null == this.mPictureDb) {
			this.mPictureDb = new PicturesDbAccess(this.mDb);
		}

		boolean result = false;
		Cursor cursor = null;

		try {
			// Check existing Picture.
			cursor = this.mPoiDb.fetchPoi(poiId);
			if (0 == cursor.getCount()) {
				throw new DataAccessException("PictureNotExist",
						"The Picture is not exist.");
			}

			if (1 != cursor.getCount()) {
				throw new DatabaseException("", "The database has wrong data.");
			}

			// Delete Picture
			if (0 < poiId) {
				this.mPictureDb.deletePicture(poiId);
			}

			cursor.close();
			cursor = null;

		} catch (DataAccessException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw e;
		} catch (DatabaseException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw e;
		} catch (SQLiteException e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new DatabaseException("", e.getMessage(), e);
		} catch (Exception e) {
			if (null != cursor) {
				cursor.close();
			}
			throw new SystemException("UnexpectedError", e.getMessage(), e);
		}

		return result;
	}
}
