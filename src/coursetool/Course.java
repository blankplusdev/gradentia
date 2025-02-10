package coursetool;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class Course extends CourseTemplate
{
    private Department courseDepartment = Department.UNDEFINED; //Categorical department that a course belongs to. Intended for sorting.
    private double courseCreditHours = 0; //Credit hour value of the course. Used for credit hour summary.
    
    private ArrayList<CourseLink> courseLinks; //An arraylist containing all complete and incomplete courseLinks to other courses.

    public Course()
    {
        super(); //Currently unutilized. Favoring mutator methods for data assignment, allowing incomplete course info for user accesibility.
    }

    public void setDepartment(Department courseDepartment)
    {
        this.courseDepartment = courseDepartment;
    }

    public void setCreditHours(double courseCreditHours) throws InvalidParameterException
    {
        if(courseCreditHours < 0) {throw new InvalidParameterException("Course credit hours cannot be negative.");}

        this.courseCreditHours = courseCreditHours;
    }

    
    public void addPrerequisites(ArrayList<CourseTemplate> prerequisiteList) //Adds all prerequisites from the passed ArrayList
    {
        for(CourseTemplate prerequisite : prerequisiteList)
        {
            courseLinks.add(new CoursePrerequisite(this, prerequisite));
        }
    }

    public void addPrerequisites(Course parentCourse)
    {
        //Imports all prerequisites from method argument.
    }

    public Department getDepartment()
    {
        return courseDepartment;
    }

    public double getCreditHours()
    {
        return courseCreditHours;
    }

    public ArrayList<CourseLink> getcourseLinks()
    {
        return courseLinks;
    }
}