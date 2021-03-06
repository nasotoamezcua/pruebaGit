package org.certificatic.spring.orm.dao.hibernate.impl;

import org.certificatic.spring.orm.dao.api.IUserDAO;
import org.certificatic.spring.orm.dao.hibernate.GenericHibernateDAO;
import org.certificatic.spring.orm.domain.entities.User;

//Habilitar bean Repository 
public class UserHibernateDAO extends GenericHibernateDAO<User, Long>
		implements IUserDAO {

	public UserHibernateDAO() {
		super(User.class);
	}

}
