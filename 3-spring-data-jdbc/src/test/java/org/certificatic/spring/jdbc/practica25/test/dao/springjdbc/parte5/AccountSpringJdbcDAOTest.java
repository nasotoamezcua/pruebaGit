package org.certificatic.spring.jdbc.practica25.test.dao.springjdbc.parte5;

import java.math.BigDecimal;
import java.util.List;

import org.certificatic.spring.jdbc.pratica25.dao.api.IAccountDAO;
import org.certificatic.spring.jdbc.pratica25.dao.api.ICustomerDAO;
import org.certificatic.spring.jdbc.pratica25.dao.api.IUserDAO;
import org.certificatic.spring.jdbc.pratica25.domain.entities.Account;
import org.certificatic.spring.jdbc.pratica25.domain.entities.Customer;
import org.certificatic.spring.jdbc.pratica25.domain.entities.User;
import org.certificatic.spring.jdbc.pratica25.domain.vo.CustomDate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
		locations = "classpath:/spring/practica25/spring-jdbc-application-context.xml")
@ActiveProfiles("mysql")
public class AccountSpringJdbcDAOTest {

	@Autowired
	private IUserDAO userDAO;

	@Autowired
	private ICustomerDAO customerDAO;

	@Autowired
	private IAccountDAO accountDAO;

	@Before
	public void setUp() {
		Assert.assertNotNull(userDAO);
		Assert.assertNotNull(customerDAO);
		Assert.assertNotNull(accountDAO);
	}
	
	@Test
	public void createAccountTest() {
		
		/*
		User newUser = User.builder().username("nestor55").password("555555").build();

		Customer newCustomer = Customer.builder().name("Nestor").lastName("Soto").user(newUser).build();

		newUser.setCustomer(newCustomer);

		userDAO.insert(newUser);
		*/
		
		
		Customer newCustomer = customerDAO.findById(2L);
		
		Account accountNew = Account.builder()
				.accountNumber("00112233445599")
				.createdDate(new CustomDate())
				.balance(new BigDecimal("51234567.45"))
				.build();

		accountNew.setCustomer(newCustomer);
		
		accountDAO.insert(accountNew);

		log.info("accountNew : {} {}", accountNew, System.identityHashCode(accountNew));

		log.info("accountNew (detached) : {} {}", accountNew,
				System.identityHashCode(accountNew));

		Account account = accountDAO.findById(accountNew.getId());

		Assert.assertEquals(account, accountNew);

		log.info("account : {} {}", account, System.identityHashCode(account));
		
		Customer customer = customerDAO.findById(account.getCustomer().getId());

		Assert.assertEquals(account.getCustomer().getId(), customer.getId());

		log.info("customer ID : {} {}", customer.getId(),
				System.identityHashCode(customer.getId()));
		
		
		
	}

	@Test
	public void accountSpringJdbcDAOTest() {
		log.info("accountSpringJdbcDAOTest -------------------");

		User user = userDAO.findById(1L);

		Customer customer = customerDAO.findById(1L);

		List<Account> accounts = accountDAO.findByCustomerId(customer.getId());

		Account account_2 = accountDAO.findById(2L);

		Assert.assertNotNull(user);
		Assert.assertNotNull(customer);
		Assert.assertNotNull(accounts);
		Assert.assertNotNull(account_2);

		Assert.assertEquals("xvanhalenx", user.getUsername());
		Assert.assertEquals("Ivan Venor", customer.getName());
		Assert.assertEquals(account_2, accounts.get(1));

		log.info("user: {}", user);
		log.info("user.customer: {}", user.getCustomer());
		log.info("customer: {}", customer);

		log.info("accounts: {}", accounts);
		log.info("account_2: {}", account_2);

	}
}
