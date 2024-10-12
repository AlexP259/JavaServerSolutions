package org.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component("personBean")
public class Person {
    private Pet pet;

    @Value("${person.surname}")
    private String surname;

    @Value("${person.age}")
    private int age;



    public Person() {
        System.out.println("Person constructor без параметров");
    }

//    @Autowired
//    public Person(@Qualifier("catBean") Pet pet) {
//        System.out.println("Person constructor");
//        this.pet = pet;
//    }

    public Person(Pet pet) {
        System.out.println("Person constructor");
        this.pet = pet;
    }




    public void callYourPet(){
        System.out.println("Привет, питомец!");
        pet.say();
    }

//    @Autowired
//    @Qualifier("catBean")
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
