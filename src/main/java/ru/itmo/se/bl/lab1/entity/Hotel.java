package ru.itmo.se.bl.lab1.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Setter;

@Entity
@Table(name = "hotels")
@Setter
@Data
public class Hotel {
	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hotels_sequence_generator")
//	@SequenceGenerator(name = "hotels_sequence_generator", sequenceName = "hotels_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "hotel_name", nullable = false)
	private String hotelName;
	
	@Column(name = "cost_per_night", nullable = false)
	private int nightCost;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "country_id", nullable = false)
	@JsonIgnore
	private Country country;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "city_id", nullable = false)
	private City city;
}
