package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private final String studentId;
    private String name;

    private LocalDate birthDate;
    private LocalDate enrollment;
    private double courseFee;
    private Module currentModule;
    private List<Module> moduleHistory;

    public Student(String studentId, String name, LocalDate birthDate, LocalDate enrollment, Module currentModule) {
        this.studentId = studentId;
        this.name = name;
        this.birthDate = birthDate;
        this.enrollment = enrollment;
        this.currentModule = currentModule;
        this.moduleHistory = new ArrayList<>();
        this.courseFee = 5_000;
    }

    public Student(String studentId, String name, LocalDate birthDate, LocalDate enrollment) {
        this.studentId = studentId;
        this.name = name;
        this.birthDate = birthDate;
        this.enrollment = enrollment;
        this.courseFee = 5_000;
    }

    public LocalDate getEnrollment() {
        return enrollment;
    }

    public double getCourseFee() {
        return courseFee;
    }

    public void setCourseFee(double courseFee) {
        this.courseFee = courseFee;
    }

    public void setCurrentModule(Module currentModule) {
        this.currentModule = currentModule;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setEnrollment(LocalDate enrollment) {
        this.enrollment = enrollment;
    }

    public void updateModuleHistory() {
        moduleHistory.add(currentModule);
    }

    public List<Module> getModuleHistory() {
        return moduleHistory;
    }
}
