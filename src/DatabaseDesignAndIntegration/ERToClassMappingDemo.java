package DatabaseDesignAndIntegration;

import java.util.*;

class Student {

    int studentId;
    String name;

    List<Course> courses = new ArrayList<>();
}

class Course {

    int courseId;
    String courseName;
}

public class ERToClassMappingDemo {

    public static void main(String[] args) {

        Student student = new Student();
        student.studentId = 1;
        student.name = "Nayeem";

        Course java = new Course();
        java.courseId = 101;
        java.courseName = "Java";

        student.courses.add(java);

        System.out.println(student.name +
                " enrolled in " +
                java.courseName);
    }
}