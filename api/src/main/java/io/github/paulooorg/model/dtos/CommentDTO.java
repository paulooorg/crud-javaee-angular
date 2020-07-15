package io.github.paulooorg.model.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

public class CommentDTO {
	private Long id;
	
	@NotBlank
	private String description;
	
	private LocalDateTime creationDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
}
