package hu.kaev.school.web;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.querydsl.core.types.Predicate;

import hu.kaev.school.dto.CourseDto;
import hu.kaev.school.mapper.CourseMapper;
import hu.kaev.school.model.Course;
import hu.kaev.school.repository.CourseRepository;
import hu.kaev.school.service.CourseService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/courses")
public class CourseController {

	private final CourseService courseService;
	private final CourseRepository courseRepository;
	private final CourseMapper courseMapper;

	@GetMapping("/search")
	public List<CourseDto> search(@QuerydslPredicate(root=Course.class) Predicate predicate, @RequestParam Optional<Boolean> full, Pageable pageable) {
		
		boolean isFull = full.orElse(false);
		
		List<Course> courses = courseService.search(predicate);
				               
		return isFull
			   ? courseMapper.coursesToDtos(courses)
			   : courseMapper.courseSummariesToDtos(courses);
		
	}
	
	@GetMapping("/{id}")
	public CourseDto findbyId(@PathVariable long id) {
		
		Course course = courseRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
		return courseMapper.courseSummaryToDto(course);
		
	}

}
