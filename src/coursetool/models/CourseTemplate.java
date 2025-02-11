package coursetool.models;

import java.security.InvalidParameterException;

public class CourseTemplate
{
    protected String courseCode = "Undefined"; //A string denoting the course code at the respective institution. Intended for user search and automatic course linking.
    protected String courseName = "Undefined"; //Name of the course. Intended for user searching.

    public CourseTemplate()
    {
        //Currently unutilized. Favoring mutator methods for data assignment, allowing incomplete course info for user accesibility.
    }

    public void setCourseCode(String courseCode) throws InvalidParameterException //courseCode Assignment
    {
        if(courseCode == null) {throw new InvalidParameterException("Course code cannot be a null string.");}

        this.courseCode = courseCode;
    }

    public void setName(String courseName) //courseName Assignment
    {
        this.courseName = courseName;
    }

    public String getCode()
    {
        return courseCode;
    }
    
    public String getName()
    {
        return courseName;
    }
}
