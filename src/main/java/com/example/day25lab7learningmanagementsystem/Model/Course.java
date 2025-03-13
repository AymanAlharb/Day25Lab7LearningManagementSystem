package com.example.day25lab7learningmanagementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Course {
    @NotEmpty(message = "The id can not be empty.")
    @Size(min = 5, max = 5, message = "The id must be 5 digits long.")
    private String id;
    @NotEmpty(message = "The course name can not be empty.")
    private String course_name;
    @NotNull(message = "The course level can not be null.")
    @Min(value = 1, message = "The minimum level is 1.")
    @Max(value = 6, message = "The maximum level is 6.")
    private int course_level;
    @NotEmpty(message = "The course teacher can not be empty.")
    private String course_teacher;
    @NotNull(message = "Course students can not be null.")
    private ArrayList<String> course_students;
    @NotNull(message = "Marks can not be null.")
    private ArrayList<Integer> marks;
}
