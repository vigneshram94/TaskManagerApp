package com.cts.TaskManagerApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.TaskManagerApp.model.Task;
import com.cts.TaskManagerApp.service.TaskService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/tasks")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@PostMapping("/create")
	public ResponseEntity<Object> addTask(@RequestBody Task task) {
		if(task==null || task.getStartDate()==null || task.getEndDate()==null )
		{
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
		}else {
		
			taskService.addTask(task);
		    return  new ResponseEntity<Object>(task, HttpStatus.CREATED);
		}

	}

	@GetMapping("/")
	public List<Task> getTasks() {
		List<Task> task = taskService.getTasks();
		return task;
	}

	@PutMapping("/update")
	public ResponseEntity<Object> updateTask( @RequestBody Task task) {
		taskService.updateTask(task);
		return new ResponseEntity<Object>(task, HttpStatus.OK);
	}
	
	@PutMapping("/end")
	public  ResponseEntity<Object> endTask( @RequestBody Task task) {
		System.out.println("inisde end");
		Task t = taskService.getTaskByTaskId(task.getTaskId());
		t.setIsActive(true);
		taskService.endTask(t);
		return new ResponseEntity<Object>(task, HttpStatus.OK);
		
	}
}
