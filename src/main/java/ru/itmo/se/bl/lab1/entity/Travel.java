package ru.itmo.se.bl.lab1.entity;

import java.sql.Date;

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
import lombok.Getter;

@Entity
@Table(name = "travels")
@Data
@Getter
public class Travel {
	@Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "travels_sequence_generator")
//	@SequenceGenerator(name = "travels_sequence_generator", sequenceName = "travels_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "travel_date")
	private Date travelDate;
	
	@Column(nullable = false)
	private int cost;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "start_city_id", nullable = false)
	private City startCity;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "end_city_id", nullable = false)
	private City endCity;
	
	@Column(name = "travel_duration", nullable = false)
	private long travelDuration; // travel duration in minutes
}
