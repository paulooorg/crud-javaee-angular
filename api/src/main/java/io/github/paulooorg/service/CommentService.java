package io.github.paulooorg.service;

import io.github.paulooorg.exceptions.ApiExceptions;
import io.github.paulooorg.infra.Page;
import io.github.paulooorg.infra.Pagination;
import io.github.paulooorg.model.dtos.CommentDTO;
import io.github.paulooorg.model.dtos.mapper.CommentMapper;
import io.github.paulooorg.model.entities.Comment;
import io.github.paulooorg.model.entities.Task;
import io.github.paulooorg.repository.CommentRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Collectors;

public class CommentService {
	@Inject
	private TaskService taskService;
	
	@Inject
	private CommentRepository commentRepository;
	
	public Page<CommentDTO> findAllByTaskId(Pagination pagination, Long taskId) {
		Optional<Task> task = taskService.findById(taskId);
		if (task.isPresent()) {
			Page<Comment> page = commentRepository.findAllByTaskId(pagination, taskId);
			return createPageDTO(pagination, page);
		}
		throw ApiExceptions.entityIdNotFound(taskId);
	}
	
	private Page<CommentDTO> createPageDTO(Pagination pagination, Page<Comment> page) {
		return new Page<CommentDTO>(page.getContent().stream().map(c -> CommentMapper.INSTANCE.commentToDTO(c)).collect(Collectors.toList()),
				page.getTotalCount(), page.getNumberOfPages(), page.getCurrentPage());
	}
	
	public Optional<Comment> findById(Long taskId, Long id) {
		return commentRepository.findBy(id);
	}
	
	@Transactional
	public Long create(Comment comment) {
		Long commentNumber = commentRepository.findLastCommentNumber(comment.getTask().getId()) + 1;
		comment.setNumber(commentNumber);
		return commentRepository.save(comment).get().getId();
	}
}
