package gradentia.models;

import java.security.InvalidParameterException;

import gradentia.models.CourseConstants.Department;

public class Course extends CourseTemplate
{
    private Department courseDepartment = Department.UNDEFINED; //Categorical department that a course belongs to. Intended for sorting.
    private double courseCreditHours = 0; //Credit hour value of the course. Used for credit hour summary.
    
    public Course(Integer ID)
    {
        super(ID);
    }

    public Course(CoursePlaceholder oldPlaceholder)
    {
        super(oldPlaceholder);
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

    public Department getDepartment()
    {
        return courseDepartment;
    }

    public double getCreditHours()
    {
        return courseCreditHours;
    }

    public String toString()
    {
        String returnString = super.toString();

        if(this.courseDepartment != Department.UNDEFINED) ///Appends Course Department to returnString
        {
            returnString += (" | "+this.courseDepartment.toString());
        }

        if(this.courseCreditHours != 0) //Appends Course Credit to returnString
        {
            returnString += (" | "+this.courseCreditHours);
        }

        return returnString;
    }
}