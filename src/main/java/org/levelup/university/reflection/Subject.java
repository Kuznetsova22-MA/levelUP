package org.levelup.university.reflection;

public class Subject {
    private String name;
    private int hour;

    private Subject(){}

    public Subject(String name) {
        this.name = name;
    }

    public Subject(String name, int hour) {
        this.name = name;
        this.hour = hour;
    }
}
