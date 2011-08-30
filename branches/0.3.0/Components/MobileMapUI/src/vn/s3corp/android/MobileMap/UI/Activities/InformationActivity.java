package vn.s3corp.android.MobileMap.UI.Activities;

import vn.s3corp.android.MobileMap.UI.R;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class InformationActivity extends ListActivity {
    /** Called when the activity is first created. */
    // I use HashMap arraList which takes objects
    private ArrayList<HashMap<String, Object>> myFeatures;
    private static final String FEATUREKEY = "featurename";
    private static final String DESCRIPTIONKEY = "description";
    private static final String IMGKEY = "iconfromraw";
    private Intent it;
    ListView listView;
    SimpleAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.mainlist);
            listView = (ListView) findViewById(android.R.id.list);
            myFeatures = new ArrayList<HashMap<String, Object>>();
            HashMap<String, Object> hm;

            // With the help of HashMap add Key of Feature like name and icon
            // path
            hm = new HashMap<String, Object>();
            hm.put(FEATUREKEY, "Show on map");
            hm.put(DESCRIPTIONKEY, "This is Show on map feature");
            hm.put(IMGKEY, R.drawable.ic_menu_info_details); // i have images in
                                                             // res/raw folder
            myFeatures.add(hm);

            hm = new HashMap<String, Object>();
            hm.put(FEATUREKEY, "Get direction");
            hm.put(DESCRIPTIONKEY, "This is Get direction feature");
            hm.put(IMGKEY, R.drawable.ic_menu_notifications);
            myFeatures.add(hm);

            hm = new HashMap<String, Object>();
            hm.put(FEATUREKEY, "Navigate");
            hm.put(DESCRIPTIONKEY, "This is Navigate feature");
            hm.put(IMGKEY, R.drawable.ic_menu_info_details); // i have images in
                                                             // res/raw folder
            myFeatures.add(hm);

            hm = new HashMap<String, Object>();
            hm.put(FEATUREKEY, "Call");
            hm.put(DESCRIPTIONKEY, "This is Call feature");
            hm.put(IMGKEY, R.drawable.ic_menu_notifications);
            myFeatures.add(hm);

            // Define SimpleAdapter and Map the values with Row view
            // R.layout.tab_information_list_item
            adapter = new SimpleAdapter(this, myFeatures, R.layout.tab_information_list_item,
                    new String[] { FEATUREKEY, DESCRIPTIONKEY, IMGKEY }, new int[] { R.id.text1,
                            R.id.text2, R.id.img });
            listView.setAdapter(adapter);
            listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

            listView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // When clicked, show a toast with the TextView text
                    // Toast.makeText(getApplicationContext(), ((TextView)
                    // view).getText(),
                    // Toast.LENGTH_SHORT).show();
                    Object o = parent.getAdapter().getItemId(position);
                    String text = o.toString();
                    if (text.equalsIgnoreCase("0")) {
                        it = new Intent(getApplicationContext(), ShowOnMapActivity.class);
                        startActivity(it);
                    }
                    if (text.equalsIgnoreCase("1")) {
                        Toast.makeText(getApplicationContext(),
                                "TO DO SOMETHING. You have chosen: " + text, Toast.LENGTH_SHORT)
                                .show();
                    }
                    if (text.equalsIgnoreCase("2")) {
                        Toast.makeText(getApplicationContext(),
                                "TO DO SOMETHING. You have chosen: " + text, Toast.LENGTH_SHORT)
                                .show();
                    }
                }
            });
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}