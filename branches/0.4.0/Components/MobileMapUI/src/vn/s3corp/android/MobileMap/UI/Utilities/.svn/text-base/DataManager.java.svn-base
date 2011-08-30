/**
 * S3 Mobile Map project.
 * 
 * @copyright Copyright (C) 2011 Sunrise Software Solutions
 * @author hieu.ha
 * @version 0.4.0
 * 
 */

package vn.s3corp.android.MobileMap.UI.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.FileChannel;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

public class DataManager {
    public static final String DIRIMAGE = "images";
    private static Context mContext;
    public static final int SDCARD = 1;
    public static final int PHONE = 0;

    /**
     * Contructor
     * 
     * @param context
     * @author hieu.ha
     */
    public DataManager(Context context) {
        this.mContext = context;

    }

    /**
     * Copy file from path to app
     * 
     * @param frompath
     * @param topath
     * @param locationmode
     *            1: SD CARD 0: PHONE
     * @return
     * @throws IOException
     */
    public String copyImage(String frompath, File topath, int locationmode) throws IOException {
        File srcFile = new File(frompath);
        String dstPath = null;
        File dstDir = null;
        if (locationmode == SDCARD) {
            if (isSdExist()) {
                dstPath = topath + File.separator + mContext.getPackageName() + File.separator
                        + DIRIMAGE + File.separator;
            } else {
                Toast.makeText(mContext.getApplicationContext(), "Sd card is not exist ",
                        Toast.LENGTH_LONG).show();
                return null;
            }
        } else if (locationmode == PHONE) {
            dstPath = topath + File.separator + DIRIMAGE + File.separator;
        }
        if (null != dstPath) {
            dstDir = new File(dstPath);
            // Check exist
            if (!dstDir.exists()) {
                dstDir.mkdirs();
            }
            // Check exist
            if (srcFile.exists()) {
                String fileName = srcFile.getName();
                FileChannel src = new FileInputStream(srcFile).getChannel();
                // Copy file to current app
                dstPath += fileName;
                FileOutputStream dstFile = new FileOutputStream(dstPath);
                FileChannel dst = dstFile.getChannel();

                // Copy
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();

            } else {
                Toast.makeText(mContext.getApplicationContext(), "Image does not exist",
                        Toast.LENGTH_LONG).show();
            }
        }
        return dstPath;
    }

    /**
     * Download image file from URL
     * 
     * @param fileUrl
     * @return
     * @author hieu.ha
     */
    public Bitmap downloadImageFile(String fileUrl) {
        URL myFileUrl = null;
        Bitmap bmImg = null;
        try {
            myFileUrl = new URL(fileUrl);
            HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();

            bmImg = BitmapFactory.decodeStream(is);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmImg;
    }

    /**
     * Check exist
     * 
     * @return
     * @author hieu.ha
     */
    public boolean isSdExist() {

        return android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED);
    }

    /**
     * Save Image
     * 
     * @param bmImg
     * @param saveto
     * @param filename
     * @author hieu.ha
     */
    public String saveImage(Bitmap bmImg, File topath, String filename, int locationmode) {
        String savedpath = null;
        try {
            if (isSdExist()) {
                String dstPath = null;
                if (locationmode == SDCARD) {
                    if (isSdExist()) {
                        dstPath = topath + File.separator + mContext.getPackageName()
                                + File.separator + DIRIMAGE + File.separator;
                    } else {
                        Toast.makeText(mContext.getApplicationContext(), "Sd card is not exist ",
                                Toast.LENGTH_LONG).show();
                        return savedpath;
                    }
                } else if (locationmode == PHONE) {
                    dstPath = topath + File.separator + DIRIMAGE + File.separator;
                }

                File saveDir = new File(dstPath.toString());
                if (!saveDir.exists()) {
                    saveDir.mkdirs();
                }
                savedpath = dstPath + File.separator + filename;
                File savefile = new File(savedpath);
                FileOutputStream out = new FileOutputStream(savefile);

                bmImg.compress(Bitmap.CompressFormat.JPEG, 90, out);
                out.flush();
                out.close();

                Toast.makeText(mContext.getApplicationContext(),
                        "File is saved in  " + savefile.getAbsolutePath(), Toast.LENGTH_LONG)
                        .show();
            } else {
                Toast.makeText(mContext.getApplicationContext(), "Sd card is not exist ",
                        Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return savedpath;
    }

    /**
     * Get image
     * 
     * @param pathData
     * @return
     * @author hieu.ha
     */
    public Bitmap getImage(String pathData) {
        File file = new File(pathData);
        Bitmap bmImg = BitmapFactory.decodeFile(file.toString());
        return bmImg;
    }
}
