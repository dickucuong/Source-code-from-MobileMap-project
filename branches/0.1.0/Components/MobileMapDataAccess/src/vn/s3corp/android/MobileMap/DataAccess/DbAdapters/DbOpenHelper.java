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
                                                              + "is_activated BOOLEAN NOT NULL, "
                                                              + "address_id INTEGER INT);";
    private static final String DB_TABLE_USERS_DROP   = "DROP TABLE IF EXISTS users;";

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

        // Create new table.
        onCreate(db);
    }
}