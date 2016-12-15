package domain;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import util.HibernateUtil;

@Transactional
public class DAOAddress extends HibernateDaoSupport{
	public DAOAddress(){
		
	}
	
	public void createAddress(Address a) {
		getHibernateTemplate().persist(a);
		getHibernateTemplate().save(a);
	}
	
	public void createAddress(String street, String city, String zip, String country){
		Address a = new Address(street, city, zip, country);
		
		getHibernateTemplate().persist(a);
		getHibernateTemplate().save(a);
	}
	
	public Address createAdd(String street, String city, String zip, String country){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();

		Address a = new Address(street, city, zip, country);
		
		session.save(a);
		Address createdAddress = (Address) session.load(Address.class,a.getId());

		transaction.commit();
		
		return a;
		
	}
	
	public void deleteAddress(long id) {
		Address add = getHibernateTemplate().get(Address.class, id);
		getHibernateTemplate().delete(add);
	}

	public void updateAddress(long id) {
		Address modifiedAddress = getHibernateTemplate().load(Address.class, id);
		getHibernateTemplate().update(modifiedAddress);
	}
	
	public void searchAddress(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		if(!session.isOpen())
			session = HibernateUtil.getSessionFactory().openSession();
		else
			session = HibernateUtil.getSessionFactory().getCurrentSession();
	    
		
		Criteria crit = session.createCriteria(Address.class).add(Restrictions.eq("id", id));
		crit.setMaxResults(50);
		crit.addOrder(Order.asc("phoneNumberId"));
		List pnlist = crit.list();
		
		
		for(int i = 0; i<pnlist.size();i++){
			System.out.println(((Address) pnlist.get(i)).getId());
		}
		session.close();
	
	}


	public void searchFrenchAddress(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		if(!session.isOpen())
			session = HibernateUtil.getSessionFactory().openSession();
		else
			session = HibernateUtil.getSessionFactory().getCurrentSession();
	    
		Address ad = new Address();
		ad.setCountry("France");
		
		Criteria crit = session.createCriteria(Address.class).add(Example.create(ad));
		List pnlist = crit.list();
		
		for(int i = 0; i<pnlist.size();i++){
			System.out.println(((Address) pnlist.get(i)).getCountry() +" Contact ID : "+((Address) pnlist.get(i)).getId());
		}
		session.close();
	
	}
	
}
