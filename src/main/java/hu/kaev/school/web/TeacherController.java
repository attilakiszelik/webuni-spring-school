package hu.kaev.school.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import hu.kaev.school.dto.TeacherDto;
import hu.kaev.school.mapper.TeacherMapper;
import hu.kaev.school.model.Teacher;
import hu.kaev.school.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/school/teacher")
public class TeacherController {

	private final TeacherRepository teacherRepository;
	private final TeacherMapper teacherMapper;
	
	@GetMapping("/{id}")
	public TeacherDto findbyId(@PathVariable long id) {
		
		Teacher teacher = teacherRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		return teacherMapper.teacherSummaryToDto(teacher);
		
	}

}
