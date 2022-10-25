package hu.kaev.school.repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import hu.kaev.school.model.Course;
import hu.kaev.school.model.QCourse;

public interface CourseRepository extends JpaRepository<Course, Long>,
										  JpaSpecificationExecutor<Course>,
										  QuerydslPredicateExecutor<Course>,
										  QuerydslBinderCustomizer<QCourse>{
	
	@Override
	default void customize(QuerydslBindings bindings, QCourse course) {
		
		bindings.bind(course.name).first((path, value)->path.startsWithIgnoreCase(value));
		
		bindings.bind(course.id).first((path, value)->path.eq(value));
		
		bindings.bind(course.teachers.any().name).first((path, value)->path.startsWithIgnoreCase(value));
		
		bindings.bind(course.students.any().id).first((path, value)->path.eq(value));
		
		bindings.bind(course.students.any().semester).all((path, values)->{
			
			if(values.size()!= 2)
				return Optional.empty();
			
			Iterator<? extends Integer> iterator= values.iterator();
			Integer start = iterator.next();
			Integer end = iterator.next();

			return Optional.of(path.between(start, end));
			
		});
		
	}

	@EntityGraph(attributePaths= {"teachers"})
	@Query("SELECT c FROM Course c WHERE c.id IN :ids")
	List<Course> findByIdWithTeachers(List<Long> ids);
	
	@EntityGraph(attributePaths= {"students"})
	@Query("SELECT c FROM Course c WHERE c.id IN :ids")
	List<Course> findByIdWithStudents(List<Long> ids, Sort sort);
	
}