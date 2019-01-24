package com.cts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.services.TaskService;
import com.cts.model.Task;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping(path = "/tasks")

public class TaskController {

	@Autowired
	private TaskService taskService;

	@GetMapping(path= "/", produces = "application/json")
	public List<Task> retrieveAllTasks() {
		return taskService.retrieveTasks();
	}
	
	@PostMapping(path= "/", consumes = "application/json")
	public void addTask(@RequestBody Task task) {
		taskService.addTask(task);
	}

}