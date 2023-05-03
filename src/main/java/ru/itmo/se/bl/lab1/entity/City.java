package ru.itmo.se.bl.lab1.entity;

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
@Table(name = "cities")
@Setter
@Data
public class City {
	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cities_sequence_generator")
//	@SequenceGenerator(name = "cities_sequence_generator", sequenceName = "counties_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "city_name", nullable = false)
	private String name;
	
	@Column(name = "city_local_name", nullable = false)
	private String localName;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "city_country_id", nullable = false)
	private Country country;
}
