package com.hx.test;


import com.hx.test.async.TestAsyncService;
import org.springframework.beans.factory.annotation.Autowired;


public class Demo {

    public static void  aa() {
        System.out.println("!");
    }

    public static void main(String[] arg) {

//        List<Long> list = new ArrayList<>();
//        list.add(11L);  //向列表中添加数据
//        list.add(10L);  //向列表中添加数据
//        list.add(20L);  //向列表中添加数据
//        List<Long>list1 = new ArrayList<>();
//        list1.add(11L);  //向列表中添加数据
//        list1.add(11L);  //向列表中添加数据
//        list1.add(11L);  //向列表中添加数据
//        list1.add(11L);  //向列表中添加数据
//        list1.add(20L);  //向列表中添加数据
//        list.removeAll(list1);  //从list中移除与list1相同的元素
//        list.forEach(System.out::println);

//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, 1970);//控制
//        cal.set(Calendar.MONTH, Calendar.JANUARY);//控制
//        cal.set(Calendar.DAY_OF_MONTH, 1);//控制
//        Date time = cal.getTime();
//        System.out.println(DateUtil.formatDateTime(time));
//        long timea = time.getTime() + 10 * 60 * 1000;
//        System.out.println(DateUtil.formatDateTime(new Date(timea)));
//
//        Timestamp newLeftTime = DateUtils.convertSqlTime(cal.getTime());
//        long addTime = newLeftTime.getTime() + 10 * 60 * 1000;
//        Timestamp newRightTime = DateUtils.convertSqlTime(new Date(addTime));
//        System.out.println(newLeftTime);
//        System.out.println(newRightTime);

//        // 获取当前时间:
//        Calendar c = Calendar.getInstance();
//        int y = c.get(Calendar.YEAR);
//        int m = 1 + c.get(Calendar.MONTH);
//        int d = c.get(Calendar.DAY_OF_MONTH);
//        int w = c.get(Calendar.DAY_OF_WEEK);
//        int hh = c.get(Calendar.HOUR_OF_DAY);
//        int mm = c.get(Calendar.MINUTE);
//        int ss = c.get(Calendar.SECOND);
//        int ms = c.get(Calendar.MILLISECOND);
//        System.out.println(y + "-" + m + "-" + d + " " + w + " " + hh + ":" + mm + ":" + ss + "." + ms);

    }

}



