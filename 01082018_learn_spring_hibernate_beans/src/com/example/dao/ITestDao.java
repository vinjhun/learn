package com.example.dao;

import java.util.List;

public interface ITestDao {
	
	void findTest();
	
	<T> void createTest(T t);
	
	<T> void batchCreateTest(List<T> t);
}
