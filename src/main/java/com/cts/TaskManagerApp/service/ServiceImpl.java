package com.cts.TaskManagerApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.TaskManagerApp.dao.TaskDao;
import com.cts.TaskManagerApp.model.Task;


@Service
public class ServiceImpl implements TaskService {

	@Autowired
	private TaskDao taskDao;
	
	public Task addTask(Task task) {	
			return taskDao.save(task);
		
	}
	
	public List<Task> getTasks(){
		List<Task> task = taskDao.findAll();
		return task;
	}
	
	@Override
	public Task updateTask(Task task) {
		return taskDao.save(task);
	}
	
	public Task endTask(Task task) {
		 return taskDao.save(task);
	}
	
	public Task getTaskByTaskId(long id) {
		return taskDao.getOne(id);
	}
	
}
