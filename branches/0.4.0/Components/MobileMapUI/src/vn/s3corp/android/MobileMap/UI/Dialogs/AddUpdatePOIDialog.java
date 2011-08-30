/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author hieu.ha
 * @version 0.2.0
 * 
 */
package vn.s3corp.android.MobileMap.UI.Dialogs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import vn.s3corp.android.MobileMap.DataAccess.ContentManager;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Category;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.LoginSession;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Picture;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Poi;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DataAccessException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DatabaseException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.InvalidDataException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.SystemException;
import vn.s3corp.android.MobileMap.UI.MobileMapApp;
import vn.s3corp.android.MobileMap.UI.R;
import vn.s3corp.android.MobileMap.UI.Activities.LoadImageActivity;
import vn.s3corp.android.MobileMap.UI.Activities.MainMapActivity;
import vn.s3corp.android.MobileMap.UI.Utilities.CategoryAdapter;
import vn.s3corp.android.MobileMap.UI.Utilities.DataManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    private RadioButton radPulic;
    private RadioButton radPrivate;

    private Boolean mIsAdd;// True: Add POI Dialog, False: Update POI dialog
    private Poi mPoi;// POI of dialog
    private ArrayList<Category> mListCategories;// Using store POI's categories
    private CategoryAdapter mSpinnerAdapter;// Spinner's Adapter
    private ArrayList<Picture> mPictures;// POI's picture
    private final static File DIR_SDCARD = Environment.getExternalStorageDirectory();
    /**
     * Add picture for special POI
     * 
     * @author anh.trinh
     * @version 0.4.0
     * 
     */
    public static String[] arraypath;
    public static ImageView imageView_picture_poi;

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

    /**
     * Set POI's categories
     * 
     * @param poi
     * @author hieu.ha
     */
    public void setListCategories(ArrayList<Category> listcategories) {
        this.mListCategories = listcategories;
    }

    /**
     * Override onStart.
     * 
     * @author hieu.ha
     */
    @Override
    protected void onStart() {
        imageView_picture_poi = (ImageView) findViewById(R.id.dialog_add_poi_picture);
        imageView_picture_poi.setOnClickListener(this);
        MobileMapApp app = (MobileMapApp) mContext.getApplicationContext();
        final Spinner spnCategoty = (Spinner) findViewById(R.id.dialog_add_poi_spinner_categoty);
        ArrayList<Category> allCategories = new ArrayList<Category>();
        // Load Category
        try {
            // Get All Categories
            ContentManager ContentManager = app.getContentManager();
            allCategories = ContentManager.getAllPoiCategories();
            // Create a Adapter
            mSpinnerAdapter = new CategoryAdapter(this.getContext(), allCategories,
                    CategoryAdapter.SPINNER);
            // Apply the Adapter to Spinner
            spnCategoty.setAdapter(mSpinnerAdapter);

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

        // Check display mode
        if (mIsDisplayMode) {
            // Disable EditText
            txtLongitude.setEnabled(false);
            txtLatitude.setEnabled(false);
            txtLongitude.setFocusable(false);
            txtLatitude.setFocusable(false);
        } else {
            // Enable EditText
            txtLongitude.setEnabled(true);
            txtLatitude.setEnabled(true);
        }
        // In case of Add POI
        if (mIsAdd) {
            // Create new POI when this is Add dialog
            mPoi = new Poi();
            mListCategories = new ArrayList<Category>();
            if (null != mGeoPoint) {
                double longitude = mGeoPoint.getLongitudeE6() / 1E6;
                double latitude = mGeoPoint.getLatitudeE6() / 1E6;
                // Set Longitude
                txtLongitude.setText(new Double(longitude).toString());
                // Set Latitude
                txtLatitude.setText(new Double(latitude).toString());
            }
        } else { // In case of Update POI
            if (null != mPoi) {
                try {
                    ContentManager contentManager = app.getContentManager();
                    long poiId = mPoi.getId();
                    // Get POI detail
                    mPoi = contentManager.getDetailPoiInformation(poiId);
                    // Get POI's Picture
                    mPictures = mPoi.getPictures();
                    // Fill data to View
                    double longitude = mPoi.getLongitude() / 1E6;
                    double latitude = mPoi.getLatitude() / 1E6;
                    this.txtLongitude.setText(Double.toString(longitude));
                    this.txtLatitude.setText(Double.toString(latitude));
                    this.txtName.setText(mPoi.getName());
                    this.txtDescription.setText(mPoi.getDescriptions());
                    this.txtAddress.setText(mPoi.getAddress().getAddressString());
                    if (this.mPoi.getPoiType() == Poi.POI_TYPE_PUBLIC) {
                        radPulic.setChecked(true);
                    } else {
                        radPrivate.setChecked(true);
                    }

                    /**
                     * Load Image
                     * 
                     * @author hieu.ha
                     * @Version 0.4.0
                     */

                    for (Object picture : mPoi.getPictures().toArray()) {
                        File pathImg = new File(((Picture) picture).getLocation());
                        Bitmap bmImg = BitmapFactory.decodeFile(pathImg.toString());
                        if (null != bmImg) {
                            AddUpdatePOIDialog.imageView_picture_poi.setImageBitmap(bmImg);
                            break;
                        }
                    }

                    // Select spinner item (Select one)
                    Object[] categories = this.mPoi.getCategories().toArray();
                    for (Category category : allCategories) {
                        if (category.getId() == ((Category) categories[0]).getId()) {
                            spnCategoty.setSelection(mSpinnerAdapter.getPosition(category), true);
                            break;
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
            }
        }

        // Handle selected event item of spinner
        spnCategoty.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long i) {
                Category SelectedCategory = mSpinnerAdapter.getItem(position);
                mListCategories = new ArrayList<Category>();// In case of select
                                                            // one
                mListCategories.add(SelectedCategory);
                // Add category to POI
                if (null == mPoi) {
                    mPoi = new Poi();
                } else {
                    mPoi.setCategories(mListCategories);
                }
                // TODO Process values
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
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
        // imageView_picture_poi = (ImageView)
        // findViewById(R.id.dialog_add_poi_picture);
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
        // imageView_picture_poi.setOnClickListener(this);

        txtLongitude = (EditText) findViewById(R.id.dialog_add_poi_edit_longitude);
        txtLatitude = (EditText) findViewById(R.id.dialog_add_poi_edit_latitude);
        txtName = (EditText) findViewById(R.id.dialog_add_poi_edit_name);
        txtDescription = (EditText) findViewById(R.id.dialog_add_poi_edit_description);
        txtAddress = (EditText) findViewById(R.id.dialog_add_poi_edit_address);
        radPulic = (RadioButton) findViewById(R.id.dialog_add_poi_radio_public);
        radPrivate = (RadioButton) findViewById(R.id.dialog_add_poi_radio_private);

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
        switch (arg0.getId()) {
        case R.id.dialog_add_poi_button_cancel: // Dismiss the dialog.

            this.dismiss();
            break;
        case R.id.dialog_add_poi_picture:
            Intent intent = new Intent(mContext, LoadImageActivity.class);
            mContext.startActivity(intent);

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
            // Create new POI
            if (mIsAdd) {
                try {
                    ContentManager ContentManager = app.getContentManager();

                    if (null != mPoi) {

                        double latitude = Double.parseDouble(txtLatitude.getText().toString()) * 1E6;
                        double longitude = Double.parseDouble(txtLongitude.getText().toString()) * 1E6;
                        mPoi.setLatitude((int) latitude);
                        mPoi.setLongitude((int) longitude);
                        mPoi.setRating(0);
                        // Set actived
                        mPoi.setActivated(true);
                        // Current user
                        LoginSession loginsession = app.getSession();
                        if (app.checkLoginStatus() && null != loginsession) {
                            mPoi.setCreationUser(loginsession.getUser());
                            mPoi.setApproveUser(loginsession.getUser());
                        }
                        // Current date
                        Date createDate = new Date();
                        mPoi.setCreationDate(createDate);
                        mPoi.setApproved(true);
                        mPoi.setApproveDate(createDate);

                        /**
                         * Add picture for special POI
                         * 
                         * @author anh.trinh
                         * @version 0.4.0
                         * 
                         */
                        if (null != arraypath && 0 < arraypath.length) {
                            // Create ArrayList picture
                            ArrayList<Picture> pictures = new ArrayList<Picture>();
                            DataManager dataManager = new DataManager(mContext);
                            String frompath = "";
                            try {
                                for (int i = 0; i < arraypath.length; i++) {
                                    frompath = dataManager.copyImage(arraypath[i], DIR_SDCARD,
                                            DataManager.SDCARD);
                                    /**
                                     * Add picture
                                     * 
                                     * @author hieu.ha
                                     * @version 0.4.0
                                     */
                                    Picture pic = new Picture();
                                    pic.setLocation(frompath);
                                    pic.setDescription("");
                                    pictures.add(pic);
                                }
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                            // Add pictures
                            mPoi.setPictures(pictures);
                        }

                        // Call method set data POI
                        setDataPOI(app);
                        // Save POI to MM system
                        boolean isSuccessful = ContentManager.addPoi(mPoi);
                        if (isSuccessful) {
                            Toast.makeText(getContext(), "Add POI successfully", Toast.LENGTH_LONG)
                                    .show();
                            this.dismiss();
                            // Process when Add POI successfully.
                            ((MainMapActivity) mContext).onFinishedDialog(true, mPoi, this);

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
                // Update POI
            } else {
                ContentManager ContentManager;
                try {
                    if (null != mPoi) {
                        ContentManager = app.getContentManager();
                        // Current user
                        LoginSession loginsession = app.getSession();
                        if (app.checkLoginStatus() && null != loginsession) {
                            mPoi.setApproveUser(loginsession.getUser());
                        }
                        // Current date
                        Date createDate = new Date();
                        mPoi.setApproveDate(createDate);
                        /**
                         * Add picture for special POI
                         * 
                         * @author hieu.ha
                         * @version 0.4.0
                         * 
                         */
                        if (null != arraypath && 0 < arraypath.length) {
                            mPictures.clear();
                            DataManager dataManager = new DataManager(mContext);
                            String frompath = "";
                            try {
                                for (int i = 0; i < arraypath.length; i++) {

                                    frompath = dataManager.copyImage(arraypath[i], DIR_SDCARD,
                                            DataManager.SDCARD);

                                    Picture pic = new Picture();
                                    pic.setLocation(frompath);
                                    pic.setDescription("");
                                    mPictures.add(pic);
                                }
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                        // Call method set data POI
                        setDataPOI(app);
                        boolean isSuccessful = ContentManager.updatePoi(mPoi);
                        if (isSuccessful) {
                            Toast.makeText(getContext(), "Update POI successfully",
                                    Toast.LENGTH_LONG).show();
                            this.dismiss();
                            // Process when Update POI successfully.
                            ((MainMapActivity) mContext).onFinishedDialog(false, mPoi, this);

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
        // Set null
        if (null != imageView_picture_poi) {
            AddUpdatePOIDialog.imageView_picture_poi.setImageDrawable(null);
            arraypath = null;
        }
    }

    /**
     * Set data POI
     * 
     * @param app
     * @author hieu.ha
     */
    private void setDataPOI(MobileMapApp app) {

        mPoi.setName(txtName.getText().toString());
        mPoi.setDescriptions(txtDescription.getText().toString());
        // Create address object
        vn.s3corp.android.MobileMap.DataAccess.DataObjects.Address address = new vn.s3corp.android.MobileMap.DataAccess.DataObjects.Address();
        address.setAddressString(txtAddress.getText().toString());
        mPoi.setAddress(address);
        // Set the state of POI
        if (radPulic.isChecked()) {
            mPoi.setPoiType(Poi.POI_TYPE_PUBLIC);
        } else {
            mPoi.setPoiType(Poi.POI_TYPE_PRIVATE);
        }
    }

}
