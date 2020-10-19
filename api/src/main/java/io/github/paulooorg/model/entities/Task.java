package io.github.paulooorg.model.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Task implements BaseEntity {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length = 150)
	private String title;
	
	@Column(length = 3000)
	private String description;
	
	@Column(name = "creation_date")
	private LocalDateTime creationDate = LocalDateTime.now();
	
	@Column(name = "update_date")
	private LocalDateTime updateDate;

	@Column
	@Enumerated(EnumType.STRING)
	private TaskStatus status = TaskStatus.TODO;
	
	@OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("creationDate DESC")
	private List<Comment> comments;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
}
