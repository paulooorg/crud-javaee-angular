package io.github.paulooorg.model.dtos.mapper;

import io.github.paulooorg.model.dtos.TaskDTO;
import io.github.paulooorg.model.entities.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {
	TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);
	
	TaskDTO taskToDTO(Task task);
	
	@Mappings({
		@Mapping(target = "creationDate", ignore = true),
		@Mapping(target = "updateDate", ignore = true)
	})
	Task DTOToTask(TaskDTO taskDTO);
	
	@Mappings({
		@Mapping(target = "id", ignore = true),
		@Mapping(target = "creationDate", ignore = true),
		@Mapping(target = "updateDate", ignore = true),
		@Mapping(target = "comments", ignore = true)
	})
	Task merge(TaskDTO dto, @MappingTarget Task task);
}
