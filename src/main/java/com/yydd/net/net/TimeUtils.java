package com.yydd.net.net;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TimeUtils {

    /**
     * 当前时间
     */
    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Timestamp getTimeAfter(Date time, int duration, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.add(field, duration);
        return new Timestamp(calendar.getTime().getTime());
    }

    public static Timestamp getTimeAfter(Date time, int duration, TimeUnitEnum timeUnit) {
        return getTimeAfter(time, duration, timeUnit.toCalendarField());
    }

    public static Timestamp getTimeAfter(Date time, int duration, TimeUnit unit) {
        return new Timestamp(time.getTime() + unit.toMillis(duration));
    }

    public static Timestamp getTimeAfterNow(long duration, TimeUnit unit) {
        return new Timestamp(System.currentTimeMillis() + unit.toMillis(duration));
    }

    public static Timestamp getTimeAfterNow(int duration, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        calendar.add(field, duration);
        return new Timestamp(calendar.getTime().getTime());
    }

    public static Timestamp getTimeAfterNow(int duration, TimeUnitEnum timeUnit) {
        return getTimeAfterNow(duration, timeUnit.toCalendarField());
    }

    /**
     * 得到今天的日期
     *
     * @return
     */
    public static Date getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(System.currentTimeMillis()));
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * @param time
     * @return
     */
    public static boolean isAfterNow(long time) {
        return System.currentTimeMillis() < time;
    }

    /**
     * @param time
     * @return
     */
    public static boolean isAfterNow(Timestamp time) {
        return System.currentTimeMillis() < time.getTime();
    }

    /**
     * @param time
     * @return
     */
    public static boolean isAfterNow(Date time) {
        return System.currentTimeMillis() < time.getTime();
    }

    /**
     * @param time
     * @return
     */
    public static boolean isBeforeNow(long time) {
        return System.currentTimeMillis() > time;
    }

    /**
     * @param time
     * @return
     */
    public static boolean isBeforeNow(Timestamp time) {
        return System.currentTimeMillis() > time.getTime();
    }

    /**
     * @param time
     * @return
     */
    public static boolean isBeforeNow(Date time) {
        return System.currentTimeMillis() > time.getTime();
    }

    public static boolean isToday(Timestamp time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        return simpleDateFormat.format(time)
                .equals(simpleDateFormat.format(new Date(System.currentTimeMillis())));
    }

    public static String parse(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    public static Timestamp today() {
        return new Timestamp(getTodayDate().getTime());
    }

    public static String todayYMD() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date(System.currentTimeMillis()));
    }
}
