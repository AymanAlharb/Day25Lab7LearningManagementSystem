package com.example.day25lab7learningmanagementsystem.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Student {
    @NotEmpty(message = "The id can not be empty.")
    @Size(min = 10, max = 10, message = "The id must be 10 digits long.")
    private String id;
    @NotEmpty(message = "The first name can not be empty.")
    private String first_name;
    @NotEmpty(message = "The last name can not be empty.")
    private String last_name;
    @NotEmpty(message = "The phone number can not be empty.")
    @Pattern(regexp = "05[0-9]{8}")
    private String phone_number;
    @NotNull(message = "The student level can not be null.")
    @Min(value = 1, message = "The minimum level is 1.")
    @Max(value = 6, message = "The maximum level is 6.")
    private int student_level;
    @Email
    private String email;
    @NotNull(message = "Courses can not be null.")
    private ArrayList<String> student_courses;
    @NotNull(message = "Marks can not be null.")
    private ArrayList<Integer> marks;

}
