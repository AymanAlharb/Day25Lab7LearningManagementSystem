package com.example.day25lab7learningmanagementsystem.Controller;

import com.example.day25lab7learningmanagementsystem.Api.ApiResponse;
import com.example.day25lab7learningmanagementsystem.Model.Course;
import com.example.day25lab7learningmanagementsystem.Model.Student;
import com.example.day25lab7learningmanagementsystem.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService service;

    @PostMapping("/add")
    public ResponseEntity createStudent(@RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        if (service.createStudent(student))
            return ResponseEntity.status(200).body(new ApiResponse("Student added successfully."));
        return ResponseEntity.status(400).body("Student id must be unique.");
    }

    @GetMapping("/get-all")
    public ResponseEntity getStudentByd() {
        return ResponseEntity.status(200).body(service.getAll());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable String id, @RequestBody @Valid Student student, Errors errors) {
        if (errors.hasErrors())
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        if (service.updateStudent(id, student))
            return ResponseEntity.status(200).body(new ApiResponse("Student updated successfully."));
        return ResponseEntity.status(400).body("Student not found.");
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteStudent(@PathVariable String id) {
        if (service.deleteStudent(id))
            return ResponseEntity.status(200).body(new ApiResponse("Student deleted successfully."));
        return ResponseEntity.status(400).body("Student not found.");
    }

    @PutMapping("/add-course/{student_id}/{course_id}/{mark}")
    public ResponseEntity addCourse(@PathVariable String student_id, @PathVariable String course_id, @PathVariable int mark) {
        char status = service.addCourse(student_id, course_id, mark);
        return switch (status) {
            case '0' -> ResponseEntity.status(404).body(new ApiResponse("Student not found."));
            case '1' -> ResponseEntity.status(404).body(new ApiResponse("Course not found."));
            case '2' -> ResponseEntity.status(200).body(new ApiResponse("Student already enrolled in the course."));
            case '3' -> ResponseEntity.status(400).body(new ApiResponse("Student added successfully."));
            default -> ResponseEntity.status(400).body(new ApiResponse("Student not eligible to add the course."));
        };
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getStudentByd(@PathVariable String id){
        Student temoStudent = service.searchStudent(id);
        if(temoStudent == null) return ResponseEntity.status(404).body(new ApiResponse("Student not found"));
        return ResponseEntity.status(200).body(temoStudent);
    }

    @PutMapping("/advance/{id}")
    public ResponseEntity advanceToTheNextLevel(@PathVariable String id){
        char advanceStatus = service.advanceToTheNextLevel(id);
        return switch (advanceStatus){
            case '0' -> ResponseEntity.status(404).body(new ApiResponse("Student not found."));
            case '1' -> ResponseEntity.status(400).body(new ApiResponse("Student not eligible to advance to the next level."));
            default -> ResponseEntity.status(200).body(new ApiResponse("Student advanced to the next level"));
        };
    }

    @GetMapping("/get-student-courses/{id}")
    public ResponseEntity getStudentCourses(@PathVariable String id){
        ArrayList<Course> tempCourseList = service.getStudentCourses(id);
        if(tempCourseList == null) return ResponseEntity.status(404).body(new ApiResponse("Student not found"));
        return ResponseEntity.status(200).body(tempCourseList);
    }
    @GetMapping("/get-avg-mark/{id}")
    public ResponseEntity getSudentAvgMark(@PathVariable String id){
        double possibleAvg = service.getAvgMarks(id);
        if(possibleAvg == -1404) return ResponseEntity.status(404).body(new ApiResponse("Student not found"));
        if(possibleAvg == -2404) return ResponseEntity.status(404).body(new ApiResponse("course not found"));
        return ResponseEntity.status(200).body(possibleAvg);
    }
}
