package org.spring_boot.paymentsystem.service;

import org.spring_boot.paymentsystem.entity.Mastercard;
import org.spring_boot.paymentsystem.entity.Mir;
import org.spring_boot.paymentsystem.entity.Visa;
import org.spring_boot.paymentsystem.entity.base.Card;
import org.spring_boot.paymentsystem.repository.MastercardRepository;
import org.spring_boot.paymentsystem.repository.MirRepository;
import org.spring_boot.paymentsystem.repository.VisaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class CardService implements ICardService{

    @Autowired
    MastercardRepository mastercardRepository;

    @Autowired
    MirRepository mirRepository;

    @Autowired
    VisaRepository visaRepository;


    @Override
    public Optional<Card> validateCard(String paymentSystem, String cardNumber, LocalDate validityPeriod, int cvv, String firstName, String lastName, String city) {
        Optional<? extends Card> card;

        switch(paymentSystem){
            case "MASTERCARD":
                card = mastercardRepository.findByCardNumberAndValidityPeriodAndCvvAndFirstNameAndLastNameAndCity(cardNumber, validityPeriod, cvv, firstName, lastName, city);
                break;
            case "MIR":
                card = mirRepository.findByCardNumberAndValidityPeriodAndCvvAndFirstNameAndLastNameAndCity(cardNumber, validityPeriod, cvv, firstName, lastName, city);
                break;
            case "VISA":
                card = visaRepository.findByCardNumberAndValidityPeriodAndCvvAndFirstNameAndLastNameAndCity(cardNumber, validityPeriod, cvv, firstName, lastName, city);
                break;
            default:
                card = Optional.empty(); // Присвоим пустой Optional. Не null!!!, иначе ошибка
        }

        return (Optional<Card>) card;
    }

    @Override
    public boolean writeOffMoney(String paymentSystem, String cardNumber, LocalDate validityPeriod, int cvv, String firstName, String lastName, String city, BigDecimal sum) {
        Optional<Card> optionalCard = validateCard(paymentSystem, cardNumber, validityPeriod, cvv, firstName, lastName, city);

        if(optionalCard.isEmpty()){
            return false;   // Карта не была найдена
        }

        Card card = optionalCard.get();

        if(card.getBalance().compareTo(sum) >= 0){
            card.setBalance(card.getBalance().subtract(sum));

            switch (paymentSystem) {
                case "MASTERCARD":
                    mastercardRepository.save((Mastercard) card);
                    break;
                case "VISA":
                    visaRepository.save((Visa) card);
                    break;
                case "MIR":
                    mirRepository.save((Mir) card);
                    break;
            }

            return true; // списание прошло успешно
        }

        return false; // недостаточно средств на балансе
    }
}
