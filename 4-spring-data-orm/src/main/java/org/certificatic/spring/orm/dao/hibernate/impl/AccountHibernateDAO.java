package org.certificatic.spring.orm.dao.hibernate.impl;

import java.util.List;

import org.certificatic.spring.orm.dao.api.IAccountDAO;
import org.certificatic.spring.orm.dao.hibernate.GenericHibernateDAO;
import org.certificatic.spring.orm.domain.entities.Account;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

// Habilitar bean Repository 
@Repository
public class AccountHibernateDAO extends GenericHibernateDAO<Account, Long>
		implements IAccountDAO {

	public AccountHibernateDAO() {
		super(Account.class);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Account> findByCustomerId(Long id) {
		
		String HQL = "FROM " + this.persistentClass.getName() + " WHERE FK_CUSTOMER_ID = :idCustomer";
		Query q = this.sessionFactory.getCurrentSession().createQuery(HQL);
		q.setParameter("idCustomer", id);
		List<Account> listAccount = q.list();

		return listAccount;
	}

}
