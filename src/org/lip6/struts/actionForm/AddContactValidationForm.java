package org.lip6.struts.actionForm;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class AddContactValidationForm extends ActionForm{
	private static final long serialVersionUID = 1L;

	private long id = 0;
	private String firstname = null;
	private String lastname = null;
	private String email = null;
	
	private String street = null;
	private String city = null;
	private String zip = null;
	private String country = null;
	
	private String home = null;
	private String perso = null;
	private String work = null;
	
	private String enterprise;
	private String numSiret;

	private String family;
	private String friends;
	private String coworkers;
	
	
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

	public String getNumSiret() {
		return numSiret;
	}

	public void setNumSiret(String numSiret) {
		this.numSiret = numSiret;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getFriends() {
		return friends;
	}

	public void setFriends(String friends) {
		this.friends = friends;
	}

	public String getCoworkers() {
		return coworkers;
	}

	public void setCoworkers(String coworkers) {
		this.coworkers = coworkers;
	}

	public String getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.id = 0;
		this.firstname = null;
		this.lastname = null;
		this.email = null;
		this.city = null;
		this.street = null;
		this.zip = null;
		this.country = null;
		this.home = null;
		this.perso = null;
		this.work = null;
		this.family = "";
		this.coworkers = "";
		this.friends = "";
		this.enterprise = "";
	}

	

	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request ) {
		ActionErrors errors = new ActionErrors();

		if(getFirstname()== null || getFirstname().length()<1){
			errors.add("firstname",new ActionMessage("error.creation.firstname.required"));
		}
		if(getLastname()== null || getLastname().length()<1) {
			errors.add("lastname",new ActionMessage("error.creation.lastname.required"));
		}
		if(getEmail() == null || getEmail().length()<1){
			errors.add("email", new ActionMessage("error.creation.email.required"));
		}
		if(getStreet() == null || getStreet().length()<1){
			errors.add("street", new ActionMessage("error.creation.street.required"));
		}
		if(getCity() == null || getCity().length()<1){
			errors.add("city", new ActionMessage("error.creation.city.required"));
		}
		if(getZip() == null || getZip().length()<1){
			errors.add("zip", new ActionMessage("error.creation.zip.required"));
		}
		if(getCountry() == null || getCountry().length()<1){
			errors.add("country", new ActionMessage("error.creation.country.required"));
		}
		
		/*
		if(isEnterprise()){
			if(getNumSiret() == null || getNumSiret().length()<1)
				errors.add("numSiret", new ActionMessage("error.creation.numSiret.required"));
		}else{
			if(getNumSiret() != null || getNumSiret().length()>1)
				errors.add("enterprise", new ActionMessage("error.creation.enterprise.required"));
		}*/
		
		return errors;
	}


}
