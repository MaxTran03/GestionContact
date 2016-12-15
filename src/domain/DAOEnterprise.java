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
public class DAOEnterprise extends HibernateDaoSupport{
	public DAOEnterprise() {

	}

	public void createEnterprise(Enterprise e) {
		getHibernateTemplate().save(e);
	}

	public void createEnterprise(String firstname, String lastname, String email, Address add, long num){
		Enterprise e = new Enterprise(firstname, lastname, email, add, num);
		
		getHibernateTemplate().persist(e);
		getHibernateTemplate().save(e);
	}

	public void deleteEnterprise(long id) {
		Enterprise e = getHibernateTemplate().load(Enterprise.class, id);
		getHibernateTemplate().delete(e);
	}

	public void updateEnterprise(long id) {
		
		Enterprise modifiedEnterprise = getHibernateTemplate().load(Enterprise.class, id);
		
		getHibernateTemplate().update(modifiedEnterprise);
	}
	
	public void updateEnterprise(Enterprise e){
		getHibernateTemplate().update(e);
	}
	
	public Enterprise searchEnterpriseByID(long id) {
		String qEnterprise = "from Enterprise as t where t.id=:id";
		
		//Enterprise enterprise = (Enterprise) session.createQuery(qEnterprise).setParameter("id", id).uniqueResult();
		Enterprise enterprise = (Enterprise) getHibernateTemplate().find("id", id);
		
		return enterprise;
	}

	public void searchEnterprise(long id) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		if(!session.isOpen())
			session = HibernateUtil.getSessionFactory().openSession();
		else
			session = HibernateUtil.getSessionFactory().getCurrentSession();
	    
		
		Criteria crit = session.createCriteria(ContactGroup.class).add(Restrictions.eq("groupId", id));
		crit.setMaxResults(50);
		crit.addOrder(Order.asc("groupId"));
		List pnlist = crit.list();
		
		for(int i = 0; i<pnlist.size();i++){
			System.out.println(((ContactGroup) pnlist.get(i)).getGroupName() +" ID : "+((ContactGroup) pnlist.get(i)).getGroupId());
		}
		session.close();
	}
}
