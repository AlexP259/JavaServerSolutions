package org.spring_boot.gamestore.service;

import org.spring_boot.gamestore.dto.CardRequest;
import org.spring_boot.gamestore.dto.WriteOffRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CardService implements ICardService{

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private UsersEWalletService usersEWalletService;

    @Override
    public boolean validateCard(String paymentSystem, String cardNumber, LocalDate validityPeriod, int cvv, String firstName, String lastName, String city) {

        String url = "http://localhost:8081/api/cards/validate";

        CardRequest request = new CardRequest(paymentSystem, cardNumber, validityPeriod, cvv, firstName, lastName, city);

        try{
            ResponseEntity<Boolean> response = restTemplate.postForEntity(url, request, Boolean.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean upBalance(String paymentSystem, String cardNumber, LocalDate validityPeriod, int cvv, String firstName, String lastName, String city, int eWalletId, BigDecimal sum) {

        String url = "http://localhost:8081/api/cards/writeOff";

        CardRequest cardRequest = new CardRequest(paymentSystem, cardNumber, validityPeriod, cvv, firstName, lastName, city);

        WriteOffRequest writeOffRequest = new WriteOffRequest(cardRequest, sum);

        try{
            ResponseEntity<Boolean> response = restTemplate.postForEntity(url, writeOffRequest, Boolean.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
