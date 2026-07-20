package org.demo.service;

import org.demo.entity.Student;
import org.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> readAllStudents() {
        return studentRepository.findAll();
    }

    public Student readStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student updateStudentById(Long id, Student student) {
        Student existingStudent = readStudentById(id);

        existingStudent.setName(student.getName());
        existingStudent.setEmail(student.getEmail());
        existingStudent.setAge(student.getAge());
        existingStudent.setSubject(student.getSubject());
        existingStudent.setRollNo(student.getRollNo());

        return studentRepository.save(existingStudent);
    }

    public Student deleteStudentById(Long id) {
        Student student = readStudentById(id);
        if (student != null) {
            studentRepository.delete(student);
            return student;
        }
        return null;
    }

    public void deleteAllStudents() {
        studentRepository.deleteAll();
    }
}