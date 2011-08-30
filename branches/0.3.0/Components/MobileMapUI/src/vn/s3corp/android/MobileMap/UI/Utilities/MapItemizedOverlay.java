/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author hieu.ha
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.UI.Utilities;

import java.util.ArrayList;

import vn.s3corp.android.MobileMap.UI.Activities.MainMapActivity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;

public class MapItemizedOverlay extends ItemizedOverlay<MapPOIItem> {

    private ArrayList<MapPOIItem> mOverlays = new ArrayList<MapPOIItem>();
    private Context mContext;

    public MapItemizedOverlay(Drawable defaultMarker, MapView mapView, Context context) {
        super(boundCenterBottom(defaultMarker));
        this.mContext = context;
    }

    /**
     * Add Overlay Item
     * 
     * @param overlay
     * 
     * @author hieu.ha
     */
    public void addOverlayItem(MapPOIItem overlay) {
        mOverlays.add(overlay);
        setLastFocusedIndex(-1);
        populate();
    }

    /**
     * Remove Overlay Item
     * 
     * @param index
     * 
     * @author hieu.ha
     */
    public void removeOverlayItem(int index) {
        if (index >= 0) {
            mOverlays.remove(index);
            setLastFocusedIndex(-1);
            populate();
        }
    }

    /**
     * Remove all OverlayItem
     * 
     * @author hieu.ha
     */
    public void removeAllOverlayItem() {
        mOverlays.clear();
        setLastFocusedIndex(-1);
        populate();
    }

    @Override
    protected MapPOIItem createItem(int i) {
        return mOverlays.get(i);
    }

    @Override
    public int size() {
        return mOverlays.size();
    }

    @Override
    protected boolean onTap(int index) {
        MapPOIItem item = mOverlays.get(index);
        ((MainMapActivity) mContext).setOverlayItemIndex(index);
        return false;
    }
}
