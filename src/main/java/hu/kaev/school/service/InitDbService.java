package hu.kaev.school.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.kaev.school.model.Course;
import hu.kaev.school.model.Student;
import hu.kaev.school.model.Teacher;
import hu.kaev.school.repository.CourseRepository;
import hu.kaev.school.repository.StudentRepository;
import hu.kaev.school.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class InitDbService {

	private final CourseRepository courseRepository;
	private final TeacherRepository teacherRepository;
	private final StudentRepository studentRepository;	
	
	@Transactional
	public void deleteDb() {
		
		courseRepository.deleteAll();
		teacherRepository.deleteAll();
		studentRepository.deleteAll();
		
	}
	
	@Transactional
	public void addInitData() {
		
		Student student1 = studentRepository.save(Student.builder().name("Tanuló 1").birthdate(LocalDate.of(2000, 1, 1)).build());
		Student student2 = studentRepository.save(Student.builder().name("Tanuló 2").birthdate(LocalDate.of(2000, 1, 1)).build());
		Student student3 = studentRepository.save(Student.builder().name("Tanuló 3").birthdate(LocalDate.of(2000, 1, 1)).build());

		Teacher teacher1 = teacherRepository.save(Teacher.builder().name("Tanár 1").birthdate(LocalDate.of(2000, 1, 1)).build());
		Teacher teacher2 = teacherRepository.save(Teacher.builder().name("Tanár 2").birthdate(LocalDate.of(2000, 1, 1)).build());
		Teacher teacher3 = teacherRepository.save(Teacher.builder().name("Tanár 3").birthdate(LocalDate.of(2000, 1, 1)).build());
		
		Course course1 = courseRepository.save(Course.builder().name("Képzés 12").build());
			Set<Teacher> teachers12 = new HashSet<>();
			teachers12.add(teacher1);
			teachers12.add(teacher2);
			course1.setTeachers(teachers12);
			Set<Student> students12 = new HashSet<>();
			students12.add(student1);
			students12.add(student2);
			course1.setStudents(students12);
			courseRepository.save(course1);
		Course course2 = courseRepository.save(Course.builder().name("Képzés 13").build());
			Set<Teacher> teachers13 = new HashSet<>();
			teachers12.add(teacher1);
			teachers12.add(teacher3);
			course2.setTeachers(teachers13);
			Set<Student> students13 = new HashSet<>();
			students12.add(student1);
			students12.add(student3);
			course2.setStudents(students13);
			courseRepository.save(course2);
		Course course3 = courseRepository.save(Course.builder().name("Képzés 23").build());
			Set<Teacher> teachers23 = new HashSet<>();
			teachers23.add(teacher2);
			teachers23.add(teacher3);
			course3.setTeachers(teachers23);
			Set<Student> students23 = new HashSet<>();
			students23.add(student2);
			students23.add(student3);
			course3.setStudents(students23);
			courseRepository.save(course3);
		
	}

}
