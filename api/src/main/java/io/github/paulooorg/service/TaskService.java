package io.github.paulooorg.service;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.transaction.Transactional;

import io.github.paulooorg.infra.BeanValidator;
import io.github.paulooorg.infra.Page;
import io.github.paulooorg.infra.Pagination;
import io.github.paulooorg.model.dtos.TaskDTO;
import io.github.paulooorg.model.dtos.mapper.TaskMapper;
import io.github.paulooorg.model.entities.Task;
import io.github.paulooorg.model.entities.TaskStatus;
import io.github.paulooorg.repository.TaskRepository;

public class TaskService {
	@Inject
	private TaskRepository taskRepository;
	
	public Page<TaskDTO> findAll(Pagination pagination) {
		Page<Task> page = taskRepository.findAll(pagination);
		return createPageDTO(pagination, page);
	}
	
	public Page<TaskDTO> searchByTitleOrDescriptionLike(Pagination pagination, String term) {
		Page<Task> page = taskRepository.searchByTitleOrDescriptionLike(pagination, term);
		return createPageDTO(pagination, page);
	}
	
	public Page<TaskDTO> searchByStatus(Pagination pagination, TaskStatus status) {
		Page<Task> page = taskRepository.searchByStatus(pagination, status);
		return createPageDTO(pagination, page);
	}
	
	private Page<TaskDTO> createPageDTO(Pagination pagination, Page<Task> page) {
		return new Page<TaskDTO>(page.getContent().stream().map(t -> TaskMapper.INSTANCE.taskToDTO(t)).collect(Collectors.toList()),
				page.getTotalCount(), page.getNumberOfPages(), page.getCurrentPage());
	}
	
	public Optional<Task> findById(Long id) {
		return taskRepository.findBy(id);
	}
	
	@Transactional
	public Long create(Task task) {
		return taskRepository.save(task).get().getId();
	}
	
	@Transactional
	public void update(Long id, TaskDTO taskDTO) {
		new BeanValidator<TaskDTO>().validate(taskDTO);
		Optional<Task> task = findById(id);
		if (task.isPresent()) {
			Task updatedTask = TaskMapper.INSTANCE.merge(taskDTO, task.get());
			taskRepository.save(updatedTask);
		}
	}
	
	@Transactional
	public void delete(Long id) {
		Optional<Task> task = findById(id);
		if (task.isPresent()) {
			taskRepository.remove(task.get());
		}
	}
}
