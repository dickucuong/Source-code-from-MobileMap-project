package vn.s3corp.android.MobileMap.UI.Activities;

import java.util.ArrayList;

import vn.s3corp.android.MobileMap.DataAccess.ContentManager;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Category;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Poi;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DataAccessException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.DatabaseException;
import vn.s3corp.android.MobileMap.DataAccess.Exceptions.SystemException;
import vn.s3corp.android.MobileMap.UI.Utilities.CategoryAdapter;
import vn.s3corp.android.MobileMap.UI.Utilities.POIadapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import vn.s3corp.android.MobileMap.UI.MobileMapApp;
import vn.s3corp.android.MobileMap.UI.R;
public class ResultSearchActivity extends Activity implements View.OnClickListener {

    private POIadapter mPoIadapter;

    /**
     * Result Search view
     * 
     * @author anh.trinh
     */
    private LinearLayout mLinearLayoutExpand;
    private Button mButtonExpand, mButtonSearch,mButtonReview;
    private EditText mEditTextSearch;
    private ArrayList<Category> mListCategories;
    private Spinner mSpinnerCategory;
    public static ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_search);
        
        mButtonSearch = (Button) findViewById(R.id.result_search_button_search);
        mButtonExpand = (Button) findViewById(R.id.result_search_button_expand);
        mButtonReview = (Button) findViewById(R.id.result_search_button_review);
        mLinearLayoutExpand = (LinearLayout) findViewById(R.id.result_search_linearlayout_expand);
        mEditTextSearch = (EditText) findViewById(R.id.result_search_edittext_search);
        mListView = (ListView) findViewById(R.id.result_search_listview_result);
        mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        mLinearLayoutExpand.setVisibility(View.GONE);
        mButtonExpand.setOnClickListener(this);
        mButtonSearch.setOnClickListener(this);
        mButtonReview.setOnClickListener(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            CharSequence SearchString = extras.getCharSequence("Search_Text");
            String[] Search = SearchString.toString().split("SearchPOI");
            mEditTextSearch.setText(Search[1]);
            String mCategory = Search[2];
            Toast.makeText(getApplicationContext(), mCategory, Toast.LENGTH_SHORT).show();
        }
        MobileMapApp app = (MobileMapApp) this.getApplicationContext();
        final ContentManager ContentManager;
        try {
            ContentManager = app.getContentManager();
            ArrayList<Poi> allPoi = new ArrayList<Poi>();
            allPoi = ContentManager.getAllPublicPoisSimple();
            mPoIadapter = new POIadapter(getApplicationContext(), allPoi);
            mListView.setAdapter(mPoIadapter);
        } catch (DataAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (DatabaseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SystemException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
        case R.id.result_search_button_expand:
            if (mLinearLayoutExpand.getVisibility() == View.GONE) {
                mLinearLayoutExpand.setVisibility(View.VISIBLE);
                mListView.setVisibility(View.GONE);
                mButtonExpand.setBackgroundResource(android.R.drawable.arrow_up_float);
                MobileMapApp app = (MobileMapApp) getApplicationContext();
                // Category
                mSpinnerCategory = (Spinner) findViewById(R.id.result_search_spinner_category);
                try {
                    // Get All Categories
                    ContentManager ContentManager = app.getContentManager();
                    ArrayList<Category> allCategories = new ArrayList<Category>();
                    allCategories = ContentManager.getAllPoiCategories();
                    // Create a Adapter
                    final CategoryAdapter adapter = new CategoryAdapter(getApplicationContext(),
                            allCategories,CategoryAdapter.SPINNER);
                    // Apply the Adapter to Spinner
                    mSpinnerCategory.setAdapter(adapter);
                    // Handle selected event item of spinner
                    mSpinnerCategory.setOnItemSelectedListener(new OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view, int position,
                                long i) {
                            Category SelectedCategory = adapter.getItem(position);

                            if (null == mListCategories) {
                                mListCategories = new ArrayList<Category>();
                            } else {
                                mListCategories.add(SelectedCategory);
                            }
                        }

                        public void onNothingSelected(AdapterView<?> arg0) {
                            // TODO Auto-generated method stub
                        }
                    });
                } catch (DataAccessException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (DatabaseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SystemException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                mLinearLayoutExpand.setVisibility(View.GONE);
                mButtonExpand.setBackgroundResource(android.R.drawable.arrow_down_float);
            }
            break;
        case R.id.result_search_button_search:
          
            mListView.setVisibility(View.GONE);
            mLinearLayoutExpand.setVisibility(View.GONE);
           mButtonExpand.setBackgroundResource(android.R.drawable.arrow_down_float);
            break;
        case R.id.result_search_button_review:
            Intent intent = new Intent(getApplicationContext(), MainMapActivity.class);
            startActivity(intent);
            break;
        default:
            break;
        }
    }
}
