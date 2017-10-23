package org.certificatic.spring.jdbc.pratica25.dao.springjdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.certificatic.spring.jdbc.pratica25.dao.api.IUserDAO;
import org.certificatic.spring.jdbc.pratica25.dao.springjdbc.GenericSpringJdbcDAO;
import org.certificatic.spring.jdbc.pratica25.dao.springjdbc.rowmapper.UserRowMapper;
import org.certificatic.spring.jdbc.pratica25.domain.entities.Customer;
import org.certificatic.spring.jdbc.pratica25.domain.entities.User;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import lombok.SneakyThrows;

@Repository
@Profile({ "h2-in-memory", "h2-local", "mysql" })
public class UserSpringJdbcDAO extends GenericSpringJdbcDAO<User, Long>
		implements IUserDAO {

	private static final String INSERT_CUSTOMER = "INSERT INTO CUSTOMER_TBL VALUES (null, ?, ?)";
	private static final String INSERT_USER = "INSERT INTO USER_TBL VALUES (null, ?, ?, ?)";

	private static final String UPDATE_CUSTOMER = "UPDATE CUSTOMER_TBL SET NAME = ?, LAST_NAME = ? WHERE CUSTOMER_ID = ?";
	private static final String UPDATE_USER = "UPDATE USER_TBL SET USERNAME = ?, PASSWORD = ? WHERE USER_ID = ?";

	private static final String SELECT_USER_CUSTOMER_WHERE_CUSTOMER_ID = "SELECT * FROM CUSTOMER_TBL INNER JOIN USER_TBL ON CUSTOMER_ID=FK_CUSTOMER_ID WHERE USER_ID = ?";
	private static final String SELECT_ALL_USER_CUSTOMER = "SELECT * FROM CUSTOMER_TBL INNER JOIN USER_TBL ON CUSTOMER_ID=FK_CUSTOMER_ID";

	// MEJORA
	@SuppressWarnings("unused")
	private static final String SELECT_ALL_CUSTOMER = "SELECT CUSTOMER_ID as ID, NAME, LAST_NAME as LASTNAME FROM CUSTOMER_TBL";

	// MEJORA
	@SuppressWarnings("unused")
	private static final String SELECT_USER_WHERE_CUSTOMER_ID = "SELECT USER_ID as ID, USERNAME, PASSWORD FROM USER_TBL WHERE FK_CUSTOMER_ID = ?";

	private static final String DELETE_ACCOUNT_WHERE_CUSTOMER_ID = "DELETE FROM ACCOUNT_TBL WHERE FK_CUSTOMER_ID = ?";
	private static final String DELETE_USER_WHERE_USER_ID = "DELETE FROM USER_TBL WHERE USER_ID = ?";
	private static final String DELETE_CUSTOMER_WHERE_CUSTOMER_ID = "DELETE FROM CUSTOMER_TBL WHERE CUSTOMER_ID = ?";

	@Override
	public void insert(User entity) {

		// OJO REVISAR MODELO, IMPLEMENTAR LOGICA EAGER Y LAZY.

		// Implementar INSERT CUSTOMER obteniendo KeyHolder
		KeyHolder keyHolder = new GeneratedKeyHolder();

		// Implementado mediante PreparedStatementCreator y keyHolder
		this.jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				// Implementar
				PreparedStatement ps = connection.prepareStatement(INSERT_CUSTOMER, new String[] {
						"CUSTOMER_ID", "NAME", "LAST_NAME" });

				ps.setString(1, entity.getCustomer().getName());
				ps.setString(2, entity.getCustomer().getLastName());

				return ps;
			}
		}, keyHolder);

		entity.getCustomer().setId(keyHolder.getKey().longValue());

		// Implementar INSERT USER recuperando fk_customer_id
		keyHolder = new GeneratedKeyHolder();

		// Implementado mediante PreparedStatementCreator y keyHolder
		this.jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				// Implementar
				PreparedStatement ps = connection.prepareStatement(INSERT_USER,
						new String[] { "USER_ID", "FK_CUSTOMER_ID", "USERNAME", "PASSWORD" });
				ps.setLong(1, entity.getCustomer().getId());
				ps.setString(2, entity.getUsername());
				ps.setString(3, entity.getPassword());
				return ps;
			}
		}, keyHolder);

		entity.setId(keyHolder.getKey().longValue());

	}

	@Override
	public void update(User entity) {

		// Implementar UPDATE CUSTOMER

		// Implementado mediante sql y parametros vararg
		// UPDATE CUSTOMER_TBL SET NAME = ?, LAST_NAME = ? WHERE CUSTOMER_ID = ?
		/*this.jdbcTemplate.update(UPDATE_CUSTOMER, entity.getCustomer().getName(),
				entity.getCustomer().getLastName(), entity.getCustomer().getId());*/

		// Implementado mediante sql y setteo de parametros mediante
		// PreparedStatementSetter
		this.jdbcTemplate.update(UPDATE_CUSTOMER,
				new PreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps)
							throws SQLException {
						// Implementar
						ps.setString(1, entity.getCustomer().getName());
						ps.setString(2, entity.getCustomer().getLastName());
						ps.setLong(3, entity.getCustomer().getId());
					}
				});

		// Implementar UPDATE USER

		// Implementado mediante sql y parametros vararg
		this.jdbcTemplate.update(UPDATE_USER, new Object[] { entity.getUsername(),
				entity.getPassword(), entity.getId() });
	}

	@Override
	@SneakyThrows
	public User findById(Long id) {

		// OJO REVISAR MODELO, IMPLEMENTAR LOGICA EAGER Y LAZY.

		User u = null;

		// Implementar SELECT USER BY ID
		// FIND COMPLETE USER (WITH CUSTOMER) BY ID

		try {
			u = this.jdbcTemplate.queryForObject(
					SELECT_USER_CUSTOMER_WHERE_CUSTOMER_ID,
					new RowMapper<User>() {
						@Override
						public User mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							User user = new User();
							user.setId(rs.getLong("USER_ID"));
							user.setUsername(rs.getString("USERNAME"));
							user.setPassword(rs.getString("PASSWORD"));

							Customer customer = new Customer();
							customer.setId(rs.getLong("CUSTOMER_ID"));
							customer.setName(rs.getString("NAME"));
							customer.setLastName(rs.getString("LAST_NAME"));

							user.setCustomer(customer);
							customer.setUser(user);

							return user;
						}
					}, id);

		} catch (EmptyResultDataAccessException ex) {
			// Cuando se usa queryForObject se espera al menos 1 resultado.
			return null;
		}

		return u;
	}

	@Override
	public User delete(Long id) {
		User user = this.findById(id);
		return this.delete(user);
	}

	@Override
	public User delete(User entity) {
		if (entity == null)
			return entity;

		// DELETE COMPLETE RELATIONS OF USER WITH ALL TABLES

		// Pasar parametros a update
		this.jdbcTemplate.update(DELETE_ACCOUNT_WHERE_CUSTOMER_ID,
				new Object[] { entity.getCustomer().getId() });

		// Pasar parametros a update
		this.jdbcTemplate.update(DELETE_USER_WHERE_USER_ID, entity.getId());

		// Pasar parametros a update
		this.jdbcTemplate.update(DELETE_CUSTOMER_WHERE_CUSTOMER_ID,
				new Object[] { entity.getCustomer().getId() });

		return entity;
	}

	@Override
	public List<User> findAll() {

		List<User> userList = null;

		// FIND COMPLETE ALL USER (WITH CUSTOMER)
		// Implementar mediante REsultSetExtractor
		userList = this.jdbcTemplate.query(SELECT_ALL_USER_CUSTOMER,
				new ResultSetExtractor<List<User>>() {

					private UserRowMapper userRowMaper = new UserRowMapper();

					@Override
					public List<User> extractData(ResultSet rs)
							throws SQLException, DataAccessException {

						// Implementar
						List<User> lu = new ArrayList<>();

						int i = 0;
						while (rs.next()) {
							lu.add(userRowMaper.mapRow(rs, ++i));
						}

						return lu;
					}

				});

		return userList;
	}

}
