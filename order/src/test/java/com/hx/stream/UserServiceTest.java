package com.hx.stream;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.hx.stream.bean.User;
import com.hx.stream.bean.UserVO;
import com.hx.stream.service.UserService;
import org.junit.Test;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        List<String> strList = Arrays.asList("a", "b", "c");
        strList.forEach(System.out::print);//abc
        System.out.println();
        strList.forEach(System.out::print);//abc
        System.out.println();
        strList.parallelStream().forEachOrdered(System.out::print);//abc
        System.out.println();
        strList.parallelStream().forEach(System.out::print);//bca
    }

    /**
     * 使用filter筛选指定部门
     */
    @Test
    public void filterTest() {
//        userList = userList.stream().filter(user -> user.getDepartment().equals("研发部")).collect(Collectors.toList());
        userList = userList.stream().filter(user -> Arrays.asList(25,20).contains(user.getAge())).collect(Collectors.toList());
//        userList = userList.stream().filter(user -> null != user.getDepartment()).collect(Collectors.toList());
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

        //分隔城市列表，使用 flatMap() 将流中的每一个元素连接成为一个流。
        cityList = cityList.stream()
                .map(city -> city.split("；"))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        //遍历城市列表
        cityList.forEach(System.out::println);
    }

    /**
     * 使用limit获取前n条数据
     */
    @Test
    public void limitTest() {
        List<User> collect = userList.stream().limit(2).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    /**
     * 使用skip跳过前n条数据
     */
    @Test
    public void skipTest() {
        List<User> collect = userList.stream().skip(2).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }

    /**
     * 是否存在指定年龄
     * 全部的年龄是否都是指定年龄
     * 是否没有指定年龄
     */
    @Test
    public void matchTest() {
        boolean a = userList.stream().anyMatch(user -> user.getAge().equals(30));
        boolean b = userList.stream().allMatch(user -> user.getAge().equals(30));
        boolean c = userList.stream().noneMatch(user -> user.getAge().equals(30));
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
    }

    /**
     * 使用reduce取年龄集合的最大值
     * 使用reduce取年龄集合的最小值
     * 使用reduce取年龄集合的总和
     * <p>
     * stream流方式，存在遍历装箱再求和情况
     */
    @Test
    public void reduceTest() {
        Integer maxAge = userList.stream().map(User::getAge).reduce(Integer::max).orElse(0);
        Integer minAge = userList.stream().map(User::getAge).reduce(Integer::min).orElse(0);
        Integer sumAge = userList.stream().map(User::getAge).reduce(0, Integer::sum);
        System.out.println(maxAge);
        System.out.println(minAge);
        System.out.println(sumAge);
    }

    /**
     * 使用reduce取年龄集合的最大值
     * 使用reduce取年龄集合的最小值
     * 使用reduce取年龄集合的总和
     * <p>
     * IntStream流方式
     */
    @Test
    public void reduceTest2() {
        Integer maxAge = userList.stream().mapToInt(User::getAge).max().orElse(0);
        Integer minAge = userList.stream().mapToInt(User::getAge).min().orElse(0);
        Integer sumAge = userList.stream().mapToInt(User::getAge).sum();
        double averageAge = userList.stream().mapToInt(User::getAge).average().getAsDouble();

        System.out.println(maxAge);
        System.out.println(minAge);
        System.out.println(sumAge);
        System.out.println(averageAge);
    }

    /**
     * 统计年龄等于30个数
     * 统计年龄大于30个数
     */
    @Test
    public void countTest() {
        Long countAgeByThirty = userList.stream().filter(user -> user.getAge().equals(30)).count();
        Long countAge = userList.stream().filter(user -> user.getAge() >= (30)).count();
        System.out.println(countAgeByThirty);
        System.out.println(countAge);
    }

    /**
     * 排序
     */
    @Test
    public void sortTest() {
        List<User> ascendingByAge = userList.stream().sorted(Comparator.comparingInt(User::getAge)).collect(Collectors.toList());
        List<User> descendingByAge = userList.stream().sorted(Comparator.comparingInt(User::getAge).reversed()).collect(Collectors.toList());
        ascendingByAge.forEach(System.out::println);
        System.out.println("--------");
        descendingByAge.forEach(System.out::println);
    }

    /**
     * 分组
     */
    @Test
    public void groupTest() {
        Map<String, List<User>> groupByDep = userList.stream().collect(Collectors.groupingBy(User::getDepartment));
        groupByDep.forEach((key, value) -> {
            System.out.println(key + ",");
            value.forEach(System.out::println);
        });
    }

    /**
     * 多级分组
     */
    @Test
    public void manyGroupTest() {
        Map<String, Map<String, List<User>>> manyGroup = userList.stream().collect(Collectors.groupingBy(User::getDepartment, Collectors.groupingBy(User::getSex)));
        String s = JSON.toJSONString(manyGroup);
        System.out.println(s);
        manyGroup.forEach((key1, map) -> {
            System.out.println(key1);
            map.forEach((key2, user) -> {
                System.out.println(key2);
                user.forEach(System.out::println);
            });
        });
    }

    /**
     * 分组汇总平均年龄
     */
    @Test
    public void groupTest2() {
        Map<String, Double> collect = userList.stream().collect(Collectors.groupingBy(User::getDepartment, Collectors.averagingInt(User::getAge)));

        collect.forEach((key, value) -> {
            System.out.println(key + "：平均年龄" + value);
        });
    }

    /**
     * 去重
     */
    @Test
    public void distinctTest() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("3333");
        strings.add("4444");
        strings.add("3333");
        List<String> collect = strings.stream().distinct().collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * 过滤
     */
    @Test
    public void filterTest2() {
        List<String> da = new ArrayList<>();
        List<String> xiao = new ArrayList<>();
        da.add("北京");
        da.add("上海");
        da.add("深圳");
        da.add("深圳22");
        da.add("深圳33");

        xiao.add("深圳");
        xiao.add("深圳33");
        xiao.add("深圳44");

        da = da.stream().filter(a -> !xiao.contains(a)).collect(Collectors.toList());

        // 合并且去重
        List<String> collect = Stream.concat(da.stream(), xiao.stream()).distinct().collect(Collectors.toList());
        System.out.println(da);
        System.out.println(collect);
//        Objects.requireNonNull()
//        CollectionUtils.transform();
//        CollectionUtils.collate()

    }

    /**
     * 无限流
     * 可用limit来限制
     */
    @Test
    public void generateTest() {
        Stream<Integer> stream1 = Stream.generate(() -> new Random().nextInt(10));
//        Stream<Integer> stream2 = Stream.generate(() -> new Random().nextInt(10)).limit(6);
        stream1.forEach(System.out::println);
//        stream2.forEach(System.out::println);
    }

    /**
     * 迭代流
     */
    @Test
    public void iterateTest() {
        //Java 9中对stream.iterate进行了增强。它支持谓词（条件）作为第二个参数，并且如果谓词为false， stream.iterate将停止。
        // Stream.iterate(1, n -> n < 20 , n -> n * 2).forEach(x -> System.out.println(x));
        Stream.iterate(0, n -> n + 1).filter(x -> x % 2 != 0).limit(10).forEach(System.out::println);
    }

    /**
     * 最大值，最小值
     */
    @Test
    public void maxTest() {
        User maxUser = userList.stream().max(Comparator.comparing(a -> a.getAge() > 25)).get();
        User minUser = userList.stream().min(Comparator.comparing(a -> a.getAge() > 25)).get();
        System.out.println(maxUser);
        System.out.println(minUser);
        System.out.println("---------------");
        // get()可能为空，所以警告
        Optional<User> maxOptional = userList.stream().max(Comparator.comparing(a -> a.getAge() > 25));
        maxOptional.ifPresent(System.out::println);
    }

    /**
     * 指定顺序流
     */
    @Test
    public void ofTest() {
        List<String> collect = Stream.of("a", "b", "c").collect(Collectors.toList());
        System.out.println(collect);
    }

    /**
     * peek and forEach
     * 场景不一样 peek是中间操作 foreach是终结消费操作
     */
    @Test
    public void peekTest() {
        Stream<String> stream = Stream.of("12", "15");
        List<String> collect = stream.peek(String::length).collect(Collectors.toList());
//        collect.forEach(System.out::println);
    }


    @Test
    public void joinTest() {
        List<String> collect = userList.stream().map(User::getName).collect(Collectors.toList());
        String join = String.join(",", collect);
        System.out.println(join);
    }

    @Test
    public void groupingTest() {
        Map<String, List<User>> collect = userList.stream().collect(Collectors.groupingBy(record -> record.getName() + "_" + record.getSex() + "_" + record.getAge()));
        collect.forEach((key,value) -> System.out.println(key + "-------" + value));
    }

    @Test
    public void test() {
        List<User> userList = UserService.getUserList();
        Map<String, List<Map<String, Object>>> simplifiedMap =
                Collections.singletonMap("疾病类型",
                        userList.stream()
                                .collect(Collectors.groupingBy(
                                        User::getName,
                                        Collectors.mapping(info -> {
                                            Map<String, Object> productMap = new LinkedHashMap<>();
                                            productMap.put("推荐产品", info.getDepartment());
                                            productMap.put("推荐理由(产品介绍)", info.getAge());
                                            productMap.put("成分功效解释", info.getSex());
                                            return productMap;
                                        }, Collectors.toList())
                                ))
                                .entrySet().stream()
                                .map(entry -> {
                                    Map<String, Object> diseaseGroup = new LinkedHashMap<>();
                                    diseaseGroup.put("疾病名称", entry.getKey());
                                    diseaseGroup.put("推荐产品列表", entry.getValue());
                                    return diseaseGroup;
                                })
                                .collect(Collectors.toList())
                );
        System.out.println(JSONUtil.toJsonStr(simplifiedMap));
    }


}