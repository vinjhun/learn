package com.example.service;

import java.util.List;

public interface ITestService {
	void findTest();
	
	<T> void createTest(T t);
	
	<T> void batchCreateTest(List<T> t);
}
