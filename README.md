# Course Model
The course model has id, course_name, course_level (level goes from 1 to 6, each level represent a semester level 1 means semester 1), course_teacher, course_students (ArrayList of String storing the students ids), marks (arrayList of integers storing the marks of the students enrolled in the course)
## CourseService
### searchCourse: 
This method take the id of a course and loops through the courses arrayList while comparing the givin id with the ids of the courses, if found return the an object of the course, otherwise return null.
### getAvgMarks:
This method take the id of a course, using the id and the searchCourse initlize an object of course, if the object equals null return -404 (which means course not found) otherwise loops through the marks list within the temp object and sum the marks than return the sum over the total number of marks.
# Student Model
The student model has id, first_name, last_name, phone_number, Student_level (same logic as the course_level), email, student_courses, marks.
## StudentService
### searchStudent:
This method take the id of a student and loops through the students arrayList while comparing the givin id with the ids of the students, if found return the an object of the course, otherwise return null.
### addCourse
This method take the id of a stuent, the id of a course and a mark, than initlize an object of Student using the givin id and the searchStudent method than if the object equal null (student not found) return '0'
If the student found than do the same for the course.
If both the student and the course found than check if the student already enrolled in the course if yes retudn '2'.
If the student not enrolled in the course add the course id to the student courses, add the student id to the course students and add the mark to both the student and the course.
### advanceToTheNextLevel
This method takes the id of a student make the obj and ... 
If the student found than loop through his marks and check if he passed (all marks above 60) if a mark bellow 60 return '1' student failed.
else advance the student, clear his marks and courses than return '2' which means that the student passed.
### getStudents
* This method is for the course controller to solve the problem of circular injection.
  This methid the the id of a student create an object than if the student found, create a temp student List, than loop through the ids of the student in the coures_students list and find the student using the searchStudent method than push the object to the temp list. 
