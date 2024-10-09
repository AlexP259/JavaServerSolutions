package org.spring;

public class Dog implements Pet{
    @Override
    public void say() {
        System.out.println("Гав-гав");
    }
}
