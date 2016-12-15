package domain;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import util.HibernateUtil;

@Transactional
public class DAOContactGroup extends HibernateDaoSupport{
	public DAOContactGroup(){
		
	}
	
	public void createContactGroup(ContactGroup cg) {
		getHibernateTemplate().persist(cg);
		getHibernateTemplate().saveOrUpdate(cg);
	}
	
	public void createContactGroup(String groupName){
		ContactGroup cg = new ContactGroup(groupName);
		getHibernateTemplate().persist(cg);
		getHibernateTemplate().save(cg);
	}

	public void deleteContactGroupe(long id) {
		ContactGroup cg = getHibernateTemplate().load(ContactGroup.class, id);
		getHibernateTemplate().delete(cg);
	}
	
	public void deleteContactGroupe(ContactGroup cg) {
		getHibernateTemplate().delete(cg);
	}

	public ContactGroup updateContactGroup(ContactGroup cg) {
		getHibernateTemplate().update(cg);
		
		return cg;
	}

	public ContactGroup loadContactGroup(long id){
		ContactGroup cg = getHibernateTemplate().load(ContactGroup.class, id);
		return cg;
	}
	
	public void searchContactGroupe(long id) {
		System.out.println("Le contact a été trouvé !");
	}
}
