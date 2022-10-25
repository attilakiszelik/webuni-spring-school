package hu.kaev.school.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.kaev.school.dto.CourseDto;
import hu.kaev.school.model.Course;

@Mapper(componentModel = "spring")
public interface CourseMapper {

	List<CourseDto> coursesToDtos(List<Course> courses);

	CourseDto courseToDto(Course course);
	
	@IterableMapping(qualifiedByName = "summary")
	List<CourseDto> courseSummariesToDtos(List<Course> courses);

	@Named("summary")
	@Mapping(target="teachers", ignore = true)
	@Mapping(target="students", ignore = true)
	CourseDto courseSummaryToDto(Course course);

}