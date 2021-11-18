package by.itacademy.javaenterprise.knyazev.dao;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.javaenterprise.knyazev.dao.exceptions.TeacherDetailsExceptionDAO;
import by.itacademy.javaenterprise.knyazev.entities.TeacherDetails;

public class TeacherDetailsDAO extends AbstractDAO<TeacherDetails> {
	private static final Logger logger = LoggerFactory.getLogger(TeacherDetailsDAO.class);

	public TeacherDetailsDAO(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public Long save(TeacherDetails teacherDetails) throws TeacherDetailsExceptionDAO {
		if (teacherDetails != null) {
			try {
				entityManager.getTransaction().begin();
				entityManager.persist(teacherDetails);
				entityManager.getTransaction().commit();
				return teacherDetails.getId();
			} catch (RuntimeException e) {
				entityManager.getTransaction().rollback();
				logger.error("Transaction on method int save(TeacherDetails teacherDetails) failed: " + e.getMessage() + "With name of class exception: " + e.getClass().getCanonicalName());
				return null;
			}
		} else {
			throw new TeacherDetailsExceptionDAO("Expected TeacherDetails object. Null was given in method Long save(TeacherDetails teacherDetails)");
		}		
	}

	@Override
	public TeacherDetails find(Long id) throws TeacherDetailsExceptionDAO {

		if (id == null || id < 0L) {
			throw new TeacherDetailsExceptionDAO("Id should be not null and above zero in method TeacherDetails find(Long id)");
		}

		try {
			return entityManager.find(TeacherDetails.class, id);
		} catch (IllegalArgumentException e) {
			logger.error("Can't get TeacherDetails object on id=" + id + " on method TeacherDetails find(Integer id): " + e.getMessage() + "with exception class name: " + e.getClass().getCanonicalName());
		}

		return null;
	}

}
