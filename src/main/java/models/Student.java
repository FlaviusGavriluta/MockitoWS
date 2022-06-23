package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Student {
    private final String studentId;
    private final String name;
    private final LocalDate birthDate;
    private Module currentModule;
    private List<Module> moduleHistory;

    public Student(String studentId, String name, LocalDate birthDate, Module currentModule) {
        this.studentId = studentId;
        this.name = name;
        this.birthDate = birthDate;
        this.currentModule = currentModule;
        this.moduleHistory = new ArrayList<>();
    }

}
