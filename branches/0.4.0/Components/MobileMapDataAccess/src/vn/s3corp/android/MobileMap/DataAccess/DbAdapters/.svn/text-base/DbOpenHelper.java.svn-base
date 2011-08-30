/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */
package vn.s3corp.android.MobileMap.DataAccess.DbAdapters;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * A helper class which helps to manage database creation and version
 * management.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class DbOpenHelper extends SQLiteOpenHelper {

    private static final String DB_TABLE_USERS_CREATE = "CREATE TABLE " + UsersDbAccess.TABLE_NAME
            + " (" + UsersDbAccess.TABLE_KEY_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + UsersDbAccess.TABLE_KEY_LOGINNAME + " VARCHAR(255) NOT NULL, "
            + UsersDbAccess.TABLE_KEY_PASSWORD + " VARCHAR(255) NOT NULL, "
            + UsersDbAccess.TABLE_KEY_USER_TYPE + " INTEGER NOT NULL, "
            + UsersDbAccess.TABLE_KEY_CREATION_DATETIME + " DATETIME NOT NULL, "
            + UsersDbAccess.TABLE_KEY_IS_ACTIVATED + " BOOLEAN NOT NULL, "
            + UsersDbAccess.TABLE_KEY_ADDRESS_ID + " INTEGER);";
    private static final String DB_TABLE_USERS_DROP = "DROP TABLE IF EXISTS "
            + UsersDbAccess.TABLE_NAME + ";";

    private static final String DB_TABLE_LOGIN_SESSIONS_CREATE = "CREATE TABLE "
            + LoginSessionDbAccess.TABLE_NAME + " (" + LoginSessionDbAccess.TABLE_KEY_SESSION_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + LoginSessionDbAccess.TABLE_KEY_USER_ID
            + " INTEGER NOT NULL, " + LoginSessionDbAccess.TABLE_KEY_LOGIN_DATETIME
            + " DATETIME NOT NULL, " + LoginSessionDbAccess.TABLE_KEY_LOGOUT_DATETIME
            + " DATETIME, " + LoginSessionDbAccess.TABLE_KEY_IS_CLOSED + " BOOLEAN NOT NULL);";
    private static final String DB_TABLE_LOGIN_SESSIONS_DROP = "DROP TABLE IF EXISTS "
            + LoginSessionDbAccess.TABLE_NAME + ";";

    private static final String DB_TABLE_CATEGORIES_CREATE = "CREATE TABLE "
            + CategoriesDbAccess.TABLE_NAME + " (" + CategoriesDbAccess.TABLE_KEY_CATEGORY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CategoriesDbAccess.TABLE_KEY_CATEGORY_NAME_EN + " VARCHAR(255) NOT NULL);";
    private static final String DB_TABLE_CATEGORIES_DROP = "DROP TABLE IF EXISTS "
            + CategoriesDbAccess.TABLE_NAME + ";";
    private static final String DB_TABLE_CATEGORIES_INITIALIZE_RESTAURANT = "INSERT INTO "
            + CategoriesDbAccess.TABLE_NAME + " (" + CategoriesDbAccess.TABLE_KEY_CATEGORY_ID
            + ", " + CategoriesDbAccess.TABLE_KEY_CATEGORY_NAME_EN
            + ") VALUES (null, 'Restaurant');";
    private static final String DB_TABLE_CATEGORIES_INITIALIZE_COFFEE = "INSERT INTO "
            + CategoriesDbAccess.TABLE_NAME + " (" + CategoriesDbAccess.TABLE_KEY_CATEGORY_ID
            + ", " + CategoriesDbAccess.TABLE_KEY_CATEGORY_NAME_EN + ") VALUES (null, 'Coffee');";
    private static final String DB_TABLE_CATEGORIES_INITIALIZE_BAR = "INSERT INTO "
            + CategoriesDbAccess.TABLE_NAME + " (" + CategoriesDbAccess.TABLE_KEY_CATEGORY_ID
            + ", " + CategoriesDbAccess.TABLE_KEY_CATEGORY_NAME_EN + ") VALUES (null, 'Bar');";
    private static final String DB_TABLE_CATEGORIES_INITIALIZE_HOTEL = "INSERT INTO "
            + CategoriesDbAccess.TABLE_NAME + " (" + CategoriesDbAccess.TABLE_KEY_CATEGORY_ID
            + ", " + CategoriesDbAccess.TABLE_KEY_CATEGORY_NAME_EN + ") VALUES (null, 'Hotel');";
    private static final String DB_TABLE_CATEGORIES_INITIALIZE_ATTRACTION = "INSERT INTO "
            + CategoriesDbAccess.TABLE_NAME + " (" + CategoriesDbAccess.TABLE_KEY_CATEGORY_ID
            + ", " + CategoriesDbAccess.TABLE_KEY_CATEGORY_NAME_EN
            + ") VALUES (null, 'Attraction');";
    private static final String DB_TABLE_CATEGORIES_INITIALIZE_ATM = "INSERT INTO "
            + CategoriesDbAccess.TABLE_NAME + " (" + CategoriesDbAccess.TABLE_KEY_CATEGORY_ID
            + ", " + CategoriesDbAccess.TABLE_KEY_CATEGORY_NAME_EN + ") VALUES (null, 'ATM');";
    private static final String DB_TABLE_CATEGORIES_INITIALIZE_GAS_STATION = "INSERT INTO "
            + CategoriesDbAccess.TABLE_NAME + " (" + CategoriesDbAccess.TABLE_KEY_CATEGORY_ID
            + ", " + CategoriesDbAccess.TABLE_KEY_CATEGORY_NAME_EN
            + ") VALUES (null, 'Gas Statison');";

    private static final String DB_TABLE_POIS_CREATE = "CREATE TABLE " + PoisDbAccess.TABLE_NAME
            + " (" + PoisDbAccess.TABLE_KEY_POI_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PoisDbAccess.TABLE_KEY_LONGITUDE + " INTEGER NOT NULL, "
            + PoisDbAccess.TABLE_KEY_LATITUDE + " INTEGER NOT NULL, "
            + PoisDbAccess.TABLE_KEY_POI_TYPE + " INTEGER NOT NULL, "
            + PoisDbAccess.TABLE_KEY_POI_NAME + " VARCHAR(255) NOT NULL, "
            + PoisDbAccess.TABLE_KEY_DESCRIPTIONS + " TEXT, " + PoisDbAccess.TABLE_KEY_RATING_VALUE
            + " INTEGER NOT NULL, " + PoisDbAccess.TABLE_KEY_ADDRESS_ID + " INTEGER, "
            + PoisDbAccess.TABLE_KEY_CREATION_DATETIME + " DATETIME NOT NULL, "
            + PoisDbAccess.TABLE_KEY_CREATION_USER_ID + " INTEGER NOT NULL, "
            + PoisDbAccess.TABLE_KEY_IS_APPROVED + " BOOLEAN NOT NULL, "
            + PoisDbAccess.TABLE_KEY_APPROVE_DATETIME + " DATETIME NOT NULL, "
            + PoisDbAccess.TABLE_KEY_APPROVE_USER_ID + " INTEGER NOT NULL, "
            + PoisDbAccess.TABLE_KEY_IS_ACTIVATED + " BOOLEAN NOT NULL);";
    private static final String DB_TABLE_POIS_DROP = "DROP TABLE IF EXISTS "
            + PoisDbAccess.TABLE_NAME + ";";

    private static final String DB_TABLE_ADDRESSES_CREATE = "CREATE TABLE "
            + AddressesDbAccess.TABLE_NAME + " (" + AddressesDbAccess.TABLE_KEY_ADDRESS_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + AddressesDbAccess.TABLE_KEY_ADDRESS_STRING
            + " TEXT NOT NULL);";
    private static final String DB_TABLE_ADDRESSES_DROP = "DROP TABLE IF EXISTS "
            + AddressesDbAccess.TABLE_NAME + ";";

    private static final String DB_TABLE_PHONE_NUMBERS_CREATE = "CREATE TABLE "
            + PhoneNumbersDbAccess.TABLE_NAME + " ("
            + PhoneNumbersDbAccess.TABLE_KEY_PHONENUMBER_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PhoneNumbersDbAccess.TABLE_KEY_USER_ID
            + " INTEGER NOT NULL, " + PhoneNumbersDbAccess.TABLE_KEY_POI_ID + " INTEGER NOT NULL, "
            + PhoneNumbersDbAccess.TABLE_KEY_PHONENUMBER_STRING + " VARCHAR(50) NOT NULL);";
    private static final String DB_TABLE_PHONE_NUMBERS_DROP = "DROP TABLE IF EXISTS "
            + PhoneNumbersDbAccess.TABLE_NAME + ";";

    private static final String DB_TABLE_EMAILS_CREATE = "CREATE TABLE "
            + EmailsDbAccess.TABLE_NAME + " (" + EmailsDbAccess.TABLE_KEY_EMAIL_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + EmailsDbAccess.TABLE_KEY_USER_ID
            + " INTEGER NOT NULL, " + EmailsDbAccess.TABLE_KEY_POI_ID + " INTEGER NOT NULL, "
            + EmailsDbAccess.TABLE_KEY_EMAIL_STRING + " VARCHAR(255) NOT NULL);";
    private static final String DB_TABLE_EMAILS_DROP = "DROP TABLE IF EXISTS "
            + EmailsDbAccess.TABLE_NAME + ";";

    private static final String DB_TABLE_POIS_CATEGORIES_CREATE = "CREATE TABLE "
            + PoisCategoriesDbAccess.TABLE_NAME + " (" + PoisCategoriesDbAccess.TABLE_KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PoisCategoriesDbAccess.TABLE_KEY_POI_ID
            + " INTEGER NOT NULL, " + PoisCategoriesDbAccess.TABLE_KEY_CATEGORY_ID
            + " INTEGER NOT NULL);";
    private static final String DB_TABLE_POIS_CATEGORIES_DROP = "DROP TABLE IF EXISTS "
            + PoisCategoriesDbAccess.TABLE_NAME + ";";

    private static final String DB_TABLE_FAVORITE_POIS_CREATE = "CREATE TABLE "
            + FavoritePoisDbAccess.TABLE_NAME + " (" + FavoritePoisDbAccess.TABLE_KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + FavoritePoisDbAccess.TABLE_KEY_USER_ID
            + " INTEGER NOT NULL, " + FavoritePoisDbAccess.TABLE_KEY_POI_ID + " INTEGER NOT NULL);";
    private static final String DB_TABLE_FAVORITE_POIS_DROP = "DROP TABLE IF EXISTS "
            + FavoritePoisDbAccess.TABLE_NAME + ";";

    private static final String DB_TABLE_RATE_POIS_CREATE = "CREATE TABLE "
            + RatingsDbAccess.TABLE_NAME + " (" + RatingsDbAccess.TABLE_KEY_RATING_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + RatingsDbAccess.TABLE_KEY_USER_ID
            + " INTEGER NOT NULL, " + RatingsDbAccess.TABLE_KEY_POI_ID + " INTEGER NOT NULL, "
            + RatingsDbAccess.TABLE_KEY_RATING_VALUE + " INTEGER NOT NULL);";
    private static final String DB_TABLE_RATE_POIS_DROP = "DROP TABLE IF EXISTS "
            + RatingsDbAccess.TABLE_NAME + ";";

    private static final String DB_TABLE_PICTURES_CREATE = "CREATE TABLE "
            + PicturesDbAccess.TABLE_NAME + " (" + PicturesDbAccess.TABLE_KEY_PICTURE_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PicturesDbAccess.TABLE_KEY_POI_ID
            + " INTEGER NOT NULL, " + PicturesDbAccess.TABLE_KEY_LOCATION
            + " VARCHAR(255) NOT NULL, " + PicturesDbAccess.TABLE_KEY_DESCRIPTIONS + " TEXT, "
            + PicturesDbAccess.TABLE_KEY_IS_BAD_REPORTED + " BOOLEAN NOT NULL, "
            + PicturesDbAccess.TABLE_KEY_IS_ACTIVATED + " BOOLEAN NOT NULL);";
    private static final String DB_TABLE_PICTURES_DROP = "DROP TABLE IF EXISTS "
            + PicturesDbAccess.TABLE_NAME + ";";

    private static final String DB_TABLE_VIDEO_CLIPS_CREATE = "CREATE TABLE "
            + VideoClipsDbAccess.TABLE_NAME + " (" + VideoClipsDbAccess.TABLE_KEY_VIDEO_CLIP_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + VideoClipsDbAccess.TABLE_KEY_POI_ID
            + " INTEGER NOT NULL, " + VideoClipsDbAccess.TABLE_KEY_LOCATION
            + " VARCHAR(255) NOT NULL, " + VideoClipsDbAccess.TABLE_KEY_DESCRIPTIONS + " TEXT, "
            + VideoClipsDbAccess.TABLE_KEY_IS_BAD_REPORTED + " BOOLEAN NOT NULL, "
            + VideoClipsDbAccess.TABLE_KEY_IS_ACTIVATED + " BOOLEAN NOT NULL);";
    private static final String DB_TABLE_VIDEO_CLIPS_DROP = "DROP TABLE IF EXISTS "
            + VideoClipsDbAccess.TABLE_NAME + ";";

    private static final String DB_TABLE_COMMENTS_CREATE = "CREATE TABLE "
            + CommentsDbAccess.TABLE_NAME + " (" + CommentsDbAccess.TABLE_KEY_COMMENT_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + CommentsDbAccess.TABLE_KEY_USER_ID
            + " INTEGER NOT NULL, " + CommentsDbAccess.TABLE_KEY_POI_ID + " INTEGER NOT NULL, "
            + CommentsDbAccess.TABLE_KEY_COMMENT_STRING + " TEXT, "
            + CommentsDbAccess.TABLE_KEY_IS_BAD_REPORTED + " BOOLEAN NOT NULL, "
            + CommentsDbAccess.TABLE_KEY_CREATION_DATETIME + " DATETIME NOT NULL, "
            + CommentsDbAccess.TABLE_KEY_IS_ACTIVATED + " BOOLEAN NOT NULL);";
    private static final String DB_TABLE_COMMENTS_DROP = "DROP TABLE IF EXISTS "
            + CommentsDbAccess.TABLE_NAME + ";";

    /**
     * The constructor.
     * 
     * @param context
     *            The context which is used to open or create the database
     * @return
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public DbOpenHelper(Context context) {
        super(context, Utility.DATABASE_NAME, null, Utility.DATABASE_VERSION);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database
     * .sqlite.SQLiteDatabase)
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DbOpenHelper.DB_TABLE_USERS_CREATE);
        db.execSQL(DbOpenHelper.DB_TABLE_LOGIN_SESSIONS_CREATE);
        db.execSQL(DbOpenHelper.DB_TABLE_CATEGORIES_CREATE);
        db.execSQL(DbOpenHelper.DB_TABLE_POIS_CREATE);
        db.execSQL(DbOpenHelper.DB_TABLE_ADDRESSES_CREATE);
        db.execSQL(DbOpenHelper.DB_TABLE_PHONE_NUMBERS_CREATE);
        db.execSQL(DbOpenHelper.DB_TABLE_EMAILS_CREATE);
        db.execSQL(DbOpenHelper.DB_TABLE_POIS_CATEGORIES_CREATE);
        db.execSQL(DbOpenHelper.DB_TABLE_FAVORITE_POIS_CREATE);
        db.execSQL(DbOpenHelper.DB_TABLE_RATE_POIS_CREATE);
        db.execSQL(DbOpenHelper.DB_TABLE_PICTURES_CREATE);
        db.execSQL(DbOpenHelper.DB_TABLE_VIDEO_CLIPS_CREATE);
        db.execSQL(DbOpenHelper.DB_TABLE_COMMENTS_CREATE);

        // Initialize data.
        db.execSQL(DbOpenHelper.DB_TABLE_CATEGORIES_INITIALIZE_RESTAURANT);
        db.execSQL(DbOpenHelper.DB_TABLE_CATEGORIES_INITIALIZE_COFFEE);
        db.execSQL(DbOpenHelper.DB_TABLE_CATEGORIES_INITIALIZE_BAR);
        db.execSQL(DbOpenHelper.DB_TABLE_CATEGORIES_INITIALIZE_HOTEL);
        db.execSQL(DbOpenHelper.DB_TABLE_CATEGORIES_INITIALIZE_ATTRACTION);
        db.execSQL(DbOpenHelper.DB_TABLE_CATEGORIES_INITIALIZE_ATM);
        db.execSQL(DbOpenHelper.DB_TABLE_CATEGORIES_INITIALIZE_GAS_STATION);
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database
     * .sqlite.SQLiteDatabase, int, int)
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Delete old table.
        db.execSQL(DbOpenHelper.DB_TABLE_USERS_DROP);
        db.execSQL(DbOpenHelper.DB_TABLE_LOGIN_SESSIONS_DROP);
        db.execSQL(DbOpenHelper.DB_TABLE_CATEGORIES_DROP);
        db.execSQL(DbOpenHelper.DB_TABLE_POIS_DROP);
        db.execSQL(DbOpenHelper.DB_TABLE_ADDRESSES_DROP);
        db.execSQL(DbOpenHelper.DB_TABLE_PHONE_NUMBERS_DROP);
        db.execSQL(DbOpenHelper.DB_TABLE_EMAILS_DROP);
        db.execSQL(DbOpenHelper.DB_TABLE_POIS_CATEGORIES_DROP);
        db.execSQL(DbOpenHelper.DB_TABLE_FAVORITE_POIS_DROP);
        db.execSQL(DbOpenHelper.DB_TABLE_RATE_POIS_DROP);
        db.execSQL(DbOpenHelper.DB_TABLE_PICTURES_DROP);
        db.execSQL(DbOpenHelper.DB_TABLE_VIDEO_CLIPS_DROP);
        db.execSQL(DbOpenHelper.DB_TABLE_COMMENTS_DROP);

        // Create new table.
        onCreate(db);
    }
}