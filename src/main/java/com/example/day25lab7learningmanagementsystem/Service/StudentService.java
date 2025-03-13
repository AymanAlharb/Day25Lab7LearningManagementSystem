package com.example.day25lab7learningmanagementsystem.Service;

import com.example.day25lab7learningmanagementsystem.Model.Course;
import com.example.day25lab7learningmanagementsystem.Model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final CourseService courseService;
    ArrayList<Student> students = new ArrayList<>();

    public boolean createStudent(Student student) {
        if (searchStudent(student.getId()) != null) return false;
        students.add(student);
        return true;
    }

    public ArrayList<Student> getAll() {
        return students;
    }

    public boolean updateStudent(String id, Student student) {
        Student tempStudent = searchStudent(id);
        if (tempStudent == null) return false;
        students.set(students.indexOf(tempStudent), student);
        return true;
    }

    public boolean deleteStudent(String id) {
        Student tempStudent = searchStudent(id);
        if (tempStudent == null) return false;
        students.remove(tempStudent);
        return true;
    }

    public char addCourse(String student_id, String course_id, int mark) {
        Student tempStudent = searchStudent(student_id);
        if (tempStudent == null) return '0';
        Course tempCourse = courseService.searchCourse(course_id);
        if (tempCourse == null) return '1';
        for (String ID : tempCourse.getCourse_students()) if(ID.equalsIgnoreCase(student_id)) return '2';
        if (tempStudent.getStudent_level() >= tempCourse.getCourse_level()) {
            tempStudent.getStudent_courses().add(tempCourse.getId());
            tempStudent.getMarks().add(mark);
            tempCourse.getCourse_students().add(tempStudent.getId());
            tempCourse.getMarks().add(mark);
            return '3';
        }
        return '4';
    }

    public char advanceToTheNextLevel(String id) {
        Student tempStudent = searchStudent(id);
        if(tempStudent == null) return '0';
        for (int mark : tempStudent.getMarks()) if (mark < 60) return '1';
        tempStudent.setStudent_level(tempStudent.getStudent_level() + 1);
        tempStudent.getMarks().clear();
        return '2';
    }

    public ArrayList<Student> getStudents(String id) {
        Course tempCourse = courseService.searchCourse(id);
        if (tempCourse == null) return null;
        ArrayList<Student> tmpStudentsList = new ArrayList<>();
        for (String ID : tempCourse.getCourse_students()) tmpStudentsList.add(searchStudent(ID));
            return tmpStudentsList;
    }

    public double getAvgMarks(String id) {
        Student tempStudent = searchStudent(id);
        if (tempStudent == null) return -1404;
        if(tempStudent.getMarks().isEmpty()) return -2404;
        double sum = 0;
        for (int mark : tempStudent.getMarks()) sum+=mark;
        return sum/tempStudent.getMarks().size();
    }

    public ArrayList<Course> getStudentCourses(String id){
        Student tempStudent  = searchStudent(id);
        if(tempStudent == null) return null;
        ArrayList<Course> tempCourseList = new ArrayList<>();
        for (String ID : tempStudent.getStudent_courses()) tempCourseList.add(courseService.searchCourse(ID));
        return tempCourseList;
    }
    public Student searchStudent(String id) {
        for (Student student : students) if (student.getId().equalsIgnoreCase(id)) return student;
        return null;
    }
}
