/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author hieu.ha
 * @version 0.2.0
 * 
 */
package vn.s3corp.android.MobileMap.UI.Utilities;

import java.util.ArrayList;

import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Category;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class CategoryAdapter extends ArrayAdapter<Category> {
    private Context mContext;
    private ArrayList<Category> mCategory;

    /**
     * The constructor.
     * 
     * @param context
     *            The context which this dialog will work.
     * @param category
     *            The Array of category will be showed in spinner
     * @return
     * @author hieu.ha
     * @version 0.2.0
     * 
     */
    public CategoryAdapter(Context context, ArrayList<Category> category) {
        super(context, android.R.layout.simple_spinner_item, category);
        this.mContext = context;
        this.mCategory = category;
    }

    /**
     * Get a View that displays in the spinner.
     * 
     * @author hieu.ha
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView txtCategory = new TextView(getContext());
        txtCategory.setText(mCategory.get(position).getName());
        txtCategory.setTextColor(Color.BLACK);

        return txtCategory;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(android.R.layout.simple_spinner_dropdown_item, null);
        CheckedTextView chkCategoryName = (CheckedTextView) convertView
                .findViewById(android.R.id.text1);
        chkCategoryName.setText(mCategory.get(position).getName());

        return convertView;
    }
}
