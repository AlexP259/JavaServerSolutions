package org.spring_boot.gamestore.enums;

public enum PaymentSystem {
    MASTERCARD("Mastercard"),
    MIR("Мир"),
    VISA("Visa");

    private final String displayName;

    PaymentSystem(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
