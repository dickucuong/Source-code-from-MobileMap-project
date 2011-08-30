/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author hieu.ha
 * @version 0.4.0
 * 
 */
package vn.s3corp.android.MobileMap.UI.Activities;

import vn.s3corp.android.MobileMap.DataAccess.ContentManager;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Poi;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DataAccessException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DatabaseException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.InvalidDataException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.SystemException;
import vn.s3corp.android.MobileMap.UI.MobileMapApp;
import vn.s3corp.android.MobileMap.UI.R;
import vn.s3corp.android.MobileMap.UI.Utilities.Routing;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class InformationActivity extends ListActivity {
    // I use HashMap arraList which takes objects
    private ArrayList<HashMap<String, Object>> myFeatures;
    private static final String FEATUREKEY = "featurename";
    private static final String DESCRIPTIONKEY = "description";
    private static final String IMGKEY = "iconfromraw";
    private ListView mListView;
    private SimpleAdapter mAdapter;
    private long poiId;
    private long ratingValue;
    private Intent intent;
    public static final int REQUEST_CODE = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_tab_detail_poi);
            intent = getIntent();
            poiId = intent.getExtras().getLong("poiId");
            ratingValue = intent.getExtras().getLong("ratingValue");
            mListView = (ListView) findViewById(android.R.id.list);
            myFeatures = new ArrayList<HashMap<String, Object>>();
            HashMap<String, Object> hm;

            // With the help of HashMap add Key of Feature like name and icon
            // path
            hm = new HashMap<String, Object>();
            hm.put(FEATUREKEY, "Show on map");
            hm.put(DESCRIPTIONKEY, "This is Show on map feature");
            hm.put(IMGKEY, android.R.drawable.ic_menu_compass); 
                                                            
            myFeatures.add(hm);

            hm = new HashMap<String, Object>();
            hm.put(FEATUREKEY, "Get direction");
            hm.put(DESCRIPTIONKEY, "This is Get direction feature");
            hm.put(IMGKEY, android.R.drawable.ic_menu_directions);
            myFeatures.add(hm);

            hm = new HashMap<String, Object>();
            hm.put(FEATUREKEY, "Navigate");
            hm.put(DESCRIPTIONKEY, "This is Navigate feature");
            hm.put(IMGKEY, android.R.drawable.ic_menu_revert); 
                                                            
            myFeatures.add(hm);

            hm = new HashMap<String, Object>();
            hm.put(FEATUREKEY, "Call");
            hm.put(DESCRIPTIONKEY, "This is Call feature");
            hm.put(IMGKEY, android.R.drawable.ic_menu_call);
            myFeatures.add(hm);
            
            hm = new HashMap<String, Object>();
            hm.put(FEATUREKEY, "Rate");
            hm.put(DESCRIPTIONKEY, "Rating of user: "+ratingValue+ "/5");
            hm.put(IMGKEY, android.R.drawable.star_big_off);
            myFeatures.add(hm);

            // Define SimpleAdapter and Map the values with Row view
            mAdapter = new SimpleAdapter(this, myFeatures, R.layout.layout_lisview_tab_information,
                    new String[] { FEATUREKEY, DESCRIPTIONKEY, IMGKEY }, new int[] { R.id.text1,
                            R.id.text2, R.id.img });
            mListView.setAdapter(mAdapter);

            mListView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position) {
                    case 0:

                        intent.putExtra("returnedData", poiId);
                        setResult(RESULT_OK, intent);
                        finishActivity(REQUEST_CODE);

                        break;
                    case 1:
                        break;
                    case 2:
                    	//routing
                    	
                    	//Routing routing = new Routing(startPoint, destPoint, color, mMapView);	
                    	finishActivity(REQUEST_CODE);
                        break;
                    default:

                        break;
                    }
                }
            });
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        // Get poi detail
        GetInformation(poiId);
    }

    /**
     * Get detail infomation of POI
     * 
     * @param id
     * @author hieu.ha
     */
    public void GetInformation(long id) {
        MobileMapApp app = (MobileMapApp) getApplicationContext();

        TextView mTextViewName = (TextView) findViewById(R.id.activity_tab_detail_poi_label_name);
        TextView mTextViewAddress = (TextView) findViewById(R.id.activity_tab_detail_poi_label_address);
        TextView mTextViewLongtitude = (TextView) findViewById(R.id.activity_tab_detail_poi_label_longtitude);
        TextView mTextViewLatitude = (TextView) findViewById(R.id.activity_tab_detail_poi_label_latitude);
        TextView mTextViewDescription = (TextView) findViewById(R.id.activity_tab_detail_poi_label_description);
        RatingBar mRatingBarFavorite = (RatingBar) findViewById(R.id.activity_tab_detail_poi_rating_poi);

        try {
            ContentManager contentmanager = app.getContentManager();
            Poi poidetail = contentmanager.getDetailPoiInformation(id);

            if (null != poidetail) {
                String mName = "Name: " + poidetail.getName();
                String mAddress = "Address: " + poidetail.getAddress().getAddressString();
                String mDescription = "Description: " + poidetail.getDescriptions();
                String mLatitude = "Latitude: " + String.valueOf(poidetail.getLatitude());
                String mLongtitude = "Longtitude: " + String.valueOf(poidetail.getLongitude());

                mRatingBarFavorite.setRating(poidetail.getRating());
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
    }
}