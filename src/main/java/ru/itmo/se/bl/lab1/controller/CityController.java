package ru.itmo.se.bl.lab1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.itmo.se.bl.lab1.entity.City;
import ru.itmo.se.bl.lab1.service.CityService;

@RestController
@RequestMapping("/api/city")
public class CityController {
	@Autowired
	private CityService service;
	
	@GetMapping(value = "/all", produces = "application/json; charset=UTF-8")
	public List<City> getAllCities() {
		return service.getAll();
	}
	
	@GetMapping(value = "/{city_id}", produces = "application/json; charset=UTF-8")
	public City getCityById(@PathVariable("city_id") String cityId) {
		return service.getCityById(Integer.decode(cityId));
	}
	
	@GetMapping(value = "/get", produces = "application/json; charset=UTF-8")
	public List<City> getCitiesByName(@RequestParam("city_name") String name) {
		return service.getCitiesByName(name);
	}
	
	@GetMapping(value = "/get-by-country", produces = "application/json; charset=UTF-8")
	public List<City> getCitiesByCountryName(@RequestParam("country_name") String countryName) {
		return service.getCitiesByCountryName(countryName);
	}
}
