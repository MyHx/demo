package com.hx;

import lombok.Data;

public class Test {
    public static void main(String[] args) {
        Point<Integer, Integer> p = new Point<>();

        p.setX(10);  // int -> Integer -> Object
        p.setY(20);
        int x = p.getX();  // 必须向下转型
        int y = p.getY();
        System.out.println("This point is：" + x + ", " + y);

        Point<Double, String> p2 = new Point<>();
        p2.setX(25.4);  // double -> Integer -> Object
        p2.setY("东京180度");
        p2.cale(p2.getX(), p2.getY());
    }
}

@Data
class Point<T1, T2> {
    T1 x;
    T2 y;

    public void cale(T1 t1, T2 t2) {
        this.x = t1;
        this.y = t2;
        System.out.println("This point is：" + x + ", " + y);
    }
}