/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShenendoahU;

import java.util.*;

public class Application {
    
    public static ArrayList<Student> studentArray  = new ArrayList<>();
    public static ArrayList<Course> courseArray  = new ArrayList<>();
    public static ArrayList<Instructor> instructorArray  = new ArrayList<>();

    public static void main(String[] args) 
    {        
        loop: for(;;)
        {            
            switch(mainMenu())
            {
                case 1:
                    createCourse();
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                    removeStudent();
                    break;
                case 4:
                    addInstructor();
                    break;
                case 5:
                    printRoster();
                    break;   
                case 6:
                    System.out.println("Exiting...");
		    break loop;
                default:
                    System.out.println("Invalid Choice, Please Choose Again\n");
                    continue;
            }            
        }
    }
    
    public static int mainMenu()
    {
        int chosenOption = 0;
        String menuOutput = "\nStudent Management System\nPlease Make a Menu Choice Below:"
                + "\n---------------------------------\n1. Create a Course\n2. Add Student a Course"
                + "\n3. Remove Student from a Course\n4. Add Instructor to a Course"
                + "\n5. Print Roster for a Course\n6. Quit\nChoice: ";
        
        System.out.print(menuOutput);
                        
        Scanner in = new Scanner(System.in);
        boolean validInput = false;
        while(!validInput) 
        {
            try 
            {
                chosenOption = in.nextInt();
                validInput = true;
            } 
            catch(InputMismatchException e) 
            {
                System.out.println("Please enter an integer!");
                System.out.print(menuOutput);
                in.next();
            }
        }  
        return chosenOption; 
    }
        
    public static void createCourse()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("\nCreate Class:");
        System.out.println("-----------------");
        
        System.out.print("Enter Course Name: ");
        String courseName = in.nextLine();
        
        System.out.print("Enter Building: ");
        String courseBuilding = in.nextLine();
        
        System.out.print("Enter Room Number: ");
        String courseBldgRoom = in.nextLine();
        
        System.out.print("Enter Room Capacity: ");
        int courseCapacity = 0;
        
        // Error handling when user inputs a string
        boolean validInput = false;
        while(!validInput) 
        {
            try 
            {
                courseCapacity = in.nextInt();
                validInput = true;
            } 
            catch(InputMismatchException e) 
            {
                System.out.println("Please enter an integer!");
                System.out.print("Enter Room Capacity: ");
                in.next();
            }
        }  
        
        Course course = new Course(courseName,courseBuilding,courseBldgRoom,courseCapacity);
        courseArray.add(course);
    }
    
    public static void addStudent()
    {
        Scanner in = new Scanner(System.in);
        
        Student stu = new Student(); //Create new Student instance to be populated with info
        studentArray.add(stu); //Adds Student instance to Student ArrayList
        
        System.out.println("\nEnter Student Information:");
        System.out.println("----------------------------");
        
        System.out.print("Name (Firstname lastname): ");
        stu.setStudentName(in.nextLine().trim());
        
        System.out.print("Major: ");
        stu.setStudentMajor(in.nextLine());
        
        System.out.print("Year: ");
        stu.setStudentYear(in.nextInt());
        
        System.out.print("GPA: ");
        
        //Attempts to add GPA via setGPAWithCheck() method
        //If GPA is between 0 and 4, sets GPA and loop breaks 
        //Otherwise it returns false, prints error message, and restarts loop
        for(;;)
        {
            if(stu.setGPAWithCheck(in.nextDouble()))
                break;
            else
            {
                System.out.print("Invalid GPA Entered. Please Reenter GPA: ");
            }
        }
                
        System.out.print("Email: ");
        
        //Attempts to add email via setStudentEmailWithCheck() method
        //If email contains "@" method returns true and loop breaks 
        //Otherwise it returns false, prints error message, and restarts loop
        in.nextLine();
        for(;;)
        {
            if(stu.setStudentEmailWithCheck(in.nextLine()))
                break;
            else
            {
                System.out.print("Invalid Email Entered. Please Reenter Email: ");
            }
        }
                
        System.out.println("\nPlease Choose a Class:");
        System.out.println("----------------------");
        
        for(Course course : courseArray) //Prints info on all courses
            System.out.println(course.toString());
        
        System.out.println("----------------------");
        System.out.print("Choose Course Number: ");
        
        int choice = in.nextInt();
        
        //Loops through all courses in courseArray and finds course with matching course ID
        //Attempts to add student to selected course, enrollStudentWithCheck() method returns true if there is room
        //And returns false if there isn't room, prints error message if unsuccessful
        for(Course course : courseArray)
        { 
            if(course.getCourseID() == choice) //Finds desired Course instance
            {
                if(course.enrollStudentWithCheck(stu)) 
                {
                    System.out.println("\nStudent Successfully Added!\n");
                    break; 
                }
                else
                {  
                    System.out.println("\nCannot Add Student - Course at Maximum Capacity!\n");
                    break;
                }
            }
        }       
    }
    
    public static void removeStudent()
    {
        //Variable Declaration
        Scanner in = new Scanner(System.in);
        int choice;
        Course course;
        
        System.out.println("\nPlease Choose a Class:");
        System.out.println("----------------------\n");
        
        //Prints info on all courses
        for(Course i : courseArray) 
            System.out.println(i.toString());
        
        System.out.println("----------------------");
        System.out.print("Choose Course Number: ");
        
        //Gets user input for desired course number
        choice = in.nextInt();
        
        System.out.println("\nPrinting Roster...");
        System.out.println("----------------------");
        
        //Iterates through all courses and selects the desired course according to courseID
        for(Course i : courseArray)
        { 
            if(i.getCourseID() == choice) //Finds desired Course instance
            {
                //Calls method which prints roster with proper formatting applied if there are students 
                //enrolled in the course, if not, method returns false and method ends  
                if(i.printRoster())
                {
                    System.out.println("----------------------");
                    System.out.print("Type ID of Student to Remove: ");

                    //Calls removeStudent() method, if successful, method returns true and prints confirmation message
                    if(i.removeStudent(in.nextInt()))
                        System.out.println("\nStudent Successfully Removed\n");
                    else
                        System.out.println("\nStudent Not Found!\n");
                } 
            }
        }             
    }
    
    public static void addInstructor()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("\nEnter Instructor Information:");
        System.out.println("-------------------------------");
        
        System.out.print("Name: ");
        String instructorName = in.nextLine();
        
        System.out.print("Prefix: ");
        String prefix = in.nextLine();
        
        System.out.print("Office: ");
        String officeLocation = in.nextLine();
        
        System.out.print("Department: ");
        String department = in.nextLine();
        
        Instructor instructor = new Instructor(instructorName,prefix,officeLocation,department); //Create new Instructor instance to be populated with info
        instructorArray.add(instructor); //Adds Instructor instance to Instructor ArrayList 
                
        System.out.print("Email: ");
        
        //Attempts to add email via setEmailWithCheck() method
        //If email contains "@" method returns true and loop breaks 
        //Otherwise it returns false, prints error message, and restarts loop
        for(;;)
        {
            if(instructor.setEmailWithCheck(in.nextLine()))
                break;
            else
            {
                System.out.print("Invalid Email Entered. Please Reenter Email: ");
            }
        }

        System.out.println("\nPlease Choose a Class:");
        System.out.println("----------------------");
        
        for(Course course : courseArray) //Prints info on all courses
            System.out.println(course.toString());
        
        System.out.println("----------------------");
        System.out.print("Choose Course Number: ");
        int choice = 0;
        boolean validInput = false;
        
        // Input validation (integer only)
        while(!validInput) 
        {
            try 
            {
                choice = in.nextInt();
                validInput = true;
            } 
            catch(InputMismatchException e) 
            {
                System.out.println("Please enter a valid course number!");
                System.out.print("Choose Course Number: ");
                in.next();
            }
        }
	    
        //Loops through all courses in courseArray and finds course with matching course ID to add instructor
        for(Course course : courseArray)
        { 
            if(course.getCourseID() == choice) //Finds desired Course instance
            {
                course.assignInstructor(instructor);
                System.out.println("\nInstructor Successfully Assigned!\n");
                break;
            }
        }
    }
    
    public static void printRoster()
    {
        Scanner in = new Scanner(System.in);
        
        System.out.println("\nPlease Choose a Class");
        System.out.println("------------------------");
        
        for(Course i : courseArray)
        {
            System.out.println(i.toString());
        }
        
        System.out.print("\nChoose Class: ");
        
	int choice = 0;
        boolean validInput = false;
        
        // Input validation (integer only)
        while(!validInput) 
        {
            try 
            {
                choice = in.nextInt();
                validInput = true;
            } 
            catch(InputMismatchException e) 
            {
                System.out.println("Please enter a valid class number!");
                System.out.print("Choose Course Number: ");
                in.next();
            }
        }

        //Loops through all Course instances to call print roster method on selected course
        for(Course course : courseArray)
        { 
            if(course.getCourseID() == choice) //Finds desired Course instance
            {
                course.printRoster();
                break;
            }
        }     
    }
}
