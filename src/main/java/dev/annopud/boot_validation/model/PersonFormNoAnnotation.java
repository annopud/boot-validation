package dev.annopud.boot_validation.model;

public class PersonFormNoAnnotation {

    private String name;
    private Integer age;
    private String guardian;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String toString() {
        return "PersonFormNoAnnotation(Name: " + this.name + ", Age: " + this.age + ")";
    }

    public String getGuardian() {
        return guardian;
    }

    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }
}
