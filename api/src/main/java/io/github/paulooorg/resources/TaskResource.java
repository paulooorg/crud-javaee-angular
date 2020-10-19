package io.github.paulooorg.resources;

import io.github.paulooorg.exceptions.ApiExceptions;
import io.github.paulooorg.infra.BeanValidator;
import io.github.paulooorg.infra.Pagination;
import io.github.paulooorg.model.dtos.TaskDTO;
import io.github.paulooorg.model.dtos.mapper.TaskMapper;
import io.github.paulooorg.model.entities.Task;
import io.github.paulooorg.model.entities.TaskStatus;
import io.github.paulooorg.service.TaskService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.Optional;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("task")
public class TaskResource {
	@Inject
	private TaskService taskService;
	
	@GET
	public Response findAll(@Context UriInfo uriInfo) {
		return Response.ok().entity(taskService.findAll(new Pagination(uriInfo))).build();
	}
	
	@GET
	@Path("search/{term}")
	public Response searchByTitleOrDescriptionLike(@Context UriInfo uriInfo, @PathParam("term") String term) {
		return Response.ok().entity(taskService.searchByTitleOrDescriptionLike(new Pagination(uriInfo), term)).build();
	}
	
	@GET
	@Path("search/status/{status}")
	public Response searchByStatus(@Context UriInfo uriInfo, @PathParam("status") TaskStatus status) {
		return Response.ok().entity(taskService.searchByStatus(new Pagination(uriInfo), status)).build();
	}
	
	@GET
	@Path("{id}")
	public Response findById(@PathParam("id") Long id) {
		Optional<Task> task = taskService.findById(id);
		if (task.isPresent()) {
			return Response.ok().entity(TaskMapper.INSTANCE.taskToDTO(task.get())).build();
		}
		throw ApiExceptions.entityIdNotFound(id);
	}
	
	@POST
	public Response create(TaskDTO taskDTO) {
		new BeanValidator<TaskDTO>().validate(taskDTO);
		return Response.ok().entity(taskService.create(TaskMapper.INSTANCE.DTOToTask(taskDTO))).build();
	}
	
	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") Long id, TaskDTO taskDTO) {
		taskService.update(id, taskDTO);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Long id) {
		taskService.delete(id);
		return Response.ok().build();
	}
}
