package hu.kaev.school.dto;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherDto {

	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private long id;
	
	private String name;
	private LocalDate birthdate;
	
	@ManyToMany(mappedBy="teachers")
	private Set<CourseDto> courses;
	
}