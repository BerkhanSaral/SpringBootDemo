package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.dto.UpdateStudentDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

        if (repository.existsByEmail(student.getEmail())) {
            //bu email daha önce kullanılmış-->hata fırlatalım
            throw new ConflictException("Email already exists!");
        }
        repository.save(student);//Insert into....

    }

    //6-id si verilen öğrenciyi bulma
    public Student getStudentById(Long id) {
        Student student = repository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Student is not found by ID : " + id));

        return student;
    }

    public void deleteStudentById(Long id) {

        //repository.deleteById(id);//delete from student where id=9999;
        //bu idye sahip bir ogrenci (satir) yoksa ??
        //ozel bir mesaj ile ozel bir exception firlatmak istiyouz

        //once idsi verilen ogrenciyi bulalim
        Student student = getStudentById(id);
        repository.delete(student);


    }

    //10=idsi verilen ogrencinin name,lastName ve email degistirme
    public void updateStudent(Long id, UpdateStudentDTO studentDTO) {//email:harry@mail.com

        Student foundStudent = getStudentById(id); //1,"Jack",  "Sparrow","jack@mail.com"

        //DTO gelen email        tablodaki email
        //1-xxx@mail.com         YOK   V (existByEmail:false) -->update
        //2-harry@mail.com       idsi:2 olan ogrencinin maili X(existByEmail:true) -->ConflictException
        boolean existaEmail = repository.existsByEmail(studentDTO.getEmail());
        if (existaEmail && !foundStudent.getEmail().equals(studentDTO.getEmail())) {
            //cakisma var
            throw new ConflictException("Email already exists ");
        }


        foundStudent.setName(studentDTO.getName());
        foundStudent.setLastName(studentDTO.getLastName());
        foundStudent.setEmail(studentDTO.getEmail());

        repository.save(foundStudent);//saveOrUpdate gibi calisiyor.

    }

    //12-tablodaki tum kayilardan istenen ogrenci sayfasini getirme
    public Page<Student> getAllStudentsPaging(Pageable pageable) {

        return repository.findAll(pageable);
        //istenen bilgiler verilirse tum kayitlardan sadece ilgili sayfayi getirir
        //istenen bilgileri pageable ile toplu olarak verebiliriz:sayfaNo,
        //                                                        her sayfada kayit sayisi
        //                                                        siralalam bilgisi(hangi ozellik, hangi yonde)




    }
}