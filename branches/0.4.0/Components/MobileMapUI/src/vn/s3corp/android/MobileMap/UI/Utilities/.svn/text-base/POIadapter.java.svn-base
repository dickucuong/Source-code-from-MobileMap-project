package vn.s3corp.android.MobileMap.UI.Utilities;

import java.util.ArrayList;

import vn.s3corp.android.MobileMap.DataAccess.ContentManager;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Poi;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DataAccessException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DatabaseException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.InvalidDataException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.SystemException;
import vn.s3corp.android.MobileMap.UI.MobileMapApp;
import vn.s3corp.android.MobileMap.UI.R;
import vn.s3corp.android.MobileMap.UI.Activities.MainMapActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class POIadapter extends ArrayAdapter<Poi> {
    private Context mContext;
    private ArrayList<Poi> mListPoi;
    Poi p = null;

    public POIadapter(Context context, ArrayList<Poi> mListPoi) {
        super(context, android.R.layout.activity_list_item, mListPoi);
        this.mContext = context;
        this.mListPoi = mListPoi;
    }

    public Long Poi_id(int position) {
        return mListPoi.get(position).getId();

    }

    public View getView(final int position, View convertView, final ViewGroup parent) {
        MobileMapApp app = (MobileMapApp) mContext.getApplicationContext();
        ContentManager contentManager;
        try {
            contentManager = app.getContentManager();
            p = contentManager.getDetailPoiInformation(mListPoi.get(position).getId());
        } catch (DataAccessException e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

        } catch (DatabaseException e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

        } catch (InvalidDataException e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

        } catch (SystemException e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_SHORT).show();

        }

        if (p != null) {
            LayoutInflater li = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.result_search_listview, null);
            TextView txtNamePOI = (TextView) convertView
                    .findViewById(R.id.result_search_listview_textview_name);
            txtNamePOI.setText(p.getName());
            final TextView txtAddressPOI = (TextView) convertView
                    .findViewById(R.id.result_search_listview_textview_address);
            txtAddressPOI.setText(p.getAddress().getAddressString());
            TextView txtPhonePOI = (TextView) convertView
                    .findViewById(R.id.result_search_listview_textview_phone);
            txtPhonePOI.setText("SDT : 01656216706");
            TextView txtRewiewPOI = (TextView) convertView
                    .findViewById(R.id.result_search_listview_textview_reveiw);
            txtRewiewPOI.setText("????");
            final ImageView mImageViewMap = (ImageView) convertView
                    .findViewById(R.id.result_search_listview_imageview_showmap);
            mImageViewMap.setOnClickListener(new OnClickListener() {

                public void onClick(View v) {
                    Intent mIntent = new Intent(mContext.getApplicationContext(),
                            MainMapActivity.class);
                    mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mIntent.putExtra("Id_POI", mListPoi.get(position).getId());
                    mContext.getApplicationContext().startActivity(mIntent);

                }
            });
            return convertView;
        } else {
            return null;
        }
    }

}
