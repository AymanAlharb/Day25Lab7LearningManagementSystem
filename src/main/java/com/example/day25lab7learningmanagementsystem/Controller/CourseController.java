package com.example.day25lab7learningmanagementsystem.Controller;

import com.example.day25lab7learningmanagementsystem.Api.ApiResponse;
import com.example.day25lab7learningmanagementsystem.Model.Course;
import com.example.day25lab7learningmanagementsystem.Model.Student;
import com.example.day25lab7learningmanagementsystem.Service.CourseService;
import com.example.day25lab7learningmanagementsystem.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final StudentService studentService;
    @PostMapping("/add")
    public ResponseEntity createCourse(@RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        if (courseService.createCourse(course))
            return ResponseEntity.status(200).body(new ApiResponse("Course added successfully."));
        return ResponseEntity.status(400).body("Course id must be unique.");
    }

    @GetMapping("/get-all")
    public ResponseEntity getCourse() {
        return ResponseEntity.status(200).body(courseService.getAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCourse(@PathVariable String id, @RequestBody @Valid Course course, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        if (courseService.updateCourse(id, course))
            return ResponseEntity.status(200).body(new ApiResponse("Course updated successfully."));
        return ResponseEntity.status(400).body("Course not found.");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteCourse(@PathVariable String id) {
        if (courseService.deleteCourse(id))
            return ResponseEntity.status(200).body(new ApiResponse("Course deleted successfully."));
        return ResponseEntity.status(400).body("Course not found.");
    }

    @GetMapping("/get-course-students/{id}")
    public ResponseEntity getCourseStudents(@PathVariable String id){
        ArrayList<Student> students = studentService.getStudents(id);
        if(students == null) return ResponseEntity.status(404).body(new ApiResponse("Course does not exists."));
        return ResponseEntity.status(200).body(students);

    }

    @GetMapping("/get-avg-mark/{id}")
    public ResponseEntity getSudentAvgMark(@PathVariable String id){
        double possibleAvg = courseService.getAvgMarks(id);
        if(possibleAvg == -1404) return ResponseEntity.status(404).body(new ApiResponse("Course not found"));
        return ResponseEntity.status(200).body(possibleAvg);
    }
}
