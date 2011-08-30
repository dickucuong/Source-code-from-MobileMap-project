package vn.s3corp.android.MobileMap.UI.Activities;

import java.util.ArrayList;
import java.util.List;

import vn.s3corp.android.MobileMap.DataAccess.DataObjects.Picture;
import vn.s3corp.android.MobileMap.UI.Dialogs.AddUpdatePOIDialog;
import vn.s3corp.android.MobileMap.UI.Utilities.ImageAdapter;

import android.R.array;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import vn.s3corp.android.MobileMap.UI.R;

public class LoadImageActivity extends Activity implements OnClickListener {
    private ArrayList<Picture> list;
    private GridView mGridView;
    private Button mButton_OK, mButton_Cancel;
    private String[] arrpath;
    private boolean[] itemsSelected;
    String path;
    private Bitmap bm;

    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loadimage);
        mGridView = (GridView) findViewById(R.id.loadimage_gridview_image);
        mButton_OK = (Button) findViewById(R.id.loadimage_button_Ok);
        mButton_Cancel = (Button) findViewById(R.id.loadimage_button_cancel);

        list = new ArrayList<Picture>();
        myThread mt = new myThread();
        Thread th = new Thread(mt);
        th.start();
        mButton_OK.setOnClickListener(this);
        mButton_Cancel.setOnClickListener(this);
    }

    public class myThread implements Runnable {
        String output = "";

        public void run() {
            // TODO Auto-generated method stub
            Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            String[] cursor_cols = { MediaStore.Images.Media.DISPLAY_NAME,
                    MediaStore.Images.Media.DATA };
            // String where = MediaStore.Images.Media. + " =1";
            Cursor cursor = getContentResolver().query(uri, cursor_cols, null, null, null);
            int i = 0;
            arrpath = new String[cursor.getCount()];

            while (i < cursor.getCount()) {
                cursor.moveToNext();
                Picture picture=  new Picture();
                path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                Bitmap img = BitmapFactory.decodeFile(path);
                String name = cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                picture.setLocation(path);
                arrpath[i] = path;
                list.add(picture);
                i++;
            }
            handler.sendEmptyMessage(0);
        }

        private Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                ImageAdapter ia = new ImageAdapter(getApplicationContext(), list);
                mGridView.setAdapter(ia);
                itemsSelected = new boolean[ia.getCount()];
                itemsSelected = ia.getitemsSelect();
            }
        };

    }

    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.loadimage_button_cancel:
            finish();
            break;
        case R.id.loadimage_button_Ok:
        int j = 0, l = 0;
        for (int i = 0; i < list.size(); i++) {
            if (itemsSelected[i] == true) {
                j++;
            }
        }
        String[] stringspath = new String[j];
        for (int i = 0; i < list.size(); i++) {
            if (itemsSelected[i] == true) {
                stringspath[l] = arrpath[i];
                l++;
                Bitmap img = BitmapFactory.decodeFile(list.get(i).getLocation());                
                AddUpdatePOIDialog.imageView_picture_poi.setImageBitmap(img);
            }
        }
        AddUpdatePOIDialog.arraypath =stringspath;
        finish();
            
            break;

        default:
            break;
        }
        
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
    }
}
