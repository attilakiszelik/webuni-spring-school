package hu.kaev.school.service;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;

import hu.kaev.school.model.Course;
import hu.kaev.school.model.QCourse;
import hu.kaev.school.repository.CourseRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CourseService {

	private final CourseRepository courseRepository;

	@Transactional
	public List<Course> findAll(@QuerydslPredicate(root=Course.class) Predicate predicate) {
		return (List<Course>) courseRepository.findAll(predicate);
	}
	
	@Transactional
	public List<Course> search(Predicate predicate) {

		List<Course> courses = courseRepository.findAll(predicate, "Course.students", EntityGraphType.LOAD);
		courses = courseRepository.findAll(QCourse.course.in(courses), "Course.teachers", EntityGraphType.LOAD);		
		return courses;
		
	}	
	
}
