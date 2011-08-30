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
import java.util.List;

import vn.s3corp.android.MobileMap.DataAccess.ContentManager;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.LoginSession;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Poi;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DataAccessException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DatabaseException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.InvalidDataException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.SystemException;
import vn.s3corp.android.MobileMap.UI.MobileMapApp;
import vn.s3corp.android.MobileMap.UI.R;
import vn.s3corp.android.MobileMap.UI.Dialogs.LoginRegisterDialog;
import vn.s3corp.android.MobileMap.UI.Dialogs.AddUpdatePOIDialog;
import vn.s3corp.android.MobileMap.UI.Utilities.MapItemizedOverlay;
import vn.s3corp.android.MobileMap.UI.Utilities.MapPOIItem;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.app.Dialog;
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
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
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
public class MainMapActivity extends MapActivity {

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
    private static final int MENU_ADD_POI_ID = Menu.FIRST + 2;
    private static final int CONTEXT_MENU_ADD_POI_ID = Menu.FIRST + 3;
    private static final int CONTEXT_MENU_UPDATE_POI_ID = Menu.FIRST + 4;

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

    private GeoPoint mGeoPoint;

    private GestureDetector mGestureDetector;
    private MapController mMapController;
    private List<Overlay> mMapOverlays;
    private MapItemizedOverlay mItemizedOverlayPublic;
    private MapItemizedOverlay mItemizedOverlayPrivate;
    /**
     * Search view
     * 
     * @author anh.trinh
     */
    private LinearLayout LL_Expand, LL_Search, LL_main;
    private Button btExpand, btpick, btSearch;
    private ToggleButton Tog_Cur;
    private EditText ELong, Elat, ERad;
    private TextView Tv_Remind;
    private GeoPoint p;

    @Override
    protected boolean isRouteDisplayed() {
        return false;
    }

    /**
     * Called when the activity is first created.
     * 
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the main layout.
        setContentView(R.layout.main);

        // Get the MapView component.
        final MapView mapView = (MapView) findViewById(R.id.main_map_view);
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
        // Callback will notify users when a particular motion event has
        // occurred.
        // This class should only be used with MotionEvents reported via touch
        // (don't use for trackball events).
        mGestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e) {
                mapView.showContextMenu();
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Toast.makeText(getApplicationContext(), "Double", Toast.LENGTH_SHORT).show();
                return super.onDoubleTap(e);
            }

            @Override
            public boolean onDown(MotionEvent e) {
                return super.onDown(e);
            }
        });
        Drawable drawablePublic = getResources().getDrawable(android.R.drawable.star_big_on);
        Drawable drawablePrivate = getResources().getDrawable(android.R.drawable.star_big_off);
        mGestureDetector.setIsLongpressEnabled(true);
        MobileMapApp app = (MobileMapApp) this.getApplication();
        mItemizedOverlayPublic = new MapItemizedOverlay(drawablePublic, mapView,
                getApplicationContext());
        mItemizedOverlayPrivate = new MapItemizedOverlay(drawablePrivate, mapView,
                getApplicationContext());
        // Load public POI
        LoadPOI(Poi.POI_TYPE_PUBLIC, mItemizedOverlayPublic);
        // Load private POI
        if (app.checkLoginStatus()) {
            LoadPOI(Poi.POI_TYPE_PRIVATE, mItemizedOverlayPrivate);
        }
        /**
         * Search view
         * 
         * @author anh.trinh
         */
        LL_Search = (LinearLayout) findViewById(R.id.LL_Search);
        LL_Expand = (LinearLayout) findViewById(R.id.LL_Expand);
        btExpand = (Button) findViewById(R.id.btExpand);
        Tog_Cur = (ToggleButton) findViewById(R.id.Tog_Cur);
        ELong = (EditText) findViewById(R.id.ELog);
        Elat = (EditText) findViewById(R.id.ELat);
        btpick = (Button) findViewById(R.id.btpick);
        btSearch = (Button) findViewById(R.id.btSearch);
        LL_main = (LinearLayout) findViewById(R.id.LL_default);
        Tv_Remind = (TextView) findViewById(R.id.Tv_Remind);
        ERad = (EditText) findViewById(R.id.ERad);
        LL_Expand.setVisibility(View.GONE);
        btExpand.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (LL_Expand.getVisibility() == View.GONE) {
                    LL_Expand.setVisibility(View.VISIBLE);
                    Tog_Cur.setChecked(true);
                    p = mapView.getMapCenter();
                    Elat.setText(Double.toString(p.getLatitudeE6() / 1E6));
                    ELong.setText(Double.toString(p.getLongitudeE6() / 1E6));
                    btExpand.setBackgroundResource(android.R.drawable.arrow_up_float);
                } else {
                    LL_Expand.setVisibility(View.GONE);
                    btExpand.setBackgroundResource(android.R.drawable.arrow_down_float);
                }

            }
        });
        Tog_Cur.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked == true) {
                    p = mapView.getMapCenter();
                    Elat.setText(Double.toString(p.getLatitudeE6() / 1E6));
                    ELong.setText(Double.toString(p.getLongitudeE6() / 1E6));
                    ERad.setVisibility(View.VISIBLE);
                } else {
                    Elat.setText("");
                    ELong.setText("");
                    ERad.setVisibility(View.GONE);
                }
            }
        });
        btpick.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Tog_Cur.setChecked(false);
                LL_Search.setVisibility(View.GONE);
                Tv_Remind.setVisibility(View.VISIBLE);

                mapView.setOnTouchListener(new OnTouchListener() {
                    public boolean onTouch(final View v, final MotionEvent event) {
                        // TODO Auto-generated method stub
                        p = ((MapView) v).getProjection().fromPixels((int) event.getX(),
                                (int) event.getY());
                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            new CountDownTimer(2000, 1000) {
                                public void onTick(long millisUntilFinished) {

                                }

                                public void onFinish() {

                                }
                            }.start();
                        }
                        return false;
                    }
                });
            }
        });
        btSearch.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if ("".equals(ERad.getText().toString())) {
                    ERad.requestFocus();
                } else {
                    Double Lat = Double.parseDouble(Elat.getText().toString());
                    Double Long = Double.parseDouble(ELong.getText().toString());
                    p = new GeoPoint((int) (Lat * 1E6), (int) (Long * 1E6));
                    LL_Expand.setVisibility(View.GONE);
                    btExpand.setBackgroundResource(android.R.drawable.arrow_down_float);
                }
            }
        });

    }

    /**
     * Load POI
     * 
     * @param drawable
     * @param poitype
     * @param mapitemizedoverlay
     * 
     * @author hieu.ha
     */
    private void LoadPOI(long poitype, MapItemizedOverlay mapitemizedoverlay) {
        MobileMapApp app = (MobileMapApp) this.getApplication();
        try {

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
                    GeoPoint point = new GeoPoint((int) (poi.getLatitude()),
                            (int) (poi.getLongitude()));
                    MapPOIItem overlayItem = new MapPOIItem(point, poi.getName(),
                            poi.getDescriptions(), poi);
                    mapitemizedoverlay.addOverlayItem(overlayItem);
                }
                // Add ItemizedOverLay over MapView
                if (!mMapOverlays.contains(mapitemizedoverlay)) {
                    mMapOverlays.add(mapitemizedoverlay);
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
        }
    }

    /**
     * Handle touch event on map
     * 
     * @author hieu.ha
     */
    public class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View v, MotionEvent event) {
            // TODO Auto-generated method stub
            GeoPoint p = ((MapView) v).getProjection().fromPixels((int) event.getX(),
                    (int) event.getY());
            mGeoPoint = p;// Use Add POI dialog
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
        default:
            dialog = null;
            break;
        }
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
            ((AddUpdatePOIDialog) dialog).setIsDisplayMode(true);
            break;
        case MainMapActivity.DIALOG_ADD_POI:
            // Prepare Add POI dialog.
            ((AddUpdatePOIDialog) dialog).clearEditText();

            break;
        case MainMapActivity.DIALOG_UPDATE_POI_CONTEXT_MENU:
            // Prepare Update POI dialog.
            ((AddUpdatePOIDialog) dialog).clearEditText();
            // TODO Load data from DB
            break;
        case MainMapActivity.DIALOG_REGISTER_ID:
        case MainMapActivity.DIALOG_LOGIN_ID:
            // Reset the content.
            ((LoginRegisterDialog) dialog).resetTextBoxes();

            break;
        default:
            break;
        }
    }
}