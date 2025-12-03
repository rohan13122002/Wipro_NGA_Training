package com.jsp.hibernate.Hibernate02;

import com.sun.tools.javac.util.List;

public class App 
{
    public static void main( String[] args )
    {
        Student s1 = new Student();
        s1.setStudentId(10);
        s1.setStudentName(ABC);
        
        Course c1 = new Course();
        c1.setCourseId(1);
        c1.setCourseName(Java);
        c1.setCourseFee(27500);
        
        Student s2 = new Student();
        s2.setStudentId(10);
        s2.setStudentName(XYZ);
        
        Course c2 = new Course();
        c2.setCourseId(2);
        c2.setCourseName(Python);
        c2.setCourseFee(20000);
        
        
        //Adding course into student
        
        List<Course> clist = new ArrayList<Course>();
        clist.add(c1);
        clist.add(c2);
        
        s1.setCourses(clist);
        s2.setCourses(clist);
        
        
    }
}
