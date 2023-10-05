package domain;

import java.util.Date;

public class Person {

    private short id;

    private String name;

    private Date born;

    // Constructors, getters, setters, and other methods

    public Person() {
        // Default constructor
    }

    public Person(short selectedPerson) {
        this.id = selectedPerson;
    }

    public Person(short id, String name, Date born) {
        this.id = id;
        this.name = name;
        this.born = born;
    }

    // Getter and setter methods for id, name, and born

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    // Other methods as needed

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", born=" + born + "]";
    }
}
