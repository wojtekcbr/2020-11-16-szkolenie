package pl.jsystems.qa.qaapi.model;

public class SimpleUser {

    public String name;
    public String surname;

    public SimpleUser(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public SimpleUser() {
    }

    @Override
    public String toString() {
        return "SimpleUser{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
