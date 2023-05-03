package ru.itmo.se.bl.lab1.entity;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "bookings")
@Data
@Getter
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "booking_date", nullable = false)
	private Date bookingDate;
	
	@Column(nullable = false)
	private int days;
	
	@Column(nullable = false)
	private int tourists;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(
			joinColumns = @JoinColumn(name = "booking_id", referencedColumnName = "id", nullable = false),
			inverseJoinColumns = @JoinColumn(name = "tourist_info_id", referencedColumnName = "id", nullable = false))
	private List<TouristInfo> touristInfo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hotel_id", nullable = false)
	private Hotel hotel;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "travel_id", nullable = false)
	private Travel travel;
}
