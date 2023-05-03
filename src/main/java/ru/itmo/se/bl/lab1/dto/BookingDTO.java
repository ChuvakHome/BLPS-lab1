package ru.itmo.se.bl.lab1.dto;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.itmo.se.bl.lab1.entity.Booking;
import ru.itmo.se.bl.lab1.utils.DateFormatters;

@AllArgsConstructor
@Getter
public class BookingDTO {
	@NotBlank(message = "Booking date is mandatory")
	private String bookingDate;
	
	@Positive(message = "Days count should be positive number")
	private int days;
	
	@Positive(message = "Tourists count should be positive number")
	private int tourists;
	
	@Positive(message = "Hotel id should be positive number")
	private int hotelId;
	
	@Positive(message = "Travel id should be positive number")
	private int travelId;
	
	@NotNull(message = "Payment info is mandatory")
	private PaymentInfoDTO paymentInfo;
	
	public Booking toRawEntity() {
		Date bookingDate = Date.valueOf(LocalDate.parse(this.bookingDate, DateFormatters.STANDARD_DATE_FORMAT));
		
		Booking booking = new Booking();
		booking.setId(null);
		booking.setTourists(tourists);
		booking.setDays(days);
		booking.setBookingDate(bookingDate);
		
		return booking;
	}
}
