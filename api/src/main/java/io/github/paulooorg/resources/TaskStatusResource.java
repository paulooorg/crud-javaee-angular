package io.github.paulooorg.resources;

import java.util.Arrays;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.github.paulooorg.model.entities.TaskStatus;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("taskstatus")
public class TaskStatusResource {
	@GET
	public Response findAll() {
		return Response.ok().entity(Arrays.asList(TaskStatus.values())).build();
	}
}
