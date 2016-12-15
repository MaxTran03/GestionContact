package domain;

import java.util.HashSet;
import java.util.Set;

public class ContactGroup {
	private long groupId;
	private String groupName;
	private Set<Contact> contacts = new HashSet<Contact>();
	
	public ContactGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public ContactGroup(String groupName) {
		super();
		this.groupName = groupName;
		this.contacts = new HashSet<Contact>();
	}


	public Set<Contact> getContacts() {
		/*Set<Contact> a = this.contacts;
		if(!a.isEmpty())
			return a;
		else{
			System.out.println("Pas de contact groupe !");
			return null;
		}*/
		return this.contacts;
	}

	public void setContacts(Set<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public long getGroupId() {
		return groupId;
	}
	public void setGroupId(long groupId) {
		this.groupId = groupId;
	}
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public void addContactAuGroupe(Contact c){
		this.contacts.add(c);
		/*if(!c.getBooks().contains(this))
			c.addGroupe(this);*/
	}
	
	public void removeContact(Contact c){
		this.contacts.remove(c);
		/*if(c.getBooks().contains(this))
			c.removeContactFromGroup();*/
	}
}
