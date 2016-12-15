package domain;

public class PhoneNumber {
	private long phoneNumberId;
	private String phoneKind;
	private String phoneNumber;
	private Contact contact;
	
	public PhoneNumber() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public PhoneNumber(String phoneKind, String phoneNumber) {
		super();
		this.phoneKind = phoneKind;
		this.phoneNumber = phoneNumber;
	}
	
	public PhoneNumber(String phoneKind, String phoneNumber, Contact contact) {
		super();
		this.phoneKind = phoneKind;
		this.phoneNumber = phoneNumber;
		this.contact = contact;
	}
	
	public PhoneNumber(long id, String phoneKind, String phoneNumber,
			Contact contact) {
		super();
		this.phoneNumberId = id;
		this.phoneKind = phoneKind;
		this.phoneNumber = phoneNumber;
		this.contact = contact;
	}

	

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
	
	public long getPhoneNumberId() {
		return phoneNumberId;
	}

	public void setPhoneNumberId(long phoneNumberId) {
		this.phoneNumberId = phoneNumberId;
	}

	public String getPhoneKind() {
		return phoneKind;
	}
	public void setPhoneKind(String phoneKind) {
		this.phoneKind = phoneKind;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
