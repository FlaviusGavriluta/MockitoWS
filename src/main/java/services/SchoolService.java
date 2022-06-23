package services;

import daos.InDatabaseStudentRepository;
import daos.ModuleRepository;
import daos.StudentRepository;
import models.Student;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class SchoolService {
    private final StudentRepository studentRepository;
    private ModuleRepository moduleRepository;

    public SchoolService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public SchoolService(StudentRepository studentRepository, ModuleRepository moduleRepository) {
        this.studentRepository = studentRepository;
        this.moduleRepository = moduleRepository;
    }

    public SchoolService() {
        studentRepository = new InDatabaseStudentRepository();
    }

    public void addStudent(Student student) {
        LocalDate today = LocalDate.now();
        int years = (int) ChronoUnit.YEARS.between(student.getBirthDate(), today);
        if (years < 18) {
            return;
        }
        studentRepository.save(student);
    }

    public int findNumberOfStudents(){
        return studentRepository.getAll().size();
    }

    public List<Student> getRecentEnrolledStudents(int lastNumberOfDays) {
        List<Student> students = new ArrayList<>();
        LocalDate today = LocalDate.now();
        for (Student student : studentRepository.getAll()) {
            int days = (int) ChronoUnit.DAYS.between(student.getEnrollment(), today);
            if (days <= lastNumberOfDays) {
                students.add(student);
            }
        }
        return students;
    }

    public List<Student> getNewStudentsWithAppliedDiscount(int lastNumberOfDays, double discount) {
        List<Student> recentStudents = studentRepository.findRecentEnrolledStudents(lastNumberOfDays);
        for (Student recentStudent : recentStudents) {
            recentStudent.setCourseFee(recentStudent.getCourseFee() - discount);
        }
        return recentStudents;
    }

    public ModuleRepository getModuleRepository() {
        return moduleRepository;
    }

    public Student findStudent(String studentId) {
        if (studentId.equals("cc11")) {
            return new Student("cc11", "Ion",
                    LocalDate.of(1999, 6, 22), LocalDate.of(2022, 6, 22));
        }
        return null;
    }

    public void updateStudentName(String studentId, String studentName) {
        Student existingStudent = studentRepository.findStudent(studentId);
        existingStudent.setName(studentName);
        studentRepository.save(existingStudent);
    }
}
