package hu.kaev.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import hu.kaev.school.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{

}