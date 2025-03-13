package com.example.day25lab7learningmanagementsystem.Service;
import com.example.day25lab7learningmanagementsystem.Service.StudentService;
import com.example.day25lab7learningmanagementsystem.Model.Course;
import com.example.day25lab7learningmanagementsystem.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CourseService {
    ArrayList<Course> courses = new ArrayList<>();

    public boolean createCourse(Course course) {
        if (searchCourse(course.getId()) != null) return false;
        courses.add(course);
        return true;
    }

    public ArrayList<Course> getAll() {
        return courses;
    }

    public boolean updateCourse(String id, Course course) {
        Course tempCourse = searchCourse(id);
        if (tempCourse == null) return false;
        courses.set(courses.indexOf(tempCourse), course);
        return true;
    }

    public boolean deleteCourse(String id) {
        Course tempCourse = searchCourse(id);
        if (tempCourse == null) return false;
        courses.remove(tempCourse);
        return true;
    }

    public double getAvgMarks(String id) {
        Course tempCourse = searchCourse(id);
        if (tempCourse == null) return -1404;
        double sum = 0;
        for (int mark : tempCourse.getMarks()) sum += mark;
        return sum / tempCourse.getMarks().size();
    }

    public Course searchCourse(String id) {
        for (Course course : courses) if (course.getId().equalsIgnoreCase(id)) return course;
        return null;
    }

}
