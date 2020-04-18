package com.example.music.utils;

public class TimeUtils {

    //毫秒转换
    public static String formatDuring(long mss) {
        long minutes = (mss % (1000 * 60 * 60)) / (1000 * 60);
        long seconds = (mss % (1000 * 60)) / 1000;

        String m = "";
        String s = "";

        if (minutes < 10) {
            m = "0" + minutes;
        } else {
            m = minutes + "";
        }

        if (seconds < 10) {
            s = "0" + seconds;
        } else {
            s = seconds + "";
        }

        return m + ":" + s;
    }
}
