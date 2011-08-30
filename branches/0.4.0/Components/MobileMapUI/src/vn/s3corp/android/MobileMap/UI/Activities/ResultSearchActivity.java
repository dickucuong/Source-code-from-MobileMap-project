package vn.s3corp.android.MobileMap.UI.Activities;

import java.util.ArrayList;

import vn.s3corp.android.MobileMap.DataAccess.ContentManager;
import vn.s3corp.android.MobileMap.DataAccess.R;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Category;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Poi;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.SearchOption;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DataAccessException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DatabaseException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.SystemException;
import vn.s3corp.android.MobileMap.UI.MobileMapApp;
import vn.s3corp.android.MobileMap.UI.Utilities.CategoryAdapter;
import vn.s3corp.android.MobileMap.UI.Utilities.POIadapter;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class ResultSearchActivity extends Activity implements OnClickListener, OnItemClickListener {

    private POIadapter mPoIadapter;

    /**
     * Result Search view
     * 
     * @author anh.trinh
     */
    private LinearLayout mLinearLayoutExpand;
    private Button mButtonExpand, mButtonSearch, mButtonPick;
    private EditText mEditTextSearch, mEditTextLatitude, mEditTextLongitude, mEditTextRadius;
    private ArrayList<Category> mListCategories;
    private Spinner mSpinnerCategory;
    private ListView mListView;
    private ToggleButton mToggleButtonCurrent;
    private String mStringNamePOI = "", mStringLongitudePOI = "0",mStringLatitudePOI="0",mStringRadiusPOI="0", mStringLongitudeCurrentPOI = "",
            mStringLatitudeCurrentPOI = "", mStringCategory = "0";
    private SearchOption mSearchOption = new SearchOption();
    private MobileMapApp app;
    private ContentManager contentManager;
    private CheckBox mCheckBox;
    private Thread mThread;
    private ProgressDialog mdialog;
    private Category SelectedCategory = new Category();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_search);
        mdialog = ProgressDialog.show(this, "", "Loading. Please wait...", true);
        mButtonSearch = (Button) findViewById(R.id.result_search_button_search);
        mButtonExpand = (Button) findViewById(R.id.result_search_button_expand);
        mLinearLayoutExpand = (LinearLayout) findViewById(R.id.result_search_linearlayout_expand);
        mEditTextSearch = (EditText) findViewById(R.id.result_search_edittext_search);
        mListView = (ListView) findViewById(R.id.result_search_listview_result);
        mEditTextLatitude = (EditText) findViewById(R.id.result_search_edittext_latitude);
        mEditTextLongitude = (EditText) findViewById(R.id.result_search_edittext_longitude);
        mEditTextRadius = (EditText) findViewById(R.id.result_search_edittext_radius);
        mButtonPick = (Button) findViewById(R.id.result_search_button_pick);
        mCheckBox = (CheckBox) findViewById(R.id.result_search_checkbox_allcategory);
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mLinearLayoutExpand.setVisibility(View.GONE);
        mButtonExpand.setOnClickListener(this);
        mButtonSearch.setOnClickListener(this);
        mButtonPick.setOnClickListener(this);
        mCheckBox.setOnClickListener(this);
        // Set Category
        app = (MobileMapApp) getApplicationContext();
        mSpinnerCategory = (Spinner) findViewById(R.id.result_search_spinner_category);
        try {
            // Get All Categories
            contentManager = app.getContentManager();
            ArrayList<Category> allCategories = new ArrayList<Category>();
            allCategories = contentManager.getAllPoiCategories();
            // Create a Adapter
            final CategoryAdapter adapter = new CategoryAdapter(getApplicationContext(),
                    allCategories, CategoryAdapter.SPINNER);
            // Apply the Adapter to Spinner
            mSpinnerCategory.setAdapter(adapter);
            // Handle selected event item of spinner
            mSpinnerCategory.setOnItemSelectedListener(new OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long i) {
                    SelectedCategory = adapter.getItem(position);

                    if (null == mListCategories) {
                        mListCategories = new ArrayList<Category>();
                    } else {
                        mListCategories.add(SelectedCategory);
                        mStringCategory = Long.toString(SelectedCategory.getId());
                        mCheckBox.setChecked(false);
                    }
                }

                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub
                }
            });
        } catch (DataAccessException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (DatabaseException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (SystemException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            CharSequence SearchString = extras.getCharSequence("Search_Text");
            String[] Search = SearchString.toString().split("/");
            mEditTextSearch.setText(Search[1]);
            mEditTextLongitude.setText(Search[3]);
            mEditTextLatitude.setText(Search[4]);
            mSearchOption.setSearchString(Search[1]);
            SelectedCategory.setId(Long.parseLong(Search[2]));
            mSearchOption.setCategory(SelectedCategory);
            mSearchOption.setCenterLongitude(Long.parseLong(Search[3]));
            mSearchOption.setCenterLatitude(Long.parseLong(Search[4]));
            mSearchOption.setDistance(Long.parseLong(Search[5]));
            mStringLongitudeCurrentPOI = Search[6];
            mStringLatitudeCurrentPOI = Search[7];
        }
        final Handler handler = new Handler();
        mThread = new Thread(new Runnable() {
            public void run() {
                Search(mSearchOption);
                handler.post(new Runnable() {
                    public void run() {
                        mListView.setAdapter(mPoIadapter);
                        mdialog.dismiss();
                    }
                });
            }
        });
        mThread.start();
        mListView.setOnItemClickListener(this);
        mToggleButtonCurrent = (ToggleButton) findViewById(R.id.result_search_togglebutton_current);
        mToggleButtonCurrent.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    mEditTextLatitude.setText(mStringLatitudeCurrentPOI);
                    mEditTextLongitude.setText(mStringLongitudeCurrentPOI);
                } else {
                    mEditTextLatitude.setText("");
                    mEditTextLongitude.setText("");
                }
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.result_search_button_expand:
            if (mLinearLayoutExpand.getVisibility() == View.GONE) {
                mLinearLayoutExpand.setVisibility(View.VISIBLE);
                mButtonExpand.setBackgroundResource(android.R.drawable.arrow_up_float);
            } else {
                mLinearLayoutExpand.setVisibility(View.GONE);
                mButtonExpand.setBackgroundResource(android.R.drawable.arrow_down_float);
            }
            break;
        case R.id.result_search_button_pick:
            if (!mEditTextSearch.getText().toString().equals("")) {
                mStringNamePOI = mEditTextSearch.getText().toString();
            }
            CharSequence StringSearch = "/" + mStringNamePOI + "/" + mStringCategory;
            Intent mIntent = new Intent(getApplicationContext(), MainMapActivity.class);
            mIntent.putExtra("Result_Search_Text", StringSearch);
            startActivity(mIntent);
            break;
        case R.id.result_search_checkbox_allcategory:
            if (mCheckBox.isChecked() == true) {
                SelectedCategory.setId(0);
                mStringCategory = "0";
            }
            break;
        case R.id.result_search_button_search:
            mdialog.show();
            mLinearLayoutExpand.setVisibility(View.GONE);
            mButtonExpand.setBackgroundResource(android.R.drawable.arrow_down_float);
            mSearchOption.setSearchString(mEditTextSearch.getText().toString());
            mSearchOption.setCategory(SelectedCategory);
            if (mLinearLayoutExpand.getVisibility() == View.VISIBLE) {
                if(!mEditTextLongitude.getText().toString().equals("")){
                mStringLongitudePOI = mEditTextLongitude.getText().toString();
                }
                if(!mEditTextLatitude.getText().toString().equals("")){
                mStringLatitudePOI = mEditTextLatitude.getText().toString();
                }
                if(!mEditTextRadius.getText().toString().equals("")){
                mStringRadiusPOI = mEditTextRadius.getText().toString();
                }
            }
            mSearchOption.setCenterLongitude(Long.parseLong(mStringLongitudePOI));
            mSearchOption.setCenterLatitude(Long.parseLong(mStringLatitudePOI));
            mSearchOption.setDistance(Long.parseLong(mStringRadiusPOI));
            final Handler handler = new Handler();
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    Search(mSearchOption);
                    handler.post(new Runnable() {
                        public void run() {
                            mListView.setAdapter(mPoIadapter);
                            mdialog.dismiss();
                        }
                    });
                }
            });
            thread.start();
            break;
        default:
            break;
        }
    }

    public void Search(SearchOption searchoption) {
        try {
            contentManager = app.getContentManager();
            ArrayList<Poi> allPoi = new ArrayList<Poi>();
            allPoi = contentManager.searchForPois(mSearchOption);
            mPoIadapter = new POIadapter(getApplicationContext(), allPoi);
        } catch (DataAccessException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (DatabaseException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        } catch (SystemException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        Intent mIntent = new Intent(getApplicationContext(), PoiDetailInformationActivity.class);
        mIntent.putExtra("poiId", mPoIadapter.Poi_id(arg2));
        startActivity(mIntent);
    }
}
