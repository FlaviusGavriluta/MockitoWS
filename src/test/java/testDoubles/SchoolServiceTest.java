package testDoubles;

import daos.ModuleRepository;
import daos.StudentRepository;
import models.Student;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import services.SchoolService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class SchoolServiceTest {
    @Test
    public void fakeExample_FindNumberOfStudents(){
        StudentRepository fakeStudentRepository = mock(StudentRepository.class);
        SchoolService schoolService = new SchoolService(fakeStudentRepository);

        Student student1 = new Student("cc11", "Ion",
                LocalDate.of(1999, 6, 22), LocalDate.of(2022, 6, 22));
        Student student2 = new Student("cc12", "Petru",
                LocalDate.of(1990, 6, 22), LocalDate.of(2022, 7, 12));
        List<Student> studentList = Arrays.asList(student1,student2);

        when(fakeStudentRepository.getAll()).thenReturn(studentList);

        assertEquals(2, schoolService.findNumberOfStudents());
    }

    @Test
    public void dummyExample_FindNumberOfStudents() {
        StudentRepository fakeStudentRepository = mock(StudentRepository.class);
        ModuleRepository dummyModuleRepository = mock(ModuleRepository.class);
        SchoolService schoolService = new SchoolService(fakeStudentRepository, dummyModuleRepository);

        Student student1 = new Student("cc11", "Ion",
                LocalDate.of(1999, 6, 22), LocalDate.of(2022, 6, 22));
        Student student2 = new Student("cc12", "Petru",
                LocalDate.of(1990, 6, 22), LocalDate.of(2022, 7, 12));
        List<Student> studentList = Arrays.asList(student1,student2);

        when(fakeStudentRepository.getAll()).thenReturn(studentList);

        assertEquals(2, schoolService.findNumberOfStudents());
    }

    @Test
    public void stubExample_GetNewStudentsWithAppliedDiscount() {
        StudentRepository stubStudentRepository = mock(StudentRepository.class);
        ModuleRepository dummyModuleRepository = mock(ModuleRepository.class);
        SchoolService schoolService = new SchoolService(stubStudentRepository, dummyModuleRepository);

        Student student1 = new Student("cc11", "Ion",
                LocalDate.of(1999, 6, 22), LocalDate.of(2022, 6, 22));
        Student student2 = new Student("cc12", "Petru",
                LocalDate.of(1990, 6, 22), LocalDate.of(2022, 7, 12));
        List<Student> studentList = Arrays.asList(student1,student2);

        when(stubStudentRepository.findRecentEnrolledStudents(60)).thenReturn(studentList);

        List<Student> studentsWithDiscount = schoolService
                .getNewStudentsWithAppliedDiscount(60, 100);

        assertEquals(2, studentsWithDiscount.size());
        assertEquals(4_900, studentsWithDiscount.get(0).getCourseFee());
    }

    @Test
    public void spyExample_AddStudent() {
        StudentRepository spyStudentRepository = spy(StudentRepository.class);
        ModuleRepository dummyModuleRepository = mock(ModuleRepository.class);
        SchoolService schoolService = new SchoolService(spyStudentRepository, dummyModuleRepository);


        Student student1 = new Student("cc11", "Ion",
                LocalDate.of(2008, 6, 22), LocalDate.of(2022, 6, 22));
        Student student2 = new Student("cc12", "Petru",
                LocalDate.of(1990, 6, 22), LocalDate.of(2022, 7, 12));

        schoolService.addStudent(student1);
        schoolService.addStudent(student2);

        verify(spyStudentRepository, times(0)).save(student1);
        verify(spyStudentRepository, times(1)).save(student2);
    }

    @Test
    public void mockExample_UpdateStudent() {
        StudentRepository mockStudentRepository = mock(StudentRepository.class);
        ModuleRepository dummyModuleRepository = mock(ModuleRepository.class);
        SchoolService schoolService = new SchoolService(mockStudentRepository, dummyModuleRepository);


        Student student1 = new Student("cc11", "Ion",
                LocalDate.of(2000, 6, 22), LocalDate.of(2022, 6, 22));

        when(mockStudentRepository.findStudent("cc11")).thenReturn(student1);

        schoolService.updateStudentName("cc11", "Petru");

        InOrder inOrder = inOrder(mockStudentRepository);
        inOrder.verify(mockStudentRepository).findStudent("cc11");
        inOrder.verify(mockStudentRepository).save(student1);

        verify(mockStudentRepository, atMost(1)).save(student1);
        verifyNoMoreInteractions(mockStudentRepository);
    }
}
