package by.itacademy.javaenterprise.knyazev.servises;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.javaenterprise.knyazev.dao.TeachersDAO;
import by.itacademy.javaenterprise.knyazev.entities.Teacher;

public class InformationService {
	private TeachersDAO teachersDAO;
	private final Logger logger;
	
	public InformationService(TeachersDAO teachersDAO) {
		this.teachersDAO = teachersDAO;
		logger = LoggerFactory.getLogger(getClass());
	}
	
	public int saveTeacher(Teacher teacher) {
		return teachersDAO.save(teacher);
	}
	
	public String getTeacherEmailOnId(Integer id) {
		String email = teachersDAO.find(id).getTeacherDetails().getEMail();
		if(email == null) {
			logger.error("Email not found on id=" + id + ". This message will be retune instead email");
			email = "Email not found on id=" + id;
		}
		
		return email;
	}
}
