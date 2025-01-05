import java.util.ArrayList;

public class Course
{
    private String courseCode;
    private String courseName;
    private Department courseDepartment = Department.UNDEFINED;
    private double courseCreditHours = 0;
    
    private ArrayList<CourseLink> links;

    public Course(String courseCode, String courseName, Department courseDepartment, double creditHours)
    {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseDepartment = courseDepartment;
        this.courseCreditHours = creditHours;
    }


    
    public void addPrerequisites(ArrayList<Course> prerequisiteList)
    {
        for(Course prerequisite : prerequisiteList)
        {
            links.add(new CoursePrerequisite(this, prerequisite));
        }
    }



    public String getCode()
    {
        return courseCode;
    }

    public String getName()
    {
        return courseName;
    }

    public Department getDepartment()
    {
        return courseDepartment;
    }

    public double getCreditHours()
    {
        return courseCreditHours;
    }

    public ArrayList<CourseLink> getLinks()
    {
        return links;
    }
}