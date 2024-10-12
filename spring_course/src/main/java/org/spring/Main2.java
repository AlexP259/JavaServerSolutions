package org.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main2 {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext2.xml");

//        Dog myDog = (Dog) context.getBean("myPet");
//        myDog.setName("Снупи");
//        Dog yourDog = (Dog) context.getBean("myPet");
//        yourDog.setName("Долли");
//
//        System.out.println(myDog.getName());
//        System.out.println(yourDog.getName());
//        System.out.println("Переменные ссылаются на один и тот же объект: " + (myDog == yourDog));
//        System.out.println(myDog);
//        System.out.println(yourDog);
        context.close();
    }
}
