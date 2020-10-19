package io.github.paulooorg.resources;

import io.github.paulooorg.exceptions.ApiExceptions;
import io.github.paulooorg.infra.BeanValidator;
import io.github.paulooorg.infra.Pagination;
import io.github.paulooorg.model.dtos.CommentDTO;
import io.github.paulooorg.model.dtos.mapper.CommentMapper;
import io.github.paulooorg.model.entities.Comment;
import io.github.paulooorg.model.entities.Task;
import io.github.paulooorg.service.CommentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Optional;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("task/{taskId}/comment")
public class CommentResource {
	@Inject
	private CommentService commentService;
	
	@GET
	public Response findAll(@PathParam("taskId") Long taskId, @Context UriInfo uriInfo) {
		return Response.ok().entity(commentService.findAllByTaskId(new Pagination(uriInfo), taskId)).build();
	}
	
	@GET
	@Path("{id}")
	public Response findById(@PathParam("taskId") Long taskId, @PathParam("id") Long id) {
		Optional<Comment> comment = commentService.findById(taskId, id);
		if (comment.isPresent()) {
			return Response.ok().entity(CommentMapper.INSTANCE.commentToDTO(comment.get())).build();
		}
		throw ApiExceptions.entityIdNotFound(id);
	}
	
	@POST
	public Response create(CommentDTO commentDTO, @PathParam("taskId") Long taskId) {
		new BeanValidator<CommentDTO>().validate(commentDTO);
		Comment comment = CommentMapper.INSTANCE.DTOToComment(commentDTO);
		Task task = new Task();
		task.setId(taskId);
		comment.setTask(task);
		return Response.ok().entity(commentService.create(comment)).build();
	}
}
