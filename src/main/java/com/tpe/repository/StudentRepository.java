package com.tpe.repository;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository//opsiyonel
public interface StudentRepository extends JpaRepository<Student, Long> {
    //JpaRepositorydeki metodlar Spring tarafından otomatik olarak implemente edilir
    boolean existsByEmail(String email);

    //14-a
    List<Student> findAllByGrade(Integer grade);//select * from student where grade=100

    //14-b
    //JPQL:javaca
    @Query("FROM Student WHERE grade=:pGrade")
    List<Student> filterStudentsByGrade(@Param("pGrade") Integer grade);

    //14-c
    //SQL
    @Query(value = "SELECT * FROM student WHERE grade=:pGrade", nativeQuery = true)
    List<Student> filterStudentsByGradeSQL(@Param("pGrade") Integer grade);

    //18-JPQL ile tablodan gelen entity objesi DTO nun constructorı ile
    // DTO objesine dönüştürülür
    @Query("SELECT new com.tpe.dto.StudentDTO(s) FROM Student s WHERE id=:pId")
    Optional<StudentDTO> findStudentDTOById(@Param("pId") Long id);




}