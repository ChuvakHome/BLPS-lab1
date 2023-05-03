package ru.itmo.se.bl.lab1.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.itmo.se.bl.lab1.entity.Tour;
import ru.itmo.se.bl.lab1.repository.TourRepository;

@Service
@AllArgsConstructor
public class TourService {
	private TourRepository repo;
	
	public List<Tour> getAll() {
		return repo.findAll();
	}
	
	public Tour getById(Integer id) {
		return repo.findById(id).orElse(null);
	}
	
	public List<Tour> getByTourCityName(String tourCityName) {
		return repo.findByTourCityName(tourCityName);
	}
}
