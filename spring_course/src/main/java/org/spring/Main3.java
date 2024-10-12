package org.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main3 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");

        Dog myDog = (Dog) context.getBean("myPet");
        myDog.say();
        Dog yourDog = (Dog) context.getBean("myPet");
        yourDog.say();

        context.close();
    }
}
