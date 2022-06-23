package models;

public class Subject {
    private String subjectId;
    private String title;
    private String description;
    private Assessment studentResult;

    public Subject(String subjectId, String title, String description, Assessment studentResult) {
        this.subjectId = subjectId;
        this.title = title;
        this.description = description;
        this.studentResult = studentResult;
    }
}
