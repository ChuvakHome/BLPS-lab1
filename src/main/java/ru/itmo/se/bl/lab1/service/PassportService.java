package ru.itmo.se.bl.lab1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.itmo.se.bl.lab1.entity.Passport;
import ru.itmo.se.bl.lab1.repository.PassportRepository;

@Service
public class PassportService {
	@Autowired
	private PassportRepository repo;
	
	public Passport getById(Integer id) {		
		return repo.findById(id).orElse(null);
	}
	
	public void save(Passport passport) {
		repo.save(passport);
	}
	
	public void saveAll(List<Passport> passports) {
		repo.saveAll(passports);
	}
	
	public Passport getBySeriesAndNumber(String series, String number) {
		return repo.findBySeriesAndNumber(series, number).orElse(null);
	}
}
