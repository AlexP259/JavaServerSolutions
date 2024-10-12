package org.spring;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GlassCup extends Cup {

    public GlassCup(@Qualifier("tea") Drink drink) {
        super(drink);
        System.out.println("Чай уже в стеклянной чашке");
    }

    public void see(){
        brewDrink();
    }

    @Override
    public void addWater() {
        System.out.println("Добавляем воду в стеклянную чашку. Видно, как заваривается напиток.");
    }
}