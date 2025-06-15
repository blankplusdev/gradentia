package gradentia.models;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public abstract class CourseTemplate
{
    protected String courseCode = "null"; //A string denoting the course code at the respective institution. Intended for user search and automatic course linking.
    protected String courseName = "null"; //Name of the course. Intended for user searching.
    protected final Integer ID;
    protected ArrayList<CourseLink> courseLinks;

    public CourseTemplate(Integer ID)
    {
        this.ID = ID;
        this.courseLinks = new ArrayList<CourseLink>();
    }

    public CourseTemplate(CoursePlaceholder oldPlaceholder)
    {
        this.ID = oldPlaceholder.getID();
        this.courseCode = oldPlaceholder.getCode();
        this.courseName = oldPlaceholder.getName();
        this.courseLinks = oldPlaceholder.getCourseLinks();
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

    public Integer getID()
    {
        return this.ID;
    }

    public String getCode()
    {
        return courseCode;
    }
    
    public String getName()
    {
        return courseName;
    }

    public String toString()
    {
        return (this.courseCode+" | \""+this.courseName+"\"");
    }

    public ArrayList<CourseLink> getCourseLinks()
    {
        return this.courseLinks;
    }
}
