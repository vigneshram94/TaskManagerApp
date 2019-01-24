package com.cts.TaskManagerApp;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.TaskManagerApp.dao.TaskDao;
import com.cts.TaskManagerApp.model.Task;
import com.cts.TaskManagerApp.service.ServiceImpl;

public class TaskServiceTest {

	@InjectMocks
    private ServiceImpl taskService;
	
	@Mock
	private TaskDao taskDaos;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getAllTaskTest() throws ParseException {

		Task task1 = new Task();
		
		task1.setTaskId(1);
		task1.setTaskName("clean tyres");
		task1.setParentTask("clean car");
		task1.setPriority(5);
		task1.setStartDate(new Date(01-01-2019));
		task1.setEndDate(new Date(02-02-2019));
		task1.setIsActive(false);
		
		Task task2 = new Task();
		task2.setTaskId(2);
		task2.setTaskName("clean iniside");
		task2.setParentTask("clean car");
		task2.setPriority(5);
		task2.setStartDate(new Date(01-01-2019));
		task2.setEndDate(new Date(02-02-2019));
		task2.setIsActive(false);

		List<Task> tasks = new ArrayList<Task>();
		tasks.add(task1);
		tasks.add(task2);
		
		 when(taskDaos.findAll()).thenReturn(tasks);
	        List<Task> taskList = taskService.getTasks();      
	        assertEquals(2, taskList.size());
	        verify(taskDaos, times(1)).findAll();
		
	}
	   @Test
	    public void addTaskTest() throws ParseException{
		          	Task task = new Task();
		          	task.setTaskId(1);
		    		task.setTaskName("clean tyres");
		    		task.setParentTask("clean car");
		    		task.setPriority(5);
		    		task.setStartDate(new Date(01-01-2019));
		    		task.setEndDate(new Date(02-02-2019));
		    		task.setIsActive(false);  
		    		taskService.addTask(task);        
		    		verify(taskDaos, times(1)).save(task);
	    }
	   @Test
	    public void updateTaskTest() throws ParseException{
		   	Task task = new Task();
          	task.setTaskId(1);
    		task.setTaskName("clean tyres");
    		task.setParentTask("clean car");
    		task.setPriority(5);
    		task.setStartDate(new Date(01-01-2019));
    		task.setEndDate(new Date(02-02-2019));
    		task.setIsActive(false);  
    		taskService.updateTask(task);          
	        verify(taskDaos, times(1)).save(task);
	    }
	   
	   @Test
	    public void endTaskTest() throws ParseException{
		   	Task task = new Task();
         	task.setTaskId(1);
	   		task.setTaskName("clean tyres");
	   		task.setParentTask("clean car");
	   		task.setPriority(5);
	   		task.setStartDate(new Date(01-01-2019));
	   		task.setEndDate(new Date(02-02-2019));
	   		task.setIsActive(false);  
	   		taskService.endTask(task);          
	        verify(taskDaos, times(1)).save(task);
	    }
	   
	
}
