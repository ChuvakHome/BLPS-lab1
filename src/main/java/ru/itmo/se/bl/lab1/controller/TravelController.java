package ru.itmo.se.bl.lab1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.itmo.se.bl.lab1.entity.Travel;
import ru.itmo.se.bl.lab1.service.TravelService;

@RestController
@RequestMapping("/api/travel")
public class TravelController {
	@Autowired
	private TravelService service;
	
	@GetMapping("/all")
	public List<Travel> getAllTours() {		
		return service.getAll();
	}
	
	@GetMapping("/{travel_id}")
	public Travel getTourById(@PathVariable("travel_id") Integer tourId) {
		return service.getById(tourId);
	}
	
	@GetMapping("/find")
	public List<Travel> getByStartCityAndEndCity(@RequestParam(name = "start_city") String startCityName, @RequestParam(name = "end_city") String endCityName) {
		List<Travel> travels = service.getByCitiesNames(startCityName, endCityName);
		
		System.out.println(travels.get(0).getTravelDate());
		
		return travels;
	}
}
