package coursetool.core;

import coursetool.models.*;

import java.util.HashMap;

public class DataRepository
{

    public Integer nextID = 1;
    public HashMap<Integer, CourseTemplate> courses;

    public DataRepository() //Instantiates a new DataRepository Object, creating a new Course Hashmap.
    {
        this.courses = new HashMap<>();
    }

    public void addCourse() //Adds a Course object to the HashMap
    {
        Course newCourse = new Course(nextID);
        this.courses.put(nextID,newCourse);
        nextID++;
    }

    public void addCoursePlaceholder() //Adds a CoursePlaceholder object to the HashMap
    {
        CoursePlaceholder newCoursePlaceholder = new CoursePlaceholder(nextID);
        this.courses.put(nextID,newCoursePlaceholder);
        nextID++;
    }

    public void updatePlaceholderToCourse(CoursePlaceholder courseToModify) //Updates a CoursePlaceholder object by replacing it with a corresponding Course object. 
    {
        Course updateCourse = new Course(courseToModify);
        this.courses.replace(courseToModify.getID(),updateCourse);
    }

    public void removeFromHashMap(CourseTemplate objectToRemove)
    {
        this.courses.remove(objectToRemove.getID());
    }
}
