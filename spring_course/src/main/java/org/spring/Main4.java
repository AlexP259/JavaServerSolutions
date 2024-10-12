package org.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main4 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext3.xml");

        Person person = (Person) context.getBean("personBean");
        person.callYourPet();

        System.out.println(person.getSurname());
        System.out.println(person.getAge());

//        Cat myCat = (Cat) context.getBean("catBean");
//        myCat.say();

        context.close();
    }
}
