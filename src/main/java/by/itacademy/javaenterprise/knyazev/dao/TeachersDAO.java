package by.itacademy.javaenterprise.knyazev.dao;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.javaenterprise.knyazev.dao.exceptions.TeacherExceptionDAO;
import by.itacademy.javaenterprise.knyazev.entities.Teacher;

public class TeachersDAO extends AbstractDAO<Teacher> {
	private static final Logger logger = LoggerFactory.getLogger(TeachersDAO.class);
	
	public TeachersDAO(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public Long save(Teacher teacher) throws TeacherExceptionDAO {
		if (teacher != null) {
			try {
			entityManager.getTransaction().begin();
			entityManager.persist(teacher);
			entityManager.getTransaction().commit();
			return teacher.getId();
			} catch(Exception e) {
				entityManager.getTransaction().rollback();
				logger.error("Transaction on method int save(Teacher teacher) failed: " + e.getMessage() + "with name of class exception: " + e.getClass().getCanonicalName());
				return null;
			}
		} else {
			throw new TeacherExceptionDAO("Expected Teacher object. Null was given in method Long save(Teacher teacher)");
		}
	}

	@Override
	public Teacher find(Long id) throws TeacherExceptionDAO {
		
		if (id == null || id < 0L) {
			throw new TeacherExceptionDAO("Expected Teacher object. Null or bad ID was given in method Teacher find(Long id)");
		}
		
		try {
			return entityManager.find(Teacher.class, id);
		} catch (IllegalArgumentException e) {
			logger.error("Can't get Teacher object on id=" + id + " on method Teacher find(Integer id): " + e.getMessage() + " with exception class name: " + e.getClass().getCanonicalName());
		}
		
		return null;
	}
	
}
