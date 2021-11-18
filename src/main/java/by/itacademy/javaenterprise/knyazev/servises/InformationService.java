package by.itacademy.javaenterprise.knyazev.servises;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.javaenterprise.knyazev.dao.TeachersDAO;
import by.itacademy.javaenterprise.knyazev.dao.exceptions.TeacherExceptionDAO;
import by.itacademy.javaenterprise.knyazev.entities.Teacher;

public class InformationService {
	private TeachersDAO teachersDAO;
	private static final Logger logger = LoggerFactory.getLogger(InformationService.class);
	
	public InformationService(TeachersDAO teachersDAO) {
		this.teachersDAO = teachersDAO;
	}
	
	public Long saveTeacher(Teacher teacher) {
		try {
			return teachersDAO.save(teacher);
		} catch (TeacherExceptionDAO e) {
			logger.error("Error in Long saveTeacher(Teacher teacher) method: " + e.getMessage());
		}
		
		return null;
	}
	
	public String getTeacherEmailOnId(Long id) {
		try {
			return teachersDAO.find(id).getTeacherDetails().getEMail();
		} catch (TeacherExceptionDAO e) {
			logger.error("Error in String getTeacherEmailOnId(Long id) method: " + e.getMessage());
			return null;
		}
	}
}
