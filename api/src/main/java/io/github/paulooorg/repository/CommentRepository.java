package io.github.paulooorg.repository;

import javax.persistence.Query;

import io.github.paulooorg.infra.Page;
import io.github.paulooorg.infra.Pagination;
import io.github.paulooorg.model.entities.Comment;

public class CommentRepository extends AbstractEntityRepository<Comment, Long> {
	public CommentRepository() {
		super(Comment.class);
	}
	
	public Page<Comment> findAllByTaskId(Pagination pagination, Long taskId) {
		String where = "where c.task.id = :taskId";
		
		Query findQuery = em.createQuery("select c from Comment c " + where, Comment.class);
		findQuery.setParameter("taskId", taskId);
		
		Query countQuery = em.createQuery("select count(c) from Comment c " + where, Long.class);
		countQuery.setParameter("taskId", taskId);
		
		return paginate(findQuery, countQuery, pagination);
	}
}
