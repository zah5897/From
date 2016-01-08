package com.haoqi.from.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by youxifuhuaqi on 2016/1/8.
 */
public class TimeUtil {

    public static String format(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sdf.format(new Date(time));
    }
}
