package ru.itmo.se.bl.lab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ru.itmo.se.bl.lab1.controller.CityController;
import ru.itmo.se.bl.lab1.controller.CountryController;
import ru.itmo.se.bl.lab1.controller.HotelController;
import ru.itmo.se.bl.lab1.controller.TourController;

@SpringBootApplication
public class BLLab1 {
	public static void main(String[] args) {
		SpringApplication.run(new Class[] {
				BLLab1.class,
				CountryController.class,
				CityController.class,
				HotelController.class,
				TourController.class,
			}, args);
	}
}
