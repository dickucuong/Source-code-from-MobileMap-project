package vn.s3corp.android.MobileMap.UI.Utilities;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

public class Routing {
	private GeoPoint startPoint;
	private GeoPoint destPoint;
	private int color;
	private MapView mMapView = null;
	private RoutingOverLay mRouteOverlay = null;
	
	public void setStartPoint(GeoPoint startPoint) {
		this.startPoint = startPoint;
	}

	public GeoPoint getStartPoint() {
		return startPoint;
	}

	public void setDestPoint(GeoPoint destPoint) {
		this.destPoint = destPoint;
	}

	public GeoPoint getDestPoint() {
		return destPoint;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getColor() {
		return color;
	}

	public void setmMapView(MapView mMapView) {
		this.mMapView = mMapView;
	}

	public MapView getmMapView() {
		return mMapView;
	}
	
	
	public Routing(GeoPoint startPoint,GeoPoint destPoint, int color, MapView mMapView){
		this.setStartPoint(startPoint);
		this.setDestPoint(destPoint);
		this.setColor(color);
		this.setmMapView(mMapView);
	}
	
	public void setRoutePath(GeoPoint startPoint,GeoPoint destPoint, int color) {
		this.setStartPoint(startPoint);
		this.setDestPoint(destPoint);
		this.setColor(color);
	}
	
	public void route(){
		// connect to map web service
		StringBuilder urlString = new StringBuilder();
		urlString.append("http://maps.google.com/maps?f=d&hl=en");
		urlString.append("&saddr=");//from
		urlString.append( Double.toString((double)getStartPoint().getLatitudeE6()/1.0E6));
		urlString.append(",");
		urlString.append( Double.toString((double)getStartPoint().getLongitudeE6()/1.0E6));
		urlString.append("&daddr=");//to
		urlString.append( Double.toString((double)getDestPoint().getLatitudeE6()/1.0E6));
		urlString.append(",");
		urlString.append( Double.toString((double)getDestPoint().getLongitudeE6()/1.0E6));
		urlString.append("&ie=UTF8&0&om=0&output=kml");
		Log.d("==========> GET routing url","URL="+urlString.toString());
		// get the kml (XML) doc. And parse it to get the coordinates(direction route).
		Document doc = null;
		HttpURLConnection urlConnection= null;
		URL url = null;
		try
		{
			url = new URL(urlString.toString());
			urlConnection=(HttpURLConnection)url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			urlConnection.connect();
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			doc = db.parse(urlConnection.getInputStream());
			if(doc.getElementsByTagName("GeometryCollection").getLength()>0){
				String path = doc.getElementsByTagName("GeometryCollection").item(0).getFirstChild().getFirstChild().getFirstChild().getNodeValue();
				Log.d("===========> Path","path="+ path);
				String [] pairs = path.split(" ");
				String[] point = pairs[0].split(","); // lngLat[0]=longitude lngLat[1]=latitude lngLat[2]=height

				ArrayList<GeoPoint> geoPoints = new ArrayList<GeoPoint>();

				for(int i=0;i<pairs.length;i++){// the last one would be crash
					point = pairs[i].split(",");

					GeoPoint gp = new GeoPoint((int)(Double.parseDouble(point[1])*1E6),(int)(Double.parseDouble(point[0])*1E6));
					geoPoints.add(gp);
				}

				if (null != this.mRouteOverlay) {
					getmMapView().getOverlays().remove(this.mRouteOverlay);
					this.mRouteOverlay = null;
				}
				
				if (null == this.mRouteOverlay) {
					this.mRouteOverlay = new RoutingOverLay(geoPoints,getColor());
				}
				
				getmMapView().getOverlays().add(this.mRouteOverlay);

			}
		}
		catch (MalformedURLException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}catch (ParserConfigurationException e){
			e.printStackTrace();
		}catch (SAXException e){
			e.printStackTrace();
		}
	}
}
