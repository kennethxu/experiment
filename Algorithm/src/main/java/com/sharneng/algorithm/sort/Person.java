package com.sharneng.algorithm.sort;


import java.util.HashMap;
import java.util.Map;

public class Person {
    private String name;
    private Person friend;

    public Person(String name, Person friend) {
        this.name = name;
        this.friend = friend;
    }

    public Person deepClone() {
        Person c = new Person(name, friend);
        if (c.friend != null) deepClone(this, c);
        return c;
    }

    private static void deepClone(Person p, Person c) {
        Map<Person, Person> clones = new HashMap<Person, Person>();
        for (;;) {
            clones.put(p, c);
            if (c.friend == null) break;
            Person cloned = clones.get(c.friend);
            if (cloned != null) {
                c.friend = cloned;
                break;
            }
            p = c.friend;
            c.friend = new Person(p.name, p.friend);
            c = c.friend;
        }
    }

    public static void main(String[] args) {
        Person c = new Person("C", null);
        Person b = new Person("B", c);
        Person a = new Person("A", b);
        c.friend = a;
        Person x = a.deepClone();
        print(x);
    }

    public static void print(Person p) {
        System.out.print(p.name + "->");
        for (Person t = p.friend; t != null; t = t.friend) {
            System.out.print(t.name);
            if (t == p) break;
            System.out.print("->");
        }
    }
}
