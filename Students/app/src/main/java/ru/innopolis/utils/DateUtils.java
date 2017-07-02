package ru.innopolis.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ibrahim on 7/2/2017.
 */

public class DateUtils {
    public static String formatDateToString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy h:mm", Locale.ENGLISH);
        return formatter.format(date);
    }

    public static String formatTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("h:mm", Locale.ENGLISH);
        return formatter.format(date);
    }

    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yy", Locale.ENGLISH);
        return formatter.format(date);
    }
}
