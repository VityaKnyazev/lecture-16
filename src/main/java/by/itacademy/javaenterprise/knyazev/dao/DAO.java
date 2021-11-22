package by.itacademy.javaenterprise.knyazev.dao;

import by.itacademy.javaenterprise.knyazev.dao.exceptions.ExceptionDAO;

public interface DAO<T> {
	Long save(T object) throws ExceptionDAO;
	T find(Long id) throws ExceptionDAO;	
}
