package org.spring;

public class Cat implements Pet {
    public Cat() {
        System.out.println("Constructor cat");
    }

    @Override
    public void say() {
        System.out.println("Мяу-мяу");
    }
}
