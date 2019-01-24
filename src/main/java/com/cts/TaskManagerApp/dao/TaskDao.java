package com.cts.TaskManagerApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.TaskManagerApp.model.Task;

@Repository
public interface TaskDao extends JpaRepository<Task, Long> {
	 
	}
