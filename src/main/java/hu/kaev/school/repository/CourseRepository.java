package hu.kaev.school.repository;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import com.querydsl.core.types.Predicate;

import hu.kaev.school.model.Course;
import hu.kaev.school.model.QCourse;

public interface CourseRepository extends JpaRepository<Course, Long>,
										  QuerydslPredicateExecutor<Course>,
										  QuerydslBinderCustomizer<QCourse>,
										  QuerydslWithEntityGraphRepository<Course, Long>{
	
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
	
}