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
import vn.s3corp.android.MobileMap.UI.R;
import vn.s3corp.android.MobileMap.UI.Dialogs.FilterPOIOverlayDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

public class CategoryAdapter extends ArrayAdapter<Category> {
    private Context mContext;
    private ArrayList<Category> mAdapterCategory;
    // mDisplayMode 1 :spinner category ;
    // 2:listview chose filter
    private int mDisplayMode;
    public static final int SPINNER = 1;
    public static final int LISTVIEW_FILTER = 2;

    /**
     * The constructor.
     * 
     * @param context
     *            The context which this dialog will work.
     * @param category
     *            The Array of category will be showed in spinner
     * @param displaymode
     *            1 : display spinner category,2: display listview chose filter
     * @return
     * @author hieu.ha
     * @version 0.2.0
     * 
     */
    public CategoryAdapter(Context context, ArrayList<Category> category, int displaymode) {
        super(context, -1, category);// -1 Not using drawable of android
        this.mContext = context;
        this.mAdapterCategory = category;
        this.mDisplayMode = displaymode;
    }

    /**
     * Get a View that displays in the spinner.
     * 
     * @author hieu.ha
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mDisplayMode == CategoryAdapter.SPINNER) {
            TextView txtCategory = new TextView(getContext());
            txtCategory.setText(mAdapterCategory.get(position).getName());
            txtCategory.setTextColor(Color.BLACK);

            return txtCategory;
        } else {
            if (mDisplayMode == CategoryAdapter.LISTVIEW_FILTER) {
                Category item = this.mAdapterCategory.get(position);

                LayoutInflater li = (LayoutInflater) mContext
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = li.inflate(R.layout.layout_listview_filter_category, null);
                TextView txtCategory = (TextView) convertView
                        .findViewById(R.id.layout_listview_filter_catetory_textview_name);
                txtCategory.setText(item.getName());

                ImageView imgIcon = (ImageView) convertView
                        .findViewById(R.id.layout_listview_filter_catetory_imageview_icon);
                // Set POI's icon
                switch ((int) item.getId()) {
                case 1:
                    imgIcon.setImageResource(R.drawable.default_icon_restaurant);
                    break;
                case 2:
                    imgIcon.setImageResource(R.drawable.default_icon_coffee);
                    break;
                case 3:
                    imgIcon.setImageResource(R.drawable.default_icon_bar);
                    break;
                case 4:
                    imgIcon.setImageResource(R.drawable.default_icon_hotel);
                    break;
                case 5:
                    imgIcon.setImageResource(R.drawable.default_icon_attraction);
                    break;
                case 6:
                    imgIcon.setImageResource(R.drawable.default_icon_atm);
                    break;
                case 7:
                    imgIcon.setImageResource(R.drawable.default_icon_gasstation);
                    break;
                default:
                    // TODO Orther case
                    imgIcon.setImageResource(R.drawable.icon);// Default icon
                    break;
                }

                CheckBox bCheck = (CheckBox) convertView
                        .findViewById(R.id.layout_listview_filter_catetory_checkbox_chosed);

                bCheck.setTag(item);
                // Set checked
                if (FilterPOIOverlayDialog.sCategory.contains(item))
                    bCheck.setChecked(true);
                // Handle on click event
                bCheck.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        CheckBox chkCategory = (CheckBox) v;
                        Category item = (Category) chkCategory.getTag();

                        if (chkCategory.isChecked()) {
                            if (!FilterPOIOverlayDialog.sCategory.contains(item))
                                FilterPOIOverlayDialog.sCategory.add(item);
                        } else {
                            if (FilterPOIOverlayDialog.sCategory.contains(item))
                                FilterPOIOverlayDialog.sCategory.remove(item);
                        }
                    }
                });

                return convertView;
            }
        }
        return null;
    }

    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(android.R.layout.simple_spinner_dropdown_item, null);
        CheckedTextView chkCategoryName = (CheckedTextView) convertView
                .findViewById(android.R.id.text1);
        chkCategoryName.setText(mAdapterCategory.get(position).getName());

        return convertView;
    }
}
