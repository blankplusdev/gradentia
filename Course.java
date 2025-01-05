import java.util.ArrayList;

public class Course
{
    public String courseCode;
    public String courseName;
    
    public Prerequisites prerequisites;
    public double creditHours;
    public int earliestSemester = 0; 



    public Course(String courseCode, String courseName, double creditHours)
    {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.creditHours = creditHours;
        this.prerequisites = new Prerequisites();
    }

    public void addPrerequisite(Course prerequisite)
    {
        this.prerequisites.addPrerequisite(prerequisite);
    }

    public void addPrerequisite(ArrayList<Course> prerequisites)
    {
        for(Course entry : prerequisites)
        {
            this.addPrerequisite(entry);
        }
    }   
}