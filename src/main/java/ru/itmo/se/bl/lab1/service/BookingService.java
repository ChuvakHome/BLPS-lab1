package ru.itmo.se.bl.lab1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.itmo.se.bl.lab1.entity.Booking;
import ru.itmo.se.bl.lab1.repository.BookingRepository;

@Service
public class BookingService {
	private final BookingRepository repo;
	
	@Autowired
	public BookingService(BookingRepository repository) {
		repo = repository;
	}
	
	public void addBooking(Booking booking) {
		repo.save(booking);
	}
}
