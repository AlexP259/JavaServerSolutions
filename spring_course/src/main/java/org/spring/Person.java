package org.spring;

public class Person {
    private Pet pet;
    private String surname;
    private int age;

//    public Person(Pet pet) {
//        System.out.println("Person constructor");
//        this.pet = pet;
//    }

    public Person() {
        System.out.println("Person constructor без параметров");
    }

    public void callYourPet(){
        System.out.println("Привет, питомец!");
        pet.say();
    }

    public void setPet(Pet pet) {
        System.out.println("В класс Person добавили животное");
        this.pet = pet;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        System.out.println("Class person: setSurname");
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        System.out.println("Class person: setAge");
        this.age = age;
    }
}
