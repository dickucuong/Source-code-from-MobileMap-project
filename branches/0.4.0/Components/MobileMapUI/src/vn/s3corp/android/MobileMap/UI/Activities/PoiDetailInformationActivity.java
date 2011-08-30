package vn.s3corp.android.MobileMap.UI.Activities;

import vn.s3corp.android.MobileMap.UI.R;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class PoiDetailInformationActivity extends TabActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_information_poi);
        // Get POI from MapView
        long poiId = getIntent().getExtras().getLong("poiId");
        long ratingValue = getIntent().getExtras().getLong("ratingValue");
        Resources res = getResources();
        TabHost tabHost = getTabHost();
        TabHost.TabSpec spec;
        Intent intent;

        // Create an Intent to launch an Address for the tab (to be reused)
        intent = new Intent().setClass(this, InformationActivity.class);
        intent.putExtra("poiId", poiId);
        intent.putExtra("ratingValue", ratingValue);
        // Information tab
        spec = tabHost.newTabSpec("information")
                .setIndicator("Information", res.getDrawable(R.drawable.ic_tab_information))
                .setContent(intent);
        tabHost.addTab(spec);

        // Detail Information tab
        intent = new Intent().setClass(this, DetailActivity.class);
        intent.putExtra("poiId", poiId);
        
        spec = tabHost.newTabSpec("detailInformation")
                .setIndicator("Detail", res.getDrawable(R.drawable.ic_tab_detail_information))
                .setContent(intent);
        tabHost.addTab(spec);

        // Review/Comment tab
        intent = new Intent().setClass(this, ReviewsActivity.class);
        intent.putExtra("poiId", poiId);
        spec = tabHost.newTabSpec("reviewComment")
                .setIndicator("Comment", res.getDrawable(R.drawable.ic_tab_review_comment))
                .setContent(intent);
        tabHost.addTab(spec);
        tabHost.setCurrentTab(0);

    }

    /**
     * Override finishActivityFromChild : Return data
     * 
     * @author hieu.ha
     */
    @Override
    public void finishActivityFromChild(Activity child, int requestCode) {
        super.finishActivityFromChild(child, requestCode);
        if (requestCode == ((InformationActivity) child).REQUEST_CODE) {
            Intent intentResult = getIntent();
            long poiIdResult = intentResult.getExtras().getLong("poiId");
            intentResult.putExtra("returnedData", poiIdResult);
            setResult(RESULT_OK, intentResult);
            finish();
        }
    }
}