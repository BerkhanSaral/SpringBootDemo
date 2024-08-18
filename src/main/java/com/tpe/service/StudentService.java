package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor//constructor injection
public class StudentService {

    private final StudentRepository repository;

    //2-tüm kayıtları listeleme
    public List<Student> getAllStudents() {
        return repository.findAll();// "FROM Student"
    }

    //4-öğrenciyi kaydetme
    public void saveStudent(Student student) {

        //student daha önce tabloya eklenmiş mi : tabloda aynı emaile sahip başka student var mı?
        //SELECT * FROM student WHERE email=student.getEmail()-->t/f

        if (repository.existsByEmail(student.getEmail())){
            //bu email daha önce kullanılmış-->hata fırlatalım
            throw new ConflictException("Email already exists!");
        }
        repository.save(student);//Insert into....

    }

    //6-id si verilen öğrenciyi bulma
    public Student getStudentById(Long id) {
        Student student=repository.findById(id).
                orElseThrow(()->new ResourceNotFoundException("Student is not found by ID : "+id));

        return student;
    }
}