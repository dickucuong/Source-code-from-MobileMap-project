/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author nam.tran
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.UI.Activities;

import vn.s3corp.android.MobileMap.UI.R;
import vn.s3corp.android.MobileMap.UI.Dialogs.LoginRegisterDialog;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * The main Activity for Maps.
 * 
 * @author nam.tran
 * @version 0.1.0
 * 
 */
public class MainMapActivity extends MapActivity {

    private static final int MENU_REGISTER_ID   = Menu.FIRST;
    private static final int MENU_LOGIN_ID      = Menu.FIRST + 1;

    private static final int DIALOG_REGISTER_ID = 1;
    private static final int DIALOG_LOGIN_ID    = 2;

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

        // Enable the Built-in zooming feature of MapView.
        mapView.setBuiltInZoomControls(true);

    }

    /**
     * Called when the menu is created.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, MainMapActivity.MENU_REGISTER_ID, 0, R.string.menu_register);
        return true;
    }

    /**
     * Called when the menu item is selected.
     */
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
        case MainMapActivity.MENU_REGISTER_ID:
            // Show the login/register dialog.
            showDialog(MainMapActivity.DIALOG_REGISTER_ID);

            return true;
        case MainMapActivity.MENU_LOGIN_ID:
            // Show the login/register dialog.
            showDialog(MainMapActivity.DIALOG_LOGIN_ID);

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
        default:
            dialog = null;
            break;
        }
        return dialog;
    }
}