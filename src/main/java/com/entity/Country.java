package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COUNTRIES")
public class Country {

	// aggiungere il censimento della sequence bisogna farlo con un sequence generator
	
//	@Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_gen")
//    @SequenceGenerator(
//        name = "country_gen", 
//        sequenceName = "COUNTRIES_SEQ", // Il nome della sequenza sul database
//        allocationSize = 1             // Incremento (deve coincidere con quello del DB)
//    )
	@Id
    @Column(name = "COUNTRY_ID")
    private String countryId;

    @Column(name = "COUNTRY_NAME")
    private String countryName;

    @ManyToOne
    @JoinColumn(name = "REGION_ID")
    private Region region;

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}
}