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

public class TeachersDAOTests {
	private EntityManager entityManagerMock;
	private static EntityTransaction entityTransactionMock;
	private TeachersDAO teachersDAO;
	
	@BeforeAll
	public static void setUp() {
		entityTransactionMock = Mockito.mock(EntityTransaction.class);
	}
	
	@BeforeEach
	public void setUpBeforeTest() {
		entityManagerMock = Mockito.mock(EntityManager.class);		
		teachersDAO = new TeachersDAO(entityManagerMock);	
	}
	
	@Test
	public void whenSaveTeacher() {
		Teacher teacher = new Teacher();
		
		Mockito.when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);
		
		int result = teachersDAO.save(teacher);
		
		assertEquals(1, result);
	}
	
	@Test
	public void whenSaveNullTeacher() {
		
		Mockito.when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);
			
		int result = teachersDAO.save(null);
		
		assertEquals(0, result);
	}
	
	@Test
	public void whenSaveTrowEntityExistsException() {
				
		Teacher teacher = new Teacher();
		
		Mockito.when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);		
		Mockito.doThrow(EntityExistsException.class).when(entityManagerMock).persist(Mockito.any());
				
		int result = teachersDAO.save(teacher);
		
		assertEquals(0, result);
	}
	
	@Test
	public void whenSaveTrowIllegalArgumentException() {
				
		Teacher teacher = new Teacher();
		
		Mockito.when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);
		Mockito.doThrow(IllegalArgumentException.class).when(entityManagerMock).persist(Mockito.any());
				
		int result = teachersDAO.save(teacher);
		
		assertEquals(0, result);
	}
	
	@Test
	public void whenSaveThrowTransactionRequiredException() {
				
		Teacher teacher = new Teacher();
		
		Mockito.when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);
		Mockito.doThrow(TransactionRequiredException.class).when(entityManagerMock).persist(Mockito.any());
				
		int result = teachersDAO.save(teacher);
		
		assertEquals(0, result);
	}
	
	@Test
	public void whenFindTeacher() {
		Teacher teacher = new Teacher();
		teacher.setId(5);
		
		Integer idForQuery = 5;
		
		Mockito.when(entityManagerMock.find(Mockito.<Class<Teacher>>any(), Mockito.eq(idForQuery))).thenReturn(teacher);
		
		assertEquals(idForQuery, teachersDAO.find(idForQuery).getId());
	}
	
	@Test
	public void whenFindTeacherOnNull() {
		
		Integer idForQuery = null;
		
		assertEquals(0, teachersDAO.find(idForQuery).getId());
	}
	
	@Test
	public void whenFindTeacherOnIdBelowZero() {
		Integer idForQuery = -1;
		
		assertEquals(0, teachersDAO.find(idForQuery).getId());
	}
	
	@Test
	public void whenFindTeacherTrowIllegalArgumentException() {
		Integer idForQuery = 12566;
		
		Mockito.when(entityManagerMock.find(Mockito.<Class<Teacher>>any(), Mockito.eq(idForQuery))).thenThrow(IllegalArgumentException.class);
		
		assertEquals(0, teachersDAO.find(idForQuery).getId());
	}
}
