package ru.itmo.se.bl.lab1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.itmo.se.bl.lab1.entity.Tour;
import ru.itmo.se.bl.lab1.service.TourService;

@RestController
@RequestMapping("/api/tour")
public class TourController {
	@Autowired
	private TourService service;
	
	@GetMapping("/all")
	public List<Tour> getAllTours() {		
		return service.getAll();
	}
	
	@GetMapping("/{tour_id}")
	public Tour getTourById(@PathVariable("tour_id") Integer tourId) {
		return service.getById(tourId);
	}
	
	@GetMapping("/find")
	public List<Tour> getToursByCityName(@RequestParam(name = "tour_city") String tourCityName) {
		return service.getByTourCityName(tourCityName);
	}
}
