package io.github.paulooorg.model.dtos.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import io.github.paulooorg.model.dtos.CommentDTO;
import io.github.paulooorg.model.entities.Comment;

@Mapper
public interface CommentMapper {
	CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
	
	CommentDTO commentToDTO(Comment comment);
	
	@Mappings({
		@Mapping(target = "creationDate", ignore = true)
	})
	Comment DTOToComment(CommentDTO commentDTO);
}
