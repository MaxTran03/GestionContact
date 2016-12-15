package domain;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import util.HibernateUtil;

@Transactional
public class DAOPhoneNumber extends HibernateDaoSupport {
	
	
	public DAOPhoneNumber(){
		
	}
	
	public void createPhoneNumber(PhoneNumber p) {
		//getHibernateTemplate().persist(p);
		getHibernateTemplate().save(p);
		
	}

	public void createPhoneNumber(String phoneKind, String phoneNumber){
		
		PhoneNumber pn = new PhoneNumber(phoneKind, phoneNumber);
		getHibernateTemplate().persist(pn);
		getHibernateTemplate().save(pn);
	}

	public void deletePhoneNumber(long id) {
		PhoneNumber pn = getHibernateTemplate().load(PhoneNumber.class, id);
		getHibernateTemplate().delete(pn);
	}
	
	public void updatePhoneNumber(long idPhone,PhoneNumber pnmod) {
        PhoneNumber pn = getHibernateTemplate().load(PhoneNumber.class, idPhone);
        pn.setContact(pnmod.getContact());
        pn.setPhoneKind(pn.getPhoneKind());
        pn.setPhoneNumber(pn.getPhoneNumber());
        
        getHibernateTemplate().update(pn);
    }


	public void searchPhoneNumber() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		if(!session.isOpen())
			session = HibernateUtil.getSessionFactory().openSession();
		else
			session = HibernateUtil.getSessionFactory().getCurrentSession();
	    
	    
		
		Criteria crit = session.createCriteria(PhoneNumber.class);
		crit.setMaxResults(50);
		List pnlist = crit.list();
		
		for(int i = 0; i<pnlist.size();i++){
			System.out.println(((PhoneNumber) pnlist.get(i)).getPhoneNumber() +" ID : "+((PhoneNumber) pnlist.get(i)).getPhoneNumberId());
		}
		
		session.close();
	}
	
	public void searchPhoneNumber(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		if(!session.isOpen())
			session = HibernateUtil.getSessionFactory().openSession();
		else
			session = HibernateUtil.getSessionFactory().getCurrentSession();
	    
		
		Criteria crit = session.createCriteria(PhoneNumber.class).add(Restrictions.eq("phoneNumberId", id));
		crit.setMaxResults(50);
		crit.addOrder(Order.asc("phoneNumberId"));
		List pnlist = crit.list();
		
		for(int i = 0; i<pnlist.size();i++){
			System.out.println(((PhoneNumber) pnlist.get(i)).getPhoneNumber() +" ID : "+((PhoneNumber) pnlist.get(i)).getPhoneNumberId());
		}
		session.close();
	}
	
	public void searchPhoneNumberByContactID(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		if(!session.isOpen())
			session = HibernateUtil.getSessionFactory().openSession();
		else
			session = HibernateUtil.getSessionFactory().getCurrentSession();
	    
		Contact contact = (Contact) session.load(Contact.class, id);
		
		Criteria crit = session.createCriteria(PhoneNumber.class).add(Restrictions.eq("contact", contact));
		crit.setMaxResults(50);
		crit.addOrder(Order.asc("phoneNumberId"));
		List pnlist = crit.list();
		
		for(int i = 0; i<pnlist.size();i++){
			System.out.println(((PhoneNumber) pnlist.get(i)).getPhoneNumber() +" ID : "+((PhoneNumber) pnlist.get(i)).getPhoneNumberId());
		}
		session.close();
	}
}
