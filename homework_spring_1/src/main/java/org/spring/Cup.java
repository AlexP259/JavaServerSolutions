package org.spring;

public abstract class Cup implements ActionWithCup {
    protected Drink drink;

    public Cup(Drink drink) {
        this.drink = drink;
    }

    public void brewDrink() {
        addWater();
        drink.brew();
    }
}
