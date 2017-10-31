package org.certificatic.spring.orm.practica27.test.dao.hibernate;

import java.math.BigDecimal;

import org.certificatic.spring.orm.dao.api.IAccountDAO;
import org.certificatic.spring.orm.dao.api.ICustomerDAO;
import org.certificatic.spring.orm.dao.api.IUserDAO;
import org.certificatic.spring.orm.dao.hibernate.api.IHibernateExtraOperationsDAO;
import org.certificatic.spring.orm.domain.entities.Account;
import org.certificatic.spring.orm.domain.entities.Customer;
import org.certificatic.spring.orm.domain.entities.User;
import org.certificatic.spring.orm.domain.vo.CustomDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/spring/practica27/orm-hibernate4-application-context.xml")
@Transactional
@Rollback(false)
@ActiveProfiles("h2-local")
public class AccountDAOTest {
	
	@Autowired
	private ICustomerDAO customerDAO;
	
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
	private IAccountDAO accountDAO;
	
	@Before
	public void setUp() {
		Assert.assertNotNull(customerDAO);
		Assert.assertNotNull(userDAO);
		Assert.assertNotNull(accountDAO);
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void createAccountTest() {
		log.info("createCustomerTest -------------------");
		
		Customer newCustomer = Customer.builder().name("Nestor Alejandro").lastName("Soto AMezcua").build();
		
		User newUser = User.builder().username("nasa").password("050684").customer(newCustomer).build();
		
		newCustomer.setUser(newUser);
		
		customerDAO.insert(newCustomer);
		
		log.info("newCustomer : {} {}", newCustomer,
				System.identityHashCode(newCustomer));
		
		((IHibernateExtraOperationsDAO<Customer, Long>)customerDAO).detach(newCustomer);
		
		log.info("newCustomer (detached) : {} {}", newCustomer,
				System.identityHashCode(newCustomer));
		
		Customer customer = customerDAO.findById(newCustomer.getId());
		
		Assert.assertEquals(customer, newCustomer);
		
		log.info("customer : {} {}", customer,
				System.identityHashCode(customer));
		
		User user = userDAO.findById(customer.getUser().getId());
		
		Assert.assertEquals(user, newUser);
		
		log.info("user : {} {}", user, System.identityHashCode(user));
		
		Account newAccount = Account.builder()
				.accountNumber("0011223344534")
				.createdDate(new CustomDate())
				.balance(new BigDecimal("150000.0000"))
				.customer(newCustomer).build();
		
		accountDAO.insert(newAccount);
		
		log.info("newAccount : {} {}", newAccount,
				System.identityHashCode(newAccount));
		
		((IHibernateExtraOperationsDAO<Account, Long>)accountDAO).detach(newAccount);
		
		log.info("newAccount (detached) : {} {}", newAccount,
				System.identityHashCode(newAccount));
		
		Account account = accountDAO.findById(newAccount.getId());
		
		Assert.assertEquals(account, newAccount);
		
		log.info("account : {} {}", account,
				System.identityHashCode(account));
		
		Account newAccount2 = Account.builder()
				.accountNumber("0011223344535")
				.createdDate(new CustomDate())
				.balance(new BigDecimal("200000.0000"))
				.customer(newCustomer).build();
		
		accountDAO.insert(newAccount2);
		
		log.info("newAccount2 : {} {}", newAccount2,
				System.identityHashCode(newAccount2));
		
		((IHibernateExtraOperationsDAO<Account, Long>)accountDAO).detach(newAccount2);
		
		log.info("newAccount2 (detached) : {} {}", newAccount2,
				System.identityHashCode(newAccount2));
		
		Account account2 = accountDAO.findById(newAccount2.getId());
		
		Assert.assertEquals(account2, newAccount2);
		
		log.info("account2 : {} {}", account2,
				System.identityHashCode(account2));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void updateAccountTest() {
		
		log.info("updateAccountTest -------------------");
		
		User newUser = User.builder().username("rkdo").password("050684").build();
		
		Customer newCustomer = Customer.builder().name("Ricardo").lastName("Soto AMezcua").user(newUser).build();	
		
		newUser.setCustomer(newCustomer);
		
		userDAO.insert(newUser);
		
		log.info("newUser : {} {}", newUser,
				System.identityHashCode(newUser));
		
		((IHibernateExtraOperationsDAO<User, Long>)userDAO).detach(newUser);
		
		log.info("newUser (detached) : {} {}", newUser,
				System.identityHashCode(newUser));
		
		User user = userDAO.findById(newUser.getId());
		
		Assert.assertEquals(user, newUser);
		
		log.info("user : {} {}", user,
				System.identityHashCode(user));
		
		Customer customer = customerDAO.findById(user.getCustomer().getId());
		
		Assert.assertEquals(customer, newCustomer);
		
		log.info("customer : {} {}", customer,
				System.identityHashCode(customer));
		
		Account newAccount = Account.builder()
				.accountNumber("0011223344536")
				.createdDate(new CustomDate())
				.balance(new BigDecimal("150000.0000"))
				.customer(newCustomer).build();
		
		accountDAO.insert(newAccount);
		
		log.info("newAccount : {} {}", newAccount,
				System.identityHashCode(newAccount));
		
		((IHibernateExtraOperationsDAO<Account, Long>)accountDAO).detach(newAccount);
		
		log.info("newAccount (detached) : {} {}", newAccount,
				System.identityHashCode(newAccount));
		
		Account account = accountDAO.findById(newAccount.getId());
		
		Assert.assertEquals(account, newAccount);
		
		log.info("account : {} {}", account,
				System.identityHashCode(account));
		
		Account newAccount2 = Account.builder()
				.accountNumber("0011223344537")
				.createdDate(new CustomDate())
				.balance(new BigDecimal("200000.0000"))
				.customer(newCustomer).build();
		
		accountDAO.insert(newAccount2);
		
		log.info("newAccount2 : {} {}", newAccount2,
				System.identityHashCode(newAccount2));
		
		((IHibernateExtraOperationsDAO<Account, Long>)accountDAO).detach(newAccount2);
		
		log.info("newAccount2 (detached) : {} {}", newAccount2,
				System.identityHashCode(newAccount2));
		
		Account account2 = accountDAO.findById(newAccount2.getId());
		
		Assert.assertEquals(account2, newAccount2);
		
		log.info("account2 : {} {}", account2,
				System.identityHashCode(account2));
		
		account.setBalance(new BigDecimal("170000.0000"));
		
		accountDAO.update(account);
		
		log.info("account (modified) : {} {}", account,
				System.identityHashCode(account));
		
		Account modifiedAccount = accountDAO.findById(account.getId());
		
		Assert.assertEquals(modifiedAccount, account);
		
		log.info("modifiedAccount : {} {}", modifiedAccount,
				System.identityHashCode(modifiedAccount));
		
		account2.setBalance(new BigDecimal("250000.0000"));
		
		accountDAO.update(account2);
		
		log.info("account2 (modified) : {} {}", account2,
				System.identityHashCode(account2));
		
		Account modifiedAccount2 = accountDAO.findById(account2.getId());
		
		Assert.assertEquals(modifiedAccount2, account2);
		
		log.info("modifiedAccount2 : {} {}", modifiedAccount2,
				System.identityHashCode(modifiedAccount2));
	
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void deleteAccountTest() {
		
		log.info("deleteAccountTest -------------------");
		
		Customer newCustomer = Customer.builder().name("Fernando").lastName("Soto AMezcua").build();
		
		User newUser = User.builder().username("ferny").password("050684").customer(newCustomer).build();
		
		newCustomer.setUser(newUser);
		
		customerDAO.insert(newCustomer);
		
		log.info("newCustomer : {} {}", newCustomer,
				System.identityHashCode(newCustomer));
		
		((IHibernateExtraOperationsDAO<Customer, Long>)customerDAO).detach(newCustomer);
		
		log.info("newCustomer (detached) : {} {}", newCustomer,
				System.identityHashCode(newCustomer));
		
		Customer customer = customerDAO.findById(newCustomer.getId());
		
		Assert.assertEquals(customer, newCustomer);
		
		log.info("customer : {} {}", customer,
				System.identityHashCode(customer));
		
		User user = userDAO.findById(customer.getUser().getId());
		
		Assert.assertEquals(user, newUser);
		
		log.info("user : {} {}", user, System.identityHashCode(user));
		
		Account newAccount = Account.builder()
				.accountNumber("0011223344539")
				.createdDate(new CustomDate())
				.balance(new BigDecimal("150000.0000"))
				.customer(newCustomer).build();
		
		accountDAO.insert(newAccount);
		
		log.info("newAccount : {} {}", newAccount,
				System.identityHashCode(newAccount));
		
		((IHibernateExtraOperationsDAO<Account, Long>)accountDAO).detach(newAccount);
		
		log.info("newAccount (detached) : {} {}", newAccount,
				System.identityHashCode(newAccount));
		
		Account account = accountDAO.findById(newAccount.getId());
		
		Assert.assertEquals(account, newAccount);
		
		log.info("account : {} {}", account,
				System.identityHashCode(account));
		
		Account newAccount2 = Account.builder()
				.accountNumber("0011223344540")
				.createdDate(new CustomDate())
				.balance(new BigDecimal("200000.0000"))
				.customer(newCustomer).build();
		
		accountDAO.insert(newAccount2);
		
		log.info("newAccount2 : {} {}", newAccount2,
				System.identityHashCode(newAccount2));
		
		((IHibernateExtraOperationsDAO<Account, Long>)accountDAO).detach(newAccount2);
		
		log.info("newAccount2 (detached) : {} {}", newAccount2,
				System.identityHashCode(newAccount2));
		
		Account account2 = accountDAO.findById(newAccount2.getId());
		
		Assert.assertEquals(account2, newAccount2);
		
		log.info("account2 : {} {}", account2,
				System.identityHashCode(account2));
		
		accountDAO.delete(account);
		
		log.info("account (deleted) : {} {}", account,
				System.identityHashCode(account));
		
		Account deleteAccount = accountDAO.findById(account.getId());
		
		Assert.assertNull(deleteAccount);
		
		log.info("deleteAccount : {} {}", deleteAccount,
				System.identityHashCode(deleteAccount));
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void deleteCustomerUserAccountTest() {
		
		log.info("deleteCustomerUserAccountTest -------------------");
		
		Customer newCustomer = Customer.builder().name("Ofelia").lastName("Soto AMezcua").build();
		
		User newUser = User.builder().username("ofla").password("050684").customer(newCustomer).build();
		
		newCustomer.setUser(newUser);
		
		customerDAO.insert(newCustomer);
		
		log.info("newCustomer : {} {}", newCustomer,
				System.identityHashCode(newCustomer));
		
		((IHibernateExtraOperationsDAO<Customer, Long>)customerDAO).detach(newCustomer);
		
		log.info("newCustomer (detached) : {} {}", newCustomer,
				System.identityHashCode(newCustomer));
		
		Customer customer = customerDAO.findById(newCustomer.getId());
		
		Assert.assertEquals(customer, newCustomer);
		
		log.info("customer : {} {}", customer,
				System.identityHashCode(customer));
		
		User user = userDAO.findById(customer.getUser().getId());
		
		Assert.assertEquals(user, newUser);
		
		log.info("user : {} {}", user, System.identityHashCode(user));
		
		Account newAccount = Account.builder()
				.accountNumber("0011223344539")
				.createdDate(new CustomDate())
				.balance(new BigDecimal("150000.0000"))
				.customer(newCustomer).build();
		
		accountDAO.insert(newAccount);
		
		log.info("newAccount : {} {}", newAccount,
				System.identityHashCode(newAccount));
		
		((IHibernateExtraOperationsDAO<Account, Long>)accountDAO).detach(newAccount);
		
		log.info("newAccount (detached) : {} {}", newAccount,
				System.identityHashCode(newAccount));
		
		Account account = accountDAO.findById(newAccount.getId());
		
		Assert.assertEquals(account, newAccount);
		
		log.info("account : {} {}", account,
				System.identityHashCode(account));
		
		Account newAccount2 = Account.builder()
				.accountNumber("0011223344540")
				.createdDate(new CustomDate())
				.balance(new BigDecimal("200000.0000"))
				.customer(newCustomer).build();
		
		accountDAO.insert(newAccount2);
		
		log.info("newAccount2 : {} {}", newAccount2,
				System.identityHashCode(newAccount2));
		
		((IHibernateExtraOperationsDAO<Account, Long>)accountDAO).detach(newAccount2);
		
		log.info("newAccount2 (detached) : {} {}", newAccount2,
				System.identityHashCode(newAccount2));
		
		Account account2 = accountDAO.findById(newAccount2.getId());
		
		Assert.assertEquals(account2, newAccount2);
		
		log.info("account2 : {} {}", account2,
				System.identityHashCode(account2));
		
		customerDAO.delete(customer);
		
		log.info("customer (deleted) : {} {}", customer,
				System.identityHashCode(customer));
		
		Customer deletedCustomer = customerDAO.findById(customer.getId());
		
		Assert.assertNull(deletedCustomer);
		
		log.info("deletedCustomer : {} {}", deletedCustomer,
				System.identityHashCode(deletedCustomer));
		
		User deleteUser = userDAO.findById(customer.getUser().getId());
		
		Assert.assertNull(deleteUser);
		
		log.info("deleteUser : {} {}", deleteUser,
				System.identityHashCode(deleteUser));
		
		Account deleteAcount = accountDAO.findById(account.getId());
		
		Assert.assertNull(deleteAcount);
		
		log.info("deleteAcount : {} {}", deleteAcount,
				System.identityHashCode(deleteAcount));
		
		Account deleteAcount2 = accountDAO.findById(account2.getId());
		
		Assert.assertNull(deleteAcount2);
		
		log.info("deleteAcount2 : {} {}", deleteAcount2,
				System.identityHashCode(deleteAcount2));
	}

}
