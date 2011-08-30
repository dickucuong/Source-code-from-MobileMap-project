/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author hieu.ha
 * @version 0.1.0
 * 
 */

package vn.s3corp.android.MobileMap.UI.Utilities;

import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Poi;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class MapPOIItem extends OverlayItem {
    private GeoPoint mGeoPoint;
    private String mName;
    private Poi mPoi;

    public MapPOIItem(GeoPoint geopoint, String name, String snippet, Poi poi) {
        super(geopoint, name, snippet);
        this.mGeoPoint = geopoint;
        this.mName = name;
        this.mPoi = poi;
    }

    public GeoPoint getPoint() {
        return mGeoPoint;
    }

    public String getName() {
        return mName;
    }

    public Poi getPoi() {
        return mPoi;
    }
}
