package com.hx.test.stream.server;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import com.hx.test.stream.bean.User;
import com.hx.test.stream.bean.UserVO;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserServiceTest {

    //获取用户列表
    List<User> userList = UserService.getUserList();

    /**
     * List与List相互转换，类似拷贝
     */
    @Test
    public void convertTest() {
        List<UserVO> convert = Convert.convert(new TypeReference<List<UserVO>>() {
        }, userList);
        convert.forEach(System.out::println);
    }

    /**
     * 使用forEach()遍历列表信息
     */
    @Test
    public void forEachTest() {
        //遍历用户列表
        userList.forEach(System.out::println);
    }

    /**
     * 使用filter筛选指定部门
     */
    @Test
    public void filterTest() {
        userList = userList.stream().filter(user -> user.getDepartment().equals("研发部")).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(userList)) {
            userList.forEach(System.out::println);
        }
    }

    /**
     * 获取用户集合第一条数据，如果集合没有数据返回null
     */
    @Test
    public void findAnyTest() {
        User user = userList.stream().findAny().orElse(null);
        System.out.println(Objects.isNull(user) ? null : user.toString());

    }

    /**
     * 集合中筛选指定年龄的第一条数据，如没有则为null
     */
    @Test
    public void findAnyTest2() {
        User user = userList.stream().filter(user1 -> user1.getAge().equals(30)).findAny().orElse(null);
        System.out.println(Objects.isNull(user) ? null : user.toString());
    }

    /**
     * 通过findFirst获取集合的第一条数据
     */
    @Test
    public void findFirst() {
        User user = userList.stream().findFirst().orElse(null);
        System.out.println(Objects.isNull(user) ? null : user.toString());
    }

    /**
     * 使用map获取年龄集合
     */
    @Test
    public void mapTest() {
        List<Integer> ages = userList.stream().map(User::getAge).collect(Collectors.toList());
        ages.forEach(System.out::println);
    }


    /**
     * 使用flatMap将流中每一个元素连接成一个流，并且使用distinct去重
     */
    @Test
    public void flatMapTest() {
        //创建城市
        List<String> cityList = new ArrayList<>();
        cityList.add("北京；上海；深圳；");
        cityList.add("广州；深圳；杭州；");

        //分隔城市列表，使用 flatMap() 将流中的每一个元素连接成为一个流。
        cityList = cityList.stream()
                .map(city -> city.split("；"))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        //遍历城市列表
        cityList.forEach(System.out::println);
    }


}