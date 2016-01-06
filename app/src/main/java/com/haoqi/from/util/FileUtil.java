package com.haoqi.from.util;

import android.graphics.Bitmap;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by youxifuhuaqi on 2016/1/6.
 */
public class FileUtil {

    public static final String ROOTPATH = "forly";
    public static final String PATH_LOG = "log";
    public static final String PATH_TEMP = "temp";


    public static String getTempPath() {
        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + ROOTPATH + File.separator + PATH_TEMP);
        f.mkdirs();
        return f.getAbsolutePath();
    }

    public static File saveBitmap(Bitmap bitmap) throws FileNotFoundException {
        String pPath = getTempPath();
        if (pPath == null) {
            throw new FileNotFoundException("sd card not exist!");
        }
        File f = new File(pPath, "head_icon_for_modify.png");
        if (f.exists()) {
            f.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return f;
    }
}
