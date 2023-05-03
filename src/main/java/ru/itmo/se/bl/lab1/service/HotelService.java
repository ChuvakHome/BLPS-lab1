package ru.itmo.se.bl.lab1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import ru.itmo.se.bl.lab1.entity.Hotel;
import ru.itmo.se.bl.lab1.repository.HotelRepository;

@Service
@RequiredArgsConstructor
public class HotelService {
	private final HotelRepository repo;
	
	public List<Hotel> getAll() {
		return repo.findAll();
	}
	
	public Hotel getById(Integer id) {
		return repo.findById(id).orElse(null);
	}
	
	public List<Hotel> getByCountryId(Integer countryId) {
		return repo.findByCountryId(countryId);
	}
	
	public List<Hotel> getByCityId(Integer cityId) {
		return repo.findByCityId(cityId);
	}
}
