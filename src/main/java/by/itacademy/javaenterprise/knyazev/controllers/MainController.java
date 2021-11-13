package by.itacademy.javaenterprise.knyazev.controllers;

import javax.persistence.EntityManager;

import by.itacademy.javaenterprise.knyazev.dao.TeachersDAO;
import by.itacademy.javaenterprise.knyazev.entities.Teacher;
import by.itacademy.javaenterprise.knyazev.jpa.JpaUtil;
import by.itacademy.javaenterprise.knyazev.servises.InformationService;

public class MainController {
	private InformationService informationService;
	private EntityManager entityManager;
	
	public MainController() {
		entityManager = JpaUtil.getInstance().getEntityManager();		
		informationService = new InformationService(new TeachersDAO(entityManager));
	}
	
	public String getServiceInformationAboutTeacherEmailOnId(Integer id) {
		return informationService.getTeacherEmailOnId(id);
	}
	
	public String saveServiceInformationAboutTeacher(Teacher teacher) {
		int result = informationService.saveTeacher(teacher);
		if (result > 0) {
		return "Affected " + result + " rows.";
		}
		
		return "Error. Current Teacher object wasn't save to database.";
	}
	
	public void closeResources() {
		if (entityManager.isOpen()) {
			entityManager.close();
			JpaUtil.getInstance().closeEntityManagerFactory();
		}
	}
}
