package org.spring;

import org.springframework.stereotype.Component;

@Component
public class Tea implements Drink {
    @Override
    public void brew() {
        System.out.println("Завариваем чай...");
    }
}