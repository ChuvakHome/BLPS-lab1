package ru.itmo.se.bl.lab1.controller;

import static ru.itmo.se.bl.lab1.utils.ValidationUtils.nullOrEmpty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.itmo.se.bl.lab1.dto.BookingDTO;
import ru.itmo.se.bl.lab1.dto.InternationalPassportDTO;
import ru.itmo.se.bl.lab1.dto.PassportDTO;
import ru.itmo.se.bl.lab1.dto.PaymentInfoDTO;
import ru.itmo.se.bl.lab1.entity.Booking;
import ru.itmo.se.bl.lab1.entity.Hotel;
import ru.itmo.se.bl.lab1.entity.InternationalPassport;
import ru.itmo.se.bl.lab1.entity.Passport;
import ru.itmo.se.bl.lab1.entity.TouristInfo;
import ru.itmo.se.bl.lab1.entity.Travel;
import ru.itmo.se.bl.lab1.model.CardInfo;
import ru.itmo.se.bl.lab1.model.PaymentResult;
import ru.itmo.se.bl.lab1.service.BookingService;
import ru.itmo.se.bl.lab1.service.HotelService;
import ru.itmo.se.bl.lab1.service.InternationalPassportService;
import ru.itmo.se.bl.lab1.service.PassportService;
import ru.itmo.se.bl.lab1.service.PaymentService;
import ru.itmo.se.bl.lab1.service.TouristInfoService;
import ru.itmo.se.bl.lab1.service.TravelService;
import ru.itmo.se.bl.lab1.utils.ValidationUtils;
import ru.itmo.se.lab1.response.BookingResponse;

@RestController
@RequestMapping("/api/booking")
public class BookingController {
	@Autowired
	private PassportService passportService;
	
	@Autowired
	private InternationalPassportService internationalPassportService;
	
	@Autowired
	private TouristInfoService touristInfoService;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private HotelService hotelService;
	
	@Autowired
	private TravelService travelService;
	
	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/book")
	public BookingResponse doBook(@RequestBody BookingDTO bookingDTO) {
		PaymentInfoDTO paymentInfoDTO = bookingDTO.getPaymentInfo();
		
		InternationalPassportDTO[] internationalPassportsDTO = paymentInfoDTO.getInternationalPassports();		
		PassportDTO[] passportsDTO =  paymentInfoDTO.getPassports();
		
		if (nullOrEmpty(internationalPassportsDTO) && nullOrEmpty(passportsDTO))
			return new BookingResponse(false, "EMPTY_TOURIST_INFO", "Tourists information is missed");
		
		List<TouristInfo> touristInfoList = new ArrayList<>();
		List<Passport> passports;
		
		if (passportsDTO != null) {
			if (Arrays.stream(passportsDTO).allMatch(ValidationUtils::validatePassport)) {
				passports = Arrays.stream(passportsDTO).map(dto -> dto.toEntity()).toList();
				
				passports.forEach(passport -> {
					Passport passportEntity = passportService.getBySeriesAndNumber(passport.getSeries(), passport.getNumber());
					TouristInfo touristInfo = new TouristInfo(null, passport, null);
					
					if (passportEntity != null) {
						passport = passportEntity;
						touristInfo = touristInfoService.getByPassportId(passport.getId());
					}
					
					touristInfoList.add(touristInfo);
				});
			}
			else
				return new BookingResponse(false, "INVALID_PASSPORT", "Invalid passport(s)");
		}
		else
			passports = new ArrayList<>();
		
		List<InternationalPassport> internationalPassports;
		
		if (internationalPassportsDTO != null) {
			if (Arrays.stream(internationalPassportsDTO).allMatch(ValidationUtils::validateInternationPassport)) {
				internationalPassports = Arrays.stream(internationalPassportsDTO).map(dto -> dto.toEntity()).toList();
				
				internationalPassports.forEach(internationalPassport -> {
					InternationalPassport internationalPassportEntity = internationalPassportService.getBySeriesAndNumber(internationalPassport.getSeries(), internationalPassport.getNumber());
					TouristInfo touristInfo = new TouristInfo(null, null, internationalPassport);
					
					if (internationalPassportEntity != null) {
						internationalPassport = internationalPassportEntity;
						touristInfo = touristInfoService.getByInternationalPassportId(internationalPassport.getId());
					}
					
					touristInfoList.add(touristInfo);
				});
			}
			else
				return new BookingResponse(false, "INVALID_INTERNATIONAL_PASSPORT", "Invalid internation passport(s)");
		}
		else
			internationalPassports = new ArrayList<>();

		Booking booking = bookingDTO.toRawEntity();
		
		CardInfo cardInfo = paymentInfoDTO.getCardInfo().toEntity();
		
		Travel travel = travelService.getById(bookingDTO.getTravelId());
		
		if (travel == null)
			return new BookingResponse(false, "INVALID_TRAVEL", "Invalid travel id");
		
		Hotel hotel = hotelService.getById(bookingDTO.getHotelId());
		
		if (hotel == null)
			return new BookingResponse(false, "INVALID_HOTEL", "Invalid hotel id");
		
		booking.setTouristInfo(touristInfoList);
		booking.setHotel(hotel);
		booking.setTravel(travel);
		
		int charge = travel.getCost() + hotel.getNightCost() * touristInfoList.size() * booking.getDays();
		
		for (int i = 0; i < 5; ++i) {
			PaymentResult paymentResult;
			
			if ((paymentResult = paymentService.pay(cardInfo, charge)).isSuccessfull()) {
				bookingService.addBooking(booking);
				
				return new BookingResponse(paymentResult);
			}
			else if (paymentResult != PaymentResult.CONNECTION_ESTABLISH_PROBLEM)
				return new BookingResponse(paymentResult);
		}
		
		return new BookingResponse(PaymentResult.CONNECTION_ESTABLISH_PROBLEM);
	}
}
