package by.itacademy.javaenterprise.knyazev.dao;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.javaenterprise.knyazev.entities.Teacher;

public class TeachersDAO implements DAO<Teacher>{
	private EntityManager entityManager;
	private final Logger logger;
	
	public TeachersDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
		logger = LoggerFactory.getLogger(getClass());
	}

	@Override
	public int save(Teacher teacher) {
		if (teacher != null) {
			try {
			entityManager.getTransaction().begin();
			entityManager.persist(teacher);
			entityManager.getTransaction().commit();
			return 1;
			} catch(Exception e) {
				entityManager.getTransaction().rollback();
				logger.error("Transaction on method int save(Teacher teacher) failed: " + e.getMessage());
			}
		} else {
			logger.error("Expected Teacher object. Null was given in method int save(Teacher teacher)");
		}
		return 0;
	}

	@Override
	public Teacher find(Integer id) {
		Teacher teacher = new Teacher();
		
		if (id == null || id < 0) {
			logger.error("Id should be not null and above zero in method Teacher find(Integer id)");
			return teacher;
		}
		
		try {
			teacher = entityManager.find(Teacher.class, id);
		} catch (IllegalArgumentException e) {
			logger.error("Can't get Teacher object on id=" + id + " on method Teacher find(Integer id): " + e.getMessage() );
		}
		
		return teacher;
	}
	
}
