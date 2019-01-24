package com.cts.TaskManagerApp.service;

import java.util.List;

import com.cts.TaskManagerApp.model.Task;


public interface TaskService {
	public Task addTask(Task task);
	public List<Task> getTasks();
	public Task updateTask(Task task);
	public Task endTask(Task task);
	public Task getTaskByTaskId(long id);
}
