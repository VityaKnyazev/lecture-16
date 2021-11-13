package by.itacademy.javaenterprise.knyazev.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TransactionRequiredException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import by.itacademy.javaenterprise.knyazev.entities.Teacher;
import by.itacademy.javaenterprise.knyazev.entities.TeacherDetails;

public class TeacherDetailsDAOTests {
	private EntityManager entityManagerMock;
	private static EntityTransaction entityTransactionMock;
	private TeacherDetailsDAO teacherDetailsDAO;
	
	@BeforeAll
	public static void setUp() {
		entityTransactionMock = Mockito.mock(EntityTransaction.class);
	}
	
	@BeforeEach
	public void setUpBeforeTest() {
		entityManagerMock = Mockito.mock(EntityManager.class);		
		teacherDetailsDAO = new TeacherDetailsDAO(entityManagerMock);	
	}
	
	@Test
	public void whenSaveTeacherDetails() {
		TeacherDetails teacherDetails = new TeacherDetails();
		teacherDetails.setId(8);
		
		Mockito.when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);
		
		int result = teacherDetailsDAO.save(teacherDetails);
		
		assertEquals(1, result);
	}
	
	@Test
	public void whenSaveNullTeacherDetails() {
		
		Mockito.when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);
			
		int result = teacherDetailsDAO.save(null);
		
		assertEquals(0, result);
	}
	
	@Test
	public void whenSaveTrowEntityExistsException() {
				
		TeacherDetails teacherDetails = new TeacherDetails();
		
		Mockito.when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);		
		Mockito.doThrow(EntityExistsException.class).when(entityManagerMock).persist(Mockito.any());
				
		int result = teacherDetailsDAO.save(teacherDetails);
		
		assertEquals(0, result);
	}
	
	@Test
	public void whenSaveTrowIllegalArgumentException() {
				
		TeacherDetails teacherDetails = new TeacherDetails();
		
		Mockito.when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);
		Mockito.doThrow(IllegalArgumentException.class).when(entityManagerMock).persist(Mockito.any());
				
		int result = teacherDetailsDAO.save(teacherDetails);
		
		assertEquals(0, result);
	}
	
	@Test
	public void whenSaveThrowTransactionRequiredException() {
				
		TeacherDetails teacherDetails = new TeacherDetails();
		
		Mockito.when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);
		Mockito.doThrow(TransactionRequiredException.class).when(entityManagerMock).persist(Mockito.any());
				
		int result = teacherDetailsDAO.save(teacherDetails);
		
		assertEquals(0, result);
	}
	
	@Test
	public void whenFindTeacherDetails() {
		TeacherDetails teacherDetails = new TeacherDetails();
		teacherDetails.setId(5);
		
		Integer idForQuery = 5;
		
		Mockito.when(entityManagerMock.find(Mockito.<Class<TeacherDetails>>any(), Mockito.eq(idForQuery))).thenReturn(teacherDetails);
		
		assertEquals(idForQuery, teacherDetailsDAO.find(idForQuery).getId());
	}
	
	@Test
	public void whenFindTeacherOnNull() {
		
		Integer idForQuery = null;
		
		assertEquals(0, teacherDetailsDAO.find(idForQuery).getId());
	}
	
	@Test
	public void whenFindTeacherOnIdBelowZero() {
		Integer idForQuery = -1;
		
		assertEquals(0, teacherDetailsDAO.find(idForQuery).getId());
	}
	
	@Test
	public void whenFindTeacherTrowIllegalArgumentException() {
		Integer idForQuery = 12566;
		
		Mockito.when(entityManagerMock.find(Mockito.<Class<Teacher>>any(), Mockito.eq(idForQuery))).thenThrow(IllegalArgumentException.class);
		
		assertEquals(0, teacherDetailsDAO.find(idForQuery).getId());
	}
}
