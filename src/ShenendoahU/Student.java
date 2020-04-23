/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShenendoahU;
public class Student {
    
    private String firstName;
    private String lastName;
    private String studentYear;
    private String studentMajor;
    private String studentEmail;
    private double GPA;
    private int studentID;
    private static int nextStudentID = 200000;
        
    public Student(String name, int year, String studentMajor, double GPA, 
            String studentEmail){
        setStudentName(name);
        setStudentYear(year);
        this.studentMajor = studentMajor;
        this.GPA = GPA;
        this.studentEmail = studentEmail;
        this.studentID = nextStudentID;
        nextStudentID++;        
    }   
    
    public Student(){
        this.studentID = nextStudentID;
        nextStudentID++;
    }
    
    public void setStudentName(String name){
        if(name.contains(" "))
        {
            String[] splitName = name.split(" ");
            this.firstName = splitName[0];
            this.lastName = splitName[1]; 
        }
        else
        {
            this.firstName = name;
            this.lastName = "";
        }        
    }
    
    public void setStudentYear(int year){
        switch(year){
            case 1:
                this.studentYear = "Freshman";
                break;
            case 2:
                this.studentYear = "Sophmore";
                break;
            case 3:
                this.studentYear = "Junior";
                break;
            case 4:
                this.studentYear = "Senior";
                break;
            default:
                this.studentYear = "Undefined";
                break;
        }
    }
        
    public void setGPA(double GPA){
        this.GPA = GPA;
    }
    
    //Checks to make sure GPA is valid, i.e. between 0 and 4 inclusive
    //If valid, initializes GPA and returns true, else does not init GPA and returns false
    public boolean setGPAWithCheck(double GPA)
    {
        if((GPA >= 0) && (GPA<=4))
        {
            this.GPA = GPA;
            return true;
        }
        else
            return false;
    }
    
    public void setStudentEmail(String studentEmail){
        this.studentEmail = studentEmail;
    }
    
    public boolean setStudentEmailWithCheck(String email){
        if(email.contains("@"))
        {
            this.studentEmail = email;
            return true;
        }
        else
            return false;        
    }
    
    public void setStudentMajor(String major){
        this.studentMajor = major;
    }         
    
    public double getGPA(){
        return this.GPA;
    }
    
    public String getName(){
        return String.format("%s %s",this.firstName,this.lastName);
    }
    
    public int getStudentID(){
        return this.studentID;
    }
    
    public String getStudentYear(){
        return this.studentYear;
    }
    
    public String getStudentMajor(){
        return this.studentMajor;
    }
}
