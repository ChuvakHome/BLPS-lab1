package ru.itmo.se.bl.lab1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.itmo.se.bl.lab1.entity.Travel;
import ru.itmo.se.bl.lab1.repository.TravelRepository;

@Service
@AllArgsConstructor
public class TravelService {
	private TravelRepository repo;
	
	public Travel getById(Integer id) {
		return repo.findById(id).orElse(null);
	}
	
	public List<Travel> getAll() {
		return repo.findAll();
	}
	
	public List<Travel> getByCitiesNames(String startCityName, String endCityName) {
		return repo.findByStartCityNameAndEndCityName(startCityName, endCityName);
	}
}
