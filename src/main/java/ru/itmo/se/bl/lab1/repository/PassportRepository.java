package ru.itmo.se.bl.lab1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.itmo.se.bl.lab1.entity.Passport;

@Repository
public interface PassportRepository extends JpaRepository<Passport, Integer> {
	Optional<Passport> findBySeriesAndNumber(String series, String number);
}
