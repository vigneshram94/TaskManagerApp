package com.cts.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "parent_task")
public class ParentTask {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int parentID;
	
	@Column(name = "Parent_Task")
	private String parentTask;
}
