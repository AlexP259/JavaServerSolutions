package org.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

        GlassCup glassCup = context.getBean("glassCup", GlassCup.class);
        MetallCup metallCup = context.getBean("metallCup", MetallCup.class);

        System.out.println();

        // Завариваем чай в стеклянной чашке
        glassCup.see();

        System.out.println();

        // Завариваем кофе в металлической чашке
        metallCup.see();

    }
}