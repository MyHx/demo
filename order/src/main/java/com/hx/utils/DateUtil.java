package com.hx.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    public static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyyMM");

    public static final DateTimeFormatter ONLY_MONTH_FORMATTER = DateTimeFormatter.ofPattern("dd");

    public static final String DAY_FORMATTER = "yyyy-MM-dd";

    public static final String HYPHEN_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DAY_MIN_TIME = " 00:00:00";

    public static final String DAY_MAX_TIME = " 23:59:59";

    public static final String DAY_MIN_DATE_TIME_HOUR_PATTERN = "%s %s:00:00";

    public static final String DAY_MAX_DATE_TIME_HOUR_PATTERN = "%s %s:59:59";

    public static final DateTimeFormatter HYPHEN_DATE_FORMATTER = DateTimeFormatter.ofPattern(DAY_FORMATTER);

    /**
     * 判断指定日期是否是今天
     *
     * @param date 指定日期
     * @return 是否是今天
     */
    public static boolean isToday(Date date) {
        if (date == null) {
            return false;
        }
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        Calendar anotherDay = Calendar.getInstance();
        anotherDay.setTime(date);
        return now.get(Calendar.YEAR) == anotherDay.get(Calendar.YEAR) &&
                now.get(Calendar.DAY_OF_YEAR) == anotherDay.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 判断指定日期是否是明天
     *
     * @param date 指定日期
     * @return 是否是明天
     */
    public static boolean isTomorrow(Date date) {
        if (date == null) {
            return false;
        }
        Calendar now = Calendar.getInstance();
        now.setTime(new Date());
        Calendar anotherDay = Calendar.getInstance();
        anotherDay.setTime(date);
        return now.get(Calendar.YEAR) == anotherDay.get(Calendar.YEAR) &&
                (now.get(Calendar.DAY_OF_YEAR) + 1) == anotherDay.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取结束时间到开始时间之间的 年+天 时间间隔（一年按照365天计算）
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return 当输入时间中有个为null或者开始时间晚于结束时间日期时返回空串，其他情况下返回年+天的时间间隔
     */
    public static String getYearDayIntervalString(LocalDate beginDate, LocalDate endDate) {
        if (beginDate == null || endDate == null) {
            return "";
        }
        if (endDate.isBefore(beginDate)) {
            return "";
        } else if (endDate.isEqual(beginDate)) {
            return "0天";
        }
        long days = endDate.toEpochDay() - beginDate.toEpochDay();
        long year = days / 365;
        long day = days % 365;
        String periodStr = "";
        if (year == 0) {
            periodStr = day + "天";
        } else {
            periodStr = year + "年" + (day == 0 ? "" : day + "天");
        }
        return periodStr;
    }

    /**
     * 获取结束时间到开始时间之间的 年+天 时间间隔（一年按照365天计算）
     *
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 当输入时间中有个为null或者开始时间晚于结束时间日期时返回空串，其他情况下返回年+天的时间间隔
     */
    public static String getYearDayIntervalString(Date beginTime, Date endTime) {
        if (beginTime == null || endTime == null) {
            return "";
        }
        LocalDate beginDate = beginTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate endDate = endTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return getYearDayIntervalString(beginDate, endDate);
    }

    /**
     * 获取当前距离某一天的 年+天(一年按365天计) 时间间隔（不考虑时分秒）
     *
     * @param beginTime 开始时间
     * @return 当输入时间中有个为null或者开始时间晚于结束时间日期时返回空串，其他情况下返回年+天的时间间隔
     */
    public static String getYearDayIntervalString(Date beginTime) {
        return getYearDayIntervalString(beginTime, new Date());
    }

    /**
     * 获取当前距离某一天的 年+天(一年按365天计) 时间间隔（不考虑时分秒）
     *
     * @param beginDate 开始时间
     * @return 当输入时间中有个为null或者开始时间晚于结束时间日期时返回空串，其他情况下返回年+天的时间间隔
     */
    public static String getYearDayIntervalString(LocalDate beginDate) {
        return getYearDayIntervalString(beginDate, new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    /**
     * 把日期字符串格式化成日期类型
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date convert2Date(String dateStr, String format) {
        SimpleDateFormat simple = new SimpleDateFormat(format);
        try {
            // simple.setLenient(true);
            return simple.parse(dateStr);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前时间的前一天或后一天日期
     *
     * @param day
     * @return
     */
    public static String getBeforeOrAfterDate(int day) {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);

        return dateString;
    }

    public static Date getBeforeOrAfterDateEndDate(int day) {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        try {
            return setEndDay(formatter.parse(dateString));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取当前时间的前一天或后一天日期
     *
     * @param day
     * @return
     */
    public static String getSimpleBeforeOrAfterDate(int day) {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(date);

        return dateString;
    }

    public static Date getBeforeOrAfterDateEndDate(int day, Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        try {
            return setEndDay(formatter.parse(dateString));
        } catch (Exception e) {
            return null;
        }
    }

    public static Date getBeforeOrAfterDateMinDate(int day) {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        try {
            return setMinTime(formatter.parse(dateString));
        } catch (Exception e) {
            return null;
        }
    }

    public static Date getBeforeOrAfterDateMinDate(int day, Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);
        try {
            return setMinTime(formatter.parse(dateString));
        } catch (Exception e) {
            return null;
        }
    }

    public static Date getCurDateBeforeOrAfterDate(int day) {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);//把日期往后增加一天.整数往后推,负数往前移动
        return calendar.getTime(); //这个时间就是日期往后推一天的结果
    }

    public static String getBeforeOrAfterDate2(int day) {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("MM月dd日");
        String dateString = formatter.format(date);

        return dateString;
    }

    public static long getBeforeOrAfterDateTime(int day) {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);//把日期往后增加一天.整数往后推,负数往前移动
        return calendar.getTime().getTime(); //这个时间就是日期往后推一天的结果
    }

    public static String getBeforeOrAfterDateNotYear(int day) {
        Date date = new Date();//取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
        String dateString = formatter.format(date);

        return dateString;
    }

    /**
     * 获取某个时间的前一天或后一天日期
     *
     * @param day
     * @return
     */
    public static String getBeforeOrAfterDate(int day, Date date) {
        if (date == null) {
            date = new Date();//取时间
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(date);

        return dateString;
    }

    public static String getBeforeOrAfterDate(int day, Date date, String format) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);//把日期往后增加一天.整数往后推,负数往前移动
        date = calendar.getTime(); //这个时间就是日期往后推一天的结果
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(date);

        return dateString;
    }

    public static boolean isThisTime(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String param = sdf.format(date);//参数时间
        String now = sdf.format(new Date());//当前时间
        if (param.equals(now)) {
            return true;
        }
        return false;
    }

    /**
     * 把日期类型格式化成长字符串
     * 年月日 时分秒
     *
     * @param date
     * @return
     */
    public static String convertDate2LongString(Date date) {
        return convertDate2String(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 把日期类型格式化成断字符串
     * 年月日
     *
     * @param date
     * @return
     */
    public static String convertDate2ShortString(Date date) {
        return convertDate2String(date, "yyyy-MM-dd");
    }

    /**
     * 把日期类型格式化成断字符串(无横线)
     *
     * @param date
     * @return
     */
    public static String convertDate2ShortNoDashString(Date date) {
        return convertDate2String(date, "yyyyMMdd");
    }

    public static String convertDate4ShortString(Date date) {
        return convertDate2String(date, "MM/dd");
    }

    public static String convertDate3ShortString(Date date) {
        return convertDate2String(date, "yyyy/MM/dd");
    }

    public static String convertDateLongString(Date date) {
        return convertDate2String(date, "MM月dd日 HH:mm");
    }

    public static String convertDateString(Date date) {
        return convertDate2String(date, "MM月dd日");
    }

    public static String convertDate2HourString(Date date) {
        return convertDate2String(date, "M月d日 HH:mm");
    }

    public static String convertDateMdString(Date date) {
        return convertDate2String(date, "M.d");
    }

    public static String convertDate5ShortString(Date date) {
        return convertDate2String(date, "MM-dd");
    }

    public static String convertDate6ShortString(Date date) {
        return convertDate2String(date, "yyyy-MM-dd");
    }

    /**
     * 把日期类型格式化成断字符串
     * 年月日
     *
     * @param date
     * @return
     */
    public static String convertTime2ShortString(Date date) {
        return convertDate2String(date, "HH:mm:ss");
    }

    /**
     * 把日期类型格式化成字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String convertDate2String(Date date, String format) {
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            return formater.format(date);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 转sql的time格式
     *
     * @param date
     * @return
     */
    public static Timestamp convertSqlTime(Date date) {
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }

    /**
     * 转sql的日期格式
     *
     * @param date
     * @return
     */
    public static java.sql.Date convertSqlDate(Date date) {
        java.sql.Date Datetamp = new java.sql.Date(date.getTime());
        return Datetamp;
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurrentTime() {
        String format = "yyyy-MM-dd HH:mm:ss";
        return getCurrentDate(format);
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getCurrentDate() {
        String format = "yyyy-MM-dd";
        return getCurrentDate(format);
    }

    /**
     * 获取当前日期
     *
     * @param format
     * @return
     */
    public static String getCurrentDate(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }


    /**
     * timestamp转长String
     * 年月日 时分秒
     *
     * @param ts
     * @return
     */
    public static String convertTimestamp2LongString(Timestamp ts) {
        return convertTimestamp2String(ts, "yyyy-MM-dd HH:mm:ss");
    }


    /**
     * timestamp转短String
     * 年月日
     *
     * @param ts
     * @return
     */
    public static String convertTimestamp2ShortString(Timestamp ts) {
        return convertTimestamp2String(ts, "yyyy-MM-dd");
    }

    /**
     * @param ts
     * @param format
     * @return
     */
    public static String convertTimestamp2String(Timestamp ts, String format) {
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            return formater.format(ts);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 获取时间戳
     *
     * @return
     */
    public static long getTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取月份的天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getDaysOfMonthgetDaysOfMonth(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取日期的年
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获取日期的月
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取日期的日
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 获取日期的时
     *
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR);
    }

    /**
     * 获取日期的分种
     *
     * @param date
     * @return
     */
    public static int getMinute(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * 获取日期的秒
     *
     * @param date
     * @return
     */
    public static int getSecond(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * 获取星期几
     *
     * @param date
     * @return
     */
    public static int getWeekDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek - 1;
    }

    /**
     * 获取哪一年共有多少周
     *
     * @param year
     * @return
     */
    public static int getMaxWeekNumOfYear(int year) {
        Calendar c = new GregorianCalendar();
        c.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
        return getWeekNumOfYear(c.getTime());
    }

    /**
     * 取得某天是一年中的多少周
     *
     * @param date
     * @return
     */
    public static int getWeekNumOfYear(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setMinimalDaysInFirstWeek(7);
        c.setTime(date);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 取得某天所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
        Date time = c.getTime();
        time = DateUtils.setHours(time, 0);
        time = DateUtils.setMinutes(time, 0);
        time = DateUtils.setSeconds(time, 0);
        time = DateUtils.setMilliseconds(time, 0);
        return time;
    }

    /**
     * 取得某天所在周的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
        Date time = c.getTime();
        time = DateUtils.setHours(time, 23);
        time = DateUtils.setMinutes(time, 59);
        time = DateUtils.setSeconds(time, 59);
        time = DateUtils.setMilliseconds(time, 0);
        return time;
    }

    // 获取上周的开始时间
    @SuppressWarnings("unused")
    public static Date getBeginDayOfLastWeek() {
        Date date = new Date();
        if (date == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek - 7);
        return getDayStartTime(cal.getTime());
    }

    // 获取上周的结束时间
    public static Date getEndDayOfLastWeek() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfLastWeek());
        cal.add(Calendar.DAY_OF_WEEK, 6);
        Date weekEndSta = cal.getTime();
        return getDayEndTime(weekEndSta);
    }

    // 获取某个日期的开始时间
    public static Timestamp getDayStartTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    // 获取某个日期的结束时间
    public static Timestamp getDayEndTime(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d) {
            calendar.setTime(d);
        }
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return new Timestamp(calendar.getTimeInMillis());
    }

    // 避免入库 23：59：59 保存为 00：00：00
    public static Date getDayEndTimeV2(Date d) {
        Calendar calendar = Calendar.getInstance();
        if (null != d)
            calendar.setTime(d);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 000);
        return new Date(calendar.getTimeInMillis());
    }

    /**
     * 获取某月的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //获取某月最小天数
        int firstDay = cal.getMinimum(Calendar.DATE);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay);
        Date time = cal.getTime();
        time = DateUtils.setHours(time, 0);
        time = DateUtils.setMinutes(time, 0);
        time = DateUtils.setSeconds(time, 1);
        time = DateUtils.setMilliseconds(time, 0);
        return time;
    }


    /**
     * 获取本月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        Date time = cal.getTime();
        time = DateUtils.setHours(time, 23);
        time = DateUtils.setMinutes(time, 59);
        time = DateUtils.setSeconds(time, 59);
        time = DateUtils.setMilliseconds(time, 0);
        return time;
    }

    /**
     * 获取当前日期所属季度的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfQuarter(Date date) {
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        if (currentMonth >= 1 && currentMonth <= 3) {
            calendar.set(Calendar.MONTH, 1);
        } else if (currentMonth >= 4 && currentMonth <= 6) {
            calendar.set(Calendar.MONTH, 3);
        } else if (currentMonth >= 7 && currentMonth <= 9) {
            calendar.set(Calendar.MONTH, 4);
        } else if (currentMonth >= 10 && currentMonth <= 12) {
            calendar.set(Calendar.MONTH, 9);
        }
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date firstDay = calendar.getTime();
        return firstDay;
    }

    /**
     * 获取本年第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.getActualMinimum(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        Date time = calendar.getTime();
        time = DateUtils.setHours(time, 0);
        time = DateUtils.setMinutes(time, 0);
        time = DateUtils.setSeconds(time, 0);
        time = DateUtils.setMilliseconds(time, 0);
        return time;
    }

    public static Date getLastDayOfLastYear() {
        LocalDate localDate = LocalDate.now();
        final LocalDate lastDayOfLastYear = localDate.plusYears(-1).withMonth(12).withDayOfMonth(31);
        return Date.from(lastDayOfLastYear.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 获取本年的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH, calendar.getActualMaximum(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date time = calendar.getTime();
        time = DateUtils.setHours(time, 23);
        time = DateUtils.setMinutes(time, 59);
        time = DateUtils.setSeconds(time, 59);
        time = DateUtils.setMilliseconds(time, 0);
        return time;
    }


    /**
     * 取得某年某周的第一天 对于交叉:2008-12-29到2009-01-04属于2008年的最后一周,2009-01-05为2009年第一周的第一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getFirstDayOfWeek(int year, int week) {
        Calendar calFirst = Calendar.getInstance();
        calFirst.set(year, 0, 7);
        Date firstDate = getFirstDayOfWeek(calFirst.getTime());

        Calendar firstDateCal = Calendar.getInstance();
        firstDateCal.setTime(firstDate);

        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, firstDateCal.get(Calendar.DATE));

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, (week - 1) * 7);
        firstDate = getFirstDayOfWeek(cal.getTime());

        return firstDate;
    }

    /**
     * 取得某年某周的最后一天 对于交叉:2008-12-29到2009-01-04属于2008年的最后一周, 2009-01-04为
     * 2008年最后一周的最后一天
     *
     * @param year
     * @param week
     * @return
     */
    public static Date getLastDayOfWeek(int year, int week) {
        Calendar calLast = Calendar.getInstance();
        calLast.set(year, 0, 7);
        Date firstDate = getLastDayOfWeek(calLast.getTime());

        Calendar firstDateCal = Calendar.getInstance();
        firstDateCal.setTime(firstDate);

        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, firstDateCal.get(Calendar.DATE));

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, (week - 1) * 7);
        Date lastDate = getLastDayOfWeek(cal.getTime());

        return lastDate;
    }


    private static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(calendarField, amount);
            return c.getTime();
        }
    }

    /*
     * 1则代表的是对年份操作， 2是对月份操作， 3是对星期操作， 5是对日期操作， 11是对小时操作， 12是对分钟操作， 13是对秒操作，
     * 14是对毫秒操作
     */

    /**
     * 增加年
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addYears(Date date, int amount) {
        return add(date, 1, amount);
    }

    /**
     * 增加月
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMonths(Date date, int amount) {
        return add(date, 2, amount);
    }

    /**
     * 增加周
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addWeeks(Date date, int amount) {
        return add(date, 3, amount);
    }

    /**
     * 增加天
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addDays(Date date, int amount) {
        return add(date, 5, amount);
    }

    /**
     * 增加时
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addHours(Date date, int amount) {
        return add(date, 11, amount);
    }

    /**
     * 增加分
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMinutes(Date date, int amount) {
        return add(date, 12, amount);
    }

    /**
     * 增加秒
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addSeconds(Date date, int amount) {
        return add(date, 13, amount);
    }

    /**
     * 增加毫秒
     *
     * @param date
     * @param amount
     * @return
     */
    public static Date addMilliseconds(Date date, int amount) {
        return add(date, 14, amount);
    }


    /**
     * time差
     *
     * @param before
     * @param after
     * @return
     */
    public static long diffTimes(Date before, Date after) {
        return after.getTime() - before.getTime();
    }

    /**
     * 秒差
     *
     * @param before
     * @param after
     * @return
     */
    public static long diffSecond(Date before, Date after) {
        return (after.getTime() - before.getTime()) / 1000;
    }

    /**
     * 分种差
     *
     * @param before
     * @param after
     * @return
     */
    public static int diffMinute(Date before, Date after) {
        return (int) (after.getTime() - before.getTime()) / 1000 / 60;
    }

    /**
     * 时差
     *
     * @param before
     * @param after
     * @return
     */
    public static int diffHour(Date before, Date after) {
        return (int) (after.getTime() - before.getTime()) / 1000 / 60 / 60;
    }

    /**
     * 天数差
     *
     * @param before
     * @param after
     * @return
     */
    public static int diffDay(Date before, Date after) {
        return Integer.parseInt(String.valueOf(((after.getTime() - before.getTime()) / 86400000)));
    }

    /**
     * 月差
     *
     * @param before
     * @param after
     * @return
     */
    public static int diffMonth(Date before, Date after) {
        int monthAll = 0;
        int yearsX = diffYear(before, after);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(before);
        c2.setTime(after);
        int monthsX = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        monthAll = yearsX * 12 + monthsX;
        int daysX = c2.get(Calendar.DATE) - c1.get(Calendar.DATE);
        if (daysX > 0) {
            monthAll = monthAll + 1;
        }
        return monthAll;
    }

    /**
     * 年差
     *
     * @param before
     * @param after
     * @return
     */
    public static int diffYear(Date before, Date after) {
        return getYear(after) - getYear(before);
    }

    /**
     * 设置23:59:59
     *
     * @param date
     * @return
     */
    public static Date setEndDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 设置00:00:00
     *
     * @param date
     * @return
     */
    public static Date setMinTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


    /**
     * 获取本季度的开始时间
     *
     * @return
     */
    public static Date getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        Date time = null;
        if (currentMonth >= 1 && currentMonth <= 3)
            c.set(Calendar.MONTH, 0);
        else if (currentMonth >= 4 && currentMonth <= 6)
            c.set(Calendar.MONTH, 3);
        else if (currentMonth >= 7 && currentMonth <= 9)
            c.set(Calendar.MONTH, 6);
        else if (currentMonth >= 10 && currentMonth <= 12)
            c.set(Calendar.MONTH, 9);
        c.set(Calendar.DATE, 1);
        time = c.getTime();
        return setMinTime(time);
    }


    /**
     * 获取上周一时间
     */
    public static Date getLastWeekMonday(Date date) {
        Date a = DateUtils.addDays(date, -1);
        Calendar cal = Calendar.getInstance();
        cal.setTime(a);
        cal.add(Calendar.WEEK_OF_YEAR, -1);// 一周
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return cal.getTime();
    }

    /**
     * 获取上个月的第一天
     *
     * @return
     */
    public static Date getLastMonthFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取某个月的第一天
     *
     * @return
     */
    public static Date getThisMonthFirstDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 获取某个月的天数
     *
     * @param date
     * @return
     */
    public static Integer getMonthDays(Date date) {
        String[] strNow1 = new SimpleDateFormat("yyyy-MM-dd").format(date).toString().split("-");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.valueOf(strNow1[0]));
        cal.set(Calendar.MONTH, Integer.valueOf(strNow1[01]) - 1);//Java月份才0开始算
        int dateOfMonth = cal.getActualMaximum(Calendar.DATE);
        return dateOfMonth;
    }

    /**
     * 获取某个月的最后一天
     *
     * @return
     */
    public static Date getLastMonthLastDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    public static Date getLastMonthOneDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//2011-03-20 12:20:20
        cal.add(Calendar.MONTH, -1);//取前一个月的同一天
        return cal.getTime();
    }

    public static String getWeekDayStr(Date date) {
        date = null == date ? new Date() : date;
        DayOfWeek today = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).getDayOfWeek();
        switch (today) {
            case MONDAY:
                return "星期一";
            case TUESDAY:
                return "星期二";
            case WEDNESDAY:
                return "星期三";
            case THURSDAY:
                return "星期四";
            case FRIDAY:
                return "星期五";
            case SATURDAY:
                return "星期六";
            case SUNDAY:
                return "星期日";
        }
        return "";
    }

    /**
     * date2比date1多的天数
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //不同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else    //同一年
        {
            System.out.println("判断day2 - day1 : " + (day2 - day1));
            return day2 - day1;
        }
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
        return days;
    }

    /**
     * Transform ISO 8601 string to Calendar.
     */
    public static Date toDate(final String data) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            Date date = formatter.parse(data);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sDate = sdf.format(date);
            return sdf.parse(sDate);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static Date getCurrentDateMin(Date currentDate) {
        LocalDateTime newLocalDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        newLocalDateTime = newLocalDateTime.with(LocalTime.MIN);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = newLocalDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static Date getCurrentDateMax(Date currentDate) {
        LocalDateTime newLocalDateTime = currentDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        newLocalDateTime = newLocalDateTime.with(LocalTime.MAX);
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = newLocalDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    public static String getHourBefore(int minute) {
        Long time = System.currentTimeMillis() - minute * 60 * 1000;
        return convertDate2LongString(new Date(time));
    }

    /**
     * 获取当前yyyyMM格式
     *
     * @return
     */
    public static String getMonthDateStr() {
        return LocalDate.now().format(MONTH_FORMATTER);
    }

    /**
     * 获取当前yyyyMM格式
     *
     * @return
     */
    public static String getOnlyMonthDateStr() {
        return LocalDate.now().format(ONLY_MONTH_FORMATTER);
    }

    /**
     * 是否是日期格式数据
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Boolean isValidDate(String dateStr, String format) {
        SimpleDateFormat simple = new SimpleDateFormat(format);
        try {
            simple.parse(dateStr);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 时间格式字符串转化为指定格式的时间
     *
     * @param beginDate
     * @param dateFormat
     * @return
     */
    public static LocalDate fromString2LocalDate(String beginDate, String dateFormat) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(dateFormat);
        try {
            LocalDate fromDate = LocalDate.parse(beginDate, df);
            return fromDate;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断time1时间是否在time2之后，注意时间格式要一致
     *
     * @param time1
     * @param time2
     * @return
     */
    public static boolean isAfterDate(Date time1, Date time2) {
        if (time1.getTime() == time2.getTime()) {
            return false;
        }

        Calendar t1 = Calendar.getInstance();
        t1.setTime(time1);

        Calendar t2 = Calendar.getInstance();
        t2.setTime(time2);

        if (t1.after(t2)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 传入指定日期，得到yyyy-MM-dd类型的Date
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getCurrentDate(Date date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(date);
        return sdf.parse(s);
    }

}
