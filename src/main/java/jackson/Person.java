package jackson_test;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    transient private int age;
    // private String sex;

    public Person() {

    }

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

    @JsonProperty(value = "NAME") // 将name字段序列化成"NAME"
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