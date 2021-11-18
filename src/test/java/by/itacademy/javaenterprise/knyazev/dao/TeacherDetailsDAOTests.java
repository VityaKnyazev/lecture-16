package by.itacademy.javaenterprise.knyazev.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TransactionRequiredException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import by.itacademy.javaenterprise.knyazev.dao.exceptions.TeacherDetailsExceptionDAO;
import by.itacademy.javaenterprise.knyazev.entities.TeacherDetails;

public class TeacherDetailsDAOTests {
	private EntityManager entityManagerMock;
	private EntityTransaction entityTransactionMock;
	private TeacherDetailsDAO teacherDetailsDAO;
		
	
	@BeforeEach
	public void setUpBeforeEachTest() {
		entityManagerMock = Mockito.mock(EntityManager.class);		
		entityTransactionMock = Mockito.mock(EntityTransaction.class);
		teacherDetailsDAO = new TeacherDetailsDAO(entityManagerMock);	
	}
	
	@Test
	public void whenSaveTeacherDetails() throws TeacherDetailsExceptionDAO {
		TeacherDetails teacherDetails = new TeacherDetails();
		teacherDetails.setId(18L);
		
		Mockito.when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);
				
		Long result = teacherDetailsDAO.save(teacherDetails);
		
		assertNotNull(result);
		assertEquals(18L, result);
		Mockito.verify(entityManagerMock, times(1)).persist(teacherDetails);
	}
	
	@Test
	public void whenSaveNullTeacherDetails() {
		
		Mockito.when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);
					
		assertThrows(TeacherDetailsExceptionDAO.class, () -> teacherDetailsDAO.save(null), "TeacherDetailsExceptionDAO was expected");
	}
	
	@Test
	public void whenSaveThrowEntityExistsException() throws TeacherDetailsExceptionDAO {
				
		TeacherDetails teacherDetails = new TeacherDetails();
		
		Mockito.when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);		
		Mockito.doThrow(EntityExistsException.class).when(entityManagerMock).persist(Mockito.eq(teacherDetails));
				
		Long result = teacherDetailsDAO.save(teacherDetails);
				
		assertNull(result);		
		Mockito.verify(entityManagerMock, times(1)).persist(teacherDetails);
		
	}
	
	@Test
	public void whenSaveTrowIllegalArgumentException() throws TeacherDetailsExceptionDAO {
				
		TeacherDetails teacherDetails = new TeacherDetails();
		
		Mockito.when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);
		Mockito.doThrow(IllegalArgumentException.class).when(entityManagerMock).persist(Mockito.eq(teacherDetails));
				
		Long result = teacherDetailsDAO.save(teacherDetails);
		
		assertNull(result);
		Mockito.verify(entityManagerMock, times(1)).persist(teacherDetails);
	}
	
	@Test
	public void whenSaveThrowTransactionRequiredException() throws TeacherDetailsExceptionDAO {
				
		TeacherDetails teacherDetails = new TeacherDetails();
		
		Mockito.when(entityManagerMock.getTransaction()).thenReturn(entityTransactionMock);
		Mockito.doThrow(TransactionRequiredException.class).when(entityManagerMock).persist(Mockito.eq(teacherDetails));
				
		Long result = teacherDetailsDAO.save(teacherDetails);
		
		assertNull(result);
		Mockito.verify(entityManagerMock, times(1)).persist(teacherDetails);
	}
	
	@Test
	public void whenFindTeacherDetails() throws TeacherDetailsExceptionDAO {
		TeacherDetails teacherDetails = new TeacherDetails();
		teacherDetails.setId(5L);
		
		Long idForQuery = 5L;
		
		Mockito.when(entityManagerMock.find(Mockito.<Class<TeacherDetails>>any(), Mockito.eq(idForQuery))).thenReturn(teacherDetails);
		
		assertEquals(teacherDetails, teacherDetailsDAO.find(idForQuery));
	}
	
	@Test
	public void whenFindTeacherDetailsOnNull() throws TeacherDetailsExceptionDAO {
		
		assertThrows(TeacherDetailsExceptionDAO.class, () -> teacherDetailsDAO.find(null), "TeacherDetailsExceptionDAO was expected");
	}
	
	@Test
	public void whenFindTeacherOnIdBelowZero() {
		Long idForQuery = -1L;
		
		assertThrows(TeacherDetailsExceptionDAO.class, () -> teacherDetailsDAO.find(idForQuery), "TeacherDetailsExceptionDAO was expected");
	}
	
	@Test
	public void whenFindTeacherTrowIllegalArgumentException() throws TeacherDetailsExceptionDAO {
		Long idForQuery = 12566L;
		
		Mockito.when(entityManagerMock.find(Mockito.<Class<TeacherDetails>>any(), Mockito.eq(idForQuery))).thenThrow(IllegalArgumentException.class);
		
		assertNull(teacherDetailsDAO.find(idForQuery));
	}
}
