/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShenendoahU;

import java.util.ArrayList;
import java.util.Iterator;

public class Course {
    //Non-Static Variable Declaration
    private String courseName;
    private String courseBuilding;
    private String courseBldgRoom;
    private int courseCapacity;
    private int courseID;
    private Instructor courseInstructor;
    private ArrayList<Student> enrolledStudents;    
    
    //Static Variable Initialization
    private static int nextCourseID = 1;
    
    //Creates unpopulated Course instance which intializes courseID and enrolledStudents
    
    public Course(String courseName, String courseBuilding, String courseBldgRoom, int courseCapacity)
    {
        this.courseName = courseName;
        this.courseBuilding = courseBuilding;
        this.courseBldgRoom = courseBldgRoom;
        this.courseCapacity = courseCapacity; 
        this.enrolledStudents = new ArrayList<Student>();
        
        this.courseID = nextCourseID;
        nextCourseID++;
    }
           
    public void enrollStudent(Student newStudent){
        enrolledStudents.add(newStudent);
    }
    
    public boolean enrollStudentWithCheck(Student newStudent)
    {
        if(enrolledStudents.size() < courseCapacity)
        {
            enrolledStudents.add(newStudent);
            return true;
        }
        else
            return false;
    }
    
    public boolean removeStudent(int studentID)
    {
        boolean result = false;    
        Iterator<Student> i = this.enrolledStudents.iterator();
        while(i.hasNext())
        {
            Student stu = i.next();
            if(stu.getStudentID() == studentID){ //if student ID matches, removes that stu instance and returns true
                i.remove();
                result = true;
            }
        }
        
        return result;
    }
    
    public void assignInstructor(Instructor newInstructor)
    {
        this.courseInstructor = newInstructor;
    }
    
    public void setCourseBldgRoom(String courseBldgRoom)
    {
        this.courseBldgRoom = courseBldgRoom;
    }
    
    public void setCourseBuilding(String courseBuilding)
    {
        this.courseBuilding = courseBuilding;
    }
    
    public void setCapacity(int courseCapacity)
    {
        this.courseCapacity = courseCapacity;
        this.enrolledStudents.ensureCapacity(courseCapacity);
    }
    
    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }
    
    public int getCourseID()
    {
        return this.courseID;
    }
    
    //Returns string containing info about a given course, if an Instructor is assigned to the course
    //It adds instructor title to the output, otherwise it shows "No Instructor Assigned"
    public String toString()
    {
        if(this.courseInstructor == null)
        {
            return String.format("Course# %d Course: %-10s Location: %-10s Room: %-5s Capacity: %d "
                    + "Instructor of Record: No Instructor Assigned", 
                    this.courseID,this.courseName,this.courseBuilding,this.courseBldgRoom,this.courseCapacity);
        }
        else
            return String.format("Course# %d Course: %-10s Location: %-10s Room: %-5s Capacity: %d "
                    + "Instructor of Record: " + this.courseInstructor.getTitle(), 
                    this.courseID,this.courseName,this.courseBuilding,this.courseBldgRoom,this.courseCapacity);
    }
           
    public String getRoster()
    {
        String roster = "";
        for(Student stu : this.enrolledStudents)
        {            
            roster += String.format("%nStudent ID: %d Name: %-25s Major: %-10s Year: %s",
                stu.getStudentID(), stu.getName(), stu.getStudentMajor(),stu.getStudentYear());
        }               
        return roster;
    }
            
    public boolean printRoster()
    {
        if (this.enrolledStudents.size() == 0)
        {
            System.out.println("\nNo Students Enrolled in Course\n");
            return false;
        }
        else
            System.out.println(this.getRoster());
            return true;
    }
}
