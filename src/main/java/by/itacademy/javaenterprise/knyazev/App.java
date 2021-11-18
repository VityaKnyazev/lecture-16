package by.itacademy.javaenterprise.knyazev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.javaenterprise.knyazev.dao.TeachersDAO;
import by.itacademy.javaenterprise.knyazev.entities.Teacher;
import by.itacademy.javaenterprise.knyazev.entities.TeacherDetails;
import by.itacademy.javaenterprise.knyazev.jpa.JpaUtil;
import by.itacademy.javaenterprise.knyazev.servises.InformationService;

public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	private static InformationService is;
	
	public static void main(String[] args) {
		Long id = 2L;
		
		TeachersDAO teachersDAO = new TeachersDAO(JpaUtil.getInstance().getEntityManager());
		is = new InformationService(teachersDAO);
		
		teacherEmail(id);		
		
		saveTeacher();
		
		teachersDAO.closeEntityManager();
		JpaUtil.getInstance().closeEntityManagerFactory();
	}
	
	public static void teacherEmail(Long id) {
		String email = is.getTeacherEmailOnId(id);
		
		if (email == null) logger.info("On current teacher id=" + id + " email not found");
		else logger.info("On current teacher id=" + id + " founded email=" + email);
	}
	
	public static void saveTeacher() {
		Teacher teacher = new Teacher();
		teacher.setName("Ленок");
		teacher.setAge(22);
		teacher.setTeacherDetails(new TeacherDetails());
		
		teacher.getTeacherDetails().setSubject("Биология");
		teacher.getTeacherDetails().setSchoolNumber(18);
		teacher.getTeacherDetails().setEMail("lenok@mail.ru");
		
		is.saveTeacher(teacher);		
	}
}