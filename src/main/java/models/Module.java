package models;

import java.util.List;

public class Module {
    private String courseId;
    private String name;
    private List<Subject> subjects;

    public Module(String courseId, String name, List<Subject> subjects) {
        this.courseId = courseId;
        this.name = name;
        this.subjects = subjects;
    }
}
