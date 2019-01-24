package com.cts.TaskManagerApp;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.cts.TaskManagerApp.controller.TaskController;
import com.cts.TaskManagerApp.exceptions.NoValuesFoundException;
import com.cts.TaskManagerApp.model.Task;
import com.cts.TaskManagerApp.service.TaskService;
import com.cts.TaskManagerApp.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@WebMvcTest(value = TaskController.class, secure = false)
public class TaskControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@InjectMocks
    TaskController taskController;
	
	
	@MockBean
	private TaskService taskService;
	
	@Test
	public void getTasks() throws Exception {
		
		List<Task> mockCourse = new ArrayList();
		Task task1 = new Task();
		task1.setTaskId(1);
		task1.setTaskName("clean tyres");
		task1.setParentTask("clean car");
		task1.setPriority(5);
		task1.setStartDate(new Date(01-01-2019));
		task1.setEndDate(new Date(02-02-2019));
		task1.setIsActive(false);
		mockCourse.add(task1);
		when(taskService.getTasks()).thenReturn(mockCourse);
		
		mockMvc.perform(get("/tasks/"))
		        .andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$[0].taskId", is(1)));	
	}
	
	@Test
	public void getTasksNegative() throws Exception {
		
		when(taskService.getTasks()).thenThrow(new NoValuesFoundException());
		
		mockMvc.perform(get("/tasks/"))
		        .andExpect(status().isBadRequest());
	}
	
	@Test
	public void updateTask() throws Exception {		
		Task task1 = new Task();
		task1.setTaskId(1);
		task1.setTaskName("clean tyres");
		task1.setParentTask("clean car");
		task1.setPriority(5);
		task1.setStartDate(new Date(01-01-2019));
		task1.setEndDate(new Date(02-02-2019));
		task1.setIsActive(false);
		when(taskService.updateTask(task1)).thenReturn(task1);
        mockMvc.perform(put("/tasks/update")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(asJsonString(task1)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.taskId", is(1)));          
	}
	
	@Test
	public void updateTaskNegative() throws Exception {		
		Task task1 = new Task();
		task1.setTaskId(1);
		task1.setTaskName("clean tyres");
		task1.setParentTask("clean car");
		task1.setPriority(5);
		task1.setStartDate(new Date(01-01-2019));
		task1.setEndDate(new Date(02-02-2019));
		task1.setIsActive(false);
		when(taskService.updateTask(task1)).thenThrow(new NoValuesFoundException());
        mockMvc.perform(put("/tasks/update"))
            .andExpect(status().isBadRequest());
        
	}
	
	@Test
	public void endTask() throws Exception {		
		Task task1 = new Task();
		task1.setTaskId(1);
		task1.setTaskName("clean tyres");
		task1.setParentTask("clean car");
		task1.setPriority(5);
		task1.setStartDate(new Date(01-01-2019));
		task1.setEndDate(new Date(02-02-2019));
		task1.setIsActive(false);
		
		Task task2 = new Task();
		task2.setTaskId(1);
		task2.setTaskName("clean tyres");
		task2.setParentTask("clean car");
		task2.setPriority(5);
		task2.setStartDate(new Date(01-01-2019));
		task2.setEndDate(new Date(02-02-2019));
		task2.setIsActive(true);
		when(taskService.getTaskByTaskId(task1.getTaskId())).thenReturn(task1);
		when(taskService.endTask(task1)).thenReturn(task2);
        mockMvc.perform(put("/tasks/end")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(asJsonString(task2)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.isActive", is(true)));          
	}
	
	@Test
	public void endTaskNegative() throws Exception {		
		Task task1 = new Task();
		task1.setTaskId(1);
		task1.setTaskName("clean tyres");
		task1.setParentTask("clean car");
		task1.setPriority(5);
		task1.setIsActive(false);
		when(taskService.endTask(task1)).thenThrow(new NoValuesFoundException());
        mockMvc.perform(put("/tasks/end"))
            .andExpect(status().isBadRequest());
        
	}
	
	@Test
	public void addTask() throws Exception {	
		Task task1 = new Task();
		task1.setTaskId(1);
		task1.setTaskName("clean tyres");
		task1.setParentTask("clean car");
		task1.setPriority(5);
		task1.setStartDate(new Date(01-01-2019));
		task1.setEndDate(new Date(02-02-2019));
		task1.setIsActive(false);
		when(taskService.addTask(task1)).thenReturn(task1);
        mockMvc.perform(post("/tasks/create")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(asJsonString(task1)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.taskId", is(1)));          
	}

	@Test
	public void addTaskNegative() throws Exception {
		
		Task task1 = new Task();
		when(taskService.addTask(task1)).thenThrow(new NoValuesFoundException());
    	
        mockMvc.perform(post("/tasks/create")
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(asJsonString(task1)))
            .andExpect(status().isBadRequest());
	}
	
	   public static String asJsonString(final Object obj) {
	        try {
	            return new ObjectMapper().writeValueAsString(obj);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }

}
