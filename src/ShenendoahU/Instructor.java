/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShenendoahU;
public class Instructor {
    private String name;
    private String prefix;
    private String officeLocation;
    private String department;
    private String email;
    private int instructorID;
    private static int nextInstructID = 100000;
    
    public Instructor()
    {
        this.instructorID = nextInstructID;
        nextInstructID++;  
    }
    
    public Instructor(String name, String prefix, String officeLocation, String department)
    {
        this.name = name;
        this.prefix = prefix;
        this.officeLocation = officeLocation;
        this.department = department;
        this.instructorID = nextInstructID;
        nextInstructID++;        
    }
    
    public Instructor(String name, String prefix, String officeLocation, String department, 
            String email)
    {
        this.name = name;
        this.prefix = prefix;
        this.officeLocation = officeLocation;
        this.department = department;
        this.email = email;
        this.instructorID = nextInstructID;
        nextInstructID++;        
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }
    
    public void setDepartment(String department)
    {
        this.department = department;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public boolean setEmailWithCheck(String email){
        if(email.contains("@"))
        {
            this.email = email;
            return true;
        }
        else
            return false;        
    }
    
    public void setOfficeLocation(String officeLocation)
    {
        this.officeLocation = officeLocation;
    }
    
    public String getTitle()
    {
        return (this.prefix + " " + this.name);
    }
    
    public String toString()
    {
        return String.format("Instructor ID: %d Name: %-25s Department: %-15s Office Location: %-15s Email: %-30s", 
                this.instructorID,this.getTitle(),this.department,this.officeLocation,this.email);
    }
    
}
