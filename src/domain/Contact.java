package domain;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.ContextLoader;

public class Contact {
	private String firstname;
	private String lastname;
	private String email;
	private long id;
	private Set<ContactGroup> books = new HashSet<ContactGroup>();
	private Set<PhoneNumber> phones = new HashSet<PhoneNumber>();
	private Address add;
	private Integer version;
	
	public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Contact( String firstname, String lastname, String email){
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.books = new HashSet<ContactGroup>();
		this.phones = new HashSet<PhoneNumber>();
	}
	
	public Contact(long id, String firstname, String lastname, String email){
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
	}
	
	public Contact(String firstname, String lastname, String email, Address add){
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.add = add;
		this.books = new HashSet<ContactGroup>();
		this.phones = new HashSet<PhoneNumber>();
	}
	
	public Contact(long id, String firstname, String lastname, String email, Address add){
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.add = add;
		this.books = new HashSet<ContactGroup>();
		this.phones = new HashSet<PhoneNumber>();
	}

	
	
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Set<ContactGroup> getBooks() {
		return books;
	}

	public void setBooks(Set<ContactGroup> books) {
		this.books = books;
	}

	public Set<PhoneNumber> getPhones() {
		return phones;
	}

	public void setPhones(Set<PhoneNumber> phones) {
		this.phones = phones;
	}

	public Address getAdd() {
		return add;
	}

	public void setAdd(Address add) {
		this.add = add;
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
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void addGroupe(ContactGroup cg){
		this.books.add(cg);
		if(!cg.getContacts().contains(this))
			cg.addContactAuGroupe(this);
	}
	
	public void addPhone(PhoneNumber pn){
		if(pn.getContact() == null)
			pn.setContact(this);
		this.phones.add(pn);
	}
	
	public void removeContactFromGroup(){
		ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();

		for(ContactGroup cg : this.getBooks()){
			for(Contact c : cg.getContacts()){
				if(this.getId() == c.getId())
					cg.getContacts().remove(c);
					c.getBooks().remove(cg);
			}
			
			DAOContactGroup dcg = (DAOContactGroup) context.getBean("DAOContactGroup");
			dcg.updateContactGroup(cg);
		}
	}
	
	public void removeContactFromPhone(){
		ApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
		
		for(PhoneNumber pn : this.getPhones()){
			//this.getPhones().remove(pn);
			DAOPhoneNumber dpn = (DAOPhoneNumber) context.getBean("DAOPhoneNumber");
			dpn.deletePhoneNumber(pn.getPhoneNumberId());
		}
		this.getPhones().removeAll(this.getPhones());
	}
	
	public void addGr(ContactGroup c){
		for(ContactGroup cg : this.getBooks()){
			if(!cg.getGroupName().equals(c.getGroupName())){
				this.books.add(c);
			}else
				cg.addContactAuGroupe(this);
		}
	}
}
