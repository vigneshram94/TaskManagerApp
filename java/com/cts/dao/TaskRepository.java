package com.cts.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cts.model.Task;

public interface  TaskRepository<P> extends CrudRepository<Task, Long> {
    
	List<Task> findByTaskName(String taskName);
	
}
