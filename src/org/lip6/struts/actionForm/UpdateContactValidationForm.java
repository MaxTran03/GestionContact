package org.lip6.struts.actionForm;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class UpdateContactValidationForm extends ActionForm{
	private static final long serialVersionUID = 1L;

	private long id = 0;
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
	
	private String cbox;
	private String numSiret;

	private String family;
	private String friends;
	private String coworkers;
	
	private String ideo;
	private String idphonehome;
	private String idphoneperso;
	private String idphonework;
	private String idgroupfamily;
	private String idgroupfriends;
	private String idgroupcoworkers;
	
	
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

	/*
	public String getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(String enterprise) {
		this.enterprise = enterprise;
	}*/
	
	public String getIdeo() {
		return ideo;
	}

	public void setIdeo(String ideo) {
		this.ideo = ideo;
	}

	public String getIdphonehome() {
		return idphonehome;
	}

	public void setIdphonehome(String idphonehome) {
		this.idphonehome = idphonehome;
	}

	public String getIdphoneperso() {
		return idphoneperso;
	}

	public void setIdphoneperso(String idphoneperso) {
		this.idphoneperso = idphoneperso;
	}

	public String getIdphonework() {
		return idphonework;
	}

	public void setIdphonework(String idphonework) {
		this.idphonework = idphonework;
	}

	public String getIdgroupfamily() {
		return idgroupfamily;
	}

	public void setIdgroupfamily(String idgroupfamily) {
		this.idgroupfamily = idgroupfamily;
	}

	public String getIdgroupfriends() {
		return idgroupfriends;
	}

	public void setIdgroupfriends(String idgroupfriends) {
		this.idgroupfriends = idgroupfriends;
	}

	public String getIdgroupcoworkers() {
		return idgroupcoworkers;
	}

	public void setIdgroupcoworkers(String idgroupcoworkers) {
		this.idgroupcoworkers = idgroupcoworkers;
	}
	
	public String getCbox() {
		return cbox;
	}

	public void setCbox(String cbox) {
		this.cbox = cbox;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.id = 0;
		this.firstname = "";
		this.lastname = "";
		this.email = "";
		this.city = "";
		this.street = "";
		this.zip = "";
		this.country = "";
		this.home = "";
		this.perso = "";
		this.work = "";
		this.family = "";
		this.coworkers = "";
		this.friends = "";
		this.cbox = "";
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
