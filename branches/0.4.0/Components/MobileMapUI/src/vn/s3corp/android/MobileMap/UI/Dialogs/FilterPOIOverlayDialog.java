/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author hieu.ha
 * @version 0.3.0
 * 
 */
package vn.s3corp.android.MobileMap.UI.Dialogs;

import java.util.ArrayList;

import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Category;
import vn.s3corp.android.MobileMap.UI.R;
import vn.s3corp.android.MobileMap.UI.Activities.MainMapActivity;
import vn.s3corp.android.MobileMap.UI.Utilities.CategoryAdapter;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class FilterPOIOverlayDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    public static ArrayList<Category> sCategory;
    private ArrayList<Category> mDataSourceCategoty;

    public FilterPOIOverlayDialog(Context context, ArrayList<Category> categories) {
        super(context);
        this.mContext = context;
        this.sCategory = categories;
        this.mDataSourceCategoty = categories;// Using set default category
    }

    /**
     * Override onStart
     * 
     * @author hieu.ha
     */
    @Override
    protected void onStart() {
        if (null == sCategory) {
            sCategory = new ArrayList<Category>();
        }
        ListView lstCategory = (ListView) findViewById(R.id.dialog_filter_poi_overlay_listview_category);
        CategoryAdapter adapter = new CategoryAdapter(this.getContext(), mDataSourceCategoty,
                CategoryAdapter.LISTVIEW_FILTER);
        lstCategory.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_filter_poi_overlay);
        setTitle(R.string.menu_filterpoi);
        // Using button
        Button btnCancel = (Button) findViewById(R.id.dialog_filter_poi_overlay_button_cancel);
        Button btnOk = (Button) findViewById(R.id.dialog_filter_poi_overlay_button_ok);
        // Handle event for button
        btnCancel.setOnClickListener(this);
        btnOk.setOnClickListener(this);

    }

    /**
     * Get list category
     * 
     * @return
     * @author hieu.ha
     */
    public ArrayList<Category> getListCategory() {
        return this.sCategory;
    }

    /**
     * Set list of category
     * 
     * @param categories
     *            List of current category on MapView
     * @author hieu.ha
     */
    public void setListCategory(ArrayList<Category> categories) {
        this.sCategory = (ArrayList<Category>) categories.clone();
    }

    public void onClick(View arg0) {
        switch (arg0.getId()) {
        case R.id.dialog_filter_poi_overlay_button_cancel:
            this.dismiss();
            break;
        case R.id.dialog_filter_poi_overlay_button_ok:
            // Call onFinishedDialog method
            ((MainMapActivity) mContext).onFinishedDialog(null, null, this);
            this.dismiss();
            break;
        }
    }
}
