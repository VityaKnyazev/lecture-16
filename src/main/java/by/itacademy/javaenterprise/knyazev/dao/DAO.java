package by.itacademy.javaenterprise.knyazev.dao;

public interface DAO<T> {
	int save(T object);
	T find(Integer id);	
}
