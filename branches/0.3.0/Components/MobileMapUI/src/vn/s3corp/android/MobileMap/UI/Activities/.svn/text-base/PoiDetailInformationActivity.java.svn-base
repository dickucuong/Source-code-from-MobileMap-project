package vn.s3corp.android.MobileMap.UI.Activities;
import vn.s3corp.android.MobileMap.UI.R;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class PoiDetailInformationActivity extends TabActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_information_poi);
        
        Resources res = getResources(); // Resource object to get Drawables
        TabHost tabHost = getTabHost();  // The activity TabHost
        TabHost.TabSpec spec;  // Resusable TabSpec for each tab
        Intent intent;  // Reusable Intent for each tab
     
        // Create an Intent to launch an Address for the tab (to be reused)
        intent = new Intent().setClass(this, InformationActivity.class);
     
        // Information tab
        spec = tabHost.newTabSpec("information").setIndicator("Information",
                          res.getDrawable(R.layout.ic_tab_information))
                      .setContent(intent);
        tabHost.addTab(spec);
     
        // Detail Information tab
        intent = new Intent().setClass(this, DetailActivity.class);
        spec = tabHost.newTabSpec("detailInformation").setIndicator("DetailInformation",
                          res.getDrawable(R.layout.ic_tab_detail_information))
                      .setContent(intent);
        tabHost.addTab(spec);
     
        //Review/Comment tab
        intent = new Intent().setClass(this, ReviewsActivity.class);
        spec = tabHost.newTabSpec("reviewComment").setIndicator("ReviewComment",
                          res.getDrawable(R.layout.ic_tab_review_comment))
                      .setContent(intent);
        tabHost.addTab(spec);
     
        tabHost.setCurrentTab(1);
    }
}