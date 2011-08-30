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

    private static final String DB_TABLE_USERS_CREATE = "CREATE TABLE users (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "loginname VARCHAR(255) NOT NULL, "
            + "password VARCHAR(255) NOT NULL, "
            + "user_type INTEGER NOT NULL, "
            + "creation_datetime DATETIME NOT NULL, "
            + "is_activated BOOLEAN NOT NULL, " + "address_id INTEGER);";
    private static final String DB_TABLE_USERS_DROP = "DROP TABLE IF EXISTS users;";

    private static final String DB_TABLE_LOGIN_SESSIONS_CREATE = "CREATE TABLE login_sessions (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "user_id INTEGER NOT NULL, "
            + "login_datetime DATETIME NOT NULL, "
            + "logout_datetime DATETIME, " + "is_closed BOOLEAN NOT NULL);";
    private static final String DB_TABLE_LOGIN_SESSIONS_DROP = "DROP TABLE IF EXISTS login_sessions;";

    private static final String DB_TABLE_CATEGORIES_CREATE = "CREATE TABLE categories (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "name_en VARCHAR(255) NOT NULL);";
    private static final String DB_TABLE_CATEGORIES_DROP = "DROP TABLE IF EXISTS categories;";
    private static final String DB_TABLE_CATEGORIES_INITIALIZE_RESTAURANT = "INSERT INTO categories (_id, name_en) VALUES (null, 'Restaurant');";
    private static final String DB_TABLE_CATEGORIES_INITIALIZE_COFFEE = "INSERT INTO categories (_id, name_en) VALUES (null, 'Coffee');";
    private static final String DB_TABLE_CATEGORIES_INITIALIZE_BAR = "INSERT INTO categories (_id, name_en) VALUES (null, 'Bar');";
    private static final String DB_TABLE_CATEGORIES_INITIALIZE_HOTEL = "INSERT INTO categories (_id, name_en) VALUES (null, 'Hotel');";
    private static final String DB_TABLE_CATEGORIES_INITIALIZE_ATTRACTION = "INSERT INTO categories (_id, name_en) VALUES (null, 'Attraction');";
    private static final String DB_TABLE_CATEGORIES_INITIALIZE_ATM = "INSERT INTO categories (_id, name_en) VALUES (null, 'ATM');";
    private static final String DB_TABLE_CATEGORIES_INITIALIZE_GAS_STATION = "INSERT INTO categories (_id, name_en) VALUES (null, 'Gas Statison');";

    private static final String DB_TABLE_POIS_CREATE = "CREATE TABLE pois (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "longitude INTEGER NOT NULL, "
            + "latitude INTEGER NOT NULL, "
            + "poi_type INTEGER NOT NULL, "
            + "name VARCHAR(255) NOT NULL, "
            + "descriptions TEXT, "
            + "rating_value INTEGER NOT NULL, "
            + "address_id INTEGER, "
            + "creation_datetime DATETIME NOT NULL, "
            + "creation_user_id INTEGER NOT NULL, "
            + "is_approved BOOLEAN NOT NULL, "
            + "approve_datetime DATETIME NOT NULL, "
            + "approve_user_id INTEGER NOT NULL, " + "is_activated BOOLEAN NOT NULL);";
    private static final String DB_TABLE_POIS_DROP = "DROP TABLE IF EXISTS pois;";

    private static final String DB_TABLE_ADDRESSES_CREATE = "CREATE TABLE addresses (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "address_string TEXT NOT NULL);";
    private static final String DB_TABLE_ADDRESSES_DROP = "DROP TABLE IF EXISTS addresses;";

    private static final String DB_TABLE_POIS_CATEGORIES_CREATE = "CREATE TABLE pois_categories (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "poi_id INTEGER NOT NULL, " + "category_id INTEGER NOT NULL);";
    private static final String DB_TABLE_POIS_CATEGORIES_DROP = "DROP TABLE IF EXISTS pois_categories;";

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
        db.execSQL(DbOpenHelper.DB_TABLE_POIS_CATEGORIES_CREATE);

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
        db.execSQL(DbOpenHelper.DB_TABLE_POIS_CATEGORIES_DROP);

        // Create new table.
        onCreate(db);
    }
}