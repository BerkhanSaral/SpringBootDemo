package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController//responsebody:metodun dönüş değerini JSON formatında cevap olarak hazırlar
@RequiredArgsConstructor
//bu classta Restful servisler yazılacak,
// requestlere karşılık responselar oluşturulacak
@RequestMapping("/students")//http://localhost:8080/students/.....
public class StudentController {

    /*
    clienttan 3 şekilde data alabiliriz
    1-request body ile JSON formatında
    2-urlde path param
    3-urlde query param
     */


    private final StudentService service;

    //Spring Bootu selamlama requesti:)
    //http://localhost:8080/students/greet + GET
    @GetMapping("/greet")
    public String greet() {
        return "Hello Spring Boot:)";
    }

    //1-tüm öğrencileri listeleyelim : READ
    //Request : http://localhost:8080/students + GET
    //Response: Tüm Öğrencilerin Listesini + 200 : OK (Http Status Kodu)
    @GetMapping
    // @ResponseBody--> RestController içinde var, bu sebeple gerek kalmadı!!!
    public ResponseEntity<List<Student>> listAllStudents() {
        //tablodan tüm öğrencileri getirelim
        List<Student> studentList = service.getAllStudents();
        return new ResponseEntity<>(studentList, HttpStatus.OK);//200
    }
    //Response entity : body + status kodu
    //jackson : objeler --> JSON formatına mapler
    //          JSON formatı --> obje


    //3-öğrenci ekleme : CREATE
    //Request : http://localhost:8080/students + POST + body(JSON)
    /*
    {
     "name":"Jack",
     "lastName":"Sparrow",
     "email":"jack@mail.com",
     "grade":98
    }
     */
    //Response : başarılı mesaj + 201 (CREATED)
    @PostMapping
    public ResponseEntity<String> createStudent(@Valid @RequestBody Student student) {

        service.saveStudent(student);

        return new ResponseEntity<>("Student is created successfully...", HttpStatus.CREATED);//201
    }
    //@RequestBody : requestin bodysini almamızı sağlar
    //jackson:bodydeki JSON formatını --> Student objesine dönüştürüyor


    //5-query param ile id si verilen öğrenciyi getirme
    //request: http://localhost:8080/students/query?id=1 + GET
    //response : student + 200
    @GetMapping("/query")
    public ResponseEntity<Student> getStudent(@RequestParam("id") Long id) {

        Student student = service.getStudentById(id);

        return new ResponseEntity<>(student, HttpStatus.OK);//200
    }

//ÖDEV:(Alternatif)5-path param ile id si verilen öğrenciyi getirme
//request: http://localhost:8080/students/1 + GET
//response : student + 200
}