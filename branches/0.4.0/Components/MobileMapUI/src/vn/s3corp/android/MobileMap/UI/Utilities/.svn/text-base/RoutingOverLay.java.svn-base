package vn.s3corp.android.MobileMap.UI.Utilities;

import java.util.ArrayList;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class RoutingOverLay extends Overlay {
	private ArrayList<GeoPoint> mGeoPoints;
	private int mRadius = 6;
	private int defaultColor;
	

	public RoutingOverLay(ArrayList<GeoPoint> geoPoints) { // GeoPoint is a int. (6E)
		this.mGeoPoints = geoPoints;
		defaultColor = 999; // no defaultColor
	}

	public RoutingOverLay(ArrayList<GeoPoint> geoPoints, int defaultColor) {
		this.mGeoPoints = geoPoints;
		this.defaultColor = defaultColor;
	}

	@Override
	public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
		Projection projection = mapView.getProjection();
		if (shadow == false) {
			Paint paint = new Paint();
			paint.setAntiAlias(true);
			Point point = new Point();

			Object[] geoPoints = this.mGeoPoints.toArray();
			for (int i = 0; i < geoPoints.length; i++) {
				if (0 == i) {
					projection.toPixels((GeoPoint) geoPoints[i], point);
					if (defaultColor == 999)
						paint.setColor(Color.BLUE);
					else
						paint.setColor(defaultColor);
					RectF oval = new RectF(point.x - mRadius, point.y - mRadius,
							point.x + mRadius, point.y + mRadius);
					// start point
					canvas.drawOval(oval, paint);
				} else {
					Point point2 = new Point();
					projection.toPixels((GeoPoint) geoPoints[i], point2);

					if (defaultColor == 999)
						paint.setColor(Color.RED);
					else
						paint.setColor(defaultColor);
					paint.setStrokeWidth(5);
					paint.setAlpha(120);
					canvas.drawLine(point.x, point.y, point2.x, point2.y, paint);

					if (geoPoints.length - 1 == i) {
						/* the last path */
						if (defaultColor == 999)
							paint.setColor(Color.GREEN);
						else
							paint.setColor(defaultColor);
						RectF oval = new RectF(point2.x - mRadius, point2.y - mRadius,
								point2.x + mRadius, point2.y + mRadius);
						/* end point */
						paint.setAlpha(255);
						canvas.drawOval(oval, paint);
					}
					point = point2;
				}
			}
		}
		return super.draw(canvas, mapView, shadow, when);
	}

}
