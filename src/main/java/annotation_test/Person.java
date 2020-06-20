package annotation_test;

/**
 * Description:
 *
 * @author Baltan
 * @date 2017/12/27 09:18
 */
@MyAnnotation(name = "Baltan", age = 26, nationality = "China")
class Person {
    private String name;
    private Integer age;
    private String nationality;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}
