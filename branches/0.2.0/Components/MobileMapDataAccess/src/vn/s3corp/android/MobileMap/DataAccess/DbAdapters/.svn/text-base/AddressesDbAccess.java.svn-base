/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.DataAccess.DbAdapters;

import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Address;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * The local database adapter for 'addresses' table.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class AddressesDbAccess {

    public static final String TABLE_NAME = "addresses";

    public static final String TABLE_KEY_ADDRESS_ID = "_id";
    public static final String TABLE_KEY_ADDRESS_STRING = "address_string";

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
    public AddressesDbAccess(SQLiteDatabase db) {
        this.mDb = db;
    }

    /**
     * Create a new address value in Mobile Map database. If the address is
     * successfully created, this method will return the new rowId value,
     * otherwise return a '-1' value to indicate failure.
     * 
     * @param address
     *            The data of input address
     * @return The category ID or -1 if failed.
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public long createAddress(Address address) {

        ContentValues values = new ContentValues();

        values.put(AddressesDbAccess.TABLE_KEY_ADDRESS_STRING, address.getAddressString());

        return this.mDb.insert(AddressesDbAccess.TABLE_NAME, null, values);
    }

    /**
     * Delete address with the given id.
     * 
     * @param addressId
     *            The ID of address which is deleted
     * @return true if address is deleted, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean deleteAddress(long addressId) {

        String table = AddressesDbAccess.TABLE_NAME;
        String whereClause = AddressesDbAccess.TABLE_KEY_ADDRESS_ID + "=?";
        String[] whereArgs = new String[] { "" + addressId };

        final int rowCnt = this.mDb.delete(table, whereClause, whereArgs);

        return (rowCnt > 0);
    }

    /**
     * Get a Cursor of a list of all addresses in the database.
     * 
     * @param
     * @return Cursor over all addresses
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAllAddresses() {

        String table = AddressesDbAccess.TABLE_NAME;
        String[] columns = new String[] { AddressesDbAccess.TABLE_KEY_ADDRESS_ID,
                AddressesDbAccess.TABLE_KEY_ADDRESS_STRING };
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
     * Get a Cursor which is positioned at the address that matches the given
     * id.
     * 
     * @param addressId
     *            The address's ID
     * @return The cursor which is positioned to matching one, null if no
     *         address could be found or retrieved
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public Cursor fetchAddress(long addressId) {

        String table = AddressesDbAccess.TABLE_NAME;
        String[] columns = new String[] { AddressesDbAccess.TABLE_KEY_ADDRESS_ID,
                AddressesDbAccess.TABLE_KEY_ADDRESS_STRING };
        String selection = AddressesDbAccess.TABLE_KEY_ADDRESS_ID + "=?";
        String[] selectionArgs = new String[] { "" + addressId };
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
     * Update address using the details provided.
     * 
     * @param address
     *            The address which is updated.
     * @return true if address was successfully updated, false otherwise
     * @author nam.tran
     * @version 0.1.0
     * 
     */
    public boolean updateAddress(Address address) {

        ContentValues values = new ContentValues();

        if (!"".equals(address.getAddressString())) {
            values.put(AddressesDbAccess.TABLE_KEY_ADDRESS_STRING, address.getAddressString());
        }

        String table = AddressesDbAccess.TABLE_NAME;
        String whereClause = AddressesDbAccess.TABLE_KEY_ADDRESS_ID + "=?";
        String[] whereArgs = new String[] { "" + address.getId() };

        final int rowCnt = this.mDb.update(table, values, whereClause, whereArgs);

        return (rowCnt > 0);
    }
}
