package org.spring_boot.gamestore.dto;

import java.math.BigDecimal;

public class WriteOffRequest {
    private CardRequest cardRequest;
    private BigDecimal sum;

    public WriteOffRequest(CardRequest cardRequest, BigDecimal sum) {
        this.cardRequest = cardRequest;
        this.sum = sum;
    }

    public CardRequest getCardRequest() {
        return cardRequest;
    }

    public void setCardRequest(CardRequest cardRequest) {
        this.cardRequest = cardRequest;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
