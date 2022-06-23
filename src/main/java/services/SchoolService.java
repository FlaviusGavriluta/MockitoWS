package services;

import daos.StudentRepository;
import models.Student;

public class SchoolService {
    private final StudentRepository studentRepository;

    public SchoolService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public int findNumberOfStudents(){
        return studentRepository.getAll().size();
    }
}
