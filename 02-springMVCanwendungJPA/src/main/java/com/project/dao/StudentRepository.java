package com.project.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	 @Query(value = "SELECT c FROM Student c WHERE c.vorname LIKE '%' || :keyword || '%'"
	            + " OR c.nachname LIKE '%' || :keyword || '%'"
	            + " OR c.email LIKE '%' || :keyword || '%'")
	public List<Student> studentSearch(@Param("keyword")String keyword);

}
