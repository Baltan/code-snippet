package io;

import java.io.Serializable;

class Person implements Serializable {
    private static final long serialVersionUID = 4360029389470370723L;

    private String name;
    transient private int age;
    // private String sex;

    public Person(String name, int age) {
        super();
        this.name = name;
        this.age = age;
    }

    // public Person(String name, int age, String sex) {
    // super();
    // this.name = name;
    // this.age = age;
    // this.sex = sex;
    // }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // @Override
    // public String toString() {
    // return "Person [name=" + name + ", age=" + age + ", sex=" + sex + "]";
    // }

    @Override
    public String toString() {
        return "Person [name=" + name + ", age=" + age + "]";
    }

}