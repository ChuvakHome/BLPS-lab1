//package ru.itmo.se.bl.lab1.controller;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Stream;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import ru.itmo.se.bl.lab1.dto.InternationalPassportDTO;
//import ru.itmo.se.bl.lab1.dto.PassportDTO;
//import ru.itmo.se.bl.lab1.dto.PaymentInfoDTO;
//import ru.itmo.se.bl.lab1.entity.Booking;
//import ru.itmo.se.bl.lab1.entity.InternationalPassport;
//import ru.itmo.se.bl.lab1.entity.TouristInfo;
//import ru.itmo.se.bl.lab1.model.CardInfo;
//import ru.itmo.se.bl.lab1.service.BookingService;
//import ru.itmo.se.bl.lab1.utils.ValidationUtils;
//import ru.itmo.se.lab1.response.PaymentResponse;
//
//@RestController
//@RequestMapping("/api/payment")
//public class PaymentController {
//	@Autowired
//	private BookingService service;
//	
//	@PostMapping("/pay")
//	public PaymentResponse doPay(@RequestBody PaymentInfoDTO paymentInfoDTO) {
//		InternationalPassportDTO[] internationalPassports = paymentInfoDTO.getInternationalPassports();
//		PassportDTO[] passports =  paymentInfoDTO.getPassports();
//		
//		Date now = Date.valueOf(LocalDate.now());
//		
//		if (!Arrays.stream(internationalPassports).allMatch(ValidationUtils::validateInternationPassport))
//			return new PaymentResponse(false, "Invalid internation passport");
//		
//		if (!Arrays.stream(passports).allMatch(ValidationUtils::validatePassport))
//			return new PaymentResponse(false, "Invalid passport");
//		
//		CardInfo cardInfo = paymentInfoDTO.getCardInfo().toEntity();
//		
//		if (cardInfo.getExpireDate().after(now)) {
//			boolean success = Math.random() * 10 >= 2;
//			
//			if (success) {
//				List<TouristInfo> touristInfo = new ArrayList<>();
//				
//				Stream.of(internationalPassports)
//					.map(dto -> new TouristInfo(0, null, dto.toEntity()))
//					.forEachOrdered(touristInfo::add);
//				
//				Stream.of(passports)
//				  .map(dto -> new TouristInfo(0, dto.toEntity(), null))
//				  .forEachOrdered(touristInfo::add);
//				
//				Booking booking = new Booking();
//				booking.setTouristInfo(touristInfo);
//				booking.setDays(10);
//				booking.setBookingDate(now);
//
//				service.addBooking(booking);
//				
//				return new PaymentResponse(success, "Payment successfull");
//			}
//			else
//				return new PaymentResponse(success, "Unable to establish connection with the bank");
//		}
//		else
//			return new PaymentResponse(false, "Card expired");
//	}
//}
