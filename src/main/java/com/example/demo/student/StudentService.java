package com.example.demo.student;

import jakarta.transaction.Transactional;
import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return this.studentRepository.findAll();
    }

    public void addStudent(Student student) {
        Optional<Student> studentOptional = this.studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Student already exists");
        }
        this.studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        if (!this.studentRepository.existsById(id)) {
            throw new IllegalStateException("Student does not exist");
        }
        this.studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long studentId, String newFName) {
        Optional<Student> student = this.studentRepository.findById(studentId);
        if (student.isEmpty()) {
            throw new IllegalStateException("Student does not exist");
        }
        if (newFName != null && !newFName.isEmpty() && !student.get().getFirstName().equals(newFName)) {
            student.get().setFirstName(newFName);
        }
    }
}
