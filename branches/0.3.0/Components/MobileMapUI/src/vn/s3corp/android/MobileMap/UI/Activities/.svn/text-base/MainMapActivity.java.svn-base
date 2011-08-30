/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.UI.Activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import vn.s3corp.android.MobileMap.UI.Dialogs.FilterPOIOverlayDialog;
import vn.s3corp.android.MobileMap.UI.Dialogs.LoginRegisterDialog;
import vn.s3corp.android.MobileMap.UI.Dialogs.AddUpdatePOIDialog;
import vn.s3corp.android.MobileMap.UI.Utilities.CategoryAdapter;
import vn.s3corp.android.MobileMap.UI.Utilities.MapItemizedOverlay;
import vn.s3corp.android.MobileMap.UI.Utilities.MapPOIItem;
import vn.s3corp.android.MobileMap.UI.Utilities.Show_Geopoin;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * The main Activity for Maps.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class MainMapActivity extends MapActivity implements View.OnClickListener {

    private static final int MENU_GROUP_MAIN_ID = Menu.FIRST;
    private static final int MENU_GROUP_POI_ID = Menu.FIRST + 1;
    private static final int MENU_GROUP_POI_DETAIL_ID = Menu.FIRST + 2;

    private static final int MENU_REGISTER_ID = Menu.FIRST;
    private static final int MENU_LOGIN_LOGOUT_ID = Menu.FIRST + 1;
    /**
     * ID of menu Add POI
     * 
     * @author hieu.ha
     */
    private static final int MENU_ADD_POI_ID = Menu.FIRST + 3;
    private static final int CONTEXT_MENU_ADD_POI_ID = Menu.FIRST + 4;
    private static final int CONTEXT_MENU_UPDATE_POI_ID = Menu.FIRST + 5;
    private static final int CONTEXT_MENU_REMOVE_POI_ID = Menu.FIRST + 6;
    private static final int MENU_FILTER_POI_OVERLAY = Menu.FIRST + 7;
    private static final int DIALOG_REGISTER_ID = 1;
    private static final int DIALOG_LOGIN_ID = 2;
    /**
     * Create dialog Add POI
     * 
     * @author hieu.ha
     */
    private static final int DIALOG_ADD_POI = 3;
    private static final int DIALOG_ADD_POI_CONTEXT_MENU = 4;
    private static final int DIALOG_UPDATE_POI_CONTEXT_MENU = 5;
    private static final int DIALOG_FILTER_POI_OVERLAY = 6;

    private GeoPoint mGeoPoint;// GeoPoint on MapView using Add POI dialog

    private GestureDetector mGestureDetector; // Handle touch event
    private MapController mMapController;// Map Controller
    private List<Overlay> mMapOverlays;// List Overlay over MapView
    private Boolean mIsAdd = false;
    private MapView mapView;
    private MapPOIItem mPOIFocus;// Using store MapPOIItem when focus POI on
                                 // MapView
    // Define category const
    private static final int RESTAURANT = 1;
    private static final int COFFEE = 2;
    private static final int BAR = 3;
    private static final int HOTEL = 4;
    private static final int ATTRACTION = 5;
    private static final int ATM = 6;
    private static final int GAS_STATION = 7;

    HashMap<Category, Drawable> mHMDrawableIcon;// List layout using format icon
    // POI's icon.
    // HashMap MapItemizedOverlay,Drawable
    HashMap<Category, HashMap<MapItemizedOverlay, Drawable>> mHMCateoryOverlay;
    private int mOverlayItemIndex = -1;// Index of POI item on ItemizedOverlay
    private int mCurrentDialogId;// id of dialog
    private ArrayList<Category> mCategory;// Current list category on MapView
    private ArrayList<Category> mAllCategories;// All of category on MM System
    /**
     * Search view
     * 
     * @author anh.trinh
     */
    private LinearLayout mLinearLayoutExpand, mLinearLayoutSearch;
    private Button mButtonExpand, mButtonPick, mButtonSearch, mButtonReview;
    private ToggleButton mToggleButtonCurrent;
    private EditText mEditTextLongitude, mEditTextLatitude, mEditTextRadius, mEditTextSearch;
    private TextView mTextViewRemind;
    private ArrayList<Category> mListCategories;
    private int mtest;
    private Drawable mDrawable, mDrawable2;
    private Show_Geopoin mShow_Geopoin, mShow_Geopoin2;
    private GeoPoint mGeoPoint2;
    private Spinner mspnCategoty;
    private String mStringNamePOI = " ", mStringCategoryPOI = " ", mStringLongitudePOI = " ",
            mStringLatitudePOI = " ", mStringRadiusPOI = " ";

    /**
     * Values of POI Information
     * 
     * @author hien.vu
     */
    private ImageButton mImageButtonDownUp;
    private ImageButton mImageButtonExit;
    private Button mButtonCenter;
    private Button mButtonCall;
    private ImageButton mImageButtonSearch;
    private Button mButtonFavorite;
    private Button mButtonAdvance;
    private LinearLayout mLinearLayoutInformation;
    private LinearLayout mLinearLayout_AdvancedButton;
    private ImageView mImageView_Picture;
    private RatingBar mRatingBarFavorite;
    private TextView mTextViewName;
    private TextView mTextViewAddress;
    private TextView mTextViewLongtitude;
    private TextView mTextViewLatitude;
    private TextView mTextViewDescription;
    private int mIsFavorite;
    private MapPOIItem item;;

    /**
     * Change text color for button.
     * 
     * @param bt
     * @return
     * @author hien.vu
     * @version 0.3.0
     * 
     */
    public void changeOptionButton(Button bt) {
        bt.setTextColor(Color.RED);
    }

    public void changeOption1(Button bt) {
        if (bt.getHeight() == 100) {
            bt.setHeight(30);
        } else
            bt.setHeight(100);
    }

    public void GetInformation(long id) {
        MobileMapApp app = (MobileMapApp) getApplicationContext();
        mTextViewName = (TextView) findViewById(R.id.main_poi_information_label_name);
        mTextViewAddress = (TextView) findViewById(R.id.main_poi_information_label_address);
        mTextViewLongtitude = (TextView) findViewById(R.id.main_poi_information_label_longtitude);
        mTextViewLatitude = (TextView) findViewById(R.id.main_poi_information_label_latitude);
        mTextViewDescription = (TextView) findViewById(R.id.main_poi_information_label_description);
        mRatingBarFavorite = (RatingBar) findViewById(R.id.main_poi_information_rating_poi);

        try {
            ContentManager a = app.getContentManager();
            Poi b = a.getDetailPoiInformation(id);

            if (null != b) {
                String mName = "Name: " + b.getName();
                String mAddress = "Address: " + b.getAddress();
                String mDescription = "Description: " + b.getDescriptions();
                String mLatitude = "Latitude: " + String.valueOf(b.getLatitude());
                String mLongtitude = "Longtitude: " + String.valueOf(b.getLongitude());
                
                mRatingBarFavorite.setRating(b.getRating());
                mTextViewName.setText(mName);
                mTextViewAddress.setText(mAddress);
                mTextViewDescription.setText(mDescription);
                mTextViewLatitude.setText(mLatitude);
                mTextViewLongtitude.setText(mLongtitude);
            } else
                Toast.makeText(this, "POI is null!", Toast.LENGTH_SHORT).show();
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
    }// the end GetInformation

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

    /**
     * ; Called when the activity is first created.
     * 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the main layout.
        setContentView(R.layout.main);
        // Get the MapView component.
        mapView = (MapView) findViewById(R.id.main_map_view);
        mMapController = mapView.getController();
        mMapOverlays = mapView.getOverlays();
        // Enable the Built-in zooming feature of MapView.
        // mapView.setBuiltInZoomControls(true);
        // Create zoomcontrol
        LinearLayout zoomLayout = (LinearLayout) findViewById(R.id.main_map_zoomcontrol);
        @SuppressWarnings("deprecation")
        View zoomView = mapView.getZoomControls();
        zoomLayout.addView(zoomView, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        mapView.displayZoomControls(true);
        // Register contextmenu for MapView
        registerForContextMenu(mapView);
        // Set on Touch Listener on MapView
        mapView.setOnTouchListener(new MyTouchListener());
        final MobileMapApp app = (MobileMapApp) getApplicationContext();
        /**
         * Handle touch event
         * 
         * @author hieu.ha
         */
        // Callback will notify users when a particular motion event has
        // occurred.
        // This class should only be used with MotionEvents reported via touch
        // (don't use for trackball events).
        mGestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e) {
                if (mtest == 1) {
                    mEditTextLatitude.setText(Double.toString(mGeoPoint2.getLatitudeE6() / 1E6));
                    mEditTextLongitude.setText(Double.toString(mGeoPoint2.getLongitudeE6() / 1E6));
                    mEditTextRadius.setVisibility(View.VISIBLE);
                    mLinearLayoutSearch.setVisibility(View.VISIBLE);
                    mTextViewRemind.setVisibility(View.GONE);


                } else {
                    GeoPoint geoPointOnTouch = mapView.getProjection().fromPixels((int) e.getX(),
                            (int) e.getY());
                    mGeoPoint = geoPointOnTouch;// Use Add POI dialog
                    mIsAdd = true;
                    // Find focus GeoPoint
                    for (Overlay overlay : mMapOverlays) {
                        mPOIFocus = ((MapItemizedOverlay) overlay).getFocus();
                        if (null != mPOIFocus) {
                            mIsAdd = false;
                            break;
                        }
                    }
                    // Check login
                    if (app.checkLoginStatus()) {
                        // Check permission
                        if (null != mPOIFocus
                                && mPOIFocus.getPoi().getCreationUser().getId() != app
                                        .getLoginUserId()) {
                            Toast.makeText(getApplicationContext(),
                                    "You might not have permisson to use this POI.",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            mapView.showContextMenu();
                        }
                    }
                }
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                mMapController.zoomIn();
                return true;
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return super.onDown(e);
            }
        });
        /**
         * Load category's layer
         * 
         * @author hieu.ha
         */
        ContentManager ContentManager;
        try {
            ContentManager = app.getContentManager();
            mAllCategories = ContentManager.getAllPoiCategories();
        } catch (DataAccessException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (DatabaseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SystemException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Load category default
        mCategory = mAllCategories;
        if (null != mCategory) {
            createDefaultOverlay(mCategory);
        }
        /*
         * Search view
         * 
         * @author anh.trinh
         */
        mLinearLayoutSearch = (LinearLayout) findViewById(R.id.main_linearlayout_search);
        mLinearLayoutExpand = (LinearLayout) findViewById(R.id.main_linearlayout_expand);
        mButtonExpand = (Button) findViewById(R.id.main_button_expand);
        mButtonReview = (Button) findViewById(R.id.main_button_review);
        mToggleButtonCurrent = (ToggleButton) findViewById(R.id.main_togglebutton_current);
        mEditTextLongitude = (EditText) findViewById(R.id.main_edittext_longitude);
        mEditTextLatitude = (EditText) findViewById(R.id.main_edittext_latitude);
        mEditTextSearch = (EditText) findViewById(R.id.main_edittext_searchtext);
        mButtonPick = (Button) findViewById(R.id.main_button_pick);
        mButtonSearch = (Button) findViewById(R.id.main_button_search);
        mTextViewRemind = (TextView) findViewById(R.id.main_textview_remind);
        mEditTextRadius = (EditText) findViewById(R.id.main_edittext_radius);
        mLinearLayoutExpand.setVisibility(View.GONE);
        mButtonExpand.setOnClickListener(this);
        mButtonPick.setOnClickListener(this);
        mButtonSearch.setOnClickListener(this);
        mButtonReview.setOnClickListener(this);
        mButtonReview.setVisibility(View.GONE);
        mDrawable = getResources().getDrawable(android.R.drawable.star_on);
        mDrawable2 = getResources().getDrawable(android.R.drawable.stat_notify_chat);
        mShow_Geopoin = new Show_Geopoin(mDrawable);
        mShow_Geopoin2 = new Show_Geopoin(mDrawable2);
        mToggleButtonCurrent.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked == true) {

                    mGeoPoint2 = mapView.getMapCenter();
                    mEditTextLatitude.setText(Double.toString(mGeoPoint2.getLatitudeE6() / 1E6));
                    mEditTextLongitude.setText(Double.toString(mGeoPoint2.getLongitudeE6() / 1E6));
                    mEditTextRadius.setVisibility(View.VISIBLE);
                    OverlayItem overlayitem = new OverlayItem(mGeoPoint2, "Current", "I'm here!");
                    mShow_Geopoin.addOverlay(overlayitem);
                    mMapOverlays.add(mShow_Geopoin);
                } else {
                    mEditTextLatitude.setText("");
                    mEditTextLongitude.setText("");
                    mEditTextRadius.setVisibility(View.INVISIBLE);
                }
            }
        });
        Bundle extras = new Bundle();
        extras = getIntent().getExtras();
        if (extras != null) {
            MapItemizedOverlay mapitemizedoverlay = new MapItemizedOverlay(mDrawable, mapView,
                    getApplicationContext());
            ContentManager contentManager;
            try {
                mMapOverlays.clear();
                contentManager = app.getContentManager();
                Poi rsp = contentManager.getDetailPoiInformation(extras.getLong("Id_POI"));
                GeoPoint geoPoint = new GeoPoint((int) (rsp.getLatitude()),
                        (int) (rsp.getLongitude()));
                MapPOIItem overlayItem = new MapPOIItem(geoPoint, rsp.getName(),
                        rsp.getDescriptions(), rsp);
                mapitemizedoverlay.addOverlayItem(overlayItem);
                mMapOverlays.add(mapitemizedoverlay);
               mButtonReview.setVisibility(View.VISIBLE);
            } catch (DataAccessException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (DatabaseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (InvalidDataException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SystemException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }

   

        /**
         * Poi Information
         * 
         * @author hien.vu
         */
        Button bt = (Button) findViewById(R.id.btnViewPOI);
        bt.setOnClickListener(this);

        mLinearLayoutInformation = (LinearLayout) findViewById(R.id.main_poi_information_linearlayout_PoiInformation);
        mLinearLayout_AdvancedButton = (LinearLayout) findViewById(R.id.main_poi_information_linearlayout_advancedButton);

        // Favorite Button
        mIsFavorite = 1;
        mButtonFavorite = (Button) findViewById(R.id.main_poi_information_button_favorite);
        mButtonFavorite.setOnClickListener((OnClickListener) this);

        // Expand ImageButton
        mImageButtonDownUp = (ImageButton) findViewById(R.id.main_poi_information_imagebutton_expand);
        mImageButtonDownUp.setOnClickListener((OnClickListener) this);

        // Exit ImageButton
        mImageButtonExit = (ImageButton) findViewById(R.id.main_poi_information_imagebutton_close);
        mImageButtonExit.setOnClickListener((OnClickListener) this);

        // Center ImageButton
        mButtonCenter = (Button) findViewById(R.id.main_poi_information_button_center);
        mButtonCenter.setOnClickListener((OnClickListener) this);

        // Call ImageButton
        mButtonCall = (Button) findViewById(R.id.main_poi_information_button_call);
        mButtonCall.setOnClickListener((OnClickListener) this);

        // Advance Button
        mButtonAdvance = (Button) findViewById(R.id.main_poi_information_button_advance);
        mButtonAdvance.setOnClickListener((OnClickListener) this);

    }

    /**
     * Load POI
     * 
     * @param poitype
     * 
     * @author hieu.ha
     */
    private void LoadPOI(long poitype, ArrayList<Category> listcategory) {
        MobileMapApp app = (MobileMapApp) this.getApplication();
        try {
            MapItemizedOverlay mapitemizedoverlay = null;
            ContentManager contentManager = app.getContentManager();
            // Load public POI
            ArrayList<Poi> listPoi = null;
            if (poitype == Poi.POI_TYPE_PUBLIC) {
                listPoi = contentManager.getAllPublicPoisSimple();
            } else if (poitype == Poi.POI_TYPE_PRIVATE) {
                listPoi = contentManager.getAllPrivatePoisSimple(app.getLoginUserId());
            }
            if (0 < listPoi.size()) {
                for (Poi poi : listPoi) {
                    GeoPoint geoPoint = new GeoPoint((int) (poi.getLatitude()),
                            (int) (poi.getLongitude()));
                    MapPOIItem overlayItem = new MapPOIItem(geoPoint, poi.getName(),
                            poi.getDescriptions(), poi);
                    // Create category's overlay
                    Object[] categories = poi.getCategories().toArray();
                    mapitemizedoverlay = getMapItemizedOverlayByCategory(categories);
                    if (0 < categories.length) {
                        for (Category category : listcategory) {
                            if (((Category) categories[0]).getId() == category.getId()) {
                                mapitemizedoverlay = getMapItemizedOverlay(mHMCateoryOverlay,
                                        category);
                                if (null != mapitemizedoverlay) {
                                    // Add ItemizedOverLay over MapView
                                    if (!mMapOverlays.contains(mapitemizedoverlay)) {
                                        mMapOverlays.add(mapitemizedoverlay);
                                    }
                                    // Add POI over Overlay
                                    mapitemizedoverlay.addOverlayItem(overlayItem);
                                }
                                break;
                            }
                        }
                    }
                }

            }
        } catch (DataAccessException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (DatabaseException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SystemException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidDataException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Handle touch event on map
     * 
     * @author hieu.ha
     */
    public class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View v, MotionEvent event) {
            return mGestureDetector.onTouchEvent(event);
        }
    }

    /**
     * Called when the context menu is created.
     * 
     * @author hieu.ha
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("POI");
        menu.add(0, MainMapActivity.CONTEXT_MENU_ADD_POI_ID, 0, R.string.menu_addpoi);
        menu.add(0, MainMapActivity.CONTEXT_MENU_UPDATE_POI_ID, 0, R.string.menu_updatepoi);
        menu.add(0, MainMapActivity.CONTEXT_MENU_REMOVE_POI_ID, 0, R.string.lable_removepoi);

        MenuItem addPOIItem = menu.findItem(MainMapActivity.CONTEXT_MENU_ADD_POI_ID);
        MenuItem updatePOIItem = menu.findItem(MainMapActivity.CONTEXT_MENU_UPDATE_POI_ID);
        MenuItem removePOIItem = menu.findItem(MainMapActivity.CONTEXT_MENU_REMOVE_POI_ID);
        if (mIsAdd) {
            addPOIItem.setVisible(true);
            updatePOIItem.setVisible(false);
            removePOIItem.setVisible(false);
        } else {
            addPOIItem.setVisible(false);
            updatePOIItem.setVisible(true);
            removePOIItem.setVisible(true);
        }
    }

    /**
     * Called when the context menu item is selected.
     * 
     * @author hieu.ha
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case MainMapActivity.CONTEXT_MENU_ADD_POI_ID:
            // Show the Add POI dialog.
            showDialog(MainMapActivity.DIALOG_ADD_POI_CONTEXT_MENU);
            return true;
        case MainMapActivity.CONTEXT_MENU_UPDATE_POI_ID:
            // Show the AdUpdate POI dialog.
            showDialog(MainMapActivity.DIALOG_UPDATE_POI_CONTEXT_MENU);
            return true;
        case MainMapActivity.CONTEXT_MENU_REMOVE_POI_ID:

            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    MobileMapApp app = (MobileMapApp) getApplicationContext();
                    try {
                        ContentManager contentManager = app.getContentManager();
                        boolean isSuccessful = contentManager.removePoi(mPOIFocus.getPoi().getId());
                        if (isSuccessful) {
                            Toast.makeText(getApplicationContext(), "Remove POI successfully",
                                    Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                            Object[] categories = mPOIFocus.getPoi().getCategories().toArray();
                            MapItemizedOverlay mapitemizedoverlay = getMapItemizedOverlayByCategory(categories);
                            // Remove item on MapView
                            mapitemizedoverlay.removeOverlayItem(mOverlayItemIndex);
                            mapView.invalidate();

                            mapitemizedoverlay.setFocus(null);// Clear
                                                              // focus
                        } else {
                            Toast.makeText(getApplicationContext(),
                                    "Have an error when removing a POI", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
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
            });
            dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            dialog.setMessage("Do you want to remove POI?");
            dialog.show();
            return true;
        default:
            break;
        }
        return super.onContextItemSelected(item);
    }

    /**
     * Called when the menu is created.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(MENU_GROUP_MAIN_ID, MainMapActivity.MENU_LOGIN_LOGOUT_ID, Menu.NONE,
                R.string.menu_login);
        menu.add(MENU_GROUP_MAIN_ID, MainMapActivity.MENU_REGISTER_ID, Menu.NONE,
                R.string.menu_register);
        // Add/Update POI menu
        menu.add(MENU_GROUP_POI_ID, MainMapActivity.MENU_ADD_POI_ID, Menu.NONE,
                R.string.menu_addpoi);
        // Filter Menu
        menu.add(MENU_GROUP_POI_ID, MainMapActivity.MENU_FILTER_POI_OVERLAY, Menu.NONE,
                R.string.menu_filterpoi);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        MobileMapApp app = (MobileMapApp) this.getApplication();

        // Set the title of Login/Logout menu.
        MenuItem loginItem = menu.findItem(MainMapActivity.MENU_LOGIN_LOGOUT_ID);
        if (null != loginItem) {
            loginItem.setTitle(app.checkLoginStatus() ? R.string.menu_logout : R.string.menu_login);
        }
        MenuItem AddPOIItem = menu.findItem(MainMapActivity.MENU_ADD_POI_ID);
        if (null != AddPOIItem) {
            AddPOIItem.setVisible(app.checkLoginStatus() ? true : false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * Called when the menu item is selected.
     */
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        MobileMapApp app = (MobileMapApp) this.getApplication();

        switch (item.getItemId()) {
        case MainMapActivity.MENU_REGISTER_ID:
            // Show the register dialog.
            showDialog(MainMapActivity.DIALOG_REGISTER_ID);

            return true;

        case MainMapActivity.MENU_LOGIN_LOGOUT_ID:
            if (app.checkLoginStatus()) {
                // Process the logout request.
                try {
                    ContentManager contentManager = app.getContentManager();
                    LoginSession session = app.getSession();

                    contentManager.logout(session);
                } catch (DataAccessException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (DatabaseException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (InvalidDataException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                } catch (SystemException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                app.removeSession();

                Toast.makeText(this, "You logout Moblie Map system.", Toast.LENGTH_SHORT).show();
            } else {
                // Show the login dialog.
                showDialog(MainMapActivity.DIALOG_LOGIN_ID);
            }

            return true;

        case MainMapActivity.MENU_ADD_POI_ID:
            // Show the Add/Update POI dialog.
            showDialog(MainMapActivity.DIALOG_ADD_POI);
            return true;
        case MainMapActivity.MENU_FILTER_POI_OVERLAY:
            // Show the Filter POI dialog.
            showDialog(MainMapActivity.DIALOG_FILTER_POI_OVERLAY);
            return true;
        default:
            break;
        }

        return super.onMenuItemSelected(featureId, item);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Dialog dialog;
        switch (id) {
        case MainMapActivity.DIALOG_REGISTER_ID:
            // Create Login/Register dialog.
            dialog = new LoginRegisterDialog(this, true);

            break;
        case MainMapActivity.DIALOG_LOGIN_ID:
            // Create Login/Register dialog.
            dialog = new LoginRegisterDialog(this, false);

            break;
        case MainMapActivity.DIALOG_ADD_POI:
            // Create Add/Update POI dialog.
            dialog = new AddUpdatePOIDialog(this, null, false, true);

            break;
        case MainMapActivity.DIALOG_ADD_POI_CONTEXT_MENU:
            // Create Add POI dialog.
            dialog = new AddUpdatePOIDialog(this, mGeoPoint, true, true);

            break;
        case MainMapActivity.DIALOG_UPDATE_POI_CONTEXT_MENU:
            // Create Update POI dialog.
            dialog = new AddUpdatePOIDialog(this, null, true, false);

            break;
        case MainMapActivity.DIALOG_FILTER_POI_OVERLAY:
            // Create Filter dialog.
            dialog = new FilterPOIOverlayDialog(this, mAllCategories);

            break;
        default:
            dialog = null;
            break;
        }
        mCurrentDialogId = id;// Current dialog id
        return dialog;
    }

    /**
     * Called when Prepare Dialog
     * 
     * @author hieu.ha
     */
    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
        case MainMapActivity.DIALOG_ADD_POI_CONTEXT_MENU:
            // Prepare Add POI dialog.
            ((AddUpdatePOIDialog) dialog).clearEditText();
            ((AddUpdatePOIDialog) dialog).setGeoPoint(mGeoPoint);
            break;
        case MainMapActivity.DIALOG_ADD_POI:
            // Prepare Add POI dialog.
            ((AddUpdatePOIDialog) dialog).clearEditText();
            break;
        case MainMapActivity.DIALOG_UPDATE_POI_CONTEXT_MENU:
            // Prepare Update POI dialog.
            ((AddUpdatePOIDialog) dialog).clearEditText();
            ((AddUpdatePOIDialog) dialog).setPOI(mPOIFocus.getPoi());
            break;
        case MainMapActivity.DIALOG_REGISTER_ID:
        case MainMapActivity.DIALOG_LOGIN_ID:
            // Reset the content.
            ((LoginRegisterDialog) dialog).resetTextBoxes();

            break;
        case MainMapActivity.DIALOG_FILTER_POI_OVERLAY:
            ((FilterPOIOverlayDialog) dialog).setListCategory(mCategory);
            break;
        default:
            break;
        }
        mCurrentDialogId = id;// Current dialog id
    }

    /**
     * Get MapItemizedOverlay by Catgory
     * 
     * @param categories
     * @return
     * @author hieu.ha
     */
    private MapItemizedOverlay getMapItemizedOverlayByCategory(Object[] categories) {
        MapItemizedOverlay mapitemizedoverlay = null;
        if (0 < categories.length) {
            mapitemizedoverlay = getMapItemizedOverlay(mHMCateoryOverlay,
                    getCategoryById(((Category) categories[0]).getId()));
            return mapitemizedoverlay;
        }
        return mapitemizedoverlay;
    }

    /**
     * Get the category by id in mAllCategories
     * 
     * @param categoryid
     * @return
     * @author hieu.ha
     */
    private Category getCategoryById(long categoryid) {
        for (Category category : mAllCategories) {
            if (categoryid == category.getId()) {
                return category;
            }
        }
        return null;
    }

    /**
     * Call when finished dialog
     * 
     * @author hieu.ha
     */
    public void onFinishedDialog(Boolean isAdd, Poi poi, Dialog dialog) {
        MapItemizedOverlay mapitemizedoverlay = null;
        if (mCurrentDialogId == MainMapActivity.DIALOG_ADD_POI
                || mCurrentDialogId == MainMapActivity.DIALOG_ADD_POI_CONTEXT_MENU
                || mCurrentDialogId == MainMapActivity.DIALOG_UPDATE_POI_CONTEXT_MENU) {
            Object[] categories = poi.getCategories().toArray();
            if (0 < categories.length) {
                mapitemizedoverlay = getMapItemizedOverlayByCategory(categories);
                // Add ItemizedOverLay over MapView
                if (!mMapOverlays.contains(mapitemizedoverlay)) {
                    mMapOverlays.add(mapitemizedoverlay);
                    // Add new category
                    mCategory.add(getCategoryById(((Category) categories[0]).getId()));
                }
            }
            if (isAdd) {
                if (null != poi && null != mapitemizedoverlay) {
                    GeoPoint geoPoint = new GeoPoint((int) (poi.getLatitude()),
                            (int) (poi.getLongitude()));
                    MapPOIItem overlayItem = new MapPOIItem(geoPoint, poi.getName(),
                            poi.getDescriptions(), poi);
                    // Add POI over Overlay
                    mapitemizedoverlay.addOverlayItem(overlayItem);
                    mapView.invalidate();
                }
            } else {
                // Reload MapView
                mMapOverlays.clear();
                createDefaultOverlay(mCategory);
                mapView.invalidate();
            }
        } else {
            if (mCurrentDialogId == MainMapActivity.DIALOG_FILTER_POI_OVERLAY) {
                // Reload MapView
                mCategory = ((FilterPOIOverlayDialog) dialog).getListCategory();
                mMapOverlays.clear();
                createDefaultOverlay(mCategory);
                mapView.invalidate();
            }
        }
    }

    /**
     * Return category's overlay by categotyId
     * 
     * @param categoryId
     * @return
     * @author hieu.ha
     */
    private MapItemizedOverlay getMapItemizedOverlay(
            HashMap<Category, HashMap<MapItemizedOverlay, Drawable>> hmMain, Category category) {
        MapItemizedOverlay mapitemizedoverlay = null;
        HashMap<MapItemizedOverlay, Drawable> hmItem = new HashMap<MapItemizedOverlay, Drawable>();
        hmItem = hmMain.get((Category) category);
        for (MapItemizedOverlay item : hmItem.keySet()) {
            mapitemizedoverlay = item;
        }
        return mapitemizedoverlay;
    }

    /**
     * Get index of selected item in ItemizedOverlay
     * 
     * @param itemindex
     * @author hieu.ha
     */
    public void setOverlayItemIndex(int overlayitemindex) {
        this.mOverlayItemIndex = overlayitemindex;
    }

    /**
     * Create all overlay
     * 
     * @author hieu.ha
     */
    private void createDefaultOverlay(ArrayList<Category> listcategory) {
        MobileMapApp app = (MobileMapApp) this.getApplication();
        mHMDrawableIcon = new HashMap<Category, Drawable>();
        mHMCateoryOverlay = new HashMap<Category, HashMap<MapItemizedOverlay, Drawable>>();
        for (Category category : mAllCategories) {
            // Create default drawable
            switch ((int) category.getId()) {

            case MainMapActivity.RESTAURANT:
                mHMDrawableIcon.put(category,
                        getResources().getDrawable(R.layout.state_icon_restaurant));
                break;
            case MainMapActivity.COFFEE:
                mHMDrawableIcon.put(category, getResources()
                        .getDrawable(R.layout.state_icon_coffee));

                break;
            case MainMapActivity.BAR:
                mHMDrawableIcon.put(category, getResources().getDrawable(R.layout.state_icon_bar));

                break;
            case MainMapActivity.HOTEL:
                mHMDrawableIcon
                        .put(category, getResources().getDrawable(R.layout.state_icon_hotel));

                break;
            case MainMapActivity.ATTRACTION:
                mHMDrawableIcon.put(category,
                        getResources().getDrawable(R.layout.state_icon_attraction));

                break;
            case MainMapActivity.ATM:
                mHMDrawableIcon.put(category, getResources().getDrawable(R.layout.state_icon_atm));

                break;
            case MainMapActivity.GAS_STATION:
                mHMDrawableIcon.put(category,
                        getResources().getDrawable(R.layout.state_icon_gasstation));

                break;
            default:

                break;
            }
            // Create ItemizedOverlay by category
            Drawable drawable = mHMDrawableIcon.get(category);
            MapItemizedOverlay overlay = new MapItemizedOverlay(drawable, mapView, this);
            HashMap<MapItemizedOverlay, Drawable> hm = new HashMap<MapItemizedOverlay, Drawable>();
            hm.put(overlay, drawable);

            mHMCateoryOverlay.put(category, hm);
        }
        // Load Overlay over MapView
        if (null != mHMCateoryOverlay && 0 <= mHMCateoryOverlay.size()) {
            // Load public POI
            LoadPOI(Poi.POI_TYPE_PUBLIC, listcategory);
            // Load private POI
            if (app.checkLoginStatus()) {
                LoadPOI(Poi.POI_TYPE_PRIVATE, listcategory);
            }
        }
    }

    /*
     * Search view
     * 
     * @author anh.trinh
     */
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
        case R.id.main_button_expand:
            if (mLinearLayoutExpand.getVisibility() == View.GONE) {
                mLinearLayoutExpand.setVisibility(View.VISIBLE);
                // set current position.
                mToggleButtonCurrent.setChecked(true);
                mGeoPoint2 = mapView.getMapCenter();
                mEditTextLatitude.setText(Double.toString(mGeoPoint2.getLatitudeE6() / 1E6));
                mEditTextLongitude.setText(Double.toString(mGeoPoint2.getLongitudeE6() / 1E6));
                OverlayItem overlayitem = new OverlayItem(mGeoPoint2, "Current", "I'm here!");
                mShow_Geopoin.addOverlay(overlayitem);
                mMapOverlays.add(mShow_Geopoin);
                mEditTextRadius.setVisibility(View.VISIBLE);
                mButtonExpand.setBackgroundResource(android.R.drawable.arrow_up_float);
                MobileMapApp app = (MobileMapApp) getApplicationContext();
                // Category
                mspnCategoty = (Spinner) findViewById(R.id.main_spinner_category);
                try {
                    // Get All Categories
                    ContentManager ContentManager = app.getContentManager();
                    ArrayList<Category> allCategories = new ArrayList<Category>();
                    allCategories = ContentManager.getAllPoiCategories();
                    // Create a Adapter
                    final CategoryAdapter adapter = new CategoryAdapter(this.getBaseContext(),
                            allCategories,CategoryAdapter.SPINNER);
                    // Apply the Adapter to Spinner
                    mspnCategoty.setAdapter(adapter);
                    // Handle selected event item of spinner
                    mspnCategoty.setOnItemSelectedListener(new OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position,
                                long i) {
                            Category SelectedCategory = adapter.getItem(position);

                            if (null == mListCategories) {
                                mListCategories = new ArrayList<Category>();
                            } else {
                                mListCategories.add(SelectedCategory);
                            }

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
            } else {
                mLinearLayoutExpand.setVisibility(View.GONE);
                mButtonExpand.setBackgroundResource(android.R.drawable.arrow_down_float);
            }
            break;
        case R.id.main_button_pick:
            mtest = 1;
            mToggleButtonCurrent.setChecked(false);
            mLinearLayoutSearch.setVisibility(View.GONE);
            mTextViewRemind.setVisibility(View.VISIBLE);
            mMapOverlays.clear();
            mapView.setOnTouchListener(new OnTouchListener() {
                public boolean onTouch(final View v, final MotionEvent event) {
                    // TODO Auto-generated method stub
                    mGeoPoint2 = ((MapView) v).getProjection().fromPixels((int) event.getX(),
                            (int) event.getY());
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        new CountDownTimer(2000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                OverlayItem overlayitem = new OverlayItem(mGeoPoint2, "", "");
                                mShow_Geopoin2.addOverlay(overlayitem);
                                mMapOverlays.add(mShow_Geopoin2);
                            }

                            public void onFinish() {
                                mMapOverlays.clear();
                            }
                        }.start();
                    }
                    return mGestureDetector.onTouchEvent(event);
                }
            });
            break;
        case R.id.main_button_search:
            mtest = 0;
            mStringNamePOI = mEditTextSearch.getText().toString();
            if ("".equals(mEditTextRadius.getText().toString())
                    && mLinearLayoutExpand.getVisibility() == View.VISIBLE) {
                mEditTextRadius.requestFocus();
            } else {
                if (mLinearLayoutExpand.getVisibility() == View.VISIBLE) {
                    CategoryAdapter mCategoryAdapter = (CategoryAdapter) mspnCategoty.getAdapter();
                    mStringCategoryPOI = mCategoryAdapter.getItem(
                            mspnCategoty.getLastVisiblePosition()).getName();
                    mStringLongitudePOI = mEditTextLongitude.getText().toString();
                    mStringLatitudePOI = mEditTextLatitude.getText().toString();
                    mStringRadiusPOI = mEditTextRadius.getText().toString();

                }
                //Toast.makeText(getApplicationContext(), "Troi oi",Toast.LENGTH_SHORT).show();
                CharSequence StringSearch = "SearchPOI" + mStringNamePOI + "SearchPOI"
                + mStringCategoryPOI + "SearchPOI" + mStringLongitudePOI + "SearchPOI"
                + mStringLatitudePOI + "SearchPOI" + mStringRadiusPOI;
                Intent mIntent = new Intent(getApplicationContext(), ResultSearchActivity.class);
                mIntent.putExtra("Search_Text", StringSearch);
                startActivity(mIntent);
            }

            break;
        case R.id.main_button_review:
            Intent intent = new Intent(getApplicationContext(), ResultSearchActivity.class);
            startActivity(intent);
            break;

        /**
         * @author hien.vu
         */
        case R.id.btnViewPOI:
            mLinearLayoutInformation.setVisibility(View.VISIBLE);
            for (Overlay overlay : mMapOverlays) {
                if (null != ((MapItemizedOverlay) overlay).getFocus()) {
                    GetInformation(((MapItemizedOverlay) overlay).getFocus().getPoi().getId());
                    break;
                }
            }
            break;
        case R.id.main_poi_information_button_favorite:
            mIsFavorite++;
            if ((mIsFavorite % 2) == 0)
                mButtonFavorite.setBackgroundResource(R.drawable.star_big_on);
            else
                mButtonFavorite.setBackgroundResource(R.drawable.star_big_off);
            break;
        case R.id.main_poi_information_imagebutton_expand:
            if (mLinearLayout_AdvancedButton.getVisibility() == View.VISIBLE) {
                // mImageButtonDownUp.setBackgroundResource(android.R.drawable.arrow_down_float);
                mImageButtonDownUp.setImageResource(R.drawable.icon_down);
                mLinearLayout_AdvancedButton.setVisibility(View.GONE);
                Toast.makeText(MainMapActivity.this, "Just click  Shorten button",
                        Toast.LENGTH_SHORT).show();
            } else {
                // mImageButtonDownUp.setBackgroundResource(android.R.drawable.arrow_up_float);
                mImageButtonDownUp.setImageResource(R.drawable.icon_up);
                mLinearLayout_AdvancedButton.setVisibility(View.VISIBLE);
                Toast.makeText(MainMapActivity.this, "Just click  Expand button",
                        Toast.LENGTH_SHORT).show();
            }
            break;
        case R.id.main_poi_information_imagebutton_close:
            Toast.makeText(MainMapActivity.this, "Just click Exit button", Toast.LENGTH_SHORT)
                    .show();
            mLinearLayoutInformation.setVisibility(View.GONE);
            break;
        case R.id.main_poi_information_button_center:
            /*
             * mc = mMapView.getController(); mc.setCenter(gp);
             */
            break;
        case R.id.main_poi_information_button_navigate:
            Toast.makeText(MainMapActivity.this, "Just click Navigate button", Toast.LENGTH_SHORT)
                    .show();
            break;
        case R.id.main_poi_information_button_call:
            Toast.makeText(MainMapActivity.this, "Just click Call button", Toast.LENGTH_SHORT)
                    .show();
            break;
        case R.id.main_poi_information_button_advance:
            changeOptionButton(mButtonAdvance);
            Toast.makeText(MainMapActivity.this, "Just click Advance button", Toast.LENGTH_SHORT)
                    .show();
            break;
        case R.id.main_poi_information_button_search:
            Toast.makeText(MainMapActivity.this, "Just click Search button", Toast.LENGTH_SHORT)
                    .show();
            break;
        default:
            break;
        }
    }
}
