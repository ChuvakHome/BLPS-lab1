package ru.itmo.se.bl.lab1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.Setter;
import ru.itmo.se.bl.lab1.entity.Hotel;
import ru.itmo.se.bl.lab1.service.HotelService;

@RestController
@RequestMapping("/api/hotel")
@Setter
public class HotelController {
	@Autowired
	private HotelService service;
	
	@GetMapping("/all")
	public List<Hotel> getAllHotels() {
		return service.getAll();
	}
	
	@GetMapping("/{hotel_id}")
	public Hotel getById(@PathVariable("hotel_id") Integer id) {
		return service.getById(id);
	}
	
	@GetMapping("/get")
	public List<Hotel> getByCountryId(
			@RequestParam("country_id") Integer countryId, 
			@RequestParam(name = "city_id", required = false) Integer cityId) {
		return cityId != null ? service.getByCityId(cityId) : service.getByCountryId(countryId);
	}
}
