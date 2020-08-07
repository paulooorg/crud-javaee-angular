package io.github.paulooorg.model.entities;

public enum TaskStatus {
	TODO("Todo"), DOING("Doing"), DONE("Done");
	
	private String label;
	
	private TaskStatus(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return this.label;
	}
}
