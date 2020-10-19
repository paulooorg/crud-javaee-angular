package io.github.paulooorg.repository;

import io.github.paulooorg.infra.Page;
import io.github.paulooorg.infra.Pagination;
import io.github.paulooorg.model.entities.Task;
import io.github.paulooorg.model.entities.TaskStatus;

import javax.persistence.Query;

public class TaskRepository extends AbstractEntityRepository<Task, Long> {
	public TaskRepository() {
		super(Task.class);
	}
	
	public Page<Task> searchByTitleOrDescriptionLike(Pagination pagination, String term) {
		String where = "where LOWER(t.title) like LOWER(CONCAT('%', :term, '%')) or LOWER(t.description) like LOWER(CONCAT('%', :term, '%'))";
		
		Query findQuery = em.createQuery("select t from Task t " + where, Task.class);
		findQuery.setParameter("term", term);
		
		Query countQuery = em.createQuery("select count(t) from Task t " + where, Long.class);
		countQuery.setParameter("term", term);
		
		return paginate(findQuery, countQuery, pagination);
	}
	
	public Page<Task> searchByStatus(Pagination pagination, TaskStatus status) {
		String where = "where t.status = :status";
		
		Query findQuery = em.createQuery("select t from Task t " + where, Task.class);
		findQuery.setParameter("status", status);
		
		Query countQuery = em.createQuery("select count(t) from Task t " + where, Long.class);
		countQuery.setParameter("status", status);
		
		return paginate(findQuery, countQuery, pagination);
	}
}
