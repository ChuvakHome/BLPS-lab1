package ru.itmo.se.bl.lab1.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PaymentInfoDTO {
	@NotNull
	private PassportDTO[] passports;
	
	@NotNull
	private InternationalPassportDTO[] internationalPassports;
	
	@NotNull
	private ContactInfoDTO contactInfo;
	
	@NotNull
	private CardInfoDTO cardInfo;
}
