package source;

import java.util.Calendar;

/**
 *
 * @author valery.titov
 */
public class DateUtil {
    public static Calendar getOnlyMonth(int i) {
        return new Calendar.Builder().set(Calendar.MONTH, i).build();
    }
    public static Calendar getDay(int i, int i1, int i2) {
        return new Calendar.Builder().setDate(i, i1, i2).build();
    }
    public static Calendar getDayAndTime(int i, int i1, int i2, int i3, int i4, int i5) {
        return new Calendar.Builder().setDate(i, i1 - 1, i2).setTimeOfDay(i3, i4, i5).build();
    }
    public static String dateFullString(Calendar c) {
        String s;
        s = String.format("%1$tH:%1$tM %1$td.%1$tm.%1$ty", c);
        return s;
    }
    public static String dateShortString(Calendar c) {
        String s;
        s = String.format("%1$td.%1$tm.%1$ty", c);
        return s;
    }
    public static String dateToString(Calendar c) {
        String s;
        s = String.format("%1$tY.%1$tm.%1$td %1$tH:%1$tM", c);
        return s;
    }
}