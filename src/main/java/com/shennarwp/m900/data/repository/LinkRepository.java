package com.shennarwp.m900.data.repository;

import com.shennarwp.m900.data.entity.LinkEntity;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class LinkRepository
{
	@Value( "${spring.datasource.url}" )
	private String url;

	@Value( "${spring.datasource.username}")
	private String username;

	@Value( "${spring.datasource.password}")
	private String password;

	private Connection connection = null;
	private EntityManagerFactory factory;
	EntityManager entityManager;
	EntityTransaction transaction;

	static Logger logger = LogManager.getLogger(LinkRepository.class);

	public LinkRepository() {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Failed to connect to the database. SQLException: " + e);
		}
		logger.log(Level.INFO, "Connection established");
	}

	public void initTransaction() {
		factory = Persistence.createEntityManagerFactory("h2");
		entityManager = factory.createEntityManager();
		transaction = entityManager.getTransaction();
	}

	public void finishTransaction() {
		entityManager.close();
		factory.close();
	}

	public void save(LinkEntity toBeSaved) {
		initTransaction();
		transaction.begin();

		entityManager.createNativeQuery("INSERT INTO LinkEntity (TITLE, URL, CATEGORY, IMAGENAME) VALUES (?,?,?,?)")
				.setParameter(1, toBeSaved.getTitle())
				.setParameter(2, toBeSaved.getUrl())
				.setParameter(3, toBeSaved.getCategory())
				.setParameter(4, toBeSaved.getImageName())
				.executeUpdate();
//		entityManager.persist(toBeSaved);
		transaction.commit();
		finishTransaction();
	}

	public Optional<LinkEntity> findById(Long id) {
		initTransaction();
		transaction.begin();

		LinkEntity link = entityManager.find(LinkEntity.class, id);
		finishTransaction();

		return Optional.of(link);
	}

	public List<LinkEntity> findByCategory(String categoryName) {
		return findAll().stream()
						.filter(l -> l.getCategory().equals(categoryName))
						.collect(Collectors.toList());
	}

	public List<LinkEntity> findAll() {
		initTransaction();
		transaction.begin();

		List<LinkEntity> list = entityManager
				.createQuery("SELECT l FROM LinkEntity l", LinkEntity.class).getResultList();

		transaction.commit();
		finishTransaction();
		return list;
	}

	public void removeAll() {
		initTransaction();
		transaction.begin();

		entityManager.createQuery("DELETE FROM LinkEntity")
					.executeUpdate();
		transaction.commit();
		finishTransaction();
	}
}
