package daos;

import models.Student;

import java.time.LocalDate;
import java.util.List;

public class InDatabaseStudentRepository implements StudentRepository {
    @Override
    public void save(Student student) {

    }

    @Override
    public List<Student> getAll() {
        return null;
    }

    @Override
    public Student findStudent(String studentId) {
        if (studentId.equals("cc11")) {
            return new Student("cc11", "Ion",
                    LocalDate.of(1999, 6, 22), LocalDate.of(2022, 6, 22));
        }
        return null;
    }

    @Override
    public List<Student> findRecentEnrolledStudents(int lastNumberOfDays) {
        return null;
    }
}
