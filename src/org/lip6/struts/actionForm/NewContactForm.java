package org.lip6.struts.actionForm;

import org.apache.struts.validator.ValidatorForm;

import domain.Address;

public class NewContactForm extends ValidatorForm{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String firstname;
	private String lastname;
	private String email;
	private String street;
	private String city;
	private String zip;
	private String country;
	private String home;
	private String perso;
	private String work;
	private long numSiret;
	private boolean family;
	private boolean friends;
	private boolean coworkers;
	private boolean enterprise;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getHome() {
		return home;
	}

	public void setHome(String home) {
		this.home = home;
	}

	public String getPerso() {
		return perso;
	}

	public void setPerso(String perso) {
		this.perso = perso;
	}

	public String getWork() {
		return work;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public long getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(long numSiret) {
		this.numSiret = numSiret;
	}

	public boolean isFamily() {
		return family;
	}

	public void setFamily(boolean family) {
		this.family = family;
	}

	public boolean isFriends() {
		return friends;
	}

	public void setFriends(boolean friends) {
		this.friends = friends;
	}

	public boolean isCoworkers() {
		return coworkers;
	}

	public void setCoworkers(boolean coworkers) {
		this.coworkers = coworkers;
	}

	public boolean isEnterprise() {
		return enterprise;
	}

	public void setEnterprise(boolean enterprise) {
		this.enterprise = enterprise;
	}
}
