package hu.kaev.school.dto;

import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDto {
	
	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include()
	private long id;

	private String name;
	
	@ManyToMany
	private Set<StudentDto> students;
	
	@ManyToMany
	private Set<TeacherDto> teachers;
	
}
