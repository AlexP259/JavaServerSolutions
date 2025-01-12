package org.spring_boot.paymentsystem.controller;

import org.spring_boot.paymentsystem.dto.CardRequest;
import org.spring_boot.paymentsystem.dto.WriteOffRequest;
import org.spring_boot.paymentsystem.service.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("/api/cards")
public class MainController {

    @Autowired
    private ICardService cardService;

    @PostMapping("/validate")
    public ResponseEntity<Boolean> validate(
            @RequestBody CardRequest request) {
        boolean isValid = cardService.validateCard(
                request.getPaymentSystem(),
                request.getCardNumber(),
                request.getValidityPeriod(),
                request.getCvv(),
                request.getFirstName(),
                request.getLastName(),
                request.getCity()
        ).isPresent();

        if (isValid) {
            return ResponseEntity.ok(true); // карта валидна
        } else {
            return ResponseEntity.status(404).body(false); // карта не найдена
        }
    }

    @PostMapping("/writeOff")
    public ResponseEntity<Boolean> writeOff(@RequestBody WriteOffRequest writeOffRequest){

        CardRequest cardRequest = writeOffRequest.getCardRequest();
        BigDecimal sum = writeOffRequest.getSum();

        boolean writeOff = cardService.writeOffMoney(
                cardRequest.getPaymentSystem(),
                cardRequest.getCardNumber(),
                cardRequest.getValidityPeriod(),
                cardRequest.getCvv(),
                cardRequest.getFirstName(),
                cardRequest.getLastName(),
                cardRequest.getCity(),
                sum
        );

        if (writeOff) {
            return ResponseEntity.ok(true); // деньги списались
        } else {
            return ResponseEntity.status(409).body(false); // деньги не списались, 409 значит что недостаточно средств
        }
    }


}
