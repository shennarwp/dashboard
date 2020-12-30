package com.shennarwp.m900.data.repository;

import com.shennarwp.m900.data.entity.LinkEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * repository class for LinkEntity
 * responsible for saving / querying from the database
 */
@Repository
public class LinkRepository
{
	private EntityManagerFactory factory;
	private EntityManager entityManager;
	private EntityTransaction transaction;

	public LinkRepository() {}

	/** open connection */
	public void initTransaction() {
		factory = Persistence.createEntityManagerFactory("h2");
		entityManager = factory.createEntityManager();
		transaction = entityManager.getTransaction();
	}

	/** close connection */
	public void finishTransaction() {
		entityManager.close();
		factory.close();
	}

	/**
	 * save LinkEntity object to the database
	 * @param toBeSaved the new object
	 */
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

	/**
	 * find LinkEntity object by its id
	 * @param id of the tobe found object
	 */
	public Optional<LinkEntity> findById(Long id) {
		initTransaction();
		transaction.begin();

		LinkEntity link = entityManager.find(LinkEntity.class, id);
		finishTransaction();

		return Optional.of(link);
	}

	/**
	 * find LinkEntity objects by category name
	 * @param categoryName name of the category to be searched
	 * @return List of LinkEntity object having the same category name
	 */
	public List<LinkEntity> findByCategory(String categoryName) {
		return findAll().stream()
						.filter(l -> l.getCategory().equals(categoryName))
						.collect(Collectors.toList());
	}

	/**
	 * @return all LinkEntity object in the database
	 */
	public List<LinkEntity> findAll() {
		initTransaction();
		transaction.begin();

		List<LinkEntity> list = entityManager
				.createQuery("SELECT l FROM LinkEntity l", LinkEntity.class)
				.getResultList();

		transaction.commit();
		finishTransaction();
		return list;
	}

	/**
	 * remove all LinkEntity object from the database
	 */
	public void removeAll() {
		initTransaction();
		transaction.begin();

		entityManager.createQuery("DELETE FROM LinkEntity")
					.executeUpdate();
		transaction.commit();
		finishTransaction();
	}
}
