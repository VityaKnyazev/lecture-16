package by.itacademy.javaenterprise.knyazev.dao;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.javaenterprise.knyazev.entities.TeacherDetails;

public class TeacherDetailsDAO implements DAO<TeacherDetails> {
	private EntityManager entityManager;
	private final Logger logger;

	public TeacherDetailsDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
		logger = LoggerFactory.getLogger(getClass());
	}

	@Override
	public int save(TeacherDetails teacherDetails) {
		if (teacherDetails != null) {
			try {
				entityManager.getTransaction().begin();
				entityManager.persist(teacherDetails);
				entityManager.getTransaction().commit();
				return 1;
			} catch (Exception e) {
				entityManager.getTransaction().rollback();
				logger.error("Transaction on method int save(TeacherDetails teacherDetails) failed: " + e.getMessage());
			}
		} else {
			logger.error("Expected TeacherDetails object. Null was given in method int save(TeacherDetails teacherDetails)");
		}
		return 0;
	}

	@Override
	public TeacherDetails find(Integer id) {
		TeacherDetails teacherDetails = new TeacherDetails();

		if (id == null || id < 0) {
			logger.error("Id should be not null and above zero in method TeacherDetails find(Integer id)");
			return teacherDetails;
		}

		try {
			teacherDetails = entityManager.find(TeacherDetails.class, id);
		} catch (IllegalArgumentException e) {
			logger.error(
					"Can't get TeacherDetails object on id=" + id + "on method TeacherDetails find(Integer id): " + e.getMessage());
		}

		return teacherDetails;
	}

}
