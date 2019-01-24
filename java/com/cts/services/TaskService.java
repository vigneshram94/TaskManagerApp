package com.cts.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cts.dao.TaskRepository;
import com.cts.model.Task;

public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	public List<Task> retrieveTasks() {	
		return (List<Task>) taskRepository.findAll();
	}
	
	public List<Task> findByTaskName(String name) {
		return taskRepository.findByTaskName(name);
	}

	public void addTask(Task task) {
		taskRepository.save(task);
	}
	
}
