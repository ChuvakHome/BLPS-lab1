package ru.itmo.se.bl.lab1.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

import org.springframework.stereotype.Service;

import ru.itmo.se.bl.lab1.model.CardInfo;
import ru.itmo.se.bl.lab1.model.PaymentResult;

@Service
public class PaymentService {
	public PaymentResult pay(CardInfo cardInfo, int charge) {
		Date now = Date.valueOf(LocalDate.now());
		Random random = new Random();
		
		if (cardInfo.getExpireDate().after(now)) {
			PaymentResult[] paymentResults = PaymentResult.values();
			
			return random.nextInt(10) > 2 ? PaymentResult.PAYMENT_SUCCESSFUL : paymentResults[random.nextInt(1, paymentResults.length - 1)];
		}
		else
			return PaymentResult.CARD_EXPIRED;
	}
}
