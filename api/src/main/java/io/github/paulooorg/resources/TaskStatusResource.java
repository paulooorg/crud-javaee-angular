package io.github.paulooorg.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.github.paulooorg.model.dtos.TaskStatusDTO;
import io.github.paulooorg.model.entities.TaskStatus;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("taskstatus")
public class TaskStatusResource {
	@GET
	public Response findAll() {
		List<TaskStatusDTO> statuses = Arrays.asList(TaskStatus.values())
				.stream().map(ts -> new TaskStatusDTO(ts.name(), ts.getLabel())).collect(Collectors.toList());
		return Response.ok().entity(statuses).build();
	}
}
