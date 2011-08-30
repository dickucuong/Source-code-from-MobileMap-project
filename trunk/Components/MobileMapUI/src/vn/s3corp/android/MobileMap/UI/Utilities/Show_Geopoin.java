package vn.s3corp.android.MobileMap.UI.Utilities;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class Show_Geopoin extends ItemizedOverlay<OverlayItem> {
    public ArrayList<OverlayItem> mOverlayItems = new ArrayList<OverlayItem>();
    Context mContext;

    public Show_Geopoin(Drawable defaultMarker) {
            super(boundCenterBottom(defaultMarker));
            // TODO Auto-generated constructor stub
    }

    @Override
    protected OverlayItem createItem(int i) {
            // TODO Auto-generated method stub
            return mOverlayItems.get(i);
    }

    @Override
    public int size() {
            // TODO Auto-generated method stub
            return mOverlayItems.size();
    }

    public void addOverlay(OverlayItem overlay) {
            mOverlayItems.add(overlay);
            populate();
    }

    public Show_Geopoin(Drawable defaultMarker, Context context) {
            super(defaultMarker);
            mContext = context;
    }

    @Override
    protected boolean onTap(int index) {
            OverlayItem item = mOverlayItems.get(index);
            AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
            dialog.setTitle(item.getTitle());
            dialog.setMessage(item.getSnippet());
            dialog.show();
            return true;
    }
}
