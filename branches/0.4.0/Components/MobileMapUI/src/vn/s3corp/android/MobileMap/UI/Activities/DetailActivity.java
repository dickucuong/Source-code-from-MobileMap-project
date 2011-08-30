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
import vn.s3corp.android.MobileMap.UI.Utilities.ImageAdapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends Activity {
    private long poiId;
    private Intent intent;
    MobileMapApp app;
    private RatingBar mRatingBarFavorite;
    private Gallery mGallery;
    private ImageView mImageView;
    private TextView mTextViewName,mTextViewAddress,mTextViewDescription,mTextViewLatitude,mTextViewLongtitude;
    
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_detail_information);
        mRatingBarFavorite = (RatingBar) findViewById(R.id.activity_tab_detail_information_rating_poi);
        mTextViewName = (TextView) findViewById(R.id.activity_tab_detail_information_label_name);
        mTextViewAddress = (TextView) findViewById(R.id.activity_tab_detail_information_label_address);
        mTextViewDescription = (TextView) findViewById(R.id.activity_tab_detail_information_label_description);
        mTextViewLatitude = (TextView) findViewById(R.id.activity_tab_detail_information_label_latitude);
        mTextViewLongtitude = (TextView) findViewById(R.id.activity_tab_detail_information_label_longtitude);
        mGallery = (Gallery) findViewById(R.id.activty_tab_detail_information_gallery_picture);
        mImageView = (ImageView) findViewById(R.id.activty_tab_detail_information_imageview_picture);
        intent = getIntent();
        poiId = intent.getExtras().getLong("poiId");        
       try {
        app = (MobileMapApp) getApplicationContext();
            ContentManager contentmanager = app.getContentManager();
            final Poi poidetail = contentmanager.getDetailPoiInformation(poiId);

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
                ImageAdapter ia = new ImageAdapter(getApplicationContext(), poidetail.getPictures());
                mGallery.setAdapter(ia);
                Bitmap img = BitmapFactory.decodeFile(poidetail.getPictures().get(0).getLocation());                
                mImageView.setImageBitmap(img);
                mGallery.setOnItemClickListener(new OnItemClickListener() {
                    public void onItemClick(AdapterView parent, View v, int position,  long id) {
                        Bitmap img = BitmapFactory.decodeFile(poidetail.getPictures().get(position).getLocation());                
                        mImageView.setImageBitmap(img);
                }

                  
          });
            } else
                Toast.makeText(this, "POI is null!", Toast.LENGTH_SHORT).show();
        } catch (DataAccessException e) {
           Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (DatabaseException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

        } catch (InvalidDataException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

        } catch (SystemException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

   }
    }
}