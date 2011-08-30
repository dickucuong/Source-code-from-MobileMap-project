/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author hieu.ha
 * @version 0.2.0
 * 
 */
package vn.s3corp.android.MobileMap.UI.Dialogs;

import java.util.ArrayList;
import java.util.Date;

import vn.s3corp.android.MobileMap.DataAccess.ContentManager;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Category;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.LoginSession;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Poi;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DataAccessException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DatabaseException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.InvalidDataException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.SystemException;
import vn.s3corp.android.MobileMap.UI.MobileMapApp;
import vn.s3corp.android.MobileMap.UI.R;
import vn.s3corp.android.MobileMap.UI.Utilities.CategoryAdapter;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;

/**
 * The Dialog which is used to Update POI on Google Map
 * 
 * @author hieu.ha
 * @version 0.2.0
 * 
 */
public class AddUpdatePOIDialog extends Dialog implements View.OnClickListener {

    private GeoPoint mGeoPoint;// Point on MapView
    private final Context mContext;// Context of application
    private Boolean mIsDisplayMode;// True show on contextmenu,false show on
                                   // main menu
    private EditText txtLongitude;
    private EditText txtLatitude;
    private EditText txtName;
    private EditText txtDescription;
    private EditText txtAddress;
    private Boolean mIsAdd;// True: Add POI Dialog, False: Update POI dialog
    private Poi mPoi;
    private ArrayList<Category> mListCategories;

    /**
     * The constructor.
     * 
     * @param context
     *            The context which this dialog will work
     * @return
     * @author hieu.ha
     * @version 0.2.0
     * 
     */
    public AddUpdatePOIDialog(Context context) {
        super(context);
        this.mContext = context;
        // TODO Auto-generated constructor stub
    }

    /**
     * set GeoPoint
     * 
     * @param geopoint
     * @author hieu.ha
     */
    public void setGeoPoint(GeoPoint geopoint) {
        this.mGeoPoint = geopoint;
    }

    /**
     * Set Display Mode
     * 
     * @param IsDisplayMode
     * @author hieu.ha
     */
    public void setIsDisplayMode(Boolean IsDisplayMode) {
        this.mIsDisplayMode = IsDisplayMode;
    }

    /**
     * Set Poi
     * 
     * @param poi
     * @author hieu.ha
     */
    public void setPOI(Poi poi) {
        this.mPoi = poi;
    }

    public void setListCategories(ArrayList<Category> listcategories) {
        this.mListCategories = listcategories;
    }

    /**
     * The constructor.
     * 
     * @param context
     *            The context which this dialog will work
     * @param geopoint
     *            The GeoPoint of POI
     * @param isDisplayOnMap
     *            The display of Update dialog
     * @return
     * @author hieu.ha
     * @version 0.2.0
     * 
     */
    public AddUpdatePOIDialog(Context context, GeoPoint geopoint, Boolean isDisplayOnMap,
            Boolean isAdd) {
        super(context);
        this.mGeoPoint = geopoint;
        this.mContext = context;
        this.mIsDisplayMode = isDisplayOnMap;
        this.mIsAdd = isAdd;
        // TODO Auto-generated constructor stub
    }

    /**
     * Override onStart.
     * 
     * @author hieu.ha
     */
    @Override
    protected void onStart() {
        // Check display mode
        if (mIsDisplayMode) {
            txtLongitude.setEnabled(false);
            txtLatitude.setEnabled(false);
            txtLongitude.setFocusable(false);
            txtLatitude.setFocusable(false);
            if (mIsAdd && null != mGeoPoint) {
                double longitude = mGeoPoint.getLongitudeE6() / 1E6;
                double latitude = mGeoPoint.getLatitudeE6() / 1E6;
                // Set Longitude
                txtLongitude.setText(new Double(longitude).toString());
                // Set Latitude
                txtLatitude.setText(new Double(latitude).toString());
                // Create new POI when this is Add dialog
                mPoi = new Poi();
                mListCategories = new ArrayList<Category>();
            }
        } else {
            txtLongitude.setEnabled(true);
            txtLatitude.setEnabled(true);
        }
        MobileMapApp app = (MobileMapApp) mContext.getApplicationContext();
        // Category
        final Spinner spnCategoty = (Spinner) findViewById(R.id.dialog_add_poi_spinner_categoty);
        try {
            // Get All Categories
            ContentManager ContentManager = app.getContentManager();
            ArrayList<Category> allCategories = new ArrayList<Category>();
            allCategories = ContentManager.getAllPoiCategories();
            // Create a Adapter
            final CategoryAdapter adapter = new CategoryAdapter(this.getContext(), allCategories);
            // Apply the Adapter to Spinner
            spnCategoty.setAdapter(adapter);
            // Handle selected event item of spinner
            spnCategoty.setOnItemSelectedListener(new OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long i) {
                    Category SelectedCategory = adapter.getItem(position);

                    if (null == mListCategories) {
                        mListCategories = new ArrayList<Category>();
                    } else {
                        mListCategories.add(SelectedCategory);
                    }
                    // Add category to POI
                    if (null == mPoi) {
                        mPoi = new Poi();
                    } else {
                        mPoi.addCategory(SelectedCategory);
                        mPoi.setCategories(mListCategories);
                    }
                    // TODO Process values
                }

                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub

                }
            });
        } catch (DataAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DatabaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SystemException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Override onCreate.
     * 
     * @param savedInstanceState
     * @return
     * @author hieu.ha
     * @version 0.2.0
     * 
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the layout.
        setContentView(R.layout.dialog_add_poi);
        // Using button
        Button btnCancel = (Button) findViewById(R.id.dialog_add_poi_button_cancel);
        Button btnUpdatePOI = (Button) findViewById(R.id.dialog_add_poi_button_addpoi);

        if (mIsAdd) {
            // Set text of Button
            btnUpdatePOI.setText(R.string.menu_addpoi);
            // Set title of dialog
            setTitle(R.string.menu_addpoi);

        } else {
            // Set text of Button
            btnUpdatePOI.setText(R.string.lable_update);
            // Set title of dialog
            setTitle(R.string.menu_updatepoi);
        }

        // Handle event for button
        btnCancel.setOnClickListener(this);
        btnUpdatePOI.setOnClickListener(this);

        // Disable EditText
        txtLongitude = (EditText) findViewById(R.id.dialog_add_poi_edit_longitude);
        txtLatitude = (EditText) findViewById(R.id.dialog_add_poi_edit_latitude);
        txtName = (EditText) findViewById(R.id.dialog_add_poi_edit_name);
        txtDescription = (EditText) findViewById(R.id.dialog_add_poi_edit_description);
        txtAddress = (EditText) findViewById(R.id.dialog_add_poi_edit_address);

    }

    /**
     * The event handler of click events.
     * 
     * @param arg0
     *            the View object which receive click event
     * @return
     * @author hieu.ha
     * @version 0.2.0
     * 
     */
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        switch (arg0.getId()) {
        case R.id.dialog_add_poi_button_cancel: // Dismiss the dialog.
            this.dismiss();
            break;
        case R.id.dialog_add_poi_button_addpoi:// Add POI into databse

            if (txtLongitude.getText().toString().equals("")) {
                Toast.makeText(this.mContext, "Longitude is empty.", Toast.LENGTH_SHORT).show();

                break;
            }
            if (txtLatitude.getText().toString().equals("")) {
                Toast.makeText(this.mContext, "Latitude is empty.", Toast.LENGTH_SHORT).show();

                break;
            }
            if (txtName.getText().toString().equals("")) {
                Toast.makeText(this.mContext, "Name is empty.", Toast.LENGTH_SHORT).show();

                break;
            }
            MobileMapApp app = (MobileMapApp) mContext.getApplicationContext();
            if (mIsAdd) {
                try {
                    ContentManager ContentManager = app.getContentManager();
                    // Create new POI
                    if (null != mPoi) {
                        mPoi.setLatitude((long) mGeoPoint.getLatitudeE6());
                        mPoi.setLongitude((long) mGeoPoint.getLongitudeE6());
                        mPoi.setName(txtName.getText().toString());
                        mPoi.setDescriptions(txtDescription.getText().toString());
                        mPoi.setRating(0);
                        // Create address object
                        vn.s3corp.android.MobileMap.DataAccess.DataObjects.Address address = new vn.s3corp.android.MobileMap.DataAccess.DataObjects.Address();
                        address.setAddressString(txtAddress.getText().toString());
                        mPoi.setAddress(address);
                        // Set the state of POI
                        RadioButton radPulic = (RadioButton) this
                                .findViewById(R.id.dialog_add_poi_radio_public);
                        if (radPulic.isChecked()) {
                            mPoi.setPoiType(Poi.POI_TYPE_PUBLIC);
                        } else {
                            mPoi.setPoiType(Poi.POI_TYPE_PRIVATE);
                        }
                        // Current user
                        LoginSession loginsession = app.getSession();
                        if (app.checkLoginStatus() && null != loginsession) {
                            mPoi.setCreationUser(loginsession.getUser());
                            mPoi.setApproveUser(loginsession.getUser());
                        }
                        // Set actived
                        mPoi.setActivated(true);
                        // Current date
                        Date createDate = new Date();
                        mPoi.setCreationDate(createDate);
                        mPoi.setApproved(false);
                        mPoi.setApproveDate(createDate);
                        // Save POI to MM system
                        boolean isSuccessful = ContentManager.addPoi(mPoi);
                        if (isSuccessful) {
                            Toast.makeText(getContext(), "Add POI successfully", Toast.LENGTH_LONG)
                                    .show();
                            this.dismiss();
                        }
                    }
                } catch (DataAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (DatabaseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (InvalidDataException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SystemException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(getContext(), "Update POI successfully", Toast.LENGTH_LONG).show();
                this.dismiss();
            }

            break;
        }
    }

    /**
     * Reset the content of all text boxes in this dialog.
     * 
     * @param
     * @return
     * @author hieu.ha
     * @version 0.1.0
     * 
     */
    public void clearEditText() {
        txtName.setText("");
        txtDescription.setText("");
        txtAddress.setText("");
        txtLongitude.setText("");
        txtLatitude.setText("");
    }
}
