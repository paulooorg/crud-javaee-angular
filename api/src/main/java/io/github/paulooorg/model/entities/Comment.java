package io.github.paulooorg.model.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment implements BaseEntity {
	@Id
	@GeneratedValue
	private Long id;

	private Long number = 1l;

	private String description;
	
	@Column(name = "creation_date")
	private LocalDateTime creationDate = LocalDateTime.now();
	
	@ManyToOne
	@JoinColumn(name = "task_id")
	private Task task;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
}
