package hu.kaev.school.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import hu.kaev.school.dto.StudentDto;
import hu.kaev.school.model.Student;

@Mapper(componentModel = "spring")
public interface StudentMapper {

	List<StudentDto> studentsToDtos(List<Student> students);

	StudentDto studentToDto(Student student);
	
	@IterableMapping(qualifiedByName = "summary")
	List<StudentDto>studentSummariesToDtos(List<Student> students);

	@Named("summary")
	@Mapping(target="courses", ignore = true)
	StudentDto studentSummaryToDto(Student student);

}