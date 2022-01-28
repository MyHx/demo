package com.hx.controller;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型
 * @author hex
 */
public class TestGenericProgramming {
	static class Animal {
	}
	static class Dog extends Animal {
	}

    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        List<Dog> dogs = new ArrayList<>();
        new TestGenericProgramming().test(animals, dogs);
        new TestGenericProgramming().test2(dogs, animals);
    }

	/**
	 * 再次体会下这个方法示例，然后再看该方法下新增的一个示例，体会其中含义
	 * @param dst
	 * @param src
	 * @param <T>
	 */
	private <T> void test(List<? super T> dst, List<T> src) {
		dst.addAll(src);

//		for (T t : src) {
//			dst.add(t);
//		}
	}

	/**
	 * 新增的方法示例，注意和上面方法入参的区别
	 * @param dst
	 * @param src
	 * @param <T>
	 */
	private <T> void test2(List<? extends T> dst, List<T> src) {
		src.addAll(dst);
		src.add(dst.get(0));
	}

}