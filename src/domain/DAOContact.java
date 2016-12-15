package domain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.management.Query;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import util.HibernateUtil;

@Transactional
public class DAOContact extends HibernateDaoSupport{
	//Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	private final static String RESOURCE_JDBC = "java:comp/env/jdbc/BDAINAS";

	public DAOContact() {
	}


	public void createContact(Contact c) {

		getHibernateTemplate().save(c);
	}

	public String createContactStruts(Contact c){
		try{
			getHibernateTemplate().save(c);

			return null;
		}catch(Exception e){
			return "Error : " + e.getMessage();
		}
	}

	public String addContact(final long id, final String firstName, final String lastName, final String email) {
		try {
			final Context lContext = new InitialContext();
			final DataSource lDataSource = (DataSource) lContext.lookup(RESOURCE_JDBC);
			final Connection lConnection  = lDataSource.getConnection();

			// adding a new contact
			final PreparedStatement lPreparedStatementCreation = 

					lConnection.prepareStatement("INSERT INTO CONTACT(ID_CONTACT, FIRSTNAME, LASTNAME, EMAIL) VALUES(?, ?, ?, ?)");

			lPreparedStatementCreation.setLong(1, id);
			lPreparedStatementCreation.setString(2, firstName);
			lPreparedStatementCreation.setString(3, lastName);
			lPreparedStatementCreation.setString(4, email);
			lPreparedStatementCreation.executeUpdate();

			return null;
		} catch (NamingException e) {
			return "NamingException : " + e.getMessage();
		} catch (SQLException e) {
			return "SQLException : " + e.getMessage();
		}
	}


	public Contact createContact(String firstname, String lastname, String email, Address add){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		if(!session.isOpen())
			session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Contact c = new Contact(firstname, lastname, email, add);

		session.save(c);
		Contact createdContact = (Contact) session.load(Contact.class,c.getId());

		transaction.commit();

		if(session.isOpen())
			session.close();

		return c;
	}

	public void deleteContact(long id) {
		Contact createdContact = getHibernateTemplate().load(Contact.class, id);
		getHibernateTemplate().initialize(createdContact.getBooks());
		getHibernateTemplate().initialize(createdContact.getPhones());

		createdContact.removeContactFromGroup();
		createdContact.removeContactFromPhone();

		getHibernateTemplate().delete(createdContact);
	}
	/*
	public void updateContact(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		if(!session.isOpen())
			session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Contact modifiedContact = (Contact) session.load(Contact.class, id);

		modifiedContact.setFirstname("lol");
		//session.merge(modifiedContact);
		//session.update(modifiedContact);

		session.update(modifiedContact);

		session.flush();
		transaction.commit();

		if(session.isOpen())
			session.close();
	}*/


	public void updateContact(long id, Contact c) {
		getHibernateTemplate().update(c);
	}

	public void updateContact(Contact c){
		getHibernateTemplate().update(c);
	}
	
	public Contact loadContact(long id){
		Contact c = getHibernateTemplate().load(Contact.class, id);
		return c;
	}

	public Enterprise loadEnterprise(long id){
		Enterprise e = getHibernateTemplate().load(Enterprise.class, id);
		return e;
	}

	public List<Contact> searchContact(String firstname) {
		String q = "from Contact as t where t.firstname like ?";

		List<Contact> contacts = (List<Contact>) getHibernateTemplate().find(q, "%"+firstname+"%");

		return contacts;
	}

	public Contact searchContactByID(long id) {
		String qContact = "from Contact as t where t.id = ?";

		Contact contact = (Contact) getHibernateTemplate().get(Contact.class, id);

		return contact;
	}

	public Enterprise searchEnterpriseByID(long id) {
		String qEnterprise = "from Enterprise as t where t.id = ?";

		Enterprise enterprise = (Enterprise) getHibernateTemplate().get(Enterprise.class, id);

		return enterprise;

	}

	//models
	public List searchContactByModels(Contact c) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = null;
		if (!session.isOpen())
			session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
		} catch (Exception e) {
			session.getTransaction().rollback();
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			if (!session.isOpen())
				session = HibernateUtil.getSessionFactory().openSession();
			transaction = session.beginTransaction();
		}

		Criteria crit = session.createCriteria(Contact.class).add(Example.create(c));
		List pnlist = crit.list();

		for(int i = 0; i<pnlist.size();i++){
			System.out.println(((Contact) pnlist.get(i)).getFirstname() +" Contact ID : "+((Contact) pnlist.get(i)).getId());
		}
		session.close();

		return pnlist;
		
		
	}
}
