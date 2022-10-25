package hu.kaev.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.kaev.school.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{
	
}