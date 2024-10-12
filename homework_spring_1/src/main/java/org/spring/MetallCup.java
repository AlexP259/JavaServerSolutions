package org.spring;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MetallCup extends Cup {
    public MetallCup(@Qualifier("coffee") Drink drink) {
        super(drink);
        System.out.println("Кофе уже в металлической чашке");
    }

    public void see(){
        brewDrink();
    }

    @Override
    public void addWater() {
        System.out.println("Добавляем воду в металлическую чашку. Чашка быстро нагревается.");
    }
}