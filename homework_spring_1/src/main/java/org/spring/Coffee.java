package org.spring;

import org.springframework.stereotype.Component;

@Component
public class Coffee implements Drink {
    @Override
    public void brew() {
        System.out.println("Завариваем кофе...");
    }
}