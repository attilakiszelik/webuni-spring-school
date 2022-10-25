package hu.kaev.school.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.kaev.school.dto.StudentDto;
import hu.kaev.school.mapper.StudentMapper;
import hu.kaev.school.model.Student;
import hu.kaev.school.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/students")
public class StudentController {

	private final StudentRepository studentRepository;
	private final StudentMapper studentMapper;
	
	@GetMapping("/{id}")
	public StudentDto findbyId(@PathVariable long id) {
		
		Student student = studentRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		return studentMapper.studentSummaryToDto(student);
		
	}

}
