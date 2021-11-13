package by.itacademy.javaenterprise.knyazev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.itacademy.javaenterprise.knyazev.controllers.MainController;
import by.itacademy.javaenterprise.knyazev.entities.Teacher;
import by.itacademy.javaenterprise.knyazev.entities.TeacherDetails;

public class App {
	private final static Logger logger = LoggerFactory.getLogger(App.class);
	private static MainController controller = new MainController();
	
	public static void main(String[] args) {
		Integer id = 2;
		teacherEmail(id);		
		
		saveTeacher();
		
		controller.closeResources();		
	}
	
	public static void teacherEmail(Integer id) {
		String email = controller.getServiceInformationAboutTeacherEmailOnId(id);
		logger.info("On current teacher id=" + id + " founded email=" + email);
	}
	
	public static void saveTeacher() {
		Teacher teacher = new Teacher();
		teacher.setName("Ленок");
		teacher.setAge(22);
		teacher.setTeacherDetails(new TeacherDetails());
		
		teacher.getTeacherDetails().setSubject("Биология");
		teacher.getTeacherDetails().setSchoolNumber("18");
		teacher.getTeacherDetails().setEMail("lenok@mail.ru");
		
		controller.saveServiceInformationAboutTeacher(teacher);		
	}
}