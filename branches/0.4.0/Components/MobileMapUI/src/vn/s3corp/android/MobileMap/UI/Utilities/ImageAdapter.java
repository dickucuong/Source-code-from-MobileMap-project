package vn.s3corp.android.MobileMap.UI.Utilities;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Picture;
import vn.s3corp.android.MobileMap.UI.R;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<Picture> list_item;
    private boolean[] itemsSelected;

    public ImageAdapter(Context c, List<Picture> obj) {
        mContext = c;
        list_item = obj;
        itemsSelected = new boolean[list_item.size()];
    }

    public int getCount() {
        return list_item.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public boolean[] getitemsSelect() {
        return itemsSelected;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(R.layout.image, null);
        TextView textView = (TextView) convertView.findViewById(R.id.image_name_picture);
        textView.setText(list_item.get(position).getLocation().split("/")[list_item.get(position)
                .getLocation().split("/").length - 1]);
        Log.d("name :", textView.getText().toString());
        ImageView imageview = (ImageView) convertView.findViewById(R.id.image_imageView_picture);
        Bitmap img = BitmapFactory.decodeFile(list_item.get(position).getLocation());
        Log.d("picture :", list_item.get(position).getLocation());
        imageview.setImageBitmap(img);
        CheckBox checkbox = (CheckBox) convertView.findViewById(R.id.image_checkbox_select);

        checkbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                itemsSelected[position] = isChecked;

            }
        });
        return convertView;
    }

}
