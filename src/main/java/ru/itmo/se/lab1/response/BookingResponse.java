package ru.itmo.se.lab1.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.itmo.se.bl.lab1.model.PaymentResult;

@AllArgsConstructor
@Getter
public class BookingResponse {
	private boolean success;
	private String result;
	private String message;
	
	public BookingResponse(PaymentResult paymentResult) {
		this(paymentResult.isSuccessfull(), paymentResult.name(), paymentResult.getMessage());
	}
}
