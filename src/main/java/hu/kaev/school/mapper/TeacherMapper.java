package hu.kaev.school.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.kaev.school.dto.TeacherDto;
import hu.kaev.school.model.Teacher;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

	List<TeacherDto> teachersToDtos(List<Teacher> teachers);

	TeacherDto teacherToDto(Teacher teacher);
	
	@IterableMapping(qualifiedByName = "summary")
	List<TeacherDto> teacherSummariesToDtos(List<Teacher> teachers);

	@Named("summary")
	@Mapping(target="courses", ignore = true)
	TeacherDto teacherSummaryToDto(Teacher teacher);

}